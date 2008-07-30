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

import com.exedio.cope.Cope;
import com.exedio.cope.DoubleField;
import com.exedio.cope.Item;
import com.exedio.cope.PlusView;
import com.exedio.cope.SetValue;

public class PlusDoubleItem extends Item
{
	public static final DoubleField num1 = new DoubleField().optional();

	public static final DoubleField num2 = new DoubleField().optional();

	public static final DoubleField num3 = new DoubleField().optional();
	
	public static final PlusView<Double> plus12 = num1.plus(num2);

	public static final PlusView<Double> plus13 = num1.plus(num3);

	public static final PlusView<Double> plus23 = num2.plus(num3);

	public static final PlusView<Double> plus123 = Cope.plus(num1, num2, num3);

	public static final PlusView<Double> plus12a3 = plus12.plus(num3);
	
	public PlusDoubleItem(final double initialNum1, final double initialNum2, final double initialNum3)
	{
		super(new SetValue[]{
			num1.map(initialNum1),
			num2.map(initialNum2),
			num3.map(initialNum3),
		});
	}

	/**

	 **
	 * Creates a new PlusDoubleItem with all the fields initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	public PlusDoubleItem()
	{
		this(new com.exedio.cope.SetValue[]{
		});
	}/**

	 **
	 * Creates a new PlusDoubleItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private PlusDoubleItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@SuppressWarnings("unused") private PlusDoubleItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of {@link #num1}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.Double getNum1()
	{
		return PlusDoubleItem.num1.get(this);
	}/**

	 **
	 * Sets a new value for {@link #num1}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setNum1(final java.lang.Double num1)
	{
		PlusDoubleItem.num1.set(this,num1);
	}/**

	 **
	 * Returns the value of {@link #num2}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.Double getNum2()
	{
		return PlusDoubleItem.num2.get(this);
	}/**

	 **
	 * Sets a new value for {@link #num2}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setNum2(final java.lang.Double num2)
	{
		PlusDoubleItem.num2.set(this,num2);
	}/**

	 **
	 * Returns the value of {@link #num3}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.Double getNum3()
	{
		return PlusDoubleItem.num3.get(this);
	}/**

	 **
	 * Sets a new value for {@link #num3}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setNum3(final java.lang.Double num3)
	{
		PlusDoubleItem.num3.set(this,num3);
	}/**

	 **
	 * Returns the value of {@link #plus12}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.Double getPlus12()
	{
		return PlusDoubleItem.plus12.get(this);
	}/**

	 **
	 * Returns the value of {@link #plus13}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.Double getPlus13()
	{
		return PlusDoubleItem.plus13.get(this);
	}/**

	 **
	 * Returns the value of {@link #plus23}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.Double getPlus23()
	{
		return PlusDoubleItem.plus23.get(this);
	}/**

	 **
	 * Returns the value of {@link #plus123}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.Double getPlus123()
	{
		return PlusDoubleItem.plus123.get(this);
	}/**

	 **
	 * Returns the value of {@link #plus12a3}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.Double getPlus12a3()
	{
		return PlusDoubleItem.plus12a3.get(this);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for plusDoubleItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<PlusDoubleItem> TYPE = newType(PlusDoubleItem.class)
;}
