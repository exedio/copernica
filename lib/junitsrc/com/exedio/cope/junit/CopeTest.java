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

package com.exedio.cope.junit;

import com.exedio.cope.Item;
import java.util.HashSet;
import java.util.Iterator;

import com.exedio.cope.Model;
import com.exedio.cope.NestingRuntimeException;
import com.exedio.cope.Properties;
import com.exedio.cope.util.PoolCounter;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * An abstract test case class for tests creating/using some persistent data.
 * @author Ralf Wiebicke
 */
public abstract class CopeTest extends CopeAssert
{
	private static HashSet createdDatabase = new HashSet();
	private static HashSet registeredDropDatabaseHook = new HashSet();
	private static Object lock = new Object();

	public final Model model;
	public final boolean exclusive;
	
	private boolean testMethodFinished = false;
	private ArrayList deleteOnTearDown = null;
	
	
	protected CopeTest(final Model model)
	{
		this(model, false);
	}
	
	/**
	 * @param exclusive
	 * if true, the database schema for the model will be created before each test
	 * and dropped after each test.
	 * This will make tests last much longer, so this is useful for debugging only.
	 */
	protected CopeTest(final Model model, final boolean exclusive)
	{
		this.model = model;
		this.exclusive = exclusive;
	}
	
	protected final void deleteOnTearDown(final Item item)
	{
		deleteOnTearDown.add(item);
	}
	
	protected final void printConnectionPoolCounter()
	{
		final PoolCounter connectionPoolCounter = model.getConnectionPoolCounter();

		System.out.println("ConnectionPool: "+connectionPoolCounter.getGetCounter()+", "+connectionPoolCounter.getPutCounter());
		for(Iterator i = connectionPoolCounter.getPools().iterator(); i.hasNext(); )
		{
			final PoolCounter.Pool pool = (PoolCounter.Pool)i.next();
			System.out.println("ConnectionPool:["+pool.getSize()+"]: "+pool.getLevel()+", "+pool.getMaxLevel()+", "+pool.getCreateCounter()+", "+pool.getDestroyCounter()+", "+pool.getLoss());
		}
	}

	private final void createDatabase()
	{
		if(exclusive)
			model.createDatabase();
		else
		{
			synchronized(lock)
			{
				if(!createdDatabase.contains(model))
				{
					model.createDatabase();
					createdDatabase.add(model);
				}
			}
		}
	}
	
	private final void dropDatabase()
	{
		if(exclusive)
			model.dropDatabase();
		else
		{
			synchronized(lock)
			{
				if(!registeredDropDatabaseHook.contains(model))
				{
					Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){
						public void run()
						{
							//printConnectionPoolCounter();
							model.dropDatabase();
							model.close();
						}
					}));
					registeredDropDatabaseHook.add(model);
				}
			}
		}
	}

	public void runBare() throws Throwable 
	{
		setUp();
		try 
		{
			testMethodFinished = false;
			runTest();
			testMethodFinished = true;
		}
		finally 
		{
			tearDown();
		}
	}
	
	protected void setUp() throws Exception
	{
		model.setPropertiesInitially(new Properties());

		super.setUp();
		deleteOnTearDown = new ArrayList();
		createDatabase();

		model.startTransaction("CopeTest");
		model.checkEmptyDatabase();
	}
	
	protected void tearDown() throws Exception
	{
		if( !exclusive )
		{
			try
			{
				if(!deleteOnTearDown.isEmpty())
				{
					for(ListIterator i = deleteOnTearDown.listIterator(deleteOnTearDown.size()); i.hasPrevious(); )
						((Item)i.previous()).deleteCopeItem();
					deleteOnTearDown.clear();
				}
				deleteOnTearDown = null;
				model.checkEmptyDatabase();
			}
			catch ( RuntimeException e )
			{
				if ( testMethodFinished )
				{
					throw new NestingRuntimeException( e, "test completed successfully but didn't clean up database" );
				}
				else
				{
					model.tearDownDatabase();
					model.createDatabase();
				}
			}
		}
		if ( testMethodFinished || model.hasCurrentTransaction() )
		{
			model.commit();
		}

		dropDatabase();
		super.tearDown();
	}
	
	protected void restartTransaction()
	{
		final String oldName = model.getCurrentTransaction().getName();
		model.commit();
		model.startTransaction( oldName+"-restart" );
	}
	
}
