
package com.exedio.cope.lib;

/**
 * An item having a unique read-only attribute.
 * @persistent
 */
public class ItemWithSingleUniqueReadOnly extends Item
{
	/**
	 * An attribute that is unique and read-only.
	 * @persistent
	 */
	public static final StringAttribute uniqueReadOnlyString = new StringAttribute(READ_ONLY_UNIQUE);

/**

	 **
	 * Constructs a new ItemWithSingleUniqueReadOnly with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param initialUniqueReadOnlyString the initial value for attribute {@link #uniqueReadOnlyString}.
	 * @throws com.exedio.cope.lib.UniqueViolationException if initialUniqueReadOnlyString is not unique.
	 *
 */public ItemWithSingleUniqueReadOnly(
				final String initialUniqueReadOnlyString)
			throws
				com.exedio.cope.lib.UniqueViolationException
	{
		super(new com.exedio.cope.lib.AttributeValue[]{
			new com.exedio.cope.lib.AttributeValue(uniqueReadOnlyString,initialUniqueReadOnlyString),
		});
		throwInitialUniqueViolationException();
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.lib.util.ReactivationConstructorDummy,int)
	 *
 */private ItemWithSingleUniqueReadOnly(com.exedio.cope.lib.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #uniqueReadOnlyString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final String getUniqueReadOnlyString()
	{
		return (String)getAttribute(this.uniqueReadOnlyString);
	}/**

	 **
	 * Finds a itemWithSingleUniqueReadOnly by it's unique attributes
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param searchedUniqueReadOnlyString shall be equal to attribute {@link #uniqueReadOnlyString}.
	 *
 */public static final ItemWithSingleUniqueReadOnly findByUniqueReadOnlyString(final String searchedUniqueReadOnlyString)
	{
		return (ItemWithSingleUniqueReadOnly)searchUnique(TYPE,equal(uniqueReadOnlyString,searchedUniqueReadOnlyString));
	}/**

	 **
	 * The persistent type information for itemWithSingleUniqueReadOnly.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(
			ItemWithSingleUniqueReadOnly.class,
			new com.exedio.cope.lib.UniqueConstraint[]{
			}
		)
;}
