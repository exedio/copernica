
package com.exedio.cope.testmodel;

import com.exedio.cope.lib.Item;

/**
 * An item not having any attribute.
 * @persistent
 */
public class EmptyItem extends Item
{
/**

	 **
	 * Creates a new EmptyItem with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public EmptyItem()
	{
		this(new com.exedio.cope.lib.AttributeValue[]{
		});
	}/**

	 **
	 * Creates a new EmptyItem and sets the given attributes initially.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */private EmptyItem(final com.exedio.cope.lib.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.lib.util.ReactivationConstructorDummy,int)
	 *
 */private EmptyItem(com.exedio.cope.lib.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * The persistent type information for emptyItem.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(EmptyItem.class)
;}
