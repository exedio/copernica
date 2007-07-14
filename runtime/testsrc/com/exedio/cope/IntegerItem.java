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
 */
public class IntegerItem extends Item
{
	public static final IntegerField any = new IntegerField().optional();

	public static final IntegerField mandatory = new IntegerField();
	
	public static final IntegerField min4 = new IntegerField().optional().min(4);
	public static final IntegerField max4 = new IntegerField().optional().max(4);
	public static final IntegerField min4Max8 = new IntegerField().optional().range(4, 8);
	
	IntegerItem(final Integer mandatory) throws LengthViolationException, MandatoryViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			IntegerItem.mandatory.map(mandatory),
		});
	}
	
	IntegerItem(final Integer max4, final Date dummy) throws LengthViolationException, MandatoryViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			IntegerItem.mandatory.map(7777777),
			IntegerItem.max4.map(max4),
		});
	}
	
	/**

	 **
	 * Creates a new IntegerItem with all the fields initially needed.
	 * @param mandatory the initial value for field {@link #mandatory}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	public IntegerItem(
				final int mandatory)
	{
		this(new com.exedio.cope.SetValue[]{
			IntegerItem.mandatory.map(mandatory),
		});
	}/**

	 **
	 * Creates a new IntegerItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	IntegerItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private IntegerItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent field {@link #any}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	public final java.lang.Integer getAny()
	{
		return IntegerItem.any.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #any}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setAny(final java.lang.Integer any)
	{
		IntegerItem.any.set(this,any);
	}/**

	 **
	 * Returns the value of the persistent field {@link #mandatory}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	public final int getMandatory()
	{
		return IntegerItem.mandatory.getMandatory(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #mandatory}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setMandatory(final int mandatory)
	{
		IntegerItem.mandatory.set(this,mandatory);
	}/**

	 **
	 * Returns the value of the persistent field {@link #min4}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	public final java.lang.Integer getMin4()
	{
		return IntegerItem.min4.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #min4}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setMin4(final java.lang.Integer min4)
			throws
				com.exedio.cope.RangeViolationException
	{
		IntegerItem.min4.set(this,min4);
	}/**

	 **
	 * Returns the value of the persistent field {@link #max4}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	public final java.lang.Integer getMax4()
	{
		return IntegerItem.max4.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #max4}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setMax4(final java.lang.Integer max4)
			throws
				com.exedio.cope.RangeViolationException
	{
		IntegerItem.max4.set(this,max4);
	}/**

	 **
	 * Returns the value of the persistent field {@link #min4Max8}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	public final java.lang.Integer getMin4Max8()
	{
		return IntegerItem.min4Max8.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #min4Max8}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setMin4Max8(final java.lang.Integer min4Max8)
			throws
				com.exedio.cope.RangeViolationException
	{
		IntegerItem.min4Max8.set(this,min4Max8);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for integerItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<IntegerItem> TYPE = newType(IntegerItem.class)
;}
