/*
 * Copyright (C) 2004-2005  exedio GmbH (www.exedio.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.exedio.cope.testmodel;

import com.exedio.cope.lib.BooleanAttribute;
import com.exedio.cope.lib.DateAttribute;
import com.exedio.cope.lib.DoubleAttribute;
import com.exedio.cope.lib.EnumAttribute;
import com.exedio.cope.lib.EnumValue;
import com.exedio.cope.lib.IntegerAttribute;
import com.exedio.cope.lib.Item;
import com.exedio.cope.lib.ItemAttribute;
import com.exedio.cope.lib.LongAttribute;
import com.exedio.cope.lib.DataAttribute;
import com.exedio.cope.lib.DataAttributeVariant;
import com.exedio.cope.lib.StringAttribute;
import com.exedio.cope.lib.function.LengthFunction;
import com.exedio.cope.lib.function.UppercaseFunction;
import com.exedio.cope.lib.pattern.Qualifier;

/**
 * An item having many attributes.
 * @persistent
 * @author ralf.wiebicke@exedio.com
 */
public class AttributeItem extends Item
{
	/**
	 * A string attribute.
	 */
	public static final StringAttribute someString = stringAttribute(DEFAULT);

	/**
	 * Test non-persistent static final attributes.
	 */
	public static final String someTransientString = "transientString";

	/**
	 * The code of the item in upper case.
	 */
	public static final UppercaseFunction someStringUpperCase = new UppercaseFunction(someString);

	/**
	 * The length of code of the item.
	 */
	public static final LengthFunction someStringLength = new LengthFunction(someString);

	/**
	 * A not-null string attribute.
	 */
	public static final StringAttribute someNotNullString = stringAttribute(NOT_NULL);

	/**
	 * An integer attribute
	 */
	public static final IntegerAttribute someInteger = integerAttribute(DEFAULT);

	/**
	 * A not-null integer attribute
	 */
	public static final IntegerAttribute someNotNullInteger = integerAttribute(NOT_NULL);

	/**
	 * An integer attribute
	 */
	public static final LongAttribute someLong = longAttribute(DEFAULT);

	/**
	 * A not-null integer attribute
	 */
	public static final LongAttribute someNotNullLong = longAttribute(NOT_NULL);

	/**
	 * A double attribute
	 */
	public static final DoubleAttribute someDouble = doubleAttribute(DEFAULT);

	/**
	 * A not-null double attribute
	 */
	public static final DoubleAttribute someNotNullDouble = doubleAttribute(NOT_NULL);

	public static final DateAttribute someDate = dateAttribute(DEFAULT);

	public static final DateAttribute someLongDate = dateAttribute(DEFAULT, true);

	/**
	 * An boolean attribute
	 */
	public static final BooleanAttribute someBoolean = booleanAttribute(DEFAULT);

	/**
	 * A not-null boolean attribute
	 */
	public static final BooleanAttribute someNotNullBoolean = booleanAttribute(NOT_NULL);
	
	/**
	 * An attribute referencing another persistent item
	 */
	public static final ItemAttribute someItem = itemAttribute(DEFAULT, EmptyItem.class);

	/**
	 * An not-null attribute referencing another persistent item
	 */
	public static final ItemAttribute someNotNullItem = itemAttribute(NOT_NULL, EmptyItem.class);

	/**
	 * An enumeration attribute
	 */
	public static final EnumAttribute someEnumeration = enumAttribute(DEFAULT, SomeEnumeration.class);

	/**
	 * A not-null enumeration attribute
	 */
	public static final EnumAttribute someNotNullEnumeration = enumAttribute(NOT_NULL, SomeEnumeration.class);

	/**
	 * A media attribute.
	 */
	public static final DataAttribute someData = dataAttribute(DEFAULT);
	
	public static final DataAttributeVariant SomeVariant = dataAttributeVariant(someData);

	public static final Qualifier emptyItem = new Qualifier(AttributeEmptyItem.parentKey);

