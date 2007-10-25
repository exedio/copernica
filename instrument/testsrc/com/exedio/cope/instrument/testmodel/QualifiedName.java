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

package com.exedio.cope.instrument.testmodel;

import com.exedio.cope.IntegerField;
import com.exedio.cope.Item;
import com.exedio.cope.ItemField;
import com.exedio.cope.StringField;
import com.exedio.cope.UniqueConstraint;
import com.exedio.cope.pattern.Qualifier;

public class QualifiedName extends Item
{
	public static final ItemField<Qualified> parent = newItemField(Qualified.class, CASCADE).optional();
	public static final StringField key = new StringField().optional();
	public static final UniqueConstraint parentKey = new UniqueConstraint(parent, key);
	public static final Qualifier nameQualifier = new Qualifier(parentKey);

	public static final IntegerField number = new IntegerField();
	public static final IntegerField optionalNumber = new IntegerField().optional();
	
	/**
	 * @cope.getter none
	 */
	public static final IntegerField noneGetterNumber = new IntegerField();

	/**
	 * @cope.getter private
	 */
	public static final IntegerField privateGetterNumber = new IntegerField();

	/**
	 * @cope.getter internal
	 */
	public static final IntegerField internalGetterNumber = new IntegerField();
	
	/**
	 * @cope.setter none
	 */
	public static final IntegerField noneSetterNumber = new IntegerField();

	/**
	 * @cope.setter private
	 */
	public static final IntegerField privateSetterNumber = new IntegerField();

	/**
	 * @cope.setter internal
	 */
	public static final IntegerField internalSetterNumber = new IntegerField();
	
	void useFeaturesToAvoidWarning()
	{
		getInternalGetterNumberInternal();
		getPrivateGetterNumber();
		setInternalSetterNumberInternal(0);
		setPrivateSetterNumber(0);
	}
	

	/**

	 **
	 * Creates a new QualifiedName with all the fields initially needed.
	 * @param number the initial value for field {@link #number}.
	 * @param noneGetterNumber the initial value for field {@link #noneGetterNumber}.
	 * @param privateGetterNumber the initial value for field {@link #privateGetterNumber}.
	 * @param internalGetterNumber the initial value for field {@link #internalGetterNumber}.
	 * @param noneSetterNumber the initial value for field {@link #noneSetterNumber}.
	 * @param privateSetterNumber the initial value for field {@link #privateSetterNumber}.
	 * @param internalSetterNumber the initial value for field {@link #internalSetterNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	public QualifiedName(
				final int number,
				final int noneGetterNumber,
				final int privateGetterNumber,
				final int internalGetterNumber,
				final int noneSetterNumber,
				final int privateSetterNumber,
				final int internalSetterNumber)
	{
		this(new com.exedio.cope.SetValue[]{
			QualifiedName.number.map(number),
			QualifiedName.noneGetterNumber.map(noneGetterNumber),
			QualifiedName.privateGetterNumber.map(privateGetterNumber),
			QualifiedName.internalGetterNumber.map(internalGetterNumber),
			QualifiedName.noneSetterNumber.map(noneSetterNumber),
			QualifiedName.privateSetterNumber.map(privateSetterNumber),
			QualifiedName.internalSetterNumber.map(internalSetterNumber),
		});
	}/**

	 **
	 * Creates a new QualifiedName and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private QualifiedName(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@SuppressWarnings("unused") private QualifiedName(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent field {@link #parent}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final Qualified getParent()
	{
		return QualifiedName.parent.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #parent}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setParent(final Qualified parent)
			throws
				com.exedio.cope.UniqueViolationException
	{
		QualifiedName.parent.set(this,parent);
	}/**

	 **
	 * Returns the value of the persistent field {@link #key}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.String getKey()
	{
		return QualifiedName.key.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #key}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setKey(final java.lang.String key)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.UniqueViolationException
	{
		QualifiedName.key.set(this,key);
	}/**

	 **
	 * Finds a qualifiedName by it's unique fields.
	 * @param parent shall be equal to field {@link #parent}.
	 * @param key shall be equal to field {@link #key}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public static final QualifiedName findByParentKey(final Qualified parent,final java.lang.String key)
	{
		return QualifiedName.parentKey.searchUnique(QualifiedName.class,parent,key);
	}/**

	 **
	 * Returns the value of the persistent field {@link #number}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final int getNumber()
	{
		return QualifiedName.number.getMandatory(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #number}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setNumber(final int number)
	{
		QualifiedName.number.set(this,number);
	}/**

	 **
	 * Returns the value of the persistent field {@link #optionalNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.Integer getOptionalNumber()
	{
		return QualifiedName.optionalNumber.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #optionalNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setOptionalNumber(final java.lang.Integer optionalNumber)
	{
		QualifiedName.optionalNumber.set(this,optionalNumber);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #noneGetterNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setNoneGetterNumber(final int noneGetterNumber)
	{
		QualifiedName.noneGetterNumber.set(this,noneGetterNumber);
	}/**

	 **
	 * Returns the value of the persistent field {@link #privateGetterNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	private final int getPrivateGetterNumber()
	{
		return QualifiedName.privateGetterNumber.getMandatory(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #privateGetterNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setPrivateGetterNumber(final int privateGetterNumber)
	{
		QualifiedName.privateGetterNumber.set(this,privateGetterNumber);
	}/**

	 **
	 * Returns the value of the persistent field {@link #internalGetterNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	private final int getInternalGetterNumberInternal()
	{
		return QualifiedName.internalGetterNumber.getMandatory(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #internalGetterNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setInternalGetterNumber(final int internalGetterNumber)
	{
		QualifiedName.internalGetterNumber.set(this,internalGetterNumber);
	}/**

	 **
	 * Returns the value of the persistent field {@link #noneSetterNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final int getNoneSetterNumber()
	{
		return QualifiedName.noneSetterNumber.getMandatory(this);
	}/**

	 **
	 * Returns the value of the persistent field {@link #privateSetterNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final int getPrivateSetterNumber()
	{
		return QualifiedName.privateSetterNumber.getMandatory(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #privateSetterNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	private final void setPrivateSetterNumber(final int privateSetterNumber)
	{
		QualifiedName.privateSetterNumber.set(this,privateSetterNumber);
	}/**

	 **
	 * Returns the value of the persistent field {@link #internalSetterNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final int getInternalSetterNumber()
	{
		return QualifiedName.internalSetterNumber.getMandatory(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #internalSetterNumber}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	private final void setInternalSetterNumberInternal(final int internalSetterNumber)
	{
		QualifiedName.internalSetterNumber.set(this,internalSetterNumber);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for qualifiedName.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<QualifiedName> TYPE = newType(QualifiedName.class)
;}
