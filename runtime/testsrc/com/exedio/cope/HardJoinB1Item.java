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

class HardJoinB1Item extends Item
{
	static final StringField code = new StringField().unique();
	
	/**
	 * @cope.initial
	 */
	static final IntegerField b1 = new IntegerField().optional();
	
	
	/**

	 **
	 * Creates a new HardJoinB1Item with all the fields initially needed.
	 * @param code the initial value for field {@link #code}.
	 * @param b1 the initial value for field {@link #b1}.
	 * @throws com.exedio.cope.LengthViolationException if code violates its length constraint.
	 * @throws com.exedio.cope.MandatoryViolationException if code is null.
	 * @throws com.exedio.cope.UniqueViolationException if code is not unique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	HardJoinB1Item(
				final java.lang.String code,
				final java.lang.Integer b1)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			HardJoinB1Item.code.map(code),
			HardJoinB1Item.b1.map(b1),
		});
	}/**

	 **
	 * Creates a new HardJoinB1Item and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	protected HardJoinB1Item(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	protected HardJoinB1Item(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of {@link #code}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getCode()
	{
		return HardJoinB1Item.code.get(this);
	}/**

	 **
	 * Sets a new value for {@link #code}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setCode(final java.lang.String code)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		HardJoinB1Item.code.set(this,code);
	}/**

	 **
	 * Finds a hardJoinB1Item by it's {@link #code}.
	 * @param code shall be equal to field {@link #code}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.finder public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	static final HardJoinB1Item forCode(final java.lang.String code)
	{
		return HardJoinB1Item.code.searchUnique(HardJoinB1Item.class,code);
	}/**

	 **
	 * Finds a hardJoinB1Item by it's {@link #code}.
	 * @deprecated use for{@link #code} instead.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.finder public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	@Deprecated
	static final HardJoinB1Item findByCode(final java.lang.String code)
	{
		return HardJoinB1Item.code.searchUnique(HardJoinB1Item.class,code);
	}/**

	 **
	 * Returns the value of {@link #b1}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.Integer getB1()
	{
		return HardJoinB1Item.b1.get(this);
	}/**

	 **
	 * Sets a new value for {@link #b1}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setB1(final java.lang.Integer b1)
	{
		HardJoinB1Item.b1.set(this,b1);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for hardJoinB1Item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	static final com.exedio.cope.Type<HardJoinB1Item> TYPE = newType(HardJoinB1Item.class)
;}
