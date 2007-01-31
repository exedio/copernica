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

/**
 * @cope.constructor private
 */
class InstanceOfRefItem extends Item
{
	static final ItemField<InstanceOfAItem> ref = newItemField(FINAL, InstanceOfAItem.class);
	static final StringField code = new StringField(FINAL).unique();
	static final ItemField<InstanceOfB2Item> refb2 = newItemField(OPTIONAL, InstanceOfB2Item.class);
	
	@Override
	public final String toString()
	{
		return getCode();
	}

	InstanceOfRefItem(final InstanceOfAItem ref)
	{
		this(ref, "->"+ref.getCode());
	}
	
	/**

	 **
	 * Creates a new InstanceOfRefItem with all the fields initially needed.
	 * @param ref the initial value for field {@link #ref}.
	 * @param code the initial value for field {@link #code}.
	 * @throws com.exedio.cope.LengthViolationException if code violates its length constraint.
	 * @throws com.exedio.cope.MandatoryViolationException if ref, code is null.
	 * @throws com.exedio.cope.UniqueViolationException if code is not unique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	private InstanceOfRefItem(
				final InstanceOfAItem ref,
				final java.lang.String code)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			InstanceOfRefItem.ref.map(ref),
			InstanceOfRefItem.code.map(code),
		});
	}/**

	 **
	 * Creates a new InstanceOfRefItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private InstanceOfRefItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private InstanceOfRefItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent field {@link #ref}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final InstanceOfAItem getRef()
	{
		return InstanceOfRefItem.ref.get(this);
	}/**

	 **
	 * Returns the value of the persistent field {@link #code}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final java.lang.String getCode()
	{
		return InstanceOfRefItem.code.get(this);
	}/**

	 **
	 * Finds a instanceOfRefItem by it's unique fields.
	 * @param code shall be equal to field {@link #code}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	static final InstanceOfRefItem findByCode(final java.lang.String code)
	{
		return (InstanceOfRefItem)InstanceOfRefItem.code.searchUnique(code);
	}/**

	 **
	 * Returns the value of the persistent field {@link #refb2}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final InstanceOfB2Item getRefb2()
	{
		return InstanceOfRefItem.refb2.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #refb2}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setRefb2(final InstanceOfB2Item refb2)
	{
		InstanceOfRefItem.refb2.set(this,refb2);
	}/**

	 **
	 * The persistent type information for instanceOfRefItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<InstanceOfRefItem> TYPE = newType(InstanceOfRefItem.class)
;}
