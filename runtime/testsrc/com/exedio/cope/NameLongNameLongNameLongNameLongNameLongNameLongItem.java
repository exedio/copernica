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
 * @author Ralf Wiebicke
 */
public class NameLongNameLongNameLongNameLongNameLongNameLongItem extends Item
{

	public static final StringField code = new StringField(UNIQUE);

	public static final StringField codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName =
		new StringField(UNIQUE_OPTIONAL);

	public static final ItemField<NameLongNameLongNameLongNameLongNameLongNameLongItem> pointerLoooooooooooooooooooooooooooooooooooooooooooooooooooongName =
		newItemAttribute(OPTIONAL, NameLongNameLongNameLongNameLongNameLongNameLongItem.class, NULLIFY);
	
/**

	 **
	 * Creates a new NameLongNameLongNameLongNameLongNameLongNameLongItem with all the attributes initially needed.
	 * @param code the initial value for attribute {@link #code}.
	 * @throws com.exedio.cope.LengthViolationException if code violates its length constraint.
	 * @throws com.exedio.cope.MandatoryViolationException if code is null.
	 * @throws com.exedio.cope.UniqueViolationException if code is not unique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of attributes.
	 */
	public NameLongNameLongNameLongNameLongNameLongNameLongItem(
				final java.lang.String code)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			NameLongNameLongNameLongNameLongNameLongNameLongItem.code.map(code),
		});
	}/**

	 **
	 * Creates a new NameLongNameLongNameLongNameLongNameLongNameLongItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private NameLongNameLongNameLongNameLongNameLongNameLongItem(final com.exedio.cope.SetValue[] setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private NameLongNameLongNameLongNameLongNameLongNameLongItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #code}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.String getCode()
	{
		return NameLongNameLongNameLongNameLongNameLongNameLongItem.code.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #code}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setCode(final java.lang.String code)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		NameLongNameLongNameLongNameLongNameLongNameLongItem.code.set(this,code);
	}/**

	 **
	 * Finds a nameLongNameLongNameLongNameLongNameLongNameLongItem by it's unique attributes.
	 * @param code shall be equal to attribute {@link #code}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public static final NameLongNameLongNameLongNameLongNameLongNameLongItem findByCode(final java.lang.String code)
	{
		return (NameLongNameLongNameLongNameLongNameLongNameLongItem)NameLongNameLongNameLongNameLongNameLongNameLongItem.code.searchUnique(code);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.String getCodeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName()
	{
		return NameLongNameLongNameLongNameLongNameLongNameLongItem.codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setCodeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName(final java.lang.String codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.UniqueViolationException
	{
		NameLongNameLongNameLongNameLongNameLongNameLongItem.codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName.set(this,codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName);
	}/**

	 **
	 * Finds a nameLongNameLongNameLongNameLongNameLongNameLongItem by it's unique attributes.
	 * @param codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName shall be equal to attribute {@link #codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public static final NameLongNameLongNameLongNameLongNameLongNameLongItem findByCodeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName(final java.lang.String codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName)
	{
		return (NameLongNameLongNameLongNameLongNameLongNameLongItem)NameLongNameLongNameLongNameLongNameLongNameLongItem.codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName.searchUnique(codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #pointerLoooooooooooooooooooooooooooooooooooooooooooooooooooongName}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final NameLongNameLongNameLongNameLongNameLongNameLongItem getPointerLoooooooooooooooooooooooooooooooooooooooooooooooooooongName()
	{
		return NameLongNameLongNameLongNameLongNameLongNameLongItem.pointerLoooooooooooooooooooooooooooooooooooooooooooooooooooongName.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #pointerLoooooooooooooooooooooooooooooooooooooooooooooooooooongName}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setPointerLoooooooooooooooooooooooooooooooooooooooooooooooooooongName(final NameLongNameLongNameLongNameLongNameLongNameLongItem pointerLoooooooooooooooooooooooooooooooooooooooooooooooooooongName)
	{
		NameLongNameLongNameLongNameLongNameLongNameLongItem.pointerLoooooooooooooooooooooooooooooooooooooooooooooooooooongName.set(this,pointerLoooooooooooooooooooooooooooooooooooooooooooooooooooongName);
	}/**

	 **
	 * The persistent type information for nameLongNameLongNameLongNameLongNameLongNameLongItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<NameLongNameLongNameLongNameLongNameLongNameLongItem> TYPE = newType(NameLongNameLongNameLongNameLongNameLongNameLongItem.class)
;}
