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
import com.exedio.cope.IntegerField;
import com.exedio.cope.Item;
import com.exedio.cope.StringField;

/**
 * @cope.generic.constructor package
 * @author Ralf Wiebicke
 */
public class FieldListLimitedItem extends Item
{
	// explicit external source

	static final IntegerField num1 = new IntegerField().optional();

	static final IntegerField num2 = new IntegerField().optional();

	static final IntegerField num3 = new IntegerField().optional();
	
	static final FieldListLimited<Integer> nums = FieldListLimited.newList(num1, num2, num3);

	// implicit external source

	static final FieldListLimited<Date> dates = FieldListLimited.newList(new DateField().optional(), new DateField().optional());

	// internal source

	static final FieldListLimited<String> strings = FieldListLimited.newList(new StringField().optional().lengthRange(1, 11), 4);

	FieldListLimitedItem(final int initialNum1, final int initialNum2, final int initialNum3)
	{
		super(
			num1.map(initialNum1),
			num2.map(initialNum2),
			num3.map(initialNum3));
	}

/**

	 **
	 * Creates a new FieldListLimitedItem with all the fields initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	public FieldListLimitedItem()
	{
		this(new com.exedio.cope.SetValue[]{
		});
	}/**

	 **
	 * Creates a new FieldListLimitedItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	FieldListLimitedItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private FieldListLimitedItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent field {@link #num1}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final java.lang.Integer getNum1()
	{
		return FieldListLimitedItem.num1.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #num1}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setNum1(final java.lang.Integer num1)
	{
		FieldListLimitedItem.num1.set(this,num1);
	}/**

	 **
	 * Returns the value of the persistent field {@link #num2}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final java.lang.Integer getNum2()
	{
		return FieldListLimitedItem.num2.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #num2}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setNum2(final java.lang.Integer num2)
	{
		FieldListLimitedItem.num2.set(this,num2);
	}/**

	 **
	 * Returns the value of the persistent field {@link #num3}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final java.lang.Integer getNum3()
	{
		return FieldListLimitedItem.num3.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #num3}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setNum3(final java.lang.Integer num3)
	{
		FieldListLimitedItem.num3.set(this,num3);
	}/**

	 **
	 * Returns the contents of the field list {@link #nums}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.util.List<Integer> getNums()
	{
		return FieldListLimitedItem.nums.get(this);
	}/**

	 **
	 * Sets the contents of the field list {@link #nums}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setNums(final java.util.Collection<? extends Integer> nums)
			throws
				com.exedio.cope.UniqueViolationException,
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.FinalViolationException,
				java.lang.ClassCastException
	{
		FieldListLimitedItem.nums.set(this,nums);
	}/**

	 **
	 * Returns the contents of the field list {@link #dates}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.util.List<Date> getDates()
	{
		return FieldListLimitedItem.dates.get(this);
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
		FieldListLimitedItem.dates.set(this,dates);
	}/**

	 **
	 * Returns the contents of the field list {@link #strings}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.util.List<String> getStrings()
	{
		return FieldListLimitedItem.strings.get(this);
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
		FieldListLimitedItem.strings.set(this,strings);
	}/**

	 **
	 * The persistent type information for fieldListLimitedItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<FieldListLimitedItem> TYPE = newType(FieldListLimitedItem.class)
;}
