
package com.exedio.cope.lib;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import bak.pcj.list.IntArrayList;

public abstract class Database
{
	public static final Database theInstance = createInstance();
	
	private static final Database createInstance()
	{
		final String databaseName = Properties.getInstance().getDatabase();

		final Class databaseClass;
		try
		{
			databaseClass = Class.forName(databaseName);
		}
		catch(ClassNotFoundException e)
		{
			final String m = "ERROR: class "+databaseName+" from cope.properties not found.";
			System.err.println(m);
			throw new RuntimeException(m);
		}
		
		if(!Database.class.isAssignableFrom(databaseClass))
		{
			final String m = "ERROR: class "+databaseName+" from cope.properties not a subclass of "+Database.class.getName()+".";
			System.err.println(m);
			throw new RuntimeException(m);
		}

		final Constructor constructor;
		try
		{
			constructor = databaseClass.getDeclaredConstructor(new Class[]{});
		}
		catch(NoSuchMethodException e)
		{
			final String m = "ERROR: class "+databaseName+" from cope.properties has no default constructor.";
			System.err.println(m);
			throw new RuntimeException(m);
		}

		try
		{
			return (Database)constructor.newInstance(new Object[]{});
		}
		catch(InstantiationException e)
		{
			e.printStackTrace();
			System.err.println(e.getMessage());
			throw new SystemException(e);
		}
		catch(IllegalAccessException e)
		{
			e.printStackTrace();
			System.err.println(e.getMessage());
			throw new SystemException(e);
		}
		catch(InvocationTargetException e)
		{
			e.printStackTrace();
			System.err.println(e.getMessage());
			throw new SystemException(e);
		}
	}
	
	private final boolean useDefineColumnTypes;
	
	protected Database()
	{
		this.useDefineColumnTypes = this instanceof DatabaseColumnTypesDefinable;
		//System.out.println("using database "+getClass());
	}
	
	private final Statement createStatement()
	{
		return new Statement(useDefineColumnTypes);
	}
	
	//private static int createTableTime = 0, dropTableTime = 0, checkEmptyTableTime = 0;
	
	public void createDatabase()
	{
		//final long time = System.currentTimeMillis();
		for(Iterator i = Type.getTypes().iterator(); i.hasNext(); )
		{
			final Type type = (Type)i.next();
			createTable(type);
			createMediaDirectories(type);
		}
		for(Iterator i = Type.getTypes().iterator(); i.hasNext(); )
			createForeignKeyConstraints((Type)i.next());
		//final long amount = (System.currentTimeMillis()-time);
		//createTableTime += amount;
		//System.out.println("CREATE TABLES "+amount+"ms  accumulated "+createTableTime);
	}

	//private static int checkTableTime = 0;

	/**
	 * Checks the database,
	 * whether the database tables representing the types do exist.
	 * Issues a single database statement,
	 * that touches all tables and columns,
	 * that would have been created by
	 * {@link #createDatabase()}.
	 * @throws SystemException
	 * 	if something is wrong with the database.
	 * 	TODO: use a more specific exception.
	 */
	public void checkDatabase()
	{
		//final long time = System.currentTimeMillis();
		final Statement bf = createStatement();
		bf.append("select count(*) from ").defineColumnInteger();
		boolean first = true;

		for(Iterator i = Type.getTypes().iterator(); i.hasNext(); )
		{
			if(first)
				first = false;
			else
				bf.append(',');

			final Type type = (Type)i.next();
			bf.append(type.protectedID);
		}
		
		final Long testDate = new Long(System.currentTimeMillis());
		
		bf.append(" where ");
		first = true;
		for(Iterator i = Type.getTypes().iterator(); i.hasNext(); )
		{
			if(first)
				first = false;
			else
				bf.append(" and ");

			final Type type = (Type)i.next();

			final Column primaryKey = type.primaryKey;
			bf.append(type.protectedID).
				append('.').
				append(primaryKey.protectedID).
				append('=').
				append(Type.NOT_A_PK);
			
			for(Iterator j = type.getColumns().iterator(); j.hasNext(); )
			{
				final Column column = (Column)j.next();
				bf.append(" and ").
					append(type.protectedID).
					append('.').
					append(column.protectedID).
					append('=');

				if(column instanceof IntegerColumn)
					bf.appendValue(column, ((IntegerColumn)column).longInsteadOfInt ? (Number)new Long(1) : new Integer(1));
				else if(column instanceof DoubleColumn)
					bf.appendValue(column, new Double(2.2));
				else if(column instanceof StringColumn)
					bf.appendValue(column, "z");
				else if(column instanceof TimestampColumn)
					bf.appendValue(column, testDate);
				else
					throw new RuntimeException(column.toString());
			}
		}
		
		try
		{
			//System.out.println("checkDatabase:"+bf.toString());
			executeSQL(bf, EMPTY_RESULT_SET_HANDLER);
		}
		catch(ConstraintViolationException e)
		{
			throw new SystemException(e);
		}

		//final long amount = (System.currentTimeMillis()-time);
		//checkTableTime += amount;
		//System.out.println("CHECK TABLES "+amount+"ms  accumulated "+checkTableTime);
	}

