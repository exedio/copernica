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
import java.util.HashMap;

import com.exedio.cope.AbstractLibTest;
import com.exedio.cope.Feature;
import com.exedio.cope.Main;
import com.exedio.cope.SetValue;

public class SerializerTest extends AbstractLibTest
{
	
	public SerializerTest()
	{
		super(Main.serializerModel);
	}

	SerializerItem item;
	
	public void setUp() throws Exception
	{
		super.setUp();
		deleteOnTearDown(item = new SerializerItem());
	}
	
	public void testSerializer()
	{
		// test model
		assertEquals(Arrays.asList(new Feature[]{
				item.integer.getSource(),
				item.integer,
				item.map.getSource(),
				item.map,
			}), item.TYPE.getFeatures());
		assertEquals(Arrays.asList(new Feature[]{
				item.integer.getSource(),
				item.integer,
				item.map.getSource(),
				item.map,
			}), item.TYPE.getDeclaredFeatures());

		assertEquals(item.TYPE, item.integer.getSource());
		assertEquals(item.TYPE, item.integer);
		assertEquals(item.TYPE, item.map.getSource());
		assertEquals(item.TYPE, item.map);
		assertEquals("integerData", item.integer.getSource().getName());
		assertEquals("integer", item.integer.getName());
		assertEquals("mapDate", item.map.getSource().getName());
		assertEquals("map", item.map.getName());

		assertEqualsUnmodifiable(list(item.integer), item.integer.getSource().getPatterns());
		assertEqualsUnmodifiable(list(item.map), item.map.getSource().getPatterns());
		
		final HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("key1a", "value1a");
		map1.put("key1b", "value1b");
		final HashMap<String, String> map2 = new HashMap<String, String>();
		map1.put("key2a", "value2a");
		map1.put("key2b", "value2b");

		assertEquals(null, item.getInteger());
		assertEquals(null, item.getMap());
		
		item.setInteger(11);
		assertEquals(new Integer(11), item.getInteger());

		item.setMap(map1);
		assertEquals(map1, item.getMap());
		assertNotSame(map1, item.getMap());
		
		item.set(new SetValue[]{
				item.integer.map(22),
				item.map.map(map2),
		});
		assertEquals(new Integer(22), item.getInteger());
		assertEquals(map2, item.getMap());
		assertNotSame(map2, item.getMap());
		
		item.setInteger(null);
		assertNull(item.getInteger());
		assertEquals(map2, item.getMap());
		
		item.setMap(null);
		assertNull(item.getInteger());
		assertNull(item.getMap());

		final SerializerItem item2 = new SerializerItem(new SetValue[]{
				item.integer.map(33),
				item.map.map(map1),
		});
		deleteOnTearDown(item2);
		assertEquals(new Integer(33), item2.getInteger());
		assertEquals(map1, item2.getMap());
		assertNotSame(map1, item2.getMap());
		
		final SerializerItem item3 = (SerializerItem)SerializerItem.TYPE.newItem(new SetValue[]{
				item.integer.map(44),
				item.map.map(map2),
		});
		deleteOnTearDown(item3);
		assertEquals(new Integer(44), item3.getInteger());
		assertEquals(map2, item3.getMap());
		assertNotSame(map2, item3.getMap());
	}
	
}
