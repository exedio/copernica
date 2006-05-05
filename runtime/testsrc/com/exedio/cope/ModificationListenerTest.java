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

import java.util.Collection;
import java.util.List;

import com.exedio.cope.util.ModificationListener;

public class ModificationListenerTest extends AbstractLibTest
{
	public ModificationListenerTest()
	{
		super(Main.matchModel);
	}
	
	final TestListener l = new TestListener();
	
	public void testCommitListener()
	{
		final MatchItem item1 = new MatchItem("item1");
		deleteOnTearDown(item1);
		l.assertIt(null);
		model.commit();
		l.assertIt(list(item1));
		l.assertIt(null);

		model.startTransaction("CommitListenerTest2");
		assertEquals("item1", item1.getText());
		l.assertIt(null);
		model.commit();
		l.assertIt(null);

		model.startTransaction("CommitListenerTest3");
		final MatchItem item2 = new MatchItem("item2");
		deleteOnTearDown(item2);
		l.assertIt(null);
		model.commit();
		l.assertIt(list(item2));
		
		model.startTransaction("CommitListenerTest4");
		item1.setText("item1x");
		l.assertIt(null);
		model.commit();
		l.assertIt(list(item1));
		
		model.startTransaction("CommitListenerTest5");
		item1.setText("item1y");
		item2.setText("item2y");
		l.assertIt(null);
		model.commit();
		l.assertIt(list(item1, item2));

		model.startTransaction("CommitListenerTest5");
		final MatchItem item3 = new MatchItem("item3");
		item1.setText("item1z");
		l.assertIt(null);
		model.commit();
		l.assertIt(list(item1, item3));

		model.startTransaction("CommitListenerTest5");
		item3.deleteCopeItem();
		l.assertIt(null);
		model.commit();
		l.assertIt(list(item3));

		model.startTransaction("CommitListenerTestX");
	}
	
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		model.addModificationListener(l);
	}
	
	@Override
	protected void tearDown() throws Exception
	{
		model.removeModificationListener(l);
		super.tearDown();
	}
	
	class TestListener implements ModificationListener
	{
		Collection<Item> items = null;
		
		public void onModifyingCommit(final Collection<Item> items)
		{
			assertTrue(items!=null);
			assertTrue(!items.isEmpty());
			assertUnmodifiable(items);
			
			assertTrue(this.items==null);
			
			//assertTrue(model.getOpenTransactions().isEmpty()); TODO
			try
			{
				model.getCurrentTransaction();
				//fail(); TODO
			}
			catch(RuntimeException e)
			{
				assertEquals("there is no cope transaction bound to this thread, see Model#startTransaction", e.getMessage());
			}
			
			this.items = items;
		}
		
		void assertIt(final List<? extends Object> expectedItems)
		{
			assertContainsList(expectedItems, items);
			items = null;
		}
	}

}
