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

import com.exedio.cope.testmodel.StringItem;

/**
 * Test, whether database converts empty strings to null,
 * and how the framework hides such behaviour from the user.
 * @author Ralf Wiebicke
 */
public class NullEmptyTest extends TestmodelTest
{
	StringItem item;
	String emptyString;

	public void setUp() throws Exception
	{
		super.setUp();
		deleteOnTearDown(item = new StringItem());

		if(model.supportsEmptyStrings())
			emptyString = "";
		else
			emptyString = null;
	}

	public void testNullEmpty()
			throws IntegrityViolationException
	{
		assertEquals(null, item.getAny());

		item.setAny("");
		assertEquals("", item.getAny()); // TODO empty to null conversion must take effect immediately
		restartTransaction();
		assertEquals(emptyString, item.getAny());

		item.setAny(null);
		assertEquals(null, item.getAny());
		restartTransaction();
		assertEquals(null, item.getAny());
		
		final StringItem item2 = new StringItem("");
		deleteOnTearDown(item2);
		assertEquals("", item2.getAny()); // TODO empty to null conversion must take effect immediately
		restartTransaction();
		assertEquals(emptyString, item2.getAny());
	}

}
