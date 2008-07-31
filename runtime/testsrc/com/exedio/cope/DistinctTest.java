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

import java.util.List;

import com.exedio.dsmf.SQLRuntimeException;

public class DistinctTest extends AbstractRuntimeTest
{
	public DistinctTest()
	{
		super(PlusIntegerTest.MODEL);
	}
	
	PlusIntegerItem item1, item2, item3, item4;
	
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		item1 = deleteOnTearDown(new PlusIntegerItem(1, 2, 0));
		item2 = deleteOnTearDown(new PlusIntegerItem(1, 3, 0));
		item3 = deleteOnTearDown(new PlusIntegerItem(1, 4, 0));
		item4 = deleteOnTearDown(new PlusIntegerItem(1, 4, 0));
		item4 = deleteOnTearDown(new PlusIntegerItem(2, 4, 0));
	}
	
	public void testDistinct()
	{
		{
			final Query<List> q = new Query<List>(new Function[]{item1.numB}, item1.TYPE, null);
			assertContains(2, 3, 4, 4, 4, q.search());
			assertEquals(5, q.total());
			q.setDistinct(true);
			assertContains(2, 3, 4, q.search());
			assertEquals(3, q.total());
		}
		{
			final Query<List> q = new Query<List>(new Function[]{item1.numA, item1.numB}, item1.TYPE, null);
			assertContains(
					list(1, 2),
					list(1, 3),
					list(1, 4),
					list(1, 4),
					list(2, 4),
				q.search());
			assertEquals(5, q.total());
			q.setDistinct(true);
			assertContains(
					list(1, 2),
					list(1, 3),
					list(1, 4),
					list(2, 4),
				q.search());
			if(!postgresql) // makes transaction invalid (see Database#needsSavepoint)
			{
				try
				{
					assertEquals(4, q.total());
					assertTrue("statement above fails on all databases but mysql", mysql);
				}
				catch(SQLRuntimeException e)
				{
					assertFalse("statement above fails on all databases but mysql", mysql);
					//e.printStackTrace();
				}
			}
		}
	}
}
