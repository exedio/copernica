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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import bak.pcj.list.IntList;

import com.exedio.cope.search.Condition;
import com.exedio.dsmf.Driver;
import com.exedio.dsmf.SQLRuntimeException;
import com.exedio.dsmf.Schema;

abstract class AbstractDatabase implements Database
{
	private final List tables = new ArrayList();
	private final HashMap uniqueConstraintsByID = new HashMap();
	private final HashMap itemColumnsByIntegrityConstraintName = new HashMap();
	private boolean buildStage = true;
	final Driver driver;
	private final boolean prepare;
	private final boolean log;
	private final boolean butterflyPkSource;
	private final boolean fulltextIndex;
	final ConnectionPool connectionPool;
	private final java.util.Properties forcedNames;
	final java.util.Properties tableOptions;
	final int limitSupport;
	
	protected AbstractDatabase(final Driver driver, final Properties properties)
	{
		this.driver = driver;
		this.prepare = !properties.getDatabaseDontSupportPreparedStatements();
		this.log = properties.getDatabaseLog();
		this.butterflyPkSource = properties.getPkSourceButterfly();
		this.fulltextIndex = properties.getFulltextIndex();
		this.connectionPool = new ConnectionPool(properties);
		this.forcedNames = properties.getDatabaseForcedNames();
		this.tableOptions = properties.getDatabaseTableOptions();
		this.limitSupport = properties.getDatabaseDontSupportLimit() ? LIMIT_SUPPORT_NONE : getLimitSupport();

		switch(limitSupport)
		{
			case LIMIT_SUPPORT_NONE:
			case LIMIT_SUPPORT_CLAUSE_AFTER_SELECT:
			case LIMIT_SUPPORT_CLAUSE_AFTER_WHERE:
			case LIMIT_SUPPORT_CLAUSES_AROUND:
				break;
			default:
				throw new RuntimeException(Integer.toString(limitSupport));
		}
		//System.out.println("using database "+getClass());
	}
	
	public final Driver getDriver()
	{
		return driver;
	}
	
	public final java.util.Properties getTableOptions()
	{
		return tableOptions;
	}
	
	public final ConnectionPool getConnectionPool()
	{
		return connectionPool;
	}
	
	public final void addTable(final Table table)
	{
		if(!buildStage)
			throw new RuntimeException();
		tables.add(table);
	}
	
	public final void addUniqueConstraint(final String constraintID, final UniqueConstraint constraint)
	{
		if(!buildStage)
			throw new RuntimeException();

		final Object collision = uniqueConstraintsByID.put(constraintID, constraint);
		if(collision!=null)
			throw new RuntimeException("ambiguous unique constraint "+constraint+" trimmed to >"+constraintID+"< colliding with "+collision);
	}
	
	public final void addIntegrityConstraint(final ItemColumn column)
	{
		if(!buildStage)
			throw new RuntimeException();
		if(itemColumnsByIntegrityConstraintName.put(column.integrityConstraintName, column)!=null)
			throw new RuntimeException("there is more than one integrity constraint with name "+column.integrityConstraintName);
	}
	
	protected final Statement createStatement()
	{
		return createStatement(true);
	}
	
	protected final Statement createStatement(final boolean qualifyTable)
	{
		return new Statement(this, prepare, qualifyTable, isDefiningColumnTypes());
	}
	
	protected final Statement createStatement(final Query query)
	{
		return new Statement(this, prepare, query, isDefiningColumnTypes());
	}
	
	public void createDatabase()
	{
		buildStage = false;
		
		makeSchema().create();
	}

	public void createDatabaseConstraints()
	{
		buildStage = false;
		
		makeSchema().createConstraints();
	}

	//private static int checkTableTime = 0;

	public void checkDatabase(final Connection connection)
	{
		buildStage = false;

		//final long time = System.currentTimeMillis();
		final Statement bf = createStatement(true);
		bf.append("select count(*) from ").defineColumnInteger();
		boolean first = true;

		for(Iterator i = tables.iterator(); i.hasNext(); )
		{
			if(first)
				first = false;
			else
				bf.append(',');

			final Table table = (Table)i.next();
			bf.append(table.protectedID);
		}
		
		bf.append(" where ");
		first = true;
		for(Iterator i = tables.iterator(); i.hasNext(); )
		{
			if(first)
				first = false;
			else
				bf.append(" and ");

			final Table table = (Table)i.next();

			final Column primaryKey = table.primaryKey;
			bf.append(primaryKey, null).
				append('=').
				appendParameter(Type.NOT_A_PK);
			
			for(Iterator j = table.getColumns().iterator(); j.hasNext(); )
			{
				final Column column = (Column)j.next();
				bf.append(" and ").
					append(column, null);
				if(column instanceof BlobColumn)
				{
					bf.append("is not null");
				}
				else
				{
					bf.append('=').
						appendParameter(column, column.getCheckValue());
				}
			}
		}
		
		executeSQLQuery(connection, bf,
			new ResultSetHandler()
			{
				public void run(final ResultSet resultSet) throws SQLException
				{
					if(!resultSet.next())
						throw new RuntimeException();
				}
			},
			false
		);
	}	

	public void dropDatabase()
	{
		buildStage = false;

		makeSchema().drop();
	}
	
