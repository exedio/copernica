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

package com.exedio.cope.testmodel;

import com.exedio.cope.Item;
import com.exedio.cope.StringAttribute;

/**
 * @cope.persistent
 * @author Ralf Wiebicke
 */
public class PointerItem2 extends Item
{

	public static final StringAttribute code = stringAttribute(NOT_NULL);

/**

	 **
	 * Creates a new PointerItem2 with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param code the initial value for attribute {@link #code}.
	 * @throws com.exedio.cope.NotNullViolationException if code is null.
	 *
 */public PointerItem2(
				final java.lang.String code)
			throws
				com.exedio.cope.NotNullViolationException
	{
		this(new com.exedio.cope.AttributeValue[]{
			new com.exedio.cope.AttributeValue(PointerItem2.code,code),
		});
		throwInitialNotNullViolationException();
	}/**

	 **
	 * Creates a new PointerItem2 and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */private PointerItem2(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 *
 */private PointerItem2(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #code}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getCode()
	{
		return (java.lang.String)get(PointerItem2.code);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #code}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setCode(final java.lang.String code)
			throws
				com.exedio.cope.NotNullViolationException
	{
		try
		{
			set(PointerItem2.code,code);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
	}/**

	 **
	 * The persistent type information for pointerItem2.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.Type TYPE = 
		new com.exedio.cope.Type(PointerItem2.class)
;}
