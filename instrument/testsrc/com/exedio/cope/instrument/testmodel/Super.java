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

package com.exedio.cope.instrument.testmodel;

import com.exedio.cope.DayField;
import com.exedio.cope.IntegerField;
import com.exedio.cope.Item;
import com.exedio.cope.StringField;

/**
 */
public abstract class Super extends Item
{
	public static final StringField superMandatory = new StringField().lengthExact(5);
	
	/**
	 * @cope.initial
	 */
	public static final IntegerField superInitial = new IntegerField().optional();

	public static final DayField superNonInitial = new DayField().optional();

/**

	 **
	 * Creates a new Super with all the fields initially needed.
	 * @param superMandatory the initial value for field {@link #superMandatory}.
	 * @param superInitial the initial value for field {@link #superInitial}.
	 * @throws com.exedio.cope.LengthViolationException if superMandatory violates its length constraint.
	 * @throws com.exedio.cope.MandatoryViolationException if superMandatory is null.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	public Super(
				final java.lang.String superMandatory,
				final java.lang.Integer superInitial)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			Super.superMandatory.map(superMandatory),
			Super.superInitial.map(superInitial),
		});
	}/**

	 **
	 * Creates a new Super and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	protected Super(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	protected Super(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent field {@link #superMandatory}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.String getSuperMandatory()
	{
		return Super.superMandatory.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #superMandatory}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setSuperMandatory(final java.lang.String superMandatory)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException
	{
		Super.superMandatory.set(this,superMandatory);
	}/**

	 **
	 * Returns the value of the persistent field {@link #superInitial}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.Integer getSuperInitial()
	{
		return Super.superInitial.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #superInitial}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setSuperInitial(final java.lang.Integer superInitial)
	{
		Super.superInitial.set(this,superInitial);
	}/**

	 **
	 * Returns the value of the persistent field {@link #superNonInitial}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final com.exedio.cope.util.Day getSuperNonInitial()
	{
		return Super.superNonInitial.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #superNonInitial}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setSuperNonInitial(final com.exedio.cope.util.Day superNonInitial)
	{
		Super.superNonInitial.set(this,superNonInitial);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for super.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<Super> TYPE = newType(Super.class)
;}
