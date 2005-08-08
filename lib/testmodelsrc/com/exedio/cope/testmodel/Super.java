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

import com.exedio.cope.IntegerAttribute;
import com.exedio.cope.Item;
import com.exedio.cope.StringAttribute;

/**
 * @cope.persistent
 * @author Ralf Wiebicke
 */
public abstract class Super extends Item
{
	public static final IntegerAttribute superInt = integerAttribute(NOT_NULL);
	
	public static final StringAttribute superString = stringAttribute(DEFAULT);
	
/**

	 **
	 * Creates a new Super with all the attributes initially needed.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 * @param superInt the initial value for attribute {@link #superInt}.
	 *
 */public Super(
				final int superInt)
	{
		this(new com.exedio.cope.AttributeValue[]{
			new com.exedio.cope.AttributeValue(Super.superInt,new java.lang.Integer(superInt)),
		});
	}/**

	 **
	 * Creates a new Super and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */protected Super(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 *
 */protected Super(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #superInt}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final int getSuperInt()
	{
		return ((java.lang.Integer)get(Super.superInt)).intValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #superInt}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setSuperInt(final int superInt)
	{
		try
		{
			set(Super.superInt,new java.lang.Integer(superInt));
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
	 * Returns the value of the persistent attribute {@link #superString}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getSuperString()
	{
		return (java.lang.String)get(Super.superString);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #superString}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setSuperString(final java.lang.String superString)
	{
		try
		{
			set(Super.superString,superString);
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
	 * The persistent type information for super.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public static final com.exedio.cope.Type TYPE = 
		new com.exedio.cope.Type(Super.class)
;}
