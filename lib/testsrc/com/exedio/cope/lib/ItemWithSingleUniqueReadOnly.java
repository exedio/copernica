
package com.exedio.cope.lib;

import com.exedio.cope.lib.Attribute;
import com.exedio.cope.lib.IntegerAttribute;
import com.exedio.cope.lib.Item;
import com.exedio.cope.lib.ItemAttribute;
import com.exedio.cope.lib.MediaAttribute;
import com.exedio.cope.lib.StringAttribute;

/**
 * An item having a unique read-only attribute.
 * @persistent
 */
public class ItemWithSingleUniqueReadOnly extends Item
{
	/**
	 * An attribute that is unique and read-only.
	 * @persistent
	 * @unique
	 * @read-only
	 */
	public static final StringAttribute uniqueReadOnlyString = new StringAttribute();

/**

	 **
	 * Constructs a new ItemWithSingleUniqueReadOnly with all the attributes initially needed.
	 * @param initialUniqueReadOnlyString the initial value for attribute {@link #uniqueReadOnlyString}.
	 * @throws com.exedio.cope.lib.UniqueViolationException if initialUniqueReadOnlyString is not unique.
	 * @generated
	 *
 */public ItemWithSingleUniqueReadOnly(
				final String initialUniqueReadOnlyString)
			throws
				com.exedio.cope.lib.UniqueViolationException
	{
		super(TYPE, new com.exedio.cope.lib.AttributeValue[]{
			new com.exedio.cope.lib.AttributeValue(uniqueReadOnlyString,initialUniqueReadOnlyString),
		});
		throwInitialUniqueViolationException();
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #uniqueReadOnlyString}.
	 * @generated
	 *
 */public final String getUniqueReadOnlyString()
	{
		return (String)getAttribute(this.uniqueReadOnlyString);
	}/**

	 **
	 * Finds a itemWithSingleUniqueReadOnly by it's unique attributes
	 * @param searchedUniqueReadOnlyString shall be equal to attribute {@link #uniqueReadOnlyString}.
	 * @generated
	 *
 */public static final ItemWithSingleUniqueReadOnly findByUniqueReadOnlyString(final String searchedUniqueReadOnlyString)
	{
		return (ItemWithSingleUniqueReadOnly)searchUnique(TYPE,equal(uniqueReadOnlyString,searchedUniqueReadOnlyString));
	}/**

	 **
	 * The persistent type information for itemWithSingleUniqueReadOnly.
	 * @generated
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(
			ItemWithSingleUniqueReadOnly.class,
			new com.exedio.cope.lib.Attribute[]{
				uniqueReadOnlyString,
			},
			new com.exedio.cope.lib.UniqueConstraint[]{
				new com.exedio.cope.lib.UniqueConstraint(uniqueReadOnlyString),
			},
			new Runnable()
			{
				public void run()
				{
					uniqueReadOnlyString.initialize("uniqueReadOnlyString",true,false);
				}
			}
		)
;}
