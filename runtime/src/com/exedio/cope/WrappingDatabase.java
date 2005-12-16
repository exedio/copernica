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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bak.pcj.list.IntList;

class WrappingDatabase implements Database
{
	private final Database nested;
	
	public WrappingDatabase( final Database nested )
	{
		this.nested = nested;
	}
	
	Database getWrappedDatabase()
	{
		return nested;
	}

	public void addIntegrityConstraint(ItemColumn itemColumn)
	{
		nested.addIntegrityConstraint( itemColumn );
	}

	public void addTable(Table table)
	{
		nested.addTable( table );
	}

	public void addUniqueConstraint(String str, UniqueConstraint uniqueConstraint)
	{
		nested.addUniqueConstraint( str, uniqueConstraint );
	}

	public void appendMatchClause(Statement statement, StringFunction stringFunction, String str)
	{
		nested.appendMatchClause( statement, stringFunction, str );
	}

	public void checkDatabase(Connection connection)
	{
		nested.checkDatabase( connection );
	}

	public void checkEmptyDatabase(Connection connection)
	{
		nested.checkEmptyDatabase( connection );
	}

	public void createDatabase()
	{
		nested.createDatabase();
	}

	public void createDatabaseConstraints()
	{
		nested.createDatabaseConstraints();
	}

	public void delete(Connection connection, Item item)
	{
		nested.delete(connection, item);
	}

	public void dropDatabase()
	{
		nested.dropDatabase();
	}

	public void dropDatabaseConstraints()
	{
		nested.dropDatabaseConstraints();
	}

	public ConnectionPool getConnectionPool()
	{
		return nested.getConnectionPool();
	}

	public String getDayType()
	{
		return nested.getDayType();
	}

	public String getDoubleType(int precision)
	{
		return nested.getDoubleType( precision );
	}

	public com.exedio.dsmf.Driver getDriver()
	{
		return nested.getDriver();
	}

	public String getIntegerType(int precision)
	{
		return nested.getIntegerType( precision );
	}

	public int[] getMinMaxPK(Connection connection, Table table)
	{
		return nested.getMinMaxPK( connection, table );
	}

	public String getStringType(int param)
	{
		return nested.getStringType( param );
	}

	public java.util.Properties getTableOptions()
	{
		return nested.getTableOptions();
	}

	public void load(Connection connection, PersistentState state)
	{
		nested.load( connection, state );
	}

	public String makeName(String str)
	{
		return nested.makeName( str );
	}

	public String makeName(String str, String str1)
	{
		return nested.makeName( str, str1 );
	}

	public PkSource makePkSource(Table table)
	{
		return nested.makePkSource( table );
	}

	public com.exedio.dsmf.Schema makeSchema()
	{
		return nested.makeSchema();
	}

	public com.exedio.dsmf.Schema makeVerifiedSchema()
	{
		return nested.makeVerifiedSchema();
	}

	public ArrayList search(Connection connection, Query query, boolean doCountOnly)
	{
		return nested.search( connection, query, doCountOnly );
	}

	public void store(Connection connection, State state, boolean param) throws UniqueViolationException
	{
		nested.store( connection, state, param );
	}

	public final byte[] load(final Connection connection, final BlobColumn column, final Item item)
	{
		return nested.load(connection, column, item);
	}
	
	public final void load(final Connection connection, final BlobColumn column, final Item item, final OutputStream data)
	{
		nested.load(connection, column, item, data);
	}
	
	public final long loadLength(final Connection connection, final BlobColumn column, final Item item)
	{
		return nested.loadLength(connection, column, item);
	}
	
	public final void store(final Connection connection, final BlobColumn column, final Item item, final InputStream data)
	throws IOException
	{
		nested.store(connection, column, item, data);
	}
	
	public boolean supportsCheckConstraints()
	{
		return nested.supportsCheckConstraints();
	}

	public boolean supportsEmptyStrings()
	{
		return nested.supportsEmptyStrings();
	}

	public boolean supportsRightOuterJoins()
	{
		return nested.supportsRightOuterJoins();
	}

	public void tearDownDatabase()
	{
		nested.tearDownDatabase();
	}

	public void tearDownDatabaseConstraints()
	{
		nested.tearDownDatabaseConstraints();
	}

	public void defineColumnTypes(IntList columnTypes, java.sql.Statement statement) throws SQLException
	{
		nested.defineColumnTypes( columnTypes, statement );
	}

	public boolean isDefiningColumnTypes()
	{
		return nested.isDefiningColumnTypes();
	}

	public String getDateTimestampType()
	{
		return nested.getDateTimestampType();
	}
	
	public String getBlobType()
	{
		return nested.getBlobType();
	}
}
