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
import com.exedio.cope.ItemField;
import com.exedio.cope.StringField;

public class FieldSetItem extends Item
{
	static final FieldSet<String> strings = FieldSet.newSet(new StringField().optional().lengthRange(4, 8));
	static final FieldSet<Date> dates = FieldSet.newSet(new DateField());
	
	// TODO generate by instrumentor
	public static final ItemField<FieldSetItem> stringsParent()
	{
		return strings.getParent(FieldSetItem.class);
	}
	
	// TODO generate by instrumentor
	public static final ItemField<FieldSetItem> datesParent()
	{
		return dates.getParent(FieldSetItem.class);
	}
	
	// TODO generate by instrumentor
	public static final List<FieldSetItem> getParentsOfStrings(final String element)
	{
		return FieldSetItem.strings.getParents(element, FieldSetItem.class);
	}
	
	// TODO generate by instrumentor
	public final boolean addToStrings(final String element)
	{
		return FieldSetItem.strings.add(this, element);
	}
	
	// TODO generate by instrumentor
	public final boolean removeFromStrings(final String element)
	{
		return FieldSetItem.strings.remove(this, element);
	}
	
	
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
	private FieldSetItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the contents of the field set {@link #strings}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.util.Set<String> getStrings()
	{
		return FieldSetItem.strings.get(this);
	}/**

	 **
	 * Sets the contents of the field set {@link #strings}.
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
		FieldSetItem.strings.set(this,strings);
	}/**

	 **
	 * Returns the contents of the field set {@link #dates}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.util.Set<Date> getDates()
	{
		return FieldSetItem.dates.get(this);
	}/**

	 **
	 * Sets the contents of the field set {@link #dates}.
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
		FieldSetItem.dates.set(this,dates);
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
