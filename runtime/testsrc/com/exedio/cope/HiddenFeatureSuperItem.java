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

public class HiddenFeatureSuperItem extends Item
{
	public static final StringField nonHiddenSuper = new StringField(OPTIONAL);
	public static final StringField hiddenSame = new StringField(OPTIONAL);
	public static final StringField hiddenOther = new StringField(OPTIONAL);
	
	/**

	 **
	 * Creates a new HiddenFeatureSuperItem with all the attributes initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of attributes.
	 */
	public HiddenFeatureSuperItem()
	{
		this(new com.exedio.cope.SetValue[]{
		});
	}/**

	 **
	 * Creates a new HiddenFeatureSuperItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	protected HiddenFeatureSuperItem(final com.exedio.cope.SetValue[] setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	protected HiddenFeatureSuperItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #nonHiddenSuper}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.String getNonHiddenSuper()
	{
		return HiddenFeatureSuperItem.nonHiddenSuper.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #nonHiddenSuper}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setNonHiddenSuper(final java.lang.String nonHiddenSuper)
			throws
				com.exedio.cope.LengthViolationException
	{
		HiddenFeatureSuperItem.nonHiddenSuper.set(this,nonHiddenSuper);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #hiddenSame}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.String getHiddenSame()
	{
		return HiddenFeatureSuperItem.hiddenSame.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #hiddenSame}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setHiddenSame(final java.lang.String hiddenSame)
			throws
				com.exedio.cope.LengthViolationException
	{
		HiddenFeatureSuperItem.hiddenSame.set(this,hiddenSame);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #hiddenOther}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.String getHiddenOther()
	{
		return HiddenFeatureSuperItem.hiddenOther.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #hiddenOther}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setHiddenOther(final java.lang.String hiddenOther)
			throws
				com.exedio.cope.LengthViolationException
	{
		HiddenFeatureSuperItem.hiddenOther.set(this,hiddenOther);
	}/**

	 **
	 * The persistent type information for hiddenFeatureSuperItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<HiddenFeatureSuperItem> TYPE = newType(HiddenFeatureSuperItem.class)
;}
