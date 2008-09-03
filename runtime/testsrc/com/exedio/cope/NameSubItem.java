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

@CopeID("NameSubItemX")
public class NameSubItem extends NameLongItem
{
	@CopeSchemaType(dialect="hsqldbx", type="zack")
	static final IntegerField unique = new IntegerField().unique();
	static final IntegerField integer = new IntegerField();
	static final ItemField<NameSubItem> item = newItemField(NameSubItem.class);
	static final UniqueConstraint integers = new UniqueConstraint(integer, item);
	
	@CopeSchemaName("uniqueY")
	@CopeSchemaType(dialect="hsqldb", type="int") // instead of integer
	static final IntegerField uniqueX = new IntegerField().unique();
	@CopeSchemaName("integerY")
	static final IntegerField integerX = new IntegerField();
	@CopeSchemaName("itemY")
	static final ItemField<NameSubItem> itemX = newItemField(NameSubItem.class);
	@CopeSchemaName("integersY")
	static final UniqueConstraint integersX = new UniqueConstraint(integerX, itemX);
	
	/**

	 **
	 * Creates a new NameSubItem with all the fields initially needed.
	 * @param code the initial value for field {@link #code}.
	 * @param unique the initial value for field {@link #unique}.
	 * @param integer the initial value for field {@link #integer}.
	 * @param item the initial value for field {@link #item}.
	 * @param uniqueX the initial value for field {@link #uniqueX}.
	 * @param integerX the initial value for field {@link #integerX}.
	 * @param itemX the initial value for field {@link #itemX}.
	 * @throws com.exedio.cope.MandatoryViolationException if code, item, itemX is null.
	 * @throws com.exedio.cope.StringLengthViolationException if code violates its length constraint.
	 * @throws com.exedio.cope.UniqueViolationException if code, unique, integer, item, uniqueX, integerX, itemX is not unique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	NameSubItem(
				final java.lang.String code,
				final int unique,
				final int integer,
				final com.exedio.cope.NameSubItem item,
				final int uniqueX,
				final int integerX,
				final com.exedio.cope.NameSubItem itemX)
			throws
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.StringLengthViolationException,
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			NameSubItem.code.map(code),
			NameSubItem.unique.map(unique),
			NameSubItem.integer.map(integer),
			NameSubItem.item.map(item),
			NameSubItem.uniqueX.map(uniqueX),
			NameSubItem.integerX.map(integerX),
			NameSubItem.itemX.map(itemX),
		});
	}/**

	 **
	 * Creates a new NameSubItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private NameSubItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@SuppressWarnings("unused") private NameSubItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of {@link #unique}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final int getUnique()
	{
		return NameSubItem.unique.getMandatory(this);
	}/**

	 **
	 * Sets a new value for {@link #unique}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setUnique(final int unique)
			throws
				com.exedio.cope.UniqueViolationException
	{
		NameSubItem.unique.set(this,unique);
	}/**

	 **
	 * Finds a nameSubItem by it's {@link #unique}.
	 * @param unique shall be equal to field {@link #unique}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.for public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	static final NameSubItem forUnique(final int unique)
	{
		return NameSubItem.unique.searchUnique(NameSubItem.class,unique);
	}/**

	 **
	 * Finds a nameSubItem by it's {@link #unique}.
	 * @deprecated use for{@link #unique} instead.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.findBy public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	@Deprecated
	static final NameSubItem findByUnique(final int unique)
	{
		return NameSubItem.unique.searchUnique(NameSubItem.class,unique);
	}/**

	 **
	 * Returns the value of {@link #integer}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final int getInteger()
	{
		return NameSubItem.integer.getMandatory(this);
	}/**

	 **
	 * Sets a new value for {@link #integer}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setInteger(final int integer)
			throws
				com.exedio.cope.UniqueViolationException
	{
		NameSubItem.integer.set(this,integer);
	}/**

	 **
	 * Returns the value of {@link #item}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final com.exedio.cope.NameSubItem getItem()
	{
		return NameSubItem.item.get(this);
	}/**

	 **
	 * Sets a new value for {@link #item}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setItem(final com.exedio.cope.NameSubItem item)
			throws
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		NameSubItem.item.set(this,item);
	}/**

	 **
	 * Finds a nameSubItem by it's unique fields.
	 * @param integer shall be equal to field {@link #integer}.
	 * @param item shall be equal to field {@link #item}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	static final NameSubItem forIntegers(final int integer,final NameSubItem item)
	{
		return NameSubItem.integers.searchUnique(NameSubItem.class,integer,item);
	}/**

	 **
	 * Finds a nameSubItem by it's unique fields.
	 * @deprecated use forIntegers instead.
	 * @param integer shall be equal to field {@link #integer}.
	 * @param item shall be equal to field {@link #item}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@Deprecated
	static final NameSubItem findByIntegers(final int integer,final NameSubItem item)
	{
		return NameSubItem.integers.searchUnique(NameSubItem.class,integer,item);
	}/**

	 **
	 * Returns the value of {@link #uniqueX}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final int getUniqueX()
	{
		return NameSubItem.uniqueX.getMandatory(this);
	}/**

	 **
	 * Sets a new value for {@link #uniqueX}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setUniqueX(final int uniqueX)
			throws
				com.exedio.cope.UniqueViolationException
	{
		NameSubItem.uniqueX.set(this,uniqueX);
	}/**

	 **
	 * Finds a nameSubItem by it's {@link #uniqueX}.
	 * @param uniqueX shall be equal to field {@link #uniqueX}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.for public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	static final NameSubItem forUniqueX(final int uniqueX)
	{
		return NameSubItem.uniqueX.searchUnique(NameSubItem.class,uniqueX);
	}/**

	 **
	 * Finds a nameSubItem by it's {@link #uniqueX}.
	 * @deprecated use for{@link #uniqueX} instead.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.findBy public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	@Deprecated
	static final NameSubItem findByUniqueX(final int uniqueX)
	{
		return NameSubItem.uniqueX.searchUnique(NameSubItem.class,uniqueX);
	}/**

	 **
	 * Returns the value of {@link #integerX}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final int getIntegerX()
	{
		return NameSubItem.integerX.getMandatory(this);
	}/**

	 **
	 * Sets a new value for {@link #integerX}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setIntegerX(final int integerX)
			throws
				com.exedio.cope.UniqueViolationException
	{
		NameSubItem.integerX.set(this,integerX);
	}/**

	 **
	 * Returns the value of {@link #itemX}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final com.exedio.cope.NameSubItem getItemX()
	{
		return NameSubItem.itemX.get(this);
	}/**

	 **
	 * Sets a new value for {@link #itemX}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setItemX(final com.exedio.cope.NameSubItem itemX)
			throws
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		NameSubItem.itemX.set(this,itemX);
	}/**

	 **
	 * Finds a nameSubItem by it's unique fields.
	 * @param integerX shall be equal to field {@link #integerX}.
	 * @param itemX shall be equal to field {@link #itemX}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	static final NameSubItem forIntegersX(final int integerX,final NameSubItem itemX)
	{
		return NameSubItem.integersX.searchUnique(NameSubItem.class,integerX,itemX);
	}/**

	 **
	 * Finds a nameSubItem by it's unique fields.
	 * @deprecated use forIntegersX instead.
	 * @param integerX shall be equal to field {@link #integerX}.
	 * @param itemX shall be equal to field {@link #itemX}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@Deprecated
	static final NameSubItem findByIntegersX(final int integerX,final NameSubItem itemX)
	{
		return NameSubItem.integersX.searchUnique(NameSubItem.class,integerX,itemX);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for nameSubItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<NameSubItem> TYPE = newType(NameSubItem.class)
;}
