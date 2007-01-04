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

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.exedio.cope.junit.CopeAssert;

public class ConnectionPoolTest extends CopeAssert
{

	public void testCp() throws SQLException
	{
		final Conn c1 = new Conn();
		final Factory f = new Factory(listg(c1));
		f.assertV(0);

		final ConnectionPool cp = new ConnectionPool(f, 1, 0);
		c1.assertV(0, 0);
		f.assertV(0);
		
		// get and create
		assertSame(c1, cp.getConnection());
		c1.assertV(0, 0);
		f.assertV(1);
		
		// put into idle
		cp.putConnection(c1);
		c1.assertV(1, 0);
		f.assertV(1);

		// get from idle
		assertSame(c1, cp.getConnection());
		c1.assertV(1, 0);
		f.assertV(1);
		
		// put into idle
		cp.putConnection(c1);
		c1.assertV(2, 0);
		f.assertV(1);

		// get from idle with other autoCommit
		assertSame(c1, cp.getConnection());
		c1.assertV(2, 0);
		f.assertV(1);
	}
	
	public void testOverflow() throws SQLException
	{
		final Conn c1 = new Conn();
		final Conn c2 = new Conn();
		final Factory f = new Factory(listg(c1, c2));
		f.assertV(0);

		final ConnectionPool cp = new ConnectionPool(f, 1, 0);
		c1.assertV(0, 0);
		c2.assertV(0, 0);
		f.assertV(0);
		
		// get and create
		assertSame(c1, cp.getConnection());
		c1.assertV(0, 0);
		c2.assertV(0, 0);
		f.assertV(1);
		
		// get and create (2)
		assertSame(c2, cp.getConnection());
		c1.assertV(0, 0);
		c2.assertV(0, 0);
		f.assertV(2);
		
		// put into idle
		cp.putConnection(c1);
		c1.assertV(1, 0);
		c2.assertV(0, 0);
		f.assertV(2);
		
		// put and close
		cp.putConnection(c2);
		c1.assertV(1, 0);
		c2.assertV(1, 1);
		f.assertV(2);
	}
	
	public void testIdleInitial() throws SQLException
	{
		final Conn c1 = new Conn();
		final Factory f = new Factory(listg(c1));
		f.assertV(0);

		final ConnectionPool cp = new ConnectionPool(f, 1, 1);
		c1.assertV(0, 0);
		f.assertV(1); // already created
		
		// get and create
		assertSame(c1, cp.getConnection());
		c1.assertV(0, 0);
		f.assertV(1);
	}
	
	public void testIsClosed() throws SQLException
	{
		final Conn c1 = new Conn();
		final Conn c2 = new Conn();
		final Factory f = new Factory(listg(c1, c2));
		f.assertV(0);

		final ConnectionPool cp = new ConnectionPool(f, 1, 0);
		c1.assertV(0, 0);
		c2.assertV(0, 0);
		f.assertV(0);
		
		// get and create
		assertSame(c1, cp.getConnection());
		c1.assertV(0, 0);
		c2.assertV(0, 0);
		f.assertV(1);
		
		// dont put into idle, because its closed
		c1.isClosed = true;
		try
		{
			cp.putConnection(c1);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals("unexpected closed connection", e.getMessage());
		}
		c1.assertV(1, 0);
		c2.assertV(0, 0);
		f.assertV(1);

		// create new because no idle available
		assertSame(c2, cp.getConnection());
		c1.assertV(1, 0);
		c2.assertV(0, 0);
		f.assertV(2);
	}
	
	public void testFlush() throws SQLException
	{
		final Conn c1 = new Conn();
		final Conn c2 = new Conn();
		final Factory f = new Factory(listg(c1, c2));
		f.assertV(0);

		final ConnectionPool cp = new ConnectionPool(f, 1, 0);
		c1.assertV(0, 0);
		c2.assertV(0, 0);
		f.assertV(0);
		
		// get and create
		assertSame(c1, cp.getConnection());
		c1.assertV(0, 0);
		c2.assertV(0, 0);
		f.assertV(1);
		
		// put into idle
		cp.putConnection(c1);
		c1.assertV(1, 0);
		c2.assertV(0, 0);
		f.assertV(1);

		// flush closes c1
		cp.flush();
		c1.assertV(1, 1);
		c2.assertV(0, 0);
		f.assertV(1);

		// create new because flushed
		assertSame(c2, cp.getConnection());
		c1.assertV(1, 1);
		c2.assertV(0, 0);
		f.assertV(2);
	}
	
	public void testNoPool() throws SQLException
	{
		final Conn c1 = new Conn();
		final Conn c2 = new Conn();
		final Factory f = new Factory(listg(c1, c2));
		f.assertV(0);

		final ConnectionPool cp = new ConnectionPool(f, 0, 0);
		c1.assertV(0, 0);
		c2.assertV(0, 0);
		f.assertV(0);
		
		// get and create
		assertSame(c1, cp.getConnection());
		c1.assertV(0, 0);
		c2.assertV(0, 0);
		f.assertV(1);
		
		// put and close because no idle
		cp.putConnection(c1);
		c1.assertV(1, 1);
		c2.assertV(0, 0);
		f.assertV(1);

		// create new because no idle
		assertSame(c2, cp.getConnection());
		c1.assertV(1, 1);
		c2.assertV(0, 0);
		f.assertV(2);
	}
	
