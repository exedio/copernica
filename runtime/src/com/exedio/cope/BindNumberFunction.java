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

import com.exedio.cope.search.SumAggregate;

public final class BindNumberFunction<E extends Number> extends BindFunction<E> implements NumberFunction<E>
{
	final NumberFunction<E> integerFunction;
	
	/**
	 * Instead of using this constructor directly,
	 * you may want to use the convenience methods.
	 * @see NumberFunction#bind(Join)
	 */
	public BindNumberFunction(final NumberFunction<E> function, final Join join)
	{
		super(function, join);
		this.integerFunction = function;
	}
	
	// convenience methods for conditions and views ---------------------------------

	/**
	 * Return this.
	 * It makes no sense wrapping a BindFunction into another BindFunction,
	 * because the inner BindFunction &quot;wins&quot;.
	 */
	@Override
	public BindNumberFunction<E> bind(final Join join)
	{
		return this;
	}
	
	public final PlusView<E> plus(final NumberFunction<E> other)
	{
		return new PlusView<E>(new NumberFunction[]{this, other});
	}

	/**
	 * @deprecated renamed to {@link #plus(NumberFunction)}.
	 */
	@Deprecated
	public final PlusView<E> sum(final NumberFunction<E> other)
	{
		return plus(other);
	}

	public final SumAggregate<E> sum()
	{
		return new SumAggregate<E>(this);
	}
}