	public void dropDatabase()
	{
		//final long time = System.currentTimeMillis();
		final List types = Type.getTypes();
		// must delete in reverse order, to obey integrity constraints
		for(ListIterator i = types.listIterator(types.size()); i.hasPrevious(); )
			dropForeignKeyConstraints((Type)i.previous());
		for(ListIterator i = types.listIterator(types.size()); i.hasPrevious(); )
			dropTable((Type)i.previous());
		//final long amount = (System.currentTimeMillis()-time);
		//dropTableTime += amount;
		//System.out.println("DROP TABLES "+amount+"ms  accumulated "+dropTableTime);
	}
	
	public void tearDownDatabase()
	{
		System.err.println("TEAR DOWN ALL DATABASE");
		for(Iterator i = Type.getTypes().iterator(); i.hasNext(); )
		{
			try
			{
				final Type type = (Type)i.next();
				System.err.print("DROPPING FOREIGN KEY CONSTRAINTS "+type+"... ");
				dropForeignKeyConstraints(type);
				System.err.println("done.");
			}
			catch(SystemException e2)
			{
				System.err.println("failed:"+e2.getMessage());
			}
		}
		for(Iterator i = Type.getTypes().iterator(); i.hasNext(); )
		{
			try
			{
				final Type type = (Type)i.next();
				System.err.print("DROPPING TABLE "+type+" ... ");
				dropTable(type);
				System.err.println("done.");
			}
			catch(SystemException e2)
			{
				System.err.println("failed:"+e2.getMessage());
			}
		}
	}

	public void checkEmptyTables()
	{
		//final long time = System.currentTimeMillis();
		for(Iterator i = Type.getTypes().iterator(); i.hasNext(); )
		{
			final Type type = (Type)i.next();
			final int count = countTable(type);
			if(count>0)
				throw new RuntimeException("there are "+count+" items left for type "+type); 
		}
		//final long amount = (System.currentTimeMillis()-time);
		//checkEmptyTableTime += amount;
		//System.out.println("CHECK EMPTY TABLES "+amount+"ms  accumulated "+checkEmptyTableTime);
	}
	
	final IntArrayList search(final Query query)
	{
		final Type selectType = query.selectType;
		final Statement bf = createStatement();

		bf.append("select ").
			append(selectType.protectedID).
			append('.').
			append(selectType.primaryKey.protectedID).defineColumnInteger().
			append(" from ");

		boolean first = true;
		for(Iterator i = query.fromTypes.iterator(); i.hasNext(); )
		{
			if(first)
				first = false;
			else
				bf.append(',');

			bf.append(((Type)i.next()).protectedID);
		}

		if(query.condition!=null)
		{
			bf.append(" where ");
			query.condition.appendStatement(bf);
		}
		
		if(query.orderBy!=null)
		{
			bf.append(" order by ").
				append(query.orderBy);
			if(!query.orderAscending)
				bf.append(" desc");
		}
		
		//System.out.println("searching "+bf.toString());
		try
		{
			final QueryResultSetHandler handler = new QueryResultSetHandler(query.start, query.count);
			executeSQL(bf, handler);
			return handler.result;
		}
		catch(ConstraintViolationException e)
		{
			throw new SystemException(e);
		}
	}

