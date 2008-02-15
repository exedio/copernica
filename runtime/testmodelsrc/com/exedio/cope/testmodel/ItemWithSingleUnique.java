/*
 * Copyright (C) 2004-2007  exedio GmbH (www.exedio.com)
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

import com.exedio.cope.CopeSchemaName;
import com.exedio.cope.Item;
import com.exedio.cope.StringField;

@CopeSchemaName("UNIQUE_ITEMS")
public class ItemWithSingleUnique extends Item
{
	/**
	 * An attribute that is unique.
	 */
	@CopeSchemaName("UNIQUE_S")
	public static final StringField uniqueString = new StringField().optional().unique();

	public static final StringField otherString = new StringField().optional();
	
	public ItemWithSingleUnique(final String uniqueString)
	{
		this(new com.exedio.cope.SetValue[]{
			ItemWithSingleUnique.uniqueString.map(uniqueString)
		});
	}

	public ItemWithSingleUnique(final String uniqueString, final String otherString)
	{
		this(new com.exedio.cope.SetValue[]{
			ItemWithSingleUnique.uniqueString.map(uniqueString),
			ItemWithSingleUnique.otherString.map(otherString)
		});
	}
	
/**

	 **
	 * Creates a new ItemWithSingleUnique with all the fields initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	public ItemWithSingleUnique()
	{
		this(new com.exedio.cope.SetValue[]{
		});
	}/**

	 **
	 * Creates a new ItemWithSingleUnique and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private ItemWithSingleUnique(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@SuppressWarnings("unused") private ItemWithSingleUnique(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of {@link #uniqueString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.String getUniqueString()
	{
		return ItemWithSingleUnique.uniqueString.get(this);
	}/**

	 **
	 * Sets a new value for {@link #uniqueString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setUniqueString(final java.lang.String uniqueString)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.UniqueViolationException
	{
		ItemWithSingleUnique.uniqueString.set(this,uniqueString);
	}/**

	 **
	 * Finds a itemWithSingleUnique by it's {@link #uniqueString}.
	 * @param uniqueString shall be equal to field {@link #uniqueString}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.finder public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public static final ItemWithSingleUnique forUniqueString(final java.lang.String uniqueString)
	{
		return ItemWithSingleUnique.uniqueString.searchUnique(ItemWithSingleUnique.class,uniqueString);
	}/**

	 **
	 * Finds a itemWithSingleUnique by it's {@link #uniqueString}.
	 * @deprecated use for{@link #uniqueString} instead.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.finder public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	@Deprecated
	public static final ItemWithSingleUnique findByUniqueString(final java.lang.String uniqueString)
	{
		return ItemWithSingleUnique.uniqueString.searchUnique(ItemWithSingleUnique.class,uniqueString);
	}/**

	 **
	 * Returns the value of {@link #otherString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.String getOtherString()
	{
		return ItemWithSingleUnique.otherString.get(this);
	}/**

	 **
	 * Sets a new value for {@link #otherString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setOtherString(final java.lang.String otherString)
			throws
				com.exedio.cope.LengthViolationException
	{
		ItemWithSingleUnique.otherString.set(this,otherString);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for itemWithSingleUnique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<ItemWithSingleUnique> TYPE = newType(ItemWithSingleUnique.class)
;}
