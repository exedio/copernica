/*
 * Copyright (C) 2004-2005  exedio GmbH (www.exedio.com)
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

import bak.pcj.map.IntKeyMap;
import bak.pcj.map.IntKeyOpenHashMap;
import bak.pcj.set.IntSet;

final class Cache
{
	private final IntKeyMap[] stateMaps;
	
	Cache( int numberOfTypes )
	{
		stateMaps = new IntKeyMap[numberOfTypes];
		for ( int i=0; i<numberOfTypes; i++ )
		{
			stateMaps[i] = new IntKeyOpenHashMap();
		}
	}
	
	private IntKeyMap getStateMap( Type type )
	{
		return getStateMap( type.transientNumber );
	}
	
	private IntKeyMap getStateMap( int transientTypeNumber )
	{
		return stateMaps[ transientTypeNumber ];
	}
	
	PersistentState getPersistentState( final Transaction connectionSource, final Item item )
	{
		PersistentState state;
		IntKeyMap stateMap = getStateMap( item.type );
		synchronized (stateMap)
		{
			state = (PersistentState)stateMap.get( item.pk );
		}
		if ( state==null )
		{
			state = new PersistentState( connectionSource.getConnection(), item );
			Object oldValue;
			synchronized (stateMap)
			{
				oldValue = stateMap.put( item.pk, state );
			}
			if ( oldValue!=null )
			{
				System.out.println("warning: duplicate computation of state "+item.getCopeID());
			}
		}
		return state;
	}
	
	void invalidate( int transientTypeNumber, IntSet invalidatedPKs )
	{
		IntKeyMap stateMap = getStateMap( transientTypeNumber );
		synchronized ( stateMap )
		{
			stateMap.keySet().removeAll( invalidatedPKs );
		}
	}

	void clear()
	{
		for ( int i=0; i<stateMaps.length; i++ )
		{
			IntKeyMap stateMap = getStateMap( i );
			synchronized ( stateMap )
			{
				stateMap.clear();
			}
		}
	}
}