	public void dropDatabaseConstraints()
	{
		buildStage = false;

		makeSchema().dropConstraints();
	}
	
	public void tearDownDatabase()
	{
		buildStage = false;

		makeSchema().tearDown();
	}

	public void tearDownDatabaseConstraints()
	{
		buildStage = false;

		makeSchema().tearDownConstraints();
	}
	
	public void checkEmptyDatabase(final Connection connection)
	{
		buildStage = false;

		//final long time = System.currentTimeMillis();
		for(Iterator i = tables.iterator(); i.hasNext(); )
		{
			final Table table = (Table)i.next();
			final int count = countTable(connection, table);
			if(count>0)
				throw new RuntimeException("there are "+count+" items left for table "+table.id);
		}
		//final long amount = (System.currentTimeMillis()-time);
		//checkEmptyTableTime += amount;
		//System.out.println("CHECK EMPTY TABLES "+amount+"ms  accumulated "+checkEmptyTableTime);
	}
	
	public final ArrayList search(final Connection connection, final Query query, final boolean doCountOnly)
	{
		buildStage = false;

		final int limitStart = query.limitStart;
		final int limitCount = query.limitCount;
		final boolean limitActive = limitStart>0 || limitCount!=Query.UNLIMITED_COUNT;

		final ArrayList queryJoins = query.joins;
		final Statement bf = createStatement(query);
		
		if(!doCountOnly && limitActive && limitSupport==LIMIT_SUPPORT_CLAUSES_AROUND)
			appendLimitClause(bf, limitStart, limitCount);
		
		bf.append("select");
		
		if(!doCountOnly && limitActive && limitSupport==LIMIT_SUPPORT_CLAUSE_AFTER_SELECT)
			appendLimitClause(bf, limitStart, limitCount);
		
		bf.append(' ');

		final Selectable[] selectables = query.selectables;
		final Column[] selectColumns = new Column[selectables.length];
		final Type[] selectTypes = new Type[selectables.length];

		if(doCountOnly)
		{
			bf.append("count(*)");
		}
		else
		{
			for(int selectableIndex = 0; selectableIndex<selectables.length; selectableIndex++)
			{
				final Selectable selectable = selectables[selectableIndex];
				final Column selectColumn;
				final Type selectType;
				final Table selectTable;
				final Column selectPrimaryKey;
				if(selectable instanceof Function)
				{
					final Function selectAttribute = (Function)selectable;
					selectType = selectAttribute.getType();
	
					if(selectableIndex>0)
						bf.append(',');
					
					if(selectable instanceof ObjectAttribute)
					{
						selectColumn = ((ObjectAttribute)selectAttribute).getColumn();
						bf.append((ObjectAttribute)selectable, (Join)null).defineColumn(selectColumn);
						if(selectable instanceof ItemAttribute)
						{
							final StringColumn typeColumn = ((ItemAttribute)selectAttribute).getTypeColumn();
							if(typeColumn!=null)
								bf.append(',').	append(typeColumn, (Join)null).defineColumn(typeColumn);
						}
					}
					else
					{
						selectColumn = null;
						final ComputedFunction computedFunction = (ComputedFunction)selectable;
						bf.append(computedFunction, (Join)null).defineColumn(computedFunction);
					}
				}
				else
				{
					selectType = (Type)selectable;
					selectTable = selectType.getTable();
					selectPrimaryKey = selectTable.primaryKey;
					selectColumn = selectPrimaryKey;
	
					if(selectableIndex>0)
						bf.append(',');
					
					bf.appendPK(selectType, (Join)null).defineColumn(selectColumn);
	
					if(selectColumn.primaryKey)
					{
						final StringColumn selectTypeColumn = selectColumn.getTypeColumn();
						if(selectTypeColumn!=null)
						{
							bf.append(',').
								append(selectTypeColumn, (Join)null).defineColumn(selectTypeColumn);
						}
						else
							selectTypes[selectableIndex] = selectType.getOnlyPossibleTypeOfInstances();
					}
					else
						selectTypes[selectableIndex] = selectType.getOnlyPossibleTypeOfInstances();
				}
	
				selectColumns[selectableIndex] = selectColumn;
			}
		}

		bf.append(" from ").
			appendTypeDefinition((Join)null, query.type);

		if(queryJoins!=null)
		{
			for(Iterator i = queryJoins.iterator(); i.hasNext(); )
			{
				final Join join = (Join)i.next();
				
				bf.append(' ').
					append(join.getKindString()).
					append(" join ").
					appendTypeDefinition(join, join.type);
				
				final Condition joinCondition = join.condition;
				if(joinCondition!=null)
				{
					bf.append(" on ");
					joinCondition.appendStatement(bf);
				}
				bf.appendTypeJoinCondition(joinCondition!=null ? " and " : " on ", join, join.type);
			}
		}

		if(query.condition!=null)
		{
			bf.append(" where ");
			query.condition.appendStatement(bf);
		}
		bf.appendTypeJoinCondition(query.condition!=null ? " and " : " where ", (Join)null, query.type);
		
		if(!doCountOnly)
		{
			boolean firstOrderBy = true;		
			if(query.orderBy!=null || query.deterministicOrder)
				bf.append(" order by ");
	
			if(query.orderBy!=null)
			{
				firstOrderBy = false;
	
				bf.append(query.orderBy, (Join)null);
				if(!query.orderAscending)
					bf.append(" desc");
			}
			
			if(query.deterministicOrder) // TODO skip this, if orderBy already orders by some unique condition
			{
				if(!firstOrderBy)
					bf.append(',');
				
				query.type.getPkSource().appendDeterministicOrderByExpression(bf, query.type);
			}

			if(limitActive && limitSupport==LIMIT_SUPPORT_CLAUSE_AFTER_WHERE)
				appendLimitClause(bf, limitStart, limitCount);
		}

		if(!doCountOnly && limitActive && limitSupport==LIMIT_SUPPORT_CLAUSES_AROUND)
			appendLimitClause2(bf, limitStart, limitCount);
		
		final Type[] types = selectTypes;
		final Model model = query.model;
		final ArrayList result = new ArrayList();

		if(limitStart<0)
			throw new RuntimeException();
		if(selectables.length!=selectColumns.length)
			throw new RuntimeException();
		if(selectables.length!=types.length)
			throw new RuntimeException();
		
		//System.out.println(bf.toString());

		query.addStatementInfo(executeSQLQuery(connection, bf, new ResultSetHandler()
			{
				public void run(final ResultSet resultSet) throws SQLException
				{
					if(doCountOnly)
					{
						resultSet.next();
						result.add(new Integer(resultSet.getInt(1)));
						if(resultSet.next())
							throw new RuntimeException();
						return;
					}
					
					if(limitStart>0 && limitSupport==LIMIT_SUPPORT_NONE)
					{
						// TODO: ResultSet.relative
						// Would like to use
						//    resultSet.relative(limitStart+1);
						// but this throws a java.sql.SQLException:
						// Invalid operation for forward only resultset : relative
						for(int i = limitStart; i>0; i--)
							resultSet.next();
					}
						
					int i = ((limitCount==Query.UNLIMITED_COUNT||(limitSupport!=LIMIT_SUPPORT_NONE)) ? Integer.MAX_VALUE : limitCount );
					if(i<=0)
						throw new RuntimeException(String.valueOf(limitCount));
					
					while(resultSet.next() && (--i)>=0)
					{
						int columnIndex = 1;
						final Object[] resultRow = (selectables.length > 1) ? new Object[selectables.length] : null;
						final Row dummyRow = new Row();
							
						for(int selectableIndex = 0; selectableIndex<selectables.length; selectableIndex++)
						{
							final Selectable selectable = selectables[selectableIndex];
							final Object resultCell;
							if(selectable instanceof ObjectAttribute)
							{
								selectColumns[selectableIndex].load(resultSet, columnIndex++, dummyRow);
								final ObjectAttribute selectAttribute = (ObjectAttribute)selectable;
								if(selectable instanceof ItemAttribute)
								{
									final StringColumn typeColumn = ((ItemAttribute)selectAttribute).getTypeColumn();
									if(typeColumn!=null)
										typeColumn.load(resultSet, columnIndex++, dummyRow);
								}
								resultCell = selectAttribute.get(dummyRow);
							}
							else if(selectable instanceof ComputedFunction)
							{
								final ComputedFunction selectFunction = (ComputedFunction)selectable;
								resultCell = selectFunction.load(resultSet, columnIndex++);
							}
							else
							{
								final Number pk = (Number)resultSet.getObject(columnIndex++);
								//System.out.println("pk:"+pk);
								if(pk==null)
								{
									// can happen when using right outer joins
									resultCell = null;
								}
								else
								{
									final Type type = types[selectableIndex];
									final Type currentType;
									if(type==null)
									{
										final String typeID = resultSet.getString(columnIndex++);
										currentType = model.findTypeByID(typeID);
										if(currentType==null)
											throw new RuntimeException("no type with type id "+typeID);
									}
									else
										currentType = type;

									resultCell = currentType.getItemObject(pk.intValue());
								}
							}
							if(resultRow!=null)
								resultRow[selectableIndex] = resultCell;
							else
								result.add(resultCell);
						}
						if(resultRow!=null)
							result.add(Collections.unmodifiableList(Arrays.asList(resultRow)));
					}
				}
			}, query.makeStatementInfo));

		return result;
	}
	
