/*
 * Copyright (C) 2004-2009  exedio GmbH (www.exedio.com)
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

package com.exedio.copernica;

public class ErrorTest extends AbstractWebTest
{
	public void testError() throws Exception
	{
		try
		{
			beginAt("copernica.jsp/type.html?t=EmptyItemx");
			fail();
		}
		catch(RuntimeException e)
		{
			final String message = e.getMessage();
			assertTrue(message, message.startsWith("com.meterware.httpunit.HttpInternalErrorException: Error on HTTP request: 500 Internal Error [http://127.0.0.1:" + System.getProperty("tomcat.port.http") + "/copetest-hsqldb/copernica.jsp/type.html?t=EmptyItemx"));
		}
	}
}
