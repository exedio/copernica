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

package com.exedio.dsmf;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class PackageTest extends TestCase
{

	public static Test suite()
	{
		final TestSuite suite = new TestSuite();
		
		suite.addTest(new TestSuite(TableTest.class));
		suite.addTest(new TestSuite(ColumnTest.class));
		suite.addTest(new TestSuite(ConstraintTest.class));
		suite.addTest(new TestSuite(CircleTest.class));

		suite.addTest(new TestSuite(SQLInjectionTest.class));
		
		return suite;
	}

}
