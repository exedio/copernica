/*
 * Copyright (C) 2004-2007  exedio GmbH (www.exedio.com)
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

import com.exedio.cope.IntegerField;
import com.exedio.cope.Item;
import com.exedio.cope.ItemField;
import com.exedio.cope.StringField;
import com.exedio.cope.UniqueConstraint;
import com.exedio.cope.pattern.Qualifier;

/**
 * @author Ralf Wiebicke
 */
public class QualifiedStringQualifier extends Item
{
	public static final ItemField<QualifiedItem> parent = newItemField(QualifiedItem.class).toFinal();
	
	public static final StringField key = new StringField().toFinal();
	
	public static final UniqueConstraint qualifyUnique = new UniqueConstraint(parent, key);
	public static final Qualifier stringQualifier = new Qualifier(qualifyUnique);
	
	public static final IntegerField qualifiedA = new IntegerField().optional();
	public static final IntegerField qualifiedB = new IntegerField().optional();
	

/**

	 **
	 * Creates a new QualifiedStringQualifier with all the fields initially needed.
	 * @param parent the initial value for field {@link #parent}.
	 * @param key the initial value for field {@link #key}.
	 * @throws com.exedio.cope.LengthViolationException if key violates its length constraint.
	 * @throws com.exedio.cope.MandatoryViolationException if parent, key is null.
	 * @throws com.exedio.cope.UniqueViolationException if parent, key is not unique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	public QualifiedStringQualifier(
				final QualifiedItem parent,
				final java.lang.String key)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			QualifiedStringQualifier.parent.map(parent),
			QualifiedStringQualifier.key.map(key),
		});
	}/**

	 **
	 * Creates a new QualifiedStringQualifier and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private QualifiedStringQualifier(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@SuppressWarnings("unused") private QualifiedStringQualifier(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent field {@link #parent}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final QualifiedItem getParent()
	{
		return QualifiedStringQualifier.parent.get(this);
	}/**

	 **
	 * Returns the value of the persistent field {@link #key}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.String getKey()
	{
		return QualifiedStringQualifier.key.get(this);
	}/**

	 **
	 * Finds a qualifiedStringQualifier by it's unique fields.
	 * @param parent shall be equal to field {@link #parent}.
	 * @param key shall be equal to field {@link #key}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public static final QualifiedStringQualifier forQualifyUnique(final QualifiedItem parent,final java.lang.String key)
	{
		return QualifiedStringQualifier.qualifyUnique.searchUnique(QualifiedStringQualifier.class,parent,key);
	}/**

	 **
	 * Finds a qualifiedStringQualifier by it's unique fields.
	 * @deprecated use forQualifyUnique instead.
	 * @param parent shall be equal to field {@link #parent}.
	 * @param key shall be equal to field {@link #key}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@Deprecated
	public static final QualifiedStringQualifier findByQualifyUnique(final QualifiedItem parent,final java.lang.String key)
	{
		return QualifiedStringQualifier.qualifyUnique.searchUnique(QualifiedStringQualifier.class,parent,key);
	}/**

	 **
	 * Returns the value of the persistent field {@link #qualifiedA}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.Integer getQualifiedA()
	{
		return QualifiedStringQualifier.qualifiedA.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #qualifiedA}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setQualifiedA(final java.lang.Integer qualifiedA)
	{
		QualifiedStringQualifier.qualifiedA.set(this,qualifiedA);
	}/**

	 **
	 * Returns the value of the persistent field {@link #qualifiedB}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.Integer getQualifiedB()
	{
		return QualifiedStringQualifier.qualifiedB.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #qualifiedB}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setQualifiedB(final java.lang.Integer qualifiedB)
	{
		QualifiedStringQualifier.qualifiedB.set(this,qualifiedB);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for qualifiedStringQualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<QualifiedStringQualifier> TYPE = newType(QualifiedStringQualifier.class)
;}
