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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import com.exedio.cope.junit.CopeTest;

public abstract class AbstractLibTest extends CopeTest
{
	
	public AbstractLibTest(final Model model)
	{
		super(model);
	}
	
	public AbstractLibTest(final Model model, final boolean exclusive)
	{
		super(model, exclusive);
	}
	
	protected final static Integer i1 = Integer.valueOf(1);
	protected final static Integer i2 = Integer.valueOf(2);
	protected final static Integer i3 = Integer.valueOf(3);
	protected final static Integer i4 = Integer.valueOf(4);
	protected final static Integer i5 = Integer.valueOf(5);
	protected final static Integer i6 = Integer.valueOf(6);
	protected final static Integer i7 = Integer.valueOf(7);
	protected final static Integer i8 = Integer.valueOf(8);
	protected final static Integer i9 = Integer.valueOf(9);
	
	protected boolean hsqldb;
	protected boolean mysql;
	protected boolean oracle;
	protected boolean cache;
	
	private final ArrayList<File> files = new ArrayList<File>();
	private TestByteArrayInputStream testStream;
	
	protected void setUp() throws Exception
	{
		super.setUp();
		final Database realDatabase = model.getDatabase(); // TODO rename
		hsqldb = "com.exedio.cope.HsqldbDatabase".equals(realDatabase.getClass().getName()); 
		mysql  = "com.exedio.cope.MysqlDatabase".equals(realDatabase.getClass().getName());
		oracle  = "com.exedio.cope.OracleDatabase".equals(realDatabase.getClass().getName());
		cache = model.getProperties().getCacheLimit()>0;
		files.clear();
	}
	
	protected void tearDown() throws Exception
	{
		for(Iterator i = files.iterator(); i.hasNext(); )
			((File)i.next()).delete();
		files.clear();

		final DatabaseListener listener = model.setDatabaseListener(null);
		
		if(testCompletedSuccessfully())
			assertNull("test didn't un-install ExpectingDatabase", listener);
		
		super.tearDown();
	}

	protected final TestByteArrayInputStream stream(final byte[] data)
	{
		assertNull(testStream);
		final TestByteArrayInputStream result = new TestByteArrayInputStream(data);
		testStream = result;
		return result;
	}
	
	protected final void assertStreamClosed()
	{
		assertNotNull(testStream);
		testStream.assertClosed();
		testStream = null;
	}
	
	protected final File file(final byte[] data)
	{
		final File result;
		FileOutputStream s = null;
		try
		{
			result = File.createTempFile("cope-AbstractLibTest-", ".tmp");
			s = new FileOutputStream(result);
			s.write(data);
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
			if(s!=null)
			{
				try
				{
					s.close();
				}
				catch(IOException e)
				{
					throw new RuntimeException(e);
				}
			}
		}
		files.add(result);
		return result;
	}
	
	protected static void assertData(final byte[] expectedData, final byte[] actualData)
	{
		if(!Arrays.equals(expectedData, actualData))
			fail("expected " + Arrays.toString(expectedData) + ", but was " + Arrays.toString(actualData));
	}
	
	protected static void assertEquals(final Function f1, final Function f2)
	{
		assertEquals((Object)f1, (Object)f2);
		assertEquals((Object)f2, (Object)f1);
		if(f1!=null)
			assertEquals(f1.hashCode(), f2.hashCode());
	}
	
	protected static void assertNotEquals(final Function f1, final Function f2)
	{
		assertTrue(!f1.equals(f2));
		assertTrue(!f2.equals(f1));
		assertTrue(f1.hashCode()!=f2.hashCode());
	}
	
	protected static void assertEquals(final Condition c1, final Condition c2)
	{
		assertEquals((Object)c1, (Object)c2);
		assertEquals((Object)c2, (Object)c1);
		if(c1!=null)
			assertEquals(c1.hashCode(), c2.hashCode());
	}
	
	protected static void assertNotEquals(final Condition c1, final Condition c2)
	{
		assertTrue(!c1.equals(c2));
		assertTrue(!c2.equals(c1));
		assertTrue(c1.hashCode()!=c2.hashCode());
	}
	
	protected static final void assertEqualContent(final byte[] expectedData, final File actualFile) throws IOException
	{
		if(expectedData==null)
			assertFalse(actualFile.exists());
		else
		{
			assertTrue(actualFile.exists());
			assertEquals(expectedData.length, actualFile.length());

			final byte[] actualData = new byte[(int)actualFile.length()];
			FileInputStream in = new FileInputStream(actualFile);
			in.read(actualData);
			in.close();
			
			for(int i = 0; i<expectedData.length; i++)
				assertEquals(expectedData[i], actualData[i]);
			
			assertTrue(actualFile.delete());
		}
	}

	protected void assertDelete(final Item item) throws IntegrityViolationException
	{
		assertTrue(item.existsCopeItem());
		item.deleteCopeItem();
		assertTrue(!item.existsCopeItem());
	}

	void assertDeleteFails(final Item item, final ItemAttribute attribute)
	{
		assertDeleteFails(item, attribute, item);
	}
	
	void assertDeleteFails(final Item item, final ItemAttribute attribute, final Item itemToBeDeleted)
	{
		try
		{
			item.deleteCopeItem();
			fail("should have thrown IntegrityViolationException");
		}
		catch(IntegrityViolationException e)
		{
			assertEquals(attribute, e.getAttribute());
			assertEquals(attribute, e.getFeature());
			assertEquals(itemToBeDeleted, e.getItem());
			assertEquals("integrity violation on deletion of " + itemToBeDeleted.getCopeID() + " because of " + e.getAttribute(), e.getMessage());
		}
		assertTrue(item.existsCopeItem());
	}
	
	protected void assertID(final int id, final Item item)
	{
		assertTrue(item.getCopeID()+"/"+id, item.getCopeID().endsWith("."+id));
	}

	protected void assertIDFails(final String id, final String detail, final boolean notAnID)
	{
		try
		{
			model.findByID(id);
			fail("should have thrown NoSuchIDException");
		}
		catch(NoSuchIDException e)
		{
			assertEquals("no such id <" + id + ">, " + detail, e.getMessage());
			assertEquals(notAnID, e.notAnID());
		}
	}

	protected void activate(final Transaction transaction)
	{
		model.leaveTransaction();
		model.joinTransaction( transaction );
	}
	
	void assertSameCache(final Object o1, final Object o2)
	{
		if(cache)
			assertSame(o1, o2);
		else
			assertNotSame(o1, o2);
	}
	
}
