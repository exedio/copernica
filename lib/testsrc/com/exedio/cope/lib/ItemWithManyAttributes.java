
package com.exedio.cope.lib;

import com.exedio.cope.lib.function.UppercaseFunction;

/**
 * An item having many attributes.
 * @persistent
 */
public class ItemWithManyAttributes extends Item
{
	/**
	 * A string attribute.
	 */
	public static final StringAttribute someString = new StringAttribute(DEFAULT);

	/**
	 * Test non-persistent static final attributes.
	 */
	public static final String someTransientString = "transientString";

	/**
	 * The code of the item in upper case.
	 */
	public static final UppercaseFunction someStringUpperCase = new UppercaseFunction(someString);

	/**
	 * A not-null string attribute.
	 */
	public static final StringAttribute someNotNullString = new StringAttribute(NOT_NULL);

	/**
	 * An integer attribute
	 */
	public static final IntegerAttribute someInteger = new IntegerAttribute(DEFAULT);

	/**
	 * A not-null integer attribute
	 */
	public static final IntegerAttribute someNotNullInteger = new IntegerAttribute(NOT_NULL);

	/**
	 * An integer attribute
	 */
	public static final LongAttribute someLong = new LongAttribute(DEFAULT);

	/**
	 * A not-null integer attribute
	 */
	public static final LongAttribute someNotNullLong = new LongAttribute(NOT_NULL);

	/**
	 * A double attribute
	 */
	public static final DoubleAttribute someDouble = new DoubleAttribute(DEFAULT);

	/**
	 * A not-null double attribute
	 */
	public static final DoubleAttribute someNotNullDouble = new DoubleAttribute(NOT_NULL);

	/**
	 * An boolean attribute
	 */
	public static final BooleanAttribute someBoolean = new BooleanAttribute(DEFAULT);

	/**
	 * A not-null boolean attribute
	 */
	public static final BooleanAttribute someNotNullBoolean = new BooleanAttribute(NOT_NULL);

	/**
	 * An attribute referencing another persistent item
	 */
	public static final ItemAttribute someItem = new ItemAttribute(DEFAULT, EmptyItem.class);

	/**
	 * An not-null attribute referencing another persistent item
	 */
	public static final ItemAttribute someNotNullItem = new ItemAttribute(NOT_NULL, EmptyItem.class);

	/**
	 * An enumeration attribute
	 */
	public static final EnumerationAttribute someEnumeration = new EnumerationAttribute(DEFAULT, SomeEnumeration.class);

	/**
	 * A not-null enumeration attribute
	 */
	public static final EnumerationAttribute someNotNullEnumeration = new EnumerationAttribute(NOT_NULL, SomeEnumeration.class);

	/**
	 * A media attribute.
	 */
	public static final MediaAttribute someMedia = new MediaAttribute(DEFAULT);
	
	public static final MediaAttributeVariant SomeVariant = new MediaAttributeVariant(someMedia);

	/**
	 * A qualified attribute.
	 * @qualifier EmptyItem
	 */
	public static final StringAttribute someQualifiedString = new StringAttribute(DEFAULT);

	/**
	 * A class representing the possible states of the persistent enumeration attribute {@link #someEnumeration}.
	 */
	public static final class SomeEnumeration extends EnumerationValue
	{
		public static final int enumValue1NUM = 100;
		public static final SomeEnumeration enumValue1 = new SomeEnumeration();

		public static final int enumValue2NUM = 200;
		public static final SomeEnumeration enumValue2 = new SomeEnumeration();

