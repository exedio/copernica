/*
 * Copyright (C) 2004-2005  exedio GmbH (www.exedio.com)
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
public class NameLongNameLongNameLongNameLongNameLongNameLongItem extends Item
{

	public static final StringAttribute code = new StringAttribute(UNIQUE);

	public static final StringAttribute codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName =
		new StringAttribute(UNIQUE_OPTIONAL);

	public static final ItemAttribute pointerLoooooooooooooooooooooooooooooooooooooooooooooooooooongName =
		new ItemAttribute(OPTIONAL, NameLongNameLongNameLongNameLongNameLongNameLongItem.class, NULLIFY);
	
/**

	 **
	 * Creates a new NameLongNameLongNameLongNameLongNameLongNameLongItem with all the attributes initially needed.
	 * @param code the initial value for attribute {@link #code}.
	 * @throws com.exedio.cope.MandatoryViolationException if code is null.
	 * @throws com.exedio.cope.UniqueViolationException if code is not unique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <code>@cope.constructor public|package|protected|private|none</code> in the class comment and <code>@cope.initial</code> in the comment of attributes.
	 */
	public NameLongNameLongNameLongNameLongNameLongNameLongItem(
				final java.lang.String code)
			throws
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.AttributeValue[]{
			new com.exedio.cope.AttributeValue(NameLongNameLongNameLongNameLongNameLongNameLongItem.code,code),
		});
		throwInitialMandatoryViolationException();
		throwInitialUniqueViolationException();
	}/**

	 **
	 * Creates a new NameLongNameLongNameLongNameLongNameLongNameLongItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.generic.constructor public|package|protected|private|none</code> in the class comment.
	 */
	private NameLongNameLongNameLongNameLongNameLongNameLongItem(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private NameLongNameLongNameLongNameLongNameLongNameLongItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #code}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.String getCode()
	{
		return NameLongNameLongNameLongNameLongNameLongNameLongItem.code.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #code}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setCode(final java.lang.String code)
			throws
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.UniqueViolationException
	{
		try
		{
			NameLongNameLongNameLongNameLongNameLongNameLongItem.code.set(this,code);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
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
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.String getCodeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName()
	{
		return NameLongNameLongNameLongNameLongNameLongNameLongItem.codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setCodeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName(final java.lang.String codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName)
			throws
				com.exedio.cope.UniqueViolationException
	{
		try
		{
			NameLongNameLongNameLongNameLongNameLongNameLongItem.codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName.set(this,codeLoooooooooooooooooooooooooooooooooooooooooooooooooooongName);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.MandatoryViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
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
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final NameLongNameLongNameLongNameLongNameLongNameLongItem getPointerLoooooooooooooooooooooooooooooooooooooooooooooooooooongName()
	{
		return (NameLongNameLongNameLongNameLongNameLongNameLongItem)NameLongNameLongNameLongNameLongNameLongNameLongItem.pointerLoooooooooooooooooooooooooooooooooooooooooooooooooooongName.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #pointerLoooooooooooooooooooooooooooooooooooooooooooooooooooongName}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setPointerLoooooooooooooooooooooooooooooooooooooooooooooooooooongName(final NameLongNameLongNameLongNameLongNameLongNameLongItem pointerLoooooooooooooooooooooooooooooooooooooooooooooooooooongName)
	{
		try
		{
			NameLongNameLongNameLongNameLongNameLongNameLongItem.pointerLoooooooooooooooooooooooooooooooooooooooooooooooooooongName.set(this,pointerLoooooooooooooooooooooooooooooooooooooooooooooooooooongName);
		}
		catch(com.exedio.cope.MandatoryViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
	}/**

	 **
	 * The persistent type information for nameLongNameLongNameLongNameLongNameLongNameLongItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.type public|package|protected|private|none</code> in the class comment.
	 */
	public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(NameLongNameLongNameLongNameLongNameLongNameLongItem.class)
;}
