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

import com.exedio.cope.testmodel.PointerItem;
import com.exedio.cope.testmodel.PointerTargetItem;

public class JoinMultipleTest extends TestmodelTest
{
	PointerItem source;
	PointerTargetItem target1;
	PointerTargetItem target2;

	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		target1 = deleteOnTearDown(new PointerTargetItem("target1"));
		target2 = deleteOnTearDown(new PointerTargetItem("target2"));
		source = deleteOnTearDown(new PointerItem("source", target1));
		source.setPointer2(target2);
	}

	public void testMultipleJoin()
	{
		{
			final Query query = source.TYPE.newQuery(null);
			assertEqualsUnmodifiable(list(), query.getJoins());

			final Join join1 = query.join(target1.TYPE);
			assertEqualsUnmodifiable(list(join1), query.getJoins());
			join1.setCondition(source.pointer.equalTarget(join1));
			assertEqualsUnmodifiable(listg(source), query.search());

			final Join join2 = query.join(target2.TYPE);
			assertEqualsUnmodifiable(list(join1, join2), query.getJoins());
			join2.setCondition(source.pointer2.equalTarget(join2));
			assertEqualsUnmodifiable(list(source), query.search());

			query.setCondition(target1.code.equal(join1, "target1"));
			assertEqualsUnmodifiable(list(source), query.search());
		}
		{
			// test using BindItemFunction
			final Query query = source.TYPE.newQuery(null);
			assertEqualsUnmodifiable(list(), query.getJoins());

			final Join join1 = query.join(target1.TYPE);
			assertEqualsUnmodifiable(list(join1), query.getJoins());
			join1.setCondition(source.pointer.equal(target1.TYPE.getThis().bind(join1)));
			assertEqualsUnmodifiable(list(source), query.search());

			final Join join2 = query.join(target2.TYPE);
			assertEqualsUnmodifiable(list(join1, join2), query.getJoins());
			join2.setCondition(source.pointer2.equal(target2.TYPE.getThis().bind(join2)));
			assertEqualsUnmodifiable(list(source), query.search());

			query.setCondition(target1.code.equal(join1, "target1"));
			assertEqualsUnmodifiable(list(source), query.search());
			
			assertEquals(
					"select this from PointerItem " +
					"join PointerTargetItem p1 on pointer=p1.PointerTargetItem.this " +
					"join PointerTargetItem p2 on pointer2=p2.PointerTargetItem.this " +
					"where p1.PointerTargetItem.code='target1'",
					query.toString());
			
			// TODO test attributes with wrong join
			// TODO test when join is falsely null
			// TODO test with functions on joined types
		}
		{
			final Query query = source.TYPE.newQuery(null);
			final Join join1 = query.join(target1.TYPE);
			query.setCondition((target1.num1.plus(target1.num2)).greater(2));
			assertEquals("select this from PointerItem join PointerTargetItem p1 where plus(PointerTargetItem.num1,PointerTargetItem.num2)>'2'", query.toString());
			query.setCondition((target1.num1.plus(target1.num2)).bind(join1).greater(2));
			assertEquals("select this from PointerItem join PointerTargetItem p1 where p1.plus(PointerTargetItem.num1,PointerTargetItem.num2)>'2'", query.toString()); // TODO put join on fields
		}
	}
}
