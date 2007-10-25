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


class CompareConditionItem extends Item
{
	/** @cope.initial */ static final StringField string = new StringField().optional();
	static final StringField otherString = new StringField().optional();
	/** @cope.initial */ static final IntegerField intx = new IntegerField().optional();
	/** @cope.initial */ static final LongField longx = new LongField().optional();
	/** @cope.initial */ static final DoubleField doublex = new DoubleField().optional();
	/** @cope.initial */ static final DateField date = new DateField().optional();
	/** @cope.initial */ static final DayField day = new DayField().optional();
	/** @cope.initial */ static final EnumField<YEnum> enumx = newEnumField(YEnum.class).optional();

	static enum YEnum
	{
		V1, V2, V3, V4, V5, VX;
	}
	
	static final ItemField<CompareConditionItem> item = newItemField(CompareConditionItem.class, NULLIFY);
	
	/**

	 **
	 * Creates a new CompareConditionItem with all the fields initially needed.
	 * @param string the initial value for field {@link #string}.
	 * @param intx the initial value for field {@link #intx}.
	 * @param longx the initial value for field {@link #longx}.
	 * @param doublex the initial value for field {@link #doublex}.
	 * @param date the initial value for field {@link #date}.
	 * @param day the initial value for field {@link #day}.
	 * @param enumx the initial value for field {@link #enumx}.
	 * @throws com.exedio.cope.LengthViolationException if string violates its length constraint.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	CompareConditionItem(
				final java.lang.String string,
				final java.lang.Integer intx,
				final java.lang.Long longx,
				final java.lang.Double doublex,
				final java.util.Date date,
				final com.exedio.cope.util.Day day,
				final YEnum enumx)
			throws
				com.exedio.cope.LengthViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			CompareConditionItem.string.map(string),
			CompareConditionItem.intx.map(intx),
			CompareConditionItem.longx.map(longx),
			CompareConditionItem.doublex.map(doublex),
			CompareConditionItem.date.map(date),
			CompareConditionItem.day.map(day),
			CompareConditionItem.enumx.map(enumx),
		});
	}/**

	 **
	 * Creates a new CompareConditionItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private CompareConditionItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@SuppressWarnings("unused") private CompareConditionItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent field {@link #string}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getString()
	{
		return CompareConditionItem.string.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #string}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setString(final java.lang.String string)
			throws
				com.exedio.cope.LengthViolationException
	{
		CompareConditionItem.string.set(this,string);
	}/**

	 **
	 * Returns the value of the persistent field {@link #otherString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getOtherString()
	{
		return CompareConditionItem.otherString.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #otherString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setOtherString(final java.lang.String otherString)
			throws
				com.exedio.cope.LengthViolationException
	{
		CompareConditionItem.otherString.set(this,otherString);
	}/**

	 **
	 * Returns the value of the persistent field {@link #intx}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.Integer getIntx()
	{
		return CompareConditionItem.intx.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #intx}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setIntx(final java.lang.Integer intx)
	{
		CompareConditionItem.intx.set(this,intx);
	}/**

	 **
	 * Returns the value of the persistent field {@link #longx}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.Long getLongx()
	{
		return CompareConditionItem.longx.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #longx}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setLongx(final java.lang.Long longx)
	{
		CompareConditionItem.longx.set(this,longx);
	}/**

	 **
	 * Returns the value of the persistent field {@link #doublex}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.Double getDoublex()
	{
		return CompareConditionItem.doublex.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #doublex}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setDoublex(final java.lang.Double doublex)
	{
		CompareConditionItem.doublex.set(this,doublex);
	}/**

	 **
	 * Returns the value of the persistent field {@link #date}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.util.Date getDate()
	{
		return CompareConditionItem.date.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #date}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setDate(final java.util.Date date)
	{
		CompareConditionItem.date.set(this,date);
	}/**

	 **
	 * Sets the current date for the date field {@link #date}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void touchDate()
	{
		CompareConditionItem.date.touch(this);
	}/**

	 **
	 * Returns the value of the persistent field {@link #day}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final com.exedio.cope.util.Day getDay()
	{
		return CompareConditionItem.day.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #day}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setDay(final com.exedio.cope.util.Day day)
	{
		CompareConditionItem.day.set(this,day);
	}/**

	 **
	 * Returns the value of the persistent field {@link #enumx}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final YEnum getEnumx()
	{
		return CompareConditionItem.enumx.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #enumx}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setEnumx(final YEnum enumx)
	{
		CompareConditionItem.enumx.set(this,enumx);
	}/**

	 **
	 * Returns the value of the persistent field {@link #item}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final CompareConditionItem getItem()
	{
		return CompareConditionItem.item.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #item}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setItem(final CompareConditionItem item)
	{
		CompareConditionItem.item.set(this,item);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for compareConditionItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<CompareConditionItem> TYPE = newType(CompareConditionItem.class)
;}
