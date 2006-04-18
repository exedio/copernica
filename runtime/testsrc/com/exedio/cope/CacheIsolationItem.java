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
 */
public class CacheIsolationItem extends Item
{
	public static final StringAttribute uniqueString = new StringAttribute( UNIQUE_OPTIONAL );
	
	public static final StringAttribute name = new StringAttribute( MANDATORY );
	
/**

	 **
	 * Creates a new CacheIsolationItem with all the attributes initially needed.
	 * @param name the initial value for attribute {@link #name}.
	 * @throws com.exedio.cope.LengthViolationException if name violates its length constraint.
	 * @throws com.exedio.cope.MandatoryViolationException if name is null.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of attributes.
	 */
	public CacheIsolationItem(
				final java.lang.String name)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			CacheIsolationItem.name.map(name),
		});
	}/**

	 **
	 * Creates a new CacheIsolationItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private CacheIsolationItem(final com.exedio.cope.SetValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private CacheIsolationItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #uniqueString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.String getUniqueString()
	{
		return CacheIsolationItem.uniqueString.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #uniqueString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setUniqueString(final java.lang.String uniqueString)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.UniqueViolationException
	{
		CacheIsolationItem.uniqueString.set(this,uniqueString);
	}/**

	 **
	 * Finds a cacheIsolationItem by it's unique attributes.
	 * @param uniqueString shall be equal to attribute {@link #uniqueString}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public static final CacheIsolationItem findByUniqueString(final java.lang.String uniqueString)
	{
		return (CacheIsolationItem)CacheIsolationItem.uniqueString.searchUnique(uniqueString);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #name}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.String getName()
	{
		return CacheIsolationItem.name.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #name}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setName(final java.lang.String name)
			throws
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.MandatoryViolationException
	{
		CacheIsolationItem.name.set(this,name);
	}/**

	 **
	 * The persistent type information for cacheIsolationItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(CacheIsolationItem.class)
;}
