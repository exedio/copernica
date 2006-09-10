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

package com.exedio.cope;

import com.exedio.cope.IntegerAttribute;
import com.exedio.cope.Item;
import com.exedio.cope.StringField;
import com.exedio.cope.function.UppercaseView;

/**
 * @author Ralf Wiebicke
 */
public abstract class HierarchySuper extends Item
{
	public static final IntegerAttribute superInt = new IntegerAttribute(UNIQUE);
	
	public static final StringField superString = new StringField(OPTIONAL);

	public static final UppercaseView superStringUpper = superString.toUpperCase();
	
/**

	 **
	 * Creates a new HierarchySuper with all the attributes initially needed.
	 * @param superInt the initial value for attribute {@link #superInt}.
	 * @throws com.exedio.cope.UniqueViolationException if superInt is not unique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of attributes.
	 */
	public HierarchySuper(
				final int superInt)
			throws
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			HierarchySuper.superInt.map(superInt),
		});
	}/**

	 **
	 * Creates a new HierarchySuper and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	protected HierarchySuper(final com.exedio.cope.SetValue[] setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	protected HierarchySuper(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #superInt}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final int getSuperInt()
	{
		return HierarchySuper.superInt.getMandatory(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #superInt}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setSuperInt(final int superInt)
			throws
				com.exedio.cope.UniqueViolationException
	{
		HierarchySuper.superInt.set(this,superInt);
	}/**

	 **
	 * Finds a hierarchySuper by it's unique attributes.
	 * @param superInt shall be equal to attribute {@link #superInt}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public static final HierarchySuper findBySuperInt(final int superInt)
	{
		return (HierarchySuper)HierarchySuper.superInt.searchUnique(new java.lang.Integer(superInt));
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #superString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.String getSuperString()
	{
		return HierarchySuper.superString.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #superString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setSuperString(final java.lang.String superString)
			throws
				com.exedio.cope.LengthViolationException
	{
		HierarchySuper.superString.set(this,superString);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #superStringUpper}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.String getSuperStringUpper()
	{
		return HierarchySuper.superStringUpper.get(this);
	}/**

	 **
	 * The persistent type information for hierarchySuper.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<HierarchySuper> TYPE = newType(HierarchySuper.class)
;}
