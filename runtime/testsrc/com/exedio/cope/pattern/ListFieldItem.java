/*
 * Copyright (C) 2004-2008  exedio GmbH (www.exedio.com)
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

import java.util.Date;

import com.exedio.cope.DateField;
import com.exedio.cope.Item;
import com.exedio.cope.StringField;

public class ListFieldItem extends Item
{
	static final ListField<String> strings = ListField.newList(new StringField().optional().lengthRange(4, 8));
	static final ListField<Date> dates = ListField.newList(new DateField());
	static final ListField<ListFieldItem> items = ListField.newList(newItemField(ListFieldItem.class, CASCADE));

	/**

	 **
	 * Creates a new ListFieldItem with all the fields initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	public ListFieldItem()
	{
		this(new com.exedio.cope.SetValue[]{
		});
	}/**

	 **
	 * Creates a new ListFieldItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private ListFieldItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@SuppressWarnings("unused") private ListFieldItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of {@link #strings}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.util.List<String> getStrings()
	{
		return ListFieldItem.strings.get(this);
	}/**

	 **
	 * Returns the items, for which field list {@link #strings} contains the given element.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getDistinctParentsOf public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	static final java.util.List<ListFieldItem> getDistinctParentsOfStrings(final String element)
	{
		return ListFieldItem.strings.getDistinctParents(ListFieldItem.class,element);
	}/**

	 **
	 * Sets a new value for {@link #strings}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setStrings(final java.util.Collection<? extends String> strings)
			throws
				com.exedio.cope.LengthViolationException,
				java.lang.ClassCastException
	{
		ListFieldItem.strings.set(this,strings);
	}/**

	 **
	 * Returns the parent field of the type of {@link #strings}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.Parent public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	static final com.exedio.cope.ItemField<ListFieldItem> stringsParent()
	{
		return ListFieldItem.strings.getParent(ListFieldItem.class);
	}/**

	 **
	 * Returns the value of {@link #dates}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.util.List<Date> getDates()
	{
		return ListFieldItem.dates.get(this);
	}/**

	 **
	 * Returns the items, for which field list {@link #dates} contains the given element.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getDistinctParentsOf public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	static final java.util.List<ListFieldItem> getDistinctParentsOfDates(final Date element)
	{
		return ListFieldItem.dates.getDistinctParents(ListFieldItem.class,element);
	}/**

	 **
	 * Sets a new value for {@link #dates}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setDates(final java.util.Collection<? extends Date> dates)
			throws
				com.exedio.cope.MandatoryViolationException,
				java.lang.ClassCastException
	{
		ListFieldItem.dates.set(this,dates);
	}/**

	 **
	 * Returns the parent field of the type of {@link #dates}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.Parent public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	static final com.exedio.cope.ItemField<ListFieldItem> datesParent()
	{
		return ListFieldItem.dates.getParent(ListFieldItem.class);
	}/**

	 **
	 * Returns the value of {@link #items}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.util.List<ListFieldItem> getItems()
	{
		return ListFieldItem.items.get(this);
	}/**

	 **
	 * Returns the items, for which field list {@link #items} contains the given element.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getDistinctParentsOf public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	static final java.util.List<ListFieldItem> getDistinctParentsOfItems(final ListFieldItem element)
	{
		return ListFieldItem.items.getDistinctParents(ListFieldItem.class,element);
	}/**

	 **
	 * Sets a new value for {@link #items}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setItems(final java.util.Collection<? extends ListFieldItem> items)
			throws
				com.exedio.cope.MandatoryViolationException,
				java.lang.ClassCastException
	{
		ListFieldItem.items.set(this,items);
	}/**

	 **
	 * Returns the parent field of the type of {@link #items}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.Parent public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	static final com.exedio.cope.ItemField<ListFieldItem> itemsParent()
	{
		return ListFieldItem.items.getParent(ListFieldItem.class);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for listFieldItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<ListFieldItem> TYPE = newType(ListFieldItem.class)
;}
