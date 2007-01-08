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

package com.exedio.cope;

import java.util.Date;

/**
 * @cope.generic.constructor package
 * @author Ralf Wiebicke
 */
class DefaultToItem extends Item
{

	static final BooleanField booleanTrue = new BooleanField(OPTIONAL).defaultTo(true);
	static final BooleanField booleanNone = new BooleanField();

	static final IntegerField integerFive = new IntegerField().defaultTo(5);
	static final IntegerField integerFifty = new IntegerField(OPTIONAL).defaultTo(50);
	static final IntegerField integerNone = new IntegerField(OPTIONAL);

	static final DateField dateEight = new DateField().defaultTo(new Date(8));
	static final DateField dateEighty = new DateField(OPTIONAL).defaultTo(new Date(80));
	static final DateField dateNow = new DateField().defaultToNow();
	static final DateField dateNowOpt = new DateField(OPTIONAL).defaultToNow();
	static final DateField dateNone = new DateField(OPTIONAL);

	enum DefaultToEnum
	{
		ONE, TWO, THREE;
	}
	
	static final EnumField<DefaultToEnum> enumOne = newEnumField(DefaultToEnum.class).defaultTo(DefaultToEnum.ONE);
	static final EnumField<DefaultToEnum> enumTwo = newEnumField(OPTIONAL, DefaultToEnum.class).defaultTo(DefaultToEnum.TWO);
	static final EnumField<DefaultToEnum> enumNone = newEnumField(OPTIONAL, DefaultToEnum.class);

	/**

	 **
	 * Creates a new DefaultToItem with all the fields initially needed.
	 * @param booleanNone the initial value for field {@link #booleanNone}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	DefaultToItem(
				final boolean booleanNone)
	{
		this(new com.exedio.cope.SetValue[]{
			DefaultToItem.booleanNone.map(booleanNone),
		});
	}/**

	 **
	 * Creates a new DefaultToItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	DefaultToItem(final com.exedio.cope.SetValue[] setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private DefaultToItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent field {@link #booleanTrue}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final java.lang.Boolean getBooleanTrue()
	{
		return DefaultToItem.booleanTrue.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #booleanTrue}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setBooleanTrue(final java.lang.Boolean booleanTrue)
	{
		DefaultToItem.booleanTrue.set(this,booleanTrue);
	}/**

	 **
	 * Returns the value of the persistent field {@link #booleanNone}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final boolean getBooleanNone()
	{
		return DefaultToItem.booleanNone.getMandatory(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #booleanNone}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setBooleanNone(final boolean booleanNone)
	{
		DefaultToItem.booleanNone.set(this,booleanNone);
	}/**

	 **
	 * Returns the value of the persistent field {@link #integerFive}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final int getIntegerFive()
	{
		return DefaultToItem.integerFive.getMandatory(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #integerFive}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setIntegerFive(final int integerFive)
	{
		DefaultToItem.integerFive.set(this,integerFive);
	}/**

	 **
	 * Returns the value of the persistent field {@link #integerFifty}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final java.lang.Integer getIntegerFifty()
	{
		return DefaultToItem.integerFifty.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #integerFifty}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setIntegerFifty(final java.lang.Integer integerFifty)
	{
		DefaultToItem.integerFifty.set(this,integerFifty);
	}/**

	 **
	 * Returns the value of the persistent field {@link #integerNone}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final java.lang.Integer getIntegerNone()
	{
		return DefaultToItem.integerNone.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #integerNone}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setIntegerNone(final java.lang.Integer integerNone)
	{
		DefaultToItem.integerNone.set(this,integerNone);
	}/**

	 **
	 * Returns the value of the persistent field {@link #dateEight}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final java.util.Date getDateEight()
	{
		return DefaultToItem.dateEight.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #dateEight}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setDateEight(final java.util.Date dateEight)
			throws
				com.exedio.cope.MandatoryViolationException
	{
		DefaultToItem.dateEight.set(this,dateEight);
	}/**

	 **
	 * Sets the current date for the date field {@link #dateEight}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	final void touchDateEight()
	{
		DefaultToItem.dateEight.touch(this);
	}/**

	 **
	 * Returns the value of the persistent field {@link #dateEighty}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final java.util.Date getDateEighty()
	{
		return DefaultToItem.dateEighty.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #dateEighty}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setDateEighty(final java.util.Date dateEighty)
	{
		DefaultToItem.dateEighty.set(this,dateEighty);
	}/**

	 **
	 * Sets the current date for the date field {@link #dateEighty}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	final void touchDateEighty()
	{
		DefaultToItem.dateEighty.touch(this);
	}/**

	 **
	 * Returns the value of the persistent field {@link #dateNow}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final java.util.Date getDateNow()
	{
		return DefaultToItem.dateNow.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #dateNow}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setDateNow(final java.util.Date dateNow)
			throws
				com.exedio.cope.MandatoryViolationException
	{
		DefaultToItem.dateNow.set(this,dateNow);
	}/**

	 **
	 * Sets the current date for the date field {@link #dateNow}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	final void touchDateNow()
	{
		DefaultToItem.dateNow.touch(this);
	}/**

	 **
	 * Returns the value of the persistent field {@link #dateNowOpt}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final java.util.Date getDateNowOpt()
	{
		return DefaultToItem.dateNowOpt.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #dateNowOpt}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setDateNowOpt(final java.util.Date dateNowOpt)
	{
		DefaultToItem.dateNowOpt.set(this,dateNowOpt);
	}/**

	 **
	 * Sets the current date for the date field {@link #dateNowOpt}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	final void touchDateNowOpt()
	{
		DefaultToItem.dateNowOpt.touch(this);
	}/**

	 **
	 * Returns the value of the persistent field {@link #dateNone}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final java.util.Date getDateNone()
	{
		return DefaultToItem.dateNone.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #dateNone}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setDateNone(final java.util.Date dateNone)
	{
		DefaultToItem.dateNone.set(this,dateNone);
	}/**

	 **
	 * Sets the current date for the date field {@link #dateNone}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	final void touchDateNone()
	{
		DefaultToItem.dateNone.touch(this);
	}/**

	 **
	 * Returns the value of the persistent field {@link #enumOne}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final DefaultToEnum getEnumOne()
	{
		return DefaultToItem.enumOne.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #enumOne}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setEnumOne(final DefaultToEnum enumOne)
			throws
				com.exedio.cope.MandatoryViolationException
	{
		DefaultToItem.enumOne.set(this,enumOne);
	}/**

	 **
	 * Returns the value of the persistent field {@link #enumTwo}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final DefaultToEnum getEnumTwo()
	{
		return DefaultToItem.enumTwo.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #enumTwo}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setEnumTwo(final DefaultToEnum enumTwo)
	{
		DefaultToItem.enumTwo.set(this,enumTwo);
	}/**

	 **
	 * Returns the value of the persistent field {@link #enumNone}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final DefaultToEnum getEnumNone()
	{
		return DefaultToItem.enumNone.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #enumNone}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setEnumNone(final DefaultToEnum enumNone)
	{
		DefaultToItem.enumNone.set(this,enumNone);
	}/**

	 **
	 * The persistent type information for defaultToItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<DefaultToItem> TYPE = newType(DefaultToItem.class)
;}
