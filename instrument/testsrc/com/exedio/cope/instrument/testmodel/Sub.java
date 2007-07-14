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

import com.exedio.cope.BooleanField;
import com.exedio.cope.DateField;
import com.exedio.cope.LongField;

public class Sub extends Super
{
	public static final BooleanField subMandatory = new BooleanField();
	
	/**
	 * @cope.initial
	 */
	public static final LongField subInitial = new LongField().optional();

	public static final DateField subNonInitial = new DateField().optional();

/**

	 **
	 * Creates a new Sub with all the fields initially needed.
	 * @param superMandatory the initial value for field {@link #superMandatory}.
	 * @param superInitial the initial value for field {@link #superInitial}.
	 * @param subMandatory the initial value for field {@link #subMandatory}.
	 * @param subInitial the initial value for field {@link #subInitial}.
	 * @throws com.exedio.cope.LengthViolationException if superMandatory violates its length constraint.
	 * @throws com.exedio.cope.MandatoryViolationException if superMandatory is null.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	public Sub(
				final java.lang.String superMandatory,
				final java.lang.Integer superInitial,
				final boolean subMandatory,
				final java.lang.Long subInitial)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			Sub.superMandatory.map(superMandatory),
			Sub.superInitial.map(superInitial),
			Sub.subMandatory.map(subMandatory),
			Sub.subInitial.map(subInitial),
		});
	}/**

	 **
	 * Creates a new Sub and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private Sub(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private Sub(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent field {@link #subMandatory}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	public final boolean getSubMandatory()
	{
		return Sub.subMandatory.getMandatory(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #subMandatory}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setSubMandatory(final boolean subMandatory)
	{
		Sub.subMandatory.set(this,subMandatory);
	}/**

	 **
	 * Returns the value of the persistent field {@link #subInitial}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	public final java.lang.Long getSubInitial()
	{
		return Sub.subInitial.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #subInitial}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setSubInitial(final java.lang.Long subInitial)
	{
		Sub.subInitial.set(this,subInitial);
	}/**

	 **
	 * Returns the value of the persistent field {@link #subNonInitial}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	public final java.util.Date getSubNonInitial()
	{
		return Sub.subNonInitial.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #subNonInitial}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setSubNonInitial(final java.util.Date subNonInitial)
	{
		Sub.subNonInitial.set(this,subNonInitial);
	}/**

	 **
	 * Sets the current date for the date field {@link #subNonInitial}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void touchSubNonInitial()
	{
		Sub.subNonInitial.touch(this);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for sub.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<Sub> TYPE = newType(Sub.class)
;}
