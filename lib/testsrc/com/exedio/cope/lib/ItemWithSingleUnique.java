
package com.exedio.cope.lib;

/**
 * An item having a unique attribute.
 * @persistent
 */
public class ItemWithSingleUnique extends Item
{
	/**
	 * An attribute that is unique.
	 */
	public static final StringAttribute uniqueString = new StringAttribute(UNIQUE);

/**

	 **
	 * Constructs a new ItemWithSingleUnique with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public ItemWithSingleUnique()
	{
		super(new com.exedio.cope.lib.AttributeValue[]{
		});
	}/**

	 **
	 * Creates an item and sets the given attributes initially.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */protected ItemWithSingleUnique(final com.exedio.cope.lib.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.lib.util.ReactivationConstructorDummy,int)
	 *
 */private ItemWithSingleUnique(com.exedio.cope.lib.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #uniqueString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final String getUniqueString()
	{
		return (String)getAttribute(ItemWithSingleUnique.uniqueString);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #uniqueString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setUniqueString(final String uniqueString)
			throws
				com.exedio.cope.lib.UniqueViolationException
	{
		try
		{
			setAttribute(ItemWithSingleUnique.uniqueString,uniqueString);
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
	 * Finds a itemWithSingleUnique by it's unique attributes.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param searchedUniqueString shall be equal to attribute {@link #uniqueString}.
	 *
 */public static final ItemWithSingleUnique findByUniqueString(final String searchedUniqueString)
	{
		return (ItemWithSingleUnique)uniqueString.searchUnique(searchedUniqueString);
	}/**

	 **
	 * The persistent type information for itemWithSingleUnique.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(ItemWithSingleUnique.class)
;}
