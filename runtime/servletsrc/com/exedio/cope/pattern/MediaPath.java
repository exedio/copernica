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

package com.exedio.cope.pattern;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exedio.cope.Item;
import com.exedio.cope.Model;
import com.exedio.cope.NoSuchIDException;
import com.exedio.cope.Pattern;

public abstract class MediaPath extends Pattern
{
	private String urlPath = null;
	private String mediaRootUrl = null;

	@Override
	public void initialize()
	{
		final String name = getName();
		
		urlPath = getType().getID() + '/' + name + '/';
	}
	
	final String getUrlPath()
	{
		if(urlPath==null)
			throw new RuntimeException("not yet initialized");
		
		return urlPath;
	}
	
	final String getMediaRootUrl()
	{
		if(mediaRootUrl==null)
			mediaRootUrl = getType().getModel().getProperties().getMediaRootUrl();
		
		return mediaRootUrl;
	}
	
	public static final Log noSuchPath = new Log("no such path", HttpServletResponse.SC_NOT_FOUND);
	public final Log exception = new Log("exception", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	public final Log notAnItem = new Log("not an item", HttpServletResponse.SC_NOT_FOUND);
	public final Log noSuchItem = new Log("no such item", HttpServletResponse.SC_NOT_FOUND);
	public final Log isNull = new Log("is null", HttpServletResponse.SC_NOT_FOUND);
	public final Log notModified = new Log("not modified", HttpServletResponse.SC_OK);
	public final Log delivered = new Log("delivered", HttpServletResponse.SC_OK);

	final Media.Log doGet(
			final HttpServletRequest request, final HttpServletResponse response,
			final String subPath)
		throws ServletException, IOException
	{
		final int firstDot = subPath.indexOf('.');
		final int dot = (firstDot>=0) ? subPath.indexOf('.', firstDot+1) : firstDot;
		//System.out.println("trailingDot="+trailingDot);

		final String id;
		final String extension;
		if(dot>=0)
		{
			id = subPath.substring(0, dot);
			extension = subPath.substring(dot);
		}
		else
		{
			id = subPath;
			extension = "";
		}
		
		//System.out.println("ID="+id);
		final Model model = getType().getModel();
		try
		{
			model.startTransaction("MediaServlet");
			final Item item = model.findByID(id);
			//System.out.println("item="+item);
			
			final Media.Log result = doGet(request, response, item, extension);
			model.commit();
			return result;
		}
		catch(NoSuchIDException e)
		{
			return e.notAnID() ? notAnItem : noSuchItem;
		}
		finally
		{
			model.rollbackIfNotCommitted();
		}
	}

	public abstract Media.Log doGet(HttpServletRequest request, HttpServletResponse response, Item item, String extension)
		throws ServletException, IOException;

	public abstract Date getStart();

	public final static class Log
	{
		private int counter = 0;
		private final Object lock = new Object();
		final String name;
		public final int responseStatus;
		
		Log(final String name, final int responseStatus)
		{
			if(name==null)
				throw new NullPointerException();
			switch(responseStatus)
			{
				case HttpServletResponse.SC_OK:
				case HttpServletResponse.SC_NOT_FOUND:
				case HttpServletResponse.SC_INTERNAL_SERVER_ERROR:
					break;
				default:
					throw new RuntimeException(String.valueOf(responseStatus));
			}
			
			this.name = name;
			this.responseStatus = responseStatus;
		}
		
		public final void increment()
		{
			synchronized(lock)
			{
				counter++;
			}
		}

		public final int get()
		{
			synchronized(lock)
			{
				return counter;
			}
		}

	}

}
