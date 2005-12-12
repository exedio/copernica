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

import com.exedio.dsmf.MysqlDriver;
import com.mysql.jdbc.Driver;

/**
 * This MySQL driver requires the InnoDB engine.
 * It makes no sense supporting older engines,
 * since cope heavily depends on foreign key constraints.
 * @author Ralf Wiebicke
 */
public final class MysqlDatabase extends AbstractDatabase

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
			throw new RuntimeException(e);
		}
	}
	
	private static final String TOLOWERCASE = "tolowercase";
	private static final String PLACEHOLDERS_IN_LIMIT = "placeholdersInLimit";
	
	/**
	 * mysql supports placeholders in version 5.0.7 and higher
	 */
	private final boolean placeholdersInLimit;

	protected MysqlDatabase(final Properties properties)
	{
		super(new MysqlDriver(Table.PK_COLUMN_NAME, Boolean.valueOf(properties.getDatabaseCustomProperty(TOLOWERCASE)).booleanValue()), properties);
		this.placeholdersInLimit = "true".equalsIgnoreCase(properties.getDatabaseCustomProperty(PLACEHOLDERS_IN_LIMIT));
	}

	public String getIntegerType(final int precision)
	{
		// TODO: use precision to select between TINYINT, SMALLINT, INTEGER, BIGINT, NUMBER
		return (precision <= 10) ? "integer" : "bigint";
	}

	public String getDoubleType(final int precision)
	{
		return "double";
	}

	public String getStringType(final int maxLength)
	{
		// TODO:
		// 255 is needed for unique columns only,
		// non-unique can have more,
		// and for longer unique columns you may specify a shorter key length
		
		// IMPLEMENTATION NOTE: "binary" is needed to make string comparisions case sensitive
		return "varchar("+(maxLength!=Integer.MAX_VALUE ? maxLength : 255)+") binary";
	}
	
	public String getDayType()
	{
		return "DATE";
	}
	
	public String getDateTimestampType()
	{
		return null;
	}
	
	int getLimitSupport()
	{
		return LIMIT_SUPPORT_CLAUSE_AFTER_WHERE;
	}

	void appendLimitClause(final Statement bf, final int start, final int count)
	{
		if((start==0&&count==Query.UNLIMITED_COUNT)||(count<=0&&count!=Query.UNLIMITED_COUNT)||start<0)
			throw new RuntimeException(start+"-"+count);
		
		bf.append(" limit ");

		if(start>0)
		{
			if(placeholdersInLimit)
				bf.appendParameter(start).append(',');
			else
				bf.append(Integer.toString(start)).append(',');
		}

		// using MAX_VALUE is really the recommended usage, see MySQL doc.
		final int countInStatement = count!=Query.UNLIMITED_COUNT ? count : Integer.MAX_VALUE;

		if(placeholdersInLimit)
			bf.appendParameter(countInStatement);
		else
			bf.append(Integer.toString(countInStatement));
	}
	
	void appendLimitClause2(final Statement bf, final int start, final int count)
	{
		throw new RuntimeException(bf.toString());
	}
	
	protected void appendMatchClauseFullTextIndex(final Statement bf, final StringFunction function, final String value)
	{
		bf.append("(match(").
			append(function, (Join)null).
			append(")against(").
			appendParameter(function, value).
			append("))");
	}
	
	public boolean supportsCheckConstraints()
	{
		return false;
	}

	private final String extractConstraintName(final SQLException e, final int vendorCode, final String start)
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
		return extractConstraintName(e, 1062, "Duplicate entry ");
	}

	protected StatementInfo makeStatementInfo(
			final Statement statement, final Connection connection,
			final long start, final long prepared, final long executed, final long resultRead, final long end)
	{
		final StatementInfo result = super.makeStatementInfo(statement, connection, start, prepared, executed, resultRead, end);

		final StatementInfo planInfo = makePlanInfo(statement, connection);
		if(planInfo!=null)
			result.addChild(planInfo);
		
		return result;
	}
	
	private StatementInfo makePlanInfo(final Statement statement, final Connection connection)
	{
		final String statementText = statement.getText();
		if(statementText.startsWith("alter table "))
			return null;
		
		final StatementInfo root = new StatementInfo(EXPLAIN_PLAN);
		{
			final Statement bf = createStatement();
			bf.append("explain ").
				append(statementText).
				appendParameters(statement);

			executeSQLQuery(connection, bf, new ResultSetHandler(){
				public void run(final ResultSet resultSet) throws SQLException
				{
					final ResultSetMetaData metaData = resultSet.getMetaData();
					final int columnCount = metaData.getColumnCount();

					while(resultSet.next())
					{
						final StringBuffer bf = new StringBuffer();

						for(int i = 1; i<=columnCount; i++)
						{
							final Object value = resultSet.getObject(i);
							if(value!=null)
							{
								if(bf.length()>0)
									bf.append(", ");
								
								bf.append(metaData.getColumnName(i)).
									append('=').
									append(value.toString());
							}
						}
						root.addChild(new StatementInfo(bf.toString()));
					}
				}
				
			}, false);
		}
		
		return root;
	}

}
