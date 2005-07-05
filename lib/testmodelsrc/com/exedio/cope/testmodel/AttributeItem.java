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

import com.exedio.cope.BooleanAttribute;
import com.exedio.cope.DateAttribute;
import com.exedio.cope.DoubleAttribute;
import com.exedio.cope.EnumAttribute;
import com.exedio.cope.EnumValue;
import com.exedio.cope.IntegerAttribute;
import com.exedio.cope.Item;
import com.exedio.cope.ItemAttribute;
import com.exedio.cope.LongAttribute;
import com.exedio.cope.DataAttribute;
import com.exedio.cope.DataAttributeVariant;
import com.exedio.cope.StringAttribute;
import com.exedio.cope.function.LengthFunction;
import com.exedio.cope.function.UppercaseFunction;
import com.exedio.cope.pattern.Qualifier;

/**
 * An item having many attributes.
 * @cope.persistent
 * @author Ralf Wiebicke
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
	public static final UppercaseFunction someStringUpperCase = uppercase(someString);

	/**
	 * The length of code of the item.
	 */
	public static final LengthFunction someStringLength = length(someString);

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
	 * A data attribute.
	 */
	public static final DataAttribute someData = dataAttribute(DEFAULT);
	
	public static final DataAttributeVariant someVariant = dataAttributeVariant(someData);

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
	 * Not used by the model, but for tests.
	 */
	public static final class SomeEnum2 extends EnumValue
	{
		public static final int enumValue1NUM = 150;
		public static final SomeEnum2 enumValue1 = new SomeEnum2();

		public static final int enumValue2NUM = 250;
		public static final SomeEnum2 enumValue2 = new SomeEnum2();
	}


