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

import com.exedio.cope.testmodel.AttributeItem;


public class AttributeStringTest extends AttributeTest
{
	public void testSomeString() throws ConstraintViolationException
	{
		// test model
		assertEquals(item.TYPE, item.someString.getType());
		assertEquals(item.TYPE, item.someStringUpperCase.getType());
		assertEquals("someString", item.someString.getName());
		assertEqualsUnmodifiable(list(), item.someString.getPatterns());
		assertEquals("someStringUpperCase", item.someStringUpperCase.getName());

		// test conditions
		assertEquals(item.someString.equal("hallo"), item.someString.equal("hallo"));
		assertNotEquals(item.someString.equal("hallo"), item.someString.equal("bello"));
		assertNotEquals(item.someString.equal("hallo"), item.someString.equal((String)null));
		assertNotEquals(item.someString.equal("hallo"), item.someString.like("hallo"));
		assertEquals(item.someString.equal(item.someNotNullString), item.someString.equal(item.someNotNullString));
		assertNotEquals(item.someString.equal(item.someNotNullString), item.someString.equal(item.someString));
		
		{
			final StringAttribute orig = new StringAttribute(Item.OPTIONAL);
			assertEquals(false, orig.isReadOnly());
			assertEquals(false, orig.isMandatory());
			assertEquals(false, orig.hasLengthConstraintCheckedException());
			assertEquals(0, orig.getMinimumLength());
			assertEquals(StringAttribute.DEFAULT_LENGTH, orig.getMaximumLength());

			final StringAttribute copy = (StringAttribute)orig.copyFunctionAttribute();
			assertEquals(false, copy.isReadOnly());
			assertEquals(false, copy.isMandatory());
			assertEquals(false, copy.hasLengthConstraintCheckedException());
			assertEquals(0, copy.getMinimumLength());
			assertEquals(StringAttribute.DEFAULT_LENGTH, copy.getMaximumLength());
		}
		{
			final StringAttribute orig = new StringAttribute(Item.READ_ONLY_OPTIONAL).lengthMin(10);
			assertEquals(true, orig.isReadOnly());
			assertEquals(false, orig.isMandatory());
			assertNull(orig.getImplicitUniqueConstraint());
			assertEquals(true, orig.hasLengthConstraintCheckedException());
			assertEquals(10, orig.getMinimumLength());
			assertEquals(StringAttribute.DEFAULT_LENGTH, orig.getMaximumLength());
			
			final StringAttribute copy = (StringAttribute)orig.copyFunctionAttribute();
			assertEquals(true, copy.isReadOnly());
			assertEquals(false, copy.isMandatory());
			assertNull(copy.getImplicitUniqueConstraint());
			assertEquals(true, copy.hasLengthConstraintCheckedException());
			assertEquals(10, copy.getMinimumLength());
			assertEquals(StringAttribute.DEFAULT_LENGTH, copy.getMaximumLength());
		}
		{
			final StringAttribute orig = new StringAttribute(Item.READ_ONLY_UNIQUE_OPTIONAL).lengthMin(20);
			assertEquals(true, orig.isReadOnly());
			assertEquals(false, orig.isMandatory());
			assertNotNull(orig.getImplicitUniqueConstraint());
			assertEquals(true, orig.hasLengthConstraintCheckedException());
			assertEquals(20, orig.getMinimumLength());
			assertEquals(StringAttribute.DEFAULT_LENGTH, orig.getMaximumLength());
			
			final StringAttribute copy = (StringAttribute)orig.copyFunctionAttribute();
			assertEquals(true, copy.isReadOnly());
			assertEquals(false, copy.isMandatory());
			assertNotNull(copy.getImplicitUniqueConstraint());
			assertEquals(true, copy.hasLengthConstraintCheckedException());
			assertEquals(20, copy.getMinimumLength());
			assertEquals(StringAttribute.DEFAULT_LENGTH, copy.getMaximumLength());
		}
		{
			final StringAttribute orig = new StringAttribute(Item.MANDATORY).lengthRange(10, 20);
			assertEquals(false, orig.isReadOnly());
			assertEquals(true, orig.isMandatory());
			assertEquals(true, orig.hasLengthConstraintCheckedException());
			assertEquals(10, orig.getMinimumLength());
			assertEquals(20, orig.getMaximumLength());
			
			final StringAttribute copy = (StringAttribute)orig.copyFunctionAttribute();
			assertEquals(false, copy.isReadOnly());
			assertEquals(true, copy.isMandatory());
			assertEquals(true, copy.hasLengthConstraintCheckedException());
			assertEquals(10, copy.getMinimumLength());
			assertEquals(20, copy.getMaximumLength());
		}
		
		assertWrongLength(-1, 20, "mimimum length must be positive, but was -1.");
		assertWrongLength( 0,  0, "maximum length must be greater zero, but was 0.");
		assertWrongLength(20, 10, "maximum length must be greater or equal mimimum length, but was 10 and 20.");

		assertString(item, item2, item.someString);

		try
		{
			item.set(item.someString, new Integer(10));
			fail();
		}
		catch(ClassCastException e)
		{
			assertEquals("expected " + String.class.getName() + ", got " + Integer.class.getName() + " for someString", e.getMessage());
		}
	}
	
	void assertWrongLength(final int minimumLength, final int maximumLength, final String message)
	{
		try
		{
			new StringAttribute(Item.OPTIONAL).lengthRange(minimumLength, maximumLength);
			fail();
		}
		catch(RuntimeException e)
		{
			assertEquals(message, e.getMessage());
		}
	}

	public void testSomeNotNullString()
		throws MandatoryViolationException
	{
		assertEquals(item.TYPE, item.someNotNullString.getType());
		assertEquals("someString", item.getSomeNotNullString());

		item.setSomeNotNullString("someOtherString");
		assertEquals("someOtherString", item.getSomeNotNullString());

		try
		{
			item.setSomeNotNullString(null);
			fail();
		}
		catch (MandatoryViolationException e)
		{
			assertEquals(item, e.getItem());
			assertEquals(item.someNotNullString, e.getMandatoryAttribute());
			assertEquals("mandatory violation on " + item + " for AttributeItem#someNotNullString", e.getMessage());
		}

		try
		{
			new AttributeItem(null, 5, 6l, 2.2, true, someItem, AttributeItem.SomeEnum.enumValue1);
			fail();
		}
		catch(MandatoryViolationException e)
		{
			assertEquals(null, e.getItem());
			assertEquals(item.someNotNullString, e.getMandatoryAttribute());
			assertEquals("mandatory violation on a newly created item for AttributeItem#someNotNullString", e.getMessage());
		}
	}

}
