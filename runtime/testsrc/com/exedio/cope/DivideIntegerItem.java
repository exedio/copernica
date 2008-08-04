
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

public class DivideIntegerItem extends Item
{
	public static final IntegerField numA = new IntegerField().optional();

	public static final IntegerField numB = new IntegerField().optional();

	public static final IntegerField numC = new IntegerField().optional();
	
	public static final DivideView<Integer> divideAB = numA.divide(numB);
	
	public static final DivideView<Integer> divideAC = numA.divide(numC);
	
	public static final DivideView<Integer> divideBC = numB.divide(numC);
	
	public DivideIntegerItem(final int initialNumA, final int initialNumB, final int initialNumC)
	{
		super(new SetValue[]{
			numA.map(initialNumA),
			numB.map(initialNumB),
			numC.map(initialNumC),
		});
	}

	/**

	 **
	 * Creates a new DivideIntegerItem with all the fields initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	public DivideIntegerItem()
	{
		this(new com.exedio.cope.SetValue[]{
		});
	}/**

	 **
	 * Creates a new DivideIntegerItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private DivideIntegerItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@SuppressWarnings("unused") private DivideIntegerItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of {@link #numA}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.Integer getNumA()
	{
		return DivideIntegerItem.numA.get(this);
	}/**

	 **
	 * Sets a new value for {@link #numA}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setNumA(final java.lang.Integer numA)
	{
		DivideIntegerItem.numA.set(this,numA);
	}/**

	 **
	 * Returns the value of {@link #numB}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.Integer getNumB()
	{
		return DivideIntegerItem.numB.get(this);
	}/**

	 **
	 * Sets a new value for {@link #numB}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setNumB(final java.lang.Integer numB)
	{
		DivideIntegerItem.numB.set(this,numB);
	}/**

	 **
	 * Returns the value of {@link #numC}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.Integer getNumC()
	{
		return DivideIntegerItem.numC.get(this);
	}/**

	 **
	 * Sets a new value for {@link #numC}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setNumC(final java.lang.Integer numC)
	{
		DivideIntegerItem.numC.set(this,numC);
	}/**

	 **
	 * Returns the value of {@link #divideAB}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.Integer getDivideAB()
	{
		return DivideIntegerItem.divideAB.get(this);
	}/**

	 **
	 * Returns the value of {@link #divideAC}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.Integer getDivideAC()
	{
		return DivideIntegerItem.divideAC.get(this);
	}/**

	 **
	 * Returns the value of {@link #divideBC}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final java.lang.Integer getDivideBC()
	{
		return DivideIntegerItem.divideBC.get(this);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for divideIntegerItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<DivideIntegerItem> TYPE = newType(DivideIntegerItem.class)
;}