/*
 * Copyright (C) 2004-2006  exedio GmbH (www.exedio.com)
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


public final class JoinedItemFunction<E extends Item> extends JoinedFunction<E> implements ItemFunction<E>
{
	final ItemFunction<E> itemFunction;
	
	public JoinedItemFunction(final ItemFunction<E> function, final Join join)
	{
		super(function, join);
		this.itemFunction = function;
	}
	
	public Type<E> getValueType()
	{
		return itemFunction.getValueType();
	}
	
	public StringColumn getTypeColumnIfExists()
	{
		return itemFunction.getTypeColumnIfExists();
	}
	
	public boolean needsCheckTypeColumn()
	{
		return itemFunction.needsCheckTypeColumn();
	}

	public int checkTypeColumn()
	{
		return itemFunction.checkTypeColumn();
	}
	
	// convenience methods for conditions and views ---------------------------------

	public TypeInCondition<E> typeIn(final Type<? extends E> type1)
	{
		return new TypeInCondition<E>(this, false, type1);
	}

	public TypeInCondition<E> typeIn(final Type<? extends E> type1, final Type<? extends E> type2)
	{
		return new TypeInCondition<E>(this, false, type1, type2);
	}

	public TypeInCondition<E> typeIn(final Type<? extends E> type1, final Type<? extends E> type2, final Type<? extends E> type3)
	{
		return new TypeInCondition<E>(this, false, type1, type2, type3);
	}

	public TypeInCondition<E> typeIn(final Type<? extends E> type1, final Type<? extends E> type2, final Type<? extends E> type3, final Type<E> type4)
	{
		return new TypeInCondition<E>(this, false, type1, type2, type3, type4);
	}

	public TypeInCondition<E> typeIn(final Type[] types)
	{
		return new TypeInCondition<E>(this, false, types);
	}
	
	public TypeInCondition<E> typeNotIn(final Type<? extends E> type1)
	{
		return new TypeInCondition<E>(this, true, type1);
	}

	public TypeInCondition<E> typeNotIn(final Type<? extends E> type1, final Type<? extends E> type2)
	{
		return new TypeInCondition<E>(this, true, type1, type2);
	}

	public TypeInCondition<E> typeNotIn(final Type<? extends E> type1, final Type<? extends E> type2, final Type<? extends E> type3)
	{
		return new TypeInCondition<E>(this, true, type1, type2, type3);
	}

	public TypeInCondition<E> typeNotIn(final Type<? extends E> type1, final Type<? extends E> type2, final Type<? extends E> type3, final Type<E> type4)
	{
		return new TypeInCondition<E>(this, true, type1, type2, type3, type4);
	}

	public TypeInCondition<E> typeNotIn(final Type[] types)
	{
		return new TypeInCondition<E>(this, true, types);
	}
}
