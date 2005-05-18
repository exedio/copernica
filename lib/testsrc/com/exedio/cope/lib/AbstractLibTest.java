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

package com.exedio.cope.lib;

import com.exedio.cope.lib.junit.CopeLibTest;
import com.exedio.cope.testmodel.Main;

public abstract class AbstractLibTest extends CopeLibTest
{
	
	public static final Type[] modelTypes = Main.modelTypes;
	
	public AbstractLibTest()
	{
		super(Main.model);
	}
	
	final static Integer i1 = new Integer(1);
	final static Integer i2 = new Integer(2);
	final static Integer i3 = new Integer(3);
	final static Integer i4 = new Integer(4);
	final static Integer i5 = new Integer(5);
	final static Integer i6 = new Integer(6);
	final static Integer i7 = new Integer(7);
	final static Integer i8 = new Integer(8);
	
	final String pkString(final Item item)
	{
		return String.valueOf(item.getCopeType().getPrimaryKeyIterator().pk2id(((Item)item).pk));
	}

}