/**

	 **
	 * Creates a new AttributeItem with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param someNotNullString the initial value for attribute {@link #someNotNullString}.
	 * @param someNotNullInteger the initial value for attribute {@link #someNotNullInteger}.
	 * @param someNotNullLong the initial value for attribute {@link #someNotNullLong}.
	 * @param someNotNullDouble the initial value for attribute {@link #someNotNullDouble}.
	 * @param someNotNullBoolean the initial value for attribute {@link #someNotNullBoolean}.
	 * @param someNotNullItem the initial value for attribute {@link #someNotNullItem}.
	 * @param someNotNullEnumeration the initial value for attribute {@link #someNotNullEnumeration}.
	 * @throws com.exedio.cope.NotNullViolationException if someNotNullString, someNotNullItem, someNotNullEnumeration is null.
	 *
 */public AttributeItem(
				final java.lang.String someNotNullString,
				final int someNotNullInteger,
				final long someNotNullLong,
				final double someNotNullDouble,
				final boolean someNotNullBoolean,
				final EmptyItem someNotNullItem,
				final SomeEnumeration someNotNullEnumeration)
			throws
				com.exedio.cope.NotNullViolationException
	{
		this(new com.exedio.cope.AttributeValue[]{
			new com.exedio.cope.AttributeValue(AttributeItem.someNotNullString,someNotNullString),
			new com.exedio.cope.AttributeValue(AttributeItem.someNotNullInteger,new java.lang.Integer(someNotNullInteger)),
			new com.exedio.cope.AttributeValue(AttributeItem.someNotNullLong,new java.lang.Long(someNotNullLong)),
			new com.exedio.cope.AttributeValue(AttributeItem.someNotNullDouble,new java.lang.Double(someNotNullDouble)),
			new com.exedio.cope.AttributeValue(AttributeItem.someNotNullBoolean,(someNotNullBoolean?java.lang.Boolean.TRUE:java.lang.Boolean.FALSE)),
			new com.exedio.cope.AttributeValue(AttributeItem.someNotNullItem,someNotNullItem),
			new com.exedio.cope.AttributeValue(AttributeItem.someNotNullEnumeration,someNotNullEnumeration),
		});
		throwInitialNotNullViolationException();
	}/**

	 **
	 * Creates a new AttributeItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */private AttributeItem(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 *
 */private AttributeItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getSomeString()
	{
		return (java.lang.String)get(AttributeItem.someString);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeString(final java.lang.String someString)
	{
		try
		{
			set(AttributeItem.someString,someString);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.NotNullViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someStringUpperCase}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getSomeStringUpperCase()
	{
		return (java.lang.String)get(AttributeItem.someStringUpperCase);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someStringLength}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.Integer getSomeStringLength()
	{
		return (java.lang.Integer)get(AttributeItem.someStringLength);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someNotNullString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getSomeNotNullString()
	{
		return (java.lang.String)get(AttributeItem.someNotNullString);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someNotNullString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeNotNullString(final java.lang.String someNotNullString)
			throws
				com.exedio.cope.NotNullViolationException
	{
		try
		{
			set(AttributeItem.someNotNullString,someNotNullString);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someInteger}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.Integer getSomeInteger()
	{
		return (java.lang.Integer)get(AttributeItem.someInteger);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someInteger}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeInteger(final java.lang.Integer someInteger)
	{
		try
		{
			set(AttributeItem.someInteger,someInteger);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.NotNullViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someNotNullInteger}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final int getSomeNotNullInteger()
	{
		return ((java.lang.Integer)get(AttributeItem.someNotNullInteger)).intValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someNotNullInteger}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeNotNullInteger(final int someNotNullInteger)
	{
		try
		{
			set(AttributeItem.someNotNullInteger,new java.lang.Integer(someNotNullInteger));
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.NotNullViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someLong}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.Long getSomeLong()
	{
		return (java.lang.Long)get(AttributeItem.someLong);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someLong}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeLong(final java.lang.Long someLong)
	{
		try
		{
			set(AttributeItem.someLong,someLong);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.NotNullViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someNotNullLong}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final long getSomeNotNullLong()
	{
		return ((java.lang.Long)get(AttributeItem.someNotNullLong)).longValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someNotNullLong}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeNotNullLong(final long someNotNullLong)
	{
		try
		{
			set(AttributeItem.someNotNullLong,new java.lang.Long(someNotNullLong));
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.NotNullViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someDouble}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.Double getSomeDouble()
	{
		return (java.lang.Double)get(AttributeItem.someDouble);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someDouble}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeDouble(final java.lang.Double someDouble)
	{
		try
		{
			set(AttributeItem.someDouble,someDouble);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.NotNullViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someNotNullDouble}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final double getSomeNotNullDouble()
	{
		return ((java.lang.Double)get(AttributeItem.someNotNullDouble)).doubleValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someNotNullDouble}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeNotNullDouble(final double someNotNullDouble)
	{
		try
		{
			set(AttributeItem.someNotNullDouble,new java.lang.Double(someNotNullDouble));
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.NotNullViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someDate}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.util.Date getSomeDate()
	{
		return (java.util.Date)get(AttributeItem.someDate);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someDate}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeDate(final java.util.Date someDate)
	{
		try
		{
			set(AttributeItem.someDate,someDate);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.NotNullViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
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
			touch(AttributeItem.someDate);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someLongDate}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.util.Date getSomeLongDate()
	{
		return (java.util.Date)get(AttributeItem.someLongDate);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someLongDate}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeLongDate(final java.util.Date someLongDate)
	{
		try
		{
			set(AttributeItem.someLongDate,someLongDate);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.NotNullViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
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
			touch(AttributeItem.someLongDate);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someBoolean}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.Boolean getSomeBoolean()
	{
		return (java.lang.Boolean)get(AttributeItem.someBoolean);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someBoolean}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeBoolean(final java.lang.Boolean someBoolean)
	{
		try
		{
			set(AttributeItem.someBoolean,someBoolean);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.NotNullViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someNotNullBoolean}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final boolean getSomeNotNullBoolean()
	{
		return ((java.lang.Boolean)get(AttributeItem.someNotNullBoolean)).booleanValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someNotNullBoolean}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeNotNullBoolean(final boolean someNotNullBoolean)
	{
		try
		{
			set(AttributeItem.someNotNullBoolean,(someNotNullBoolean?java.lang.Boolean.TRUE:java.lang.Boolean.FALSE));
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.NotNullViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someItem}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final EmptyItem getSomeItem()
	{
		return (EmptyItem)get(AttributeItem.someItem);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someItem}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeItem(final EmptyItem someItem)
	{
		try
		{
			set(AttributeItem.someItem,someItem);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.NotNullViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someNotNullItem}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final EmptyItem getSomeNotNullItem()
	{
		return (EmptyItem)get(AttributeItem.someNotNullItem);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someNotNullItem}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeNotNullItem(final EmptyItem someNotNullItem)
			throws
				com.exedio.cope.NotNullViolationException
	{
		try
		{
			set(AttributeItem.someNotNullItem,someNotNullItem);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someEnumeration}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final SomeEnumeration getSomeEnumeration()
	{
		return (SomeEnumeration)get(AttributeItem.someEnumeration);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someEnumeration}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeEnumeration(final SomeEnumeration someEnumeration)
	{
		try
		{
			set(AttributeItem.someEnumeration,someEnumeration);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.NotNullViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someNotNullEnumeration}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final SomeEnumeration getSomeNotNullEnumeration()
	{
		return (SomeEnumeration)get(AttributeItem.someNotNullEnumeration);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someNotNullEnumeration}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeNotNullEnumeration(final SomeEnumeration someNotNullEnumeration)
			throws
				com.exedio.cope.NotNullViolationException
	{
		try
		{
			set(AttributeItem.someNotNullEnumeration,someNotNullEnumeration);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
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
	 * Returns a URL the data of the {@link #someVariant someVariant} variant of the data attribute {@link #someData} is available under.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getSomeDataURLSomeVariant()
	{
		return getURL(AttributeItem.someVariant);
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
 */public final void setSomeData(final java.io.InputStream data,final java.lang.String mimeMajor,final java.lang.String mimeMinor)throws java.io.IOException
	{
		try
		{
			set(AttributeItem.someData,data,mimeMajor,mimeMinor);
		}
		catch(com.exedio.cope.NotNullViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
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
		return (java.lang.String)emptyItem.get(new Object[]{this,key},AttributeEmptyItem.someQualifiedString);
	}/**

	 **
	 * Sets the qualifier.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setSomeQualifiedString(final EmptyItem key,final java.lang.String someQualifiedString)
			throws
				com.exedio.cope.NotNullViolationException,
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.ReadOnlyViolationException,
				java.lang.ClassCastException
	{
		emptyItem.set(new Object[]{this,key},AttributeEmptyItem.someQualifiedString,someQualifiedString);
	}/**

	 **
	 * The persistent type information for attributeItem.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.Type TYPE = 
		new com.exedio.cope.Type(AttributeItem.class)
;}
