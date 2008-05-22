/*
 * Copyright (C) 2004-2008  exedio GmbH (www.exedio.com)
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

class CopySourceItem extends Item
{
	static final ItemField<CopyTargetItem> targetItem = newItemField(CopyTargetItem.class).toFinal().optional();
	
	static final StringField templateString = new StringField().toFinal().optional();
	static final ItemField<CopyValueItem> templateItem = newItemField(CopyValueItem.class).toFinal().optional();
	
	static final CopyConstraint templateStringCopyFromTarget = new CopyConstraint(targetItem, templateString);
	static final CopyConstraint templateItemCopyFromTarget = new CopyConstraint(targetItem, templateItem);
	
	@Override
	public String toString()
	{
		// for testing, that CopyViolation#getMessage does not call toString(), but getCopeID()
		return "toString(" + getCopeID() + ')';
	}
	
	/**

	 **
	 * Creates a new CopySourceItem with all the fields initially needed.
	 * @param targetItem the initial value for field {@link #targetItem}.
	 * @param templateString the initial value for field {@link #templateString}.
	 * @param templateItem the initial value for field {@link #templateItem}.
	 * @throws com.exedio.cope.LengthViolationException if templateString violates its length constraint.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	CopySourceItem(
				final com.exedio.cope.CopyTargetItem targetItem,
				final java.lang.String templateString,
				final com.exedio.cope.CopyValueItem templateItem)
			throws
				com.exedio.cope.LengthViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			CopySourceItem.targetItem.map(targetItem),
			CopySourceItem.templateString.map(templateString),
			CopySourceItem.templateItem.map(templateItem),
		});
	}/**

	 **
	 * Creates a new CopySourceItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private CopySourceItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@SuppressWarnings("unused") private CopySourceItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of {@link #targetItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final com.exedio.cope.CopyTargetItem getTargetItem()
	{
		return CopySourceItem.targetItem.get(this);
	}/**

	 **
	 * Returns the value of {@link #templateString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getTemplateString()
	{
		return CopySourceItem.templateString.get(this);
	}/**

	 **
	 * Returns the value of {@link #templateItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final com.exedio.cope.CopyValueItem getTemplateItem()
	{
		return CopySourceItem.templateItem.get(this);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for copySourceItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	static final com.exedio.cope.Type<CopySourceItem> TYPE = newType(CopySourceItem.class)
;}
