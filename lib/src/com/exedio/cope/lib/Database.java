
package com.exedio.cope.lib;

import com.exedio.cope.lib.Attribute;
import com.exedio.cope.lib.BooleanAttribute;
import com.exedio.cope.lib.EnumerationAttribute;
import com.exedio.cope.lib.EnumerationValue;
import com.exedio.cope.lib.IntegerAttribute;
import com.exedio.cope.lib.Item;
import com.exedio.cope.lib.ItemAttribute;
import com.exedio.cope.lib.MediaAttribute;
import com.exedio.cope.lib.StringAttribute;
import com.exedio.cope.lib.SystemException;
import com.exedio.cope.lib.Type;
import com.exedio.cope.lib.database.OracleDatabase;
import com.exedio.cope.lib.search.Condition;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class Database
{
	public static final Database theInstance = new OracleDatabase();
	
	protected Database()
	{
	}
	
	protected abstract char getNameDelimiterStart();
	protected abstract char getNameDelimiterEnd();
	
	private String getSyntheticPrimaryKeyQualifier()
	{
		return "\"PK\"";
	}
	
	private String getSyntheticPrimaryKeyType()
	{
		return "number(10,0)";
	}
	
	public void createTables()
	{
		for(Iterator i = Type.getTypes().iterator(); i.hasNext(); )
			createTable((Type)i.next());
	}

	public void dropTables()
	{
		for(Iterator i = Type.getTypes().iterator(); i.hasNext(); )
			dropTable((Type)i.next());
	}

	private void createTable(final Type type)
	{
		executeSQL(getCreateTableStatement(type));
	}
	
	private void dropTable(final Type type)
	{
		executeSQL(getDropTableStatement(type));
	}

	/**
	 * Converts a collection of primary keys to a collection of items of the given type.
	 * @param pks the collection of primary keys, is expected not to be modified
	 * @return an unmodifiable collection.
	 */
	private final Collection wrapPrimaryKeys(final Type type, final Collection pks)
	{
		// TODO: dont convert all items at once, but use some kind of wrapper collection
		final ArrayList result = new ArrayList(pks.size());
		for(Iterator i = pks.iterator(); i.hasNext(); )
		{
			final Integer pk = (Integer)i.next();
			System.out.println("pk:"+pk);
			final Item item = type.getActiveItem(pk.intValue());
			if(item!=null)
				result.add(item);
			else
			{
				// TODO create inactive item with the given pk, if there is no active item
				System.out.println("OOOOPS:"+type+" "+pk);
			}
		}
		return Collections.unmodifiableList(result);
	}
	
	Collection search(final Type type, final Condition condition)
	{
		final StringBuffer bf = new StringBuffer();
		bf.append("select ").
			append(getSyntheticPrimaryKeyQualifier()).
			append(" from ").
			append(type.getPersistentQualifier()).
			append(" where ");
		condition.appendSQL(this, bf);
		
		System.out.println("searching "+bf.toString());
		return wrapPrimaryKeys(type, executeSQLQuery(bf.toString()));
	}

	public String makeValue(final Object o)
	{
		if(o==null)
			return "NULL";
		else
		{
			if(o instanceof String)
				return "'" + o + '\'';
			else if(o instanceof Boolean)
				return ((Boolean)o).booleanValue() ? "1" : "0";
			else if(o instanceof Item)
				return Integer.toString(((Item)o).pk);
			else if(o instanceof EnumerationValue)
				return Integer.toString(((EnumerationValue)o).number);
			else
				return o.toString();
		}
	}

	void write(final Type type, final int pk, final HashMap itemCache, final boolean present)
	{
		final List attributes = type.getAttributes();

		// TODO: use prepared statements and reuse the statement.
		final StringBuffer bf = new StringBuffer();
		if(present)
		{
			bf.append("update ").
				append(type.getPersistentQualifier()).
				append(" set ");

			boolean first = true;
			for(Iterator i = attributes.iterator(); i.hasNext(); )
			{
				if(first)
					first = false;
				else
					bf.append(',');

				final Attribute attribute = (Attribute)i.next();
				bf.append(attribute.getPersistentQualifier()).
					append('=');

				final Object value = itemCache.get(attribute);
				bf.append(makeValue(value));
			}
			bf.append(" where ").
				append(getSyntheticPrimaryKeyQualifier()).
				append('=').
				append(pk);
		}
		else
		{
			bf.append("insert into ").
				append(type.getPersistentQualifier()).
				append("(").
				append(getSyntheticPrimaryKeyQualifier());

			boolean first = true;
			for(Iterator i = attributes.iterator(); i.hasNext(); )
			{
				bf.append(',');
				final Attribute attribute = (Attribute)i.next();
				bf.append(attribute.getPersistentQualifier());
			}

			bf.append(")values(").
				append(pk);
			for(Iterator i = attributes.iterator(); i.hasNext(); )
			{
				bf.append(',');
				final Attribute attribute = (Attribute)i.next();
				final Object value = itemCache.get(attribute);
				bf.append(makeValue(value));
			}
			bf.append(')');
		}

		System.out.println("writing "+bf.toString());

		executeSQL(bf.toString());
	}

	private void executeSQL(final String sql)
	{
		executeSQLInternal(sql, false);
	}
	
	private ArrayList executeSQLQuery(final String sql)
	{
		return executeSQLInternal(sql, true);
	}
		
	private ArrayList executeSQLInternal(final String sql, final boolean query)
	{
		final String driver = "oracle.jdbc.driver.OracleDriver";
		final String url = "jdbc:oracle:thin:@database3.exedio.com:1521:DB3";
		final String user = "wiebicke";
		final String password = "wiebicke1234";
		
		try
		{
			Class.forName(driver);
		}
		catch(ClassNotFoundException e)
		{
			throw new SystemException(e);
		}
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try
		{
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			if(query)
			{
				resultSet = statement.executeQuery(sql);
				// TODO: use special list for integers
				final ArrayList result = new ArrayList();
				while(resultSet.next())
				{
					final Integer pk = new Integer(resultSet.getInt(1));
					//System.out.println("pk:"+pk);
					result.add(pk);
				}
				return result;
			}
			else
			{
				statement.execute(sql);
				return null;
			}
		}
		catch(SQLException e)
		{
			throw new SystemException(e, sql);
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
			if(statement!=null)
			{
				try
				{
					statement.close();
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
					connection.close();
				}
				catch(SQLException e)
				{
					// exception is already thrown
				}
			}
		}
	}
	
	String makePersistentQualifier(final Type type)
	{
		final String className = type.getJavaClass().getName();
		final int pos = className.lastIndexOf('.');
		return getNameDelimiterStart() + className.substring(pos+1) + getNameDelimiterEnd();
	}
	
	String makePersistentQualifier(final Attribute attribute)
	{
		return getNameDelimiterStart() + attribute.getName() + getNameDelimiterEnd();
	}
	
	private String getPersistentType(final Attribute attribute)
	{
		if(attribute instanceof StringAttribute)
			return "varchar2(4000)";
		else if(attribute instanceof BooleanAttribute)
			return "number(1,0)";
		else if(attribute instanceof IntegerAttribute)
			return "number(20,0)";
		else if(attribute instanceof EnumerationAttribute)
			return "number(10,0)";
		else if(attribute instanceof ItemAttribute)
			return getSyntheticPrimaryKeyType();
		else if(attribute instanceof MediaAttribute)
			// TODO, separate major/minor mime type and introduce width/height for images
			return "varchar(30)";
		else
			throw new RuntimeException(attribute.toString());
	}
	
	private String getCreateTableStatement(final Type type)
	{
		final StringBuffer bf = new StringBuffer();
		bf.append("create table ").
			append(type.getPersistentQualifier()).
			append('(');

		bf.append(getSyntheticPrimaryKeyQualifier()).
			append(' ').
			append(getSyntheticPrimaryKeyType()).
			append(" primary key");
		
		for(Iterator i = type.getAttributes().iterator(); i.hasNext(); )
		{
			bf.append(',');
			final Attribute attribute = (Attribute)i.next();
			bf.append(attribute.getPersistentQualifier()).
				append(' ').
				append(getPersistentType(attribute));
		}
		bf.append(')');
		return bf.toString();
	}
	
	private String getDropTableStatement(final Type type)
	{
		final StringBuffer bf = new StringBuffer();
		bf.append("drop table ").
			append(type.getPersistentQualifier());
		return bf.toString();
	}
	
}