	void load(final Row row)
	{
		final Statement bf = createStatement();
		bf.append("select ");

		boolean first = true;
		for(Type type = row.type; type!=null; type = type.getSupertype())
		{
			final List columns = type.getColumns();
			for(Iterator i = columns.iterator(); i.hasNext(); )
			{
				if(first)
					first = false;
				else
					bf.append(',');

				final Column column = (Column)i.next();
				bf.append(type.protectedID).
					append('.').
					append(column.protectedID).defineColumn(column);
			}
		}

		bf.append(" from ");
		first = true;
		for(Type type = row.type; type!=null; type = type.getSupertype())
		{
			if(first)
				first = false;
			else
				bf.append(',');

			bf.append(type.protectedID);
		}
			
		bf.append(" where ");
		first = true;
		for(Type type = row.type; type!=null; type = type.getSupertype())
		{
			if(first)
				first = false;
			else
				bf.append(" and ");

			bf.append(type.protectedID).
				append('.').
				append(type.primaryKey.protectedID).
				append('=').
				append(row.pk);
		}

		//System.out.println("loading "+bf.toString());
		try
		{
			executeSQL(bf, new LoadResultSetHandler(row));
		}
		catch(ConstraintViolationException e)
		{
			throw new SystemException(e);
		}
	}

	void store(final Row row)
			throws UniqueViolationException
	{
		store(row, row.type);
	}

	private void store(final Row row, final Type type)
			throws UniqueViolationException
	{
		final Type supertype = type.getSupertype();
		if(supertype!=null)
			store(row, supertype);

		final List columns = type.getColumns();

		final Statement bf = createStatement();
		if(row.present)
		{
			bf.append("update ").
				append(type.protectedID).
				append(" set ");

			boolean first = true;
			for(Iterator i = columns.iterator(); i.hasNext(); )
			{
				if(first)
					first = false;
				else
					bf.append(',');

				final Column column = (Column)i.next();
				bf.append(column.protectedID).
					append('=');

				final Object value = row.store(column);
				bf.append(column.cacheToDatabase(value));
			}
			bf.append(" where ").
				append(type.primaryKey.protectedID).
				append('=').
				append(row.pk);
		}
		else
		{
			bf.append("insert into ").
				append(type.protectedID).
				append("(").
				append(type.primaryKey.protectedID);

			boolean first = true;
			for(Iterator i = columns.iterator(); i.hasNext(); )
			{
				bf.append(',');
				final Column column = (Column)i.next();
				bf.append(column.protectedID);
			}

			bf.append(")values(").
				append(row.pk);
			for(Iterator i = columns.iterator(); i.hasNext(); )
			{
				bf.append(',');
				final Column column = (Column)i.next();
				final Object value = row.store(column);
				bf.append(column.cacheToDatabase(value));
			}
			bf.append(')');
		}

		try
		{
			//System.out.println("storing "+bf.toString());
			executeSQL(bf, EMPTY_RESULT_SET_HANDLER);
		}
		catch(UniqueViolationException e)
		{
			throw e;
		}
		catch(ConstraintViolationException e)
		{
			throw new SystemException(e);
		}
	}

	void delete(final Type type, final int pk)
			throws IntegrityViolationException
	{
		for(Type currentType = type; currentType!=null; currentType = currentType.getSupertype())
		{
			final Statement bf = createStatement();
			bf.append("delete from ").
				append(currentType.protectedID).
				append(" where ").
				append(currentType.primaryKey.protectedID).
				append('=').
				append(pk);

			//System.out.println("deleting "+bf.toString());

			try
			{
				executeSQL(bf, EMPTY_RESULT_SET_HANDLER);
			}
			catch(IntegrityViolationException e)
			{
				throw e;
			}
			catch(ConstraintViolationException e)
			{
				throw new SystemException(e);
			}
		}
	}

	private static interface ResultSetHandler
	{
		public void run(ResultSet resultSet) throws SQLException;
	}

	private static final ResultSetHandler EMPTY_RESULT_SET_HANDLER = new ResultSetHandler()
	{
		public void run(ResultSet resultSet)
		{
		}
	};
		
	private static class QueryResultSetHandler implements ResultSetHandler
	{
		private final int start;
		private final int count;
		private final IntArrayList result = new IntArrayList();
		
		QueryResultSetHandler(final int start, final int count)
		{
			this.start = start;
			this.count = count;
			if(start<0)
				throw new RuntimeException();
		}

		public void run(ResultSet resultSet) throws SQLException
		{
			if(start>0)
			{
				// TODO: ResultSet.relative
				// Would like to use
				//    resultSet.relative(start+1);
				// but this throws a java.sql.SQLException:
				// Invalid operation for forward only resultset : relative
				for(int i = start; i>0; i--)
					resultSet.next();
			}
				
			int i = (count>=0 ? count : Integer.MAX_VALUE);

			while(resultSet.next() && (--i)>=0)
			{
				final int pk = resultSet.getInt(1);
				//System.out.println("pk:"+pk);
				result.add(pk);
			}
		}
	}
	
