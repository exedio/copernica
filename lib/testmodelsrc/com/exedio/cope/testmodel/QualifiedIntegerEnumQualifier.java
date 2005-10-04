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

import com.exedio.cope.EnumAttribute;
import com.exedio.cope.EnumValue;
import com.exedio.cope.IntegerAttribute;
import com.exedio.cope.Item;
import com.exedio.cope.ItemAttribute;
import com.exedio.cope.StringAttribute;
import com.exedio.cope.UniqueConstraint;

/**
 * @cope.persistent
 * @author Ralf Wiebicke
 */
public class QualifiedIntegerEnumQualifier extends Item
{
	public static final ItemAttribute up = new ItemAttribute(READ_ONLY, QualifiedItem.class, CASCADE);
	
	public static final IntegerAttribute keyX = new IntegerAttribute(READ_ONLY);
	public static final EnumAttribute keyY = new EnumAttribute(READ_ONLY, KeyEnum.class);
	
	public static final UniqueConstraint qualifyUnique = new UniqueConstraint(up, keyX, keyY);
	
	public static final StringAttribute qualifiedA = new StringAttribute(OPTIONAL);
	public static final StringAttribute qualifiedB = new StringAttribute(OPTIONAL);

	public static final class KeyEnum extends EnumValue
	{
		public static final int key1NUM = 100;
		public static final KeyEnum key1 = new KeyEnum();

		public static final int key2NUM = 200;
		public static final KeyEnum key2 = new KeyEnum();

		public static final int key3NUM = 300;
		public static final KeyEnum key3 = new KeyEnum();
	}
/**

	 **
	 * Creates a new QualifiedIntegerEnumQualifier with all the attributes initially needed.
	 * @param up the initial value for attribute {@link #up}.
	 * @param keyX the initial value for attribute {@link #keyX}.
	 * @param keyY the initial value for attribute {@link #keyY}.
	 * @throws com.exedio.cope.MandatoryViolationException if up, keyY is null.
	 * @throws com.exedio.cope.UniqueViolationException if up, keyX, keyY is not unique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public QualifiedIntegerEnumQualifier(
				final QualifiedItem up,
				final int keyX,
				final KeyEnum keyY)
			throws
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.AttributeValue[]{
			new com.exedio.cope.AttributeValue(QualifiedIntegerEnumQualifier.up,up),
			new com.exedio.cope.AttributeValue(QualifiedIntegerEnumQualifier.keyX,new java.lang.Integer(keyX)),
			new com.exedio.cope.AttributeValue(QualifiedIntegerEnumQualifier.keyY,keyY),
		});
		throwInitialMandatoryViolationException();
		throwInitialUniqueViolationException();
	}/**

	 **
	 * Creates a new QualifiedIntegerEnumQualifier and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */private QualifiedIntegerEnumQualifier(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */private QualifiedIntegerEnumQualifier(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #up}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none</code> in the comment of the attribute.
	 *
 */public final QualifiedItem getUp()
	{
		return (QualifiedItem)get(QualifiedIntegerEnumQualifier.up);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #keyX}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none</code> in the comment of the attribute.
	 *
 */public final int getKeyX()
	{
		return ((java.lang.Integer)get(QualifiedIntegerEnumQualifier.keyX)).intValue();
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #keyY}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none</code> in the comment of the attribute.
	 *
 */public final KeyEnum getKeyY()
	{
		return (KeyEnum)get(QualifiedIntegerEnumQualifier.keyY);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #qualifiedA}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none</code> in the comment of the attribute.
	 *
 */public final java.lang.String getQualifiedA()
	{
		return (java.lang.String)get(QualifiedIntegerEnumQualifier.qualifiedA);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #qualifiedA}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setQualifiedA(final java.lang.String qualifiedA)
	{
		try
		{
			set(QualifiedIntegerEnumQualifier.qualifiedA,qualifiedA);
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
	 * Returns the value of the persistent attribute {@link #qualifiedB}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none</code> in the comment of the attribute.
	 *
 */public final java.lang.String getQualifiedB()
	{
		return (java.lang.String)get(QualifiedIntegerEnumQualifier.qualifiedB);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #qualifiedB}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setQualifiedB(final java.lang.String qualifiedB)
	{
		try
		{
			set(QualifiedIntegerEnumQualifier.qualifiedB,qualifiedB);
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
	 * Finds a qualifiedIntegerEnumQualifier by it's unique attributes.
	 * @param up shall be equal to attribute {@link #up}.
	 * @param keyX shall be equal to attribute {@link #keyX}.
	 * @param keyY shall be equal to attribute {@link #keyY}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public static final QualifiedIntegerEnumQualifier findByQualifyUnique(final QualifiedItem up,final int keyX,final KeyEnum keyY)
	{
		return (QualifiedIntegerEnumQualifier)QualifiedIntegerEnumQualifier.qualifyUnique.searchUnique(new Object[]{up,new java.lang.Integer(keyX),keyY});
	}/**

	 **
	 * The persistent type information for qualifiedIntegerEnumQualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.type public|package|protected|private|none</code> in the class comment.
	 *
 */public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(QualifiedIntegerEnumQualifier.class)
;}
