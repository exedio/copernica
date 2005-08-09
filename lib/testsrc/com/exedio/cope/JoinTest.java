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
import com.exedio.cope.testmodel.PointerTargetItem;

public class JoinTest extends TestmodelTest
{
	PointerItem item1a;
	PointerItem item1b;
	PointerTargetItem item2a;
	PointerTargetItem item2b;

	protected void setUp() throws Exception
	{
		super.setUp();
		deleteOnTearDown(item2a = new PointerTargetItem("hallo"));
		deleteOnTearDown(item2b = new PointerTargetItem("bello"));
		deleteOnTearDown(item1a = new PointerItem("bello", item2a));
		deleteOnTearDown(item1b = new PointerItem("collo", item2b));
	}

	public void testJoin()
	{
		{
			final Query query = new Query(PointerTargetItem.TYPE, null);
			assertEquals(list(), query.getJoins());
			final Join join = query.join(PointerItem.TYPE, Cope.isNotNull(PointerItem.code));
			assertEquals(list(join), query.getJoins());
			assertContains(item2b, item2a, item2b, item2a, query.search());
		}
		{
			final Query query = new Query(PointerTargetItem.TYPE, null);
			query.join(PointerItem.TYPE, Cope.join(PointerItem.pointer));
			assertContains(item2b, item2a, query.search());
		}
		{
			final Query query = new Query(PointerItem.TYPE, PointerTargetItem.TYPE, null);
			query.join(PointerItem.TYPE, Cope.join(PointerItem.pointer));
			assertContains(item1b, item1a, query.search());
		}
		{
			final Query query = new Query(PointerTargetItem.TYPE, null);
			query.join(PointerItem.TYPE, Cope.equal(PointerItem.code, PointerTargetItem.code));
			assertContains(item2b, query.search());
		}
		{
			final Query query = new Query(PointerItem.TYPE, Cope.equal(PointerTargetItem.code, "hallo"));
			query.join(PointerTargetItem.TYPE, Cope.join(PointerItem.pointer));
			assertContains(item1a, query.search());
		}
		{
			final Query query = new Query(new Selectable[]{PointerTargetItem.code, PointerItem.TYPE, PointerItem.code}, PointerTargetItem.TYPE, null);
			query.join(PointerItem.TYPE, Cope.join(PointerItem.pointer));
			assertContains(
					list("bello", item1b, "collo"),
					list("hallo", item1a, "bello"),
					query.search());
		}
	}

}
