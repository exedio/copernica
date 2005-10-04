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

import com.exedio.cope.AttributeValue;
import com.exedio.cope.Item;
import com.exedio.cope.MandatoryViolationException;
import com.exedio.cope.StringAttribute;
import com.exedio.cope.function.LengthFunction;
import com.exedio.cope.function.SumFunction;
import com.exedio.cope.function.UppercaseFunction;

/**
 * @cope.persistent
 * @author Ralf Wiebicke
 */
public class StringItem extends Item
{
	public static final StringAttribute any = new StringAttribute(OPTIONAL);

	public static final StringAttribute mandatory = new StringAttribute(MANDATORY);
	
	public static final StringAttribute min4 = new StringAttribute(OPTIONAL, 4);

	public static final StringAttribute max4 = new StringAttribute(OPTIONAL, 0, 4);

	public static final StringAttribute min4Max8 = new StringAttribute(OPTIONAL, 4, 8);

	public static final StringAttribute exact6 = new StringAttribute(OPTIONAL, 6, 6);
	
	public static final UppercaseFunction min4Upper = min4.uppercase();
	public static final UppercaseFunction max4Upper = max4.uppercase();

	public static final LengthFunction min4UpperLength = min4Upper.length();
	public static final LengthFunction max4UpperLength = max4Upper.length();
	
	public static final SumFunction min4AndMax4UpperLength = min4UpperLength.sum(max4UpperLength);
	
	public StringItem(final String any, boolean dummy)
	{
		this(new AttributeValue[]{
				new AttributeValue(StringItem.any, any),
				new AttributeValue(StringItem.mandatory, "dummy"),
		});
	}
	
	public StringItem(final String mandatory, double dummy) throws MandatoryViolationException
	{
		this(new AttributeValue[]{
				new AttributeValue(StringItem.mandatory, mandatory),
		});
		throwInitialMandatoryViolationException();
	}
	
/**

	 **
	 * Creates a new StringItem with all the attributes initially needed.
	 * @param mandatory the initial value for attribute {@link #mandatory}.
	 * @throws com.exedio.cope.MandatoryViolationException if mandatory is null.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public StringItem(
				final java.lang.String mandatory)
			throws
				com.exedio.cope.MandatoryViolationException
	{
		this(new com.exedio.cope.AttributeValue[]{
			new com.exedio.cope.AttributeValue(StringItem.mandatory,mandatory),
		});
		throwInitialMandatoryViolationException();
	}/**

	 **
	 * Creates a new StringItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */private StringItem(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */private StringItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #any}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getAny()
	{
		return (java.lang.String)get(StringItem.any);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #any}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setAny(final java.lang.String any)
	{
		try
		{
			set(StringItem.any,any);
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
	 * Returns the value of the persistent attribute {@link #mandatory}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getMandatory()
	{
		return (java.lang.String)get(StringItem.mandatory);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #mandatory}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setMandatory(final java.lang.String mandatory)
			throws
				com.exedio.cope.MandatoryViolationException
	{
		try
		{
			set(StringItem.mandatory,mandatory);
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
	 * Returns the value of the persistent attribute {@link #min4}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getMin4()
	{
		return (java.lang.String)get(StringItem.min4);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #min4}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setMin4(final java.lang.String min4)
			throws
				com.exedio.cope.LengthViolationException
	{
		try
		{
			set(StringItem.min4,min4);
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
	 * Returns the value of the persistent attribute {@link #max4}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getMax4()
	{
		return (java.lang.String)get(StringItem.max4);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #max4}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setMax4(final java.lang.String max4)
			throws
				com.exedio.cope.LengthViolationException
	{
		try
		{
			set(StringItem.max4,max4);
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
	 * Returns the value of the persistent attribute {@link #min4Max8}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getMin4Max8()
	{
		return (java.lang.String)get(StringItem.min4Max8);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #min4Max8}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setMin4Max8(final java.lang.String min4Max8)
			throws
				com.exedio.cope.LengthViolationException
	{
		try
		{
			set(StringItem.min4Max8,min4Max8);
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
	 * Returns the value of the persistent attribute {@link #exact6}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getExact6()
	{
		return (java.lang.String)get(StringItem.exact6);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #exact6}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setExact6(final java.lang.String exact6)
			throws
				com.exedio.cope.LengthViolationException
	{
		try
		{
			set(StringItem.exact6,exact6);
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
	 * Returns the value of the persistent attribute {@link #min4Upper}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getMin4Upper()
	{
		return (java.lang.String)get(StringItem.min4Upper);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #max4Upper}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getMax4Upper()
	{
		return (java.lang.String)get(StringItem.max4Upper);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #min4UpperLength}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.Integer getMin4UpperLength()
	{
		return (java.lang.Integer)get(StringItem.min4UpperLength);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #max4UpperLength}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.Integer getMax4UpperLength()
	{
		return (java.lang.Integer)get(StringItem.max4UpperLength);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #min4AndMax4UpperLength}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.Integer getMin4AndMax4UpperLength()
	{
		return (java.lang.Integer)get(StringItem.min4AndMax4UpperLength);
	}/**

	 **
	 * The persistent type information for stringItem.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.type public|package|protected|private|none</code> in the class comment.
	 *
 */public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(StringItem.class)
;}
