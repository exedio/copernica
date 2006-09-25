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

package com.exedio.copernica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class ResourceTest extends AbstractWebTest
{

	public void testError() throws Exception
	{
		final String prefix = "http://localhost:8080/copetest-hsqldb/console/";
		final URL stylesheet = new URL(prefix + "admin.css");

		final long stylesheetLastModified = assertURL(stylesheet);
		assertEquals(stylesheetLastModified, assertURL(stylesheet));
		assertEquals(stylesheetLastModified, assertURL(stylesheet, stylesheetLastModified-1, false));
		assertEquals(stylesheetLastModified, assertURL(stylesheet, stylesheetLastModified, true));
		assertEquals(stylesheetLastModified, assertURL(stylesheet, stylesheetLastModified+5000, true));

	}
	
	private long assertURL(final URL url) throws IOException
	{
		return assertURL(url, -1, false);
	}
	
	private long assertURL(final URL url, final String contentType) throws IOException
	{
		return assertURL(url, contentType, -1, false);
	}
	
	private long assertURL(final URL url, final long ifModifiedSince, final boolean expectNotModified) throws IOException
	{
		return assertURL(url, "text/css", ifModifiedSince, expectNotModified);
	}

	private long assertURL(final URL url, final String contentType, final long ifModifiedSince, final boolean expectNotModified) throws IOException
	{
		final Date before = new Date();
		final HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setFollowRedirects(false);
		if(ifModifiedSince>=0)
			conn.setIfModifiedSince(ifModifiedSince);
		conn.connect();
		assertEquals(expectNotModified ? conn.HTTP_NOT_MODIFIED : conn.HTTP_OK, conn.getResponseCode());
		assertEquals(expectNotModified ? "Not Modified" : "OK", conn.getResponseMessage());
		final long date = conn.getDate();
		final Date after = new Date();
		//System.out.println("Date: "+new Date(date));
		assertWithinHttpDate(before, after, new Date(date));
		final long lastModified = conn.getLastModified();
		//System.out.println("LastModified: "+new Date(lastModified));
		assertTrue((date+1000)>=lastModified);
		assertEquals(expectNotModified ? null : contentType, conn.getContentType()); // TODO: content type should be set on 304
		//System.out.println("Expires: "+new Date(textConn.getExpiration()));
		assertWithin(new Date(date+(4*60*1000)), new Date(date+(6*60*1000)), new Date(conn.getExpiration()));
		assertEquals(expectNotModified ? -1 : 3018, conn.getContentLength());
		
		final BufferedReader is = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		if(!expectNotModified)
		{
			assertEquals("/*", is.readLine());
			assertEquals("This is the default style sheet for the", is.readLine());
			assertEquals("cope console", is.readLine());
			assertEquals("*/", is.readLine());
			assertEquals("", is.readLine());
		}
		is.close();
		
		return lastModified;
	}

}
