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
 * An item having a unique read-only attribute.
 * @persistent
 * @author Ralf Wiebicke
 */
public class ItemWithSingleUniqueReadOnly extends Item
{
	/**
	 * An attribute that is unique and read-only.
	 */
	public static final StringAttribute uniqueReadOnlyString = stringAttribute(READ_ONLY_UNIQUE);

/**

	 **
	 * Creates a new ItemWithSingleUniqueReadOnly with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param uniqueReadOnlyString the initial value for attribute {@link #uniqueReadOnlyString}.
	 * @throws com.exedio.cope.UniqueViolationException if uniqueReadOnlyString is not unique.
	 *
 */public ItemWithSingleUniqueReadOnly(
				final java.lang.String uniqueReadOnlyString)
			throws
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.AttributeValue[]{
			new com.exedio.cope.AttributeValue(ItemWithSingleUniqueReadOnly.uniqueReadOnlyString,uniqueReadOnlyString),
		});
		throwInitialUniqueViolationException();
	}/**

	 **
	 * Creates a new ItemWithSingleUniqueReadOnly and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */private ItemWithSingleUniqueReadOnly(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 *
 */private ItemWithSingleUniqueReadOnly(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #uniqueReadOnlyString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getUniqueReadOnlyString()
	{
		return (java.lang.String)getAttribute(ItemWithSingleUniqueReadOnly.uniqueReadOnlyString);
	}/**

	 **
	 * Finds a itemWithSingleUniqueReadOnly by it's unique attributes.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param searchedUniqueReadOnlyString shall be equal to attribute {@link #uniqueReadOnlyString}.
	 *
 */public static final ItemWithSingleUniqueReadOnly findByUniqueReadOnlyString(final java.lang.String searchedUniqueReadOnlyString)
	{
		return (ItemWithSingleUniqueReadOnly)uniqueReadOnlyString.searchUnique(searchedUniqueReadOnlyString);
	}/**

	 **
	 * The persistent type information for itemWithSingleUniqueReadOnly.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.Type TYPE = 
		new com.exedio.cope.Type(ItemWithSingleUniqueReadOnly.class)
;}
