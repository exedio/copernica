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

import com.exedio.cope.IntegerAttribute;
import com.exedio.cope.Item;
import com.exedio.cope.ItemAttribute;
import com.exedio.cope.StringAttribute;
import com.exedio.cope.UniqueConstraint;

/**
 * @cope.persistent
 */
public class QualifiedName extends Item
{
	public static final ItemAttribute parent = new ItemAttribute(OPTIONAL, Qualified.class, CASCADE);
	public static final StringAttribute key = new StringAttribute(OPTIONAL);
	public static final UniqueConstraint parentKey = new UniqueConstraint(parent, key);

	public static final IntegerAttribute number = new IntegerAttribute(MANDATORY);
	public static final IntegerAttribute optionalNumber = new IntegerAttribute(OPTIONAL);
	
	/**
	 * @cope.getter none
	 */
	public static final IntegerAttribute noneGetterNumber = new IntegerAttribute(MANDATORY);

	/**
	 * @cope.getter private
	 */
	public static final IntegerAttribute privateGetterNumber = new IntegerAttribute(MANDATORY);

	/**
	 * @cope.getter internal
	 */
	public static final IntegerAttribute internalGetterNumber = new IntegerAttribute(MANDATORY);
	
	/**
	 * @cope.setter none
	 */
	public static final IntegerAttribute noneSetterNumber = new IntegerAttribute(MANDATORY);

	/**
	 * @cope.setter private
	 */
	public static final IntegerAttribute privateSetterNumber = new IntegerAttribute(MANDATORY);

