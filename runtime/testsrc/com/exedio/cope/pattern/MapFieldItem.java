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

package com.exedio.cope.pattern;

import com.exedio.cope.IntegerField;
import com.exedio.cope.Item;
import com.exedio.cope.StringField;

public class MapFieldItem extends Item
{
	static enum Language
	{
		DE, EN, PL;
	}
	
	static final MapField<Language, String> name = MapField.newMap(newEnumField(Language.class).toFinal(), new StringField());
	
	static final MapField<Language, Integer> nameLength = MapField.newMap(newEnumField(Language.class).toFinal(), new IntegerField());
	
	static final MapField<String, String> string = MapField.newMap(new StringField().toFinal().lengthRange(4, 8), new StringField());
	
	static final MapField<String, Integer> integer = MapField.newMap(new StringField().toFinal().lengthRange(4, 8), new IntegerField());
	
	
	/**

	 **
	 * Creates a new MapFieldItem with all the fields initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	public MapFieldItem()
	{
		this(new com.exedio.cope.SetValue[]{
		});
	}/**

	 **
	 * Creates a new MapFieldItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private MapFieldItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@SuppressWarnings("unused") private MapFieldItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value mapped to <tt>k</tt> by the field map {@link #name}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final String getName(final Language k)
	{
		return MapFieldItem.name.get(this,k);
	}/**

	 **
	 * Associates <tt>k</tt> to a new value in the field map {@link #name}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setName(final Language k,final String name)
	{
		MapFieldItem.name.set(this,k,name);
	}/**

	 **
	 * Returns the parent field of the type of {@link #name}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.parent public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	static final com.exedio.cope.ItemField<MapFieldItem> nameParent()
	{
		return MapFieldItem.name.getParent(MapFieldItem.class);
	}/**

	 **
	 * Returns the value mapped to <tt>k</tt> by the field map {@link #nameLength}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final Integer getNameLength(final Language k)
	{
		return MapFieldItem.nameLength.get(this,k);
	}/**

	 **
	 * Associates <tt>k</tt> to a new value in the field map {@link #nameLength}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setNameLength(final Language k,final Integer nameLength)
	{
		MapFieldItem.nameLength.set(this,k,nameLength);
	}/**

	 **
	 * Returns the parent field of the type of {@link #nameLength}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.parent public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	static final com.exedio.cope.ItemField<MapFieldItem> nameLengthParent()
	{
		return MapFieldItem.nameLength.getParent(MapFieldItem.class);
	}/**

	 **
	 * Returns the value mapped to <tt>k</tt> by the field map {@link #string}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final String getString(final String k)
	{
		return MapFieldItem.string.get(this,k);
	}/**

	 **
	 * Associates <tt>k</tt> to a new value in the field map {@link #string}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setString(final String k,final String string)
	{
		MapFieldItem.string.set(this,k,string);
	}/**

	 **
	 * Returns the parent field of the type of {@link #string}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.parent public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	static final com.exedio.cope.ItemField<MapFieldItem> stringParent()
	{
		return MapFieldItem.string.getParent(MapFieldItem.class);
	}/**

	 **
	 * Returns the value mapped to <tt>k</tt> by the field map {@link #integer}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final Integer getInteger(final String k)
	{
		return MapFieldItem.integer.get(this,k);
	}/**

	 **
	 * Associates <tt>k</tt> to a new value in the field map {@link #integer}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setInteger(final String k,final Integer integer)
	{
		MapFieldItem.integer.set(this,k,integer);
	}/**

	 **
	 * Returns the parent field of the type of {@link #integer}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.parent public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	static final com.exedio.cope.ItemField<MapFieldItem> integerParent()
	{
		return MapFieldItem.integer.getParent(MapFieldItem.class);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for mapFieldItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<MapFieldItem> TYPE = newType(MapFieldItem.class)
;}