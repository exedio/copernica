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

import com.exedio.cope.function.SumView;
import com.exedio.cope.search.GreaterCondition;
import com.exedio.cope.search.GreaterEqualCondition;
import com.exedio.cope.search.LessCondition;
import com.exedio.cope.search.LessEqualCondition;

public final class IntegerAttribute extends FunctionAttribute<Integer> implements IntegerFunction
{

	private IntegerAttribute(final boolean isfinal, final boolean optional, final boolean unique, final Integer defaultValue)
	{
		super(isfinal, optional, unique, defaultValue);
		checkDefaultValue();
	}
	
	public IntegerAttribute(final Option option)
	{
		this(option.isFinal, option.optional, option.unique, null);
	}
	
	public FunctionAttribute<Integer> copyFunctionAttribute()
	{
		return new IntegerAttribute(isfinal, optional, implicitUniqueConstraint!=null, defaultValue);
	}

	public IntegerAttribute defaultTo(final Integer defaultValue)
	{
		return new IntegerAttribute(isfinal, optional, implicitUniqueConstraint!=null, defaultValue);
	}
	
	@Override
	Class initialize(final java.lang.reflect.Type genericType)
	{
		return Integer.class;
	}
	
	Column createColumn(final Table table, final String name, final boolean optional)
	{
		return new IntegerColumn(table, name, optional, 10, false, null);
	}
	
	Integer get(final Row row)
	{
		return (Integer)row.get(getColumn());
	}
	
	void set(final Row row, final Integer surface)
	{
		row.put(getColumn(), surface);
	}
	
	/**
	 * @throws RuntimeException if this attribute is not {@link #isMandatory() mandatory}.
	 */
	public final int getMandatory(final Item item)
	{
		if(optional)
			throw new RuntimeException("attribute " + toString() + " is not mandatory");
		
		return get(item).intValue();
	}
	
	public final void set(final Item item, final int value)
		throws
			UniqueViolationException,
			FinalViolationException
	{
		set(item, Integer.valueOf(value));
	}

	public final SetValue map(final int value)
	{
		return new SetValue(this, Integer.valueOf(value));
	}
	
	public final EqualCondition equal(final int value)
	{
		return new EqualCondition<Integer>(this, Integer.valueOf(value));
	}
	
	public final NotEqualCondition notEqual(final int value)
	{
		return new NotEqualCondition<Integer>(this, Integer.valueOf(value));
	}
	
	public final LessCondition less(final int value)
	{
		return new LessCondition<Integer>(this, Integer.valueOf(value));
	}
	
	public final LessEqualCondition lessOrEqual(final int value)
	{
		return new LessEqualCondition<Integer>(this, Integer.valueOf(value));
	}
	
	public final GreaterCondition greater(final int value)
	{
		return new GreaterCondition<Integer>(this, Integer.valueOf(value));
	}
	
	public final GreaterEqualCondition greaterOrEqual(final int value)
	{
		return new GreaterEqualCondition<Integer>(this, Integer.valueOf(value));
	}
	
	public final SumView sum(final IntegerFunction other)
	{
		return new SumView(new IntegerFunction[]{this, other});
	}

}
