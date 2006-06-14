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

/**
 * @cope.constructor private
 */
public class TypeInConditionRefItem extends Item
{
	public static final ItemAttribute<TypeInConditionAItem> ref = newItemAttribute(FINAL, TypeInConditionAItem.class);
	public static final StringAttribute code = new StringAttribute(FINAL_UNIQUE);
	public static final ItemAttribute<TypeInConditionB2Item> refb2 = newItemAttribute(OPTIONAL, TypeInConditionB2Item.class);
	
	@Override
	public final String toString()
	{
		return getCode();
	}

	public TypeInConditionRefItem(final TypeInConditionAItem ref)
	{
		this(ref, "->"+ref.getCode());
	}
	
	/**

	 **
	 * Creates a new TypeInConditionRefItem with all the attributes initially needed.
	 * @param ref the initial value for attribute {@link #ref}.
	 * @param code the initial value for attribute {@link #code}.
	 * @throws com.exedio.cope.LengthViolationException if code violates its length constraint.
	 * @throws com.exedio.cope.MandatoryViolationException if ref, code is null.
	 * @throws com.exedio.cope.UniqueViolationException if code is not unique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of attributes.
	 */
	private TypeInConditionRefItem(
				final TypeInConditionAItem ref,
				final java.lang.String code)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			TypeInConditionRefItem.ref.map(ref),
			TypeInConditionRefItem.code.map(code),
		});
	}/**

	 **
	 * Creates a new TypeInConditionRefItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private TypeInConditionRefItem(final com.exedio.cope.SetValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private TypeInConditionRefItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #ref}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final TypeInConditionAItem getRef()
	{
		return TypeInConditionRefItem.ref.get(this);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #code}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.String getCode()
	{
		return TypeInConditionRefItem.code.get(this);
	}/**

	 **
	 * Finds a typeInConditionRefItem by it's unique attributes.
	 * @param code shall be equal to attribute {@link #code}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public static final TypeInConditionRefItem findByCode(final java.lang.String code)
	{
		return (TypeInConditionRefItem)TypeInConditionRefItem.code.searchUnique(code);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #refb2}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final TypeInConditionB2Item getRefb2()
	{
		return TypeInConditionRefItem.refb2.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #refb2}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setRefb2(final TypeInConditionB2Item refb2)
	{
		TypeInConditionRefItem.refb2.set(this,refb2);
	}/**

	 **
	 * The persistent type information for typeInConditionRefItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<TypeInConditionRefItem> TYPE = newType(TypeInConditionRefItem.class)
;}
