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

package com.exedio.cope.testmodel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exedio.cope.Item;
import com.exedio.cope.StringAttribute;
import com.exedio.cope.pattern.Media;
import com.exedio.cope.pattern.MediaException;
import com.exedio.cope.pattern.MediaPath;

/**
 * A test subclass of MediaPath for unit-testing custom extentions of MediaPath.
 * @author Ralf Wiebicke
 */
public final class MediaNameServer extends MediaPath
{
	final StringAttribute source;

	public MediaNameServer(final StringAttribute source)
	{
		this.source = source;
	}
	
	public StringAttribute getSource()
	{
		return source;
	}
	
	public void initialize()
	{
		super.initialize();
		
		final String name = getName();
		if(source!=null && !source.isInitialized())
			initialize(source, name+"Source");
	}
	
	private long start = System.currentTimeMillis();
	
	public final Date getStart()
	{
		return new Date(start);
	}

	private static final long EXPIRES_OFFSET = 1000 * 5; // 5 seconds
	
	private static final String RESPONSE_EXPIRES = "Expires";
	private static final String RESPONSE_CONTENT_LENGTH = "Content-Length";
	
	public Media.Log doGet(
			final HttpServletRequest request, final HttpServletResponse response,
			final Item item, final String extension)
		throws ServletException, IOException, MediaException
	{
		final String content = (String)item.get(source);
		//System.out.println("contentType="+contentType);
		if(content==null)
			throw new MediaException(itemFound);

		response.setContentType("text/plain");

		final long now = System.currentTimeMillis();
		response.setDateHeader(RESPONSE_EXPIRES, now+EXPIRES_OFFSET);
		
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final OutputStreamWriter osw = new OutputStreamWriter(baos);
		osw.write(content);
		osw.close();
		final byte[] contentBytes = baos.toByteArray();
		
		final long contentLength = contentBytes.length;
		//System.out.println("contentLength="+String.valueOf(contentLength));
		response.setHeader(RESPONSE_CONTENT_LENGTH, String.valueOf(contentLength));
		//response.setHeader("Cache-Control", "public");

		System.out.println(request.getMethod()+' '+request.getProtocol()+" modified: "+contentLength);

		ServletOutputStream out = null;
		try
		{
			out = response.getOutputStream();
			out.write(contentBytes);
		}
		finally
		{
			if(out!=null)
				out.close();
		}
		return null;
	}

}
