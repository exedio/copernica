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

import java.util.Collection;
import java.util.List;

public final class CompositeCondition extends Condition
{
	private final Operator operator;
	public final Condition[] conditions;

	/**
	 * @throws NullPointerException if <tt>conditions==null</tt>
	 * @throws RuntimeException if <tt>conditions.size()==0</tt>
	 */
	public CompositeCondition(final Operator operator, final List<? extends Condition> conditions)
	{
		this(operator, conditions.toArray(new Condition[conditions.size()]));
	}
	
	/**
	 * @throws NullPointerException if <tt>conditions==null</tt>
	 * @throws RuntimeException if <tt>conditions.length==0</tt>
	 */
	public CompositeCondition(final Operator operator, final Condition[] conditions)
	{
		if(operator==null)
			throw new NullPointerException("operator must not be null");
		if(conditions==null)
			throw new NullPointerException("conditions must not be null");
		if(conditions.length==0)
			throw new RuntimeException("composite condition must have at least one subcondition");
		for(int i = 0; i<conditions.length; i++)
			if(conditions[i]==null)
				throw new NullPointerException("condition " + i + " must not be null");
				

		this.operator = operator;
		this.conditions = conditions;
	}

	void append(final Statement bf)
	{
		bf.append('(');
		conditions[0].append(bf);
		for(int i = 1; i<conditions.length; i++)
		{
			bf.append(operator.sql);
			conditions[i].append(bf);
		}
		bf.append(')');
	}

	void check(final Query query)
	{
		for(int i = 0; i<conditions.length; i++)
			conditions[i].check(query);
	}
	
	public boolean equals(final Object other)
	{
		if(!(other instanceof CompositeCondition))
			return false;
		
		final CompositeCondition o = (CompositeCondition)other;
		
		if(!operator.equals(o.operator) || conditions.length!=o.conditions.length)
			return false;
		
		for(int i = 0; i<conditions.length; i++)
		{
			if(!conditions[i].equals(o.conditions[i]))
				return false;
		}

		return true;
	}
	
	public int hashCode()
	{
		int result = operator.hashCode();
		
		for(int i = 0; i<conditions.length; i++)
			result = (31*result) + conditions[i].hashCode(); // may not be commutative

		return result;
	}

	public String toString()
	{
		final StringBuffer buf = new StringBuffer();
		
		buf.append('(');
		buf.append(conditions[0].toString());
		for(int i = 1; i<conditions.length; i++)
		{
			buf.append(operator);
			buf.append(conditions[i].toString());
		}
		buf.append(')');
		
		return buf.toString();
	}

	public static final <E> CompositeCondition in(final Function<E> function, final Collection<E> values)
	{
		final EqualCondition[] result = new EqualCondition[values.size()];

		int i = 0;
		for(E value : values)
			result[i++] = function.equal(value);
		
		return new CompositeCondition(Operator.OR, result);
	}
	
	static enum Operator
	{
		AND(" and "),
		OR(" or ");
		
		private final String sql;
		
		Operator(final String sql)
		{
			this.sql = sql;
		}
	}

}
