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

package com.exedio.cope.testmodel;

import com.exedio.cope.Item;
import com.exedio.cope.ItemAttribute;

/**
 * Test for database name collisions
 * by using the same attributes names
 * in different persistent classes.
 * @persistent
 * @author Ralf Wiebicke
 */
public class CollisionItem1 extends Item
{

	public static final ItemAttribute collisionAttribute = itemAttribute(READ_ONLY_NOT_NULL_UNIQUE, EmptyItem.class); 

/**

	 **
	 * Creates a new CollisionItem1 with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param initialCollisionAttribute the initial value for attribute {@link #collisionAttribute}.
	 * @throws com.exedio.cope.NotNullViolationException if initialCollisionAttribute is null.
	 * @throws com.exedio.cope.UniqueViolationException if initialCollisionAttribute is not unique.
	 *
 */public CollisionItem1(
				final EmptyItem initialCollisionAttribute)
			throws
				com.exedio.cope.NotNullViolationException,
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.AttributeValue[]{
			new com.exedio.cope.AttributeValue(collisionAttribute,initialCollisionAttribute),
		});
		throwInitialNotNullViolationException();
		throwInitialUniqueViolationException();
	}/**

	 **
	 * Creates a new CollisionItem1 and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */private CollisionItem1(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 *
 */private CollisionItem1(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #collisionAttribute}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final EmptyItem getCollisionAttribute()
	{
		return (EmptyItem)getAttribute(CollisionItem1.collisionAttribute);
	}/**

	 **
	 * Finds a collisionItem1 by it's unique attributes.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param searchedCollisionAttribute shall be equal to attribute {@link #collisionAttribute}.
	 *
 */public static final CollisionItem1 findByCollisionAttribute(final EmptyItem searchedCollisionAttribute)
	{
		return (CollisionItem1)collisionAttribute.searchUnique(searchedCollisionAttribute);
	}/**

	 **
	 * The persistent type information for collisionItem1.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.Type TYPE = 
		new com.exedio.cope.Type(CollisionItem1.class)
;}