	private static class LoadResultSetHandler implements ResultSetHandler
	{
		private final Row row;

		LoadResultSetHandler(final Row row)
		{
			this.row = row;
		}

		public void run(ResultSet resultSet) throws SQLException
		{
			if(!resultSet.next())
				throw new RuntimeException("no such pk"); // TODO use some better exception
			int columnIndex = 1;
			for(Type type = row.type; type!=null; type = type.getSupertype())
				for(Iterator i = type.getColumns().iterator(); i.hasNext(); )
					((Column)i.next()).load(resultSet, columnIndex++, row);
			return;
		}
	}
	
	private final static int convertSQLResult(final Object sqlInteger)
	{
		// IMPLEMENTATION NOTE for Oracle
		// Whether the returned object is an Integer or a BigDecimal,
		// depends on whether OracleStatement.defineColumnType is used or not,
		// so we support both here.
		if(sqlInteger instanceof BigDecimal)
			return ((BigDecimal)sqlInteger).intValue();
		else
			return ((Integer)sqlInteger).intValue();
	}

	private static class IntegerResultSetHandler implements ResultSetHandler
	{
		int result;

		public void run(ResultSet resultSet) throws SQLException
		{
			if(!resultSet.next())
				throw new RuntimeException();

			result = convertSQLResult(resultSet.getObject(1));
		}
	}

	private static class NextPKResultSetHandler implements ResultSetHandler
	{
		int resultLo;
		int resultHi;

		public void run(ResultSet resultSet) throws SQLException
		{
			if(!resultSet.next())
				throw new RuntimeException();
			final Object oLo = resultSet.getObject(1);
			if(oLo==null)
			{
				resultLo = -1;
				resultHi = 0;
			}
			else
			{
				resultLo = convertSQLResult(oLo)-1;
				final Object oHi = resultSet.getObject(2);
				resultHi = convertSQLResult(oHi)+1;
			}
		}
	}
	
	//private static int timeExecuteQuery = 0;

	private void executeSQL(final Statement statement, final ResultSetHandler resultSetHandler)
			throws ConstraintViolationException
	{
		final ConnectionPool connectionPool = ConnectionPool.getInstance();
		
		Connection connection = null;
		java.sql.Statement sqlStatement = null;
		ResultSet resultSet = null;
		try
		{
			connection = connectionPool.getConnection();
			// TODO: use prepared statements and reuse the statement.
			sqlStatement = connection.createStatement();
			final String sqlText = statement.getText();
			if(!sqlText.startsWith("select "))
			{
				final int rows = sqlStatement.executeUpdate(sqlText);
				//System.out.println("("+rows+"): "+statement.getText());
			}
			else
			{
				//long time = System.currentTimeMillis();
				if(useDefineColumnTypes)
					((DatabaseColumnTypesDefinable)this).defineColumnTypes(statement.columnTypes, sqlStatement);
				resultSet = sqlStatement.executeQuery(sqlText);
				//long interval = System.currentTimeMillis() - time;
				//timeExecuteQuery += interval;
				//System.out.println("executeQuery: "+interval+"ms sum "+timeExecuteQuery+"ms");
				resultSetHandler.run(resultSet);
			}
		}
		catch(SQLException e)
		{
			final ConstraintViolationException wrappedException = wrapException(e);
			if(wrappedException!=null)
				throw wrappedException;
			else
				throw new SystemException(e, statement.toString());
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
			if(connection!=null)
			{
				try
				{
					connectionPool.putConnection(connection);
				}
				catch(SQLException e)
				{
					// exception is already thrown
				}
			}
		}
	}
	
	protected abstract String extractUniqueConstraintName(SQLException e);
	protected abstract String extractIntegrityConstraintName(SQLException e);

	private final ConstraintViolationException wrapException(final SQLException e)
	{
		{		
			final String uniqueConstraintID = extractUniqueConstraintName(e);
			if(uniqueConstraintID!=null)
			{
				final UniqueConstraint constraint = UniqueConstraint.findByID(uniqueConstraintID, e);
				return new UniqueViolationException(e, null, constraint);
			}
		}
		{		
			final String integrityConstraintName = extractIntegrityConstraintName(e);
			if(integrityConstraintName!=null)
			{
				final ItemAttribute attribute = ItemAttribute.getItemAttributeByIntegrityConstraintName(integrityConstraintName, e);
				return new IntegrityViolationException(e, null, attribute);
			}
		}
		return null;
	}
	
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
	
