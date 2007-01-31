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
 * The name of this item is deliberately chosen to collide with
 * {@link NameCollisionlooooooooooooooooooooooooooooooooooooooooongbItem},
 * after trimming of database names
 *
 * @author Ralf Wiebicke
 */
class NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem extends Item
{

	static final StringField code = new StringField().unique();

	/**
	 * The name of this attribute is deliberately chosen to collide with
	 * {@link #collisionloooooooooooooooooooooooooooooooooooooooooooooooongbNumber},
	 * after trimming of database names
	 */
	static final IntegerField collisionloooooooooooooooooooooooooooooooooooooooooooooooongaNumber =
		new IntegerField(OPTIONAL);
	
	static final IntegerField collisionloooooooooooooooooooooooooooooooooooooooooooooooongbNumber =
		new IntegerField(OPTIONAL);
	
/**

	 **
	 * Creates a new NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem with all the fields initially needed.
	 * @param code the initial value for field {@link #code}.
	 * @throws com.exedio.cope.LengthViolationException if code violates its length constraint.
	 * @throws com.exedio.cope.MandatoryViolationException if code is null.
	 * @throws com.exedio.cope.UniqueViolationException if code is not unique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem(
				final java.lang.String code)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem.code.map(code),
		});
	}/**

	 **
	 * Creates a new NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent field {@link #code}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final java.lang.String getCode()
	{
		return NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem.code.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #code}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setCode(final java.lang.String code)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem.code.set(this,code);
	}/**

	 **
	 * Finds a nameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem by it's unique fields.
	 * @param code shall be equal to field {@link #code}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	static final NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem findByCode(final java.lang.String code)
	{
		return (NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem)NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem.code.searchUnique(code);
	}/**

	 **
	 * Returns the value of the persistent field {@link #collisionloooooooooooooooooooooooooooooooooooooooooooooooongaNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final java.lang.Integer getCollisionloooooooooooooooooooooooooooooooooooooooooooooooongaNumber()
	{
		return NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem.collisionloooooooooooooooooooooooooooooooooooooooooooooooongaNumber.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #collisionloooooooooooooooooooooooooooooooooooooooooooooooongaNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setCollisionloooooooooooooooooooooooooooooooooooooooooooooooongaNumber(final java.lang.Integer collisionloooooooooooooooooooooooooooooooooooooooooooooooongaNumber)
	{
		NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem.collisionloooooooooooooooooooooooooooooooooooooooooooooooongaNumber.set(this,collisionloooooooooooooooooooooooooooooooooooooooooooooooongaNumber);
	}/**

	 **
	 * Returns the value of the persistent field {@link #collisionloooooooooooooooooooooooooooooooooooooooooooooooongbNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final java.lang.Integer getCollisionloooooooooooooooooooooooooooooooooooooooooooooooongbNumber()
	{
		return NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem.collisionloooooooooooooooooooooooooooooooooooooooooooooooongbNumber.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #collisionloooooooooooooooooooooooooooooooooooooooooooooooongbNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setCollisionloooooooooooooooooooooooooooooooooooooooooooooooongbNumber(final java.lang.Integer collisionloooooooooooooooooooooooooooooooooooooooooooooooongbNumber)
	{
		NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem.collisionloooooooooooooooooooooooooooooooooooooooooooooooongbNumber.set(this,collisionloooooooooooooooooooooooooooooooooooooooooooooooongbNumber);
	}/**

	 **
	 * The persistent type information for nameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem> TYPE = newType(NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem.class)
;}
