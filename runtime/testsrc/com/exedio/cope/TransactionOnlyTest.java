package com.exedio.cope;

public class TransactionOnlyTest extends AbstractLibTest
{
	
	public TransactionOnlyTest()
	{
		super(CacheIsolationTest.MODEL);
	}
	
	public void testNesting()
	{
		assertEquals( true, model.hasCurrentTransaction() );
		Transaction tx = model.getCurrentTransaction();
		try
		{
			model.startTransaction("nested");
			fail();
		}
		catch ( RuntimeException e )
		{
			assertEquals( "there is already a transaction bound to current thread", e.getMessage() );
		}
		assertEquals( tx, model.getCurrentTransaction() );
	}
	
	public void testJoinClosed()
	{
		Transaction tx = model.getCurrentTransaction();
		model.commit();
		try
		{
			model.joinTransaction( tx );
		}
		catch ( RuntimeException e )
		{
			assertEquals( "cannot bind to closed transaction", e.getMessage() );
		}
		assertEquals( false, model.hasCurrentTransaction() );
		model.startTransaction("just for tearDown");
		assertEquals( "just for tearDown", model.getCurrentTransaction().getName() );
	}
	
	public void testTransactionLifecycle()
	{
		final Transaction copeTest = model.getCurrentTransaction();
		assertContains( copeTest, model.getOpenTransactions() );
		assertUnmodifiable( model.getOpenTransactions() );
		assertEquals( false, copeTest.isClosed() );
		assertCurrentTransaction( copeTest );
		
		model.leaveTransaction();
		assertContains( copeTest, model.getOpenTransactions() );
		assertEquals( false, copeTest.isClosed() );
		assertCurrentTransaction( null );
		
		final Transaction tx1 = model.startTransaction( "tx1" );
		assertContains( copeTest, tx1, model.getOpenTransactions() );
		assertEquals( false, copeTest.isClosed() );
		assertEquals( false, tx1.isClosed() );
		assertCurrentTransaction( tx1 );
		
		model.leaveTransaction();
		assertContains( copeTest, tx1, model.getOpenTransactions() );
		assertEquals( false, copeTest.isClosed() );
		assertEquals( false, tx1.isClosed() );
		assertCurrentTransaction( null );
		
		model.joinTransaction( copeTest );
		assertContains( copeTest, tx1, model.getOpenTransactions() );
		assertEquals( false, copeTest.isClosed() );
		assertEquals( false, tx1.isClosed() );
		assertCurrentTransaction( copeTest );
		
		model.commit();
		assertContains( tx1, model.getOpenTransactions() );
		assertEquals( true, copeTest.isClosed() );
		assertEquals( false, tx1.isClosed() );
		assertCurrentTransaction( null );
		
		model.joinTransaction( tx1 );
		assertContains( tx1, model.getOpenTransactions() );
		assertEquals( true, copeTest.isClosed() );
		assertEquals( false, tx1.isClosed() );
		assertCurrentTransaction( tx1 );
		
		model.rollback();
		assertContains( model.getOpenTransactions() );
		assertEquals( true, copeTest.isClosed() );
		assertEquals( true, tx1.isClosed() );
		assertCurrentTransaction( null );
		
		model.startTransaction( "forTearDown" );
	}
	
	private void assertCurrentTransaction( Transaction tx )
	{
		assertEquals( tx!=null, model.hasCurrentTransaction() );
		if ( tx==null )
		{
			try
			{
				model.getCurrentTransaction();
				fail();
			}
			catch ( RuntimeException e )
			{
				assertEquals( "there is no cope transaction bound to this thread, see Model#startTransaction", e.getMessage() );
			}
		}
		else
		{
			assertEquals( tx, model.getCurrentTransaction() );
		}
	}
}
