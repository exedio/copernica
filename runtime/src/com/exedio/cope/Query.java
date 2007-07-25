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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class Query<R>
{
	final static int UNLIMITED = -66;
	
	final Model model;
	final Selectable[] selects;
	boolean distinct = false;
	final Type<?> type;
	private int joinIndex = 0;
	ArrayList<Join> joins = null;
	Condition condition;

	Function[] orderBy = null;
	boolean[] orderAscending;
	
	int offset = 0;
	int limit = UNLIMITED;
	
	public Query(final Selectable<? extends R> select)
	{
		this(select, (Condition)null);
	}
	
	public Query(final Selectable<? extends R> select, final Condition condition)
	{
		this.selects = new Selectable[]{select};
		this.type = select.getType();
		this.model = this.type.getModel();
		this.condition = replaceTrue(condition);
	}
	
	public Query(final Selectable<R> select, final Type type, final Condition condition)
	{
		this.model = type.getModel();
		this.selects = new Selectable[]{select};
		this.type = type;
		this.condition = replaceTrue(condition);
	}
	
	public Query(final Selectable[] selects, final Type type, final Condition condition)
	{
		this.model = type.getModel();
		this.selects = selects;
		this.type = type;
		this.condition = replaceTrue(condition);
	}
	
	public boolean isDistinct()
	{
		return distinct;
	}
	
	public void setDistinct(final boolean distinct)
	{
		this.distinct = distinct;
	}
	
	public Type getType()
	{
		return type;
	}
	
	public void setCondition(final Condition condition)
	{
		this.condition = replaceTrue(condition);
	}
	
	public Condition getCondition()
	{
		return this.condition;
	}

	/**
	 * If there is already a condition set for this query,
	 * this is equivalent to
	 * <tt>{@link #setCondition(Condition) setCondition}({@link #getCondition()}.{@link Condition#and(Condition) and}(narrowingCondition))</tt>.
	 */
	public void narrow(final Condition narrowingCondition)
	{
		condition =
			condition!=null
			? condition.and(narrowingCondition)
			: narrowingCondition;
	}
	
	private Join join(final Join join)
	{
		if(joins==null)
			joins = new ArrayList<Join>();
		
		joins.add(join);

		return join;
	}
	
	/**
	 * Does an inner join with the given type without any join condition.
	 */
	public Join join(final Type type)
	{
		return join(new Join(joinIndex++, Join.Kind.INNER, type, null));
	}
	
	/**
	 * Does an inner join with the given type on the given join condition.
	 */
	public Join join(final Type type, final Condition condition)
	{
		return join(new Join(joinIndex++, Join.Kind.INNER, type, condition));
	}
	
	public Join joinOuterLeft(final Type type, final Condition condition)
	{
		return join(new Join(joinIndex++, Join.Kind.OUTER_LEFT, type, condition));
	}
	
	public Join joinOuterRight(final Type type, final Condition condition)
	{
		return join(new Join(joinIndex++, Join.Kind.OUTER_RIGHT, type, condition));
	}
	
	public List<Join> getJoins()
	{
		return joins==null ? Collections.<Join>emptyList() : Collections.unmodifiableList(joins);
	}
	
	public List<Function> getOrderByFunctions()
	{
		return
			orderBy==null
			? Collections.<Function>emptyList()
			: Collections.unmodifiableList(Arrays.asList(orderBy));
	}
	
	public List<Boolean> getOrderByAscending()
	{
		if(orderAscending==null)
			return Collections.<Boolean>emptyList();
		
		final ArrayList<Boolean> result = new ArrayList<Boolean>(orderAscending.length);
		for(int i = 0; i<orderAscending.length; i++)
			result.add(orderAscending[i]);
		return Collections.unmodifiableList(result);
	}
	
	public void setOrderByThis(final boolean ascending)
	{
		this.orderBy = new Function[]{type.thisFunction};
		this.orderAscending = new boolean[]{ascending};
	}
	
	public void setOrderBy(final Function orderBy, final boolean ascending)
	{
		if(orderBy==null)
			throw new NullPointerException("orderBy is null");
		
		this.orderBy = new Function[]{orderBy};
		this.orderAscending = new boolean[]{ascending};
	}
	
	public void setOrderByAndThis(final Function orderBy, final boolean ascending)
	{
		if(orderBy==null)
			throw new NullPointerException("orderBy is null");
		
		this.orderBy = new Function[]{orderBy, type.thisFunction};
		this.orderAscending = new boolean[]{ascending, true};
	}
	
	/**
	 * @throws IllegalArgumentException if <tt>orderBy.length!=ascending.length</tt>
	 */
	public void setOrderBy(final Function[] orderBy, final boolean[] ascending)
	{
		if(orderBy.length!=ascending.length)
			throw new IllegalArgumentException("orderBy and ascending must have same length, but was "+orderBy.length+" and "+ascending.length);
		for(int i = 0; i<orderBy.length; i++)
			if(orderBy[i]==null)
				throw new NullPointerException("orderBy contains null at index "+i);
		
		this.orderBy = orderBy;
		this.orderAscending = ascending;
	}

	public void addOrderBy(final Function orderBy)
	{
		addOrderBy(orderBy, true);
	}
	
	public void addOrderByDescending(final Function orderBy)
	{
		addOrderBy(orderBy, false);
	}
	
	public void addOrderBy(final Function orderBy, final boolean ascending)
	{
		if(this.orderBy==null)
			this.orderBy = new Function[]{ orderBy };
		else
		{
			final int l = this.orderBy.length;
			final Function[] result = new Function[l+1];
			System.arraycopy(this.orderBy, 0, result, 0, l);
			result[l] = orderBy;
			this.orderBy = result;
		}

		if(this.orderAscending==null)
			this.orderAscending = new boolean[]{ ascending };
		else
		{
			final int l = this.orderAscending.length;
			final boolean[] result = new boolean[l+1];
			System.arraycopy(this.orderAscending, 0, result, 0, l);
			result[l] = ascending;
			this.orderAscending = result;
		}
	}
	
	public void resetOrderBy()
	{
		orderBy = null;
		orderAscending = null;
	}
	
	/**
	 * @see #setLimit(int)
	 * @param limit the maximum number of items to be found.
	 * @throws IllegalArgumentException if offset is a negative value
	 * @throws IllegalArgumentException if limit is a negative value
	 */
	public void setLimit(final int offset, final int limit)
	{
		if(offset<0)
			throw new IllegalArgumentException("offset must not be negative, but was " + offset);
		if(limit<0)
			throw new IllegalArgumentException("limit must not be negative, but was " + limit);

		this.offset = offset;
		this.limit = limit;
	}
	
	/**
	 * @see #setLimit(int, int)
	 * @throws IllegalArgumentException if offset is a negative value
	 */
	public void setLimit(final int offset)
	{
		if(offset<0)
			throw new IllegalArgumentException("offset must not be negative, but was " + offset);

		this.offset = offset;
		this.limit = UNLIMITED;
	}
	
	/**
	 * Searches for items matching this query.
	 * <p>
	 * Returns an unmodifiable collection.
	 * Any attempts to modify the returned collection, whether direct or via its iterator,
	 * result in an <tt>UnsupportedOperationException</tt>.
	 */
	public List<R> search()
	{
		final Transaction transaction = model.getCurrentTransaction();
		
		if(limit==0 || condition==Condition.FALSE)
		{
			final List<QueryInfo> queryInfos = transaction.queryInfos;
			if(queryInfos!=null)
				queryInfos.add(new QueryInfo("skipped search because " + (limit==0 ? "limit==0" : "condition==false")));
			return Collections.<R>emptyList();
		}
		
		return Collections.unmodifiableList(castQL(transaction.search(this, false)));
	}
	
	@SuppressWarnings("unchecked") // TODO: Database#search does not support generics
	private List<R> castQL(final List o)
	{
		return o;
	}
	
	/**
	 * Counts the items matching this query.
	 * <p>
	 * Returns the
	 * {@link Collection#size() size} of what
	 * {@link #search()} would have returned for this query with
	 * {@link #setLimit(int)} reset set to <tt>(0)</tt>.
	 */
	public int countWithoutLimit()
	{
		final ArrayList<Object> result = model.getCurrentTransaction().search(this, true);
		assert result.size()==1;
		return ((Integer)result.iterator().next()).intValue();
	}

	TC check()
	{
		final TC tc = new TC(this);
		
		for(final Selectable select : selects)
			Cope.check(select, tc, null);
		
		if(condition!=null)
			condition.check(tc);

		if(joins!=null)
		{
			for(final Join j : joins)
			{
				final Condition c = j.condition;
				if(c!=null)
					c.check(tc);
			}
		}
		
		if(orderBy!=null)
			for(Function ob : orderBy)
				Cope.check(ob, tc, null);
		
		return tc;
	}

	/**
	 * Searches for items matching this query.
	 * <p>
	 * Returns a {@link Result} containing the
	 * {@link Result#getData() data} and the
	 * {@link Result#getCountWithoutLimit() countWithoutLimit}.
	 * The {@link Result#getData() data} is equal to
	 * what {@link #search()} would have returned for this query.
	 * The {@link Result#getCountWithoutLimit() countWithoutLimit} is equal to what
	 * {@link #countWithoutLimit()} would have returned for this query.
	 * <p>
	 * This method does it's best to avoid issuing two queries
	 * for searching and counting.
	 */
	public Result<R> searchAndCountWithoutLimit()
	{
		final List<R> data = search();
		final int dataSize = data.size();

		return new Result<R>(data,
				(((dataSize>0) || (offset==0))  &&  ((dataSize<limit) || (limit==UNLIMITED)))
				? (offset+dataSize)
				: countWithoutLimit());
	}
	
	public static final class Result<R>
	{
		final List<R> data;
		final int countWithoutLimit;
		
		private Result(final List<R> data, final int countWithoutLimit)
		{
			assert data!=null;
			assert countWithoutLimit>=0 : countWithoutLimit;
			
			this.data = data;
			this.countWithoutLimit = countWithoutLimit;
		}
		
		public List<R> getData()
		{
			return data;
		}
		
		public int getCountWithoutLimit()
		{
			return countWithoutLimit;
		}
		
		@Override
		public boolean equals(final Object o)
		{
			final Result or = (Result)o;

			return countWithoutLimit==or.countWithoutLimit && data.equals(or.data);
		}
		
		@Override
		public String toString()
		{
			return data.toString() + '(' + countWithoutLimit + ')';
		}
	}
	
	/**
	 * Searches equivalently to {@link #search()},
	 * but assumes that the search result has at most one element.
	 * <p>
	 * Returns null, if the search result is {@link Collection#isEmpty() empty},
	 * returns the only element of the search result, if the result {@link Collection#size() size} is exactly one.
	 * @throws IllegalArgumentException if the search result size is greater than one.
	 * @see Type#searchSingleton(Condition)
	 */
	public R searchSingleton()
	{
		final List<R> searchResultCollection = search();
		final Iterator<R> searchResult = searchResultCollection.iterator();
		if(searchResult.hasNext())
		{
			final R result = searchResult.next();
			if(searchResult.hasNext())
				throw new IllegalArgumentException("expected result of size one or less, but was " + searchResultCollection + " for query: " + toString());
			else
				return result;
		}
		else
			return null;
	}
	
	/**
	 * @deprecated renamed to {@link #searchSingleton()}.
	 */
	@Deprecated
	public R searchUnique()
	{
		return searchSingleton();
	}
	
	@Override
	public String toString()
	{
		return toString(false, false);
	}
	
	String toString(final boolean key, final boolean doCountOnly)
	{
		final Type type = this.type;
		final StringBuffer bf = new StringBuffer();
		
		bf.append("select ");
		
		if(distinct)
			bf.append("distinct ");

		if(doCountOnly)
		{
			bf.append("count(*)");
		}
		else
		{
			for(int i = 0; i<selects.length; i++)
			{
				if(i>0)
					bf.append(',');
	
				selects[i].toString(bf, type);
			}
		}

		bf.append(" from ").
			append(type);

		if(joins!=null)
		{
			for(final Join join : joins)
				join.toString(bf, key, type);
		}

		if(condition!=null)
		{
			bf.append(" where ");
			condition.toString(bf, key, type);
		}

		if(!doCountOnly)
		{
			if(orderBy!=null)
			{
				bf.append(" order by ");
				for(int i = 0; i<orderBy.length; i++)
				{
					if(i>0)
						bf.append(", ");
					
					orderBy[i].toString(bf, type);
					if(!orderAscending[i])
						bf.append(" desc");
				}
			}

			if(offset>0)
				bf.append(" offset '").
					append(offset).
					append('\'');
			
			if(limit!=UNLIMITED)
				bf.append(" limit '").
					append(limit).
					append('\'');
		}
		
		return bf.toString();
	}
	
	ArrayList<Object> searchUncached(final Transaction transaction, final boolean doCountOnly)
	{
		return model.getDatabase().search(transaction.getConnection(), this, doCountOnly, transaction.queryInfos);
	}
	
	private static final Condition replaceTrue(final Condition c)
	{
		return c==Condition.TRUE ? null : c;
	}
}
