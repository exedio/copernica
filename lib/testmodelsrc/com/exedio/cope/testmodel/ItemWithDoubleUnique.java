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
import com.exedio.cope.UniqueConstraint;

/**
 * An item having two attributes and a unique constraint over these attributes.
 * @cope.persistent
 * @author Ralf Wiebicke
 */
public class ItemWithDoubleUnique extends Item
{
	public static final StringAttribute string = new StringAttribute(MANDATORY);
	
	public static final IntegerAttribute integer = new IntegerAttribute(MANDATORY);
	
	public static final UniqueConstraint doubleUnique = new UniqueConstraint(string, integer);

/**

	 **
	 * Creates a new ItemWithDoubleUnique with all the attributes initially needed.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 * @param string the initial value for attribute {@link #string}.
	 * @param integer the initial value for attribute {@link #integer}.
	 * @throws com.exedio.cope.MandatoryViolationException if string is null.
	 * @throws com.exedio.cope.UniqueViolationException if string, integer is not unique.
	 *
 */public ItemWithDoubleUnique(
				final java.lang.String string,
				final int integer)
			throws
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.AttributeValue[]{
			new com.exedio.cope.AttributeValue(ItemWithDoubleUnique.string,string),
			new com.exedio.cope.AttributeValue(ItemWithDoubleUnique.integer,new java.lang.Integer(integer)),
		});
		throwInitialMandatoryViolationException();
		throwInitialUniqueViolationException();
	}/**

	 **
	 * Creates a new ItemWithDoubleUnique and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */private ItemWithDoubleUnique(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 *
 */private ItemWithDoubleUnique(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #string}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getString()
	{
		return (java.lang.String)get(ItemWithDoubleUnique.string);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #string}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setString(final java.lang.String string)
			throws
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		try
		{
			set(ItemWithDoubleUnique.string,string);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #integer}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final int getInteger()
	{
		return ((java.lang.Integer)get(ItemWithDoubleUnique.integer)).intValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #integer}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setInteger(final int integer)
			throws
				com.exedio.cope.UniqueViolationException
	{
		try
		{
			set(ItemWithDoubleUnique.integer,new java.lang.Integer(integer));
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
	}/**

	 **
	 * Finds a itemWithDoubleUnique by it's unique attributes.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 * @param string shall be equal to attribute {@link #string}.
	 * @param integer shall be equal to attribute {@link #integer}.
	 * @return null if there is no matching item.
	 *
 */public static final ItemWithDoubleUnique findByDoubleUnique(final java.lang.String string,final int integer)
	{
		return (ItemWithDoubleUnique)ItemWithDoubleUnique.doubleUnique.searchUnique(new Object[]{string,new java.lang.Integer(integer)});
	}/**

	 **
	 * The persistent type information for itemWithDoubleUnique.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(ItemWithDoubleUnique.class)
;}
