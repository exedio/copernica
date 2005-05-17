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

package com.exedio.cope.testmodel;

import com.exedio.cope.lib.Item;

/**
 * Another item not having any attribute.
 * @persistent
 * @author Ralf Wiebicke
 */
public class EmptyItem2 extends Item
{
/**

	 **
	 * Creates a new EmptyItem2 with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public EmptyItem2()
	{
		this(new com.exedio.cope.lib.AttributeValue[]{
		});
	}/**

	 **
	 * Creates a new EmptyItem2 and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.lib.Type#newItem Type.newItem}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */private EmptyItem2(final com.exedio.cope.lib.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.lib.util.ReactivationConstructorDummy,int)
	 *
 */private EmptyItem2(com.exedio.cope.lib.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * The persistent type information for emptyItem2.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(EmptyItem2.class)
;}
