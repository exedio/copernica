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

import com.exedio.cope.Item;
import com.exedio.cope.ItemAttribute;
import com.exedio.cope.StringAttribute;

/**
 * @cope.persistent
 * @author Ralf Wiebicke
 */
public class PointerItem extends Item
{

	public static final StringAttribute code = new StringAttribute(MANDATORY);
	
	public static final ItemAttribute<PointerTargetItem> pointer = new ItemAttribute<PointerTargetItem>(MANDATORY);

	public static final ItemAttribute<PointerTargetItem> pointer2 = new ItemAttribute<PointerTargetItem>(OPTIONAL);

	public static final ItemAttribute<PointerItem> self = new ItemAttribute<PointerItem>(OPTIONAL);

	public static final ItemAttribute<EmptyItem2> empty2 = new ItemAttribute<EmptyItem2>(OPTIONAL);

/**

	 **
	 * Creates a new PointerItem with all the attributes initially needed.
	 * @param code the initial value for attribute {@link #code}.
	 * @param pointer the initial value for attribute {@link #pointer}.
	 * @throws com.exedio.cope.LengthViolationException if code violates its length constraint.
	 * @throws com.exedio.cope.MandatoryViolationException if code, pointer is null.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <code>@cope.constructor public|package|protected|private|none</code> in the class comment and <code>@cope.initial</code> in the comment of attributes.
	 */
	public PointerItem(
				final java.lang.String code,
				final PointerTargetItem pointer)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException
	{
		this(new com.exedio.cope.AttributeValue[]{
			PointerItem.code.map(code),
			PointerItem.pointer.map(pointer),
		});
	}/**

	 **
	 * Creates a new PointerItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.generic.constructor public|package|protected|private|none</code> in the class comment.
	 */
	private PointerItem(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private PointerItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #code}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.String getCode()
	{
		return PointerItem.code.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #code}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setCode(final java.lang.String code)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException
	{
		PointerItem.code.set(this,code);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #pointer}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final PointerTargetItem getPointer()
	{
		return PointerItem.pointer.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #pointer}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setPointer(final PointerTargetItem pointer)
			throws
				com.exedio.cope.MandatoryViolationException
	{
		PointerItem.pointer.set(this,pointer);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #pointer2}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final PointerTargetItem getPointer2()
	{
		return PointerItem.pointer2.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #pointer2}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setPointer2(final PointerTargetItem pointer2)
	{
		PointerItem.pointer2.set(this,pointer2);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #self}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final PointerItem getSelf()
	{
		return PointerItem.self.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #self}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setSelf(final PointerItem self)
	{
		PointerItem.self.set(this,self);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #empty2}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final EmptyItem2 getEmpty2()
	{
		return PointerItem.empty2.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #empty2}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setEmpty2(final EmptyItem2 empty2)
	{
		PointerItem.empty2.set(this,empty2);
	}/**

	 **
	 * The persistent type information for pointerItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.type public|package|protected|private|none</code> in the class comment.
	 */
	public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(PointerItem.class)
;}
