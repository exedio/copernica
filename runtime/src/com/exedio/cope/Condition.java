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

import java.util.Iterator;
import java.util.List;

public abstract class Condition
{
	abstract void append(Statement statment);
	
	abstract void check(Query query);

	final void check(final Function function, final Query query)
	{
		final Type functionType = function.getType();

		final Type queryType = query.getType();
		if(functionType.isAssignableFrom(queryType))
			return;

		final List queryJoins = query.getJoins();
		if(queryJoins!=null)
		{
			for(Iterator i = queryJoins.iterator(); i.hasNext(); )
			{
				final Join join = (Join)i.next();
				if(functionType.isAssignableFrom(join.getType()))
					return;
			}
		}

		throw new RuntimeException(
			"function "
				+ function
				+ " belongs to type "
				+ functionType
				+ ", which is not a type of the query: "
				+ queryType
				+ ", "
				+ queryJoins);
	}

	final void check(final Type type, final Query query)
	{
		final Type queryType = query.getType();
		if(type==queryType)
			return;

		final List queryJoins = query.getJoins();
		if(queryJoins!=null)
		{
			for(Iterator i = queryJoins.iterator(); i.hasNext(); )
			{
				final Join join = (Join)i.next();
				if(type==join.getType())
					return;
			}
		}

		throw new RuntimeException(
				type.toString()
				+ " is not a type of the query: "
				+ queryType
				+ ", "
				+ queryJoins);
	}

	public final NotCondition not()
	{
		return new NotCondition(this);
	}
	
	public final CompositeCondition and(final Condition other)
	{
		return new CompositeCondition(CompositeCondition.Operator.AND, new Condition[]{this, other});
	}
	
	public final CompositeCondition or(final Condition other)
	{
		return new CompositeCondition(CompositeCondition.Operator.OR, new Condition[]{this, other});
	}
	
	public abstract boolean equals(Object o);
	public abstract int hashCode();
	
	static final boolean equals(final Object o1, final Object o2)
	{
		return o1==null ? o2==null : o1.equals(o2);
	}
	
	static final int hashCode(final Object o)
	{
		return o==null ? 0 : o.hashCode();
	}
}
