
package com.exedio.cope.testmodel;

import com.exedio.cope.lib.Item;
import com.exedio.cope.lib.ItemAttribute;
import com.exedio.cope.lib.StringAttribute;
import com.exedio.cope.lib.UniqueConstraint;


/**
 * @persistent
 */
public class AttributeEmptyItem extends Item
{
	public static final ItemAttribute parent = itemAttribute(DEFAULT, AttributeItem.class);

	public static final ItemAttribute key = itemAttribute(DEFAULT, EmptyItem.class);
	
	public static final UniqueConstraint parentKey = uniqueConstraint(parent, key);

	public static final StringAttribute someQualifiedString = stringAttribute(DEFAULT);

/**

	 **
	 * Creates a new AttributeEmptyItem with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public AttributeEmptyItem()
	{
		this(new com.exedio.cope.lib.AttributeValue[]{
		});
	}/**

	 **
	 * Creates a new AttributeEmptyItem and sets the given attributes initially.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */private AttributeEmptyItem(final com.exedio.cope.lib.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.lib.util.ReactivationConstructorDummy,int)
	 *
 */private AttributeEmptyItem(com.exedio.cope.lib.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #parent}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final AttributeItem getParent()
	{
		return (AttributeItem)getAttribute(AttributeEmptyItem.parent);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #parent}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setParent(final AttributeItem parent)
			throws
				com.exedio.cope.lib.UniqueViolationException
	{
		try
		{
			setAttribute(AttributeEmptyItem.parent,parent);
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #key}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final EmptyItem getKey()
	{
		return (EmptyItem)getAttribute(AttributeEmptyItem.key);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #key}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setKey(final EmptyItem key)
			throws
				com.exedio.cope.lib.UniqueViolationException
	{
		try
		{
			setAttribute(AttributeEmptyItem.key,key);
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someQualifiedString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getSomeQualifiedString()
	{
		return (java.lang.String)getAttribute(AttributeEmptyItem.someQualifiedString);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someQualifiedString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeQualifiedString(final java.lang.String someQualifiedString)
	{
		try
		{
			setAttribute(AttributeEmptyItem.someQualifiedString,someQualifiedString);
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.lib.UniqueViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Finds a attributeEmptyItem by it's unique attributes.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param searchedParent shall be equal to attribute {@link #parent}.
	 * @param searchedKey shall be equal to attribute {@link #key}.
	 *
 */public static final AttributeEmptyItem findByParentKey(final AttributeItem searchedParent,final EmptyItem searchedKey)
	{
		return (AttributeEmptyItem)parentKey.searchUnique(new Object[]{searchedParent,searchedKey});
	}/**

	 **
	 * The persistent type information for attributeEmptyItem.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(AttributeEmptyItem.class)
;}
