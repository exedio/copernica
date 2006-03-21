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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.exedio.cope.testmodel.SumItem;


public class SumOrderTest extends TestmodelTest
{
	SumItem item1;
	SumItem item2;
	SumItem item3;
	
	public void setUp() throws Exception
	{
		super.setUp();
		deleteOnTearDown(item1 = new SumItem(1, 6, -1000));
		deleteOnTearDown(item2 = new SumItem(2, 1, -1000));
		deleteOnTearDown(item3 = new SumItem(6, 2, -1000));
	}
	
	public void testSumOrder()
	{
		assertEquals(i7, item1.getSum12());
		assertEquals(i3, item2.getSum12());
		assertEquals(i8, item3.getSum12());

		assertOrder(list(item1, item2, item3), item1.num1);
		assertOrder(list(item2, item3, item1), item1.num2);
		assertOrder(list(item2, item1, item3), item1.sum12);
	}

	private void assertOrder(final List<? extends Object> expectedOrder, final Function searchFunction)
	{
		final Query query = new Query(item1.TYPE, null);
		query.setOrderBy(searchFunction, true);
		assertEquals(expectedOrder, query.search());

		final List<? extends Object> expectedReverseOrder = new ArrayList<Object>(expectedOrder);
		Collections.reverse(expectedReverseOrder);
		query.setOrderBy(searchFunction, false);
		assertEquals(expectedReverseOrder, query.search());
	}
}
