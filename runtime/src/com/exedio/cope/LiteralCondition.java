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


public final class LiteralCondition<E> extends Condition
{
	private final Operator operator;
	private final Function<E> function;
	private final E value;

	/**
	 * Creates a new LiteralCondition.
	 * Instead of using this constructor directly,
	 * you may want to use the convenience methods.
	 * @see com.exedio.cope.Function#less(Object)
	 * @see com.exedio.cope.Function#lessOrEqual(Object)
	 * @see com.exedio.cope.Function#greater(Object)
	 * @see com.exedio.cope.Function#greaterOrEqual(Object)
	 */
	public LiteralCondition(final Operator operator, final Function<E> function, final E value)
	{
		this.operator = operator;
		this.function = function;
		this.value = value;

		if(operator==null)
			throw new NullPointerException();
		if(function==null)
			throw new NullPointerException();
		if(value==null)
			throw new NullPointerException();
	}
	
	void append(final Statement bf)
	{
		bf.append(function, (Join)null).
			append(operator.sql).
			appendParameter(function, value);
	}

	void check(final Query query)
	{
		check(function, query);
	}

	public boolean equals(final Object other)
	{
		if(!(other instanceof LiteralCondition))
			return false;
		
		final LiteralCondition o = (LiteralCondition)other;
		
		return operator.equals(o.operator) && function.equals(o.function) && value.equals(o.value);
	}
	
	public int hashCode()
	{
		return operator.hashCode() ^ function.hashCode() ^ value.hashCode() ^ 918276;
	}

	public String toString()
	{
		return function.toString() + operator.sql + '\'' + value + '\'';
	}

	public static enum Operator
	{
		Less("<"),
		LessEqual("<="),
		Greater(">"),
		GreaterEqual(">=");
		
		private final String sql;
		
		Operator(final String sql)
		{
			this.sql = sql;
		}
	}

}
