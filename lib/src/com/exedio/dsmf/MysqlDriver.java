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

package com.exedio.dsmf;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Iterator;
import java.util.StringTokenizer;

import com.exedio.dsmf.Node.ResultSetHandler;


public final class MysqlDriver extends Driver
{
	final String primaryKeyColumnName;
	
	public MysqlDriver(final String primaryKeyColumnName)
	{
		super(null);
		this.primaryKeyColumnName = primaryKeyColumnName;
	}

	private static final char PROTECTOR = '`';

	public String protectName(final String name)
	{
		return PROTECTOR + name + PROTECTOR;
	}
	
	String getColumnType(final int dataType, final ResultSet resultSet) throws SQLException
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
	
	private final String unprotectName(final String protectedName)
	{
		final int length = protectedName.length();
		if(length<3)
			throw new RuntimeException(protectedName);
		if(protectedName.charAt(0)!=MysqlDriver.PROTECTOR)
			throw new RuntimeException(protectedName);
		if(protectedName.charAt(length-1)!=MysqlDriver.PROTECTOR)
			throw new RuntimeException(protectedName);

		return protectedName.substring(1, protectedName.length()-1);
	}

	void verify(final Schema schema)
	{
		super.verify(schema);
		{
			for(Iterator i = schema.getTables().iterator(); i.hasNext(); )
			{
				final Table table = (Table)i.next();
				if(!table.exists())
					continue;
				
				{
					final StringBuffer bf = new StringBuffer();
					bf.append("show columns from ").
						append(protectName(table.name));
					
					schema.querySQL(bf.toString(), new Node.ResultSetHandler()
						{
							public void run(final ResultSet resultSet) throws SQLException
							{
								//printMeta(resultSet);
								while(resultSet.next())
								{
									//printRow(resultSet);
									final String key = resultSet.getString("Key");
									if("PRI".equals(key))
									{
										final String field = resultSet.getString("Field");
										if(primaryKeyColumnName.equals(field) && table.required())
										{
											for(Iterator j = table.getConstraints().iterator(); j.hasNext(); )
											{
												final Constraint c = (Constraint)j.next();
												if(c instanceof PrimaryKeyConstraint)
												{
													table.notifyExistentPrimaryKeyConstraint(c.name);
													break;
												}
											}
										}
										else
											table.notifyExistentPrimaryKeyConstraint(field+"_Pk");
									}
								}
							}
						});
				}
				{
					final StringBuffer bf = new StringBuffer();
					bf.append("show create table ").
						append(protectName(table.name));
					
					schema.querySQL(bf.toString(), new ResultSetHandler()
						{
							public void run(final ResultSet resultSet) throws SQLException
							{
								while(resultSet.next())
								{
									final String tableName = resultSet.getString("Table");
									final String createTable = resultSet.getString("Create Table");
									final Table table = schema.notifyExistentTable(tableName);
									//System.out.println("----------"+tableName+"----"+createTable);
									final StringTokenizer t = new StringTokenizer(createTable);
									for(String s = t.nextToken(); t.hasMoreTokens(); s = t.nextToken())
									{
										//System.out.println("----------"+tableName+"---------------"+s);
										if("CONSTRAINT".equals(s))
										{
											if(!t.hasMoreTokens())
												continue;
											final String protectedName = t.nextToken();
											//System.out.println("----------"+tableName+"--------------------protectedName:"+protectedName);
											final String name = unprotectName(protectedName);
											//System.out.println("----------"+tableName+"--------------------name:"+name);
											if(!t.hasMoreTokens() || !"FOREIGN".equals(t.nextToken()) ||
												!t.hasMoreTokens() || !"KEY".equals(t.nextToken()) ||
												!t.hasMoreTokens())
												continue;
											final String source = t.nextToken();
											//System.out.println("----------"+tableName+"--------------------source:"+source);
											if(!t.hasMoreTokens() || !"REFERENCES".equals(t.nextToken()) ||
												!t.hasMoreTokens())
												continue;
											final String targetTable = t.nextToken();
											//System.out.println("----------"+tableName+"--------------------targetTable:"+targetTable);
											if(!t.hasMoreTokens())
												continue;
											final String targetAttribute = t.nextToken();
											//System.out.println("----------"+tableName+"--------------------targetAttribute:"+targetAttribute);
											
											table.notifyExistentForeignKeyConstraint(name);
										}
										//UNIQUE KEY `AttriEmptyItem_parKey_Unq` (`parent`,`key`)
										if("UNIQUE".equals(s))
										{
											if(!t.hasMoreTokens() || !"KEY".equals(t.nextToken()) ||
												!t.hasMoreTokens())
												continue;
											final String protectedName = t.nextToken();
											//System.out.println("----------"+tableName+"--------------------protectedName:"+protectedName);
											final String name = unprotectName(protectedName);
											//System.out.println("----------"+tableName+"--------------------name:"+name);
											if(!t.hasMoreTokens())
												continue;
											final String clause = t.nextToken();
											//System.out.println("----------"+tableName+"--------------------clause:"+clause);

											final int clauseLengthM1 = clause.length()-1;
											table.notifyExistentUniqueConstraint(name, clause.charAt(clauseLengthM1)==',' ? clause.substring(0, clauseLengthM1) : clause);
										}
									}
								}
							}
						});
				}
			}
		}
	}

	void appendTableCreateStatement(final StringBuffer bf)
	{
		bf.append(" engine=innodb");
	}
	
	boolean needsTargetColumnName()
	{
		return true;
	}
	
	String getRenameColumnStatement(final String tableName, final String oldColumnName, final String newColumnName, final String columnType)
	{
		final StringBuffer bf = new StringBuffer();
		bf.append("alter table ").
			append(tableName).
			append(" change ").
			append(oldColumnName).
			append(' ').
			append(newColumnName).
			append(' ').
			append(columnType);
		return bf.toString();
	}

	// TODO is same as hsqldb
	String getCreateColumnStatement(final String tableName, final String columnName, final String columnType)
	{
		final StringBuffer bf = new StringBuffer();
		bf.append("alter table ").
			append(tableName).
			append(" add column ").
			append(columnName).
			append(' ').
			append(columnType);
		return bf.toString();
	}

	String getModifyColumnStatement(final String tableName, final String columnName, final String newColumnType)
	{
		throw new RuntimeException("not implemented");
	}

	String getDropForeignKeyConstraintStatement(final String tableName, final String constraintName)
	{
		final StringBuffer bf = new StringBuffer();
		bf.append("alter table ").
			append(tableName).
			append(" drop foreign key ").
			append(constraintName);
		return bf.toString();
	}
	
}
