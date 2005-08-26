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

import com.exedio.cope.testmodel.AttributeEmptyItem;
import com.exedio.cope.testmodel.AttributeItem;
import com.exedio.cope.testmodel.EmptyItem;
import com.exedio.cope.testmodel.EmptyItem2;
import com.exedio.cope.testmodel.PointerItem;
import com.exedio.cope.testmodel.PointerTargetItem;
import com.exedio.cope.testmodel.SumItem;


public class AttributeItemTest extends AttributeTest
{
	public void testSomeItem() throws ConstraintViolationException
	{
		assertEquals(item.TYPE, item.someItem.getType());
		assertEquals(EmptyItem.TYPE, item.someItem.getTargetType());
		assertEquals(Item.FORBID, item.someItem.getDeletePolicy());
		assertEqualsUnmodifiable(list(), SumItem.TYPE.getReferences());
		assertEqualsUnmodifiable(list(AttributeEmptyItem.parent), AttributeItem.TYPE.getReferences());
		
		assertEquals(null, item.getSomeItem());
		item.setSomeItem(someItem);
		assertEquals(someItem, item.getSomeItem());

		assertContains(item,
				item.TYPE.search(item.someItem.equal(someItem)));
		assertContains(item2,
				item.TYPE.search(item.someItem.equal(null)));
		assertContains(item2,
				item.TYPE.search(item.someItem.notEqual(someItem)));
		assertContains(item,
				item.TYPE.search(item.someItem.notEqual(null)));

		assertContains(someItem, null, search(item.someItem));
		assertContains(someItem, search(item.someItem, item.someItem.equal(someItem)));

		item.passivateCopeItem();
		assertEquals(someItem, item.getSomeItem());
		item.setSomeItem(null);
		assertEquals(null, item.getSomeItem());
		
		try
		{
			item.set(item.someItem, new Integer(10));
			fail();
		}
		catch(ClassCastException e)
		{
			assertEquals("expected " + EmptyItem.class.getName() + ", got " + Integer.class.getName() + " for someItem", e.getMessage());
		}
		
		final EmptyItem2 wrongItem = new EmptyItem2();
		deleteOnTearDown(wrongItem);
		try
		{
			item.set(item.someItem, wrongItem);
			fail();
		}
		catch(ClassCastException e)
		{
			assertEquals("expected " + EmptyItem.class.getName() + ", got " + EmptyItem2.class.getName() + " for someItem", e.getMessage());
		}
	}

	public void testSomeNotNullItem()
		throws MandatoryViolationException
	{
		assertEquals(item.TYPE, item.someNotNullItem.getType());
		assertEquals(
			EmptyItem.TYPE,
			item.someNotNullItem.getTargetType());
		assertEquals(Item.FORBID, item.someNotNullItem.getDeletePolicy());
		assertEquals(someItem, item.getSomeNotNullItem());

		item.setSomeNotNullItem(someItem2);
		assertEquals(someItem2, item.getSomeNotNullItem());

		item.passivateCopeItem();
		assertEquals(someItem2, item.getSomeNotNullItem());
		try
		{
			item.setSomeNotNullItem(null);
			fail("should have thrown NotNullViolationException");
		}
		catch (MandatoryViolationException e)
		{
			assertEquals(item, e.getItem());
			assertEquals(item.someNotNullItem, e.getNotNullAttribute());
		}
		assertEquals(someItem2, item.getSomeNotNullItem());
		assertDeleteFails(someItem2, item.someNotNullItem, false);

		try
		{
			new AttributeItem("someString", 5, 6l, 2.2, true, null, AttributeItem.SomeEnum.enumValue1);
			fail("should have thrown NotNullViolationException");
		}
		catch(MandatoryViolationException e)
		{
			assertEquals(null, e.getItem());
			assertEquals(item.someNotNullItem, e.getNotNullAttribute());
		}
	}
	
	public void testIntegrity() throws ConstraintViolationException
	{
		final EmptyItem2 target = new EmptyItem2();
		deleteOnTearDown(target);
		final PointerTargetItem pointer2 = new PointerTargetItem("pointer2");
		deleteOnTearDown(pointer2);
		final PointerItem source = new PointerItem("source", pointer2);
		deleteOnTearDown(source);
		source.setEmpty2(target);

		assertDeleteFails(target, source.empty2, true);
	}

}
