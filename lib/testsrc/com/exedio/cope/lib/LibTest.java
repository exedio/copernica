
package com.exedio.cope.lib;

import com.exedio.cope.lib.database.Database;
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
			final ItemWithoutAttributes item3 = new ItemWithoutAttributes();
			
			assertEquals(item1, item1);
			assertEquals(item2, item2);
			assertEquals(item3, item3);

			assertFalse(item1.equals(null));
			assertFalse(item2.equals(null));
			assertFalse(item3.equals(null));
			assertFalse(item1.equals(item2));
			assertFalse(item1.equals(item3));
			assertFalse(item2.equals(item1));
			assertFalse(item2.equals(item3));
			assertFalse(item3.equals(item1));
			assertFalse(item3.equals(item2));
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
			assertEquals(null, item.getUniqueString());
			assertEquals(null, ItemWithSingleUnique.findByUniqueString("uniqueString"));
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
			assertEquals(null, item.getUniqueReadOnlyString());
			assertEquals(null, ItemWithSingleUniqueReadOnly.findByUniqueReadOnlyString("uniqueString"));
			
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
			assertEquals(null, item.getUniqueNotNullString());
			assertEquals(null, ItemWithSingleUniqueNotNull.findByUniqueNotNullString("uniqueString"));
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
			assertEquals(null, item.getUniqueNotNullString());
			assertEquals(null, ItemWithSingleUniqueNotNull.findByUniqueNotNullString("uniqueString"));
			assertEquals(null, ItemWithSingleUniqueNotNull.findByUniqueNotNullString("uniqueString2"));
		}
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
