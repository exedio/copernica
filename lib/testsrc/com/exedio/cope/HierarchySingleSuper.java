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
public abstract class HierarchySingleSuper extends Item
{
	public static final IntegerAttribute superInt = new IntegerAttribute(OPTIONAL);
	
/**

	 **
	 * Creates a new HierarchySingleSuper with all the attributes initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <code>@cope.constructor public|package|protected|private|none</code> in the class comment and <code>@cope.initial</code> in the comment of attributes.
	 *
 */public HierarchySingleSuper()
	{
		this(new com.exedio.cope.AttributeValue[]{
		});
	}/**

	 **
	 * Creates a new HierarchySingleSuper and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.generic.constructor public|package|protected|private|none</code> in the class comment.
	 *
 */protected HierarchySingleSuper(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */protected HierarchySingleSuper(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #superInt}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 *
 */public final java.lang.Integer getSuperInt()
	{
		return HierarchySingleSuper.superInt.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #superInt}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 *
 */public final void setSuperInt(final java.lang.Integer superInt)
	{
		try
		{
			HierarchySingleSuper.superInt.set(this,superInt);
		}
		catch(com.exedio.cope.MandatoryViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
	}/**

	 **
	 * The persistent type information for hierarchySingleSuper.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.type public|package|protected|private|none</code> in the class comment.
	 *
 */public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(HierarchySingleSuper.class)
;}
