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

import com.exedio.cope.lib.Item;
import com.exedio.cope.lib.ItemAttribute;

/**
 * @persistent
 * @author Ralf Wiebicke
 */
public class CollisionItem2 extends Item
{

	public static final ItemAttribute collisionAttribute = itemAttribute(READ_ONLY_NOT_NULL_UNIQUE, EmptyItem.class); 

/**

	 **
	 * Creates a new CollisionItem2 with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param initialCollisionAttribute the initial value for attribute {@link #collisionAttribute}.
	 * @throws com.exedio.cope.lib.NotNullViolationException if initialCollisionAttribute is null.
	 * @throws com.exedio.cope.lib.UniqueViolationException if initialCollisionAttribute is not unique.
	 *
 */public CollisionItem2(
				final EmptyItem initialCollisionAttribute)
			throws
				com.exedio.cope.lib.NotNullViolationException,
				com.exedio.cope.lib.UniqueViolationException
	{
		this(new com.exedio.cope.lib.AttributeValue[]{
			new com.exedio.cope.lib.AttributeValue(collisionAttribute,initialCollisionAttribute),
		});
		throwInitialNotNullViolationException();
		throwInitialUniqueViolationException();
	}/**

	 **
	 * Creates a new CollisionItem2 and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.lib.Type#newItem Type.newItem}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */private CollisionItem2(final com.exedio.cope.lib.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.lib.util.ReactivationConstructorDummy,int)
	 *
 */private CollisionItem2(com.exedio.cope.lib.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #collisionAttribute}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final EmptyItem getCollisionAttribute()
	{
		return (EmptyItem)getAttribute(CollisionItem2.collisionAttribute);
	}/**

	 **
	 * Finds a collisionItem2 by it's unique attributes.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param searchedCollisionAttribute shall be equal to attribute {@link #collisionAttribute}.
	 *
 */public static final CollisionItem2 findByCollisionAttribute(final EmptyItem searchedCollisionAttribute)
	{
		return (CollisionItem2)collisionAttribute.searchUnique(searchedCollisionAttribute);
	}/**

	 **
	 * The persistent type information for collisionItem2.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(CollisionItem2.class)
;}