	private void log(final long start, final long end, final String sqlText)
	{
		final SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS");
		System.out.println(df.format(new Date(start)) + "  " + (end-start) + "ms:  " + sqlText);
	}
	
	public void load(final Connection connection, final PersistentState state)
	{
		buildStage = false;

		final Statement bf = createStatement(state.type.getSupertype()!=null);
		bf.append("select ");

		boolean first = true;
		for(Type type = state.type; type!=null; type = type.getSupertype())
		{
			for(Iterator i = type.getTable().getColumns().iterator(); i.hasNext(); )
			{
				final Column column = (Column)i.next();
				if(!(column instanceof BlobColumn))
				{
					if(first)
						first = false;
					else
						bf.append(',');

					bf.append(column, (Join)null).defineColumn(column);
				}
			}
		}
		
		if(first)
		{
			// no columns in type
			bf.appendPK(state.type, (Join)null);
		}

		bf.append(" from ");
		first = true;
		for(Type type = state.type; type!=null; type = type.getSupertype())
		{
			if(first)
				first = false;
			else
				bf.append(',');

			bf.append(type.getTable().protectedID);
		}
			
		bf.append(" where ");
		first = true;
		for(Type type = state.type; type!=null; type = type.getSupertype())
		{
			if(first)
				first = false;
			else
				bf.append(" and ");

			bf.appendPK(type, (Join)null).
				append('=').
				appendParameter(state.pk);
		}

		//System.out.println(bf.toString());
		executeSQLQuery(connection, bf, state, false);
	}

