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

package com.exedio.cope.instrument.testmodel;

import com.exedio.cope.Item;
import com.exedio.cope.pattern.Qualifier;

/**
 */
public class Qualified extends Item
{
	public static final Qualifier nameQualifier = new Qualifier(QualifiedName.parentKey);
/**

	 **
	 * Creates a new Qualified with all the attributes initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of attributes.
	 */
	public Qualified()
	{
		this(new com.exedio.cope.SetValue[]{
		});
	}/**

	 **
	 * Creates a new Qualified and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private Qualified(final com.exedio.cope.SetValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private Qualified(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final QualifiedName getNameQualifier(final java.lang.String key)
	{
		return (QualifiedName)nameQualifier.getQualifier(new Object[]{this,key});
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.lang.Integer getNumber(final java.lang.String key)
	{
		return (java.lang.Integer)nameQualifier.get(new Object[]{this,key},QualifiedName.number);
	}/**

	 **
	 * Sets the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setNumber(final java.lang.String key,final int number)
	{
		QualifiedName.number.set(nameQualifier.getForSet(new Object[]{this,key}),number);
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.lang.Integer getOptionalNumber(final java.lang.String key)
	{
		return (java.lang.Integer)nameQualifier.get(new Object[]{this,key},QualifiedName.optionalNumber);
	}/**

	 **
	 * Sets the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setOptionalNumber(final java.lang.String key,final java.lang.Integer optionalNumber)
	{
		QualifiedName.optionalNumber.set(nameQualifier.getForSet(new Object[]{this,key}),optionalNumber);
	}/**

	 **
	 * Sets the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setNoneGetterNumber(final java.lang.String key,final int noneGetterNumber)
	{
		QualifiedName.noneGetterNumber.set(nameQualifier.getForSet(new Object[]{this,key}),noneGetterNumber);
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private final java.lang.Integer getPrivateGetterNumber(final java.lang.String key)
	{
		return (java.lang.Integer)nameQualifier.get(new Object[]{this,key},QualifiedName.privateGetterNumber);
	}/**

	 **
	 * Sets the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setPrivateGetterNumber(final java.lang.String key,final int privateGetterNumber)
	{
		QualifiedName.privateGetterNumber.set(nameQualifier.getForSet(new Object[]{this,key}),privateGetterNumber);
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private final java.lang.Integer getInternalGetterNumberInternal(final java.lang.String key)
	{
		return (java.lang.Integer)nameQualifier.get(new Object[]{this,key},QualifiedName.internalGetterNumber);
	}/**

	 **
	 * Sets the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setInternalGetterNumber(final java.lang.String key,final int internalGetterNumber)
	{
		QualifiedName.internalGetterNumber.set(nameQualifier.getForSet(new Object[]{this,key}),internalGetterNumber);
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.lang.Integer getNoneSetterNumber(final java.lang.String key)
	{
		return (java.lang.Integer)nameQualifier.get(new Object[]{this,key},QualifiedName.noneSetterNumber);
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.lang.Integer getPrivateSetterNumber(final java.lang.String key)
	{
		return (java.lang.Integer)nameQualifier.get(new Object[]{this,key},QualifiedName.privateSetterNumber);
	}/**

	 **
	 * Sets the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private final void setPrivateSetterNumber(final java.lang.String key,final int privateSetterNumber)
	{
		QualifiedName.privateSetterNumber.set(nameQualifier.getForSet(new Object[]{this,key}),privateSetterNumber);
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.lang.Integer getInternalSetterNumber(final java.lang.String key)
	{
		return (java.lang.Integer)nameQualifier.get(new Object[]{this,key},QualifiedName.internalSetterNumber);
	}/**

	 **
	 * Sets the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private final void setInternalSetterNumberInternal(final java.lang.String key,final int internalSetterNumber)
	{
		QualifiedName.internalSetterNumber.set(nameQualifier.getForSet(new Object[]{this,key}),internalSetterNumber);
	}/**

	 **
	 * The persistent type information for qualified.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<Qualified> TYPE =
		newType(Qualified.class)
;}
