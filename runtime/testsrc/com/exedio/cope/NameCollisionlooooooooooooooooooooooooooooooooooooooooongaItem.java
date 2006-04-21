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
 * The name of this item is deliberately chosen to collide with
 * {@link NameCollisionlooooooooooooooooooooooooooooooooooooooooongbItem},
 * after trimming of database names
 * 
 * @author Ralf Wiebicke
 */
public class NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem extends Item
{

	public static final StringAttribute code = new StringAttribute(UNIQUE);

	/**
	 * The name of this attribute is deliberately chosen to collide with
	 * {@link #collisionloooooooooooooooooooooooooooooooooooooooooooooooongbNumber},
	 * after trimming of database names
	 */
	public static final IntegerAttribute collisionloooooooooooooooooooooooooooooooooooooooooooooooongaNumber =
		new IntegerAttribute(OPTIONAL);
	
	public static final IntegerAttribute collisionloooooooooooooooooooooooooooooooooooooooooooooooongbNumber =
		new IntegerAttribute(OPTIONAL);
	
/**

	 **
	 * Creates a new NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem with all the attributes initially needed.
	 * @param code the initial value for attribute {@link #code}.
	 * @throws com.exedio.cope.LengthViolationException if code violates its length constraint.
	 * @throws com.exedio.cope.MandatoryViolationException if code is null.
	 * @throws com.exedio.cope.UniqueViolationException if code is not unique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of attributes.
	 */
	public NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem(
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
	 * Creates a new NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem(final com.exedio.cope.SetValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #code}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.String getCode()
	{
		return NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem.code.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #code}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setCode(final java.lang.String code)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem.code.set(this,code);
	}/**

	 **
	 * Finds a nameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem by it's unique attributes.
	 * @param code shall be equal to attribute {@link #code}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public static final NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem findByCode(final java.lang.String code)
	{
		return (NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem)NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem.code.searchUnique(code);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #collisionloooooooooooooooooooooooooooooooooooooooooooooooongaNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.Integer getCollisionloooooooooooooooooooooooooooooooooooooooooooooooongaNumber()
	{
		return NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem.collisionloooooooooooooooooooooooooooooooooooooooooooooooongaNumber.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #collisionloooooooooooooooooooooooooooooooooooooooooooooooongaNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setCollisionloooooooooooooooooooooooooooooooooooooooooooooooongaNumber(final java.lang.Integer collisionloooooooooooooooooooooooooooooooooooooooooooooooongaNumber)
	{
		NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem.collisionloooooooooooooooooooooooooooooooooooooooooooooooongaNumber.set(this,collisionloooooooooooooooooooooooooooooooooooooooooooooooongaNumber);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #collisionloooooooooooooooooooooooooooooooooooooooooooooooongbNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.Integer getCollisionloooooooooooooooooooooooooooooooooooooooooooooooongbNumber()
	{
		return NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem.collisionloooooooooooooooooooooooooooooooooooooooooooooooongbNumber.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #collisionloooooooooooooooooooooooooooooooooooooooooooooooongbNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setCollisionloooooooooooooooooooooooooooooooooooooooooooooooongbNumber(final java.lang.Integer collisionloooooooooooooooooooooooooooooooooooooooooooooooongbNumber)
	{
		NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem.collisionloooooooooooooooooooooooooooooooooooooooooooooooongbNumber.set(this,collisionloooooooooooooooooooooooooooooooooooooooooooooooongbNumber);
	}/**

	 **
	 * The persistent type information for nameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem> TYPE =
		new com.exedio.cope.Type<NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem>(NameCollisionlooooooooooooooooooooooooooooooooooooooooongaItem.class)
;}