	public void store(final Connection connection, final State state, final boolean present)
			throws UniqueViolationException
	{
		store(connection, state, state.type, present);
	}

	private void store(final Connection connection, final State state, final Type type, final boolean present)
			throws UniqueViolationException
	{
		buildStage = false;

		final Type supertype = type.getSupertype();
		if(supertype!=null)
			store(connection, state, supertype, present);
			
		final Table table = type.getTable();

		final List columns = table.getColumns();

		final Statement bf = createStatement();
		final StringColumn typeColumn = table.typeColumn;
		if(present)
		{
			bf.append("update ").
				append(table.protectedID).
				append(" set ");

			boolean first = true;
			for(Iterator i = columns.iterator(); i.hasNext(); )
			{
				final Column column = (Column)i.next();
				if(!(column instanceof BlobColumn))
				{
					if(first)
						first = false;
					else
						bf.append(',');
					
					bf.append(column.protectedID).
						append('=').
						appendParameter(column, state.store(column));
				}
			}
			if(first) // no columns in table
				return;
			
			bf.append(" where ").
				append(table.primaryKey.protectedID).
				append('=').
				appendParameter(state.pk);
			
			// Additionally check correctness of type column
			// If type column is inconsistent, the database
			// will return "0 rows affected" and executeSQLUpdate
			// will fail
			if(typeColumn!=null)
			{
				bf.append(" and ").
					append(typeColumn.protectedID).
					append('=').
					appendParameter(state.type.id);
			}
		}
		else
		{
			bf.append("insert into ").
				append(table.protectedID).
				append("(").
				append(table.primaryKey.protectedID);
			
			if(typeColumn!=null)
			{
				bf.append(',').
					append(typeColumn.protectedID);
			}

			for(Iterator i = columns.iterator(); i.hasNext(); )
			{
				final Column column = (Column)i.next();
				if(!(column instanceof BlobColumn))
				{
					bf.append(',').
						append(column.protectedID);
				}
			}

			bf.append(")values(").
				appendParameter(state.pk);
			
			if(typeColumn!=null)
			{
				bf.append(',').
					appendParameter(state.type.id);
			}

			for(Iterator i = columns.iterator(); i.hasNext(); )
			{
				final Column column = (Column)i.next();
				if(!(column instanceof BlobColumn))
				{
					bf.append(',').
					   appendParameter(column, state.store(column));
				}
			}
			bf.append(')');
		}

		//System.out.println("storing "+bf.toString());
		final List uqs = type.declaredUniqueConstraints;
		executeSQLUpdate(connection, bf, 1, uqs.size()==1?(UniqueConstraint)uqs.iterator().next():null);
	}

	public void delete(final Connection connection, final Item item)
	{
		buildStage = false;
		final Type type = item.type;
		final int pk = item.pk;

		for(Type currentType = type; currentType!=null; currentType = currentType.getSupertype())
		{
			final Table currentTable = currentType.getTable();
			final Statement bf = createStatement();
			bf.append("delete from ").
				append(currentTable.protectedID).
				append(" where ").
				append(currentTable.primaryKey.protectedID).
				append('=').
				appendParameter(pk);

			//System.out.println("deleting "+bf.toString());

			try
			{
				executeSQLUpdate(connection, bf, 1);
			}
			catch(UniqueViolationException e)
			{
				throw new RuntimeException(e);
			}
		}
	}

	public final byte[] load(final Connection connection, final BlobColumn column, final Item item)
	{
		// TODO reuse code in load blob methods
		buildStage = false;

		final Table table = column.table;
		final Statement bf = createStatement();
		bf.append("select ").
			append(column.protectedID).defineColumn(column).
			append(" from ").
			append(table.protectedID).
			append(" where ").
			append(table.primaryKey.protectedID).
			append('=').
			appendParameter(item.pk);
			
		// Additionally check correctness of type column
		// If type column is inconsistent, the database
		// will return no rows and the result set handler
		// will fail
		final StringColumn typeColumn = table.typeColumn;
		if(typeColumn!=null)
		{
			bf.append(" and ").
				append(typeColumn.protectedID).
				append('=').
				appendParameter(item.type.id);
		}
		
		// TODO introduce Database#supportsGetBytes()
		final LoadBlobResultSetHandler handler = new LoadBlobResultSetHandler(!"com.exedio.cope.OracleDatabase".equals(getClass().getName()));
		executeSQLQuery(connection, bf, handler, false);
		return handler.result;
	}
	
	private static class LoadBlobResultSetHandler implements ResultSetHandler
	{
		final boolean supportsGetBytes;
		
		LoadBlobResultSetHandler(final boolean supportsGetBytes)
		{
			this.supportsGetBytes = supportsGetBytes;
		}
		
		byte[] result;

