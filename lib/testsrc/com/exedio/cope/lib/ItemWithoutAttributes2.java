
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
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public ItemWithoutAttributes2()
	{
		super(new com.exedio.cope.lib.AttributeValue[]{
		});
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.lib.util.ReactivationConstructorDummy,int)
	 *
 */private ItemWithoutAttributes2(com.exedio.cope.lib.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * The persistent type information for itemWithoutAttributes2.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(ItemWithoutAttributes2.class)
;}
