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

package com.exedio.cope;


public class CompareConditionItem extends Item
{
	public static final StringField someString = new StringField(OPTIONAL);
	public static final StringField string = new StringField();
	public static final IntegerField intx = new IntegerField();
	public static final LongField longx = new LongField();
	public static final DoubleField someNotNullDouble = new DoubleField();
	public static final DateField someDate = new DateField(OPTIONAL);
	public static final EnumField<XSomeEnum> someNotNullEnum = newEnumField(XSomeEnum.class);

	public static enum XSomeEnum
	{
		enumValue1,
		enumValue2,
		enumValue3;
	}
	
	/**

	 **
	 * Creates a new CompareConditionItem with all the fields initially needed.
	 * @param string the initial value for field {@link #string}.
	 * @param intx the initial value for field {@link #intx}.
	 * @param longx the initial value for field {@link #longx}.
	 * @param someNotNullDouble the initial value for field {@link #someNotNullDouble}.
	 * @param someNotNullEnum the initial value for field {@link #someNotNullEnum}.
	 * @throws com.exedio.cope.LengthViolationException if string violates its length constraint.
	 * @throws com.exedio.cope.MandatoryViolationException if string, someNotNullEnum is null.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	public CompareConditionItem(
				final java.lang.String string,
				final int intx,
				final long longx,
				final double someNotNullDouble,
				final XSomeEnum someNotNullEnum)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			CompareConditionItem.string.map(string),
			CompareConditionItem.intx.map(intx),
			CompareConditionItem.longx.map(longx),
			CompareConditionItem.someNotNullDouble.map(someNotNullDouble),
			CompareConditionItem.someNotNullEnum.map(someNotNullEnum),
		});
	}/**

	 **
	 * Creates a new CompareConditionItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private CompareConditionItem(final com.exedio.cope.SetValue[] setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private CompareConditionItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent field {@link #someString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	public final java.lang.String getSomeString()
	{
		return CompareConditionItem.someString.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #someString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setSomeString(final java.lang.String someString)
			throws
				com.exedio.cope.LengthViolationException
	{
		CompareConditionItem.someString.set(this,someString);
	}/**

	 **
	 * Returns the value of the persistent field {@link #string}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	public final java.lang.String getString()
	{
		return CompareConditionItem.string.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #string}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setString(final java.lang.String string)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException
	{
		CompareConditionItem.string.set(this,string);
	}/**

	 **
	 * Returns the value of the persistent field {@link #intx}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	public final int getIntx()
	{
		return CompareConditionItem.intx.getMandatory(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #intx}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setIntx(final int intx)
	{
		CompareConditionItem.intx.set(this,intx);
	}/**

	 **
	 * Returns the value of the persistent field {@link #longx}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	public final long getLongx()
	{
		return CompareConditionItem.longx.getMandatory(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #longx}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setLongx(final long longx)
	{
		CompareConditionItem.longx.set(this,longx);
	}/**

	 **
	 * Returns the value of the persistent field {@link #someNotNullDouble}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	public final double getSomeNotNullDouble()
	{
		return CompareConditionItem.someNotNullDouble.getMandatory(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #someNotNullDouble}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setSomeNotNullDouble(final double someNotNullDouble)
	{
		CompareConditionItem.someNotNullDouble.set(this,someNotNullDouble);
	}/**

	 **
	 * Returns the value of the persistent field {@link #someDate}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	public final java.util.Date getSomeDate()
	{
		return CompareConditionItem.someDate.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #someDate}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setSomeDate(final java.util.Date someDate)
	{
		CompareConditionItem.someDate.set(this,someDate);
	}/**

	 **
	 * Sets the current date for the date field {@link #someDate}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void touchSomeDate()
	{
		CompareConditionItem.someDate.touch(this);
	}/**

	 **
	 * Returns the value of the persistent field {@link #someNotNullEnum}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	public final XSomeEnum getSomeNotNullEnum()
	{
		return CompareConditionItem.someNotNullEnum.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #someNotNullEnum}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setSomeNotNullEnum(final XSomeEnum someNotNullEnum)
			throws
				com.exedio.cope.MandatoryViolationException
	{
		CompareConditionItem.someNotNullEnum.set(this,someNotNullEnum);
	}/**

	 **
	 * The persistent type information for compareConditionItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<CompareConditionItem> TYPE = newType(CompareConditionItem.class)
;}
