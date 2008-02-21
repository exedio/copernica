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

package com.exedio.cope.pattern;

import com.exedio.cope.Item;
import com.exedio.cope.ItemField;

public class VectorRelationItem extends Item
{
	static final ItemField<RelationSourceItem> vectorSource = newItemField(RelationSourceItem.class, CASCADE);
	static final ItemField<RelationTargetItem> vectorTarget = newItemField(RelationTargetItem.class, CASCADE);
	
	@Deprecated transient // OK: test deprecated api
	static final VectorRelation<RelationSourceItem, RelationTargetItem> relation = VectorRelation.newRelation(vectorSource, vectorTarget);

	/**

	 **
	 * Creates a new VectorRelationItem with all the fields initially needed.
	 * @param vectorSource the initial value for field {@link #vectorSource}.
	 * @param vectorTarget the initial value for field {@link #vectorTarget}.
	 * @throws com.exedio.cope.MandatoryViolationException if vectorSource, vectorTarget is null.
	 * @throws com.exedio.cope.UniqueViolationException if vectorSource is not unique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	VectorRelationItem(
				final com.exedio.cope.pattern.RelationSourceItem vectorSource,
				final com.exedio.cope.pattern.RelationTargetItem vectorTarget)
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
	 * Creates a new VectorRelationItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private VectorRelationItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@SuppressWarnings("unused") private VectorRelationItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of {@link #vectorSource}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final com.exedio.cope.pattern.RelationSourceItem getVectorSource()
	{
		return VectorRelationItem.vectorSource.get(this);
	}/**

	 **
	 * Sets a new value for {@link #vectorSource}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setVectorSource(final com.exedio.cope.pattern.RelationSourceItem vectorSource)
			throws
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		VectorRelationItem.vectorSource.set(this,vectorSource);
	}/**

	 **
	 * Returns the value of {@link #vectorTarget}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final com.exedio.cope.pattern.RelationTargetItem getVectorTarget()
	{
		return VectorRelationItem.vectorTarget.get(this);
	}/**

	 **
	 * Sets a new value for {@link #vectorTarget}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setVectorTarget(final com.exedio.cope.pattern.RelationTargetItem vectorTarget)
			throws
				com.exedio.cope.MandatoryViolationException
	{
		VectorRelationItem.vectorTarget.set(this,vectorTarget);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for vectorRelationItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<VectorRelationItem> TYPE = newType(VectorRelationItem.class)
;}
