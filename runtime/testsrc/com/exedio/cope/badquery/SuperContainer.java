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

package com.exedio.cope.badquery;

import com.exedio.cope.BooleanField;
import com.exedio.cope.ItemField;

public class SuperContainer extends SuperItem
{
	public static final ItemField<QueryItem> queryItem = newItemField(QueryItem.class).toFinal();
	public static final BooleanField superflag = new BooleanField();
	
	/**

	 **
	 * Creates a new SuperContainer with all the fields initially needed.
	 * @param code the initial value for field {@link #code}.
	 * @param queryItem the initial value for field {@link #queryItem}.
	 * @param superflag the initial value for field {@link #superflag}.
	 * @throws com.exedio.cope.LengthViolationException if code violates its length constraint.
	 * @throws com.exedio.cope.MandatoryViolationException if code, queryItem is null.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	SuperContainer(
				final java.lang.String code,
				final com.exedio.cope.badquery.QueryItem queryItem,
				final boolean superflag)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			SuperContainer.code.map(code),
			SuperContainer.queryItem.map(queryItem),
			SuperContainer.superflag.map(superflag),
		});
	}/**

	 **
	 * Creates a new SuperContainer and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	protected SuperContainer(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	protected SuperContainer(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of {@link #queryItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final com.exedio.cope.badquery.QueryItem getQueryItem()
	{
		return SuperContainer.queryItem.get(this);
	}/**

	 **
	 * Returns the value of {@link #superflag}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	public final boolean getSuperflag()
	{
		return SuperContainer.superflag.getMandatory(this);
	}/**

	 **
	 * Sets a new value for {@link #superflag}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setSuperflag(final boolean superflag)
	{
		SuperContainer.superflag.set(this,superflag);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for superContainer.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<SuperContainer> TYPE = newType(SuperContainer.class)
;}
