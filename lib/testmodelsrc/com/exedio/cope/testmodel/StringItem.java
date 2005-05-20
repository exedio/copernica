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

import com.exedio.cope.Hash;
import com.exedio.cope.Item;
import com.exedio.cope.StringAttribute;
import com.exedio.cope.function.LengthFunction;
import com.exedio.cope.function.SumFunction;
import com.exedio.cope.function.UppercaseFunction;
import com.exedio.cope.pattern.MD5Hash;

/**
 * @persistent
 * @author Ralf Wiebicke
 */
public class StringItem extends Item
{
	public static final StringAttribute any = stringAttribute(DEFAULT);

	public static final StringAttribute min4 = stringAttribute(DEFAULT, 4);

	public static final StringAttribute max4 = stringAttribute(DEFAULT, 0, 4);

	public static final StringAttribute min4Max8 = stringAttribute(DEFAULT, 4, 8);
	
	public static final UppercaseFunction min4Upper = new UppercaseFunction(min4);
	public static final UppercaseFunction max4Upper = new UppercaseFunction(max4);

	public static final LengthFunction min4UpperLength = new LengthFunction(min4Upper);
	public static final LengthFunction max4UpperLength = new LengthFunction(max4Upper);
	
	public static final SumFunction min4AndMax4UpperLength = new SumFunction(min4UpperLength, max4UpperLength);
	
	public static final StringAttribute hashed1MD5 = stringAttribute(DEFAULT);
	public static final MD5Hash hashed1 = new MD5Hash(hashed1MD5);
	public static final MD5Hash hashed1Latin = new MD5Hash(hashed1MD5, "ISO-8859-1");

	public static final StringAttribute hashed2Wrap = stringAttribute(DEFAULT);
	public static final Hash hashed2 = new WrapHash(hashed2Wrap);

/**

	 **
	 * Creates a new StringItem with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public StringItem()
	{
		this(new com.exedio.cope.AttributeValue[]{
		});
	}/**

	 **
	 * Creates a new StringItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */private StringItem(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 *
 */private StringItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #any}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getAny()
	{
		return (java.lang.String)getAttribute(StringItem.any);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #any}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setAny(final java.lang.String any)
	{
		try
		{
			setAttribute(StringItem.any,any);
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
	 * Returns the value of the persistent attribute {@link #min4}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getMin4()
	{
		return (java.lang.String)getAttribute(StringItem.min4);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #min4}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setMin4(final java.lang.String min4)
			throws
				com.exedio.cope.LengthViolationException
	{
		try
		{
			setAttribute(StringItem.min4,min4);
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
	 * Returns the value of the persistent attribute {@link #max4}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getMax4()
	{
		return (java.lang.String)getAttribute(StringItem.max4);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #max4}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setMax4(final java.lang.String max4)
			throws
				com.exedio.cope.LengthViolationException
	{
		try
		{
			setAttribute(StringItem.max4,max4);
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
	 * Returns the value of the persistent attribute {@link #min4Max8}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getMin4Max8()
	{
		return (java.lang.String)getAttribute(StringItem.min4Max8);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #min4Max8}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setMin4Max8(final java.lang.String min4Max8)
			throws
				com.exedio.cope.LengthViolationException
	{
		try
		{
			setAttribute(StringItem.min4Max8,min4Max8);
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
	 * Returns the value of the persistent attribute {@link #min4Upper}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getMin4Upper()
	{
		return (java.lang.String)getAttribute(StringItem.min4Upper);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #max4Upper}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getMax4Upper()
	{
		return (java.lang.String)getAttribute(StringItem.max4Upper);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #min4UpperLength}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.Integer getMin4UpperLength()
	{
		return (java.lang.Integer)getAttribute(StringItem.min4UpperLength);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #max4UpperLength}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.Integer getMax4UpperLength()
	{
		return (java.lang.Integer)getAttribute(StringItem.max4UpperLength);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #min4AndMax4UpperLength}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.Integer getMin4AndMax4UpperLength()
	{
		return (java.lang.Integer)getAttribute(StringItem.min4AndMax4UpperLength);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #hashed1MD5}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getHashed1MD5()
	{
		return (java.lang.String)getAttribute(StringItem.hashed1MD5);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #hashed1MD5}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setHashed1MD5(final java.lang.String hashed1MD5)
	{
		try
		{
			setAttribute(StringItem.hashed1MD5,hashed1MD5);
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
	 * Returns whether the given value corresponds to the hash in {@link #hashed1}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final boolean checkHashed1(final java.lang.String hashed1)
	{
		return StringItem.hashed1.checkHash(this,hashed1);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #hashed1}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setHashed1(final java.lang.String hashed1)
	{
		try
		{
			StringItem.hashed1.setHash(this,hashed1);
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
	 * Returns whether the given value corresponds to the hash in {@link #hashed1Latin}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final boolean checkHashed1Latin(final java.lang.String hashed1Latin)
	{
		return StringItem.hashed1Latin.checkHash(this,hashed1Latin);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #hashed1Latin}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setHashed1Latin(final java.lang.String hashed1Latin)
	{
		try
		{
			StringItem.hashed1Latin.setHash(this,hashed1Latin);
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
	 * Returns the value of the persistent attribute {@link #hashed2Wrap}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getHashed2Wrap()
	{
		return (java.lang.String)getAttribute(StringItem.hashed2Wrap);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #hashed2Wrap}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setHashed2Wrap(final java.lang.String hashed2Wrap)
	{
		try
		{
			setAttribute(StringItem.hashed2Wrap,hashed2Wrap);
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
	 * Returns whether the given value corresponds to the hash in {@link #hashed2}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final boolean checkHashed2(final java.lang.String hashed2)
	{
		return StringItem.hashed2.checkHash(this,hashed2);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #hashed2}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setHashed2(final java.lang.String hashed2)
	{
		try
		{
			StringItem.hashed2.setHash(this,hashed2);
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
	 * The persistent type information for stringItem.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.Type TYPE = 
		new com.exedio.cope.Type(StringItem.class)
;}
