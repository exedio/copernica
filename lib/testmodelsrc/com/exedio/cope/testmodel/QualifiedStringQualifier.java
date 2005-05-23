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

import com.exedio.cope.IntegerAttribute;
import com.exedio.cope.Item;
import com.exedio.cope.ItemAttribute;
import com.exedio.cope.StringAttribute;
import com.exedio.cope.UniqueConstraint;

/**
 * @persistent
 * @author Ralf Wiebicke
 */
public class QualifiedStringQualifier extends Item
{
	public static final ItemAttribute parent = itemAttribute(READ_ONLY_NOT_NULL, QualifiedItem.class);
	
	public static final StringAttribute key = stringAttribute(READ_ONLY_NOT_NULL);
	
	public static final UniqueConstraint qualifyUnique = uniqueConstraint(parent, key);
	
	public static final IntegerAttribute qualifiedA = integerAttribute(DEFAULT);
	public static final IntegerAttribute qualifiedB = integerAttribute(DEFAULT);
	

/**

	 **
	 * Creates a new QualifiedStringQualifier with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param parent the initial value for attribute {@link #parent}.
	 * @param key the initial value for attribute {@link #key}.
	 * @throws com.exedio.cope.NotNullViolationException if parent, key is null.
	 * @throws com.exedio.cope.UniqueViolationException if parent, key is not unique.
	 *
 */public QualifiedStringQualifier(
				final QualifiedItem parent,
				final java.lang.String key)
			throws
				com.exedio.cope.NotNullViolationException,
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.AttributeValue[]{
			new com.exedio.cope.AttributeValue(QualifiedStringQualifier.parent,parent),
			new com.exedio.cope.AttributeValue(QualifiedStringQualifier.key,key),
		});
		throwInitialNotNullViolationException();
		throwInitialUniqueViolationException();
	}/**

	 **
	 * Creates a new QualifiedStringQualifier and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */private QualifiedStringQualifier(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 *
 */private QualifiedStringQualifier(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #parent}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final QualifiedItem getParent()
	{
		return (QualifiedItem)get(QualifiedStringQualifier.parent);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #key}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.String getKey()
	{
		return (java.lang.String)get(QualifiedStringQualifier.key);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #qualifiedA}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.Integer getQualifiedA()
	{
		return (java.lang.Integer)get(QualifiedStringQualifier.qualifiedA);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #qualifiedA}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setQualifiedA(final java.lang.Integer qualifiedA)
	{
		try
		{
			setAttribute(QualifiedStringQualifier.qualifiedA,qualifiedA);
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
	 * Returns the value of the persistent attribute {@link #qualifiedB}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final java.lang.Integer getQualifiedB()
	{
		return (java.lang.Integer)get(QualifiedStringQualifier.qualifiedB);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #qualifiedB}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public final void setQualifiedB(final java.lang.Integer qualifiedB)
	{
		try
		{
			setAttribute(QualifiedStringQualifier.qualifiedB,qualifiedB);
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
	 * Finds a qualifiedStringQualifier by it's unique attributes.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param parent shall be equal to attribute {@link #parent}.
	 * @param key shall be equal to attribute {@link #key}.
	 *
 */public static final QualifiedStringQualifier findByQualifyUnique(final QualifiedItem parent,final java.lang.String key)
	{
		return (QualifiedStringQualifier)QualifiedStringQualifier.qualifyUnique.searchUnique(new Object[]{parent,key});
	}/**

	 **
	 * The persistent type information for qualifiedStringQualifier.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.Type TYPE = 
		new com.exedio.cope.Type(QualifiedStringQualifier.class)
;}
