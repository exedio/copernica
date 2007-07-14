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

class UniqueHierarchySubItem extends UniqueHierarchySuperItem
{
	static final StringField subField = new StringField().unique();
	
	@Override
	public final String toString()
	{
		return getSubField();
	}

	/**

	 **
	 * Creates a new UniqueHierarchySubItem with all the fields initially needed.
	 * @param superField the initial value for field {@link #superField}.
	 * @param subField the initial value for field {@link #subField}.
	 * @throws com.exedio.cope.LengthViolationException if superField, subField violates its length constraint.
	 * @throws com.exedio.cope.MandatoryViolationException if superField, subField is null.
	 * @throws com.exedio.cope.UniqueViolationException if subField is not unique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	UniqueHierarchySubItem(
				final java.lang.String superField,
				final java.lang.String subField)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			UniqueHierarchySubItem.superField.map(superField),
			UniqueHierarchySubItem.subField.map(subField),
		});
	}/**

	 **
	 * Creates a new UniqueHierarchySubItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private UniqueHierarchySubItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private UniqueHierarchySubItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent field {@link #subField}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final java.lang.String getSubField()
	{
		return UniqueHierarchySubItem.subField.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #subField}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setSubField(final java.lang.String subField)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		UniqueHierarchySubItem.subField.set(this,subField);
	}/**

	 **
	 * Finds a uniqueHierarchySubItem by it's unique fields.
	 * @param subField shall be equal to field {@link #subField}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	static final UniqueHierarchySubItem findBySubField(final java.lang.String subField)
	{
		return (UniqueHierarchySubItem)UniqueHierarchySubItem.subField.searchUnique(subField);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for uniqueHierarchySubItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<UniqueHierarchySubItem> TYPE = newType(UniqueHierarchySubItem.class)
;}
