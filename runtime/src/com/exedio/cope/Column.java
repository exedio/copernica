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

import java.sql.ResultSet;
import java.sql.SQLException;

import com.exedio.dsmf.CheckConstraint;
import com.exedio.dsmf.PrimaryKeyConstraint;

abstract class Column
{
	final Table table;
	final String id;
	final String protectedID;
	final boolean primaryKey;
	final boolean optional;
	final int typeForDefiningColumn;
	
	Column(
			final Table table, final String id,
			final boolean primaryKey, final boolean optional,
			final int typeForDefiningColumn)
	{
		this.table = table;
		this.id = table.database.makeName(table.id, id).intern();
		this.protectedID = table.database.getDriver().protectName(this.id).intern();
		this.primaryKey = primaryKey;
		this.optional = optional;
		this.typeForDefiningColumn = typeForDefiningColumn;
		table.addColumn(this);
	}
	
	abstract String getDatabaseType();
	
	final StringColumn getTypeColumn()
	{
		if(!primaryKey)
			throw new RuntimeException(id);
		
		return table.typeColumn;
	}

	abstract String getCheckConstraintIfNotNull(); // TODO SOON rename to getCheckConstraintIgnoringMandatory
	
	public final String toString()
	{
		return id;
	}

	/**
	 * Loads the value of the column from a result set,
	 * that loads the item into memory, and put the results into
	 * a row.
	 */
	abstract void load(ResultSet resultSet, int columnIndex, Row row) throws SQLException;
	abstract String cacheToDatabase(Object cache);
	abstract Object cacheToDatabasePrepared(Object cache);
	abstract Object getCheckValue();

	void makeSchema(final com.exedio.dsmf.Table dsmfTable)
	{
		new com.exedio.dsmf.Column(dsmfTable, id, getDatabaseType());

		final String ccinn = getCheckConstraintIfNotNull();
		if(primaryKey)
		{
			new PrimaryKeyConstraint(dsmfTable, table.database.makeName(table.id + "_" + "Pk"), id);
			if(ccinn!=null)
				new CheckConstraint(dsmfTable, table.database.makeName(table.id + "_" + id + "_CkPk"), ccinn);
		}
		else
		{
			final String checkConstraint;
			
			if(optional)
			{
				if(ccinn!=null)
					checkConstraint = "(" + ccinn + ") OR (" + protectedID + " IS NULL)";
				else
					checkConstraint = null;
			}
			else
			{
				if(ccinn!=null)
					checkConstraint = "(" + protectedID + " IS NOT NULL) AND (" + ccinn + ')';
				else
					checkConstraint = protectedID + " IS NOT NULL";
			}

			if(checkConstraint!=null)
				new CheckConstraint(dsmfTable, table.database.makeName(table.id + "_" + id + "_Ck"), checkConstraint);
		}
	}
		
}
