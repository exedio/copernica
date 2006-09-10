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

package com.exedio.cope.instrument.testmodel;

import com.exedio.cope.Item;
import com.exedio.cope.ItemField;
import com.exedio.cope.StringField;
import com.exedio.cope.UniqueConstraint;
import com.exedio.cope.instrument.testmodel.sub.SubTarget;

/**
 */
public class DoubleUnique extends Item
{
	public static final StringField string = new StringField(FINAL);
	public static final ItemField<SubTarget> item = newItemAttribute(FINAL, SubTarget.class, CASCADE);
	public static final UniqueConstraint unique = new UniqueConstraint(string, item);
	

/**

	 **
	 * Creates a new DoubleUnique with all the attributes initially needed.
	 * @param string the initial value for attribute {@link #string}.
	 * @param item the initial value for attribute {@link #item}.
	 * @throws com.exedio.cope.LengthViolationException if string violates its length constraint.
	 * @throws com.exedio.cope.MandatoryViolationException if string, item is null.
	 * @throws com.exedio.cope.UniqueViolationException if string, item is not unique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of attributes.
	 */
	public DoubleUnique(
				final java.lang.String string,
				final SubTarget item)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			DoubleUnique.string.map(string),
			DoubleUnique.item.map(item),
		});
	}/**

	 **
	 * Creates a new DoubleUnique and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private DoubleUnique(final com.exedio.cope.SetValue[] setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private DoubleUnique(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #string}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.String getString()
	{
		return DoubleUnique.string.get(this);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #item}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final SubTarget getItem()
	{
		return DoubleUnique.item.get(this);
	}/**

	 **
	 * Finds a doubleUnique by it's unique attributes.
	 * @param string shall be equal to attribute {@link #string}.
	 * @param item shall be equal to attribute {@link #item}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public static final DoubleUnique findByUnique(final java.lang.String string,final SubTarget item)
	{
		return (DoubleUnique)DoubleUnique.unique.searchUnique(new Object[]{string,item});
	}/**

	 **
	 * The persistent type information for doubleUnique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<DoubleUnique> TYPE = newType(DoubleUnique.class)
;}
