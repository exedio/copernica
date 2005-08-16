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

import com.exedio.cope.Item;
import com.exedio.cope.ItemAttribute;
import com.exedio.cope.StringAttribute;
import com.exedio.cope.UniqueConstraint;

/**
 * @cope.persistent
 * @author Ralf Wiebicke
 */
public class QualifiedEmptyQualifier extends Item
{
	public static final ItemAttribute parent = itemAttribute(READ_ONLY_NOT_NULL, QualifiedItem.class);
	
	public static final ItemAttribute key = itemAttribute(READ_ONLY_NOT_NULL, EmptyItem.class);
	
	public static final UniqueConstraint qualifyUnique = uniqueConstraint(parent, key);
	
	public static final StringAttribute qualifiedA = stringAttribute(DEFAULT);
	public static final StringAttribute qualifiedB = stringAttribute(DEFAULT);
	

/**

	 **
	 * Creates a new QualifiedEmptyQualifier with all the attributes initially needed.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 * @param parent the initial value for attribute {@link #parent}.
	 * @param key the initial value for attribute {@link #key}.
	 * @throws com.exedio.cope.NotNullViolationException if parent, key is null.
	 * @throws com.exedio.cope.UniqueViolationException if parent, key is not unique.
	 *
 */public QualifiedEmptyQualifier(
				final QualifiedItem parent,
				final EmptyItem key)
			throws
				com.exedio.cope.NotNullViolationException,
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.AttributeValue[]{
			new com.exedio.cope.AttributeValue(QualifiedEmptyQualifier.parent,parent),
			new com.exedio.cope.AttributeValue(QualifiedEmptyQualifier.key,key),
		});
		throwInitialNotNullViolationException();
		throwInitialUniqueViolationException();
	}/**

	 **
	 * Creates a new QualifiedEmptyQualifier and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */private QualifiedEmptyQualifier(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 *
 */private QualifiedEmptyQualifier(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #parent}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final QualifiedItem getParent()
	{
		return (QualifiedItem)get(QualifiedEmptyQualifier.parent);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #key}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final EmptyItem getKey()
	{
		return (EmptyItem)get(QualifiedEmptyQualifier.key);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #qualifiedA}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getQualifiedA()
	{
		return (java.lang.String)get(QualifiedEmptyQualifier.qualifiedA);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #qualifiedA}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setQualifiedA(final java.lang.String qualifiedA)
	{
		try
		{
			set(QualifiedEmptyQualifier.qualifiedA,qualifiedA);
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
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getQualifiedB()
	{
		return (java.lang.String)get(QualifiedEmptyQualifier.qualifiedB);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #qualifiedB}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setQualifiedB(final java.lang.String qualifiedB)
	{
		try
		{
			set(QualifiedEmptyQualifier.qualifiedB,qualifiedB);
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
	 * Finds a qualifiedEmptyQualifier by it's unique attributes.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 * @param parent shall be equal to attribute {@link #parent}.
	 * @param key shall be equal to attribute {@link #key}.
	 *
 */public static final QualifiedEmptyQualifier findByQualifyUnique(final QualifiedItem parent,final EmptyItem key)
	{
		return (QualifiedEmptyQualifier)QualifiedEmptyQualifier.qualifyUnique.searchUnique(new Object[]{parent,key});
	}/**

	 **
	 * The persistent type information for qualifiedEmptyQualifier.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(QualifiedEmptyQualifier.class)
;}
