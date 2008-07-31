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

package com.exedio.cope;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlusLongOrderTest extends AbstractRuntimeTest
{
	public PlusLongOrderTest()
	{
		super(PlusLongTest.MODEL);
	}
	
	PlusLongItem item1;
	PlusLongItem item2;
	PlusLongItem item3;
	
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		item1 = deleteOnTearDown(new PlusLongItem(1l, 6l, -1000l));
		item2 = deleteOnTearDown(new PlusLongItem(2l, 1l, -1000l));
		item3 = deleteOnTearDown(new PlusLongItem(6l, 2l, -1000l));
	}
	
	public void testSumOrder()
	{
		assertEquals(l7, item1.getPlusAB());
		assertEquals(l3, item2.getPlusAB());
		assertEquals(l8, item3.getPlusAB());
		assertEquals(new Long(-6000l), item1.getMultiplyBC());
		assertEquals(new Long(-1000l), item2.getMultiplyBC());
		assertEquals(new Long(-2000l), item3.getMultiplyBC());

		assertOrder(list(item1, item2, item3), item1.numA);
		assertOrder(list(item2, item3, item1), item1.numB);
		assertOrder(list(item2, item1, item3), item1.plusAB);
		assertOrder(list(item1, item3, item2), item1.multiplyBC);
	}

	private void assertOrder(final List<? extends Object> expectedOrder, final Function searchFunction)
	{
		final Query query = item1.TYPE.newQuery(null);
		query.setOrderBy(searchFunction, true);
		assertEquals(expectedOrder, query.search());

		final List<? extends Object> expectedReverseOrder = new ArrayList<Object>(expectedOrder);
		Collections.reverse(expectedReverseOrder);
		query.setOrderBy(searchFunction, false);
		assertEquals(expectedReverseOrder, query.search());
	}
}
