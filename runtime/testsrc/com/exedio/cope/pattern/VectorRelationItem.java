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

package com.exedio.cope.pattern;

import com.exedio.cope.Item;
import com.exedio.cope.ItemField;

/**
 * @author Ralf Wiebicke
 */
public class VectorRelationItem extends Item
{
	public static final ItemField<RelationSourceItem> vectorSource = newItemAttribute(RelationSourceItem.class, CASCADE);
	public static final ItemField<RelationTargetItem> vectorTarget = newItemAttribute(RelationTargetItem.class, CASCADE);
	
	public static final VectorRelation<RelationSourceItem, RelationTargetItem> relation = VectorRelation.newRelation(vectorSource, vectorTarget);

	/**

	 **
	 * Creates a new VectorRelationItem with all the attributes initially needed.
	 * @param vectorSource the initial value for attribute {@link #vectorSource}.
	 * @param vectorTarget the initial value for attribute {@link #vectorTarget}.
	 * @throws com.exedio.cope.MandatoryViolationException if vectorSource, vectorTarget is null.
	 * @throws com.exedio.cope.UniqueViolationException if vectorSource is not unique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of attributes.
	 */
	public VectorRelationItem(
				final RelationSourceItem vectorSource,
				final RelationTargetItem vectorTarget)
			throws
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			VectorRelationItem.vectorSource.map(vectorSource),
			VectorRelationItem.vectorTarget.map(vectorTarget),
		});
	}/**

	 **
	 * Creates a new VectorRelationItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private VectorRelationItem(final com.exedio.cope.SetValue[] setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private VectorRelationItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #vectorSource}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final RelationSourceItem getVectorSource()
	{
		return VectorRelationItem.vectorSource.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #vectorSource}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setVectorSource(final RelationSourceItem vectorSource)
			throws
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		VectorRelationItem.vectorSource.set(this,vectorSource);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #vectorTarget}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final RelationTargetItem getVectorTarget()
	{
		return VectorRelationItem.vectorTarget.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #vectorTarget}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setVectorTarget(final RelationTargetItem vectorTarget)
			throws
				com.exedio.cope.MandatoryViolationException
	{
		VectorRelationItem.vectorTarget.set(this,vectorTarget);
	}/**

	 **
	 * The persistent type information for vectorRelationItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<VectorRelationItem> TYPE = newType(VectorRelationItem.class)
;}
