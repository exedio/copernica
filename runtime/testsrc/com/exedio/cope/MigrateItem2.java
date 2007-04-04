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

/**
 * @cope.type none
 */
class MigrateItem2 extends Item
{
	static final StringField field5 = new StringField();
	static final StringField field6 = new StringField();
	static final StringField field6b = new StringField();
	static final StringField field7 = new StringField();
	
	/**
	 * The persistent type information.
	 */
	public static final com.exedio.cope.Type<MigrateItem2> TYPE = newType(MigrateItem2.class, "MigrateItem");
	
	/**

	 **
	 * Creates a new MigrateItem2 with all the fields initially needed.
	 * @param field5 the initial value for field {@link #field5}.
	 * @param field6 the initial value for field {@link #field6}.
	 * @param field6b the initial value for field {@link #field6b}.
	 * @param field7 the initial value for field {@link #field7}.
	 * @throws com.exedio.cope.LengthViolationException if field5, field6, field6b, field7 violates its length constraint.
	 * @throws com.exedio.cope.MandatoryViolationException if field5, field6, field6b, field7 is null.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	MigrateItem2(
				final java.lang.String field5,
				final java.lang.String field6,
				final java.lang.String field6b,
				final java.lang.String field7)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			MigrateItem2.field5.map(field5),
			MigrateItem2.field6.map(field6),
			MigrateItem2.field6b.map(field6b),
			MigrateItem2.field7.map(field7),
		});
	}/**

	 **
	 * Creates a new MigrateItem2 and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private MigrateItem2(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private MigrateItem2(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent field {@link #field5}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final java.lang.String getField5()
	{
		return MigrateItem2.field5.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #field5}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setField5(final java.lang.String field5)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException
	{
		MigrateItem2.field5.set(this,field5);
	}/**

	 **
	 * Returns the value of the persistent field {@link #field6}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final java.lang.String getField6()
	{
		return MigrateItem2.field6.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #field6}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setField6(final java.lang.String field6)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException
	{
		MigrateItem2.field6.set(this,field6);
	}/**

	 **
	 * Returns the value of the persistent field {@link #field6b}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final java.lang.String getField6b()
	{
		return MigrateItem2.field6b.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #field6b}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setField6b(final java.lang.String field6b)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException
	{
		MigrateItem2.field6b.set(this,field6b);
	}/**

	 **
	 * Returns the value of the persistent field {@link #field7}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final java.lang.String getField7()
	{
		return MigrateItem2.field7.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #field7}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setField7(final java.lang.String field7)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException
	{
		MigrateItem2.field7.set(this,field7);
	}}