	public void testTimeout() throws SQLException
	{
		final Conn c1 = new Conn();
		final Conn c2 = new Conn();
		final Factory f = new Factory(listg(c1, c2));
		f.assertV(0);

		final ConnectionPool cp = new ConnectionPool(f, 1, 0);
		c1.assertV(0, 0);
		c2.assertV(0, 0);
		f.assertV(0);
		
		// get and create
		assertSame(c1, cp.getConnection());
		c1.assertV(0, 0);
		c2.assertV(0, 0);
		f.assertV(1);
		
		// put into idle
		cp.putConnection(c1);
		c1.assertV(1, 0);
		c2.assertV(0, 0);
		f.assertV(1);

		// create new because c1 timed out
		c1.timeout = true;
		assertSame(c2, cp.getConnection());
		c1.assertV(1, 0);
		c2.assertV(0, 0);
		f.assertV(2);
	}
	
	static class Factory implements ConnectionPool.Factory
	{
		final Iterator<Conn> connections;
		int createCount = 0;
		
		Factory(final List<Conn> connections)
		{
			this.connections = connections.iterator();
		}

		void assertV(final int createCount)
		{
			assertEquals(createCount, this.createCount);
		}
		
		public java.sql.Connection createConnection()
		{
			createCount++;
			return connections.next();
		}
		
		public boolean isValid(final Connection e)
		{
			return ((Conn)e).isValid();
		}
		
		public void dispose(final Connection e) throws SQLException
		{
			((Conn)e).dispose();
		}
	}
	
	static class Conn implements Connection
	{
		boolean isClosed = false;
		int isClosedCount = 0;
		int closedCount = 0;
		boolean timeout = false;
		
		void assertV(final int isClosedCount, final int closedCount)
		{
			assertEquals(isClosedCount, this.isClosedCount);
			assertEquals(closedCount, this.closedCount);
		}
		
		public void setAutoCommit(final boolean autoCommit) throws SQLException
		{
			throw new RuntimeException();
		}

		public boolean isClosed() throws SQLException
		{
			isClosedCount++;
			return isClosed;
		}

		boolean isValid()
		{
			return !timeout;
		}

		void dispose() throws SQLException
		{
			closedCount++;
		}

		public void close() throws SQLException
		{
			throw new RuntimeException();
		}

		public DatabaseMetaData getMetaData() throws SQLException
		{
			throw new RuntimeException();
		}
		
		public Statement createStatement() throws SQLException
		{
			throw new RuntimeException();
		}

		public PreparedStatement prepareStatement(String sql) throws SQLException
		{
			throw new RuntimeException();
		}

		public CallableStatement prepareCall(String sql) throws SQLException
		{
			throw new RuntimeException();
		}

		public String nativeSQL(String sql) throws SQLException
		{
			throw new RuntimeException();
		}

		public boolean getAutoCommit() throws SQLException
		{
			throw new RuntimeException();
		}

		public void commit() throws SQLException
		{
			throw new RuntimeException();
		}

		public void rollback() throws SQLException
		{
			throw new RuntimeException();
		}

		public void setReadOnly(boolean readOnly) throws SQLException
		{
			throw new RuntimeException();
		}

		public boolean isReadOnly() throws SQLException
		{
			throw new RuntimeException();
		}

		public void setCatalog(String catalog) throws SQLException
		{
			throw new RuntimeException();
		}

		public String getCatalog() throws SQLException
		{
			throw new RuntimeException();
		}

		public void setTransactionIsolation(int level) throws SQLException
		{
			throw new RuntimeException();
		}

		public int getTransactionIsolation() throws SQLException
		{
			throw new RuntimeException();
		}

		public SQLWarning getWarnings() throws SQLException
		{
			throw new RuntimeException();
		}

		public void clearWarnings() throws SQLException
		{
			throw new RuntimeException();
		}

		public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException
		{
			throw new RuntimeException();
		}

		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException
		{
			throw new RuntimeException();
		}

		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException
		{
			throw new RuntimeException();
		}

		public Map<String, Class<?>> getTypeMap() throws SQLException
		{
			throw new RuntimeException();
		}

		public void setTypeMap(Map<String, Class<?>> arg0) throws SQLException
		{
			throw new RuntimeException();
		}

		public void setHoldability(int holdability) throws SQLException
		{
			throw new RuntimeException();
		}

		public int getHoldability() throws SQLException
		{
			throw new RuntimeException();
		}

		public Savepoint setSavepoint() throws SQLException
		{
			throw new RuntimeException();
		}

		public Savepoint setSavepoint(String name) throws SQLException
		{
			throw new RuntimeException();
		}

		public void rollback(Savepoint savepoint) throws SQLException
		{
			throw new RuntimeException();
		}

		public void releaseSavepoint(Savepoint savepoint) throws SQLException
		{
			throw new RuntimeException();
		}

		public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException
		{
			throw new RuntimeException();
		}

		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException
		{
			throw new RuntimeException();
		}

		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException
		{
			throw new RuntimeException();
		}

		public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException
		{
			throw new RuntimeException();
		}

		public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException
		{
			throw new RuntimeException();
		}

		public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException
		{
			throw new RuntimeException();
		}
	}
}