	/**
	 * @cope.setter internal
	 */
	public static final IntegerAttribute internalSetterNumber = new IntegerAttribute(MANDATORY);
	

/**

	 **
	 * Creates a new QualifiedName with all the attributes initially needed.
	 * @param number the initial value for attribute {@link #number}.
	 * @param noneGetterNumber the initial value for attribute {@link #noneGetterNumber}.
	 * @param privateGetterNumber the initial value for attribute {@link #privateGetterNumber}.
	 * @param internalGetterNumber the initial value for attribute {@link #internalGetterNumber}.
	 * @param noneSetterNumber the initial value for attribute {@link #noneSetterNumber}.
	 * @param privateSetterNumber the initial value for attribute {@link #privateSetterNumber}.
	 * @param internalSetterNumber the initial value for attribute {@link #internalSetterNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <code>@cope.constructor public|package|protected|private|none</code> in the class comment and <code>@cope.initial</code> in the comment of attributes.
	 *
 */public QualifiedName(
				final int number,
				final int noneGetterNumber,
				final int privateGetterNumber,
				final int internalGetterNumber,
				final int noneSetterNumber,
				final int privateSetterNumber,
				final int internalSetterNumber)
	{
		this(new com.exedio.cope.AttributeValue[]{
			new com.exedio.cope.AttributeValue(QualifiedName.number,new java.lang.Integer(number)),
			new com.exedio.cope.AttributeValue(QualifiedName.noneGetterNumber,new java.lang.Integer(noneGetterNumber)),
			new com.exedio.cope.AttributeValue(QualifiedName.privateGetterNumber,new java.lang.Integer(privateGetterNumber)),
			new com.exedio.cope.AttributeValue(QualifiedName.internalGetterNumber,new java.lang.Integer(internalGetterNumber)),
			new com.exedio.cope.AttributeValue(QualifiedName.noneSetterNumber,new java.lang.Integer(noneSetterNumber)),
			new com.exedio.cope.AttributeValue(QualifiedName.privateSetterNumber,new java.lang.Integer(privateSetterNumber)),
			new com.exedio.cope.AttributeValue(QualifiedName.internalSetterNumber,new java.lang.Integer(internalSetterNumber)),
		});
	}/**

	 **
	 * Creates a new QualifiedName and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.generic.constructor public|package|protected|private|none</code> in the class comment.
	 *
 */private QualifiedName(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */private QualifiedName(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #parent}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 *
 */public final Qualified getParent()
	{
		return (Qualified)QualifiedName.parent.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #parent}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 *
 */public final void setParent(final Qualified parent)
			throws
				com.exedio.cope.UniqueViolationException
	{
		try
		{
			QualifiedName.parent.set(this,parent);
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
	 * Returns the value of the persistent attribute {@link #key}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 *
 */public final java.lang.String getKey()
	{
		return QualifiedName.key.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #key}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 *
 */public final void setKey(final java.lang.String key)
			throws
				com.exedio.cope.UniqueViolationException
	{
		try
		{
			QualifiedName.key.set(this,key);
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
	 * Returns the value of the persistent attribute {@link #number}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 *
 */public final int getNumber()
	{
		return (QualifiedName.number.get(this)).intValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #number}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 *
 */public final void setNumber(final int number)
	{
		try
		{
			QualifiedName.number.set(this,new java.lang.Integer(number));
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
	 * Returns the value of the persistent attribute {@link #optionalNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 *
 */public final java.lang.Integer getOptionalNumber()
	{
		return QualifiedName.optionalNumber.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #optionalNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 *
 */public final void setOptionalNumber(final java.lang.Integer optionalNumber)
	{
		try
		{
			QualifiedName.optionalNumber.set(this,optionalNumber);
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
	 * Sets a new value for the persistent attribute {@link #noneGetterNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 *
 */public final void setNoneGetterNumber(final int noneGetterNumber)
	{
		try
		{
			QualifiedName.noneGetterNumber.set(this,new java.lang.Integer(noneGetterNumber));
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
	 * Returns the value of the persistent attribute {@link #privateGetterNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 *
 */private final int getPrivateGetterNumber()
	{
		return (QualifiedName.privateGetterNumber.get(this)).intValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #privateGetterNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 *
 */public final void setPrivateGetterNumber(final int privateGetterNumber)
	{
		try
		{
			QualifiedName.privateGetterNumber.set(this,new java.lang.Integer(privateGetterNumber));
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
	 * Returns the value of the persistent attribute {@link #internalGetterNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 *
 */private final int getInternalGetterNumberInternal()
	{
		return (QualifiedName.internalGetterNumber.get(this)).intValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #internalGetterNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 *
 */public final void setInternalGetterNumber(final int internalGetterNumber)
	{
		try
		{
			QualifiedName.internalGetterNumber.set(this,new java.lang.Integer(internalGetterNumber));
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
	 * Returns the value of the persistent attribute {@link #noneSetterNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 *
 */public final int getNoneSetterNumber()
	{
		return (QualifiedName.noneSetterNumber.get(this)).intValue();
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #privateSetterNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 *
 */public final int getPrivateSetterNumber()
	{
		return (QualifiedName.privateSetterNumber.get(this)).intValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #privateSetterNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 *
 */private final void setPrivateSetterNumber(final int privateSetterNumber)
	{
		try
		{
			QualifiedName.privateSetterNumber.set(this,new java.lang.Integer(privateSetterNumber));
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
	 * Returns the value of the persistent attribute {@link #internalSetterNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 *
 */public final int getInternalSetterNumber()
	{
		return (QualifiedName.internalSetterNumber.get(this)).intValue();
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #internalSetterNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 *
 */private final void setInternalSetterNumberInternal(final int internalSetterNumber)
	{
		try
		{
			QualifiedName.internalSetterNumber.set(this,new java.lang.Integer(internalSetterNumber));
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
	 * Finds a qualifiedName by it's unique attributes.
	 * @param parent shall be equal to attribute {@link #parent}.
	 * @param key shall be equal to attribute {@link #key}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public static final QualifiedName findByParentKey(final Qualified parent,final java.lang.String key)
	{
		return (QualifiedName)QualifiedName.parentKey.searchUnique(new Object[]{parent,key});
	}/**

	 **
	 * The persistent type information for qualifiedName.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.type public|package|protected|private|none</code> in the class comment.
	 *
 */public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(QualifiedName.class)
;}
