
package com.exedio.cope.lib;

/**
 * Another item not having any attribute.
 * @persistent
 */
public class ItemWithoutAttributes2 extends Item
{
/**

	 **
	 * Constructs a new ItemWithoutAttributes2 with all the attributes initially needed.
	 * @generated
	 *
 */public ItemWithoutAttributes2()
	{
		super(TYPE, new com.exedio.cope.lib.AttributeValue[]{
		});
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see Item#Item(com.exedio.cope.lib.util.ReactivationConstructorDummy,int)
	 * @generated
	 *
 */private ItemWithoutAttributes2(com.exedio.cope.lib.util.ReactivationConstructorDummy d, final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * The persistent type information for itemWithoutAttributes2.
	 * @generated
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(
			ItemWithoutAttributes2.class,
			null,
			null
		)
;}
