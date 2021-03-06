/*
 * Copyright (C) 2004-2009  exedio GmbH (www.exedio.com)
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

package com.exedio.cope.testmodel;

import com.exedio.cope.Item;
import com.exedio.cope.StringField;

/**
 * An item having a unique final attribute.
 * @author Ralf Wiebicke
 */
public final class UniqueFinal extends Item
{
	/**
	 * An attribute that is unique and final.
	 */
	public static final StringField uniqueFinalString = new StringField().toFinal().optional().unique();

/**

	 **
	 * Creates a new UniqueFinal with all the fields initially needed.
	 * @param uniqueFinalString the initial value for field {@link #uniqueFinalString}.
	 * @throws com.exedio.cope.StringLengthViolationException if uniqueFinalString violates its length constraint.
	 * @throws com.exedio.cope.UniqueViolationException if uniqueFinalString is not unique.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	@javax.annotation.Generated("com.exedio.cope.instrument")
	public UniqueFinal(
				final java.lang.String uniqueFinalString)
			throws
				com.exedio.cope.StringLengthViolationException,
				com.exedio.cope.UniqueViolationException
	{
		this(new com.exedio.cope.SetValue<?>[]{
			UniqueFinal.uniqueFinalString.map(uniqueFinalString),
		});
	}/**

	 **
	 * Creates a new UniqueFinal and sets the given fields initially.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	@javax.annotation.Generated("com.exedio.cope.instrument")
	private UniqueFinal(final com.exedio.cope.SetValue<?>... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Returns the value of {@link #uniqueFinalString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	@javax.annotation.Generated("com.exedio.cope.instrument")
	public final java.lang.String getUniqueFinalString()
	{
		return UniqueFinal.uniqueFinalString.get(this);
	}/**

	 **
	 * Finds a uniqueFinal by it's {@link #uniqueFinalString}.
	 * @param uniqueFinalString shall be equal to field {@link #uniqueFinalString}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.for public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	@javax.annotation.Generated("com.exedio.cope.instrument")
	public static final UniqueFinal forUniqueFinalString(final java.lang.String uniqueFinalString)
	{
		return UniqueFinal.uniqueFinalString.searchUnique(UniqueFinal.class,uniqueFinalString);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@javax.annotation.Generated("com.exedio.cope.instrument")
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for uniqueFinal.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	@javax.annotation.Generated("com.exedio.cope.instrument")
	public static final com.exedio.cope.Type<UniqueFinal> TYPE = com.exedio.cope.TypesBound.newType(UniqueFinal.class);/**

	 **
	 * Activation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.ActivationParameters)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@javax.annotation.Generated("com.exedio.cope.instrument")
	private UniqueFinal(final com.exedio.cope.ActivationParameters ap){super(ap);
}}