	String trimName(final Type type)
	{
		final String className = type.getJavaClass().getName();
		final int pos = className.lastIndexOf('.');
		return trimString(className.substring(pos+1), 25);
	}
	
	/**
	 * Trims a name to length for being be a suitable qualifier for database entities,
	 * such as tables, columns, indexes, constraints, partitions etc.
	 */
	String trimName(final String longName)
	{
		return trimString(longName, 25);
	}

	/**
	 * Protects a database name from being interpreted as a SQL keyword.
	 * This is usually done by enclosing the name with some (database specific) delimiters.
	 * The default implementation uses double quotes as delimiter.
	 */
	protected String protectName(String name)
	{
		return '"' + name + '"';
	}

	abstract String getIntegerType(int precision);
	abstract String getDoubleType(int precision);
	abstract String getStringType(int maxLength);
	abstract String getDateTimestampType();
	
	private void createTable(final Type type)
	{
		final Statement bf = createStatement();
		bf.append("create table ").
			append(type.protectedID).
			append('(');

		final Column primaryKey = type.primaryKey;
		bf.append(primaryKey.protectedID).
			append(' ').
			append(primaryKey.databaseType).
			append(" primary key");
			
		for(Iterator i = type.getColumns().iterator(); i.hasNext(); )
		{
			final Column column = (Column)i.next();
			bf.append(',').
				append(column.protectedID).
				append(' ').
				append(column.databaseType);
			
			if(column.notNull)
				bf.append(" not null");
		}
		
		final Type supertype = type.getSupertype();
		if(supertype!=null)
		{
			bf.append(",constraint ").
				append(protectName(type.id+"SUP")).
				append(" foreign key(").
				append(type.primaryKey.protectedID).
				append(")references ").
				append(supertype.protectedID).
				append('(').
				append(supertype.primaryKey.protectedID).
				append(')');
		}

		// attribute constraints		
		for(Iterator i = type.getColumns().iterator(); i.hasNext(); )
		{
			final Column column = (Column)i.next();

			if(column instanceof StringColumn)
			{
				final StringColumn stringColumn = (StringColumn)column;
				if(stringColumn.minimumLengthID!=null)
				{
					bf.append(",constraint ").
						append(protectName(stringColumn.minimumLengthID)).
						append(" check(length(").
						append(column.protectedID).
						append(")>=").
						append(stringColumn.minimumLength);

					if(!column.notNull)
					{
						bf.append(" or ").
							append(column.protectedID).
							append(" is null");
					}
					bf.append(')');
				}
				if(stringColumn.maximumLengthID!=null)
				{
					bf.append(",constraint ").
						append(protectName(stringColumn.maximumLengthID)).
						append(" check(length(").
						append(column.protectedID).
						append(")<=").
						append(stringColumn.maximumLength);

					if(!column.notNull)
					{
						bf.append(" or ").
							append(column.protectedID).
							append(" is null");
					}
					bf.append(')');
				}
			}
			else if(column instanceof IntegerColumn)
			{
				final IntegerColumn intColumn = (IntegerColumn)column;
				final int[] allowedValues = intColumn.allowedValues;
				if(allowedValues!=null)
				{
					bf.append(",constraint ").
						append(protectName(intColumn.allowedValuesID)).
						append(" check(").
						append(column.protectedID).
						append(" in (");

					for(int j = 0; j<allowedValues.length; j++)
					{
						if(j>0)
							bf.append(',');
						bf.append(allowedValues[j]);
					}
					bf.append(')');

					if(!column.notNull)
					{
						bf.append(" or ").
							append(column.protectedID).
							append(" is null");
					}
					bf.append(')');
				}
			}
		}

		for(Iterator i = type.getUniqueConstraints().iterator(); i.hasNext(); )
		{
			final UniqueConstraint uniqueConstraint = (UniqueConstraint)i.next();
			bf.append(",constraint ").
				append(uniqueConstraint.getProtectedID()).
				append(" unique(");
			boolean first = true;
			for(Iterator j = uniqueConstraint.getUniqueAttributes().iterator(); j.hasNext(); )
			{
				if(first)
					first = false;
				else
					bf.append(',');
				final Attribute uniqueAttribute = (Attribute)j.next();
				bf.append(uniqueAttribute.getMainColumn().protectedID);
			}
			bf.append(')');
		}
		
		bf.append(')');

		try
		{
			//System.out.println("createTable:"+bf.toString());
			executeSQL(bf, EMPTY_RESULT_SET_HANDLER);
		}
		catch(ConstraintViolationException e)
		{
			throw new SystemException(e);
		}
	}
	
