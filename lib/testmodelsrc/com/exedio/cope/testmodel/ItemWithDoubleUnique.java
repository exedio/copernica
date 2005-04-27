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

import com.exedio.cope.lib.IntegerAttribute;
import com.exedio.cope.lib.Item;
import com.exedio.cope.lib.StringAttribute;
import com.exedio.cope.lib.UniqueConstraint;

/**
 * An item having two attributes and a unique constraint over these attributes.
 * @persistent
 */
public class ItemWithDoubleUnique extends Item
{

	/**
	 * @cope-setter none
	 */
	public static final StringAttribute string = stringAttribute(NOT_NULL);
	
	/**
	 * @cope-setter private
	 */
	public static final IntegerAttribute integer = integerAttribute(NOT_NULL);
	
	public static final UniqueConstraint doubleUnique = uniqueConstraint(string, integer);

/**

	 **
	 * Creates a new ItemWithDoubleUnique with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param initialString the initial value for attribute {@link #string}.
	 * @param initialInteger the initial value for attribute {@link #integer}.
	 * @throws com.exedio.cope.lib.NotNullViolationException if initialString is null.
	 * @throws com.exedio.cope.lib.UniqueViolationException if initialString, initialInteger is not unique.
	 *
 */public ItemWithDoubleUnique(
				final java.lang.String initialString,
				final int initialInteger)
			throws
				com.exedio.cope.lib.NotNullViolationException,
				com.exedio.cope.lib.UniqueViolationException
	{
		this(new com.exedio.cope.lib.AttributeValue[]{
			new com.exedio.cope.lib.AttributeValue(string,initialString),
			new com.exedio.cope.lib.AttributeValue(integer,new java.lang.Integer(initialInteger)),
		});
		throwInitialNotNullViolationException();
		throwInitialUniqueViolationException();
	}/**

	 **
	 * Creates a new ItemWithDoubleUnique and sets the given attributes initially.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */private ItemWithDoubleUnique(final com.exedio.cope.lib.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.lib.util.ReactivationConstructorDummy,int)
	 *
 */private ItemWithDoubleUnique(com.exedio.cope.lib.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #string}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getString()
	{
		return (java.lang.String)getAttribute(ItemWithDoubleUnique.string);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #integer}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final int getInteger()
	{
		return ((java.lang.Integer)getAttribute(ItemWithDoubleUnique.integer)).intValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #integer}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */private final void setInteger(final int integer)
			throws
				com.exedio.cope.lib.UniqueViolationException
	{
		try
		{
			setAttribute(ItemWithDoubleUnique.integer,new java.lang.Integer(integer));
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Finds a itemWithDoubleUnique by it's unique attributes.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param searchedString shall be equal to attribute {@link #string}.
	 * @param searchedInteger shall be equal to attribute {@link #integer}.
	 *
 */public static final ItemWithDoubleUnique findByDoubleUnique(final java.lang.String searchedString,final int searchedInteger)
	{
		return (ItemWithDoubleUnique)doubleUnique.searchUnique(new Object[]{searchedString,new java.lang.Integer(searchedInteger)});
	}/**

	 **
	 * The persistent type information for itemWithDoubleUnique.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(ItemWithDoubleUnique.class)
;}
