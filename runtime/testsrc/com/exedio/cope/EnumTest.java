/*
 * Copyright (C) 2004-2007  exedio GmbH (www.exedio.com)
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

public class EnumTest extends AbstractLibTest
{
	static final Model MODEL = new Model(EnumItem.TYPE, EnumItem2.TYPE);

	EnumItem item;
	EnumItem2 item2;
	
	private static final EnumItem.Status status1 = EnumItem.Status.status1;
	private static final EnumItem2.Status state1 = EnumItem2.Status.state1;
	
	public EnumTest()
	{
		super(MODEL);
	}
	
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		item = deleteOnTearDown(new EnumItem(EnumItem.Status.status1));
		item2 = deleteOnTearDown(new EnumItem2(EnumItem2.Status.state1));
	}

	public void testIt()
	{
		assertEquals(EnumItem.Status.class, item.status.getValueClass());
		assertEquals(EnumItem2.Status.class, item2.status.getValueClass());
		
		assertSame(item.status, item.status.as(EnumItem.Status.class));
		try
		{
			item.status.as(EnumItem2.Status.class);
			fail();
		}
		catch(ClassCastException e)
		{
			assertEquals(
					"expected a " + EnumField.class.getName() + '<' + EnumItem2.Status.class.getName() + ">, " +
					"but was a " + EnumField.class.getName() + '<' + EnumItem.Status.class.getName() + '>',
				e.getMessage());
		}
		
		assertEquals(status1, item.getStatus());
		assertEquals(state1, item2.getStatus());
		
		assertEquals(10, item.status.getColumnValue(EnumItem.Status.status1));
		assertEquals(20, item.status.getColumnValue(EnumItem.Status.status2));
		assertEquals(30, item.status.getColumnValue(EnumItem.Status.status3));
		assertEquals(10, item2.status.getColumnValue(EnumItem2.Status.state1));
		assertEquals(20, item2.status.getColumnValue(EnumItem2.Status.state2));
		try
		{
			item2.status.getColumnValue(null);
			fail();
		}
		catch(NullPointerException e)
		{
			assertEquals(null, e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked") // OK: test bad api usage
	public void testUnchecked()
	{
		try
		{
			((EnumField)item.status).getColumnValue((Enum)EnumItem2.Status.state2);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals("expected " + EnumItem.Status.class.getName() + ", but was a " + EnumItem2.Status.class.getName(), e.getMessage());
		}
	}
}
