package com.exedio.cope.lib;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;

import oracle.jdbc.OracleStatement;
import oracle.jdbc.driver.OracleDriver;
import bak.pcj.IntIterator;
import bak.pcj.list.IntList;
import bak.pcj.map.IntKeyChainedHashMap;

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
			Class.forName(OracleDriver.class.getName());
		}
		catch(ClassNotFoundException e)
		{
			throw new NestingRuntimeException(e);
		}
	}

	protected OracleDatabase(final Properties properties)
	{
		super(properties);
	}
	
	String getIntegerType(final int precision)
	{
		return "NUMBER(" + precision + ",0)";
	}

	String getDoubleType(final int precision)
	{
		return "NUMBER(" + precision + ",8)";
	}

	String getStringType(final int maxLength)
	{
		return "VARCHAR2("+(maxLength!=Integer.MAX_VALUE ? maxLength : 2000)+")";
	}
	
	public String getDateTimestampType()
	{
		return "TIMESTAMP(3)";
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

	protected String extractIntegrityConstraintName(final SQLException e)
	{
		return extractConstraintName(e, 2292, "ORA-02292: integrity constraint (", ") violated - child record found\n");
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


	void fillReport(final Report report)
	{
		{
			final Statement bf = createStatement();
			bf.append("select TABLE_NAME, LAST_ANALYZED from user_tables").
				defineColumnString().
				defineColumnTimestamp();

			executeSQLQuery(bf, new ReportTableHandler(report), false);
		}
		{
			final Statement bf = createStatement();
			bf.append("select TABLE_NAME, COLUMN_NAME, DATA_TYPE, DATA_LENGTH, DATA_PRECISION, DATA_SCALE from user_tab_columns").
				defineColumnString().
				defineColumnString().
				defineColumnString().
				defineColumnInteger().
				defineColumnInteger().
				defineColumnInteger();

			executeSQLQuery(bf, new ReportColumnHandler(report), false);
		}
		{
			final Statement bf = createStatement();
			bf.append(
					"select " +
					"TABLE_NAME," +
					"CONSTRAINT_NAME," +
					"CONSTRAINT_TYPE," +
					"SEARCH_CONDITION " +
					"from user_constraints order by table_name").
				defineColumnString().
				defineColumnString().
				defineColumnString().
				defineColumnString();

			executeSQLQuery(bf, new ReportConstraintHandler(report), false);
		}
	}

	private static class ReportTableHandler implements ResultSetHandler
	{
		private final Report report;

		ReportTableHandler(final Report report)
		{
			this.report = report;
		}

		public void run(ResultSet resultSet) throws SQLException
		{
			while(resultSet.next())
			{
				final String tableName = resultSet.getString(1);
				final Date lastAnalyzed = (Date)resultSet.getObject(2);
				final ReportTable table = report.notifyExistentTable(tableName);
				table.setLastAnalyzed(lastAnalyzed);
				//System.out.println("EXISTS:"+tableName);
			}
		}
	}

	private static class ReportColumnHandler implements ResultSetHandler
	{
		private final Report report;

		ReportColumnHandler(final Report report)
		{
			this.report = report;
		}

		public void run(ResultSet resultSet) throws SQLException
		{
			while(resultSet.next())
			{
				final String tableName = resultSet.getString(1);
				final String columnName = resultSet.getString(2);
				final String dataType = resultSet.getString(3);

				final String columnType;
				if(dataType.equals("NUMBER"))
				{
					final int dataPrecision = resultSet.getInt(5);
					final int dataScale = resultSet.getInt(6);
					columnType = "NUMBER("+dataPrecision+','+dataScale+')';
				}
				else if(dataType.equals("VARCHAR2"))
				{
					final int dataLength = resultSet.getInt(4);
					columnType = "VARCHAR2("+dataLength+')';
				}
				else if(dataType.equals("NVARCHAR2"))
				{
					final int dataLength = resultSet.getInt(4);
					columnType = "NVARCHAR2("+dataLength+')';
				}
				else
					columnType = dataType;
					
				final ReportTable table = report.notifyExistentTable(tableName);
				final ReportColumn column = table.notifyExistentColumn(columnName, columnType);
				//System.out.println("EXISTS:"+tableName);
			}
		}
	}

	private static class ReportConstraintHandler implements ResultSetHandler
	{
		private final Report report;

		ReportConstraintHandler(final Report report)
		{
			this.report = report;
		}

		public void run(ResultSet resultSet) throws SQLException
		{
			while(resultSet.next())
			{
				final String tableName = resultSet.getString(1);
				final String constraintName = resultSet.getString(2);
				final String constraintType = resultSet.getString(3);
				final ReportTable table = report.notifyExistentTable(tableName);
				//System.out.println("tableName:"+tableName+" constraintName:"+constraintName+" constraintType:>"+constraintType+"<");
				final ReportConstraint constraint;
				if("C".equals(constraintType))
				{
					final String searchCondition = resultSet.getString(4);
					//System.out.println("searchCondition:>"+searchCondition+"<");
					constraint = table.notifyExistentCheckConstraint(constraintName, searchCondition);
				}
				else
					constraint = table.notifyExistentConstraint(constraintName);
				//System.out.println("EXISTS:"+tableName);
			}
		}
	}

	Statement getRenameColumnStatement(final String tableName,
			final String oldColumnName, final String newColumnName, final String columnType)
	{
		final Statement bf = createStatement();
		bf.append("alter table ").
			append(tableName).
			append(" rename column ").
			append(oldColumnName).
			append(" to ").
			append(newColumnName);
		return bf;
	}

	Statement getCreateColumnStatement(final String tableName, final String columnName, final String columnType)
	{
		final Statement bf = createStatement();
		bf.append("alter table ").
			append(tableName).
			append(" add (").
			append(columnName).
			append(' ').
			append(columnType).
			append(')');
		return bf;
	}

	Statement getModifyColumnStatement(final String tableName, final String columnName, final String newColumnType)
	{
		final Statement bf = createStatement();
		bf.append("alter table ").
			append(tableName).
			append(" modify ").
			append(columnName).
			append(' ').
			append(newColumnType);
		return bf;
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
	private static final String STATEMENT_ID = "STATEMENT_ID";
	private static final String STATEMENT_ID_PREFIX = "cope";

	private static final HashSet skippedColumnNames = new HashSet(Arrays.asList(new String[]{
			STATEMENT_ID,
			"OPERATION",
			"OPTIONS",
			"TIMESTAMP",
			"OBJECT_OWNER",
			"OBJECT_NAME",
			"OBJECT_INSTANCE",
			"OBJECT_TYPE",
			"ID",
			"PARENT_ID",
			"POSITION",
		}));
	
	private StatementInfo makePlanInfo(final Statement statement, final Connection connection)
	{
		final String statementText = statement.getText();
		if(statementText.startsWith("alter table "))
			return null;
		
		final int statementID;
		synchronized(statementIDCounter)
		{
			statementID = Math.abs(statementIDCounter.nextInt());
		}
		
		StatementInfo root = null;
		{
			final Statement explainStatement = createStatement();
			explainStatement.
				append("explain plan set "+STATEMENT_ID+"='"+STATEMENT_ID_PREFIX).
				append(statementID).
				append("' for ").
				append(statementText);
			java.sql.Statement sqlExplainStatement = null;
			try
			{
				sqlExplainStatement = connection.createStatement();
				sqlExplainStatement.executeUpdate(explainStatement.getText());
			}
			catch(SQLException e)
			{
				throw new NestingRuntimeException(e, explainStatement.toString());
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
			final Statement fetchStatement = createStatement();
			fetchStatement.
				append(
						"select * from plan_table " +
						"where "+STATEMENT_ID+"='"+STATEMENT_ID_PREFIX).
				append(statementID).
				append("' order by ID");
			java.sql.Statement sqlFetchStatement = null;
			ResultSet sqlFetchResultSet = null;
			try
			{
				sqlFetchStatement = connection.createStatement();
				defineColumnTypes(fetchStatement.columnTypes, sqlFetchStatement);
				sqlFetchResultSet = sqlFetchStatement.executeQuery(fetchStatement.getText());
				final IntKeyChainedHashMap infos = new IntKeyChainedHashMap();

				final ResultSetMetaData metaData = sqlFetchResultSet.getMetaData();
				final int columnCount = metaData.getColumnCount();

				while(sqlFetchResultSet.next())
				{
					final String operation = sqlFetchResultSet.getString("OPERATION");
					final String options = sqlFetchResultSet.getString("OPTIONS");
					final String objectName = sqlFetchResultSet.getString("OBJECT_NAME");
					final int objectInstance = sqlFetchResultSet.getInt("OBJECT_INSTANCE");
					final String objectType = sqlFetchResultSet.getString("OBJECT_TYPE");
					final int id = sqlFetchResultSet.getInt("ID");
					final Number parentID = (Number)sqlFetchResultSet.getObject("PARENT_ID");
					
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
							final Object value = sqlFetchResultSet.getObject(i);
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
			catch(SQLException e)
			{
				throw new NestingRuntimeException(e, fetchStatement.toString());
			}
			finally
			{
				if(sqlFetchResultSet!=null)
				{
					try
					{
						sqlFetchResultSet.close();
					}
					catch(SQLException e)
					{
						// exception is already thrown
					}
				}
				if(sqlFetchStatement!=null)
				{
					try
					{
						sqlFetchStatement.close();
					}
					catch(SQLException e)
					{
						// exception is already thrown
					}
				}
			}
		}
		if(root==null)
			throw new RuntimeException();
		
		final StatementInfo result = new StatementInfo("execution plan statement_id = " + STATEMENT_ID_PREFIX + statementID);
		result.addChild(root);
		
		//System.out.println("######################");
		//System.out.println(statement.getText());
		//root.print(System.out);
		//System.out.println("######################");
		return result;
	}
	
}
