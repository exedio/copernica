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

import com.exedio.cope.lib.Item;
import com.exedio.cope.lib.StringAttribute;

/**
 * @persistent
 * @author ralf.wiebicke@exedio.com
 */
public class SecondSub extends Super
{

	public static final StringAttribute firstSubString = stringAttribute(DEFAULT);
	
	public SecondSub(final int initialSuperInt)
	{
		super(new com.exedio.cope.lib.AttributeValue[]{
			new com.exedio.cope.lib.AttributeValue(superInt,new Integer(initialSuperInt)),
		});
	} 

/**

	 **
	 * Creates a new SecondSub with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public SecondSub()
	{
		this(new com.exedio.cope.lib.AttributeValue[]{
		});
	}/**

	 **
	 * Creates a new SecondSub and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.lib.Type#newItem Type.newItem}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */private SecondSub(final com.exedio.cope.lib.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.lib.util.ReactivationConstructorDummy,int)
	 *
 */private SecondSub(com.exedio.cope.lib.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #firstSubString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getFirstSubString()
	{
		return (java.lang.String)getAttribute(SecondSub.firstSubString);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #firstSubString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setFirstSubString(final java.lang.String firstSubString)
	{
		try
		{
			setAttribute(SecondSub.firstSubString,firstSubString);
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
	 * The persistent type information for secondSub.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(SecondSub.class)
;}
