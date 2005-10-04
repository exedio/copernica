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
import com.exedio.cope.pattern.Qualifier;
import com.exedio.cope.testmodel.QualifiedIntegerEnumQualifier.KeyEnum;

/**
 * @cope.persistent
 * @author Ralf Wiebicke
 */
public class QualifiedItem extends Item
{
	public static final IntegerAttribute number = new IntegerAttribute(OPTIONAL);
	
	public static final Qualifier qualifier = new Qualifier(QualifiedEmptyQualifier.qualifyUnique);
	public static final Qualifier stringQualifier = new Qualifier(QualifiedStringQualifier.qualifyUnique);
	public static final Qualifier intEnumQualifier = new Qualifier(QualifiedIntegerEnumQualifier.qualifyUnique);


/**

	 **
	 * Creates a new QualifiedItem with all the attributes initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public QualifiedItem()
	{
		this(new com.exedio.cope.AttributeValue[]{
		});
	}/**

	 **
	 * Creates a new QualifiedItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */private QualifiedItem(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */private QualifiedItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #number}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none</code> in the comment of the attribute.
	 *
 */public final java.lang.Integer getNumber()
	{
		return (java.lang.Integer)get(QualifiedItem.number);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #number}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setNumber(final java.lang.Integer number)
	{
		try
		{
			set(QualifiedItem.number,number);
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
	 * Returns the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final QualifiedEmptyQualifier getQualifier(final EmptyItem key)
	{
		return (QualifiedEmptyQualifier)qualifier.getQualifier(new Object[]{this,key});
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getQualifiedA(final EmptyItem key)
	{
		return (java.lang.String)qualifier.get(new Object[]{this,key},QualifiedEmptyQualifier.qualifiedA);
	}/**

	 **
	 * Sets the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setQualifiedA(final EmptyItem key,final java.lang.String qualifiedA)
	{
		try
		{
			qualifier.set(new Object[]{this,key},QualifiedEmptyQualifier.qualifiedA,qualifiedA);
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
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getQualifiedB(final EmptyItem key)
	{
		return (java.lang.String)qualifier.get(new Object[]{this,key},QualifiedEmptyQualifier.qualifiedB);
	}/**

	 **
	 * Sets the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setQualifiedB(final EmptyItem key,final java.lang.String qualifiedB)
	{
		try
		{
			qualifier.set(new Object[]{this,key},QualifiedEmptyQualifier.qualifiedB,qualifiedB);
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
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final QualifiedStringQualifier getStringQualifier(final java.lang.String key)
	{
		return (QualifiedStringQualifier)stringQualifier.getQualifier(new Object[]{this,key});
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.Integer getQualifiedA(final java.lang.String key)
	{
		return (java.lang.Integer)stringQualifier.get(new Object[]{this,key},QualifiedStringQualifier.qualifiedA);
	}/**

	 **
	 * Sets the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setQualifiedA(final java.lang.String key,final java.lang.Integer qualifiedA)
	{
		try
		{
			stringQualifier.set(new Object[]{this,key},QualifiedStringQualifier.qualifiedA,qualifiedA);
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
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.Integer getQualifiedB(final java.lang.String key)
	{
		return (java.lang.Integer)stringQualifier.get(new Object[]{this,key},QualifiedStringQualifier.qualifiedB);
	}/**

	 **
	 * Sets the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setQualifiedB(final java.lang.String key,final java.lang.Integer qualifiedB)
	{
		try
		{
			stringQualifier.set(new Object[]{this,key},QualifiedStringQualifier.qualifiedB,qualifiedB);
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
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final QualifiedIntegerEnumQualifier getIntEnumQualifier(final java.lang.Integer keyX,final KeyEnum keyY)
	{
		return (QualifiedIntegerEnumQualifier)intEnumQualifier.getQualifier(new Object[]{this,keyX,keyY});
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getQualifiedA(final java.lang.Integer keyX,final KeyEnum keyY)
	{
		return (java.lang.String)intEnumQualifier.get(new Object[]{this,keyX,keyY},QualifiedIntegerEnumQualifier.qualifiedA);
	}/**

	 **
	 * Sets the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setQualifiedA(final java.lang.Integer keyX,final KeyEnum keyY,final java.lang.String qualifiedA)
	{
		try
		{
			intEnumQualifier.set(new Object[]{this,keyX,keyY},QualifiedIntegerEnumQualifier.qualifiedA,qualifiedA);
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
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getQualifiedB(final java.lang.Integer keyX,final KeyEnum keyY)
	{
		return (java.lang.String)intEnumQualifier.get(new Object[]{this,keyX,keyY},QualifiedIntegerEnumQualifier.qualifiedB);
	}/**

	 **
	 * Sets the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setQualifiedB(final java.lang.Integer keyX,final KeyEnum keyY,final java.lang.String qualifiedB)
	{
		try
		{
			intEnumQualifier.set(new Object[]{this,keyX,keyY},QualifiedIntegerEnumQualifier.qualifiedB,qualifiedB);
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
	}/**

	 **
	 * The persistent type information for qualifiedItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.type public|package|protected|private|none</code> in the class comment.
	 *
 */public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(QualifiedItem.class)
;}
