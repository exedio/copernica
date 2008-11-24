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

package com.exedio.cope.pattern;

import com.exedio.cope.Item;

/**
 * @cope.generic.constructor package
 */
public class PriceFieldItem extends Item
{
	static final PriceField finalPrice = new PriceField().toFinal();
	static final PriceField optionalPrice = new PriceField().optional();
	static final PriceField bigPrice = new PriceField().min(5000);
	

	/**

	 **
	 * Creates a new PriceFieldItem with all the fields initially needed.
	 * @param finalPrice the initial value for field {@link #finalPrice}.
	 * @param bigPrice the initial value for field {@link #bigPrice}.
	 * @throws com.exedio.cope.IntegerRangeViolationException if bigPrice violates its range constraint.
	 * @throws com.exedio.cope.MandatoryViolationException if finalPrice, bigPrice is null.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	PriceFieldItem(
				final com.exedio.cope.pattern.Price finalPrice,
				final com.exedio.cope.pattern.Price bigPrice)
			throws
				com.exedio.cope.IntegerRangeViolationException,
				com.exedio.cope.MandatoryViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			PriceFieldItem.finalPrice.map(finalPrice),
			PriceFieldItem.bigPrice.map(bigPrice),
		});
	}/**

	 **
	 * Creates a new PriceFieldItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	PriceFieldItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@SuppressWarnings("unused") private PriceFieldItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of {@link #finalPrice}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final com.exedio.cope.pattern.Price getFinalPrice()
	{
		return PriceFieldItem.finalPrice.get(this);
	}/**

	 **
	 * Returns the value of {@link #optionalPrice}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final com.exedio.cope.pattern.Price getOptionalPrice()
	{
		return PriceFieldItem.optionalPrice.get(this);
	}/**

	 **
	 * Sets a new value for {@link #optionalPrice}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setOptionalPrice(final com.exedio.cope.pattern.Price optionalPrice)
	{
		PriceFieldItem.optionalPrice.set(this,optionalPrice);
	}/**

	 **
	 * Returns the value of {@link #bigPrice}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final com.exedio.cope.pattern.Price getBigPrice()
	{
		return PriceFieldItem.bigPrice.get(this);
	}/**

	 **
	 * Sets a new value for {@link #bigPrice}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setBigPrice(final com.exedio.cope.pattern.Price bigPrice)
			throws
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.IntegerRangeViolationException
	{
		PriceFieldItem.bigPrice.set(this,bigPrice);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for priceFieldItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<PriceFieldItem> TYPE = newType(PriceFieldItem.class)
;}