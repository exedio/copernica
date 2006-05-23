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


public final class BooleanAttribute extends FunctionAttribute<Boolean>
{
	static final int[] ALLOWED_VALUES = {0, 1};

	private BooleanAttribute(final boolean isfinal, final boolean optional, final boolean unique, final Boolean defaultConstant)
	{
		super(isfinal, optional, unique, Boolean.class, defaultConstant);
		checkDefaultValue();
	}
	
	public BooleanAttribute(final Option option)
	{
		this(option.isFinal, option.optional, option.unique, null);
	}
	
	public FunctionAttribute<Boolean> copyFunctionAttribute()
	{
		return new BooleanAttribute(isfinal, optional, implicitUniqueConstraint!=null, defaultConstant);
	}
	
	public BooleanAttribute defaultTo(final Boolean defaultConstant)
	{
		return new BooleanAttribute(isfinal, optional, implicitUniqueConstraint!=null, defaultConstant);
	}
	
	Column createColumn(final Table table, final String name, final boolean optional)
	{
		return new IntegerColumn(table, name, optional, 1, false, ALLOWED_VALUES);
	}
	
	Boolean get(final Row row)
	{
		final Object cell = row.get(getColumn());
		if(cell==null)
			return null;
		else
		{
			switch(((Integer)cell).intValue())
			{
				case 0:
					return Boolean.FALSE;
				case 1:
					return Boolean.TRUE;
				default:
					throw new RuntimeException("cacheToSurface:"+cell);
			}
		}
	}
	
	static final Integer FALSE = Integer.valueOf(0);
	static final Integer TRUE = Integer.valueOf(1);
		
	void set(final Row row, final Boolean surface)
	{
		row.put(getColumn(), surface==null ? null : surface.booleanValue() ? TRUE : FALSE);
	}
	
	/**
	 * @throws RuntimeException if this attribute is not {@link #isMandatory() mandatory}.
	 */
	public final boolean getMandatory(final Item item)
	{
		if(optional)
			throw new RuntimeException("attribute " + toString() + " is not mandatory");
		
		return get(item).booleanValue();
	}
	
	public final void set(final Item item, final boolean value)
		throws
			UniqueViolationException,
			FinalViolationException
	{
		set(item, new Boolean(value));
	}
	
	public final SetValue map(final boolean value)
	{
		return new SetValue(this, value ? Boolean.TRUE : Boolean.FALSE);
	}
}
