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

import com.exedio.cope.function.LengthView;
import com.exedio.cope.function.UppercaseView;

public final class StringAttribute extends FunctionAttribute<String> implements StringFunction
{
	private final int minimumLength;
	private final int maximumLength;
	
	public static final int DEFAULT_LENGTH = 100;

	private StringAttribute(
			final boolean isfinal, final boolean optional, final boolean unique,
			final int minimumLength, final int maximumLength)
	{
		super(isfinal, optional, unique);
		this.minimumLength = minimumLength;
		this.maximumLength = maximumLength;

		if(minimumLength<0)
			throw new RuntimeException("mimimum length must be positive, but was " + minimumLength + '.');
		if(maximumLength<=0)
			throw new RuntimeException("maximum length must be greater zero, but was " + maximumLength + '.');
		if(minimumLength>maximumLength)
			throw new RuntimeException("maximum length must be greater or equal mimimum length, but was " + maximumLength + " and " + minimumLength + '.');
	}
	
	public StringAttribute(final Option option)
	{
		this(option.isFinal, option.optional, option.unique, 0, DEFAULT_LENGTH);
	}
	
	/**
	 * @deprecated use {@link #lengthMin(int)} instead.
	 */
	@Deprecated
	public StringAttribute(final Option option, final int minimumLength)
	{
		this(option.isFinal, option.optional, option.unique, minimumLength, DEFAULT_LENGTH);
	}
	
	/**
	 * @deprecated use {@link #lengthRange(int, int)} or {@link #lengthMax(int)} or {@link #lengthExact(int)} instead.
	 */
	@Deprecated
	public StringAttribute(final Option option, final int minimumLength, final int maximumLength)
	{
		this(option.isFinal, option.optional, option.unique, minimumLength, maximumLength);
	}
	
	public FunctionAttribute copyFunctionAttribute()
	{
		return new StringAttribute(isfinal, optional, implicitUniqueConstraint!=null, minimumLength, maximumLength);
	}
	
	@Override
	Class initialize(final java.lang.reflect.Type genericType)
	{
		return String.class;
	}
	
	public StringAttribute lengthRange(final int minimumLength, final int maximumLength)
	{
		return new StringAttribute(isfinal, optional, implicitUniqueConstraint!=null, minimumLength, maximumLength);
	}
	
	public StringAttribute lengthMin(final int minimumLength)
	{
		return new StringAttribute(isfinal, optional, implicitUniqueConstraint!=null, minimumLength, DEFAULT_LENGTH);
	}
	
	public StringAttribute lengthMax(final int maximumLength)
	{
		return new StringAttribute(isfinal, optional, implicitUniqueConstraint!=null, 0, maximumLength);
	}
	
	/**
	 * @deprecated use {@link #lengthMax(int)}.
	 */
	@Deprecated
	public StringAttribute lengthMaxUnchecked(final int maximumLength)
	{
		return lengthMax(maximumLength);
	}
	
	public StringAttribute lengthExact(final int exactLength)
	{
		return new StringAttribute(isfinal, optional, implicitUniqueConstraint!=null, exactLength, exactLength);
	}
	
	public final int getMinimumLength()
	{
		return minimumLength;
	}
	
	public final int getMaximumLength()
	{
		return maximumLength;
	}
	
	Column createColumn(final Table table, final String name, final boolean optional)
	{
		return new StringColumn(table, name, optional, minimumLength, maximumLength);
	}
	
	String get(final Row row)
	{
		return (String)row.get(getColumn());
	}
		
	void set(final Row row, final String surface)
	{
		final String cell;
		if(getType().getModel().supportsEmptyStrings()) // TODO dont fetch this that often
			cell = surface;
		else
		{
			if(surface!=null && surface.length()==0)
				cell = null;
			else
				cell = surface;
		}
		row.put(getColumn(), cell);
	}
	
	void checkNotNullValue(final Object value, final Item item)
		throws
			LengthViolationException
	{
		final String stringValue = (String)value;
		// TODO make only one call to stringValue.length()
		if(stringValue.length()<minimumLength)
			throw new LengthViolationException(this, item, stringValue, true);
		if(stringValue.length()>maximumLength)
			throw new LengthViolationException(this, item, stringValue, false);
	}
	
	public final EqualCondition equal(final Join join, final String value)
	{
		return new EqualCondition<String>(new JoinedFunction<String>(this, join), value);
	}
	
	public final EqualAttributeCondition equal(final StringAttribute other)
	{
		return new EqualAttributeCondition(this, other);
	}
	
	public final LikeCondition like(final String value)
	{
		return new LikeCondition(this, value);
	}
	
	public final LengthView length()
	{
		return new LengthView(this);
	}
	
	public final UppercaseView uppercase()
	{
		return new UppercaseView(this);
	}
	
}
