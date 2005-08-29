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
import com.exedio.cope.StringAttribute;
import com.exedio.cope.function.LengthFunction;
import com.exedio.cope.function.UppercaseFunction;
import com.exedio.cope.pattern.Media;
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
	public static final StringAttribute someString = stringAttribute(OPTIONAL);

	/**
	 * Test non-persistent static final attributes.
	 */
	public static final String someTransientString = "transientString";

	/**
	 * The code of the item in upper case.
	 */
	public static final UppercaseFunction someStringUpperCase = someString.uppercase();

	/**
	 * The length of code of the item.
	 */
	public static final LengthFunction someStringLength = someString.length();

	/**
	 * A mandatory string attribute.
	 */
	public static final StringAttribute someNotNullString = stringAttribute(MANDATORY);

	/**
	 * An integer attribute
	 */
	public static final IntegerAttribute someInteger = integerAttribute(OPTIONAL);

	/**
	 * A mandatory integer attribute
	 */
	public static final IntegerAttribute someNotNullInteger = integerAttribute(MANDATORY);

	/**
	 * An integer attribute
	 */
	public static final LongAttribute someLong = longAttribute(OPTIONAL);

	/**
	 * A mandatory integer attribute
	 */
	public static final LongAttribute someNotNullLong = longAttribute(MANDATORY);

	/**
	 * A double attribute
	 */
	public static final DoubleAttribute someDouble = doubleAttribute(OPTIONAL);

	/**
	 * A mandatory double attribute
	 */
	public static final DoubleAttribute someNotNullDouble = doubleAttribute(MANDATORY);

	public static final DateAttribute someDate = dateAttribute(OPTIONAL);

	public static final DateAttribute someLongDate = dateAttribute(OPTIONAL, true);

	/**
	 * An boolean attribute
	 */
	public static final BooleanAttribute someBoolean = booleanAttribute(OPTIONAL);

	/**
	 * A mandatory boolean attribute
	 */
	public static final BooleanAttribute someNotNullBoolean = booleanAttribute(MANDATORY);
	
	/**
	 * An attribute referencing another persistent item
	 */
	public static final ItemAttribute someItem = itemAttribute(OPTIONAL, EmptyItem.class);

	/**
	 * An mandatory attribute referencing another persistent item
	 */
	public static final ItemAttribute someNotNullItem = itemAttribute(MANDATORY, EmptyItem.class);

	/**
	 * An enumeration attribute
	 */
	public static final EnumAttribute someEnum = enumAttribute(OPTIONAL, SomeEnum.class);

	/**
	 * A mandatory enumeration attribute
	 */
	public static final EnumAttribute someNotNullEnum = enumAttribute(MANDATORY, SomeEnum.class);

	/**
	 * A data attribute.
	 */
	public static final Media someData = new Media(OPTIONAL);
	
	public static final Qualifier emptyItem = new Qualifier(AttributeEmptyItem.parentKey);

	/**
	 * A class representing the possible states of the persistent enumeration attribute {@link #someEnum}.
	 */
	public static final class SomeEnum extends EnumValue
	{
		public static final int enumValue1NUM = 100;
		public static final SomeEnum enumValue1 = new SomeEnum();

		public static final int enumValue2NUM = 200;
		public static final SomeEnum enumValue2 = new SomeEnum();

		public static final int enumValue3NUM = 300;
		public static final SomeEnum enumValue3 = new SomeEnum();
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
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 * @param someNotNullString the initial value for attribute {@link #someNotNullString}.
	 * @param someNotNullInteger the initial value for attribute {@link #someNotNullInteger}.
	 * @param someNotNullLong the initial value for attribute {@link #someNotNullLong}.
	 * @param someNotNullDouble the initial value for attribute {@link #someNotNullDouble}.
	 * @param someNotNullBoolean the initial value for attribute {@link #someNotNullBoolean}.
	 * @param someNotNullItem the initial value for attribute {@link #someNotNullItem}.
	 * @param someNotNullEnum the initial value for attribute {@link #someNotNullEnum}.
	 * @throws com.exedio.cope.MandatoryViolationException if someNotNullString, someNotNullItem, someNotNullEnum is null.
	 *
 */public AttributeItem(
				final java.lang.String someNotNullString,
				final int someNotNullInteger,
				final long someNotNullLong,
				final double someNotNullDouble,
				final boolean someNotNullBoolean,
				final EmptyItem someNotNullItem,
				final SomeEnum someNotNullEnum)
			throws
				com.exedio.cope.MandatoryViolationException
	{
		this(new com.exedio.cope.AttributeValue[]{
			new com.exedio.cope.AttributeValue(AttributeItem.someNotNullString,someNotNullString),
			new com.exedio.cope.AttributeValue(AttributeItem.someNotNullInteger,new java.lang.Integer(someNotNullInteger)),
			new com.exedio.cope.AttributeValue(AttributeItem.someNotNullLong,new java.lang.Long(someNotNullLong)),
			new com.exedio.cope.AttributeValue(AttributeItem.someNotNullDouble,new java.lang.Double(someNotNullDouble)),
			new com.exedio.cope.AttributeValue(AttributeItem.someNotNullBoolean,(someNotNullBoolean?java.lang.Boolean.TRUE:java.lang.Boolean.FALSE)),
			new com.exedio.cope.AttributeValue(AttributeItem.someNotNullItem,someNotNullItem),
			new com.exedio.cope.AttributeValue(AttributeItem.someNotNullEnum,someNotNullEnum),
		});
		throwInitialMandatoryViolationException();
	}/**

	 **
	 * Creates a new AttributeItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */private AttributeItem(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 *
 */private AttributeItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someString}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getSomeString()
	{
		return (java.lang.String)get(AttributeItem.someString);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someString}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
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
		catch(com.exedio.cope.MandatoryViolationException e)
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
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getSomeStringUpperCase()
	{
		return (java.lang.String)get(AttributeItem.someStringUpperCase);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someStringLength}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.Integer getSomeStringLength()
	{
		return (java.lang.Integer)get(AttributeItem.someStringLength);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someNotNullString}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getSomeNotNullString()
	{
		return (java.lang.String)get(AttributeItem.someNotNullString);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someNotNullString}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setSomeNotNullString(final java.lang.String someNotNullString)
			throws
				com.exedio.cope.MandatoryViolationException
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
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.Integer getSomeInteger()
	{
		return (java.lang.Integer)get(AttributeItem.someInteger);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someInteger}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
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
		catch(com.exedio.cope.MandatoryViolationException e)
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
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final int getSomeNotNullInteger()
	{
		return ((java.lang.Integer)get(AttributeItem.someNotNullInteger)).intValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someNotNullInteger}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
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
		catch(com.exedio.cope.MandatoryViolationException e)
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
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.Long getSomeLong()
	{
		return (java.lang.Long)get(AttributeItem.someLong);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someLong}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
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
		catch(com.exedio.cope.MandatoryViolationException e)
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
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final long getSomeNotNullLong()
	{
		return ((java.lang.Long)get(AttributeItem.someNotNullLong)).longValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someNotNullLong}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
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
		catch(com.exedio.cope.MandatoryViolationException e)
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
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.Double getSomeDouble()
	{
		return (java.lang.Double)get(AttributeItem.someDouble);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someDouble}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
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
		catch(com.exedio.cope.MandatoryViolationException e)
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
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final double getSomeNotNullDouble()
	{
		return ((java.lang.Double)get(AttributeItem.someNotNullDouble)).doubleValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someNotNullDouble}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
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
		catch(com.exedio.cope.MandatoryViolationException e)
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
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.util.Date getSomeDate()
	{
		return (java.util.Date)get(AttributeItem.someDate);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someDate}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
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
		catch(com.exedio.cope.MandatoryViolationException e)
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
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
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
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.util.Date getSomeLongDate()
	{
		return (java.util.Date)get(AttributeItem.someLongDate);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someLongDate}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
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
		catch(com.exedio.cope.MandatoryViolationException e)
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
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
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
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.Boolean getSomeBoolean()
	{
		return (java.lang.Boolean)get(AttributeItem.someBoolean);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someBoolean}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
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
		catch(com.exedio.cope.MandatoryViolationException e)
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
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final boolean getSomeNotNullBoolean()
	{
		return ((java.lang.Boolean)get(AttributeItem.someNotNullBoolean)).booleanValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someNotNullBoolean}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
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
		catch(com.exedio.cope.MandatoryViolationException e)
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
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final EmptyItem getSomeItem()
	{
		return (EmptyItem)get(AttributeItem.someItem);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someItem}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
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
		catch(com.exedio.cope.MandatoryViolationException e)
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
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final EmptyItem getSomeNotNullItem()
	{
		return (EmptyItem)get(AttributeItem.someNotNullItem);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someNotNullItem}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setSomeNotNullItem(final EmptyItem someNotNullItem)
			throws
				com.exedio.cope.MandatoryViolationException
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
	 * Returns the value of the persistent attribute {@link #someEnum}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final SomeEnum getSomeEnum()
	{
		return (SomeEnum)get(AttributeItem.someEnum);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someEnum}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setSomeEnum(final SomeEnum someEnum)
	{
		try
		{
			set(AttributeItem.someEnum,someEnum);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.MandatoryViolationException e)
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
	 * Returns the value of the persistent attribute {@link #someNotNullEnum}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final SomeEnum getSomeNotNullEnum()
	{
		return (SomeEnum)get(AttributeItem.someNotNullEnum);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someNotNullEnum}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setSomeNotNullEnum(final SomeEnum someNotNullEnum)
			throws
				com.exedio.cope.MandatoryViolationException
	{
		try
		{
			set(AttributeItem.someNotNullEnum,someNotNullEnum);
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
	 * Returns the qualifier.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final AttributeEmptyItem getEmptyItem(final EmptyItem key)
	{
		return (AttributeEmptyItem)emptyItem.getQualifier(new Object[]{this,key});
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getSomeQualifiedString(final EmptyItem key)
	{
		return (java.lang.String)emptyItem.get(new Object[]{this,key},AttributeEmptyItem.someQualifiedString);
	}/**

	 **
	 * Sets the qualifier.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setSomeQualifiedString(final EmptyItem key,final java.lang.String someQualifiedString)
			throws
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.ReadOnlyViolationException,
				java.lang.ClassCastException
	{
		emptyItem.set(new Object[]{this,key},AttributeEmptyItem.someQualifiedString,someQualifiedString);
	}/**

	 **
	 * Returns whether this http entity {@link #someData} has data available.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final boolean isSomeDataNull()
	{
		return AttributeItem.someData.isNull(this);
	}/**

	 **
	 * Returns a URL the data of the http entity {@link #someData} is available under.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getSomeDataURL()
	{
		return AttributeItem.someData.getURL(this);
	}/**

	 **
	 * Returns the major mime type of the http entity {@link #someData}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getSomeDataMimeMajor()
	{
		return AttributeItem.someData.getMimeMajor(this);
	}/**

	 **
	 * Returns the minor mime type of the http entity {@link #someData}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getSomeDataMimeMinor()
	{
		return AttributeItem.someData.getMimeMinor(this);
	}/**

	 **
	 * Returns the content type of the http entity {@link #someData}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getSomeDataContentType()
	{
		return AttributeItem.someData.getContentType(this);
	}/**

	 **
	 * Returns the data of the http entity {@link #someData}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.io.InputStream getSomeDataData()
	{
		return AttributeItem.someData.getData(this);
	}/**

	 **
	 * Sets the new data for the http entity {@link #someData}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 * @throws java.io.IOException if accessing <code>data</code> throws an IOException.
	 *
 */public final void setSomeData(final java.io.InputStream data,final java.lang.String mimeMajor,final java.lang.String mimeMinor)
			throws
				java.io.IOException
	{
		AttributeItem.someData.set(this,data,mimeMajor,mimeMinor);
	}/**

	 **
	 * The persistent type information for attributeItem.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(AttributeItem.class)
;}
