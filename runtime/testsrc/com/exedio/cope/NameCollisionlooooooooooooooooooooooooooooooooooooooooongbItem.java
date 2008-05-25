/*
 * Copyright (C) 2004-2008  exedio GmbH (www.exedio.com)
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

@CopeSchemaName("NameCollisionlongBItem")
class NameCollisionlooooooooooooooooooooooooooooooooooooooooongbItem extends Item
{

	static final StringField code = new StringField().unique();

/**

	 **
	 * Creates a new NameCollisionlooooooooooooooooooooooooooooooooooooooooongbItem with all the fields initially needed.
	 * @param code the initial value for field {@link #code}.
	 * @throws com.exedio.cope.MandatoryViolationException if code is null.
	 * @throws com.exedio.cope.StringLengthViolationException if code violates its length constraint.
	 * @throws com.exedio.cope.UniqueViolationException if code is not unique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	NameCollisionlooooooooooooooooooooooooooooooooooooooooongbItem(
				final java.lang.String code)
			throws
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.StringLengthViolationException,
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			NameCollisionlooooooooooooooooooooooooooooooooooooooooongbItem.code.map(code),
		});
	}/**

	 **
	 * Creates a new NameCollisionlooooooooooooooooooooooooooooooooooooooooongbItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private NameCollisionlooooooooooooooooooooooooooooooooooooooooongbItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@SuppressWarnings("unused") private NameCollisionlooooooooooooooooooooooooooooooooooooooooongbItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of {@link #code}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getCode()
	{
		return NameCollisionlooooooooooooooooooooooooooooooooooooooooongbItem.code.get(this);
	}/**

	 **
	 * Sets a new value for {@link #code}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setCode(final java.lang.String code)
			throws
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException,
				com.exedio.cope.StringLengthViolationException
	{
		NameCollisionlooooooooooooooooooooooooooooooooooooooooongbItem.code.set(this,code);
	}/**

	 **
	 * Finds a nameCollisionlooooooooooooooooooooooooooooooooooooooooongbItem by it's {@link #code}.
	 * @param code shall be equal to field {@link #code}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.for public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	static final NameCollisionlooooooooooooooooooooooooooooooooooooooooongbItem forCode(final java.lang.String code)
	{
		return NameCollisionlooooooooooooooooooooooooooooooooooooooooongbItem.code.searchUnique(NameCollisionlooooooooooooooooooooooooooooooooooooooooongbItem.class,code);
	}/**

	 **
	 * Finds a nameCollisionlooooooooooooooooooooooooooooooooooooooooongbItem by it's {@link #code}.
	 * @deprecated use for{@link #code} instead.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.findBy public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	@Deprecated
	static final NameCollisionlooooooooooooooooooooooooooooooooooooooooongbItem findByCode(final java.lang.String code)
	{
		return NameCollisionlooooooooooooooooooooooooooooooooooooooooongbItem.code.searchUnique(NameCollisionlooooooooooooooooooooooooooooooooooooooooongbItem.class,code);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for nameCollisionlooooooooooooooooooooooooooooooooooooooooongbItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	static final com.exedio.cope.Type<NameCollisionlooooooooooooooooooooooooooooooooooooooooongbItem> TYPE = newType(NameCollisionlooooooooooooooooooooooooooooooooooooooooongbItem.class)
;}
