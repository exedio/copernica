
package com.exedio.cope.lib;

import com.exedio.cope.lib.Attribute;
import com.exedio.cope.lib.IntegerAttribute;
import com.exedio.cope.lib.Item;
import com.exedio.cope.lib.ItemAttribute;
import com.exedio.cope.lib.MediaAttribute;
import com.exedio.cope.lib.StringAttribute;

/**
 * An item having a unique not-null attribute.
 * @persistent
 */
public class ItemWithSingleUniqueNotNull extends Item
{
	/**
	 * An attribute that is unique and read-only.
	 * @persistent
	 * @unique
	 * @not-null
	 */
	public static final StringAttribute uniqueNotNullString = new StringAttribute();

/**

	 **
	 * Constructs a new ItemWithSingleUniqueNotNull with all the attributes initially needed.
	 * @param initialUniqueNotNullString the initial value for attribute {@link #uniqueNotNullString}.
	 * @throws com.exedio.cope.lib.NotNullViolationException if initialUniqueNotNullString is not null.
	 * @throws com.exedio.cope.lib.UniqueViolationException if initialUniqueNotNullString is not unique.
	 * @generated
	 *
 */public ItemWithSingleUniqueNotNull(
				final String initialUniqueNotNullString)
			throws
				com.exedio.cope.lib.NotNullViolationException,
				com.exedio.cope.lib.UniqueViolationException
	{
		super(TYPE, new com.exedio.cope.lib.AttributeValue[]{
			new com.exedio.cope.lib.AttributeValue(uniqueNotNullString,initialUniqueNotNullString),
		});
		throwInitialNotNullViolationException();
		throwInitialUniqueViolationException();
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see Item#Item(Type, int)
	 * @generated
	 *
 */private ItemWithSingleUniqueNotNull(com.exedio.cope.lib.util.ReactivationConstructorDummy d, final int pk)
	{
		super(TYPE, pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #uniqueNotNullString}.
	 * @generated
	 *
 */public final String getUniqueNotNullString()
	{
		return (String)getAttribute(this.uniqueNotNullString);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #uniqueNotNullString}.
	 * @generated
	 *
 */public final void setUniqueNotNullString(final String uniqueNotNullString)
			throws
				com.exedio.cope.lib.NotNullViolationException,
				com.exedio.cope.lib.UniqueViolationException
	{
		try
		{
			setAttribute(this.uniqueNotNullString,uniqueNotNullString);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
	}/**

	 **
	 * Finds a itemWithSingleUniqueNotNull by it's unique attributes
	 * @param searchedUniqueNotNullString shall be equal to attribute {@link #uniqueNotNullString}.
	 * @generated
	 *
 */public static final ItemWithSingleUniqueNotNull findByUniqueNotNullString(final String searchedUniqueNotNullString)
	{
		return (ItemWithSingleUniqueNotNull)searchUnique(TYPE,equal(uniqueNotNullString,searchedUniqueNotNullString));
	}/**

	 **
	 * The persistent type information for itemWithSingleUniqueNotNull.
	 * @generated
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(
			ItemWithSingleUniqueNotNull.class,
			new com.exedio.cope.lib.Attribute[]{
				uniqueNotNullString.initialize("uniqueNotNullString",false,true),
			},
			new com.exedio.cope.lib.UniqueConstraint[]{
				new com.exedio.cope.lib.UniqueConstraint(uniqueNotNullString),
			}
		)
;}
