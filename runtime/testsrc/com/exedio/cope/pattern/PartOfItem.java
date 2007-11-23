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

import com.exedio.cope.IntegerField;
import com.exedio.cope.Item;
import com.exedio.cope.ItemField;
import com.exedio.cope.StringField;

public class PartOfItem extends Item
{
	static final ItemField<PartOfContainerItem> container = newItemField(PartOfContainerItem.class, CASCADE);
	
	static final PartOf<PartOfContainerItem> parts = PartOf.newPartOf(container);

	static final StringField partString = new StringField();
	static final IntegerField partInteger = new IntegerField();
	
	
	/**

	 **
	 * Creates a new PartOfItem with all the fields initially needed.
	 * @param container the initial value for field {@link #container}.
	 * @param partString the initial value for field {@link #partString}.
	 * @param partInteger the initial value for field {@link #partInteger}.
	 * @throws com.exedio.cope.LengthViolationException if partString violates its length constraint.
	 * @throws com.exedio.cope.MandatoryViolationException if container, partString is null.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	PartOfItem(
				final PartOfContainerItem container,
				final java.lang.String partString,
				final int partInteger)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			PartOfItem.container.map(container),
			PartOfItem.partString.map(partString),
			PartOfItem.partInteger.map(partInteger),
		});
	}/**

	 **
	 * Creates a new PartOfItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private PartOfItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@SuppressWarnings("unused") private PartOfItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of {@link #container}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final PartOfContainerItem getContainer()
	{
		return PartOfItem.container.get(this);
	}/**

	 **
	 * Sets a new value for {@link #container}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setContainer(final PartOfContainerItem container)
			throws
				com.exedio.cope.MandatoryViolationException
	{
		PartOfItem.container.set(this,container);
	}/**

	 **
	 * Returns the container this item is part of by {@link #parts}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final PartOfContainerItem getPartsContainer()
	{
		return PartOfItem.parts.getContainer(this);
	}/**

	 **
	 * Returns the value of {@link #partString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getPartString()
	{
		return PartOfItem.partString.get(this);
	}/**

	 **
	 * Sets a new value for {@link #partString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setPartString(final java.lang.String partString)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException
	{
		PartOfItem.partString.set(this,partString);
	}/**

	 **
	 * Returns the value of {@link #partInteger}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final int getPartInteger()
	{
		return PartOfItem.partInteger.getMandatory(this);
	}/**

	 **
	 * Sets a new value for {@link #partInteger}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setPartInteger(final int partInteger)
	{
		PartOfItem.partInteger.set(this,partInteger);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for partOfItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<PartOfItem> TYPE = newType(PartOfItem.class)
;}
