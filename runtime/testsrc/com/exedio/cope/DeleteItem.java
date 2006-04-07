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
 * @cope.persistent
 * @author Ralf Wiebicke
 */
public class DeleteItem extends Item
{

	public static final ItemAttribute<DeleteItem> selfForbid = new ItemAttribute<DeleteItem>(OPTIONAL);

	public static final ItemAttribute<DeleteItem> selfNullify = new ItemAttribute<DeleteItem>(OPTIONAL, NULLIFY);

	public static final ItemAttribute<DeleteItem> selfCascade = new ItemAttribute<DeleteItem>(OPTIONAL, CASCADE);
	public static final ItemAttribute<DeleteItem> selfCascade2 = new ItemAttribute<DeleteItem>(OPTIONAL, CASCADE);
	
	public static final ItemAttribute<DeleteOtherItem> otherForbid = new ItemAttribute<DeleteOtherItem>(OPTIONAL);

	public static final ItemAttribute<DeleteOtherItem> otherNullify = new ItemAttribute<DeleteOtherItem>(OPTIONAL, NULLIFY);

	public static final ItemAttribute<DeleteOtherItem> otherCascade = new ItemAttribute<DeleteOtherItem>(OPTIONAL, CASCADE);
	

	public String name = null;

	public DeleteItem(final String name)
	{
		this();
		this.name = name;
	}
	
	public String toString()
	{
		return name!=null ? name : getCopeID();
	}
	
	
/**

	 **
	 * Creates a new DeleteItem with all the attributes initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of attributes.
	 */
	public DeleteItem()
	{
		this(new com.exedio.cope.SetValue[]{
		});
	}/**

	 **
	 * Creates a new DeleteItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private DeleteItem(final com.exedio.cope.SetValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private DeleteItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #selfForbid}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final DeleteItem getSelfForbid()
	{
		return DeleteItem.selfForbid.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #selfForbid}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setSelfForbid(final DeleteItem selfForbid)
	{
		DeleteItem.selfForbid.set(this,selfForbid);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #selfNullify}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final DeleteItem getSelfNullify()
	{
		return DeleteItem.selfNullify.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #selfNullify}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setSelfNullify(final DeleteItem selfNullify)
	{
		DeleteItem.selfNullify.set(this,selfNullify);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #selfCascade}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final DeleteItem getSelfCascade()
	{
		return DeleteItem.selfCascade.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #selfCascade}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setSelfCascade(final DeleteItem selfCascade)
	{
		DeleteItem.selfCascade.set(this,selfCascade);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #selfCascade2}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final DeleteItem getSelfCascade2()
	{
		return DeleteItem.selfCascade2.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #selfCascade2}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setSelfCascade2(final DeleteItem selfCascade2)
	{
		DeleteItem.selfCascade2.set(this,selfCascade2);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #otherForbid}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final DeleteOtherItem getOtherForbid()
	{
		return DeleteItem.otherForbid.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #otherForbid}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setOtherForbid(final DeleteOtherItem otherForbid)
	{
		DeleteItem.otherForbid.set(this,otherForbid);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #otherNullify}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final DeleteOtherItem getOtherNullify()
	{
		return DeleteItem.otherNullify.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #otherNullify}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setOtherNullify(final DeleteOtherItem otherNullify)
	{
		DeleteItem.otherNullify.set(this,otherNullify);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #otherCascade}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final DeleteOtherItem getOtherCascade()
	{
		return DeleteItem.otherCascade.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #otherCascade}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setOtherCascade(final DeleteOtherItem otherCascade)
	{
		DeleteItem.otherCascade.set(this,otherCascade);
	}/**

	 **
	 * The persistent type information for deleteItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(DeleteItem.class)
;}
