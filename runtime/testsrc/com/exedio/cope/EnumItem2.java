/*
 * Copyright (C) 2004-2005  exedio GmbH (www.exedio.com)
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
public class EnumItem2 extends Item
{
	public static final class Status extends EnumValue
	{
		public static final int state1NUM = 40;
		public static final Status state1 = new Status();

		public static final int state2NUM = 50;
		public static final Status state2 = new Status();
	}
	
	public static final EnumAttribute status = new EnumAttribute(MANDATORY, Status.class);

	
/**

	 **
	 * Creates a new EnumItem2 with all the attributes initially needed.
	 * @param status the initial value for attribute {@link #status}.
	 * @throws com.exedio.cope.MandatoryViolationException if status is null.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <code>@cope.constructor public|package|protected|private|none</code> in the class comment and <code>@cope.initial</code> in the comment of attributes.
	 */
	public EnumItem2(
				final Status status)
			throws
				com.exedio.cope.MandatoryViolationException
	{
		this(new com.exedio.cope.AttributeValue[]{
			EnumItem2.status.map(status),
		});
		throwInitialMandatoryViolationException();
	}/**

	 **
	 * Creates a new EnumItem2 and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.generic.constructor public|package|protected|private|none</code> in the class comment.
	 */
	private EnumItem2(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private EnumItem2(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #status}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final Status getStatus()
	{
		return (Status)EnumItem2.status.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #status}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setStatus(final Status status)
			throws
				com.exedio.cope.MandatoryViolationException
	{
		try
		{
			EnumItem2.status.set(this,status);
		}
		catch(com.exedio.cope.FinalViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
	}/**

	 **
	 * The persistent type information for enumItem2.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.type public|package|protected|private|none</code> in the class comment.
	 */
	public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(EnumItem2.class)
;}
