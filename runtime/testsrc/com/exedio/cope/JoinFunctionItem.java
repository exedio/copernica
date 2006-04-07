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


/**
 * @cope.persistent
 * @author Ralf Wiebicke
 */
public class JoinFunctionItem extends Item
{
	/**
	 * @cope.initial
	 */
	public static final StringAttribute string = new StringAttribute(OPTIONAL);

	/**
	 * @cope.initial
	 */
	public static final IntegerAttribute integer = new IntegerAttribute(OPTIONAL);

	
/**

	 **
	 * Creates a new JoinFunctionItem with all the attributes initially needed.
	 * @param string the initial value for attribute {@link #string}.
	 * @param integer the initial value for attribute {@link #integer}.
	 * @throws com.exedio.cope.LengthViolationException if string violates its length constraint.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of attributes.
	 */
	public JoinFunctionItem(
				final java.lang.String string,
				final java.lang.Integer integer)
			throws
				com.exedio.cope.LengthViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			JoinFunctionItem.string.map(string),
			JoinFunctionItem.integer.map(integer),
		});
	}/**

	 **
	 * Creates a new JoinFunctionItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private JoinFunctionItem(final com.exedio.cope.SetValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private JoinFunctionItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #string}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.String getString()
	{
		return JoinFunctionItem.string.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #string}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setString(final java.lang.String string)
			throws
				com.exedio.cope.LengthViolationException
	{
		JoinFunctionItem.string.set(this,string);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #integer}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.Integer getInteger()
	{
		return JoinFunctionItem.integer.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #integer}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setInteger(final java.lang.Integer integer)
	{
		JoinFunctionItem.integer.set(this,integer);
	}/**

	 **
	 * The persistent type information for joinFunctionItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(JoinFunctionItem.class)
;}