		public void run(final ResultSet resultSet) throws SQLException
		{
			if(!resultSet.next())
				throw new RuntimeException();
			
			if(supportsGetBytes)
			{
				result = resultSet.getBytes(1);
			}
			else
			{
				final Blob blob = resultSet.getBlob(1);
				if(blob!=null)
				{
					result = new byte[(int)blob.length()];
					InputStream stream = null;
					try
					{
						stream = blob.getBinaryStream();
						stream.read(result);
					}
					catch(IOException e)
					{
						throw new RuntimeException(e);
					}
					finally
					{
						if(stream!=null)
						{
							try
							{
								stream.close();
							}
							catch(IOException e)
							{
								throw new RuntimeException(e);
							}
						}
					}
				}
				else
				{
					result = null;
				}
			}
		}
	}
	
	public final void load(final Connection connection, final BlobColumn column, final Item item, final OutputStream data)
	{
		buildStage = false;

		final Table table = column.table;
		final Statement bf = createStatement();
		bf.append("select ").
			append(column.protectedID).defineColumn(column).
			append(" from ").
			append(table.protectedID).
			append(" where ").
			append(table.primaryKey.protectedID).
			append('=').
			appendParameter(item.pk);
			
		// Additionally check correctness of type column
		// If type column is inconsistent, the database
		// will return no rows and the result set handler
		// will fail
		final StringColumn typeColumn = table.typeColumn;
		if(typeColumn!=null)
		{
			bf.append(" and ").
				append(typeColumn.protectedID).
				append('=').
				appendParameter(item.type.id);
		}
		
		executeSQLQuery(connection, bf, new ResultSetHandler(){
			
			public void run(final ResultSet resultSet) throws SQLException
			{
				if(!resultSet.next())
					throw new RuntimeException();
				final Blob blob = resultSet.getBlob(1);
				if(blob!=null)
				{
					try
					{
						final InputStream source = blob.getBinaryStream();
						final byte[] b = new byte[Math.min(50*1024, (int)blob.length())];
						for(int len = source.read(b); len>=0; len = source.read(b))
							data.write(b, 0, len);
					}
					catch(IOException e)
					{
						throw new RuntimeException(e);
					}
				}
			}
			
		}, false);
	}
	
	public final long loadLength(final Connection connection, final BlobColumn column, final Item item)
	{
		buildStage = false;

		// TODO should check whether there is some kind of "length" function for binary 
		final Table table = column.table;
		final Statement bf = createStatement();
		bf.append("select ").
			append(column.protectedID).defineColumn(column).
			append(" from ").
			append(table.protectedID).
			append(" where ").
			append(table.primaryKey.protectedID).
			append('=').
			appendParameter(item.pk);
			
		// Additionally check correctness of type column
		// If type column is inconsistent, the database
		// will return no rows and the result set handler
		// will fail
		final StringColumn typeColumn = table.typeColumn;
		if(typeColumn!=null)
		{
			bf.append(" and ").
				append(typeColumn.protectedID).
				append('=').
				appendParameter(item.type.id);
		}
		
		final LoadBlobLengthResultSetHandler handler = new LoadBlobLengthResultSetHandler();
		executeSQLQuery(connection, bf, handler, false);
		return handler.result;
	}
	
	private static class LoadBlobLengthResultSetHandler implements ResultSetHandler
	{
		long result;

		public void run(final ResultSet resultSet) throws SQLException
		{
			if(!resultSet.next())
				throw new RuntimeException();
			final Blob blob = resultSet.getBlob(1);
			result = (blob!=null) ? blob.length() : -1;
		}
	}
	
