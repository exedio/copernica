package com.exedio.cope.lib;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

final class StringColumn extends Column
{
	static final Integer JDBC_TYPE = new Integer(Types.VARCHAR);

	StringColumn(final Type type, final String trimmedName, final boolean notNull)
	{
		// TODO: support min/max length
		super(type, trimmedName, notNull, "varchar2(2000)"/* TODO: this is database specific */, JDBC_TYPE);
	}
	
	void load(final ResultSet resultSet, final int columnIndex, final Row row)
			throws SQLException
	{
		final String loadedString = resultSet.getString(columnIndex);
		//System.out.println("StringColumn.load:"+loadedString);
		if(loadedString!=null)
			row.load(this, loadedString);
	}

	Object cacheToDatabase(final Object cache)
	{
		if(cache==null)
			return "NULL";
		else
			return "'" + ((String)cache) + '\'';
	}

}


