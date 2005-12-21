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


public final class NotCondition extends Condition
{
	private final Condition argument; 

	/**
	 * Creates a new NotCondition.
	 * Instead of using this constructor directly,
	 * you may want to use the more type-safe wrapper method.
	 * @see Condition#not()
	 * @throws NullPointerException if <code>argument</code> is null.
	 */
	public NotCondition(final Condition argument)
	{
		if(argument==null)
			throw new NullPointerException();
		
		this.argument = argument;
	}
	
	void appendStatement(final Statement bf)
	{
		bf.append("not(");
		argument.appendStatement(bf);
		bf.append(')');
	}

	void check(final Query query)
	{
		argument.check(query);
	}

	public boolean equals(final Object other)
	{
		if(!(other instanceof NotCondition))
			return false;
		
		final NotCondition o = (NotCondition)other;
		
		return argument.equals(o.argument);
	}
	
	public int hashCode()
	{
		return argument.hashCode() ^ 8432756;
	}

	public final String toString()
	{
		return "!(" + argument + ')';
	}

}
