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
import com.exedio.cope.StringField;

public final class CompositeOptionalItem extends Item
{
	public static final StringField code = new StringField().toFinal();

	public static final Composite<CompositeValue> uno = Composite.newComposite(CompositeValue.class).optional();
	public static final Composite<CompositeValue> duo = Composite.newComposite(CompositeValue.class).optional();
	
	
	/**

	 **
	 * Creates a new CompositeOptionalItem with all the fields initially needed.
	 * @param code the initial value for field {@link #code}.
	 * @throws com.exedio.cope.LengthViolationException if code violates its length constraint.
	 * @throws com.exedio.cope.MandatoryViolationException if code is null.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	public CompositeOptionalItem(
				final java.lang.String code)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			CompositeOptionalItem.code.map(code),
		});
	}/**

	 **
	 * Creates a new CompositeOptionalItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private CompositeOptionalItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@SuppressWarnings("unused") private CompositeOptionalItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of {@link #code}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.String getCode()
	{
		return CompositeOptionalItem.code.get(this);
	}/**

	 **
	 * Returns the value of {@link #uno}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final CompositeValue getUno()
	{
		return CompositeOptionalItem.uno.get(this);
	}/**

	 **
	 * Sets a new value for {@link #uno}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setUno(final CompositeValue uno)
	{
		CompositeOptionalItem.uno.set(this,uno);
	}/**

	 **
	 * Returns the value of {@link #duo}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final CompositeValue getDuo()
	{
		return CompositeOptionalItem.duo.get(this);
	}/**

	 **
	 * Sets a new value for {@link #duo}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setDuo(final CompositeValue duo)
	{
		CompositeOptionalItem.duo.set(this,duo);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for compositeOptionalItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<CompositeOptionalItem> TYPE = newType(CompositeOptionalItem.class)
;}
