
package com.exedio.cope.lib;

import java.io.IOException;

import com.exedio.cope.lib.contacts.Contact;
import com.exedio.cope.lib.contacts.SMSContact;

/**
 * An abstract test class for tests creating/using some persistent data.
 */
public abstract class DatabaseLibTest extends AbstractLibTest
{
	protected static final Type[] types = new Type[]
	{
		ItemWithSingleUnique.TYPE,
		ItemWithSingleUniqueReadOnly.TYPE,
		ItemWithSingleUniqueNotNull.TYPE,
		ItemWithoutAttributes.TYPE,
		ItemWithoutAttributes2.TYPE,
		ItemWithManyAttributes.TYPE,
		PointerItem2.TYPE,
		PointerItem.TYPE,
		Contact.TYPE,
		SMSContact.TYPE,
	};
	
	private static boolean createdDatabase = false;
	private static boolean registeredDropDatabaseHook = false;
	private static Object lock = new Object(); 
	
	private static void createDatabase()
	{
		synchronized(lock)
		{
			if(!createdDatabase)
			{
				Database.theInstance.createDatabase();
				createdDatabase = true;
			}
		}
	}
	
	private void dropDatabase()
	{
		synchronized(lock)
		{
			if(!registeredDropDatabaseHook)
			{
				Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){
					public void run()
					{
						Database.theInstance.dropDatabase();
					}
				}));
				registeredDropDatabaseHook = true;
			}
		}
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		createDatabase();
		Database.theInstance.checkEmptyTables();
	}
	
	protected void tearDown() throws Exception
	{
		Database.theInstance.checkEmptyTables();
		dropDatabase();
		super.tearDown();
	}
	
	protected void assertMediaMime(final ItemWithManyAttributes item,
											final String mimeMajor,
											final String mimeMinor,
											final String url)
	{
		try
		{
			item.setSomeMediaData(null/*some data*/, mimeMajor, mimeMinor);
		}
		catch(IOException e)
		{
			throw new SystemException(e);
		}
		final String prefix = "/medias/com.exedio.cope.lib.ItemWithManyAttributes/someMedia/";
		final String expectedURL = prefix+item.pk+'.'+url;
		final String expectedURLSomeVariant = prefix+"SomeVariant/"+item.pk+'.'+url;
		//System.out.println(expectedURL);
		//System.out.println(item.getSomeMediaURL());
		assertEquals(expectedURL, item.getSomeMediaURL());
		assertEquals(expectedURLSomeVariant, item.getSomeMediaURLSomeVariant());
		//System.out.println(expectedURLSomeVariant);
		//System.out.println(item.getSomeMediaURL());
		assertEquals(null/*somehow gets the data*/, item.getSomeMediaData());
		assertEquals(mimeMajor, item.getSomeMediaMimeMajor());
		assertEquals(mimeMinor, item.getSomeMediaMimeMinor());
	}

	protected void assertNotEquals(final Item item1, final Item item2)
	{
		assertFalse(item1.equals(item2));
		assertFalse(item2.equals(item1));
		assertFalse(item1.getID().equals(item2.getID()));
		assertFalse(item1.hashCode()==item2.hashCode());
	}
	
	protected void assertID(final int id, final Item item)
	{
		assertTrue(item.getID()+"/"+id, item.getID().endsWith("."+id));
	}

	protected void assertDelete(final Item item)
			throws IntegrityViolationException
	{
		assertTrue(!item.isDeleted());
		item.delete();
		assertTrue(item.isDeleted());
	}

	public static void main(String[] args)
	{
		Database.theInstance.tearDownDatabase();
	}

}
