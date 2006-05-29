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
import com.exedio.cope.testmodel.QualifiedIntegerEnumQualifier.KeyEnum;

/**
 * @author Ralf Wiebicke
 */
public class QualifiedItem extends Item
{
	public static final IntegerAttribute number = new IntegerAttribute(OPTIONAL);
	
	/**

	 **
	 * Creates a new QualifiedItem with all the attributes initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of attributes.
	 */
	public QualifiedItem()
	{
		this(new com.exedio.cope.SetValue[]{
		});
	}/**

	 **
	 * Creates a new QualifiedItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private QualifiedItem(final com.exedio.cope.SetValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private QualifiedItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #number}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.Integer getNumber()
	{
		return QualifiedItem.number.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #number}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setNumber(final java.lang.Integer number)
	{
		QualifiedItem.number.set(this,number);
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final com.exedio.cope.testmodel.QualifiedEmptyQualifier getQualifier(final EmptyItem key)
	{
		return (com.exedio.cope.testmodel.QualifiedEmptyQualifier)com.exedio.cope.testmodel.QualifiedEmptyQualifier.qualifier.getQualifier(new Object[]{this,key});
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.lang.String getQualifiedA(final EmptyItem key)
	{
		return (java.lang.String)com.exedio.cope.testmodel.QualifiedEmptyQualifier.qualifier.get(new Object[]{this,key},com.exedio.cope.testmodel.QualifiedEmptyQualifier.qualifiedA);
	}/**

	 **
	 * Sets the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setQualifiedA(final EmptyItem key,final java.lang.String qualifiedA)
			throws
				com.exedio.cope.LengthViolationException
	{
		com.exedio.cope.testmodel.QualifiedEmptyQualifier.qualifiedA.set(com.exedio.cope.testmodel.QualifiedEmptyQualifier.qualifier.getForSet(new Object[]{this,key}),qualifiedA);
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.lang.String getQualifiedB(final EmptyItem key)
	{
		return (java.lang.String)com.exedio.cope.testmodel.QualifiedEmptyQualifier.qualifier.get(new Object[]{this,key},com.exedio.cope.testmodel.QualifiedEmptyQualifier.qualifiedB);
	}/**

	 **
	 * Sets the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setQualifiedB(final EmptyItem key,final java.lang.String qualifiedB)
			throws
				com.exedio.cope.LengthViolationException
	{
		com.exedio.cope.testmodel.QualifiedEmptyQualifier.qualifiedB.set(com.exedio.cope.testmodel.QualifiedEmptyQualifier.qualifier.getForSet(new Object[]{this,key}),qualifiedB);
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final com.exedio.cope.testmodel.QualifiedIntegerEnumQualifier getIntEnumQualifier(final java.lang.Integer keyX,final KeyEnum keyY)
	{
		return (com.exedio.cope.testmodel.QualifiedIntegerEnumQualifier)com.exedio.cope.testmodel.QualifiedIntegerEnumQualifier.intEnumQualifier.getQualifier(new Object[]{this,keyX,keyY});
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.lang.String getQualifiedA(final java.lang.Integer keyX,final KeyEnum keyY)
	{
		return (java.lang.String)com.exedio.cope.testmodel.QualifiedIntegerEnumQualifier.intEnumQualifier.get(new Object[]{this,keyX,keyY},com.exedio.cope.testmodel.QualifiedIntegerEnumQualifier.qualifiedA);
	}/**

	 **
	 * Sets the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setQualifiedA(final java.lang.Integer keyX,final KeyEnum keyY,final java.lang.String qualifiedA)
			throws
				com.exedio.cope.LengthViolationException
	{
		com.exedio.cope.testmodel.QualifiedIntegerEnumQualifier.qualifiedA.set(com.exedio.cope.testmodel.QualifiedIntegerEnumQualifier.intEnumQualifier.getForSet(new Object[]{this,keyX,keyY}),qualifiedA);
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.lang.String getQualifiedB(final java.lang.Integer keyX,final KeyEnum keyY)
	{
		return (java.lang.String)com.exedio.cope.testmodel.QualifiedIntegerEnumQualifier.intEnumQualifier.get(new Object[]{this,keyX,keyY},com.exedio.cope.testmodel.QualifiedIntegerEnumQualifier.qualifiedB);
	}/**

	 **
	 * Sets the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setQualifiedB(final java.lang.Integer keyX,final KeyEnum keyY,final java.lang.String qualifiedB)
			throws
				com.exedio.cope.LengthViolationException
	{
		com.exedio.cope.testmodel.QualifiedIntegerEnumQualifier.qualifiedB.set(com.exedio.cope.testmodel.QualifiedIntegerEnumQualifier.intEnumQualifier.getForSet(new Object[]{this,keyX,keyY}),qualifiedB);
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final com.exedio.cope.testmodel.QualifiedStringQualifier getStringQualifier(final java.lang.String key)
	{
		return (com.exedio.cope.testmodel.QualifiedStringQualifier)com.exedio.cope.testmodel.QualifiedStringQualifier.stringQualifier.getQualifier(new Object[]{this,key});
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.lang.Integer getQualifiedA(final java.lang.String key)
	{
		return (java.lang.Integer)com.exedio.cope.testmodel.QualifiedStringQualifier.stringQualifier.get(new Object[]{this,key},com.exedio.cope.testmodel.QualifiedStringQualifier.qualifiedA);
	}/**

	 **
	 * Sets the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setQualifiedA(final java.lang.String key,final java.lang.Integer qualifiedA)
	{
		com.exedio.cope.testmodel.QualifiedStringQualifier.qualifiedA.set(com.exedio.cope.testmodel.QualifiedStringQualifier.stringQualifier.getForSet(new Object[]{this,key}),qualifiedA);
	}/**

	 **
	 * Returns the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.lang.Integer getQualifiedB(final java.lang.String key)
	{
		return (java.lang.Integer)com.exedio.cope.testmodel.QualifiedStringQualifier.stringQualifier.get(new Object[]{this,key},com.exedio.cope.testmodel.QualifiedStringQualifier.qualifiedB);
	}/**

	 **
	 * Sets the qualifier.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setQualifiedB(final java.lang.String key,final java.lang.Integer qualifiedB)
	{
		com.exedio.cope.testmodel.QualifiedStringQualifier.qualifiedB.set(com.exedio.cope.testmodel.QualifiedStringQualifier.stringQualifier.getForSet(new Object[]{this,key}),qualifiedB);
	}/**

	 **
	 * The persistent type information for qualifiedItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<QualifiedItem> TYPE = newType(QualifiedItem.class)
;}
