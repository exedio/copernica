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

import com.exedio.cope.SetValue;
import com.exedio.cope.IntegerAttribute;
import com.exedio.cope.Item;
import com.exedio.cope.function.PlusView;

/**
 * @author Ralf Wiebicke
 */
public class PlusItem extends Item
{
	public static final IntegerAttribute num1 = new IntegerAttribute(OPTIONAL);

	public static final IntegerAttribute num2 = new IntegerAttribute(OPTIONAL);

	public static final IntegerAttribute num3 = new IntegerAttribute(OPTIONAL);
	
	public static final PlusView plus12 = num1.plus(num2);

	public static final PlusView sum13 = num1.plus(num3);

	public static final PlusView sum23 = num2.plus(num3);

	public static final PlusView sum123 = plus(num1, num2, num3);

	public static final PlusView sum12a3 = plus12.plus(num3);
	
	public PlusItem(final int initialNum1, final int initialNum2, final int initialNum3)
	{
		super(new SetValue[]{
			num1.map(initialNum1),
			num2.map(initialNum2),
			num3.map(initialNum3),
		});
	}

/**

	 **
	 * Creates a new PlusItem with all the attributes initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of attributes.
	 */
	public PlusItem()
	{
		this(new com.exedio.cope.SetValue[]{
		});
	}/**

	 **
	 * Creates a new PlusItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private PlusItem(final com.exedio.cope.SetValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private PlusItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #num1}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.Integer getNum1()
	{
		return PlusItem.num1.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #num1}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setNum1(final java.lang.Integer num1)
	{
		PlusItem.num1.set(this,num1);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #num2}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.Integer getNum2()
	{
		return PlusItem.num2.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #num2}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setNum2(final java.lang.Integer num2)
	{
		PlusItem.num2.set(this,num2);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #num3}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.Integer getNum3()
	{
		return PlusItem.num3.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #num3}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setNum3(final java.lang.Integer num3)
	{
		PlusItem.num3.set(this,num3);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #plus12}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.Integer getPlus12()
	{
		return PlusItem.plus12.get(this);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #sum13}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.Integer getSum13()
	{
		return PlusItem.sum13.get(this);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #sum23}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.Integer getSum23()
	{
		return PlusItem.sum23.get(this);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #sum123}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.Integer getSum123()
	{
		return PlusItem.sum123.get(this);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #sum12a3}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.Integer getSum12a3()
	{
		return PlusItem.sum12a3.get(this);
	}/**

	 **
	 * The persistent type information for plusItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<PlusItem> TYPE = newType(PlusItem.class)
;}
