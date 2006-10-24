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


public final class Join
{
	static enum Kind
	{
		INNER("join "),
		OUTER_LEFT("left join "),
		OUTER_RIGHT("right join ");
		
		final String sql;
		
		Kind(final String sql)
		{
			this.sql = sql;
		}
	}
	
	final Kind kind;
	final Type type;
	Condition condition;
	
	Join(final Kind kind, final Type type, final Condition condition)
	{
		this.kind = kind;
		this.type = type;
		this.condition = condition;

		if(kind==null)
			throw new NullPointerException("kind must not be null");
		if(type==null)
			throw new NullPointerException("type must not be null");
	}
	
	public void setCondition(final Condition condition)
	{
		this.condition = condition;
	}
	
	public final Kind getKind()
	{
		return kind;
	}
	
	public final Type getType()
	{
		return type;
	}
	
	@Override
	public boolean equals(final Object other)
	{
		final Join o = (Join)other;
		return
			kind==o.kind &&
			type==o.type &&
			condition==o.condition; // TODO SOON should use equals, but this causes infinite recursion
	}
	
	@Override
	public int hashCode()
	{
		return
			kind.hashCode() ^
			type.hashCode() ^
			(condition==null ? 0 : System.identityHashCode(condition)); // TODO SOON should use hashCode(), but this causes infinite recursion
	}
	
	@Override
	public final String toString()
	{
		return kind.sql + type + (condition!=null ? (" on "+condition) : "");
	}

}
