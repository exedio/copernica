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

import java.util.Iterator;

import com.exedio.cope.junit.CopeAssert;
import com.exedio.dsmf.Column;
import com.exedio.dsmf.Driver;
import com.exedio.dsmf.Schema;
import com.exedio.dsmf.Table;

public class MigrationTest extends CopeAssert
{
	private static final Model model1 = new Model(6, null, MigrationItem1.TYPE);
	
	private static final Model model2 = new Model(7, null, MigrationItem2.TYPE);
	
	public void testMigrations()
	{
		try
		{
			new Migration(-1, null, (String[])null);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals("version must not be negative", e.getMessage());
		}
		try
		{
			new Migration(0, null, (String[])null);
			fail();
		}
		catch(NullPointerException e)
		{
			assertEquals("comment must not be null", e.getMessage());
		}
		try
		{
			new Migration(0, "some comment", (String[])null);
			fail();
		}
		catch(NullPointerException e)
		{
			assertEquals("body must not be null", e.getMessage());
		}
		try
		{
			new Migration(0, "some comment", new String[0]);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals("body must not be empty", e.getMessage());
		}
	}
		
	public void testMigrate()
	{
		final Properties props = new Properties();
		
		model1.connect(props);
		model1.tearDownDatabase();
		model1.createDatabase();
		
		assertSchema(model1.getVerifiedSchema(), false, false);
		model1.disconnect();
		
		model2.connect(props);
		assertSchema(model2.getVerifiedSchema(), true, false);

		model2.setMigrations(new Migration[]{
				new Migration(6, "nonsense6", "nonsense statement causing a test failure if executed for version 6"),
				new Migration(8, "nonsense8", "nonsense statement causing a test failure if executed for version 8"),
			});
		try
		{
			model2.migrateIfSupported();
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals("no migration for versions [7] on migration from 6 to 7", e.getMessage());
		}
		assertSchema(model2.getVerifiedSchema(), true, false);
		
		model2.setMigrations(new Migration[]{
				new Migration(7, "nonsense7a", "nonsense statement causing a test failure if executed for version 7a"),
				new Migration(7, "nonsense7b", "nonsense statement causing a test failure if executed for version 7b"),
			});
		try
		{
			model2.migrateIfSupported();
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals("there is more than one migration for version 7: nonsense7a and nonsense7b", e.getMessage());
		}
		assertSchema(model2.getVerifiedSchema(), true, false);
		
		final Database database = model2.getDatabase();
		final Driver driver = database.driver;
		final Migration[] migrations2 = new Migration[]{
				new Migration(5, "nonsense", "nonsense statement causing a test failure if executed for version 5"),
				new Migration(6, "nonsense", "nonsense statement causing a test failure if executed for version 6"),
				// BEWARE:
				// Never do this in real projects,
				// always use plain string literals
				// containing the sql statement!
				new Migration(7, "add column field2", driver.createColumn(driver.protectName("MigrationItem"), driver.protectName("field2"), database.getStringType(100))),
				new Migration(8, "nonsense", "nonsense statement causing a test failure if executed for version 8"),
				new Migration(9, "nonsense", "nonsense statement causing a test failure if executed for version 9"),
			};
		assertEquals("MS7:add column field2", migrations2[2].toString());
		
		model2.setMigrations(migrations2);
		model2.migrateIfSupported();
		assertSchema(model2.getVerifiedSchema(), true, true);
		
		// test, that MigrationStep is not executed again,
		// causing a SQLException because column does already exist
		model2.migrate();
		assertSchema(model2.getVerifiedSchema(), true, true);
		
		model2.tearDownDatabase();
	}
	
	private void assertSchema(final Schema schema, final boolean model2, final boolean migrated)
	{
		final Table table = schema.getTable("MigrationItem");
		assertEquals("MigrationItem", table.getName());
		assertEquals(true, table.required());
		assertEquals(true, table.exists());
		final Iterator<Column> columns = table.getColumns().iterator();

		final Column columnThis = columns.next();
		assertEquals("this", columnThis.getName());
		assertEquals(true, columnThis.required());
		assertEquals(true, columnThis.exists());
		assertNotNull(columnThis.getType());
		
		final Column column1 = columns.next();
		assertEquals("field1", column1.getName());
		assertEquals(true, column1.required());
		assertEquals(true, column1.exists());
		assertNotNull(column1.getType());
		
		if(model2)
		{
			final Column column2 = columns.next();
			assertEquals("field2", column2.getName());
			assertEquals(true, column2.required());
			assertEquals(migrated, column2.exists());
			assertNotNull(column2.getType());
		}
		
		assertFalse(columns.hasNext());
		
		final Table migrationTable = schema.getTable("while");
		assertEquals("while", migrationTable.getName());
		assertEquals(true, migrationTable.required());
		assertEquals(true, migrationTable.exists());
	}
}
