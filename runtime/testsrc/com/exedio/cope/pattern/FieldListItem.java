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

import java.util.Date;
import java.util.List;

import com.exedio.cope.DateField;
import com.exedio.cope.Item;
import com.exedio.cope.StringField;

public class FieldListItem extends Item
{

	static final FieldList<String> strings = FieldList.newList(new StringField(OPTIONAL).lengthRange(4, 8));
	static final FieldList<Date> dates = FieldList.newList(new DateField());
	static final FieldList<FieldListItem> items = FieldList.newList(newItemField(FieldListItem.class, CASCADE));

	// TODO generate by instrumentor
	static final List<? extends Item> getDistinctParentsOfStrings(final String element)
	{
		return FieldListItem.strings.getDistinctParents(element);
	}
	
	// TODO generate by instrumentor
	static final List<? extends Item> getDistinctParentsOfDates(final Date element)
	{
		return FieldListItem.dates.getDistinctParents(element);
	}
	
	// TODO generate by instrumentor
	static final List<? extends Item> getDistinctParentsOfItems(final FieldListItem element)
	{
		return FieldListItem.items.getDistinctParents(element);
	}
	
	/**

	 **
	 * Creates a new FieldListItem with all the fields initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	public FieldListItem()
	{
		this(new com.exedio.cope.SetValue[]{
		});
	}/**

	 **
	 * Creates a new FieldListItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private FieldListItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private FieldListItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the contents of the field list {@link #strings}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.util.List<String> getStrings()
	{
		return FieldListItem.strings.get(this);
	}/**

	 **
	 * Sets the contents of the field list {@link #strings}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setStrings(final java.util.Collection<? extends String> strings)
			throws
				com.exedio.cope.UniqueViolationException,
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.FinalViolationException,
				java.lang.ClassCastException
	{
		FieldListItem.strings.set(this,strings);
	}/**

	 **
	 * Returns the contents of the field list {@link #dates}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.util.List<Date> getDates()
	{
		return FieldListItem.dates.get(this);
	}/**

	 **
	 * Sets the contents of the field list {@link #dates}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setDates(final java.util.Collection<? extends Date> dates)
			throws
				com.exedio.cope.UniqueViolationException,
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.FinalViolationException,
				java.lang.ClassCastException
	{
		FieldListItem.dates.set(this,dates);
	}/**

	 **
	 * Returns the contents of the field list {@link #items}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.util.List<FieldListItem> getItems()
	{
		return FieldListItem.items.get(this);
	}/**

	 **
	 * Sets the contents of the field list {@link #items}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setItems(final java.util.Collection<? extends FieldListItem> items)
			throws
				com.exedio.cope.UniqueViolationException,
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.FinalViolationException,
				java.lang.ClassCastException
	{
		FieldListItem.items.set(this,items);
	}/**

	 **
	 * The persistent type information for fieldListItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<FieldListItem> TYPE = newType(FieldListItem.class)
;}
