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


package com.exedio.cope.lib.util;

import java.util.Date;
import java.util.Iterator;

import com.exedio.cope.lib.AbstractLibTest;


public class PoolCounterTest extends AbstractLibTest
{
	public void testIt()
	{
		final Date before = new Date();
		final PoolCounter c = new PoolCounter();
		final Date after = new Date();
		assertWithin(before, after, c.start);
		
		final Iterator pi = c.getPools().iterator();
		final PoolCounter.Pool p2 = (PoolCounter.Pool)pi.next();
		assertIt(p2, 2, 0, 0, 0, 0, 0);
		
		c.get();
		assertIt(p2, 2, /*pool*/0, /*get*/1, /*put*/0, /*create*/1, /*destroy*/0);
		
		c.get();
		assertIt(p2, 2, /*pool*/0, /*get*/2, /*put*/0, /*create*/2, /*destroy*/0);
		
		c.put();
		assertIt(p2, 2, /*pool*/1, /*get*/2, /*put*/1, /*create*/2, /*destroy*/0);
		
		c.put();
		assertIt(p2, 2, /*pool*/2, /*get*/2, /*put*/2, /*create*/2, /*destroy*/0);
		
		c.put();
		assertIt(p2, 2, /*pool*/2, /*get*/2, /*put*/3, /*create*/2, /*destroy*/1);
	}
	
	static final void assertIt(final PoolCounter.Pool p, final int size, final int pool, final int getCounter, final int putCounter, final int createCounter, final int destroyCounter)
	{
		assertEquals(size, p.getSize());
		assertEquals(pool, p.getPool());
		assertEquals(getCounter, p.getGetCounter());
		assertEquals(putCounter, p.getPutCounter());
		assertEquals(createCounter, p.getCreateCounter());
		assertEquals(destroyCounter, p.getDestroyCounter());
	}

}
