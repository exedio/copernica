/*
 * Copyright (C) 2004-2006  exedio GmbH (www.exedio.com)
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
import com.exedio.cope.StringField;

/**
 * An item having a unique attribute.
 * @author Ralf Wiebicke
 */
public class ItemWithSingleUnique extends Item
{
	/**
	 * An attribute that is unique.
	 */
	public static final StringField uniqueString = new StringField(UNIQUE_OPTIONAL);

	public static final StringField otherString = new StringField(OPTIONAL);

/**

	 **
	 * Creates a new ItemWithSingleUnique with all the attributes initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of attributes.
	 */
	public ItemWithSingleUnique()
	{
		this(new com.exedio.cope.SetValue[]{
		});
	}/**

	 **
	 * Creates a new ItemWithSingleUnique and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private ItemWithSingleUnique(final com.exedio.cope.SetValue[] setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private ItemWithSingleUnique(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #uniqueString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.String getUniqueString()
	{
		return ItemWithSingleUnique.uniqueString.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #uniqueString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setUniqueString(final java.lang.String uniqueString)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.UniqueViolationException
	{
		ItemWithSingleUnique.uniqueString.set(this,uniqueString);
	}/**

	 **
	 * Finds a itemWithSingleUnique by it's unique attributes.
	 * @param uniqueString shall be equal to attribute {@link #uniqueString}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public static final ItemWithSingleUnique findByUniqueString(final java.lang.String uniqueString)
	{
		return (ItemWithSingleUnique)ItemWithSingleUnique.uniqueString.searchUnique(uniqueString);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #otherString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.String getOtherString()
	{
		return ItemWithSingleUnique.otherString.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #otherString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setOtherString(final java.lang.String otherString)
			throws
				com.exedio.cope.LengthViolationException
	{
		ItemWithSingleUnique.otherString.set(this,otherString);
	}/**

	 **
	 * The persistent type information for itemWithSingleUnique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<ItemWithSingleUnique> TYPE = newType(ItemWithSingleUnique.class)
;}
