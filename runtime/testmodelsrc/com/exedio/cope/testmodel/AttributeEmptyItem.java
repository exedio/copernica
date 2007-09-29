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

import com.exedio.cope.Item;
import com.exedio.cope.ItemField;
import com.exedio.cope.StringField;
import com.exedio.cope.UniqueConstraint;
import com.exedio.cope.pattern.Qualifier;


/**
 * @author Ralf Wiebicke
 */
public class AttributeEmptyItem extends Item
{
	public static final ItemField<AttributeItem> parent = newItemField(AttributeItem.class, CASCADE).optional();

	public static final ItemField<EmptyItem> key = newItemField(EmptyItem.class).optional();
	
	public static final UniqueConstraint parentKey = new UniqueConstraint(parent, key);
	public static final Qualifier emptyItem = new Qualifier(parentKey);

	public static final StringField someQualifiedString = new StringField().optional();

	/**

	 **
	 * Creates a new AttributeEmptyItem with all the fields initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	public AttributeEmptyItem()
	{
		this(new com.exedio.cope.SetValue[]{
		});
	}/**

	 **
	 * Creates a new AttributeEmptyItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private AttributeEmptyItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private AttributeEmptyItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent field {@link #parent}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final AttributeItem getParent()
	{
		return AttributeEmptyItem.parent.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #parent}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setParent(final AttributeItem parent)
			throws
				com.exedio.cope.UniqueViolationException
	{
		AttributeEmptyItem.parent.set(this,parent);
	}/**

	 **
	 * Returns the value of the persistent field {@link #key}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final EmptyItem getKey()
	{
		return AttributeEmptyItem.key.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #key}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setKey(final EmptyItem key)
			throws
				com.exedio.cope.UniqueViolationException
	{
		AttributeEmptyItem.key.set(this,key);
	}/**

	 **
	 * Finds a attributeEmptyItem by it's unique fields.
	 * @param parent shall be equal to field {@link #parent}.
	 * @param key shall be equal to field {@link #key}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public static final AttributeEmptyItem findByParentKey(final AttributeItem parent,final EmptyItem key)
	{
		return AttributeEmptyItem.parentKey.searchUnique(AttributeEmptyItem.class,parent,key);
	}/**

	 **
	 * Returns the value of the persistent field {@link #someQualifiedString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.String getSomeQualifiedString()
	{
		return AttributeEmptyItem.someQualifiedString.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #someQualifiedString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setSomeQualifiedString(final java.lang.String someQualifiedString)
			throws
				com.exedio.cope.LengthViolationException
	{
		AttributeEmptyItem.someQualifiedString.set(this,someQualifiedString);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for attributeEmptyItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<AttributeEmptyItem> TYPE = newType(AttributeEmptyItem.class)
;}
