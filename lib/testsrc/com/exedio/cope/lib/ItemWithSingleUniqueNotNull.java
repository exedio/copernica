
package com.exedio.cope.lib;

/**
 * An item having a unique not-null attribute.
 * @persistent
 */
public class ItemWithSingleUniqueNotNull extends Item
{
	/**
	 * An attribute that is unique and read-only.
	 */
	public static final StringAttribute uniqueNotNullString = new StringAttribute(NOT_NULL_UNIQUE);

/**

	 **
	 * Constructs a new ItemWithSingleUniqueNotNull with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param initialUniqueNotNullString the initial value for attribute {@link #uniqueNotNullString}.
	 * @throws com.exedio.cope.lib.NotNullViolationException if initialUniqueNotNullString is not null.
	 * @throws com.exedio.cope.lib.UniqueViolationException if initialUniqueNotNullString is not unique.
	 *
 */public ItemWithSingleUniqueNotNull(
				final java.lang.String initialUniqueNotNullString)
			throws
				com.exedio.cope.lib.NotNullViolationException,
				com.exedio.cope.lib.UniqueViolationException
	{
		super(new com.exedio.cope.lib.AttributeValue[]{
			new com.exedio.cope.lib.AttributeValue(uniqueNotNullString,initialUniqueNotNullString),
		});
		throwInitialNotNullViolationException();
		throwInitialUniqueViolationException();
	}/**

	 **
	 * Creates an item and sets the given attributes initially.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */protected ItemWithSingleUniqueNotNull(final com.exedio.cope.lib.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.lib.util.ReactivationConstructorDummy,int)
	 *
 */private ItemWithSingleUniqueNotNull(com.exedio.cope.lib.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #uniqueNotNullString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getUniqueNotNullString()
	{
		return (java.lang.String)getAttribute(ItemWithSingleUniqueNotNull.uniqueNotNullString);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #uniqueNotNullString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setUniqueNotNullString(final java.lang.String uniqueNotNullString)
			throws
				com.exedio.cope.lib.NotNullViolationException,
				com.exedio.cope.lib.UniqueViolationException
	{
		try
		{
			setAttribute(ItemWithSingleUniqueNotNull.uniqueNotNullString,uniqueNotNullString);
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Finds a itemWithSingleUniqueNotNull by it's unique attributes.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param searchedUniqueNotNullString shall be equal to attribute {@link #uniqueNotNullString}.
	 *
 */public static final ItemWithSingleUniqueNotNull findByUniqueNotNullString(final java.lang.String searchedUniqueNotNullString)
	{
		return (ItemWithSingleUniqueNotNull)uniqueNotNullString.searchUnique(searchedUniqueNotNullString);
	}/**

	 **
	 * The persistent type information for itemWithSingleUniqueNotNull.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(ItemWithSingleUniqueNotNull.class)
;}
