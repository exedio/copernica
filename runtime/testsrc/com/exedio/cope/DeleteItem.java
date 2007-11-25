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

package com.exedio.cope;

/**
 * @author Ralf Wiebicke
 */
class DeleteItem extends Item
{

	static final ItemField<DeleteItem> selfForbid = newItemField(DeleteItem.class).optional();

	static final ItemField<DeleteItem> selfNullify = newItemField(DeleteItem.class, NULLIFY);

	static final ItemField<DeleteItem> selfCascade = newItemField(DeleteItem.class, CASCADE).optional();
	static final ItemField<DeleteItem> selfCascade2 = newItemField(DeleteItem.class, CASCADE).optional();
	
	static final ItemField<DeleteOtherItem> otherForbid = newItemField(DeleteOtherItem.class).optional();

	static final ItemField<DeleteOtherItem> otherNullify = newItemField(DeleteOtherItem.class, NULLIFY);

	static final ItemField<DeleteOtherItem> otherCascade = newItemField(DeleteOtherItem.class, CASCADE).optional();
	

	String name = null;

	DeleteItem(final String name)
	{
		this();
		this.name = name;
	}
	
	@Override
	public String toString()
	{
		return name!=null ? name : getCopeID();
	}
	
	
	/**

	 **
	 * Creates a new DeleteItem with all the fields initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	DeleteItem()
	{
		this(new com.exedio.cope.SetValue[]{
		});
	}/**

	 **
	 * Creates a new DeleteItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private DeleteItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@SuppressWarnings("unused") private DeleteItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of {@link #selfForbid}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final DeleteItem getSelfForbid()
	{
		return DeleteItem.selfForbid.get(this);
	}/**

	 **
	 * Sets a new value for {@link #selfForbid}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setSelfForbid(final DeleteItem selfForbid)
	{
		DeleteItem.selfForbid.set(this,selfForbid);
	}/**

	 **
	 * Returns the value of {@link #selfNullify}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final DeleteItem getSelfNullify()
	{
		return DeleteItem.selfNullify.get(this);
	}/**

	 **
	 * Sets a new value for {@link #selfNullify}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setSelfNullify(final DeleteItem selfNullify)
	{
		DeleteItem.selfNullify.set(this,selfNullify);
	}/**

	 **
	 * Returns the value of {@link #selfCascade}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final DeleteItem getSelfCascade()
	{
		return DeleteItem.selfCascade.get(this);
	}/**

	 **
	 * Sets a new value for {@link #selfCascade}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setSelfCascade(final DeleteItem selfCascade)
	{
		DeleteItem.selfCascade.set(this,selfCascade);
	}/**

	 **
	 * Returns the value of {@link #selfCascade2}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final DeleteItem getSelfCascade2()
	{
		return DeleteItem.selfCascade2.get(this);
	}/**

	 **
	 * Sets a new value for {@link #selfCascade2}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setSelfCascade2(final DeleteItem selfCascade2)
	{
		DeleteItem.selfCascade2.set(this,selfCascade2);
	}/**

	 **
	 * Returns the value of {@link #otherForbid}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final DeleteOtherItem getOtherForbid()
	{
		return DeleteItem.otherForbid.get(this);
	}/**

	 **
	 * Sets a new value for {@link #otherForbid}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setOtherForbid(final DeleteOtherItem otherForbid)
	{
		DeleteItem.otherForbid.set(this,otherForbid);
	}/**

	 **
	 * Returns the value of {@link #otherNullify}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final DeleteOtherItem getOtherNullify()
	{
		return DeleteItem.otherNullify.get(this);
	}/**

	 **
	 * Sets a new value for {@link #otherNullify}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setOtherNullify(final DeleteOtherItem otherNullify)
	{
		DeleteItem.otherNullify.set(this,otherNullify);
	}/**

	 **
	 * Returns the value of {@link #otherCascade}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final DeleteOtherItem getOtherCascade()
	{
		return DeleteItem.otherCascade.get(this);
	}/**

	 **
	 * Sets a new value for {@link #otherCascade}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setOtherCascade(final DeleteOtherItem otherCascade)
	{
		DeleteItem.otherCascade.set(this,otherCascade);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for deleteItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	static final com.exedio.cope.Type<DeleteItem> TYPE = newType(DeleteItem.class)
;}
