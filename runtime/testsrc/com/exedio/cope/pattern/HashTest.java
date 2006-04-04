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

package com.exedio.cope.pattern;

import java.util.Arrays;

import com.exedio.cope.AbstractLibTest;
import com.exedio.cope.SetValue;
import com.exedio.cope.Feature;
import com.exedio.cope.Main;

public class HashTest extends AbstractLibTest
{
	public HashTest()
	{
		super(Main.hashModel);
	}
	
	HashItem item;
	
	public void setUp() throws Exception
	{
		super.setUp();
		deleteOnTearDown(item = new HashItem());
	}
	
	public void testExplicitExternal()
	{
		assertEquals(Arrays.asList(new Feature[]{
				item.explicitExternalWrap,
				item.explicitExternal,
				item.implicitExternal,
				item.implicitExternal.getStorage(),
				item.internal,
				item.internal.getStorage(),
			}), item.TYPE.getFeatures());

		assertEquals(item.TYPE, item.explicitExternal.getType());
		assertEquals("explicitExternal", item.explicitExternal.getName());
		assertEquals(item.explicitExternalWrap, item.explicitExternal.getStorage());
		assertEqualsUnmodifiable(list(item.explicitExternal), item.explicitExternalWrap.getPatterns());

		assertNull(item.getExplicitExternalWrap());
		assertTrue(item.checkExplicitExternal(null));
		assertTrue(!item.checkExplicitExternal("bing"));
		assertContains(item, item.TYPE.search(item.explicitExternal.equal(null)));
		assertContains(item.TYPE.search(item.explicitExternal.equal("bing")));
		assertContains(item.TYPE.search(item.explicitExternal.notEqual(null)));
		assertContains(item, item.TYPE.search(item.explicitExternal.notEqual("bing")));
		
		item.setExplicitExternalWrap("bello");
		assertEquals("bello", item.getExplicitExternalWrap());
		assertTrue(!item.checkExplicitExternal(null));
		assertTrue(!item.checkExplicitExternal("bello"));
		assertContains(item.TYPE.search(item.explicitExternal.equal(null)));
		assertContains(item.TYPE.search(item.explicitExternal.equal("bello")));
		assertContains(item, item.TYPE.search(item.explicitExternal.notEqual(null)));
		assertContains(item, item.TYPE.search(item.explicitExternal.notEqual("bello")));
		
		item.setExplicitExternal("knollo");
		assertEquals("[knollo]", item.getExplicitExternalWrap());
		assertTrue(!item.checkExplicitExternal(null));
		assertTrue(!item.checkExplicitExternal("bello"));
		assertTrue(item.checkExplicitExternal("knollo"));
		assertContains(item.TYPE.search(item.explicitExternal.equal(null)));
		assertContains(item, item.TYPE.search(item.explicitExternal.equal("knollo")));
		assertContains(item.TYPE.search(item.explicitExternal.equal("bello")));
		assertContains(item, item.TYPE.search(item.explicitExternal.notEqual(null)));
		assertContains(item.TYPE.search(item.explicitExternal.notEqual("knollo")));
		assertContains(item, item.TYPE.search(item.explicitExternal.notEqual("bello")));
	}
	
	public void testImplicitExternal()
	{
		assertEquals(item.TYPE, item.implicitExternal.getType());
		assertEquals("implicitExternal", item.implicitExternal.getName());
		assertEquals(item.TYPE, item.implicitExternal.getStorage().getType());
		assertEquals("implicitExternalHash", item.implicitExternal.getStorage().getName());
		assertEqualsUnmodifiable(list(item.implicitExternal), item.implicitExternal.getStorage().getPatterns());
		
		assertEquals(null, item.get(item.implicitExternal.getStorage()));
		assertTrue(item.checkImplicitExternal(null));
		assertFalse(item.checkImplicitExternal(""));
		assertFalse(item.checkImplicitExternal("zack"));

		item.setImplicitExternal("zack");
		assertEquals("[zack]", item.get(item.implicitExternal.getStorage()));
		assertFalse(item.checkImplicitExternal(null));
		assertFalse(item.checkImplicitExternal(""));
		assertTrue(item.checkImplicitExternal("zack"));
	}

	public void testInternal()
	{
		assertEquals(item.TYPE, item.internal.getType());
		assertEquals("internal", item.internal.getName());
		assertEquals(item.TYPE, item.internal.getStorage().getType());
		assertEquals("internalHash", item.internal.getStorage().getName());
		assertEqualsUnmodifiable(list(item.internal), item.internal.getStorage().getPatterns());
		
		assertEquals(null, item.get(item.internal.getStorage()));
		assertTrue(item.checkInternal(null));
		assertFalse(item.checkInternal(""));
		assertFalse(item.checkInternal("zack"));

		item.setInternal("zack");
		assertEquals("[zack]", item.get(item.internal.getStorage()));
		assertFalse(item.checkInternal(null));
		assertFalse(item.checkInternal(""));
		assertTrue(item.checkInternal("zack"));

		item.set(new SetValue[]{item.internal.map("zosch")});
		assertEquals("[zosch]", item.get(item.internal.getStorage()));
		assertFalse(item.checkInternal(null));
		assertFalse(item.checkInternal(""));
		assertFalse(item.checkInternal("zack"));
		assertTrue(item.checkInternal("zosch"));
		
		final HashItem item2 = new HashItem(new SetValue[]{item.internal.map("lets")});
		deleteOnTearDown(item2);
		assertEquals("[lets]", item2.get(item2.internal.getStorage()));
		assertFalse(item2.checkInternal(null));
		assertFalse(item2.checkInternal(""));
		assertTrue(item2.checkInternal("lets"));
		
		final HashItem item3 = (HashItem)HashItem.TYPE.newItem(new SetValue[]{item.internal.map("fetz")});
		deleteOnTearDown(item3);
		assertEquals("[fetz]", item3.get(item3.internal.getStorage()));
		assertFalse(item3.checkInternal(null));
		assertFalse(item3.checkInternal(""));
		assertTrue(item3.checkInternal("fetz"));
	}

}
