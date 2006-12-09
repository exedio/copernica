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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.exedio.dsmf.Driver;

final class MigrationStep // TODO make public when migration has matured
{
	final int version;
	final String comment;
	final Body body;
	
	MigrationStep(final int version, final String comment, final Body body) // TODO make public when migration has matured
	{
		this.version = version;
		this.comment = comment;
		this.body = body;
		
		if(version<0)
			throw new IllegalArgumentException("version must not be negative");
		if(comment==null)
			throw new NullPointerException("comment must not be null");
		if(body==null)
			throw new NullPointerException("body must not be null");
	}
	
	static abstract class Body // TODO make public when migration has matured
	{
		private final Object lock = new Object();
		private Driver driver;
		private ArrayList<String> result;
		
		final List<String> getPatch(final Driver driver)
		{
			assert driver!=null;
			
			synchronized(lock)
			{
				assert this.driver==null;
				assert this.result==null;
				
				try
				{
					this.driver = driver;
					this.result = new ArrayList<String>();
					execute();
					return Collections.unmodifiableList(result);
				}
				finally
				{
					this.driver = null;
					this.result = null;
				}
			}
		}
		
		public final String protectName(final String name)
		{
			return driver.protectName(name);
		}
		
		public final void sql(final String sql)
		{
			if(sql==null)
				throw new NullPointerException("sql must not be null");
			
			result.add(sql);
		}
		
		public final void renameColumn(final String tableName, final String oldColumnName, final String newColumnName, final String columnType)
		{
			sql(driver.renameColumn(tableName, oldColumnName, newColumnName, columnType));
		}
		
		public final void createColumn(final String tableName, final String columnName, final String columnType)
		{
			sql(driver.createColumn(tableName, columnName, columnType));
		}
		
		public final void modifyColumn(final String tableName, final String columnName, final String newColumnType)
		{
			sql(driver.modifyColumn(tableName, columnName, newColumnType));
		}

		public final void dropPrimaryKeyConstraint(final String tableName, final String constraintName)
		{
			sql(driver.dropPrimaryKeyConstraint(tableName, constraintName));
		}
		
		public final void dropForeignKeyConstraint(final String tableName, final String constraintName)
		{
			sql(driver.dropForeignKeyConstraint(tableName, constraintName));
		}
		
		public final void dropUniqueConstraint(final String tableName, final String constraintName)
		{
			sql(driver.dropUniqueConstraint(tableName, constraintName));
		}
		
		public abstract void execute();
	}
	
	@Override
	public String toString()
	{
		return "MS" + version + ':' + comment;
	}
}
