
package com.exedio.cope.lib;

import com.exedio.cope.lib.database.Database;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import junit.framework.TestCase;

public class LibTest extends TestCase
{
	
	final Type[] types = new Type[]
	{
		ItemWithSingleUnique.TYPE,
		ItemWithSingleUniqueReadOnly.TYPE,
		ItemWithSingleUniqueNotNull.TYPE,
		ItemWithoutAttributes.TYPE,
		ItemWithoutAttributes2.TYPE,
		ItemWithManyAttributes.TYPE,
	};
	
	public LibTest()
	{}
	
	public void setUp()
	{
		Database.theInstance.createTables();
	}
	
	public void tearDown()
	{
		Database.theInstance.dropTables();
	}
	
	public void testLib()
	{
		// BEWARE:
		// if something does not compile,
		// it may be an error in the 
		// instrumentor as well.
		
		
		// ID, equals, hashCode
		{
			final ItemWithoutAttributes item1 = new ItemWithoutAttributes();
			final ItemWithoutAttributes item2 = new ItemWithoutAttributes();
			final ItemWithoutAttributes2 item3 = new ItemWithoutAttributes2();
			
			assertEquals(item1, item1);
			assertEquals(item2, item2);
			assertEquals(item3, item3);

			assertFalse(item1.equals(null));
			assertFalse(item2.equals(null));
			assertFalse(item3.equals(null));
			
			assertNotEquals(item1, item2);
			assertNotEquals(item1, item3);
			assertNotEquals(item2, item3);
			
			assertFalse(item1.equals("hello"));
			assertFalse(item1.equals(new Integer(1)));
			assertFalse(item1.equals(Boolean.TRUE));
		}
		
		// ItemWithSingleUnique
		{
			assertEquals(null, ItemWithSingleUnique.findByUniqueString("uniqueString"));

			final ItemWithSingleUnique item = new ItemWithSingleUnique();
			assertEquals(null, item.getUniqueString());
			assertEquals(null, item.findByUniqueString("uniqueString"));

			try
			{
				item.setUniqueString("uniqueString");
			}
			catch(UniqueViolationException e)
			{
				throw new SystemException(e);
			}
			assertEquals("uniqueString", item.getUniqueString());
			assertEquals(null/*item*/, ItemWithSingleUnique.findByUniqueString("uniqueString"));
		}
		

		// ItemWithSingleUniqueReadOnly
		{
			assertEquals(null, ItemWithSingleUniqueReadOnly.findByUniqueReadOnlyString("uniqueString"));

			final ItemWithSingleUniqueReadOnly item;
			try
			{
				item = new ItemWithSingleUniqueReadOnly("uniqueString");
			}
			catch(UniqueViolationException e)
			{
				throw new SystemException(e);
			}
			assertEquals("uniqueString", item.getUniqueReadOnlyString());
			assertEquals(null/*item*/, ItemWithSingleUniqueReadOnly.findByUniqueReadOnlyString("uniqueString"));
			
			try
			{
				item.setAttribute(item.uniqueReadOnlyString, "zapp");
				fail("should have thrown ReadOnlyViolationException");
			}
			catch(ReadOnlyViolationException e)
			{
				assertEquals(item.uniqueReadOnlyString, e.getReadOnlyAttribute());
				assertEquals(item, e.getItem());
			}
			catch(ConstraintViolationException e)
			{
				throw new SystemException(e);
			}
			assertEquals("uniqueString", item.getUniqueReadOnlyString());
			assertEquals(null/*item*/, ItemWithSingleUniqueReadOnly.findByUniqueReadOnlyString("uniqueString"));
		}

	
		// ItemWithSingleNotNull
		{
			assertEquals(null, ItemWithSingleUniqueNotNull.findByUniqueNotNullString("uniqueString"));
			assertEquals(null, ItemWithSingleUniqueNotNull.findByUniqueNotNullString("uniqueString2"));

			final ItemWithSingleUniqueNotNull item;
			try
			{
				item = new ItemWithSingleUniqueNotNull("uniqueString");
			}
			catch(UniqueViolationException e)
			{
				throw new SystemException(e);
			}
			catch(NotNullViolationException e)
			{
				throw new SystemException(e);
			}
			assertEquals("uniqueString", item.getUniqueNotNullString());
			assertEquals(null/*item*/, ItemWithSingleUniqueNotNull.findByUniqueNotNullString("uniqueString"));
			assertEquals(null, ItemWithSingleUniqueNotNull.findByUniqueNotNullString("uniqueString2"));

			try
			{
				item.setUniqueNotNullString("uniqueString2");
			}
			catch(UniqueViolationException e)
			{
				throw new SystemException(e);
			}
			catch(NotNullViolationException e)
			{
				throw new SystemException(e);
			}
			assertEquals("uniqueString2", item.getUniqueNotNullString());
			assertEquals(null, ItemWithSingleUniqueNotNull.findByUniqueNotNullString("uniqueString"));
			assertEquals(null/*item*/, ItemWithSingleUniqueNotNull.findByUniqueNotNullString("uniqueString2"));
		}
		
		// ItemWithManyAttributes
		{
			final ItemWithManyAttributes item;
			try
			{
				item = new ItemWithManyAttributes("someString", 5, true);
			}
			catch(NotNullViolationException e)
			{
				throw new SystemException(e);
			}
			
			// someString
			assertEquals(null, item.getSomeString());
			item.setSomeString("someString");
			assertEquals("someString", item.getSomeString());
			item.setSomeString(null);
			assertEquals(null, item.getSomeString());

			// someNotNullString
			assertEquals("someString", item.getSomeNotNullString());
			try
			{
				item.setSomeNotNullString("someOtherString");
			}
			catch(NotNullViolationException e)
			{
				throw new SystemException(e);
			}
			assertEquals("someOtherString", item.getSomeNotNullString());
			try
			{
				item.setSomeNotNullString(null);
			}
			catch(NotNullViolationException e)
			{
				assertEquals(item.someNotNullString, e.getNotNullAttribute());
				assertEquals(item, e.getItem());
			}

			// someInteger
			assertEquals(null, item.getSomeInteger());
			item.setSomeInteger(new Integer(10));
			assertEquals(new Integer(10), item.getSomeInteger());
			item.setSomeInteger(null);
			assertEquals(null, item.getSomeInteger());

			// someNotNullInteger
			assertEquals(5, item.getSomeNotNullInteger());
			item.setSomeNotNullInteger(20);
			assertEquals(20, item.getSomeNotNullInteger());
			
			// someBoolean
			assertEquals(null, item.getSomeBoolean());
			item.setSomeBoolean(Boolean.TRUE);
			assertEquals(Boolean.TRUE, item.getSomeBoolean());
			item.setSomeBoolean(Boolean.FALSE);
			assertEquals(Boolean.FALSE, item.getSomeBoolean());
			item.setSomeBoolean(null);
			assertEquals(null, item.getSomeBoolean());

			// someNotNullBoolean
			assertEquals(true, item.getSomeNotNullBoolean());
			item.setSomeNotNullBoolean(false);
			assertEquals(false, item.getSomeNotNullBoolean());
			
			// someItem
			assertEquals(null, item.getSomeItem());
			final ItemWithoutAttributes someItem = new ItemWithoutAttributes();
			item.setSomeItem(someItem);
			assertEquals(someItem, item.getSomeItem());
			item.setSomeItem(null);
			assertEquals(null, item.getSomeItem());
			
			// someEnumeration
			ItemWithManyAttributes.SomeEnumeration someEnumeration = item.getSomeEnumeration();
			assertEquals(null, someEnumeration);
			if(someEnumeration!=ItemWithManyAttributes.SomeEnumeration.enumValue1)
				someEnumeration = ItemWithManyAttributes.SomeEnumeration.enumValue1;
			switch(someEnumeration.number)
			{
				case ItemWithManyAttributes.SomeEnumeration.enumValue1NUM:
					someEnumeration = ItemWithManyAttributes.SomeEnumeration.enumValue1;
					break;
				default:
					throw new RuntimeException("Ooooops");
			}
			item.setSomeEnumeration(someEnumeration);
			assertEquals(ItemWithManyAttributes.SomeEnumeration.enumValue1, item.getSomeEnumeration());
			item.setSomeEnumeration(ItemWithManyAttributes.SomeEnumeration.enumValue1);
			assertEquals(ItemWithManyAttributes.SomeEnumeration.enumValue1, item.getSomeEnumeration());

			// someMedia
			assertEquals(null, item.getSomeMediaURL());
			assertEquals(null, item.getSomeMediaURLSomeVariant());
			assertEquals(null, item.getSomeMediaData());
			assertEquals(null, item.getSomeMediaMimeMajor());
			assertEquals(null, item.getSomeMediaMimeMinor());
			try
			{
				item.setSomeMediaData(null/*some data*/, "someMimeMajor", "someMimeMinor");
			}
			catch(IOException e)
			{
				throw new SystemException(e);
			}
			assertEquals(null/*somehow gets the data*/, item.getSomeMediaURL());
			assertEquals(null/*somehow gets the data*/, item.getSomeMediaURLSomeVariant());
			assertEquals(null/*somehow gets the data*/, item.getSomeMediaData());
			assertEquals(null/*"someMimeMajor"*/, item.getSomeMediaMimeMajor());
			assertEquals(null/*"someMimeMinor"*/, item.getSomeMediaMimeMinor());
			try
			{
				item.setSomeMediaData(null, null, null);
			}
			catch(IOException e)
			{
				throw new SystemException(e);
			}
			assertEquals(null, item.getSomeMediaURL());
			assertEquals(null, item.getSomeMediaURLSomeVariant());
			assertEquals(null, item.getSomeMediaData());
			assertEquals(null, item.getSomeMediaMimeMajor());
			assertEquals(null, item.getSomeMediaMimeMinor());
			
			// someQualifiedAttribute
			final ItemWithoutAttributes someItem2 = new ItemWithoutAttributes();
			assertEquals(null, item.getSomeQualifiedString(someItem));
			assertEquals(null, item.getSomeQualifiedString(someItem2));
			item.setSomeQualifiedString(someItem, "someQualifiedValue");
			assertEquals("someQualifiedValue", item.getSomeQualifiedString(someItem));
			assertEquals("someQualifiedValue"/*null TODO*/, item.getSomeQualifiedString(someItem2));
			item.setSomeQualifiedString(someItem, null);
			assertEquals(null, item.getSomeQualifiedString(someItem));
			assertEquals(null, item.getSomeQualifiedString(someItem2));
		}
	}


	protected void assertNotEquals(final Item item1, final Item item2)
	{
		assertFalse(item1.equals(item2));
		assertFalse(item2.equals(item1));
		assertFalse(item1.getID().equals(item2.getID()));
		assertFalse(item1.hashCode()==item2.hashCode());
	}

	protected Set toSet(final Collection collection)
	{
		return new HashSet(collection);
	}

	protected Set set()
	{
		return Collections.EMPTY_SET;
	}

}
