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

package com.exedio.cope.testmodel;

import com.exedio.cope.Item;
import com.exedio.cope.ItemField;

/**
 * Test for database name collisions
 * by using the same attributes names
 * in different persistent classes.
 * @author Ralf Wiebicke
 */
public class CollisionItem1 extends Item
{

	public static final ItemField<EmptyItem> collisionAttribute = newItemField(EmptyItem.class).toFinal().unique();

/**

	 **
	 * Creates a new CollisionItem1 with all the fields initially needed.
	 * @param collisionAttribute the initial value for field {@link #collisionAttribute}.
	 * @throws com.exedio.cope.MandatoryViolationException if collisionAttribute is null.
	 * @throws com.exedio.cope.UniqueViolationException if collisionAttribute is not unique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	public CollisionItem1(
				final EmptyItem collisionAttribute)
			throws
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			CollisionItem1.collisionAttribute.map(collisionAttribute),
		});
	}/**

	 **
	 * Creates a new CollisionItem1 and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private CollisionItem1(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private CollisionItem1(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent field {@link #collisionAttribute}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	public final EmptyItem getCollisionAttribute()
	{
		return CollisionItem1.collisionAttribute.get(this);
	}/**

	 **
	 * Finds a collisionItem1 by it's unique fields.
	 * @param collisionAttribute shall be equal to field {@link #collisionAttribute}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public static final CollisionItem1 findByCollisionAttribute(final EmptyItem collisionAttribute)
	{
		return (CollisionItem1)CollisionItem1.collisionAttribute.searchUnique(collisionAttribute);
	}/**

	 **
	 * The persistent type information for collisionItem1.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<CollisionItem1> TYPE = newType(CollisionItem1.class)
;}
