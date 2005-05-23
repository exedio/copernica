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

import com.exedio.cope.AttributeValue;
import com.exedio.cope.Item;
import com.exedio.cope.StringAttribute;

/**
 * @persistent
 * @cope-type private
 * @cope-generic-constructor none
 */
public class TypePrivate extends Item
{
	public static final StringAttribute defaultString = stringAttribute(DEFAULT);

	/**
	 * Creates a new TypeNone and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 */
	private TypePrivate(final AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
		// here one could do additional things
	}

/**

	 **
	 * Creates a new TypePrivate with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public TypePrivate()
	{
		this(new com.exedio.cope.AttributeValue[]{
		});
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 *
 */private TypePrivate(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #defaultString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getDefaultString()
	{
		return (java.lang.String)get(TypePrivate.defaultString);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #defaultString}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setDefaultString(final java.lang.String defaultString)
	{
		try
		{
			set(TypePrivate.defaultString,defaultString);
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
	 * The persistent type information for typePrivate.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */private static final com.exedio.cope.Type TYPE = 
		new com.exedio.cope.Type(TypePrivate.class)
;}
