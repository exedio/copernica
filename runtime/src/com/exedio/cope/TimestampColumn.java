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
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;

final class TimestampColumn extends Column
{
	static final int JDBC_TYPE = Types.TIMESTAMP;
	
	TimestampColumn(final Table table, final String id, final boolean optional)
	{
		super(table, id, false, optional, JDBC_TYPE);
		assert table.database.getDateTimestampType()!=null;
	}
	
	final String getDatabaseType()
	{
		return table.database.getDateTimestampType();
	}

	final String getCheckConstraintIgnoringMandatory()
	{
		return null;
	}
	
	final void load(final ResultSet resultSet, final int columnIndex, final Row row)
			throws SQLException
	{
		final Object loadedTimestamp = resultSet.getObject(columnIndex);
		//System.out.println("TimestampColumn.load "+columnIndex+" "+loadedTimestamp);
		row.put(this, (loadedTimestamp!=null) ? Long.valueOf(((Date)loadedTimestamp).getTime()) : null);
	}
	
	final String cacheToDatabase(final Object cache)
	{
		// Don't use a static instance,
		// since then access must be synchronized
		final SimpleDateFormat df = new SimpleDateFormat("{'ts' ''yyyy-MM-dd HH:mm:ss.SSS''}");

		if(cache==null)
			return "NULL";
		else
		{
			return df.format(new Date(((Long)cache).longValue()));
		}
	}
	
	Object cacheToDatabasePrepared(final Object cache)
	{
		return (cache==null) ? null : new Timestamp(((Long)cache).longValue());
	}

	Object getCheckValue()
	{
		return Long.valueOf(System.currentTimeMillis());
	}
	
}
