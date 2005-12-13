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
 * An item having a unique mandatory attribute.
 * @cope.persistent
 * @author Ralf Wiebicke
 */
public class ItemWithSingleUniqueNotNull extends Item
{
	/**
	 * An attribute that is unique and read-only.
	 */
	public static final StringAttribute uniqueNotNullString = new StringAttribute(UNIQUE);

/**

	 **
	 * Creates a new ItemWithSingleUniqueNotNull with all the attributes initially needed.
	 * @param uniqueNotNullString the initial value for attribute {@link #uniqueNotNullString}.
	 * @throws com.exedio.cope.MandatoryViolationException if uniqueNotNullString is null.
	 * @throws com.exedio.cope.UniqueViolationException if uniqueNotNullString is not unique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <code>@cope.constructor public|package|protected|private|none</code> in the class comment and <code>@cope.initial</code> in the comment of attributes.
	 */
	public ItemWithSingleUniqueNotNull(
				final java.lang.String uniqueNotNullString)
			throws
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.AttributeValue[]{
			ItemWithSingleUniqueNotNull.uniqueNotNullString.map(uniqueNotNullString),
		});
		throwInitialMandatoryViolationException();
		throwInitialUniqueViolationException();
	}/**

	 **
	 * Creates a new ItemWithSingleUniqueNotNull and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.generic.constructor public|package|protected|private|none</code> in the class comment.
	 */
	private ItemWithSingleUniqueNotNull(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private ItemWithSingleUniqueNotNull(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #uniqueNotNullString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.String getUniqueNotNullString()
	{
		return ItemWithSingleUniqueNotNull.uniqueNotNullString.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #uniqueNotNullString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setUniqueNotNullString(final java.lang.String uniqueNotNullString)
			throws
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		try
		{
			ItemWithSingleUniqueNotNull.uniqueNotNullString.set(this,uniqueNotNullString);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
	}/**

	 **
	 * Finds a itemWithSingleUniqueNotNull by it's unique attributes.
	 * @param uniqueNotNullString shall be equal to attribute {@link #uniqueNotNullString}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public static final ItemWithSingleUniqueNotNull findByUniqueNotNullString(final java.lang.String uniqueNotNullString)
	{
		return (ItemWithSingleUniqueNotNull)ItemWithSingleUniqueNotNull.uniqueNotNullString.searchUnique(uniqueNotNullString);
	}/**

	 **
	 * The persistent type information for itemWithSingleUniqueNotNull.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.type public|package|protected|private|none</code> in the class comment.
	 */
	public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(ItemWithSingleUniqueNotNull.class)
;}
