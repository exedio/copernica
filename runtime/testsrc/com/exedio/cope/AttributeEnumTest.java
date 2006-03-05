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

import com.exedio.cope.testmodel.AttributeItem;
import com.exedio.cope.testmodel.AttributeItem.SomeEnum;


public class AttributeEnumTest extends AttributeTest
{
	public static enum SomeEnum2
	{
		enumValue1,
		enumValue2;
	}

	public void testSomeEnum()
	{
		// model
		assertEquals(item.TYPE, item.someEnum.getType());
		assertEquals(
			list(
				AttributeItem.SomeEnum.enumValue1,
				AttributeItem.SomeEnum.enumValue2,
				AttributeItem.SomeEnum.enumValue3),
			item.someEnum.getValues());

		assertEquals(
			AttributeItem.SomeEnum.enumValue1,
			item.someEnum.getValue("enumValue1"));
		assertEquals(
			AttributeItem.SomeEnum.enumValue2,
			item.someEnum.getValue("enumValue2"));
		assertEquals(
			AttributeItem.SomeEnum.enumValue3,
			item.someEnum.getValue("enumValue3"));

		assertEquals(AttributeItem.SomeEnum.class,
			AttributeItem.SomeEnum.enumValue1.getClass());
		assertEquals(AttributeItem.SomeEnum.class,
			AttributeItem.SomeEnum.enumValue2.getClass());
		assertEquals(AttributeItem.SomeEnum.class,
			AttributeItem.SomeEnum.enumValue3.getClass());

		assertEquals("enumValue1",
			AttributeItem.SomeEnum.enumValue1.name());
		assertEquals("enumValue2",
			AttributeItem.SomeEnum.enumValue2.name());
		assertEquals("enumValue3",
			AttributeItem.SomeEnum.enumValue3.name());

		assertTrue(!
			AttributeItem.SomeEnum.enumValue1.equals(
			AttributeItem.SomeEnum.enumValue2));
		assertTrue(!
			AttributeItem.SomeEnum.enumValue2.equals(
			AttributeItem.SomeEnum.enumValue3));
		assertTrue(!
			AttributeItem.SomeEnum.enumValue3.equals(
			AttributeItem.SomeEnum.enumValue1));

		AttributeItem.SomeEnum someEnumeration = AttributeItem.SomeEnum.enumValue1;
		switch(someEnumeration)
		{
			case enumValue1:
				someEnumeration = AttributeItem.SomeEnum.enumValue2;
				break;
			case enumValue2:
				someEnumeration = AttributeItem.SomeEnum.enumValue3;
				break;
			case enumValue3:
				someEnumeration = AttributeItem.SomeEnum.enumValue1;
				break;
			default :
				throw new RuntimeException("Ooooops");
		}
		assertEquals(someEnumeration, AttributeItem.SomeEnum.enumValue2);
		
		try
		{
			new EnumAttribute(Item.OPTIONAL, null);
			fail();
		}
		catch(NullPointerException e)
		{
			// fine
		}
		try
		{
			new EnumAttribute(Item.OPTIONAL, getClass());
			fail();
		}
		catch(RuntimeException e)
		{
			assertEquals("is not a subclass of java.lang.Enum: "+getClass().getName(), e.getMessage());
		}


		assertEquals(null, item.getSomeEnum());
		item.setSomeEnum(AttributeItem.SomeEnum.enumValue1);
		assertEquals(
			AttributeItem.SomeEnum.enumValue1,
			item.getSomeEnum());
		item.setSomeEnum(
			AttributeItem.SomeEnum.enumValue2);
		assertEquals(
			AttributeItem.SomeEnum.enumValue2,
			item.getSomeEnum());
		
		assertContains(item,
				item.TYPE.search(item.someEnum.equal(AttributeItem.SomeEnum.enumValue2)));
		assertContains(item2,
				item.TYPE.search(item.someEnum.equal(null)));
		assertContains(item, item2,
				item.TYPE.search(item.someEnum.notEqual(AttributeItem.SomeEnum.enumValue1)));
		assertContains(item2,
				item.TYPE.search(item.someEnum.notEqual(AttributeItem.SomeEnum.enumValue2)));
		assertContains(item,
				item.TYPE.search(item.someEnum.notEqual(null)));

		assertContains(AttributeItem.SomeEnum.enumValue2, null, search(item.someEnum));
		assertContains(AttributeItem.SomeEnum.enumValue2, search(item.someEnum, item.someEnum.equal(AttributeItem.SomeEnum.enumValue2)));

		restartTransaction();
		assertEquals(
			AttributeItem.SomeEnum.enumValue2,
			item.getSomeEnum());
		item.setSomeEnum(null);
		assertEquals(null, item.getSomeEnum());
		
		try
		{
			item.set(item.someEnum, Integer.valueOf(10));
			fail();
		}
		catch(ClassCastException e)
		{
			assertEquals("expected " + SomeEnum.class.getName() + ", got " + Integer.class.getName() + " for someEnum", e.getMessage());
		}
		
		try
		{
			item.set(item.someEnum, SomeEnum2.enumValue2);
			fail();
		}
		catch(ClassCastException e)
		{
			assertEquals("expected " + SomeEnum.class.getName() + ", got " + SomeEnum2.class.getName() + " for someEnum", e.getMessage());
		}
	}

	public void testNotNullSomeEnum()
			throws MandatoryViolationException
	{
		assertEquals(AttributeItem.SomeEnum.enumValue1, item.getSomeNotNullEnum());
		item.setSomeNotNullEnum(AttributeItem.SomeEnum.enumValue2);
		assertEquals(
			AttributeItem.SomeEnum.enumValue2,
			item.getSomeNotNullEnum());
		item.setSomeNotNullEnum(
			AttributeItem.SomeEnum.enumValue3);
		assertEquals(
			AttributeItem.SomeEnum.enumValue3,
			item.getSomeNotNullEnum());
		restartTransaction();
		assertEquals(
			AttributeItem.SomeEnum.enumValue3,
			item.getSomeNotNullEnum());
		try
		{
			item.setSomeNotNullEnum(null);
			fail();
		}
		catch(MandatoryViolationException e)
		{
			assertEquals(item, e.getItem());
			assertEquals(item.someNotNullEnum, e.getMandatoryAttribute());
			assertEquals(item.someNotNullEnum, e.getFeature());
			assertEquals("mandatory violation on " + item + " for AttributeItem#someNotNullEnum", e.getMessage());
		}
		assertEquals(
			AttributeItem.SomeEnum.enumValue3,
			item.getSomeNotNullEnum());

		try
		{
			new AttributeItem("someString", 5, 6l, 2.2, true, someItem, null);
			fail();
		}
		catch(MandatoryViolationException e)
		{
			assertEquals(null, e.getItem());
			assertEquals(item.someNotNullEnum, e.getMandatoryAttribute());
			assertEquals(item.someNotNullEnum, e.getFeature());
			assertEquals("mandatory violation on a newly created item for AttributeItem#someNotNullEnum", e.getMessage());
		}
	}
}
