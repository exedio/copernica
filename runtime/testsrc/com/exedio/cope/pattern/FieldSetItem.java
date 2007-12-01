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

import com.exedio.cope.DateField;
import com.exedio.cope.Item;
import com.exedio.cope.StringField;

public class FieldSetItem extends Item
{
	static final SetField<String> strings = SetField.newSet(new StringField().optional().lengthRange(4, 8));
	static final SetField<Date> dates = SetField.newSet(new DateField());
	
	
	/**

	 **
	 * Creates a new FieldSetItem with all the fields initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	public FieldSetItem()
	{
		this(new com.exedio.cope.SetValue[]{
		});
	}/**

	 **
	 * Creates a new FieldSetItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private FieldSetItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@SuppressWarnings("unused") private FieldSetItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of {@link #strings}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.util.Set<String> getStrings()
	{
		return FieldSetItem.strings.get(this);
	}/**

	 **
	 * Returns the items, for which field set {@link #strings} contains the given element.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	static final java.util.List<FieldSetItem> getParentsOfStrings(final String element)
	{
		return FieldSetItem.strings.getParents(FieldSetItem.class,element);
	}/**

	 **
	 * Sets a new value for {@link #strings}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setStrings(final java.util.Collection<? extends String> strings)
			throws
				com.exedio.cope.LengthViolationException,
				java.lang.ClassCastException
	{
		FieldSetItem.strings.set(this,strings);
	}/**

	 **
	 * Adds a new element to {@link #strings}.
	 * @return <tt>true</tt> if the field set changed as a result of the call.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final boolean addToStrings(final String element)
			throws
				com.exedio.cope.LengthViolationException,
				java.lang.ClassCastException
	{
		return FieldSetItem.strings.add(this,element);
	}/**

	 **
	 * Removes an element from {@link #strings}.
	 * @return <tt>true</tt> if the field set changed as a result of the call.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final boolean removeFromStrings(final String element)
			throws
				com.exedio.cope.LengthViolationException,
				java.lang.ClassCastException
	{
		return FieldSetItem.strings.remove(this,element);
	}/**

	 **
	 * Returns the parent field of the type of {@link #strings}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.parent public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	static final com.exedio.cope.ItemField<FieldSetItem> stringsParent()
	{
		return FieldSetItem.strings.getParent(FieldSetItem.class);
	}/**

	 **
	 * Returns the value of {@link #dates}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.util.Set<Date> getDates()
	{
		return FieldSetItem.dates.get(this);
	}/**

	 **
	 * Returns the items, for which field set {@link #dates} contains the given element.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	static final java.util.List<FieldSetItem> getParentsOfDates(final Date element)
	{
		return FieldSetItem.dates.getParents(FieldSetItem.class,element);
	}/**

	 **
	 * Sets a new value for {@link #dates}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setDates(final java.util.Collection<? extends Date> dates)
			throws
				com.exedio.cope.MandatoryViolationException,
				java.lang.ClassCastException
	{
		FieldSetItem.dates.set(this,dates);
	}/**

	 **
	 * Adds a new element to {@link #dates}.
	 * @return <tt>true</tt> if the field set changed as a result of the call.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final boolean addToDates(final Date element)
			throws
				com.exedio.cope.MandatoryViolationException,
				java.lang.ClassCastException
	{
		return FieldSetItem.dates.add(this,element);
	}/**

	 **
	 * Removes an element from {@link #dates}.
	 * @return <tt>true</tt> if the field set changed as a result of the call.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final boolean removeFromDates(final Date element)
			throws
				com.exedio.cope.MandatoryViolationException,
				java.lang.ClassCastException
	{
		return FieldSetItem.dates.remove(this,element);
	}/**

	 **
	 * Returns the parent field of the type of {@link #dates}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.parent public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	static final com.exedio.cope.ItemField<FieldSetItem> datesParent()
	{
		return FieldSetItem.dates.getParent(FieldSetItem.class);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for fieldSetItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<FieldSetItem> TYPE = newType(FieldSetItem.class)
;}
