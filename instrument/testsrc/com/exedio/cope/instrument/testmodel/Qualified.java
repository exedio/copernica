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

import com.exedio.cope.Item;
import com.exedio.cope.pattern.Qualifier;

/**
 * @cope.persistent
 */
public class Qualified extends Item
{
	public static final Qualifier nameQualifier = new Qualifier(QualifiedName.parentKey);
/**

	 **
	 * Creates a new Qualified with all the attributes initially needed.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public Qualified()
	{
		this(new com.exedio.cope.AttributeValue[]{
		});
	}/**

	 **
	 * Creates a new Qualified and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */private Qualified(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 *
 */private Qualified(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final QualifiedName getNameQualifier(final java.lang.String key)
	{
		return (QualifiedName)nameQualifier.getQualifier(new Object[]{this,key});
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.Integer getNumber(final java.lang.String key)
	{
		return (java.lang.Integer)nameQualifier.get(new Object[]{this,key},QualifiedName.number);
	}/**

	 **
	 * Sets the qualifier.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setNumber(final java.lang.String key,final java.lang.Integer number)
			throws
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.ReadOnlyViolationException,
				java.lang.ClassCastException
	{
		nameQualifier.set(new Object[]{this,key},QualifiedName.number,number);
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.Integer getOptionalNumber(final java.lang.String key)
	{
		return (java.lang.Integer)nameQualifier.get(new Object[]{this,key},QualifiedName.optionalNumber);
	}/**

	 **
	 * Sets the qualifier.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setOptionalNumber(final java.lang.String key,final java.lang.Integer optionalNumber)
			throws
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.ReadOnlyViolationException,
				java.lang.ClassCastException
	{
		nameQualifier.set(new Object[]{this,key},QualifiedName.optionalNumber,optionalNumber);
	}/**

	 **
	 * The persistent type information for qualified.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(Qualified.class)
;}
