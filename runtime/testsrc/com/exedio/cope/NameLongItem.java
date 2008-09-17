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

@CopeSchemaName("NameLongItem")
@CopeID("NameLongNameLongNameLongNameLongNameLongNameLongItem")
class NameLongItem extends Item
{

	static final StringField code = new StringField().unique();

	static final StringField codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName =
		new StringField().optional().unique();

	static final ItemField<NameLongItem> pointerLoooooooooooooooooooooooooooooooooooooooooooooooooooongName =
		newItemField(NameLongItem.class, NULLIFY);
	
	/**

	 **
	 * Creates a new NameLongItem with all the fields initially needed.
	 * @param code the initial value for field {@link #code}.
	 * @throws com.exedio.cope.MandatoryViolationException if code is null.
	 * @throws com.exedio.cope.StringLengthViolationException if code violates its length constraint.
	 * @throws com.exedio.cope.UniqueViolationException if code is not unique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	NameLongItem(
				final java.lang.String code)
			throws
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.StringLengthViolationException,
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			NameLongItem.code.map(code),
		});
	}/**

	 **
	 * Creates a new NameLongItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	protected NameLongItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	protected NameLongItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of {@link #code}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getCode()
	{
		return NameLongItem.code.get(this);
	}/**

	 **
	 * Sets a new value for {@link #code}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setCode(final java.lang.String code)
			throws
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException,
				com.exedio.cope.StringLengthViolationException
	{
		NameLongItem.code.set(this,code);
	}/**

	 **
	 * Finds a nameLongItem by it's {@link #code}.
	 * @param code shall be equal to field {@link #code}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.for public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	static final NameLongItem forCode(final java.lang.String code)
	{
		return NameLongItem.code.searchUnique(NameLongItem.class,code);
	}/**

	 **
	 * Finds a nameLongItem by it's {@link #code}.
	 * @deprecated use for{@link #code} instead.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.findBy public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	@Deprecated
	static final NameLongItem findByCode(final java.lang.String code)
	{
		return NameLongItem.code.searchUnique(NameLongItem.class,code);
	}/**

	 **
	 * Returns the value of {@link #codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getCodeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName()
	{
		return NameLongItem.codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName.get(this);
	}/**

	 **
	 * Sets a new value for {@link #codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setCodeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName(final java.lang.String codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName)
			throws
				com.exedio.cope.UniqueViolationException,
				com.exedio.cope.StringLengthViolationException
	{
		NameLongItem.codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName.set(this,codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName);
	}/**

	 **
	 * Finds a nameLongItem by it's {@link #codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName}.
	 * @param codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName shall be equal to field {@link #codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.for public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	static final NameLongItem forCodeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName(final java.lang.String codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName)
	{
		return NameLongItem.codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName.searchUnique(NameLongItem.class,codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName);
	}/**

	 **
	 * Finds a nameLongItem by it's {@link #codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName}.
	 * @deprecated use for{@link #codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName} instead.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.findBy public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	@Deprecated
	static final NameLongItem findByCodeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName(final java.lang.String codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName)
	{
		return NameLongItem.codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName.searchUnique(NameLongItem.class,codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName);
	}/**

	 **
	 * Returns the value of {@link #pointerLoooooooooooooooooooooooooooooooooooooooooooooooooooongName}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final com.exedio.cope.NameLongItem getPointerLoooooooooooooooooooooooooooooooooooooooooooooooooooongName()
	{
		return NameLongItem.pointerLoooooooooooooooooooooooooooooooooooooooooooooooooooongName.get(this);
	}/**

	 **
	 * Sets a new value for {@link #pointerLoooooooooooooooooooooooooooooooooooooooooooooooooooongName}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setPointerLoooooooooooooooooooooooooooooooooooooooooooooooooooongName(final com.exedio.cope.NameLongItem pointerLoooooooooooooooooooooooooooooooooooooooooooooooooooongName)
	{
		NameLongItem.pointerLoooooooooooooooooooooooooooooooooooooooooooooooooooongName.set(this,pointerLoooooooooooooooooooooooooooooooooooooooooooooooooooongName);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for nameLongItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	static final com.exedio.cope.Type<NameLongItem> TYPE = newType(NameLongItem.class)
;}