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
package com.exedio.cope.lib;

import com.exedio.cope.lib.function.UppercaseFunction;
import com.exedio.cope.testmodel.AttributeItem;


public class AttributeStringTest extends AttributeTest
{
	public void testSomeString() throws ConstraintViolationException
	{
		assertEquals(item.TYPE, item.someString.getType());
		assertEquals(item.TYPE, item.someStringUpperCase.getType());
		assertEquals(null, item.getSomeString());
		assertEquals(null, item.getSomeStringUpperCase());
		assertEquals(null, item.getSomeStringLength());
		item.setSomeString("someString");
		assertEquals("someString", item.getSomeString());
		assertEquals("SOMESTRING", item.getSomeStringUpperCase());
		assertEquals(new Integer("someString".length()), item.getSomeStringLength());
		assertContains(item, item.TYPE.search(Cope.equal(item.someString, "someString")));
		assertContains(item2, item.TYPE.search(Cope.notEqual(item.someString, "someString")));
		assertContains(item.TYPE.search(Cope.equal(item.someString, "SOMESTRING")));
		assertContains(item, item.TYPE.search(Cope.like(item.someNotNullString, "someString")));
		assertContains(item, item2, item.TYPE.search(Cope.like(item.someNotNullString, "someString%")));
		assertContains(item2, item.TYPE.search(Cope.like(item.someNotNullString, "someString2%")));

		assertContains(item, item.TYPE.search(Cope.equal(item.someStringUpperCase, "SOMESTRING")));
		assertContains(item, item.TYPE.search(Cope.equal(new UppercaseFunction(item.someString), "SOMESTRING")));
		assertContains(item2, item.TYPE.search(Cope.notEqual(item.someStringUpperCase, "SOMESTRING")));
		assertContains(item2, item.TYPE.search(Cope.notEqual(new UppercaseFunction(item.someString), "SOMESTRING")));
		assertContains(item.TYPE.search(Cope.equal(item.someStringUpperCase, "someString")));
		assertContains(item.TYPE.search(Cope.equal(new UppercaseFunction(item.someString), "someString")));
		
		assertContains(item, item.TYPE.search(Cope.equal(item.someStringLength, "someString".length())));
		assertContains(item2, item.TYPE.search(Cope.notEqual(item.someStringLength, "someString".length())));
		assertContains(item.TYPE.search(Cope.equal(item.someStringLength, "someString".length()+1)));

		assertContains("someString", null, search(item.someString));
		assertContains("someString", search(item.someString, Cope.equal(item.someString, "someString")));
		// TODO allow functions for select
		//assertContains("SOMESTRING", search(item.someStringUpperCase, Cope.equal(item.someString, "someString")));

		item.passivateCopeItem();
		assertEquals("someString", item.getSomeString());
		assertEquals("SOMESTRING", item.getSomeStringUpperCase());
		assertEquals(new Integer("someString".length()), item.getSomeStringLength());
		item.setSomeString(null);
		assertEquals(null, item.getSomeString());
		assertEquals(null, item.getSomeStringUpperCase());
		assertEquals(null, item.getSomeStringLength());

		try
		{
			item.setAttribute(item.someString, new Integer(10));
			fail();
		}
		catch(ClassCastException e)
		{
			assertEquals("expected string, got " + Integer.class.getName() + " for someString", e.getMessage());
		}
	}

	public void testSomeNotNullString()
		throws NotNullViolationException
	{
		assertEquals(item.TYPE, item.someNotNullString.getType());
		assertEquals("someString", item.getSomeNotNullString());

		item.setSomeNotNullString("someOtherString");
		assertEquals("someOtherString", item.getSomeNotNullString());

		try
		{
			item.setSomeNotNullString(null);
			fail("should have thrown NotNullViolationException");
		}
		catch (NotNullViolationException e)
		{
			assertEquals(item, e.getItem());
			assertEquals(item.someNotNullString, e.getNotNullAttribute());
		}

		try
		{
			new AttributeItem(null, 5, 6l, 2.2, true, someItem, AttributeItem.SomeEnumeration.enumValue1);
			fail("should have thrown NotNullViolationException");
		}
		catch(NotNullViolationException e)
		{
			assertEquals(null, e.getItem());
			assertEquals(item.someNotNullString, e.getNotNullAttribute());
		}
	}

}
