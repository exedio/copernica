
package com.exedio.cope.lib.collision;

import com.exedio.cope.lib.EmptyItem;
import com.exedio.cope.lib.Item;
import com.exedio.cope.lib.ItemAttribute;

/**
 * @persistent
 */
public class CollisionItem2 extends Item
{

	public static final ItemAttribute collisionAttribute = new ItemAttribute(READ_ONLY_NOT_NULL_UNIQUE, EmptyItem.class); 

/**

	 **
	 * Constructs a new CollisionItem2 with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param initialCollisionAttribute the initial value for attribute {@link #collisionAttribute}.
	 * @throws com.exedio.cope.lib.NotNullViolationException if initialCollisionAttribute is not null.
	 * @throws com.exedio.cope.lib.UniqueViolationException if initialCollisionAttribute is not unique.
	 *
 */public CollisionItem2(
				final EmptyItem initialCollisionAttribute)
			throws
				com.exedio.cope.lib.NotNullViolationException,
				com.exedio.cope.lib.UniqueViolationException
	{
		super(new com.exedio.cope.lib.AttributeValue[]{
			new com.exedio.cope.lib.AttributeValue(collisionAttribute,initialCollisionAttribute),
		});
		throwInitialNotNullViolationException();
		throwInitialUniqueViolationException();
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
		return (CollisionItem2)TYPE.searchUnique(equal(collisionAttribute,searchedCollisionAttribute));
	}/**

	 **
	 * The persistent type information for collisionItem2.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(CollisionItem2.class)
;}
