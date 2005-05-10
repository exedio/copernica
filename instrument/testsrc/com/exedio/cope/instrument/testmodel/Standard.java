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

package com.exedio.cope.instrument.testmodel;

import com.exedio.cope.lib.DoubleAttribute;
import com.exedio.cope.lib.IntegerAttribute;
import com.exedio.cope.lib.Item;
import com.exedio.cope.lib.LongAttribute;
import com.exedio.cope.lib.StringAttribute;

/**
 * @persistent
 */
public class Standard extends Item
{
	public static final StringAttribute defaultString = stringAttribute(DEFAULT);
	public static final StringAttribute readOnlyString = stringAttribute(READ_ONLY);

	public static final IntegerAttribute defaultInteger = integerAttribute(DEFAULT);
	public static final IntegerAttribute nativeInteger = integerAttribute(NOT_NULL);

	public static final LongAttribute defaultLong = longAttribute(DEFAULT);
	public static final LongAttribute nativeLong = longAttribute(NOT_NULL);

	public static final DoubleAttribute defaultDouble = doubleAttribute(DEFAULT);
	public static final DoubleAttribute nativeDouble = doubleAttribute(NOT_NULL);

/**

	 **
	 * Creates a new Standard with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param initialReadOnlyString the initial value for attribute {@link #readOnlyString}.
	 * @param initialNativeInteger the initial value for attribute {@link #nativeInteger}.
	 * @param initialNativeLong the initial value for attribute {@link #nativeLong}.
	 * @param initialNativeDouble the initial value for attribute {@link #nativeDouble}.
	 *
 */public Standard(
				final java.lang.String initialReadOnlyString,
				final int initialNativeInteger,
				final long initialNativeLong,
				final double initialNativeDouble)
	{
		this(new com.exedio.cope.lib.AttributeValue[]{
			new com.exedio.cope.lib.AttributeValue(readOnlyString,initialReadOnlyString),
			new com.exedio.cope.lib.AttributeValue(nativeInteger,new java.lang.Integer(initialNativeInteger)),
			new com.exedio.cope.lib.AttributeValue(nativeLong,new java.lang.Long(initialNativeLong)),
			new com.exedio.cope.lib.AttributeValue(nativeDouble,new java.lang.Double(initialNativeDouble)),
		});
	}/**

	 **
	 * Creates a new Standard and sets the given attributes initially.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */private Standard(final com.exedio.cope.lib.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.lib.util.ReactivationConstructorDummy,int)
	 *
 */private Standard(com.exedio.cope.lib.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #defaultString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getDefaultString()
	{
		return (java.lang.String)getAttribute(Standard.defaultString);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #defaultString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setDefaultString(final java.lang.String defaultString)
	{
		try
		{
			setAttribute(Standard.defaultString,defaultString);
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
	 * Returns the value of the persistent attribute {@link #readOnlyString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getReadOnlyString()
	{
		return (java.lang.String)getAttribute(Standard.readOnlyString);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #defaultInteger}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.Integer getDefaultInteger()
	{
		return (java.lang.Integer)getAttribute(Standard.defaultInteger);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #defaultInteger}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setDefaultInteger(final java.lang.Integer defaultInteger)
	{
		try
		{
			setAttribute(Standard.defaultInteger,defaultInteger);
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
	 * Returns the value of the persistent attribute {@link #nativeInteger}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final int getNativeInteger()
	{
		return ((java.lang.Integer)getAttribute(Standard.nativeInteger)).intValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #nativeInteger}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setNativeInteger(final int nativeInteger)
	{
		try
		{
			setAttribute(Standard.nativeInteger,new java.lang.Integer(nativeInteger));
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
	 * Returns the value of the persistent attribute {@link #defaultLong}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.Long getDefaultLong()
	{
		return (java.lang.Long)getAttribute(Standard.defaultLong);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #defaultLong}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setDefaultLong(final java.lang.Long defaultLong)
	{
		try
		{
			setAttribute(Standard.defaultLong,defaultLong);
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
	 * Returns the value of the persistent attribute {@link #nativeLong}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final long getNativeLong()
	{
		return ((java.lang.Long)getAttribute(Standard.nativeLong)).longValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #nativeLong}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setNativeLong(final long nativeLong)
	{
		try
		{
			setAttribute(Standard.nativeLong,new java.lang.Long(nativeLong));
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
	 * Returns the value of the persistent attribute {@link #defaultDouble}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.Double getDefaultDouble()
	{
		return (java.lang.Double)getAttribute(Standard.defaultDouble);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #defaultDouble}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setDefaultDouble(final java.lang.Double defaultDouble)
	{
		try
		{
			setAttribute(Standard.defaultDouble,defaultDouble);
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
	 * Returns the value of the persistent attribute {@link #nativeDouble}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final double getNativeDouble()
	{
		return ((java.lang.Double)getAttribute(Standard.nativeDouble)).doubleValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #nativeDouble}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setNativeDouble(final double nativeDouble)
	{
		try
		{
			setAttribute(Standard.nativeDouble,new java.lang.Double(nativeDouble));
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
	 * The persistent type information for standard.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(Standard.class)
;}
