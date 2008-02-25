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

package com.exedio.cope.pattern;

import com.exedio.cope.Item;
import com.exedio.cope.StringField;
import com.exedio.cope.testmodel.WrapHash;

/**
 * @cope.generic.constructor package
 * @author Ralf Wiebicke
 */
public class HashItem extends Item
{
	static final StringField explicitExternalWrap = new StringField().optional();
	static final Hash explicitExternal = new WrapHash(explicitExternalWrap);

	static final Hash implicitExternal = new WrapHash(new StringField().optional());

	static final Hash internal = new WrapHash().optional();

/**

	 **
	 * Creates a new HashItem with all the fields initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	public HashItem()
	{
		this(new com.exedio.cope.SetValue[]{
		});
	}/**

	 **
	 * Creates a new HashItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	HashItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@SuppressWarnings("unused") private HashItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of {@link #explicitExternalWrap}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getExplicitExternalWrap()
	{
		return HashItem.explicitExternalWrap.get(this);
	}/**

	 **
	 * Sets a new value for {@link #explicitExternalWrap}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setExplicitExternalWrap(final java.lang.String explicitExternalWrap)
			throws
				com.exedio.cope.LengthViolationException
	{
		HashItem.explicitExternalWrap.set(this,explicitExternalWrap);
	}/**

	 **
	 * Returns whether the given value corresponds to the hash in {@link #explicitExternal}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.check public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final boolean checkExplicitExternal(final java.lang.String explicitExternal)
	{
		return HashItem.explicitExternal.check(this,explicitExternal);
	}/**

	 **
	 * Sets a new value for {@link #explicitExternal}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setExplicitExternal(final java.lang.String explicitExternal)
			throws
				com.exedio.cope.LengthViolationException
	{
		HashItem.explicitExternal.set(this,explicitExternal);
	}/**

	 **
	 * Returns the encoded hash value for hash {@link #explicitExternal}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getHash public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getExplicitExternalHash()
	{
		return HashItem.explicitExternal.getHash(this);
	}/**

	 **
	 * Sets the encoded hash value for hash {@link #explicitExternal}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setHash public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setExplicitExternalHash(final java.lang.String explicitExternal)
			throws
				com.exedio.cope.LengthViolationException
	{
		HashItem.explicitExternal.setHash(this,explicitExternal);
	}/**

	 **
	 * Returns whether the given value corresponds to the hash in {@link #implicitExternal}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.check public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final boolean checkImplicitExternal(final java.lang.String implicitExternal)
	{
		return HashItem.implicitExternal.check(this,implicitExternal);
	}/**

	 **
	 * Sets a new value for {@link #implicitExternal}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setImplicitExternal(final java.lang.String implicitExternal)
			throws
				com.exedio.cope.LengthViolationException
	{
		HashItem.implicitExternal.set(this,implicitExternal);
	}/**

	 **
	 * Returns the encoded hash value for hash {@link #implicitExternal}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getHash public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getImplicitExternalHash()
	{
		return HashItem.implicitExternal.getHash(this);
	}/**

	 **
	 * Sets the encoded hash value for hash {@link #implicitExternal}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setHash public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setImplicitExternalHash(final java.lang.String implicitExternal)
			throws
				com.exedio.cope.LengthViolationException
	{
		HashItem.implicitExternal.setHash(this,implicitExternal);
	}/**

	 **
	 * Returns whether the given value corresponds to the hash in {@link #internal}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.check public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final boolean checkInternal(final java.lang.String internal)
	{
		return HashItem.internal.check(this,internal);
	}/**

	 **
	 * Sets a new value for {@link #internal}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setInternal(final java.lang.String internal)
			throws
				com.exedio.cope.LengthViolationException
	{
		HashItem.internal.set(this,internal);
	}/**

	 **
	 * Returns the encoded hash value for hash {@link #internal}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getHash public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getInternalHash()
	{
		return HashItem.internal.getHash(this);
	}/**

	 **
	 * Sets the encoded hash value for hash {@link #internal}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setHash public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setInternalHash(final java.lang.String internal)
			throws
				com.exedio.cope.LengthViolationException
	{
		HashItem.internal.setHash(this,internal);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for hashItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<HashItem> TYPE = newType(HashItem.class)
;}
