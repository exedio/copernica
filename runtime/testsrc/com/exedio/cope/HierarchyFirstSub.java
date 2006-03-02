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

import com.exedio.cope.Item;
import com.exedio.cope.StringAttribute;
import com.exedio.cope.function.UppercaseView;

/**
 * @cope.persistent
 * @author Ralf Wiebicke
 */
public class HierarchyFirstSub extends HierarchySuper
{

	public static final StringAttribute firstSubString = new StringAttribute(UNIQUE_OPTIONAL);
	
	public static final UppercaseView firstSubStringUpper = firstSubString.uppercase();
	
	
/**

	 **
	 * Creates a new HierarchyFirstSub with all the attributes initially needed.
	 * @param superInt the initial value for attribute {@link #superInt}.
	 * @throws com.exedio.cope.UniqueViolationException if superInt is not unique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <code>@cope.constructor public|package|protected|private|none</code> in the class comment and <code>@cope.initial</code> in the comment of attributes.
	 */
	public HierarchyFirstSub(
				final int superInt)
			throws
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.AttributeValue[]{
			HierarchyFirstSub.superInt.map(superInt),
		});
	}/**

	 **
	 * Creates a new HierarchyFirstSub and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.generic.constructor public|package|protected|private|none</code> in the class comment.
	 */
	private HierarchyFirstSub(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private HierarchyFirstSub(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #firstSubString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.String getFirstSubString()
	{
		return HierarchyFirstSub.firstSubString.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #firstSubString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setFirstSubString(final java.lang.String firstSubString)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.UniqueViolationException
	{
		HierarchyFirstSub.firstSubString.set(this,firstSubString);
	}/**

	 **
	 * Finds a hierarchyFirstSub by it's unique attributes.
	 * @param firstSubString shall be equal to attribute {@link #firstSubString}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public static final HierarchyFirstSub findByFirstSubString(final java.lang.String firstSubString)
	{
		return (HierarchyFirstSub)HierarchyFirstSub.firstSubString.searchUnique(firstSubString);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #firstSubStringUpper}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.String getFirstSubStringUpper()
	{
		return HierarchyFirstSub.firstSubStringUpper.get(this);
	}/**

	 **
	 * The persistent type information for hierarchyFirstSub.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.type public|package|protected|private|none</code> in the class comment.
	 */
	public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(HierarchyFirstSub.class)
;}
