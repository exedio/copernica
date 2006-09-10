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

package com.exedio.cope.pattern;

import com.exedio.cope.IntegerField;
import com.exedio.cope.Item;
import com.exedio.cope.StringField;

public class AttributeMapLimitedItem extends Item
{
	static enum Language
	{
		DE, EN, PL;
	}
	
	public static final FieldMapLimited<Language, String> name = FieldMapLimited.newMap(Language.class, new StringField(OPTIONAL));
	
	public static final FieldMapLimited<Language, Integer> nameLength = FieldMapLimited.newMap(Language.class, new IntegerField(OPTIONAL));
	
	/**

	 **
	 * Creates a new AttributeMapLimitedItem with all the attributes initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of attributes.
	 */
	public AttributeMapLimitedItem()
	{
		this(new com.exedio.cope.SetValue[]{
		});
	}/**

	 **
	 * Creates a new AttributeMapLimitedItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private AttributeMapLimitedItem(final com.exedio.cope.SetValue[] setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private AttributeMapLimitedItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value mapped to <tt>k</tt> by the attribute map {@link #name}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final String getName(final Language k)
	{
		return AttributeMapLimitedItem.name.get(this,k);
	}/**

	 **
	 * Associates <tt>k</tt> to a new value in the attribute map {@link #name}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setName(final Language k,final String name)
	{
		AttributeMapLimitedItem.name.set(this,k,name);
	}/**

	 **
	 * Returns the value mapped to <tt>k</tt> by the attribute map {@link #nameLength}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final Integer getNameLength(final Language k)
	{
		return AttributeMapLimitedItem.nameLength.get(this,k);
	}/**

	 **
	 * Associates <tt>k</tt> to a new value in the attribute map {@link #nameLength}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setNameLength(final Language k,final Integer nameLength)
	{
		AttributeMapLimitedItem.nameLength.set(this,k,nameLength);
	}/**

	 **
	 * The persistent type information for attributeMapLimitedItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<AttributeMapLimitedItem> TYPE = newType(AttributeMapLimitedItem.class)
;}
