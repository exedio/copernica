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
 * An item having a unique attribute.
 * @persistent
 * @author Ralf Wiebicke
 */
public class ItemWithSingleUnique extends Item
{
	/**
	 * An attribute that is unique.
	 */
	public static final StringAttribute uniqueString = stringAttribute(UNIQUE);

/**

	 **
	 * Creates a new ItemWithSingleUnique with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public ItemWithSingleUnique()
	{
		this(new com.exedio.cope.AttributeValue[]{
		});
	}/**

	 **
	 * Creates a new ItemWithSingleUnique and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */private ItemWithSingleUnique(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 *
 */private ItemWithSingleUnique(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #uniqueString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getUniqueString()
	{
		return (java.lang.String)get(ItemWithSingleUnique.uniqueString);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #uniqueString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setUniqueString(final java.lang.String uniqueString)
			throws
				com.exedio.cope.UniqueViolationException
	{
		try
		{
			setAttribute(ItemWithSingleUnique.uniqueString,uniqueString);
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
	}/**

	 **
	 * Finds a itemWithSingleUnique by it's unique attributes.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param uniqueString shall be equal to attribute {@link #uniqueString}.
	 *
 */public static final ItemWithSingleUnique findByUniqueString(final java.lang.String uniqueString)
	{
		return (ItemWithSingleUnique)ItemWithSingleUnique.uniqueString.searchUnique(uniqueString);
	}/**

	 **
	 * The persistent type information for itemWithSingleUnique.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.Type TYPE = 
		new com.exedio.cope.Type(ItemWithSingleUnique.class)
;}
