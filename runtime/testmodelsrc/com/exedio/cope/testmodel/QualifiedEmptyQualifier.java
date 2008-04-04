/*
 * Copyright (C) 2004-2008  exedio GmbH (www.exedio.com)
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
import com.exedio.cope.ItemField;
import com.exedio.cope.StringField;
import com.exedio.cope.UniqueConstraint;
import com.exedio.cope.pattern.Qualifier;

/**
 * @author Ralf Wiebicke
 */
public class QualifiedEmptyQualifier extends Item
{
	public static final ItemField<QualifiedItem> parent = newItemField(QualifiedItem.class, CASCADE).toFinal();
	
	public static final ItemField<EmptyItem> key = newItemField(EmptyItem.class).toFinal();
	
	public static final UniqueConstraint qualifyUnique = new UniqueConstraint(parent, key);
	@Deprecated() // OK: test deprecated api
	public static final Qualifier qualifier = new Qualifier(qualifyUnique);
	
	public static final StringField qualifiedA = new StringField().optional();
	public static final StringField qualifiedB = new StringField().optional();
	

/**

	 **
	 * Creates a new QualifiedEmptyQualifier with all the fields initially needed.
	 * @param parent the initial value for field {@link #parent}.
	 * @param key the initial value for field {@link #key}.
	 * @throws com.exedio.cope.MandatoryViolationException if parent, key is null.
	 * @throws com.exedio.cope.UniqueViolationException if parent, key is not unique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	public QualifiedEmptyQualifier(
				final com.exedio.cope.testmodel.QualifiedItem parent,
				final com.exedio.cope.testmodel.EmptyItem key)
			throws
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			QualifiedEmptyQualifier.parent.map(parent),
			QualifiedEmptyQualifier.key.map(key),
		});
	}/**

	 **
	 * Creates a new QualifiedEmptyQualifier and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private QualifiedEmptyQualifier(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@SuppressWarnings("unused") private QualifiedEmptyQualifier(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of {@link #parent}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final com.exedio.cope.testmodel.QualifiedItem getParent()
	{
		return QualifiedEmptyQualifier.parent.get(this);
	}/**

	 **
	 * Returns the value of {@link #key}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final com.exedio.cope.testmodel.EmptyItem getKey()
	{
		return QualifiedEmptyQualifier.key.get(this);
	}/**

	 **
	 * Finds a qualifiedEmptyQualifier by it's unique fields.
	 * @param parent shall be equal to field {@link #parent}.
	 * @param key shall be equal to field {@link #key}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public static final QualifiedEmptyQualifier forQualifyUnique(final QualifiedItem parent,final EmptyItem key)
	{
		return QualifiedEmptyQualifier.qualifyUnique.searchUnique(QualifiedEmptyQualifier.class,parent,key);
	}/**

	 **
	 * Finds a qualifiedEmptyQualifier by it's unique fields.
	 * @deprecated use forQualifyUnique instead.
	 * @param parent shall be equal to field {@link #parent}.
	 * @param key shall be equal to field {@link #key}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@Deprecated
	public static final QualifiedEmptyQualifier findByQualifyUnique(final QualifiedItem parent,final EmptyItem key)
	{
		return QualifiedEmptyQualifier.qualifyUnique.searchUnique(QualifiedEmptyQualifier.class,parent,key);
	}/**

	 **
	 * Returns the value of {@link #qualifiedA}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.String getQualifiedA()
	{
		return QualifiedEmptyQualifier.qualifiedA.get(this);
	}/**

	 **
	 * Sets a new value for {@link #qualifiedA}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setQualifiedA(final java.lang.String qualifiedA)
			throws
				com.exedio.cope.LengthViolationException
	{
		QualifiedEmptyQualifier.qualifiedA.set(this,qualifiedA);
	}/**

	 **
	 * Returns the value of {@link #qualifiedB}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.String getQualifiedB()
	{
		return QualifiedEmptyQualifier.qualifiedB.get(this);
	}/**

	 **
	 * Sets a new value for {@link #qualifiedB}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setQualifiedB(final java.lang.String qualifiedB)
			throws
				com.exedio.cope.LengthViolationException
	{
		QualifiedEmptyQualifier.qualifiedB.set(this,qualifiedB);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for qualifiedEmptyQualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<QualifiedEmptyQualifier> TYPE = newType(QualifiedEmptyQualifier.class)
;}
