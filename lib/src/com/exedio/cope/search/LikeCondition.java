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

package com.exedio.cope.search;

import com.exedio.cope.Join;
import com.exedio.cope.Query;
import com.exedio.cope.Statement;
import com.exedio.cope.StringFunction;

/**
 * @author Ralf Wiebicke
 */
public final class LikeCondition extends Condition
{
	public final StringFunction function;
	public final String value;

	/**
	 * Creates a new LikeCondition.
	 * Instead of using this constructor directly,
	 * you may want to use the more convenient wrapper method
	 * {@link StringFunction#like(String)}.
	 */
	public LikeCondition(final StringFunction function, final String value)
	{
		this.function = function;
		this.value = value;

		if(function==null)
			throw new NullPointerException("function must ot be null");
		if(value==null)
			throw new NullPointerException("value must ot be null");
	}
	
	public final void appendStatement(final Statement bf)
	{
		bf.append(function, (Join)null).
			append(" like ").
			appendValue(function, value);
	}

	public final void check(final Query query)
	{
		check(function, query);
	}

	public boolean equals(final Object other)
	{
		if(!(other instanceof LikeCondition))
			return false;
		
		final LikeCondition o = (LikeCondition)other;
		
		return function.equals(o.function) && value.equals(o.value);
	}
	
	public int hashCode()
	{
		return function.hashCode() ^ value.hashCode() ^ 1872643;
	}

	public final String toString()
	{
		return function.getName() + " like '" + value + '\'';
	}
	
}
