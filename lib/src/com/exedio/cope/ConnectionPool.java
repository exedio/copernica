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
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.exedio.cope.util.PoolCounter;
import com.exedio.dsmf.ConnectionProvider;

final class ConnectionPool implements ConnectionProvider
{
	// TODO: allow changing pool size
	// TODO: gather pool effectivity statistics
	
	private final Connection[] pool = new Connection[10];
	private int size = 0;
	private int due = 0;
	private final Object lock = new Object();

	private final String url;
	private final String user;
	private final String password;
	
	final PoolCounter counter;

	ConnectionPool(final Properties properties)
	{
		this.url = properties.getDatabaseUrl();
		this.user = properties.getDatabaseUser();
		this.password = properties.getDatabasePassword();
		
		// TODO: make this customizable and disableable
		this.counter = new PoolCounter(new int[]{0,1,2,3,4,5,6,7,8,9,10,12,15,16,18,20,25,30,35,40,45,50,60,70,80,90,100,120,140,160,180,200,250,300,350,400,450,500,600,700,800,900,1000});
	}

	public final Connection getConnection() throws SQLException
	{
		counter.get();

		synchronized(lock)
		{
			due++;
			
			if(size>0)
			{
				//System.out.println("connection pool: fetch "+(size-1));
				final Connection result = pool[--size];
				pool[size] = null; // do not reference old connections anymore
				return result;
			}
		}
		//System.out.println("connection pool: CREATE");

		// Important to do this outside the synchronized block!
		return DriverManager.getConnection(url, user, password);
	}

	/**
	 * TODO: If we want to implement changing connection parameters on-the-fly
	 * somewhere in the future, it's important, that client return connections
	 * to exactly the same instance of ConnectionPool.
	 */
	public final void putConnection(final Connection connection) throws SQLException
	{
		if(connection==null)
			throw new NullPointerException();

		counter.put();
		
		synchronized(lock)
		{
			due--;

			if(size<pool.length)
			{
				//System.out.println("connection pool: store "+size);
				pool[size++] = connection;
				return;
			}
		}
		
		//System.out.println("connection pool: CLOSE ");

		// Important to do this outside the synchronized block!
		connection.close();
	}
	
	final void flush()
	{
		final ArrayList connections = new ArrayList(pool.length);

		synchronized(lock)
		{
			if(due!=0)
				throw new RuntimeException("still "+due+" connection outside");
			
			if(size==0)
				return;

			//System.out.println("connection pool: FLUSH "+size);
			for(int i = 0; i<size; i++)
			{
				connections.add(pool[i]);
				pool[i] = null; // do not reference old connections anymore
			}
			size = 0;
		}
		
		try
		{
			for(Iterator i = connections.iterator(); i.hasNext(); )
				((Connection)i.next()).close();
		}
		catch(SQLException e)
		{
			throw new NestingRuntimeException(e);
		}
	}

}
