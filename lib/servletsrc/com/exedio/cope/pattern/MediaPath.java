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
	
	public static final Log noSuchPath = new Log("no such path");
	public final Log noSuchItem = new Log("no such item");
	public final Log dataIsNull = new Log("data is null");
	public final Log notModified = new Log("not modified");
	public final Log delivered = new Log("delivered");

	final Media.Log doGet(
			final HttpServletRequest request, final HttpServletResponse response,
			final String subPath)
		throws ServletException, IOException, MediaException
	{
		final int dot = subPath.indexOf('.');
		//System.out.println("trailingDot="+trailingDot);

		final String pkString;
		final String extension;
		if(dot>=0)
		{
			pkString = subPath.substring(0, dot);
			extension = subPath.substring(dot);
		}
		else
		{
			pkString = subPath;
			extension = "";
		}
		
		//System.out.println("pkString="+pkString);

		final String id = getType().getID() + '.' + pkString;
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
			throw new MediaException(noSuchItem);
		}
		finally
		{
			model.rollbackIfNotCommitted();
		}
	}

	public abstract Media.Log doGet(HttpServletRequest request, HttpServletResponse response, Item item, String extension)
		throws ServletException, IOException, MediaException;

	public abstract Date getStart();

	public final static class Log
	{
		private int counter = 0;
		private final Object lock = new Object();
		final String name;
		
		public Log(final String name)
		{
			if(name==null)
				throw new NullPointerException();
			
			this.name = name;
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

		final void reset()
		{
			synchronized(lock)
			{
				counter = 0;
			}
		}
	}

}
