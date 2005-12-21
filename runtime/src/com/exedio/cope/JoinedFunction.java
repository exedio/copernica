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


public final class JoinedFunction implements Function
{
	final Function function;
	final Join join;
	
	public JoinedFunction(final Function function, final Join join)
	{
		assert function!=null;
		assert join!=null;
		
		this.function = function;
		this.join = join;
	}

	public void append(final Statement bf, final Join join)
	{
		function.append(bf, this.join);
	}
	
	public void appendParameter(final Statement bf, final Object value)
	{
		bf.appendParameter(function, value);
	}
	
	public Type getType()
	{
		return function.getType();
	}
	
	public String getName()
	{
		return function.getName();
	}
	
	public boolean equals(final Object other)
	{
		if(!(other instanceof JoinedFunction))
			return false;
		
		final JoinedFunction o = (JoinedFunction)other;
		
		return function.equals(o.function) && join.equals(o.join);
	}
	
	public int hashCode()
	{
		return function.hashCode() ^ join.hashCode();
	}

	public String toString()
	{
		return function.toString();
	}
	
}
