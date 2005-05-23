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
import com.exedio.cope.ItemAttribute;
import com.exedio.cope.StringAttribute;

/**
 * @persistent
 * @author Ralf Wiebicke
 */
public class PointerItem extends Item
{

	public static final StringAttribute code = stringAttribute(NOT_NULL);
	
	public static final ItemAttribute pointer = itemAttribute(NOT_NULL, PointerItem2.class);

	public static final ItemAttribute self = itemAttribute(DEFAULT, PointerItem.class);

	public static final ItemAttribute empty2 = itemAttribute(DEFAULT, EmptyItem2.class);

/**

	 **
	 * Creates a new PointerItem with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param code the initial value for attribute {@link #code}.
	 * @param pointer the initial value for attribute {@link #pointer}.
	 * @throws com.exedio.cope.NotNullViolationException if code, pointer is null.
	 *
 */public PointerItem(
				final java.lang.String code,
				final PointerItem2 pointer)
			throws
				com.exedio.cope.NotNullViolationException
	{
		this(new com.exedio.cope.AttributeValue[]{
			new com.exedio.cope.AttributeValue(PointerItem.code,code),
			new com.exedio.cope.AttributeValue(PointerItem.pointer,pointer),
		});
		throwInitialNotNullViolationException();
	}/**

	 **
	 * Creates a new PointerItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */private PointerItem(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 *
 */private PointerItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #code}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getCode()
	{
		return (java.lang.String)getAttribute(PointerItem.code);
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
			setAttribute(PointerItem.code,code);
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
	 * Returns the value of the persistent attribute {@link #pointer}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final PointerItem2 getPointer()
	{
		return (PointerItem2)getAttribute(PointerItem.pointer);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #pointer}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setPointer(final PointerItem2 pointer)
			throws
				com.exedio.cope.NotNullViolationException
	{
		try
		{
			setAttribute(PointerItem.pointer,pointer);
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
	 * Returns the value of the persistent attribute {@link #self}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final PointerItem getSelf()
	{
		return (PointerItem)getAttribute(PointerItem.self);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #self}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSelf(final PointerItem self)
	{
		try
		{
			setAttribute(PointerItem.self,self);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.NotNullViolationException e)
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
	 * Returns the value of the persistent attribute {@link #empty2}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final EmptyItem2 getEmpty2()
	{
		return (EmptyItem2)getAttribute(PointerItem.empty2);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #empty2}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setEmpty2(final EmptyItem2 empty2)
	{
		try
		{
			setAttribute(PointerItem.empty2,empty2);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.NotNullViolationException e)
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
	 * The persistent type information for pointerItem.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.Type TYPE = 
		new com.exedio.cope.Type(PointerItem.class)
;}
