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
import com.exedio.cope.function.LengthView;
import com.exedio.cope.function.SumView;
import com.exedio.cope.function.UppercaseView;

/**
 * @cope.persistent
 * @author Ralf Wiebicke
 */
public class StringItem extends Item
{
	public static final StringAttribute any = new StringAttribute(OPTIONAL);

	public static final StringAttribute mandatory = new StringAttribute(MANDATORY);
	
	public static final StringAttribute min4 = new StringAttribute(OPTIONAL).lengthMin(4);

	public static final StringAttribute max4 = new StringAttribute(OPTIONAL).lengthMax(4);
	public static final StringAttribute max5Unchecked = new StringAttribute(OPTIONAL).lengthMaxUnchecked(5);

	public static final StringAttribute min4Max8 = new StringAttribute(OPTIONAL).lengthRange(4, 8);

	public static final StringAttribute exact6 = new StringAttribute(OPTIONAL).lengthExact(6);
	
	public static final StringAttribute long1K = new StringAttribute(OPTIONAL).lengthMax(1000);
	public static final StringAttribute long1M = new StringAttribute(OPTIONAL).lengthMax(1000000);
	
	public static final UppercaseView min4Upper = min4.uppercase();
	public static final UppercaseView max4Upper = max4.uppercase();

	public static final LengthView min4UpperLength = min4Upper.length();
	public static final LengthView max4UpperLength = max4Upper.length();
	
	public static final SumView min4AndMax4UpperLength = min4UpperLength.sum(max4UpperLength);
	
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
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <code>@cope.constructor public|package|protected|private|none</code> in the class comment and <code>@cope.initial</code> in the comment of attributes.
	 */
	public StringItem(
				final java.lang.String mandatory)
			throws
				com.exedio.cope.MandatoryViolationException
	{
		this(new com.exedio.cope.AttributeValue[]{
			StringItem.mandatory.map(mandatory),
		});
		throwInitialMandatoryViolationException();
	}/**

	 **
	 * Creates a new StringItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.generic.constructor public|package|protected|private|none</code> in the class comment.
	 */
	private StringItem(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private StringItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #any}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.String getAny()
	{
		return StringItem.any.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #any}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setAny(final java.lang.String any)
	{
		try
		{
			StringItem.any.set(this,any);
		}
		catch(com.exedio.cope.FinalViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.MandatoryViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #mandatory}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.String getMandatory()
	{
		return StringItem.mandatory.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #mandatory}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setMandatory(final java.lang.String mandatory)
			throws
				com.exedio.cope.MandatoryViolationException
	{
		try
		{
			StringItem.mandatory.set(this,mandatory);
		}
		catch(com.exedio.cope.FinalViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #min4}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.String getMin4()
	{
		return StringItem.min4.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #min4}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setMin4(final java.lang.String min4)
			throws
				com.exedio.cope.LengthViolationException
	{
		try
		{
			StringItem.min4.set(this,min4);
		}
		catch(com.exedio.cope.FinalViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.MandatoryViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #max4}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.String getMax4()
	{
		return StringItem.max4.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #max4}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setMax4(final java.lang.String max4)
			throws
				com.exedio.cope.LengthViolationException
	{
		try
		{
			StringItem.max4.set(this,max4);
		}
		catch(com.exedio.cope.FinalViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.MandatoryViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #max5Unchecked}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.String getMax5Unchecked()
	{
		return StringItem.max5Unchecked.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #max5Unchecked}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setMax5Unchecked(final java.lang.String max5Unchecked)
	{
		try
		{
			StringItem.max5Unchecked.set(this,max5Unchecked);
		}
		catch(com.exedio.cope.FinalViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.MandatoryViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #min4Max8}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.String getMin4Max8()
	{
		return StringItem.min4Max8.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #min4Max8}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setMin4Max8(final java.lang.String min4Max8)
			throws
				com.exedio.cope.LengthViolationException
	{
		try
		{
			StringItem.min4Max8.set(this,min4Max8);
		}
		catch(com.exedio.cope.FinalViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.MandatoryViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #exact6}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.String getExact6()
	{
		return StringItem.exact6.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #exact6}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setExact6(final java.lang.String exact6)
			throws
				com.exedio.cope.LengthViolationException
	{
		try
		{
			StringItem.exact6.set(this,exact6);
		}
		catch(com.exedio.cope.FinalViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.MandatoryViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #long1K}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.String getLong1K()
	{
		return StringItem.long1K.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #long1K}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setLong1K(final java.lang.String long1K)
			throws
				com.exedio.cope.LengthViolationException
	{
		try
		{
			StringItem.long1K.set(this,long1K);
		}
		catch(com.exedio.cope.FinalViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.MandatoryViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #long1M}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.String getLong1M()
	{
		return StringItem.long1M.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #long1M}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setLong1M(final java.lang.String long1M)
			throws
				com.exedio.cope.LengthViolationException
	{
		try
		{
			StringItem.long1M.set(this,long1M);
		}
		catch(com.exedio.cope.FinalViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.MandatoryViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #min4Upper}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.String getMin4Upper()
	{
		return StringItem.min4Upper.get(this);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #max4Upper}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.String getMax4Upper()
	{
		return StringItem.max4Upper.get(this);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #min4UpperLength}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.Integer getMin4UpperLength()
	{
		return StringItem.min4UpperLength.get(this);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #max4UpperLength}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.Integer getMax4UpperLength()
	{
		return StringItem.max4UpperLength.get(this);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #min4AndMax4UpperLength}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.Integer getMin4AndMax4UpperLength()
	{
		return StringItem.min4AndMax4UpperLength.get(this);
	}/**

	 **
	 * The persistent type information for stringItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.type public|package|protected|private|none</code> in the class comment.
	 */
	public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(StringItem.class)
;}
