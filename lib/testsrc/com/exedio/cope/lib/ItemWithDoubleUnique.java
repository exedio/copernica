
package com.exedio.cope.lib;

/**
 * An item having two attributes and a unique constraint over these attributes.
 * @persistent
 * @unique string integer
 */
public class ItemWithDoubleUnique extends Item
{

	public static final StringAttribute string = new StringAttribute(NOT_NULL);
	
	public static final IntegerAttribute integer = new IntegerAttribute(NOT_NULL);

/**

	 **
	 * Constructs a new ItemWithDoubleUnique with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param initialString the initial value for attribute {@link #string}.
	 * @param initialInteger the initial value for attribute {@link #integer}.
	 * @throws com.exedio.cope.lib.NotNullViolationException if initialString is not null.
	 * @throws com.exedio.cope.lib.UniqueViolationException if initialString, initialInteger is not unique.
	 *
 */public ItemWithDoubleUnique(
				final String initialString,
				final int initialInteger)
			throws
				com.exedio.cope.lib.NotNullViolationException,
				com.exedio.cope.lib.UniqueViolationException
	{
		super(new com.exedio.cope.lib.AttributeValue[]{
			new com.exedio.cope.lib.AttributeValue(string,initialString),
			new com.exedio.cope.lib.AttributeValue(integer,new Integer(initialInteger)),
		});
		throwInitialNotNullViolationException();
		throwInitialUniqueViolationException();
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.lib.util.ReactivationConstructorDummy,int)
	 *
 */private ItemWithDoubleUnique(com.exedio.cope.lib.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #string}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final String getString()
	{
		return (String)getAttribute(this.string);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #string}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setString(final String string)
			throws
				com.exedio.cope.lib.NotNullViolationException,
				com.exedio.cope.lib.UniqueViolationException
	{
		try
		{
			setAttribute(this.string,string);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #integer}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final int getInteger()
	{
		return ((Integer)getAttribute(this.integer)).intValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #integer}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setInteger(final int integer)
			throws
				com.exedio.cope.lib.UniqueViolationException
	{
		try
		{
			setAttribute(this.integer,new Integer(integer));
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
	}/**

	 **
	 * Finds a itemWithDoubleUnique by it's unique attributes.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param searchedString shall be equal to attribute {@link #string}.
	 * @param searchedInteger shall be equal to attribute {@link #integer}.
	 *
 */public static final ItemWithDoubleUnique findByStringAndInteger(final String searchedString,final Integer searchedInteger)
	{
		return (ItemWithDoubleUnique)searchUnique(TYPE,and(equal(string,searchedString),equal(integer,searchedInteger)));
	}/**

	 **
	 * The persistent type information for itemWithDoubleUnique.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(
			ItemWithDoubleUnique.class,
			new com.exedio.cope.lib.UniqueConstraint[]{
				new com.exedio.cope.lib.UniqueConstraint(new com.exedio.cope.lib.Attribute[]{string,integer,}),
			}
		)
;}
