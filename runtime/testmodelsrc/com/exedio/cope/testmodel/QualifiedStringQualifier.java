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

import com.exedio.cope.IntegerAttribute;
import com.exedio.cope.Item;
import com.exedio.cope.ItemAttribute;
import com.exedio.cope.StringAttribute;
import com.exedio.cope.UniqueConstraint;

/**
 * @cope.persistent
 * @author Ralf Wiebicke
 */
public class QualifiedStringQualifier extends Item
{
	public static final ItemAttribute<QualifiedItem> parent = new ItemAttribute<QualifiedItem>(FINAL);
	
	public static final StringAttribute key = new StringAttribute(FINAL);
	
	public static final UniqueConstraint qualifyUnique = new UniqueConstraint(parent, key);
	
	public static final IntegerAttribute qualifiedA = new IntegerAttribute(OPTIONAL);
	public static final IntegerAttribute qualifiedB = new IntegerAttribute(OPTIONAL);
	

/**

	 **
	 * Creates a new QualifiedStringQualifier with all the attributes initially needed.
	 * @param parent the initial value for attribute {@link #parent}.
	 * @param key the initial value for attribute {@link #key}.
	 * @throws com.exedio.cope.LengthViolationException if key violates its length constraint.
	 * @throws com.exedio.cope.MandatoryViolationException if parent, key is null.
	 * @throws com.exedio.cope.UniqueViolationException if parent, key is not unique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <code>@cope.constructor public|package|protected|private|none</code> in the class comment and <code>@cope.initial</code> in the comment of attributes.
	 */
	public QualifiedStringQualifier(
				final QualifiedItem parent,
				final java.lang.String key)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.AttributeValue[]{
			QualifiedStringQualifier.parent.map(parent),
			QualifiedStringQualifier.key.map(key),
		});
	}/**

	 **
	 * Creates a new QualifiedStringQualifier and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.generic.constructor public|package|protected|private|none</code> in the class comment.
	 */
	private QualifiedStringQualifier(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private QualifiedStringQualifier(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #parent}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final QualifiedItem getParent()
	{
		return QualifiedStringQualifier.parent.get(this);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #key}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.String getKey()
	{
		return QualifiedStringQualifier.key.get(this);
	}/**

	 **
	 * Finds a qualifiedStringQualifier by it's unique attributes.
	 * @param parent shall be equal to attribute {@link #parent}.
	 * @param key shall be equal to attribute {@link #key}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public static final QualifiedStringQualifier findByQualifyUnique(final QualifiedItem parent,final java.lang.String key)
	{
		return (QualifiedStringQualifier)QualifiedStringQualifier.qualifyUnique.searchUnique(new Object[]{parent,key});
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #qualifiedA}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.Integer getQualifiedA()
	{
		return QualifiedStringQualifier.qualifiedA.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #qualifiedA}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setQualifiedA(final java.lang.Integer qualifiedA)
	{
		QualifiedStringQualifier.qualifiedA.set(this,qualifiedA);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #qualifiedB}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.Integer getQualifiedB()
	{
		return QualifiedStringQualifier.qualifiedB.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #qualifiedB}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setQualifiedB(final java.lang.Integer qualifiedB)
	{
		QualifiedStringQualifier.qualifiedB.set(this,qualifiedB);
	}/**

	 **
	 * The persistent type information for qualifiedStringQualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.type public|package|protected|private|none</code> in the class comment.
	 */
	public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(QualifiedStringQualifier.class)
;}
