
package com.exedio.cope.lib.collision;

import com.exedio.cope.lib.Item;
import com.exedio.cope.lib.ItemAttribute;
import com.exedio.cope.lib.ItemWithoutAttributes;

/**
 * Test for database name collisions
 * by using the same attributes names
 * in different persistent classes.
 * @persistent
 */
public class CollisionItem1 extends Item
{

	public static final ItemAttribute collisionAttribute = new ItemAttribute(READ_ONLY_NOT_NULL_UNIQUE, ItemWithoutAttributes.class); 

/**

	 **
	 * Constructs a new CollisionItem1 with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param initialCollisionAttribute the initial value for attribute {@link #collisionAttribute}.
	 * @throws com.exedio.cope.lib.NotNullViolationException if initialCollisionAttribute is not null.
	 * @throws com.exedio.cope.lib.UniqueViolationException if initialCollisionAttribute is not unique.
	 *
 */public CollisionItem1(
				final ItemWithoutAttributes initialCollisionAttribute)
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
 */private CollisionItem1(com.exedio.cope.lib.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #collisionAttribute}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final ItemWithoutAttributes getCollisionAttribute()
	{
		return (ItemWithoutAttributes)getAttribute(this.collisionAttribute);
	}/**

	 **
	 * Finds a collisionItem1 by it's unique attributes.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param searchedCollisionAttribute shall be equal to attribute {@link #collisionAttribute}.
	 *
 */public static final CollisionItem1 findByCollisionAttribute(final ItemWithoutAttributes searchedCollisionAttribute)
	{
		return (CollisionItem1)searchUnique(TYPE,equal(collisionAttribute,searchedCollisionAttribute));
	}/**

	 **
	 * The persistent type information for collisionItem1.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(
			CollisionItem1.class
		)
;}
