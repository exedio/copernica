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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Iterator;

import com.mysql.jdbc.Driver;

/**
 * This MySQL driver requires the InnoDB engine.
 * It makes no sense supporting older engines,
 * since cope heavily depends on foreign key constraints.
 * @author Ralf Wiebicke
 */
public final class MysqlDatabase extends Database

// TODO
//	implements DatabaseTimestampCapable
// would require type "timestamp(14,3) null default null"
// but (14,3) is not yet supported
// "null default null" is needed to allow null and
// make null the default value
// This works with 4.1.6 and higher only

{
	static
	{
		try
		{
			Class.forName(Driver.class.getName());
		}
		catch(ClassNotFoundException e)
		{
			throw new NestingRuntimeException(e);
		}
	}

	protected MysqlDatabase(final Properties properties)
	{
		super(properties, null);
	}

	String getIntegerType(final int precision)
	{
		// TODO: use precision to select between TINYINT, SMALLINT, INTEGER, BIGINT, NUMBER
		return (precision <= 10) ? "integer" : "bigint";
	}

	String getDoubleType(final int precision)
	{
		return "double";
	}

	String getStringType(final int maxLength)
	{
		// TODO:
		// 255 is needed for unique columns only,
		// non-unique can have more,
		// and for longer unique columns you may specify a shorter key length
		
		// IMPLEMENTATION NOTE: "binary" is needed to make string comparisions case sensitive
		return "varchar("+(maxLength!=Integer.MAX_VALUE ? maxLength : 255)+") binary";
	}
	
	protected String getColumnType(final int dataType, final ResultSet resultSet) throws SQLException
	{
		switch(dataType)
		{
			case Types.INTEGER:
				return "integer";
			case Types.BIGINT:
				return "bigint";
			case Types.DOUBLE:
				return "double";
			case Types.TIMESTAMP:
				return "timestamp";
			case Types.VARCHAR:
				final int columnSize = resultSet.getInt("COLUMN_SIZE");
				return "varchar("+columnSize+") binary";
			default:
				return null;
		}
	}

	protected String protectName(final String name)
	{
		return '`' + name + '`';
	}

	private final String extracteConstraintName(final SQLException e, final int vendorCode, final String start)
	{
		// TODO: MySQL does not deliver constraint name in exception
		//System.out.println("-u-"+e.getClass()+" "+e.getCause()+" "+e.getErrorCode()+" "+e.getLocalizedMessage()+" "+e.getSQLState()+" "+e.getNextException());

		if(e.getErrorCode()==vendorCode &&
				e.getMessage().startsWith(start))
			return ANY_CONSTRAINT;
		else
			return null;
	}

	protected String extractUniqueConstraintName(final SQLException e)
	{
		return extracteConstraintName(e, 1062, "Duplicate entry ");
	}

	protected String extractIntegrityConstraintName(final SQLException e)
	{
		return extracteConstraintName(e, 1217, "Cannot delete or update a parent row: a foreign key constraint fails");
	}

	void fillReport(final Report report)
	{
		super.fillReport(report);
		{
			for(Iterator i = report.getTables().iterator(); i.hasNext(); )
			{
				final ReportTable table = (ReportTable)i.next();
				final Statement bf = createStatement();

				bf.append("show columns from ").
					append(protectName(table.name));
				
				try
				{
					executeSQLQuery(bf, new ReportConstraintHandler(report, table), false);
				}
				catch(NestingRuntimeException nre)
				{
					final Exception e = nre.getNestedCause();
					if(e!=null)
					{
						final String m = e.getMessage();
						//System.out.println("--------------"+m);
						if(e instanceof SQLException && m.startsWith("Table ") && m.endsWith(" doesn't exist"))
							; // ignore
						else
							throw nre;
					}
					else
						throw nre;
				}
			}
		}
	}

	private static class ReportConstraintHandler implements ResultSetHandler
	{
		private final Report report;
		private final ReportTable reportTable;

		ReportConstraintHandler(final Report report, final ReportTable reportTable)
		{
			this.report = report;
			this.reportTable = reportTable;
		}

		public void run(final ResultSet resultSet) throws SQLException
		{
			//printMeta(resultSet);
			final Table table = reportTable.table;
			while(resultSet.next())
			{
				//printRow(resultSet);
				final String key = resultSet.getString("Key");
				if("PRI".equals(key))
				{
					final String field = resultSet.getString("Field");
					if("this".equals(field) && table!=null)
						reportTable.notifyExistentConstraint(table.getPrimaryKey().getPrimaryKeyConstraintID(), ReportConstraint.TYPE_PRIMARY_KEY);
					else
						reportTable.notifyExistentConstraint(field+"_Pk", ReportConstraint.TYPE_PRIMARY_KEY);
				}
			}
		}
	}

	protected Statement getDropForeignKeyConstraintStatement(final Table table, final ItemColumn column)
	{
		final Statement bf = createStatement();
		bf.append("alter table ").
			append(table.protectedID).
			append(" drop foreign key ").
			append(protectName(column.integrityConstraintName));
		return bf;
	}
	
	Statement getRenameColumnStatement(final String tableName, final String oldColumnName, final String newColumnName, final String columnType)
	{
		final Statement bf = createStatement();
		bf.append("alter table ").
			append(tableName).
			append(" change ").
			append(oldColumnName).
			append(' ').
			append(newColumnName).
			append(' ').
			append(columnType);
		return bf;
	}

	// TODO is same as hsqldb
	Statement getCreateColumnStatement(final String tableName, final String columnName, final String columnType)
	{
		final Statement bf = createStatement();
		bf.append("alter table ").
			append(tableName).
			append(" add column ").
			append(columnName).
			append(' ').
			append(columnType);
		return bf;
	}

	Statement getModifyColumnStatement(final String tableName, final String columnName, final String newColumnType)
	{
		throw new RuntimeException("not implemented");
	}

}
