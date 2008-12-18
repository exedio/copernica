/*
 * Copyright (C) 2004-2008  exedio GmbH (www.exedio.com)
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

import com.exedio.dsmf.Schema;

/**
 * Implements the defaultToNext functionality.
 * @see IntegerField#defaultToNext(int)
 *
 * @author Ralf Wiebicke
 */
final class DefaultToNextMaxImpl implements DefaultToNextImpl
{
	private final IntegerField field;
	private final int start;
	private boolean computed = false;
	private int next = Integer.MIN_VALUE;
	private final Object lock = new Object();

	DefaultToNextMaxImpl(final IntegerField field, final int start)
	{
		this.field = field;
		this.start = start;
	}
	
	public void makeSchema(final Schema schema)
	{
		// empty
	}
	
	public int next()
	{
		synchronized(lock)
		{
			final int result;
			if(computed)
			{
				result = next;
			}
			else
			{
				final Integer current = new Query<Integer>(field.max()).searchSingleton();
				result = current!=null ? (current.intValue() + 1) : start;
				computed = true;
			}
			
			next = result + 1;
			return result;
		}
	}
	
	public void flush()
	{
		synchronized(lock)
		{
			computed = false;
		}
	}
}