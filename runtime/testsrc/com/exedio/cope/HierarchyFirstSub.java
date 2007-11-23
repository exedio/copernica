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

package com.exedio.cope;

import com.exedio.cope.function.UppercaseView;

/**
 * @author Ralf Wiebicke
 */
public class HierarchyFirstSub extends HierarchySuper
{

	public static final StringField firstSubString = new StringField().optional().unique();
	
	public static final UppercaseView firstSubStringUpper = firstSubString.toUpperCase();
	
	
/**

	 **
	 * Creates a new HierarchyFirstSub with all the fields initially needed.
	 * @param superInt the initial value for field {@link #superInt}.
	 * @throws com.exedio.cope.UniqueViolationException if superInt is not unique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	public HierarchyFirstSub(
				final int superInt)
			throws
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			HierarchyFirstSub.superInt.map(superInt),
		});
	}/**

	 **
	 * Creates a new HierarchyFirstSub and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private HierarchyFirstSub(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@SuppressWarnings("unused") private HierarchyFirstSub(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of {@link #firstSubString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.String getFirstSubString()
	{
		return HierarchyFirstSub.firstSubString.get(this);
	}/**

	 **
	 * Sets a new value for {@link #firstSubString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setFirstSubString(final java.lang.String firstSubString)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.UniqueViolationException
	{
		HierarchyFirstSub.firstSubString.set(this,firstSubString);
	}/**

	 **
	 * Finds a hierarchyFirstSub by it's {@link #firstSubString}.
	 * @param firstSubString shall be equal to field {@link #firstSubString}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.finder public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public static final HierarchyFirstSub forFirstSubString(final java.lang.String firstSubString)
	{
		return HierarchyFirstSub.firstSubString.searchUnique(HierarchyFirstSub.class,firstSubString);
	}/**

	 **
	 * Finds a hierarchyFirstSub by it's {@link #firstSubString}.
	 * @deprecated use for{@link #firstSubString} instead.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.finder public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	@Deprecated
	public static final HierarchyFirstSub findByFirstSubString(final java.lang.String firstSubString)
	{
		return HierarchyFirstSub.firstSubString.searchUnique(HierarchyFirstSub.class,firstSubString);
	}/**

	 **
	 * Returns the value of {@link #firstSubStringUpper}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.String getFirstSubStringUpper()
	{
		return HierarchyFirstSub.firstSubStringUpper.get(this);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for hierarchyFirstSub.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<HierarchyFirstSub> TYPE = newType(HierarchyFirstSub.class)
;}
