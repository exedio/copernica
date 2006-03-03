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

import com.exedio.cope.search.GreaterCondition;
import com.exedio.cope.search.GreaterEqualCondition;
import com.exedio.cope.search.LessCondition;
import com.exedio.cope.search.LessEqualCondition;

public final class LongAttribute extends FunctionAttribute
{

	private LongAttribute(final boolean isfinal, final boolean mandatory, final boolean unique)
	{
		super(isfinal, mandatory, unique, Long.class);
	}
	
	public LongAttribute(final Option option)
	{
		this(option.isFinal, option.mandatory, option.unique);
	}
	
	public FunctionAttribute copyFunctionAttribute()
	{
		return new LongAttribute(isfinal, mandatory, implicitUniqueConstraint!=null);
	}
	
	Column createColumn(final Table table, final String name, final boolean notNull)
	{
		return new IntegerColumn(table, name, notNull, 20, true, null);
	}
	
	Object get(final Row row)
	{
		return (Long)row.get(getColumn());
	}
		
	void set(final Row row, final Object surface)
	{
		row.put(getColumn(), (Long)surface);
	}
	
	public final Long get(final Item item)
	{
		return (Long)getObject(item);
	}
	
	/**
	 * @throws RuntimeException if this attribute is not {@link #isMandatory() mandatory}.
	 */
	public final long getMandatory(final Item item)
	{
		if(!mandatory)
			throw new RuntimeException("attribute " + toString() + " is not mandatory");
		
		return get(item).longValue();
	}
	
	public final void set(final Item item, final Long value)
		throws
			UniqueViolationException,
			MandatoryViolationException,
			FinalViolationException
	{
		try
		{
			item.set(this, value);
		}
		catch(LengthViolationException e)
		{
			throw new RuntimeException(e);
		}
	}

	public final void set(final Item item, final long value)
		throws
			UniqueViolationException,
			FinalViolationException
	{
		try
		{
			set(item, Long.valueOf(value));
		}
		catch(MandatoryViolationException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public final AttributeValue map(final Long value)
	{
		return new AttributeValue(this, value);
	}
	
	public final AttributeValue map(final long value)
	{
		return new AttributeValue(this, Long.valueOf(value));
	}
	
	public final EqualCondition equal(final Long value)
	{
		return new EqualCondition(this, value);
	}
	
	public final EqualCondition equal(final long value)
	{
		return new EqualCondition(this, Long.valueOf(value));
	}
	
	public final NotEqualCondition notEqual(final Long value)
	{
		return new NotEqualCondition(this, value);
	}
	
	public final NotEqualCondition notEqual(final long value)
	{
		return new NotEqualCondition(this, Long.valueOf(value));
	}
	
	public final LessCondition less(final long value)
	{
		return new LessCondition(this, Long.valueOf(value));
	}
	
	public final LessEqualCondition lessOrEqual(final long value)
	{
		return new LessEqualCondition(this, Long.valueOf(value));
	}
	
	public final GreaterCondition greater(final long value)
	{
		return new GreaterCondition(this, Long.valueOf(value));
	}
	
	public final GreaterEqualCondition greaterOrEqual(final long value)
	{
		return new GreaterEqualCondition(this, Long.valueOf(value));
	}
	
}
