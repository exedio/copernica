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
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.exedio.cope.AbstractLibTest;
import com.exedio.cope.AttributeValue;
import com.exedio.cope.DateAttribute;
import com.exedio.cope.Feature;
import com.exedio.cope.FunctionAttribute;
import com.exedio.cope.Main;
import com.exedio.cope.StringAttribute;

public class VectorTest extends AbstractLibTest
{
	
	public VectorTest()
	{
		super(Main.vectorModel);
	}

	VectorItem item;
	
	public void setUp() throws Exception
	{
		super.setUp();
		deleteOnTearDown(item = new VectorItem(1, 2, 3));
	}
	
	public void testVector()
	{
		// test model
		assertEquals(Arrays.asList(new Feature[]{
				item.num1,
				item.num2,
				item.num3,
				item.nums,
				item.dates,
				(Feature)item.dates.getSources().get(0),
				(Feature)item.dates.getSources().get(1),
				item.strings,
				(Feature)item.strings.getSources().get(0),
				(Feature)item.strings.getSources().get(1),
				(Feature)item.strings.getSources().get(2),
				(Feature)item.strings.getSources().get(3),
			}), item.TYPE.getFeatures());

		assertEquals(item.TYPE, item.num1.getType());
		assertEquals(item.TYPE, item.num2.getType());
		assertEquals(item.TYPE, item.num3.getType());
		assertEquals("num1", item.num1.getName());
		assertEquals("num2", item.num2.getName());
		assertEquals("num3", item.num3.getName());
		assertEquals(item.TYPE, item.nums.getType());
		assertEquals("nums", item.nums.getName());
		assertEqualsUnmodifiable(list(item.num1, item.num2, item.num3), item.nums.getSources());
		assertEqualsUnmodifiable(list(item.nums), item.num1.getPatterns());
		assertEqualsUnmodifiable(list(item.nums), item.num2.getPatterns());
		assertEqualsUnmodifiable(list(item.nums), item.num3.getPatterns());

		assertEquals(item.TYPE, item.dates.getType());
		assertEquals("dates", item.dates.getName());
		final List<FunctionAttribute> dateSources = item.dates.getSources();
		assertEquals(2, dateSources.size());
		assertUnmodifiable(dateSources);
		final Iterator dateSourcesIterator = dateSources.iterator();
		final DateAttribute date1 = assertDate(dateSourcesIterator, 1);
		final DateAttribute date2 = assertDate(dateSourcesIterator, 2);
		assertTrue(!dateSourcesIterator.hasNext());
		assertEqualsUnmodifiable(list(item.dates), date1.getPatterns());
		assertEqualsUnmodifiable(list(item.dates), date2.getPatterns());

		assertEquals(item.TYPE, item.strings.getType());
		assertEquals("strings", item.strings.getName());
		final List<FunctionAttribute> stringSources = item.strings.getSources();
		assertEquals(4, stringSources.size());
		assertUnmodifiable(stringSources);
		final Iterator stringSourcesIterator = stringSources.iterator();
		final StringAttribute string1 = assertString(stringSourcesIterator, 1);
		final StringAttribute string2 = assertString(stringSourcesIterator, 2);
		final StringAttribute string3 = assertString(stringSourcesIterator, 3);
		final StringAttribute string4 = assertString(stringSourcesIterator, 4);
		assertTrue(!stringSourcesIterator.hasNext());
		assertEqualsUnmodifiable(list(item.strings), string1.getPatterns());
		assertEqualsUnmodifiable(list(item.strings), string2.getPatterns());
		assertEqualsUnmodifiable(list(item.strings), string3.getPatterns());
		assertEqualsUnmodifiable(list(item.strings), string4.getPatterns());

		assertEquals(
				list(item.num1, item.num2, item.num3, date1, date2, string1, string2, string3, string4),
				item.TYPE.getDeclaredAttributes());

		assertEquals(i1, item.getNum1());
		assertEquals(i2, item.getNum2());
		assertEquals(i3, item.getNum3());
		assertContains(item, item.TYPE.search(item.nums.equal(list(i1, i2, i3))));
		assertContains(item.TYPE.search(item.nums.equal(list(i1, i2))));
		assertContains(item.TYPE.search(item.nums.notEqual(list(i1, i2, i3))));
		assertContains(item, item.TYPE.search(item.nums.notEqual(list(i1, i2))));
		assertContains(item, item.TYPE.search(item.nums.contains(i1)));
		assertContains(item, item.TYPE.search(item.nums.contains(i2)));
		assertContains(item, item.TYPE.search(item.nums.contains(i3)));
		assertContains(item.TYPE.search(item.nums.contains(null)));
		
		item.setNums(list(i3, i2, i1));
		assertEquals(i3, item.getNum1());
		assertEquals(i2, item.getNum2());
		assertEquals(i1, item.getNum3());

		item.setNums(list(i2, i1));
		assertEquals(i2, item.getNum1());
		assertEquals(i1, item.getNum2());
		assertEquals(null, item.getNum3());

		item.setNums(list());
		assertEquals(null, item.getNum1());
		assertEquals(null, item.getNum2());
		assertEquals(null, item.getNum3());
		assertContains(item, item.TYPE.search(item.nums.equal(list())));
		assertContains(item.TYPE.search(item.nums.equal(list(i1))));
		assertContains(item.TYPE.search(item.nums.notEqual(list())));
		assertContains(item, item.TYPE.search(item.nums.notEqual(list(i1))));
		assertContains(item.TYPE.search(item.nums.contains(i1)));
		assertContains(item.TYPE.search(item.nums.contains(i2)));
		assertContains(item.TYPE.search(item.nums.contains(i3)));
		assertContains(item, item.TYPE.search(item.nums.contains(null)));
		
		item.setNum1(i1);
		item.setNum2(i2);
		item.setNum3(i3);
		assertEquals(list(i1, i2, i3), item.getNums());
		
		item.setNum1(null);
		item.setNum2(i2);
		item.setNum3(i3);
		assertEquals(list(i2, i3), item.getNums());
		
		item.setNum1(i1);
		item.setNum2(null);
		item.setNum3(i3);
		assertEquals(list(i1, i3), item.getNums());
		
		item.setNum1(null);
		item.setNum2(null);
		item.setNum3(null);
		assertEquals(list(), item.getNums());
		
		final Date ts1 = new Date(8172541283976l);
		final Date ts2 = new Date(3874656234632l);
		item.setDates(list(ts1, ts2));
		assertEquals(list(ts1, ts2), item.getDates());
		assertEquals(ts1, item.get(date1));
		assertEquals(ts2, item.get(date2));
		
		item.setStrings(list("hallo", "bello"));
		assertEquals(list("hallo", "bello"), item.getStrings());
		assertEquals("hallo", item.get(string1));
		assertEquals("bello", item.get(string2));
		assertEquals(null, item.get(string3));
		assertEquals(null, item.get(string4));
		assertContains(item.TYPE.search(item.strings.equal(list())));
		assertContains(item.TYPE.search(item.strings.equal(list("hallo"))));
		assertContains(item, item.TYPE.search(item.strings.equal(list("hallo", "bello"))));
		assertContains(item.TYPE.search(item.strings.equal(list("bello", "hallo", "zollo"))));
		assertContains(item.TYPE.search(item.strings.equal(list("bello", "hallo"))));
		assertContains(item, item.TYPE.search(item.strings.notEqual(list())));
		assertContains(item, item.TYPE.search(item.strings.notEqual(list("hallo"))));
		assertContains(item.TYPE.search(item.strings.notEqual(list("hallo", "bello"))));
		assertContains(item, item.TYPE.search(item.strings.notEqual(list("bello", "hallo", "zollo"))));
		assertContains(item, item.TYPE.search(item.strings.notEqual(list("bello", "hallo"))));
		assertContains(item, item.TYPE.search(item.strings.contains("hallo")));
		assertContains(item, item.TYPE.search(item.strings.contains("bello")));
		assertContains(item, item.TYPE.search(item.strings.contains(null)));
		assertContains(item.TYPE.search(item.strings.contains("zollo")));
		
		item.set(new AttributeValue[]{item.strings.map(list("zicko", "zacko", "zocko"))});
		assertEquals(list("zicko", "zacko", "zocko"), item.getStrings());
		assertEquals("zicko", item.get(string1));
		assertEquals("zacko", item.get(string2));
		assertEquals("zocko", item.get(string3));
		assertEquals(null, item.get(string4));
		
		final VectorItem item2 = new VectorItem(new AttributeValue[]{item.strings.map(list("lets1", "lets2", "lets3", "lets4"))});
		deleteOnTearDown(item2);
		assertEquals(list("lets1", "lets2", "lets3", "lets4"), item2.getStrings());
		assertEquals("lets1", item2.get(string1));
		assertEquals("lets2", item2.get(string2));
		assertEquals("lets3", item2.get(string3));
		assertEquals("lets4", item2.get(string4));
		
		final VectorItem item3 = (VectorItem)VectorItem.TYPE.newItem(new AttributeValue[]{item.strings.map(list("fetz1", null, null, null))});
		deleteOnTearDown(item3);
		assertEquals(list("fetz1"), item3.getStrings());
		assertEquals("fetz1", item3.get(string1));
		assertEquals(null, item3.get(string2));
		assertEquals(null, item3.get(string3));
		assertEquals(null, item3.get(string4));
	}
	
	private final DateAttribute assertDate(final Iterator i, final int num)
	{
		final DateAttribute date = (DateAttribute)i.next();
		assertEquals(item.TYPE, date.getType());
		assertEquals("dates"+num, date.getName());
		assertEquals(false, date.isMandatory());
		assertEquals(false, date.isFinal());
		return date;
	}

	private final StringAttribute assertString(final Iterator i, final int num)
	{
		final StringAttribute string = (StringAttribute)i.next();
		assertEquals(item.TYPE, string.getType());
		assertEquals("strings"+num, string.getName());
		assertEquals(false, string.isMandatory());
		assertEquals(false, string.isFinal());
		assertEquals(1, string.getMinimumLength());
		assertEquals(11, string.getMaximumLength());
		return string;
	}

}
