
package com.exedio.cope.lib;

public class StringTest extends DatabaseLibTest
{
	StringItem item;

	public void setUp() throws Exception
	{
		super.setUp();
		item = new StringItem(); 
	}
	
	public void tearDown() throws Exception
	{
		item.delete();
		super.tearDown();
	}
	
	public void testStrings() throws LengthViolationException
	{
		assertEquals(0, item.any.getMinimumLength());
		assertEquals(Integer.MAX_VALUE, item.any.getMaximumLength());
		assertEquals(false, item.any.isLengthConstrained());

		assertEquals(4, item.min4.getMinimumLength());
		assertEquals(Integer.MAX_VALUE, item.min4.getMaximumLength());
		assertEquals(true, item.min4.isLengthConstrained());

		assertEquals(0, item.max4.getMinimumLength());
		assertEquals(4, item.max4.getMaximumLength());
		assertEquals(true, item.max4.isLengthConstrained());

		assertEquals(4, item.min4Max8.getMinimumLength());
		assertEquals(8, item.min4Max8.getMaximumLength());
		assertEquals(true, item.min4Max8.isLengthConstrained());

		// any
		item.setAny("1234");
		assertEquals("1234", item.getAny());
		item.setAny("123");
		assertEquals("123", item.getAny());

		// min4
		try
		{
			item.setMin4("123");
			fail("should have thrown LengthViolationException");
		}
		catch(LengthViolationException e)
		{
			assertEquals(item, e.getItem());
			assertEquals(item.min4, e.getStringAttribute());
			assertEquals("123", e.getValue());
			assertEquals(true, e.isTooShort());
		}
		assertEquals(null, item.getMin4());

		item.setMin4("1234");
		assertEquals("1234", item.getMin4());

		// max4		
		item.setMax4("1234");
		assertEquals("1234", item.getMax4());
		try
		{
			item.setMax4("12345");
			fail("should have thrown LengthViolationException");
		}
		catch(LengthViolationException e)
		{
			assertEquals(item, e.getItem());
			assertEquals(item.max4, e.getStringAttribute());
			assertEquals("12345", e.getValue());
			assertEquals(false, e.isTooShort());
		}
		assertEquals("1234", item.getMax4());

		// min4max8		
		try
		{
			item.setMin4Max8("123");
			fail("should have thrown LengthViolationException");
		}
		catch(LengthViolationException e)
		{
			assertEquals(item, e.getItem());
			assertEquals(item.min4Max8, e.getStringAttribute());
			assertEquals("123", e.getValue());
			assertEquals(true, e.isTooShort());
		}
		assertEquals(null, item.getMin4Max8());

		item.setMin4Max8("1234");
		assertEquals("1234", item.getMin4Max8());

		item.setMin4Max8("12345678");
		assertEquals("12345678", item.getMin4Max8());

		item.passivate();
		assertEquals("12345678", item.getMin4Max8());

		try
		{
			item.setMin4Max8("123456789");
			fail("should have thrown LengthViolationException");
		}
		catch(LengthViolationException e)
		{
			assertEquals(item, e.getItem());
			assertEquals(item.min4Max8, e.getStringAttribute());
			assertEquals("123456789", e.getValue());
			assertEquals(false, e.isTooShort());
		}
		assertEquals("12345678", item.getMin4Max8());

		item.passivate();
		assertEquals("12345678", item.getMin4Max8());
	}

}
