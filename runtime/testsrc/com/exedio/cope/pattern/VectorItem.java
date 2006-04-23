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

import java.util.Date;

import com.exedio.cope.DateAttribute;
import com.exedio.cope.IntegerAttribute;
import com.exedio.cope.Item;
import com.exedio.cope.SetValue;
import com.exedio.cope.StringAttribute;

/**
 * @cope.generic.constructor package
 * @author Ralf Wiebicke
 */
public class VectorItem extends Item
{
	// explicit external source

	public static final IntegerAttribute num1 = new IntegerAttribute(OPTIONAL);

	public static final IntegerAttribute num2 = new IntegerAttribute(OPTIONAL);

	public static final IntegerAttribute num3 = new IntegerAttribute(OPTIONAL);
	
	public static final Vector<Integer> nums = new Vector<Integer>(num1, num2, num3);

	// implicit external source

	public static final Vector<Date> dates = new Vector<Date>(new DateAttribute(OPTIONAL), new DateAttribute(OPTIONAL));

	// internal source

	public static final Vector<String> strings = new Vector<String>(new StringAttribute(OPTIONAL).lengthRange(1, 11), 4);

	public VectorItem(final int initialNum1, final int initialNum2, final int initialNum3)
	{
		super(new SetValue[]{
			num1.map(initialNum1),
			num2.map(initialNum2),
			num3.map(initialNum3),
		});
	}

/**

	 **
	 * Creates a new VectorItem with all the attributes initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of attributes.
	 */
	public VectorItem()
	{
		this(new com.exedio.cope.SetValue[]{
		});
	}/**

	 **
	 * Creates a new VectorItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	VectorItem(final com.exedio.cope.SetValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private VectorItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #num1}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.Integer getNum1()
	{
		return VectorItem.num1.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #num1}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setNum1(final java.lang.Integer num1)
	{
		VectorItem.num1.set(this,num1);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #num2}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.Integer getNum2()
	{
		return VectorItem.num2.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #num2}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setNum2(final java.lang.Integer num2)
	{
		VectorItem.num2.set(this,num2);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #num3}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.Integer getNum3()
	{
		return VectorItem.num3.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #num3}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setNum3(final java.lang.Integer num3)
	{
		VectorItem.num3.set(this,num3);
	}/**

	 **
	 * Returns the value of the vector.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.util.List getNums()
	{
		return VectorItem.nums.get(this);
	}/**

	 **
	 * Sets the vector.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setNums(final java.util.Collection nums)
			throws
				com.exedio.cope.UniqueViolationException,
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.FinalViolationException,
				java.lang.ClassCastException
	{
		VectorItem.nums.set(this,nums);
	}/**

	 **
	 * Returns the value of the vector.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.util.List getDates()
	{
		return VectorItem.dates.get(this);
	}/**

	 **
	 * Sets the vector.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setDates(final java.util.Collection dates)
			throws
				com.exedio.cope.UniqueViolationException,
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.FinalViolationException,
				java.lang.ClassCastException
	{
		VectorItem.dates.set(this,dates);
	}/**

	 **
	 * Returns the value of the vector.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.util.List getStrings()
	{
		return VectorItem.strings.get(this);
	}/**

	 **
	 * Sets the vector.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setStrings(final java.util.Collection strings)
			throws
				com.exedio.cope.UniqueViolationException,
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.FinalViolationException,
				java.lang.ClassCastException
	{
		VectorItem.strings.set(this,strings);
	}/**

	 **
	 * The persistent type information for vectorItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<VectorItem> TYPE = newType(VectorItem.class)
;}
