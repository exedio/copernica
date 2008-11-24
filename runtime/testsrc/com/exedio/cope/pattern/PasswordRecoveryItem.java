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

package com.exedio.cope.pattern;

import com.exedio.cope.Item;

public class PasswordRecoveryItem extends Item
{
	static final MD5Hash password = new MD5Hash();
	static final PasswordRecovery passwordRecovery = new PasswordRecovery(password);
	
	

	/**

	 **
	 * Creates a new PasswordRecoveryItem with all the fields initially needed.
	 * @param password the initial value for field {@link #password}.
	 * @throws com.exedio.cope.MandatoryViolationException if password is null.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	PasswordRecoveryItem(
				final java.lang.String password)
			throws
				com.exedio.cope.MandatoryViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			PasswordRecoveryItem.password.map(password),
		});
	}/**

	 **
	 * Creates a new PasswordRecoveryItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private PasswordRecoveryItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@SuppressWarnings("unused") private PasswordRecoveryItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns whether the given value corresponds to the hash in {@link #password}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.check public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final boolean checkPassword(final java.lang.String password)
	{
		return PasswordRecoveryItem.password.check(this,password);
	}/**

	 **
	 * Sets a new value for {@link #password}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setPassword(final java.lang.String password)
			throws
				com.exedio.cope.MandatoryViolationException
	{
		PasswordRecoveryItem.password.set(this,password);
	}/**

	 **
	 * Returns the encoded hash value for hash {@link #password}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getHash public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getPasswordHash()
	{
		return PasswordRecoveryItem.password.getHash(this);
	}/**

	 **
	 * Sets the encoded hash value for hash {@link #password}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setHash public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setPasswordHash(final java.lang.String password)
			throws
				com.exedio.cope.MandatoryViolationException
	{
		PasswordRecoveryItem.password.setHash(this,password);
	}/**

	 **
	 * @param expiryMillis the time span, after which this token will not be valid anymore, in milliseconds
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.issue public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final com.exedio.cope.pattern.PasswordRecovery.Token issuePasswordRecovery(final int expiryMillis)
	{
		return PasswordRecoveryItem.passwordRecovery.issue(this,expiryMillis);
	}/**

	 **
	 * @param secret a token secret for password recovery
	 * @return a new password, if the token was valid, otherwise null
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.redeem public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String redeemPasswordRecovery(final long secret)
	{
		return PasswordRecoveryItem.passwordRecovery.redeem(this,secret);
	}/**

	 **
	 * @return the number of tokens purged
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.purge public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	static final int purgePasswordRecovery()
	{
		return PasswordRecoveryItem.passwordRecovery.purge(PasswordRecoveryItem.class);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for passwordRecoveryItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<PasswordRecoveryItem> TYPE = newType(PasswordRecoveryItem.class)
;}
