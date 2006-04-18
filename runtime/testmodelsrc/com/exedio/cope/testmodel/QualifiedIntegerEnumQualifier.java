/*
 * Copyright (C) 2004-2006  exedio GmbH (www.exedio.com)
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
import com.exedio.cope.IntegerAttribute;
import com.exedio.cope.Item;
import com.exedio.cope.ItemAttribute;
import com.exedio.cope.StringAttribute;
import com.exedio.cope.UniqueConstraint;

/**
 * @author Ralf Wiebicke
 */
public class QualifiedIntegerEnumQualifier extends Item
{
	public static final ItemAttribute<QualifiedItem> up = new ItemAttribute<QualifiedItem>(FINAL, CASCADE);
	
	public static final IntegerAttribute keyX = new IntegerAttribute(FINAL);
	public static final EnumAttribute<KeyEnum> keyY = new EnumAttribute<KeyEnum>(FINAL);
	
	public static final UniqueConstraint qualifyUnique = new UniqueConstraint(up, keyX, keyY);
	
	public static final StringAttribute qualifiedA = new StringAttribute(OPTIONAL);
	public static final StringAttribute qualifiedB = new StringAttribute(OPTIONAL);

	public static enum KeyEnum
	{
		key1,
		key2,
		key3;
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
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of attributes.
	 */
	public QualifiedIntegerEnumQualifier(
				final QualifiedItem up,
				final int keyX,
				final KeyEnum keyY)
			throws
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			QualifiedIntegerEnumQualifier.up.map(up),
			QualifiedIntegerEnumQualifier.keyX.map(keyX),
			QualifiedIntegerEnumQualifier.keyY.map(keyY),
		});
	}/**

	 **
	 * Creates a new QualifiedIntegerEnumQualifier and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private QualifiedIntegerEnumQualifier(final com.exedio.cope.SetValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private QualifiedIntegerEnumQualifier(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #up}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final QualifiedItem getUp()
	{
		return QualifiedIntegerEnumQualifier.up.get(this);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #keyX}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final int getKeyX()
	{
		return QualifiedIntegerEnumQualifier.keyX.getMandatory(this);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #keyY}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final KeyEnum getKeyY()
	{
		return QualifiedIntegerEnumQualifier.keyY.get(this);
	}/**

	 **
	 * Finds a qualifiedIntegerEnumQualifier by it's unique attributes.
	 * @param up shall be equal to attribute {@link #up}.
	 * @param keyX shall be equal to attribute {@link #keyX}.
	 * @param keyY shall be equal to attribute {@link #keyY}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public static final QualifiedIntegerEnumQualifier findByQualifyUnique(final QualifiedItem up,final int keyX,final KeyEnum keyY)
	{
		return (QualifiedIntegerEnumQualifier)QualifiedIntegerEnumQualifier.qualifyUnique.searchUnique(new Object[]{up,new java.lang.Integer(keyX),keyY});
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #qualifiedA}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.String getQualifiedA()
	{
		return QualifiedIntegerEnumQualifier.qualifiedA.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #qualifiedA}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setQualifiedA(final java.lang.String qualifiedA)
			throws
				com.exedio.cope.LengthViolationException
	{
		QualifiedIntegerEnumQualifier.qualifiedA.set(this,qualifiedA);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #qualifiedB}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.String getQualifiedB()
	{
		return QualifiedIntegerEnumQualifier.qualifiedB.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #qualifiedB}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setQualifiedB(final java.lang.String qualifiedB)
			throws
				com.exedio.cope.LengthViolationException
	{
		QualifiedIntegerEnumQualifier.qualifiedB.set(this,qualifiedB);
	}/**

	 **
	 * The persistent type information for qualifiedIntegerEnumQualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(QualifiedIntegerEnumQualifier.class)
;}
