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
public class DeleteOtherItem extends Item
{

	public String name = null;

	public DeleteOtherItem(final String name)
	{
		this();
		this.name = name;
	}
	
	public String toString()
	{
		return name;
	}
	
	
/**

	 **
	 * Creates a new DeleteOtherItem with all the attributes initially needed.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public DeleteOtherItem()
	{
		this(new com.exedio.cope.AttributeValue[]{
		});
	}/**

	 **
	 * Creates a new DeleteOtherItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */private DeleteOtherItem(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 *
 */private DeleteOtherItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * The persistent type information for deleteOtherItem.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(DeleteOtherItem.class)
;}
