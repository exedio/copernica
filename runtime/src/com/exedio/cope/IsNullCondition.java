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

public final class IsNullCondition<E> extends Condition
{
	private final Function<E> function;
	private final boolean not;

	/**
	 * Creates a new IsNullCondition.
	 * Instead of using this constructor directly,
	 * you may want to use the more type-safe wrapper methods.
	 * @see com.exedio.cope.Function#isNull()
	 * @see com.exedio.cope.Function#isNotNull()
	 */
	public IsNullCondition(final Function<E> function, final boolean not)
	{
		if(function==null)
			throw new NullPointerException("function must not be null");

		this.function = function;
		this.not = not;
	}
	
	private final String sql()
	{
		return not ? " is not null" : " is null";
	}
	
	@Override
	void append(final Statement bf)
	{
		bf.append(function, null).
			append(sql());
	}

	@SuppressWarnings("deprecation") // OK: For internal use within COPE only
	@Override
	void check(final TC tc)
	{
		function.check(tc, null);
	}

	@Override
	public boolean equals(final Object other)
	{
		if(!(other instanceof IsNullCondition))
			return false;
		
		final IsNullCondition o = (IsNullCondition)other;
		
		return function.equals(o.function) && not==o.not;
	}
	
	@Override
	public int hashCode()
	{
		return function.hashCode() ^ (not ? 934658732 : 546637842);
	}

	@Override
	public String toString()
	{
		return function.toString() + sql();
	}
	
	@Override
	String toStringForQueryKey()
	{
		return toString();
	}
}