	public final void store(final Connection connection, final BlobColumn column, final Item item, final InputStream data) throws IOException
	{
		buildStage = false;

		final Table table = column.table;
		final Statement bf = createStatement();
		bf.append("update ").
			append(table.protectedID).
			append(" set ").
			append(column.protectedID).
			append('=');
		
		if(data!=null)
			bf.appendParameterBlob(column, data);
		else
			bf.append("NULL");
		
		bf.append(" where ").
			append(table.primaryKey.protectedID).
			append('=').
			appendParameter(item.pk);
		
		// Additionally check correctness of type column
		// If type column is inconsistent, the database
		// will return "0 rows affected" and executeSQLUpdate
		// will fail
		final StringColumn typeColumn = table.typeColumn;
		if(typeColumn!=null)
		{
			bf.append(" and ").
				append(typeColumn.protectedID).
				append('=').
				appendParameter(item.type.id);
		}

		//System.out.println("storing "+bf.toString());
		try
		{
			executeSQLUpdate(connection, bf, 1, null);
		}
		catch(UniqueViolationException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	static interface ResultSetHandler
	{
		public void run(ResultSet resultSet) throws SQLException;
	}

	private final static int convertSQLResult(final Object sqlInteger)
	{
		// IMPLEMENTATION NOTE
		// Whether the returned object is an Integer, a Long or a BigDecimal,
		// depends on the database used and for oracle on whether
		// OracleStatement.defineColumnType is used or not, so we support all
		// here.
		return ((Number)sqlInteger).intValue();
	}

	//private static int timeExecuteQuery = 0;

	protected final StatementInfo executeSQLQuery(
		final Connection connection,
		final Statement statement,
		final ResultSetHandler resultSetHandler,
		final boolean makeStatementInfo)
	{
		java.sql.Statement sqlStatement = null;
		ResultSet resultSet = null;
		try
		{
			final String sqlText = statement.getText();
			final long logStart = (log||makeStatementInfo) ? System.currentTimeMillis() : 0;
			final long logPrepared;
			final long logExecuted;
			
			if(!prepare)
			{
				sqlStatement = connection.createStatement();

				defineColumnTypes(statement.columnTypes, sqlStatement);
				
				logPrepared = (log||makeStatementInfo) ? System.currentTimeMillis() : 0;
				resultSet = sqlStatement.executeQuery(sqlText);
				logExecuted = (log||makeStatementInfo) ? System.currentTimeMillis() : 0;
				resultSetHandler.run(resultSet);
			}
			else
			{
				final PreparedStatement prepared = connection.prepareStatement(sqlText);
				sqlStatement = prepared;
				int parameterIndex = 1;
				for(Iterator i = statement.parameters.iterator(); i.hasNext(); parameterIndex++)
					setObject(sqlText, prepared, parameterIndex, i.next());

				defineColumnTypes(statement.columnTypes, sqlStatement);
				
				logPrepared = (log||makeStatementInfo) ? System.currentTimeMillis() : 0;
				resultSet = prepared.executeQuery();
				logExecuted = (log||makeStatementInfo) ? System.currentTimeMillis() : 0;
				resultSetHandler.run(resultSet);
			}
			final long logResultRead = (log||makeStatementInfo) ? System.currentTimeMillis() : 0;
			
			if(resultSet!=null)
			{
				resultSet.close();
				resultSet = null;
			}
			if(sqlStatement!=null)
			{
				sqlStatement.close();
				sqlStatement = null;
			}

			final long logEnd = (log||makeStatementInfo) ? System.currentTimeMillis() : 0;
			
			if(log)
				log(logStart, logEnd, sqlText);
			
			if(makeStatementInfo)
				return makeStatementInfo(statement, connection, logStart, logPrepared, logExecuted, logResultRead, logEnd);
			else
				return null;
		}
		catch(SQLException e)
		{
			throw new SQLRuntimeException(e, statement.toString());
		}
		finally
		{
			if(resultSet!=null)
			{
				try
				{
					resultSet.close();
				}
				catch(SQLException e)
				{
					// exception is already thrown
				}
			}
			if(sqlStatement!=null)
			{
				try
				{
					sqlStatement.close();
				}
				catch(SQLException e)
				{
					// exception is already thrown
				}
			}
		}
	}
	
	private final void executeSQLUpdate(final Connection connection, final Statement statement, final int expectedRows)
			throws UniqueViolationException
	{
		executeSQLUpdate(connection, statement, expectedRows, null);
	}

	private final void executeSQLUpdate(
			final Connection connection,
			final Statement statement, final int expectedRows,
			final UniqueConstraint onlyThreatenedUniqueConstraint)
		throws UniqueViolationException
	{
		java.sql.Statement sqlStatement = null;
		try
		{
			final String sqlText = statement.getText();
			final long logStart = log ? System.currentTimeMillis() : 0;
			final int rows;
			if(!prepare)
			{
				sqlStatement = connection.createStatement();
				rows = sqlStatement.executeUpdate(sqlText);
			}
			else
			{
				final PreparedStatement prepared = connection.prepareStatement(sqlText);
				sqlStatement = prepared;
				int parameterIndex = 1;
				for(Iterator i = statement.parameters.iterator(); i.hasNext(); parameterIndex++)
					setObject(sqlText, prepared, parameterIndex, i.next());
				rows = prepared.executeUpdate();
			}
			
			final long logEnd = log ? System.currentTimeMillis() : 0;

			if(log)
				log(logStart, logEnd, sqlText);

			//System.out.println("("+rows+"): "+statement.getText());
			if(rows!=expectedRows)
				throw new RuntimeException("expected "+expectedRows+" rows, but got "+rows+" on statement "+sqlText);
		}
		catch(SQLException e)
		{
			final UniqueViolationException wrappedException = wrapException(e, onlyThreatenedUniqueConstraint);
			if(wrappedException!=null)
				throw wrappedException;
			else
				throw new SQLRuntimeException(e, statement.toString());
		}
		finally
		{
			if(sqlStatement!=null)
			{
				try
				{
					sqlStatement.close();
				}
				catch(SQLException e)
				{
					// exception is already thrown
				}
			}
		}
	}
	
	private static final void setObject(String s, final PreparedStatement statement, final int parameterIndex, final Object value)
		throws SQLException
	{
		//try{
		statement.setObject(parameterIndex, value);
		//}catch(SQLException e){ throw new SQLRuntimeException(e, "setObject("+parameterIndex+","+value+")"+s); }
	}
	
	protected static final String EXPLAIN_PLAN = "explain plan";
	
	protected StatementInfo makeStatementInfo(
			final Statement statement, final Connection connection,
			final long start, final long prepared, final long executed, final long resultRead, final long end)
	{
		final StatementInfo result = new StatementInfo(statement.getText());
		result.addChild(new StatementInfo("timing "+(end-start)+'/'+(prepared-start)+'/'+(executed-prepared)+'/'+(resultRead-executed)+'/'+(end-resultRead)+" (total/prepare/execute/readResult/close in ms)"));
		return result;
	}
	
	protected abstract String extractUniqueConstraintName(SQLException e);
	
	protected final static String ANY_CONSTRAINT = "--ANY--";

	private final UniqueViolationException wrapException(
			final SQLException e,
			final UniqueConstraint onlyThreatenedUniqueConstraint)
	{
		final String uniqueConstraintID = extractUniqueConstraintName(e);
		if(uniqueConstraintID!=null)
		{
			final UniqueConstraint constraint;
			if(ANY_CONSTRAINT.equals(uniqueConstraintID))
				constraint = onlyThreatenedUniqueConstraint;
			else
			{
				constraint = (UniqueConstraint)uniqueConstraintsByID.get(uniqueConstraintID);
				if(constraint==null)
					throw new SQLRuntimeException(e, "no unique constraint found for >"+uniqueConstraintID
																			+"<, has only "+uniqueConstraintsByID.keySet());
			}
			return new UniqueViolationException(e, null, constraint);
		}
		return null;
	}
	
	/**
	 * Trims a name to length for being a suitable qualifier for database entities,
	 * such as tables, columns, indexes, constraints, partitions etc.
	 */
	protected static final String trimString(final String longString, final int maxLength)
	{
		if(maxLength<=0)
			throw new RuntimeException("maxLength must be greater zero");
		if(longString.length()==0)
			throw new RuntimeException("longString must not be empty");

		if(longString.length()<=maxLength)
			return longString;

		int longStringLength = longString.length();
		final int[] trimPotential = new int[maxLength];
		final ArrayList words = new ArrayList();
		{
			final StringBuffer buf = new StringBuffer();
			for(int i=0; i<longString.length(); i++)
			{
				final char c = longString.charAt(i);
				if((c=='_' || Character.isUpperCase(c) || Character.isDigit(c)) && buf.length()>0)
				{
					words.add(buf.toString());
					int potential = 1;
					for(int j = buf.length()-1; j>=0; j--, potential++)
						trimPotential[j] += potential; 
					buf.setLength(0);
				}
				if(buf.length()<maxLength)
					buf.append(c);
				else
					longStringLength--;
			}
			if(buf.length()>0)
			{
				words.add(buf.toString());
				int potential = 1;
				for(int j = buf.length()-1; j>=0; j--, potential++)
					trimPotential[j] += potential; 
				buf.setLength(0);
			}
		}
		
		final int expectedTrimPotential = longStringLength - maxLength;
		//System.out.println("expected trim potential = "+expectedTrimPotential);

		int wordLength;
		int remainder = 0;
		for(wordLength = trimPotential.length-1; wordLength>=0; wordLength--)
		{
			//System.out.println("trim potential ["+wordLength+"] = "+trimPotential[wordLength]);
			remainder = trimPotential[wordLength] - expectedTrimPotential;
			if(remainder>=0)
				break;
		}
		
		final StringBuffer result = new StringBuffer(longStringLength);
		for(Iterator i = words.iterator(); i.hasNext(); )
		{
			final String word = (String)i.next();
			//System.out.println("word "+word+" remainder:"+remainder);
			if((word.length()>wordLength) && remainder>0)
			{
				result.append(word.substring(0, wordLength+1));
				remainder--;
			}
			else if(word.length()>wordLength)
				result.append(word.substring(0, wordLength));
			else
				result.append(word);
		}
		//System.out.println("---- trimName("+longString+","+maxLength+") == "+result+"     --- "+words);

		if(result.length()!=maxLength)
			throw new RuntimeException(result.toString()+maxLength);

		return result.toString();
	}
	
	public String makeName(final String longName)
	{
		return makeName(null, longName);
	}

	public String makeName(final String prefix, final String longName)
	{
		final String query = prefix==null ? longName : prefix+'.'+longName;
		final String forcedName = (String)forcedNames.get(query);
		//System.out.println("---------"+query+"--"+forcedName);
		if(forcedName!=null)
			return forcedName;
		
		return trimString(longName, 25);
	}

	public boolean supportsCheckConstraints()
	{
		return true;
	}

	public boolean supportsEmptyStrings()
	{
		return true;
	}

	public boolean supportsRightOuterJoins()
	{
		return true;
	}

	public abstract String getIntegerType(int precision);
	public abstract String getDoubleType(int precision);
	public abstract String getStringType(int maxLength);
	public abstract String getDayType();
	abstract int getLimitSupport();
	
	protected static final int LIMIT_SUPPORT_NONE = 26;
	protected static final int LIMIT_SUPPORT_CLAUSE_AFTER_SELECT = 63;
	protected static final int LIMIT_SUPPORT_CLAUSE_AFTER_WHERE = 93;
	protected static final int LIMIT_SUPPORT_CLAUSES_AROUND = 134;

	/**
	 * Appends a clause to the statement causing the database limiting the query result.
	 * This method is never called for <code>start==0 && count=={@link Query#UNLIMITED_COUNT}</code>.
	 * NOTE: Don't forget the space before the keyword 'limit'!
	 * @param start the number of rows to be skipped
	 *        or zero, if no rows to be skipped.
	 *        Is never negative.
	 * @param count the number of rows to be returned
	 *        or {@link Query#UNLIMITED_COUNT} if all rows to be returned.
	 *        Is always positive (greater zero).
	 */
	abstract void appendLimitClause(Statement bf, int start, int count);
	
	/**
	 * Same as {@link #appendLimitClause(Statement, int, int}.
	 * Is used for {@link #LIMIT_SUPPORT_CLAUSES_AROUND} only,
	 * for the postfix.
	 */
	abstract void appendLimitClause2(Statement bf, int start, int count);

	abstract void appendMatchClauseFullTextIndex(Statement bf, StringFunction function, String value);
	
	/**
	 * Search full text.
	 */
	public final void appendMatchClause(final Statement bf, final StringFunction function, final String value)
	{
		if(fulltextIndex)
			appendMatchClauseFullTextIndex(bf, function, value);
		else
			appendMatchClauseByLike(bf, function, value);
	}
	
	protected final void appendMatchClauseByLike(final Statement bf, final StringFunction function, final String value)
	{
		bf.append(function, (Join)null).
			append(" like ").
			appendParameter(function, '%'+value+'%');
	}
	
	private int countTable(final Connection connection, final Table table)
	{
		final Statement bf = createStatement();
		bf.append("select count(*) from ").defineColumnInteger().
			append(table.protectedID);

		final CountResultSetHandler handler = new CountResultSetHandler();
		executeSQLQuery(connection, bf, handler, false);
		return handler.result;
	}
	
	private static class CountResultSetHandler implements ResultSetHandler
	{
		int result;

		public void run(final ResultSet resultSet) throws SQLException
		{
			if(!resultSet.next())
				throw new RuntimeException();

			result = convertSQLResult(resultSet.getObject(1));
		}
	}


	public final PkSource makePkSource(final Table table)
	{
		return butterflyPkSource ? (PkSource)new ButterflyPkSource(table) : new SequentialPkSource(table);
	}
	
	public final int[] getMinMaxPK(final Connection connection, final Table table)
	{
		buildStage = false;

		final Statement bf = createStatement();
		final String primaryKeyProtectedID = table.primaryKey.protectedID;
		bf.append("select min(").
			append(primaryKeyProtectedID).defineColumnInteger().
			append("),max(").
			append(primaryKeyProtectedID).defineColumnInteger().
			append(") from ").
			append(table.protectedID);
			
		final NextPKResultSetHandler handler = new NextPKResultSetHandler();
		executeSQLQuery(connection, bf, handler, false);
		return handler.result;
	}
	
	private static class NextPKResultSetHandler implements ResultSetHandler
	{
		int[] result;

		public void run(final ResultSet resultSet) throws SQLException
		{
			if(!resultSet.next())
				throw new RuntimeException();
			final Object oLo = resultSet.getObject(1);
			if(oLo!=null)
			{
				result = new int[2];
				result[0] = convertSQLResult(oLo);
				final Object oHi = resultSet.getObject(2);
				result[1] = convertSQLResult(oHi);
			}
		}
	}
	
	public final Schema makeSchema()
	{
		final Schema result = new Schema(driver, connectionPool);
		for(Iterator i = tables.iterator(); i.hasNext(); )
			((Table)i.next()).makeSchema(result);
		completeSchema(result);
		return result;
	}
	
	protected void completeSchema(final Schema schema)
	{
	}
	
	public final Schema makeVerifiedSchema()
	{
		final Schema result = makeSchema();
		result.verify();
		return result;
	}

	
	
	/**
	 * @deprecated for debugging only, should never be used in committed code
	 */
	protected static final void printMeta(final ResultSet resultSet) throws SQLException
	{
		final ResultSetMetaData metaData = resultSet.getMetaData();;
		final int columnCount = metaData.getColumnCount();
		for(int i = 1; i<=columnCount; i++)
			System.out.println("------"+i+":"+metaData.getColumnName(i)+":"+metaData.getColumnType(i));
	}
	
	/**
	 * @deprecated for debugging only, should never be used in committed code
	 */
	protected static final void printRow(final ResultSet resultSet) throws SQLException
	{
		final ResultSetMetaData metaData = resultSet.getMetaData();;
		final int columnCount = metaData.getColumnCount();
		for(int i = 1; i<=columnCount; i++)
			System.out.println("----------"+i+":"+resultSet.getObject(i));
	}
	
	/**
	 * @deprecated for debugging only, should never be used in committed code
	 */
	static final ResultSetHandler logHandler = new ResultSetHandler()
	{
		public void run(final ResultSet resultSet) throws SQLException
		{
			final int columnCount = resultSet.getMetaData().getColumnCount();
			System.out.println("columnCount:"+columnCount);
			final ResultSetMetaData meta = resultSet.getMetaData();
			for(int i = 1; i<=columnCount; i++)
			{
				System.out.println(meta.getColumnName(i)+"|");
			}
			while(resultSet.next())
			{
				for(int i = 1; i<=columnCount; i++)
				{
					System.out.println(resultSet.getObject(i)+"|");
				}
			}
		}
	};
	
	public boolean isDefiningColumnTypes()
	{
		return false;
	}
	
	public void defineColumnTypes(IntList columnTypes, java.sql.Statement statement)
			throws SQLException
	{
		// default implementation does nothing, may be overwritten by subclasses
	}
}
