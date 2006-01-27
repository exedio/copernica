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

package com.exedio.cope.pattern;

import java.io.IOException;
import java.util.Arrays;

import com.exedio.cope.AbstractLibTest;
import com.exedio.cope.AttributeValue;
import com.exedio.cope.ConstraintViolationException;
import com.exedio.cope.Feature;
import com.exedio.cope.Main;

public class CustomTest extends AbstractLibTest
{
	public CustomTest()
	{
		super(Main.customModel);
	}
	
	CustomItem item;
	
	public void setUp() throws Exception
	{
		super.setUp();
		deleteOnTearDown(item = new CustomItem());
	}
	
	static final Integer i20 = new Integer(20);
	static final Integer i44 = new Integer(44);
	static final Integer i55 = new Integer(55);
	static final Integer i56 = new Integer(56);
	static final Integer im2 = new Integer(-2);
	
	public void testNumber() throws ConstraintViolationException, IOException, CustomAttributeException
	{
		assertEquals(Arrays.asList(new Feature[]{
				item.numberString,
				item.number,
				item.element1,
				item.element2,
				item.element3,
				item.elements,
			}), item.TYPE.getDeclaredFeatures());
		assertEquals(item.TYPE.getDeclaredFeatures(), item.TYPE.getFeatures());

		assertEquals(item.TYPE, item.number.getType());
		assertEquals("number", item.number.getName());
		assertEqualsUnmodifiable(list(item.numberString), item.number.getStorages());
		assertEqualsUnmodifiable(list(item.number), item.numberString.getPatterns());
		assertEquals(Integer.class, item.number.getValueType());

		assertNull(item.getNumberString());
		assertNull(item.getNumber());
		assertNull(item.number.get(item));
		
		item.setNumber(i2);
		assertEquals("2", item.getNumberString());
		assertEquals(i2, item.getNumber());
		assertEquals(i2, item.number.get(item));
		
		item.setNumber(i20);
		assertEquals("20", item.getNumberString());
		assertEquals(i20, item.getNumber());
		assertEquals(i20, item.number.get(item));
		
		item.number.set(item, i44);
		assertEquals("44", item.getNumberString());
		assertEquals(i44, item.getNumber());
		assertEquals(i44, item.number.get(item));
		
		try
		{
			item.setNumber(im2);
			fail();
		}
		catch(IOException e)
		{
			assertEquals("test exception:-2", e.getMessage());
		}
		assertEquals("44", item.getNumberString());
		
		try
		{
			item.number.set(item, im2);
			fail();
		}
		catch(CustomAttributeException e)
		{
			assertSame(item.number, e.getAttribute());
			assertSame(item.number, e.getFeature());
			assertEquals("test exception:-2", e.getCause().getMessage());
			assertEquals(IOException.class, e.getCause().getClass());
		}
		assertEquals("44", item.getNumberString());
		
		assertContains(item, item.TYPE.search(null));
		try
		{
			new CustomItem(im2);
			fail();
		}
		catch(IOException e)
		{
			assertEquals("test exception:-2", e.getMessage());
		}
		assertContains(item, item.TYPE.search(null));
		try
		{
			CustomItem.TYPE.newItem(new AttributeValue[]{new AttributeValue(item.number, im2)});
			fail();
		}
		catch(CustomAttributeException e)
		{
			assertSame(item.number, e.getAttribute());
			assertSame(item.number, e.getFeature());
			assertEquals("test exception:-2", e.getCause().getMessage());
			assertEquals(IOException.class, e.getCause().getClass());
		}
		assertContains(item, item.TYPE.search(null));
		
		item.setNumber(null);
		assertNull(item.getNumberString());
		assertNull(item.getNumber());
		assertNull(item.number.get(item));
		assertEquals(list(null, null, null), item.getElements());

		{
			final CustomItem numberItem55 = new CustomItem(i55);
			deleteOnTearDown(numberItem55);
			assertEquals("55", numberItem55.getNumberString());
			assertEquals(i55, numberItem55.getNumber());
			assertEquals(i55, numberItem55.number.get(numberItem55));
		}
		{
			final CustomItem numberItem56 =
				(CustomItem)CustomItem.TYPE.newItem(new AttributeValue[]{CustomItem.number.map(i56)});
			deleteOnTearDown(numberItem56);
			assertEquals("56", numberItem56.getNumberString());
			assertEquals(i56, numberItem56.getNumber());
			assertEquals(i56, numberItem56.number.get(numberItem56));
		}
		
		item.setElements(list(i2, i4, i8));
		assertEquals(list(i2, i4, i8), item.getElements());
		assertEquals(i2, item.getElement1());
		assertEquals(i4, item.getElement2());
		assertEquals(i8, item.getElement3());
		
		{
			final CustomItem elementsItem3 = new CustomItem(list(i3, i4, i5));
			deleteOnTearDown(elementsItem3);
			assertEquals(list(i3, i4, i5), elementsItem3.getElements());
			assertEquals(list(i3, i4, i5), elementsItem3.elements.get(elementsItem3));
			assertEquals(i3, elementsItem3.getElement1());
			assertEquals(i4, elementsItem3.getElement2());
			assertEquals(i5, elementsItem3.getElement3());
		}
		{
			final CustomItem elementsItem7 =
				(CustomItem)CustomItem.TYPE.newItem(new AttributeValue[]{CustomItem.elements.map(list(i7, i8, i9))});
			deleteOnTearDown(elementsItem7);
			assertEquals(list(i7, i8, i9), elementsItem7.getElements());
			assertEquals(list(i7, i8, i9), elementsItem7.elements.get(elementsItem7));
			assertEquals(i7, elementsItem7.getElement1());
			assertEquals(i8, elementsItem7.getElement2());
			assertEquals(i9, elementsItem7.getElement3());
		}
	}
	
}