	/**
	 * A class representing the possible states of the persistent enumeration attribute {@link #someEnumeration}.
	 */
	public static final class SomeEnumeration extends EnumValue
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
	 * Creates a new AttributeItem with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param initialSomeNotNullString the initial value for attribute {@link #someNotNullString}.
	 * @param initialSomeNotNullInteger the initial value for attribute {@link #someNotNullInteger}.
	 * @param initialSomeNotNullLong the initial value for attribute {@link #someNotNullLong}.
	 * @param initialSomeNotNullDouble the initial value for attribute {@link #someNotNullDouble}.
	 * @param initialSomeNotNullBoolean the initial value for attribute {@link #someNotNullBoolean}.
	 * @param initialSomeNotNullItem the initial value for attribute {@link #someNotNullItem}.
	 * @param initialSomeNotNullEnumeration the initial value for attribute {@link #someNotNullEnumeration}.
	 * @throws com.exedio.cope.lib.NotNullViolationException if initialSomeNotNullString, initialSomeNotNullItem, initialSomeNotNullEnumeration is null.
	 *
 */public AttributeItem(
				final java.lang.String initialSomeNotNullString,
				final int initialSomeNotNullInteger,
				final long initialSomeNotNullLong,
				final double initialSomeNotNullDouble,
				final boolean initialSomeNotNullBoolean,
				final EmptyItem initialSomeNotNullItem,
				final SomeEnumeration initialSomeNotNullEnumeration)
			throws
				com.exedio.cope.lib.NotNullViolationException
	{
		this(new com.exedio.cope.lib.AttributeValue[]{
			new com.exedio.cope.lib.AttributeValue(someNotNullString,initialSomeNotNullString),
			new com.exedio.cope.lib.AttributeValue(someNotNullInteger,new java.lang.Integer(initialSomeNotNullInteger)),
			new com.exedio.cope.lib.AttributeValue(someNotNullLong,new java.lang.Long(initialSomeNotNullLong)),
			new com.exedio.cope.lib.AttributeValue(someNotNullDouble,new java.lang.Double(initialSomeNotNullDouble)),
			new com.exedio.cope.lib.AttributeValue(someNotNullBoolean,(initialSomeNotNullBoolean?java.lang.Boolean.TRUE:java.lang.Boolean.FALSE)),
			new com.exedio.cope.lib.AttributeValue(someNotNullItem,initialSomeNotNullItem),
			new com.exedio.cope.lib.AttributeValue(someNotNullEnumeration,initialSomeNotNullEnumeration),
		});
		throwInitialNotNullViolationException();
	}/**

	 **
	 * Creates a new AttributeItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.lib.Type#newItem Type.newItem}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */private AttributeItem(final com.exedio.cope.lib.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.lib.util.ReactivationConstructorDummy,int)
	 *
 */private AttributeItem(com.exedio.cope.lib.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getSomeString()
	{
		return (java.lang.String)getAttribute(AttributeItem.someString);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeString(final java.lang.String someString)
	{
		try
		{
			setAttribute(AttributeItem.someString,someString);
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
	 * Returns the value of the persistent attribute {@link #someStringUpperCase}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getSomeStringUpperCase()
	{
		return (java.lang.String)getAttribute(AttributeItem.someStringUpperCase);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someStringLength}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.Integer getSomeStringLength()
	{
		return (java.lang.Integer)getAttribute(AttributeItem.someStringLength);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someNotNullString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getSomeNotNullString()
	{
		return (java.lang.String)getAttribute(AttributeItem.someNotNullString);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someNotNullString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeNotNullString(final java.lang.String someNotNullString)
			throws
				com.exedio.cope.lib.NotNullViolationException
	{
		try
		{
			setAttribute(AttributeItem.someNotNullString,someNotNullString);
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
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
	 * Returns the value of the persistent attribute {@link #someInteger}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.Integer getSomeInteger()
	{
		return (java.lang.Integer)getAttribute(AttributeItem.someInteger);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someInteger}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeInteger(final java.lang.Integer someInteger)
	{
		try
		{
			setAttribute(AttributeItem.someInteger,someInteger);
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
	 * Returns the value of the persistent attribute {@link #someNotNullInteger}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final int getSomeNotNullInteger()
	{
		return ((java.lang.Integer)getAttribute(AttributeItem.someNotNullInteger)).intValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someNotNullInteger}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeNotNullInteger(final int someNotNullInteger)
	{
		try
		{
			setAttribute(AttributeItem.someNotNullInteger,new java.lang.Integer(someNotNullInteger));
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
	 * Returns the value of the persistent attribute {@link #someLong}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.Long getSomeLong()
	{
		return (java.lang.Long)getAttribute(AttributeItem.someLong);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someLong}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeLong(final java.lang.Long someLong)
	{
		try
		{
			setAttribute(AttributeItem.someLong,someLong);
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
	 * Returns the value of the persistent attribute {@link #someNotNullLong}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final long getSomeNotNullLong()
	{
		return ((java.lang.Long)getAttribute(AttributeItem.someNotNullLong)).longValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someNotNullLong}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeNotNullLong(final long someNotNullLong)
	{
		try
		{
			setAttribute(AttributeItem.someNotNullLong,new java.lang.Long(someNotNullLong));
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
	 * Returns the value of the persistent attribute {@link #someDouble}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.Double getSomeDouble()
	{
		return (java.lang.Double)getAttribute(AttributeItem.someDouble);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someDouble}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeDouble(final java.lang.Double someDouble)
	{
		try
		{
			setAttribute(AttributeItem.someDouble,someDouble);
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
	 * Returns the value of the persistent attribute {@link #someNotNullDouble}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final double getSomeNotNullDouble()
	{
		return ((java.lang.Double)getAttribute(AttributeItem.someNotNullDouble)).doubleValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someNotNullDouble}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeNotNullDouble(final double someNotNullDouble)
	{
		try
		{
			setAttribute(AttributeItem.someNotNullDouble,new java.lang.Double(someNotNullDouble));
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
	 * Returns the value of the persistent attribute {@link #someDate}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.util.Date getSomeDate()
	{
		return (java.util.Date)getAttribute(AttributeItem.someDate);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someDate}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeDate(final java.util.Date someDate)
	{
		try
		{
			setAttribute(AttributeItem.someDate,someDate);
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
	 * Sets the current date for the date attribute {@link #someDate}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void touchSomeDate()
	{
		try
		{
			touchAttribute(AttributeItem.someDate);
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
	 * Returns the value of the persistent attribute {@link #someLongDate}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.util.Date getSomeLongDate()
	{
		return (java.util.Date)getAttribute(AttributeItem.someLongDate);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someLongDate}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeLongDate(final java.util.Date someLongDate)
	{
		try
		{
			setAttribute(AttributeItem.someLongDate,someLongDate);
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
	 * Sets the current date for the date attribute {@link #someLongDate}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void touchSomeLongDate()
	{
		try
		{
			touchAttribute(AttributeItem.someLongDate);
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
	 * Returns the value of the persistent attribute {@link #someBoolean}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.Boolean getSomeBoolean()
	{
		return (java.lang.Boolean)getAttribute(AttributeItem.someBoolean);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someBoolean}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeBoolean(final java.lang.Boolean someBoolean)
	{
		try
		{
			setAttribute(AttributeItem.someBoolean,someBoolean);
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
	 * Returns the value of the persistent attribute {@link #someNotNullBoolean}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final boolean getSomeNotNullBoolean()
	{
		return ((java.lang.Boolean)getAttribute(AttributeItem.someNotNullBoolean)).booleanValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someNotNullBoolean}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeNotNullBoolean(final boolean someNotNullBoolean)
	{
		try
		{
			setAttribute(AttributeItem.someNotNullBoolean,(someNotNullBoolean?java.lang.Boolean.TRUE:java.lang.Boolean.FALSE));
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
	 * Returns the value of the persistent attribute {@link #someItem}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final EmptyItem getSomeItem()
	{
		return (EmptyItem)getAttribute(AttributeItem.someItem);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someItem}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeItem(final EmptyItem someItem)
	{
		try
		{
			setAttribute(AttributeItem.someItem,someItem);
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
	 * Returns the value of the persistent attribute {@link #someNotNullItem}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final EmptyItem getSomeNotNullItem()
	{
		return (EmptyItem)getAttribute(AttributeItem.someNotNullItem);
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
			setAttribute(AttributeItem.someNotNullItem,someNotNullItem);
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
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
	 * Returns the value of the persistent attribute {@link #someEnumeration}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final SomeEnumeration getSomeEnumeration()
	{
		return (SomeEnumeration)getAttribute(AttributeItem.someEnumeration);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someEnumeration}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeEnumeration(final SomeEnumeration someEnumeration)
	{
		try
		{
			setAttribute(AttributeItem.someEnumeration,someEnumeration);
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
	 * Returns the value of the persistent attribute {@link #someNotNullEnumeration}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final SomeEnumeration getSomeNotNullEnumeration()
	{
		return (SomeEnumeration)getAttribute(AttributeItem.someNotNullEnumeration);
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
			setAttribute(AttributeItem.someNotNullEnumeration,someNotNullEnumeration);
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
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
	 * Returns a URL the data of the data attribute {@link #someData} is available under.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getSomeDataURL()
	{
		return getURL(AttributeItem.someData);
	}/**

	 **
	 * Returns a URL the data of the {@link #SomeVariant SomeVariant} variant of the data attribute {@link #someData} is available under.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getSomeDataURLSomeVariant()
	{
		return getURL(AttributeItem.SomeVariant);
	}/**

	 **
	 * Returns the major mime type of the data attribute {@link #someData}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getSomeDataMimeMajor()
	{
		return getMimeMajor(AttributeItem.someData);
	}/**

	 **
	 * Returns the minor mime type of the data attribute {@link #someData}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getSomeDataMimeMinor()
	{
		return getMimeMinor(AttributeItem.someData);
	}/**

	 **
	 * Returns the data of the data attribute {@link #someData}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.io.InputStream getSomeDataData()
	{
		return getData(AttributeItem.someData);
	}/**

	 **
	 * Sets the new data for the data attribute {@link #someData}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @throws java.io.IOException if accessing <code>data</code> throws an IOException.
	 *
 */public final void setSomeDataData(final java.io.InputStream data,final java.lang.String mimeMajor,final java.lang.String mimeMinor)throws java.io.IOException
	{
		try
		{
			setData(AttributeItem.someData,data,mimeMajor,mimeMinor);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the qualifier.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final AttributeEmptyItem getEmptyItem(final EmptyItem key)
	{
		return (AttributeEmptyItem)emptyItem.getQualifier(new Object[]{this,key});
	}/**

	 **
	 * Returns the qualifier.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getSomeQualifiedString(final EmptyItem key)
	{
		return (java.lang.String)emptyItem.getQualified(new Object[]{this,key},AttributeEmptyItem.someQualifiedString);
	}/**

	 **
	 * Sets the qualifier.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeQualifiedString(final EmptyItem key,final java.lang.String someQualifiedString)
			throws
				com.exedio.cope.lib.NotNullViolationException,
				com.exedio.cope.lib.LengthViolationException,
				com.exedio.cope.lib.ReadOnlyViolationException,
				java.lang.ClassCastException
	{
		emptyItem.setQualified(new Object[]{this,key},AttributeEmptyItem.someQualifiedString,someQualifiedString);
	}/**

	 **
	 * The persistent type information for attributeItem.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(AttributeItem.class)
;}
