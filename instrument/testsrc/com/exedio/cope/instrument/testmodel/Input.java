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

import com.exedio.cope.IntegerField;
import com.exedio.cope.Item;
import com.exedio.cope.StringField;
import com.exedio.cope.UniqueConstraint;

/**
 */
public class Input extends Item
{
	public static final StringField string = new StringField().toFinal();
	public static final IntegerField integer = new IntegerField().toFinal();
	public static final UniqueConstraint unique = new UniqueConstraint( string, integer );
	
/**

	 **
	 * Creates a new Input with all the fields initially needed.
	 * @param string the initial value for field {@link #string}.
	 * @param integer the initial value for field {@link #integer}.
	 * @throws com.exedio.cope.LengthViolationException if string violates its length constraint.
	 * @throws com.exedio.cope.MandatoryViolationException if string is null.
	 * @throws com.exedio.cope.UniqueViolationException if string, integer is not unique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	public Input(
				final java.lang.String string,
				final int integer)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			Input.string.map(string),
			Input.integer.map(integer),
		});
	}/**

	 **
	 * Creates a new Input and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	protected Input(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	protected Input(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent field {@link #string}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	public final java.lang.String getString()
	{
		return Input.string.get(this);
	}/**

	 **
	 * Returns the value of the persistent field {@link #integer}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	public final int getInteger()
	{
		return Input.integer.getMandatory(this);
	}/**

	 **
	 * Finds a input by it's unique fields.
	 * @param string shall be equal to field {@link #string}.
	 * @param integer shall be equal to field {@link #integer}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public static final Input findByUnique(final java.lang.String string,final int integer)
	{
		return (Input)Input.unique.searchUnique(new Object[]{string,new java.lang.Integer(integer)});
	}/**

	 **
	 * The persistent type information for input.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<Input> TYPE = newType(Input.class)
;}