	private void createForeignKeyConstraints(final Type type)
	{
		//System.out.println("createForeignKeyConstraints:"+bf);

		for(Iterator i = type.getColumns().iterator(); i.hasNext(); )
		{
			final Column column = (Column)i.next();
			//System.out.println("createForeignKeyConstraints("+column+"):"+bf);
			if(column instanceof ItemColumn)
			{
				final ItemColumn itemColumn = (ItemColumn)column;
				final Statement bf = createStatement();
				bf.append("alter table ").
					append(type.protectedID).
					append(" add constraint ").
					append(Database.theInstance.protectName(itemColumn.integrityConstraintName)).
					append(" foreign key (").
					append(column.protectedID).
					append(") references ").
					append(itemColumn.getForeignTableNameProtected());

				//System.out.println("createForeignKeyConstraints:"+bf);
				try
				{
					executeSQL(bf, EMPTY_RESULT_SET_HANDLER);
				}
				catch(ConstraintViolationException e)
				{
					throw new SystemException(e);
				}
			}
		}
	}
	
	private void createMediaDirectories(final Type type)
	{
		File typeDirectory = null;

		for(Iterator i = type.getAttributes().iterator(); i.hasNext(); )
		{
			final Attribute attribute = (Attribute)i.next();
			if(attribute instanceof MediaAttribute)
			{
				if(typeDirectory==null)
				{
					final File directory = Properties.getInstance().getMediaDirectory();
					typeDirectory = new File(directory, type.id);
					typeDirectory.mkdir();
				}
				final File attributeDirectory = new File(typeDirectory, attribute.getName());
				attributeDirectory.mkdir();
			}
		}
	}

	private void dropTable(final Type type)
	{
		type.onDropTable();

		final Statement bf = createStatement();
		bf.append("drop table ").
			append(type.protectedID);

		try
		{
			executeSQL(bf, EMPTY_RESULT_SET_HANDLER);
		}
		catch(ConstraintViolationException e)
		{
			throw new SystemException(e);
		}
	}
	
	private int countTable(final Type type)
	{
		final Statement bf = createStatement();
		bf.append("select count(*) from ").defineColumnInteger().
			append(type.protectedID);

		try
		{
			final IntegerResultSetHandler handler = new IntegerResultSetHandler();
			executeSQL(bf, handler);
			return handler.result;
		}
		catch(ConstraintViolationException e)
		{
			throw new SystemException(e);
		}
	}
	
	private void dropForeignKeyConstraints(final Type type)
	{
		for(Iterator i = type.getColumns().iterator(); i.hasNext(); )
		{
			final Column column = (Column)i.next();
			//System.out.println("dropForeignKeyConstraints("+column+")");
			if(column instanceof ItemColumn)
			{
				final ItemColumn itemColumn = (ItemColumn)column;
				final Statement bf = createStatement();
				boolean hasOne = false;

				bf.append("alter table ").
					append(type.protectedID).
					append(" drop constraint ").
					append(Database.theInstance.protectName(itemColumn.integrityConstraintName));

				//System.out.println("dropForeignKeyConstraints:"+bf);
				try
				{
					executeSQL(bf, EMPTY_RESULT_SET_HANDLER);
				}
				catch(ConstraintViolationException e)
				{
					throw new SystemException(e);
				}
			}
		}
	}
	
	int[] getNextPK(final Type type)
	{
		final Statement bf = createStatement();
		final String primaryKeyProtectedID = type.primaryKey.protectedID;
		bf.append("select min(").
			append(primaryKeyProtectedID).defineColumnInteger().
			append("),max(").
			append(primaryKeyProtectedID).defineColumnInteger().
			append(") from ").
			append(type.protectedID);
			
		try
		{
			final NextPKResultSetHandler handler = new NextPKResultSetHandler();
			executeSQL(bf, handler);
			//System.err.println("select max("+type.primaryKey.trimmedName+") from "+type.trimmedName+" : "+handler.result);
			return new int[] {handler.resultLo, handler.resultHi};
		}
		catch(ConstraintViolationException e)
		{
			throw new SystemException(e);
		}
	}
	
}
