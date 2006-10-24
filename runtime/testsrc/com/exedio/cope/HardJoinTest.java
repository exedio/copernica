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


public class HardJoinTest extends AbstractLibTest
{
	public HardJoinTest()
	{
		super(Main.hardJoinModel);
	}
	
	private HardJoinA3Item a;
	private HardJoinB3Item b;
	boolean mysql5;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		
		deleteOnTearDown(a = new HardJoinA3Item("a", 10, 11, 12));
		deleteOnTearDown(b = new HardJoinB3Item("b", 20, 21, 22));
		mysql5 = mysql && ((String)model.getDatabaseInfo().get("database.version")).endsWith("(5.0)");
	}
	
	private void reset()
	{
		a.setA1(10);
		a.setA2(11);
		a.setA3(12);
		b.setB1(20);
		b.setB2(21);
		b.setB3(22);
	}
	
	private void assert1x1(final IntegerField ax, final IntegerField bx, final int bv)
	{
		reset();
		final Query<HardJoinA3Item> q = a.TYPE.newQuery();
		q.join(b.TYPE, ax.equal(bx));
		assertEquals(list(), q.search());
		bx.set(b, bv);
		assertEquals(list(a), q.search());
	}
	
	public void test11()
	{
		if(hsqldb||mysql5||oracle||postgresql) // TODO
			return;
		
		//System.out.println("------------------------test11---------------------");

		assert1x1(a.a1, b.b1, 10);
		assert1x1(a.a1, b.b2, 10);
		assert1x1(a.a1, b.b3, 10);
		assert1x1(a.a2, b.b1, 11);
		assert1x1(a.a2, b.b2, 11);
		assert1x1(a.a2, b.b3, 11);
		assert1x1(a.a3, b.b1, 12);
		assert1x1(a.a3, b.b2, 12);
		assert1x1(a.a3, b.b3, 12);
	}
	
	private void assert2x1(final IntegerField a1, final IntegerField a2, final IntegerField bx, final int bv)
	{
		reset();
		final Query<HardJoinA3Item> q = a.TYPE.newQuery();
		q.join(b.TYPE, a1.equal(bx).and(a2.equal(bx)));
		assertEquals(list(), q.search());
		a1.set(a, bv);
		assertEquals(list(), q.search());
		a2.set(a, bv);
		assertEquals(list(a), q.search());
	}
	
	public void test2x1()
	{
		if(hsqldb||mysql5||oracle||postgresql) // TODO
			return;
		
		//System.out.println("------------------------test2x1---------------------");
		
		assert2x1(a.a1, a.a2, b.b1, 20);
		assert2x1(a.a1, a.a3, b.b1, 20);
		assert2x1(a.a2, a.a3, b.b1, 20);
		assert2x1(a.a1, a.a2, b.b2, 21);
		assert2x1(a.a1, a.a3, b.b2, 21);
		assert2x1(a.a2, a.a3, b.b2, 21);
		assert2x1(a.a1, a.a2, b.b3, 22);
		assert2x1(a.a1, a.a3, b.b3, 22);
		assert2x1(a.a2, a.a3, b.b3, 22);
	}
}