		public static final int enumValue3NUM = 300;
		public static final SomeEnumeration enumValue3 = new SomeEnumeration();
	}


/**

	 **
	 * Constructs a new ItemWithManyAttributes with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param initialSomeNotNullString the initial value for attribute {@link #someNotNullString}.
	 * @param initialSomeNotNullInteger the initial value for attribute {@link #someNotNullInteger}.
	 * @param initialSomeNotNullLong the initial value for attribute {@link #someNotNullLong}.
	 * @param initialSomeNotNullDouble the initial value for attribute {@link #someNotNullDouble}.
	 * @param initialSomeNotNullBoolean the initial value for attribute {@link #someNotNullBoolean}.
	 * @param initialSomeNotNullItem the initial value for attribute {@link #someNotNullItem}.
	 * @param initialSomeNotNullEnumeration the initial value for attribute {@link #someNotNullEnumeration}.
	 * @throws com.exedio.cope.lib.NotNullViolationException if initialSomeNotNullString, initialSomeNotNullItem, initialSomeNotNullEnumeration is not null.
	 *
 */public ItemWithManyAttributes(
				final String initialSomeNotNullString,
				final int initialSomeNotNullInteger,
				final long initialSomeNotNullLong,
				final double initialSomeNotNullDouble,
				final boolean initialSomeNotNullBoolean,
				final EmptyItem initialSomeNotNullItem,
				final SomeEnumeration initialSomeNotNullEnumeration)
			throws
				com.exedio.cope.lib.NotNullViolationException
	{
		super(new com.exedio.cope.lib.AttributeValue[]{
			new com.exedio.cope.lib.AttributeValue(someNotNullString,initialSomeNotNullString),
			new com.exedio.cope.lib.AttributeValue(someNotNullInteger,new Integer(initialSomeNotNullInteger)),
			new com.exedio.cope.lib.AttributeValue(someNotNullLong,new Long(initialSomeNotNullLong)),
			new com.exedio.cope.lib.AttributeValue(someNotNullDouble,new Double(initialSomeNotNullDouble)),
			new com.exedio.cope.lib.AttributeValue(someNotNullBoolean,(initialSomeNotNullBoolean?Boolean.TRUE:Boolean.FALSE)),
			new com.exedio.cope.lib.AttributeValue(someNotNullItem,initialSomeNotNullItem),
			new com.exedio.cope.lib.AttributeValue(someNotNullEnumeration,initialSomeNotNullEnumeration),
		});
		throwInitialNotNullViolationException();
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.lib.util.ReactivationConstructorDummy,int)
	 *
 */private ItemWithManyAttributes(com.exedio.cope.lib.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final String getSomeString()
	{
		return (String)getAttribute(this.someString);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeString(final String someString)
	{
		try
		{
			setAttribute(this.someString,someString);
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.UniqueViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someStringUpperCase}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final String getSomeStringUpperCase()
	{
		return (String)getAttribute(this.someStringUpperCase);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someNotNullString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final String getSomeNotNullString()
	{
		return (String)getAttribute(this.someNotNullString);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someNotNullString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeNotNullString(final String someNotNullString)
			throws
				com.exedio.cope.lib.NotNullViolationException
	{
		try
		{
			setAttribute(this.someNotNullString,someNotNullString);
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.UniqueViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someInteger}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final Integer getSomeInteger()
	{
		return (Integer)getAttribute(this.someInteger);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someInteger}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeInteger(final Integer someInteger)
	{
		try
		{
			setAttribute(this.someInteger,someInteger);
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.UniqueViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someNotNullInteger}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final int getSomeNotNullInteger()
	{
		return ((Integer)getAttribute(this.someNotNullInteger)).intValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someNotNullInteger}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeNotNullInteger(final int someNotNullInteger)
	{
		try
		{
			setAttribute(this.someNotNullInteger,new Integer(someNotNullInteger));
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.UniqueViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someLong}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final Long getSomeLong()
	{
		return (Long)getAttribute(this.someLong);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someLong}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeLong(final Long someLong)
	{
		try
		{
			setAttribute(this.someLong,someLong);
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.UniqueViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someNotNullLong}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final long getSomeNotNullLong()
	{
		return ((Long)getAttribute(this.someNotNullLong)).longValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someNotNullLong}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeNotNullLong(final long someNotNullLong)
	{
		try
		{
			setAttribute(this.someNotNullLong,new Long(someNotNullLong));
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.UniqueViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someDouble}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final Double getSomeDouble()
	{
		return (Double)getAttribute(this.someDouble);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someDouble}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeDouble(final Double someDouble)
	{
		try
		{
			setAttribute(this.someDouble,someDouble);
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.UniqueViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someNotNullDouble}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final double getSomeNotNullDouble()
	{
		return ((Double)getAttribute(this.someNotNullDouble)).doubleValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someNotNullDouble}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeNotNullDouble(final double someNotNullDouble)
	{
		try
		{
			setAttribute(this.someNotNullDouble,new Double(someNotNullDouble));
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.UniqueViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someBoolean}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final Boolean getSomeBoolean()
	{
		return (Boolean)getAttribute(this.someBoolean);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someBoolean}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeBoolean(final Boolean someBoolean)
	{
		try
		{
			setAttribute(this.someBoolean,someBoolean);
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.UniqueViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someNotNullBoolean}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final boolean getSomeNotNullBoolean()
	{
		return ((Boolean)getAttribute(this.someNotNullBoolean)).booleanValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someNotNullBoolean}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeNotNullBoolean(final boolean someNotNullBoolean)
	{
		try
		{
			setAttribute(this.someNotNullBoolean,(someNotNullBoolean?Boolean.TRUE:Boolean.FALSE));
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.UniqueViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someItem}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final EmptyItem getSomeItem()
	{
		return (EmptyItem)getAttribute(this.someItem);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someItem}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeItem(final EmptyItem someItem)
	{
		try
		{
			setAttribute(this.someItem,someItem);
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.UniqueViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someNotNullItem}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final EmptyItem getSomeNotNullItem()
	{
		return (EmptyItem)getAttribute(this.someNotNullItem);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someNotNullItem}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeNotNullItem(final EmptyItem someNotNullItem)
			throws
				com.exedio.cope.lib.NotNullViolationException
	{
		try
		{
			setAttribute(this.someNotNullItem,someNotNullItem);
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.UniqueViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someEnumeration}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final SomeEnumeration getSomeEnumeration()
	{
		return (SomeEnumeration)getAttribute(this.someEnumeration);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someEnumeration}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeEnumeration(final SomeEnumeration someEnumeration)
	{
		try
		{
			setAttribute(this.someEnumeration,someEnumeration);
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.UniqueViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someNotNullEnumeration}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final SomeEnumeration getSomeNotNullEnumeration()
	{
		return (SomeEnumeration)getAttribute(this.someNotNullEnumeration);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someNotNullEnumeration}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeNotNullEnumeration(final SomeEnumeration someNotNullEnumeration)
			throws
				com.exedio.cope.lib.NotNullViolationException
	{
		try
		{
			setAttribute(this.someNotNullEnumeration,someNotNullEnumeration);
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.UniqueViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
	}/**

	 **
	 * Returns a URL pointing to the data of the persistent attribute {@link #someMedia}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getSomeMediaURL()
	{
		return getMediaURL(this.someMedia);
	}/**

	 **
	 * Returns a URL pointing to the varied data of the persistent attribute {@link #someMedia}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getSomeMediaURLSomeVariant()
	{
		return getMediaURL(this.SomeVariant);
	}/**

	 **
	 * Returns the major mime type of the persistent media attribute {@link #someMedia}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getSomeMediaMimeMajor()
	{
		return getMediaMimeMajor(this.someMedia);
	}/**

	 **
	 * Returns the minor mime type of the persistent media attribute {@link #someMedia}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getSomeMediaMimeMinor()
	{
		return getMediaMimeMinor(this.someMedia);
	}/**

	 **
	 * Returns a stream for fetching the data of the persistent media attribute {@link #someMedia}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.io.InputStream getSomeMediaData()
	{
		return getMediaData(this.someMedia);
	}/**

	 **
	 * Provides data for the persistent media attribute {@link #someMedia}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeMediaData(final java.io.InputStream data,final java.lang.String mimeMajor,final java.lang.String mimeMinor)throws java.io.IOException
	{
		try
		{
			setMediaData(this.someMedia,data,mimeMajor,mimeMinor);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someQualifiedString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final String getSomeQualifiedString(final EmptyItem emptyItem)
	{
		return (String)getAttribute(this.someQualifiedString);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someQualifiedString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeQualifiedString(final EmptyItem emptyItem,final String someQualifiedString)
	{
		try
		{
			setAttribute(this.someQualifiedString,someQualifiedString);
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.UniqueViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
	}/**

	 **
	 * The persistent type information for itemWithManyAttributes.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(ItemWithManyAttributes.class)
;}
