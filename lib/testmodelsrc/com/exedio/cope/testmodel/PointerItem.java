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
 * @cope.persistent
 * @author Ralf Wiebicke
 */
public class PointerItem extends Item
{

	public static final StringAttribute code = new StringAttribute(MANDATORY);
	
	public static final ItemAttribute pointer = new ItemAttribute(MANDATORY, PointerTargetItem.class);

	public static final ItemAttribute pointer2 = new ItemAttribute(OPTIONAL, PointerTargetItem.class);

	public static final ItemAttribute self = new ItemAttribute(OPTIONAL, PointerItem.class);

	public static final ItemAttribute empty2 = new ItemAttribute(OPTIONAL, EmptyItem2.class);

/**

	 **
	 * Creates a new PointerItem with all the attributes initially needed.
	 * @param code the initial value for attribute {@link #code}.
	 * @param pointer the initial value for attribute {@link #pointer}.
	 * @throws com.exedio.cope.MandatoryViolationException if code, pointer is null.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public PointerItem(
				final java.lang.String code,
				final PointerTargetItem pointer)
			throws
				com.exedio.cope.MandatoryViolationException
	{
		this(new com.exedio.cope.AttributeValue[]{
			new com.exedio.cope.AttributeValue(PointerItem.code,code),
			new com.exedio.cope.AttributeValue(PointerItem.pointer,pointer),
		});
		throwInitialMandatoryViolationException();
	}/**

	 **
	 * Creates a new PointerItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */private PointerItem(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */private PointerItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #code}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getCode()
	{
		return (java.lang.String)get(PointerItem.code);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #code}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setCode(final java.lang.String code)
			throws
				com.exedio.cope.MandatoryViolationException
	{
		try
		{
			set(PointerItem.code,code);
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
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final PointerTargetItem getPointer()
	{
		return (PointerTargetItem)get(PointerItem.pointer);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #pointer}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setPointer(final PointerTargetItem pointer)
			throws
				com.exedio.cope.MandatoryViolationException
	{
		try
		{
			set(PointerItem.pointer,pointer);
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
	 * Returns the value of the persistent attribute {@link #pointer2}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final PointerTargetItem getPointer2()
	{
		return (PointerTargetItem)get(PointerItem.pointer2);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #pointer2}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setPointer2(final PointerTargetItem pointer2)
	{
		try
		{
			set(PointerItem.pointer2,pointer2);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.MandatoryViolationException e)
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
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final PointerItem getSelf()
	{
		return (PointerItem)get(PointerItem.self);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #self}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setSelf(final PointerItem self)
	{
		try
		{
			set(PointerItem.self,self);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.MandatoryViolationException e)
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
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final EmptyItem2 getEmpty2()
	{
		return (EmptyItem2)get(PointerItem.empty2);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #empty2}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setEmpty2(final EmptyItem2 empty2)
	{
		try
		{
			set(PointerItem.empty2,empty2);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.MandatoryViolationException e)
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
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.type public|package|protected|private|none</code> in the class comment.
	 *
 */public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(PointerItem.class)
;}
