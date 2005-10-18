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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import oracle.jdbc.OracleStatement;
import bak.pcj.IntIterator;
import bak.pcj.list.IntList;
import bak.pcj.map.IntKeyChainedHashMap;

import com.exedio.dsmf.Column;
import com.exedio.dsmf.OracleDriver;
import com.exedio.dsmf.SQLRuntimeException;
import com.exedio.dsmf.Schema;
import com.exedio.dsmf.Table;

final class OracleDatabase
		extends Database
		implements
			DatabaseColumnTypesDefinable,
			DatabaseTimestampCapable
{
	static
	{
		try
		{
			Class.forName(oracle.jdbc.driver.OracleDriver.class.getName());
		}
		catch(ClassNotFoundException e)
		{
			throw new NestingRuntimeException(e);
		}
	}
	
	protected OracleDatabase(final Properties properties)
	{
		super(new OracleDriver(properties.getDatabaseUser().toUpperCase()), properties);
	}
	
	String getIntegerType(final int precision)
	{
		return "NUMBER(" + precision + ')';
	}

	String getDoubleType(final int precision)
	{
		return "NUMBER(" + precision + ",8)";
	}

	String getStringType(final int maxLength)
	{
		return "NVARCHAR2("+(maxLength!=Integer.MAX_VALUE ? maxLength : 2000)+")";
	}
	
	String getDayType()
	{
		return "DATE";
	}
	
	public String getDateTimestampType()
	{
		return "TIMESTAMP(3)";
	}
	
	int getLimitSupport()
	{
		// TODO: ROWNUMs cannot be supported through the interface of this method
		return LIMIT_SUPPORT_NONE;
	}

	void appendLimitClause(final Statement bf, final int start, final int count)
	{
		throw new RuntimeException(bf.toString());
	}
	
	protected boolean supportsEmptyStrings()
	{
		return false;
	}

	private String extractConstraintName(final SQLException e, final int vendorCode, final String start, final String end)
	{
		if(e.getErrorCode()!=vendorCode)
			return null;
		
		final String m = e.getMessage();
		if(m.startsWith(start) && m.endsWith(end))
		{
			final int pos = m.indexOf('.', start.length());
			return m.substring(pos+1, m.length()-end.length());
		}
		else
			return null;
	}
	
	protected String extractUniqueConstraintName(final SQLException e)
	{
		return extractConstraintName(e, 1, "ORA-00001: unique constraint (", ") violated\n");
	}

	public void defineColumnTypes(final IntList columnTypes, final java.sql.Statement statement)
			throws SQLException
	{
		//System.out.println("defineColumnTypes: "+columnTypes);
		final OracleStatement s = (OracleStatement)statement;
		int columnIndex = 1;
		for(IntIterator i = columnTypes.iterator(); i.hasNext(); columnIndex++)
		{
			final int columnType = i.next();
			s.defineColumnType(columnIndex, columnType);
		}
	}

	protected void completeSchema(final Schema schema)
	{
		final Table planTable = new Table(schema, "PLAN_TABLE");
		planTable.makeDefensive();
		new Column(planTable, STATEMENT_ID, "VARCHAR2(30)");
		new Column(planTable, "TIMESTAMP", "DATE");
		new Column(planTable, "REMARKS", "VARCHAR2(80)");
		new Column(planTable, OPERATION, "VARCHAR2(30)");
		new Column(planTable, OPTIONS, "VARCHAR2(30)");
		new Column(planTable, "OBJECT_NODE", "VARCHAR2(128)");
		new Column(planTable, "OBJECT_OWNER", "VARCHAR2(30)");
		new Column(planTable, OBJECT_NAME, "VARCHAR2(30)");
		new Column(planTable, OBJECT_INSTANCE, "NUMBER(22)");
		new Column(planTable, OBJECT_TYPE, "VARCHAR2(30)");
		new Column(planTable, "OPTIMIZER", "VARCHAR2(255)");
		new Column(planTable, "SEARCH_COLUMNS", "NUMBER(22)");
		new Column(planTable, ID, "NUMBER(22)");
		new Column(planTable, PARENT_ID, "NUMBER(22)");
		new Column(planTable, "POSITION", "NUMBER(22)");
		new Column(planTable, "COST", "NUMBER(22)");
		new Column(planTable, "CARDINALITY", "NUMBER(22)");
		new Column(planTable, "BYTES", "NUMBER(22)");
		new Column(planTable, "OTHER_TAG", "VARCHAR2(255)");
		new Column(planTable, "OTHER", "LONG");
	}
	
	protected StatementInfo makeStatementInfo(final Statement statement, final Connection connection)
	{
		final StatementInfo result = super.makeStatementInfo(statement, connection);

		final StatementInfo planInfo = makePlanInfo(statement, connection);
		if(planInfo!=null)
			result.addChild(planInfo);
		
		return result;
	}
	
	private static final Random statementIDCounter = new Random();
	
	private static final String PLAN_TABLE = "PLAN_TABLE";
	private static final String STATEMENT_ID = "STATEMENT_ID";
	private static final String OPERATION = "OPERATION";
	private static final String OPTIONS = "OPTIONS";
	private static final String OBJECT_NAME = "OBJECT_NAME";
	private static final String OBJECT_INSTANCE = "OBJECT_INSTANCE";
	private static final String OBJECT_TYPE = "OBJECT_TYPE";
	private static final String ID = "ID";
	private static final String PARENT_ID = "PARENT_ID";

	private static final String STATEMENT_ID_PREFIX = "cope";

	private static final HashSet skippedColumnNames = new HashSet(Arrays.asList(new String[]{
			STATEMENT_ID,
			OPERATION,
			OPTIONS,
			"TIMESTAMP",
			"OBJECT_OWNER",
			OBJECT_NAME,
			OBJECT_INSTANCE,
			OBJECT_TYPE,
			ID,
			PARENT_ID,
			"POSITION",
		}));
	
	private StatementInfo makePlanInfo(final Statement statement, final Connection connection)
	{
		final String statementText = statement.getText();
		if(statementText.startsWith("alter table "))
			return null;
		
		final int statementIDNumber;
		synchronized(statementIDCounter)
		{
			statementIDNumber = statementIDCounter.nextInt();
		}
		final String statementID = STATEMENT_ID_PREFIX + Integer.toString(Math.abs(statementIDNumber));
		
		final StatementInfo root;
		{
			final Statement bf = createStatement();
			bf.append("explain plan set "+STATEMENT_ID+"='").
				append(statementID). // TODO use placeholders for prepared statements
				append("' for ").
				append(statementText);
			java.sql.Statement sqlExplainStatement = null;
			try
			{
				// TODO: use executeSQLUpdate
				sqlExplainStatement = connection.createStatement();
				sqlExplainStatement.executeUpdate(bf.getText());
			}
			catch(SQLException e)
			{
				throw new SQLRuntimeException(e, bf.toString());
			}
			finally
			{
				if(sqlExplainStatement!=null)
				{
					try
					{
						sqlExplainStatement.close();
					}
					catch(SQLException e)
					{
						// exception is already thrown
					}
				}
			}
		}
		{
			final Statement bf = createStatement();
			bf.append("select * from "+PLAN_TABLE+" where "+STATEMENT_ID+'=').
				appendValue(statementID).
				append(" order by "+ID);

			final PlanResultSetHandler handler = new PlanResultSetHandler();
			executeSQLQuery(connection, bf, handler, false);
			root = handler.root;
		}
		if(root==null)
			throw new RuntimeException();
		
		final StatementInfo result = new StatementInfo("execution plan statement_id = " + statementID);
		result.addChild(root);
		
		//System.out.println("######################");
		//System.out.println(statement.getText());
		//root.print(System.out);
		//System.out.println("######################");
		return result;
	}
	
	private static class PlanResultSetHandler implements ResultSetHandler
	{
		StatementInfo root;

		public void run(final ResultSet resultSet) throws SQLException
		{
			final IntKeyChainedHashMap infos = new IntKeyChainedHashMap();

			final ResultSetMetaData metaData = resultSet.getMetaData();
			final int columnCount = metaData.getColumnCount();

			while(resultSet.next())
			{
				final String operation = resultSet.getString(OPERATION);
				final String options = resultSet.getString(OPTIONS);
				final String objectName = resultSet.getString(OBJECT_NAME);
				final int objectInstance = resultSet.getInt(OBJECT_INSTANCE);
				final String objectType = resultSet.getString(OBJECT_TYPE);
				final int id = resultSet.getInt(ID);
				final Number parentID = (Number)resultSet.getObject(PARENT_ID);
				
				final StringBuffer bf = new StringBuffer(operation);

				if(options!=null)
					bf.append(" (").append(options).append(')');

				if(objectName!=null)
					bf.append(" on ").append(objectName);
				
				if(objectInstance!=0)
					bf.append('[').append(objectInstance).append(']');

				if(objectType!=null)
					bf.append('[').append(objectType).append(']');
				

				for(int i = 1; i<=columnCount; i++)
				{
					final String columnName = metaData.getColumnName(i);
					if(!skippedColumnNames.contains(columnName))
					{
						final Object value = resultSet.getObject(i);
						if(value!=null)
						{
							bf.append(' ').
								append(columnName.toLowerCase()).
								append('=').
								append(value.toString());
						}
					}
				}

				final StatementInfo info = new StatementInfo(bf.toString());
				if(parentID==null)
				{
					if(root!=null)
						throw new RuntimeException(String.valueOf(id));
					root = info;
				}
				else
				{
					final StatementInfo parent = (StatementInfo)infos.get(parentID.intValue());
					if(parent==null)
						throw new RuntimeException();
					parent.addChild(info);
				}
				infos.put(id, info);
			}
		}
		
	}
	
}
