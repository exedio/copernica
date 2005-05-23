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
 * An item having a unique not-null attribute.
 * @persistent
 * @author Ralf Wiebicke
 */
public class ItemWithSingleUniqueNotNull extends Item
{
	/**
	 * An attribute that is unique and read-only.
	 */
	public static final StringAttribute uniqueNotNullString = stringAttribute(NOT_NULL_UNIQUE);

/**

	 **
	 * Creates a new ItemWithSingleUniqueNotNull with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param uniqueNotNullString the initial value for attribute {@link #uniqueNotNullString}.
	 * @throws com.exedio.cope.NotNullViolationException if uniqueNotNullString is null.
	 * @throws com.exedio.cope.UniqueViolationException if uniqueNotNullString is not unique.
	 *
 */public ItemWithSingleUniqueNotNull(
				final java.lang.String uniqueNotNullString)
			throws
				com.exedio.cope.NotNullViolationException,
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.AttributeValue[]{
			new com.exedio.cope.AttributeValue(ItemWithSingleUniqueNotNull.uniqueNotNullString,uniqueNotNullString),
		});
		throwInitialNotNullViolationException();
		throwInitialUniqueViolationException();
	}/**

	 **
	 * Creates a new ItemWithSingleUniqueNotNull and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */private ItemWithSingleUniqueNotNull(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 *
 */private ItemWithSingleUniqueNotNull(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #uniqueNotNullString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getUniqueNotNullString()
	{
		return (java.lang.String)get(ItemWithSingleUniqueNotNull.uniqueNotNullString);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #uniqueNotNullString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setUniqueNotNullString(final java.lang.String uniqueNotNullString)
			throws
				com.exedio.cope.NotNullViolationException,
				com.exedio.cope.UniqueViolationException
	{
		try
		{
			set(ItemWithSingleUniqueNotNull.uniqueNotNullString,uniqueNotNullString);
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
	 * Finds a itemWithSingleUniqueNotNull by it's unique attributes.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param uniqueNotNullString shall be equal to attribute {@link #uniqueNotNullString}.
	 *
 */public static final ItemWithSingleUniqueNotNull findByUniqueNotNullString(final java.lang.String uniqueNotNullString)
	{
		return (ItemWithSingleUniqueNotNull)ItemWithSingleUniqueNotNull.uniqueNotNullString.searchUnique(uniqueNotNullString);
	}/**

	 **
	 * The persistent type information for itemWithSingleUniqueNotNull.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.Type TYPE = 
		new com.exedio.cope.Type(ItemWithSingleUniqueNotNull.class)
;}
