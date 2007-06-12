/*
 * Copyright (C) 2004-2007  exedio GmbH (www.exedio.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.exedio.cope;

import gnu.trove.TIntArrayList;
import gnu.trove.TIntHashSet;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.exedio.cope.util.CacheQueryInfo;

final class QueryCache
{
	private final LRUMap<Key, ArrayList<Object>> map;
	private volatile int hits = 0, misses = 0;
	private final boolean histogram;

	QueryCache(final int limit, final boolean histogram)
	{
		this.map = limit>0 ? new LRUMap<Key, ArrayList<Object>>(limit) : null;
		this.histogram = histogram;
	}
	
	ArrayList<Object> search(final Transaction transaction, final Query<?> query, final boolean doCountOnly)
	{
		if(map==null)
		{
			throw new RuntimeException( "search in cache must not be called if query caching is disabled" );
		}
		final Key key = new Key(query, doCountOnly);
		ArrayList<Object> result;
		synchronized(map)
		{
			result = map.get(key);
		}
		if ( result==null )
		{
			result = query.searchUncached(transaction, doCountOnly);
			key.prepareForPut(query);
			synchronized(map)
			{
				map.put(key, result);
			}
			misses++;
		}
		else
		{
			final List<QueryInfo> queryInfos = transaction.queryInfos;
			if(histogram || queryInfos!=null)
			{
				final Key originalKey;
				synchronized(map)
				{
					originalKey = map.getKeyIfHackSucceeded(key);
				}
				if(originalKey!=null)
					originalKey.hits++;
				
				if(queryInfos!=null)
					queryInfos.add(new QueryInfo("query cache hit #" + originalKey.hits + " for " + originalKey.getText()));
			}
			hits++;
		}
		
		return result;
	}
	
	boolean isEnabled()
	{
		return map!=null;
	}
	
	void invalidate(final TIntHashSet[] invalidations)
	{
		final TIntArrayList invalidatedTypesTransientlyList = new TIntArrayList();

		for(int typeTransiently=0; typeTransiently<invalidations.length; typeTransiently++)
			if(invalidations[typeTransiently]!=null)
				invalidatedTypesTransientlyList.add(typeTransiently);
		
		if(map!=null && !invalidatedTypesTransientlyList.isEmpty())
		{
			final int[] invalidatedTypesTransiently = invalidatedTypesTransientlyList.toNativeArray();
			
			synchronized(map)
			{
				final Iterator<Key> keys = map.keySet().iterator();
				while(keys.hasNext())
				{
					final Key key = keys.next();
					query: for(final int queryTypeTransiently : key.invalidationTypesTransiently)
					{
						for(final int invalidatedTypeTransiently : invalidatedTypesTransiently)
						if(queryTypeTransiently==invalidatedTypeTransiently)
						{
							keys.remove();
							break query;
						}
					}
				}
			}
		}
	}
	
	void clear()
	{
		if(map!=null)
		{
			synchronized(map)
			{
				map.clear();
			}
		}
	}
	
	int[] getQueryInfo()
	{
		final int level;
		
		if(map!=null)
		{
			synchronized(map)
			{
				level = map.size();
			}
		}
		else
			level = 0;
		
		return new int[]{hits, misses, level};
	}
	
	CacheQueryInfo[] getHistogram()
	{
		if(map==null)
			return new CacheQueryInfo[0];
		
		final Key[] keys;
		final ArrayList[] values;
		synchronized(map)
		{
			keys = map.keySet().toArray(new Key[map.size()]);
			values = map.values().toArray(new ArrayList[map.size()]);
		}

		final CacheQueryInfo[] result = new CacheQueryInfo[keys.length];
		int i = result.length-1;
		int j = 0;
		for(final Key key : keys)
			result[i--] = new CacheQueryInfo(key.getText(), values[j++].size(), key.hits);
		
		return result;
	}
	
	private static final class Key
	{
		private final byte[] text;
		int[] invalidationTypesTransiently = null;
		volatile int hits = 0;
		
		private static final String CHARSET = "utf8";
		
		Key(final Query<? extends Object> query, final boolean doCountOnly)
		{
			try
			{
				text = query.toString(true, doCountOnly).getBytes(CHARSET);
			}
			catch(UnsupportedEncodingException e)
			{
				throw new RuntimeException(e);
			}
			// TODO compress
		}
		
		/**
		 * @param query must be the same as in the constructor!
		 */
		void prepareForPut(final Query<? extends Object> query)
		{
			assert this.invalidationTypesTransiently==null;
			
			final ArrayList<Join> joins = query.joins;
			final TIntHashSet typeSet = new TIntHashSet();
			for(final Type<?> t : query.type.getTypesOfInstances())
				typeSet.add(t.idTransiently);
			if(joins!=null)
			{
				for(final Join join : joins)
					for(final Type t : join.type.getTypesOfInstances())
						typeSet.add(t.idTransiently);
			}
			this.invalidationTypesTransiently = typeSet.toArray();
		}
		
		@Override
		public boolean equals(final Object obj)
		{
			final Key other = (Key)obj;
			return Arrays.equals(text, other.text);
		}
		
		@Override
		public int hashCode()
		{
			return Arrays.hashCode(text);
		}
		
		String getText()
		{
			try
			{
				return new String(text, CHARSET);
			}
			catch(UnsupportedEncodingException e)
			{
				throw new RuntimeException(e);
			}
		}
		
		@Override
		public String toString()
		{
			return getText();
		}
	}
	
	private static final class LRUMap<K,V> extends LinkedHashMap<K,V>
	{
		private static final long serialVersionUID = 19641264861283476l;
		
		private final int maxSize;
		
		LRUMap(final int maxSize)
		{
			super(maxSize, 0.75f/*DEFAULT_LOAD_FACTOR*/, true);
			this.maxSize = maxSize;
		}
		
		private static Method getEntry = null;

		static
		{
			try
			{
				final Method m = HashMap.class.getDeclaredMethod("getEntry", Object.class);
				m.setAccessible(true);
				getEntry = m;
			}
			catch(Exception e)
			{
				System.out.println("Accessing getEntry method failed, no query histograms available: " + e.getMessage());;
			}
		}
		
		@SuppressWarnings("unchecked") // OK: reflection does not support generics
		K getKeyIfHackSucceeded(final K key)
		{
			if(getEntry==null)
				return null;
			
			try
			{
				final Map.Entry e = (Map.Entry)getEntry.invoke(this, key);
				return (K)e.getKey();
			}
			catch(InvocationTargetException e)
			{
				throw new RuntimeException(e);
			}
			catch(IllegalAccessException e)
			{
				throw new RuntimeException(e);
			}
		}
		
		@Override
		protected boolean removeEldestEntry(final Map.Entry<K,V> eldest)
		{
			//System.out.println("-----eldest("+size()+"):"+eldest.getKey());
			return size() > maxSize;
		}
	}
}
