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

import com.exedio.cope.testmodel.PointerItem;
import com.exedio.cope.testmodel.PointerItem2;

public class JoinOuterTest extends TestmodelTest
{
	PointerItem leftJoined;
	PointerItem leftLonely;
	PointerItem2 rightJoined;
	PointerItem2 rightLonely;

	protected void setUp() throws Exception
	{
		super.setUp();
		deleteOnTearDown(rightLonely = new PointerItem2("right"));
		deleteOnTearDown(rightJoined = new PointerItem2("joined"));
		deleteOnTearDown(leftJoined = new PointerItem("joined", rightJoined));
		deleteOnTearDown(leftLonely = new PointerItem("left", rightJoined));
	}

	public void testJoin()
	{
		{
			final Query query = new Query(PointerItem.TYPE, null);
			assertEquals(null, query.getJoins());
			final Join join = query.join(PointerItem2.TYPE, Cope.equal(PointerItem.code, PointerItem2.code));
			assertEquals(list(join), query.getJoins());
			assertContains(leftJoined, query.search());
		}
		{
			final Query query = new Query(PointerItem.TYPE, null);
			assertEquals(null, query.getJoins());
			final Join join = query.joinOuterLeft(PointerItem2.TYPE, Cope.equal(PointerItem.code, PointerItem2.code));
			assertEquals(list(join), query.getJoins());
			assertContains(leftJoined, leftLonely, query.search());
		}
		{
			final Query query = new Query(PointerItem.TYPE, null);
			assertEquals(null, query.getJoins());
			final Join join = query.joinOuterRight(PointerItem2.TYPE, Cope.equal(PointerItem.code, PointerItem2.code));
			assertEquals(list(join), query.getJoins());
			if(hsqldb)
			{
				try
				{
					query.search();
					fail("should have thrown RuntimeException");
				}
				catch(RuntimeException e)
				{
					assertEquals("hsqldb not support right outer joins", e.getMessage());
				}
			}
			else
			{
				assertContains(leftJoined, null, query.search());
			}
		}
	}

}
