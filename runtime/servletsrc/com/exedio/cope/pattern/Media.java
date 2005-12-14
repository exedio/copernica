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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exedio.cope.Attribute;
import com.exedio.cope.AttributeValue;
import com.exedio.cope.BooleanAttribute;
import com.exedio.cope.ConstraintViolationException;
import com.exedio.cope.DataAttribute;
import com.exedio.cope.Item;
import com.exedio.cope.MandatoryViolationException;
import com.exedio.cope.ObjectAttribute;
import com.exedio.cope.Pattern;
import com.exedio.cope.StringAttribute;
import com.exedio.cope.Attribute.Option;

public final class Media extends MediaPath
{
	final boolean notNull;
	final DataAttribute data;
	final ContentType contentType;
	final BooleanAttribute exists;
	final ObjectAttribute isNull;

	public Media(final Option option, final String fixedMimeMajor, final String fixedMimeMinor)
	{
		if(option==null)
			throw new NullPointerException("option must not be null");
		
		this.notNull = option.mandatory;
		registerSource(this.data = new DataAttribute(option));
		this.contentType = new FixedContentType(fixedMimeMajor, fixedMimeMinor);
		this.exists = option.mandatory ? null : new BooleanAttribute(Item.OPTIONAL);
		this.isNull = exists;
		if(this.exists!=null)
			registerSource(this.exists);
	}
	
	public Media(final Option option, final String fixedMimeMajor)
	{
		if(option==null)
			throw new NullPointerException("option must not be null");

		this.notNull = option.mandatory;
		registerSource(this.data = new DataAttribute(option));
		final StringAttribute mimeMinor = new StringAttribute(option, 1, 30);
		this.contentType = new HalfFixedContentType(fixedMimeMajor, mimeMinor);
		this.exists = null;
		this.isNull = mimeMinor;
	}
	
	public Media(final Option option)
	{
		if(option==null)
			throw new NullPointerException("option must not be null");

		this.notNull = option.mandatory;
		registerSource(this.data = new DataAttribute(option));
		final StringAttribute mimeMajor = new StringAttribute(option, 1, 30);
		final StringAttribute mimeMinor = new StringAttribute(option, 1, 30);
		this.contentType = new StoredContentType(mimeMajor, mimeMinor);
		this.exists = null;
		this.isNull = mimeMajor;
	}
	
	public final String getFixedMimeMajor()
	{
		return contentType.getFixedMimeMajor();
	}
	
	public final String getFixedMimeMinor()
	{
		return contentType.getFixedMimeMinor();
	}
	
	public final DataAttribute getData()
	{
		return data;
	}
	
	public final StringAttribute getMimeMajor()
	{
		return contentType.getMimeMajor();
	}
	
	public final StringAttribute getMimeMinor()
	{
		return contentType.getMimeMinor();
	}
	
	public final BooleanAttribute getExists()
	{
		return exists;
	}
	
	public final ObjectAttribute getIsNull()
	{
		return isNull;
	}
	
	public void initialize()
	{
		super.initialize();
		
		final String name = getName();
		if(data!=null && !data.isInitialized())
			initialize(data, name+"Data");
		contentType.initialize(name);
		if(exists!=null && !exists.isInitialized())
			initialize(exists, name+"Exists");
	}
	
	public boolean isNull(final Item item)
	{
		return notNull ? false : (item.get(isNull)==null);
	}

	/**
	 * Returns the major mime type of this media.
	 * Returns null, if there is no data for this media.
	 */
	public final String getMimeMajor(final Item item)
	{
		final String contentType = getContentType(item);
		return contentType!=null ? contentType.substring(0, contentType.indexOf('/')) : null;
	}
	
	/**
	 * Returns the minor mime type of this media.
	 * Returns null, if there is no data for this media.
	 */
	public final String getMimeMinor(final Item item)
	{
		final String contentType = getContentType(item);
		return contentType!=null ? contentType.substring(contentType.indexOf('/')+1) : null;
	}
          
	/**
	 * Returns the content type of this media.
	 * Returns null, if there is no data for this media.
	 */
	public final String getContentType(final Item item)
	{
		if(isNull(item))
			return null;

		return contentType.getContentType(item);
	}
	
	private final RuntimeException newNoDataException(final Item item)
	{
		return new RuntimeException("missing data for "+this.toString()+" on "+item.toString());
	}

	/**
	 * Returns a stream for fetching the data of this media.
	 * <b>You are responsible for closing the stream, when you are finished!</b>
	 * Returns null, if there is no data for this media.
	 */
	public final InputStream getData(final Item item)
	{
		if(isNull(item))
			return null;

		final InputStream result = data.get(item);
		if(result==null)
			throw newNoDataException(item);
		
		return result;
	}

	/**
	 * Returns the length of the data of this media.
	 * Returns -1, if there is no data for this media.
	 */
	public final long getLength(final Item item)
	{
		if(isNull(item))
			return -1;
		
		final long result = data.getLength(item);
		if(result<0)
			throw newNoDataException(item);

		return result;
	}

	/**
	 * Returns the date of the last modification
	 * of the data of this media.
	 * Returns -1, if there is no data for this media.
	 */
	public final long getLastModified(final Item item)
	{
		if(isNull(item))
			return -1;

		final long result = data.getLastModified(item);
		if(result<=0)
			throw newNoDataException(item);

		return result;
	}

	/**
	 * Reads data of this media
	 * and writes it into the given file.
	 * Does nothing, if there is no data for this media.
	 * @throws NullPointerException
	 *         if data is null.
	 * @throws IOException if writing data throws an IOException.
	 */
	public final void getData(final Item item, final File data) throws IOException
	{
		this.data.get(item, data);
	}

	/**
	 * Returns a URL pointing to the data of this media.
	 * Returns null, if there is no data for this media.
	 */
	public final String getURL(final Item item)
	{
		if(isNull(item))
			return null;

		final StringBuffer bf = new StringBuffer(getMediaRootUrl());
		appendDataPath(item, bf);
		appendExtension(item, bf);
		return bf.toString();
	}

	/**
	 * Provides data for this persistent media.
	 * Closes <data>data</data> after reading the contents of the stream.
	 * @param data give null to remove data.
	 * @throws MandatoryViolationException
	 *         if data is null and attribute is {@link Attribute#isMandatory() mandatory}.
	 * @throws IOException if reading data throws an IOException.
	 */
	public final void set(final Item item, final InputStream data, final String mimeMajor, final String mimeMinor)
		throws IOException
	{
		try
		{
			set(item, (Object)data, mimeMajor, mimeMinor);
		}
		finally
		{
			if(data!=null)
				data.close();
		}
	}
	
	/**
	 * Provides data for this persistent media.
	 * @param data give null to remove data.
	 * @throws MandatoryViolationException
	 *         if data is null and attribute is {@link Attribute#isMandatory() mandatory}.
	 * @throws IOException if reading data throws an IOException.
	 */
	public final void set(final Item item, final File data, final String mimeMajor, final String mimeMinor)
		throws IOException
	{
		set(item, (Object)data, mimeMajor, mimeMinor);
	}
	
	private final void set(final Item item, final Object data, final String mimeMajor, final String mimeMinor)
		throws IOException
	{
		try
		{
			if(data!=null)
			{
				if((mimeMajor==null&&contentType.getFixedMimeMajor()==null) ||
					(mimeMinor==null&&contentType.getFixedMimeMinor()==null))
					throw new RuntimeException("if data is not null, mime types must also be not null");
			}
			else
			{
				if(mimeMajor!=null||mimeMinor!=null)
					throw new RuntimeException("if data is null, mime types must also be null");
			}
	
			final ArrayList values = new ArrayList(3);
			contentType.map(values, mimeMajor, mimeMinor);
			if(this.exists!=null)
				values.add(this.exists.map((data!=null) ? Boolean.TRUE : null));
			item.set((AttributeValue[])values.toArray(new AttributeValue[values.size()]));
			
			// TODO set this via Item.set(AttributeValue[]) as well
			if(data instanceof InputStream)
				this.data.set(item, (InputStream)data);
			else
				this.data.set(item, (File)data);
	
		}
		catch(ConstraintViolationException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	private final void appendDataPath(final Item item, final StringBuffer bf)
	{
		final String id = item.getCopeID();
		final int dot = id.indexOf('.');
		if(dot<0)
			throw new RuntimeException(id);
		
		bf.append(getUrlPath()).
			append(id.substring(dot+1));
	}

	private final void appendExtension(final Item item, final StringBuffer bf)
	{
		final String contentType = getContentType(item);

		final String compactExtension = getCompactExtension(contentType);
		if(compactExtension==null)
		{
			bf.append('.').
				append(contentType.replace('/', '.'));
		}
		else
			bf.append(compactExtension);
	}
	
	private static final HashMap compactExtensions = new HashMap();
	
	static
	{
		compactExtensions.put("image/jpeg", ".jpg");
		compactExtensions.put("image/pjpeg", ".jpg");
		compactExtensions.put("image/gif", ".gif");
		compactExtensions.put("image/png", ".png");
		compactExtensions.put("text/html", ".html");
		compactExtensions.put("text/plain", ".txt");
		compactExtensions.put("text/css", ".css");
	}

	private static final String getCompactExtension(final String contentType)
	{
		return (String)compactExtensions.get(contentType);
	}
	
	public final static Media get(final DataAttribute attribute)
	{
		for(Iterator i = attribute.getPatterns().iterator(); i.hasNext(); )
		{
			final Pattern pattern = (Pattern)i.next();
			if(pattern instanceof Media)
			{
				final Media media = (Media)pattern;
				if(media.getData()==attribute)
					return media;
			}
		}
		throw new NullPointerException(attribute.toString());
	}
	
	// logs --------------------------
	
	private long start = System.currentTimeMillis();
	
	public final Date getStart()
	{
		return new Date(start);
	}

	// /logs -------------------------

	
	private static final String REQUEST_IF_MODIFIED_SINCE = "If-Modified-Since";
	private static final String RESPONSE_EXPIRES = "Expires";
	private static final String RESPONSE_LAST_MODIFIED = "Last-Modified";
	
	public final Media.Log doGet(
			final HttpServletRequest request, final HttpServletResponse response,
			final Item item, final String extension)
		throws ServletException, IOException
	{
		final String contentType = getContentType(item);
		//System.out.println("contentType="+contentType);
		if(contentType==null)
			return dataIsNull;

		response.setContentType(contentType);

		// NOTE:
		//
		// Last Modification Date must be rounded to full seconds,
		// otherwise comparison for SC_NOT_MODIFIED doesn't work
		// correctly on systems, where file timestamps
		// have a resolution more precise than seconds.
		final long lastModified = (getLastModified(item) / 1000) * 1000;
		//System.out.println("lastModified="+lastModified+"("+getLastModified(item)+")");
		response.setDateHeader(RESPONSE_LAST_MODIFIED, lastModified);

		final int mediaOffsetExpires = getType().getModel().getProperties().getMediaOffsetExpires();
		if(mediaOffsetExpires>0)
		{
			final long now = System.currentTimeMillis();
			response.setDateHeader(RESPONSE_EXPIRES, now+mediaOffsetExpires);
		}
		
		final long ifModifiedSince = request.getDateHeader(REQUEST_IF_MODIFIED_SINCE);
		//System.out.println("ifModifiedSince="+request.getHeader(REQUEST_IF_MODIFIED_SINCE));
		//System.out.println("ifModifiedSince="+ifModifiedSince);
		
		if(ifModifiedSince>=0 && ifModifiedSince>=lastModified)
		{
			//System.out.println("not modified");
			response.setStatus(response.SC_NOT_MODIFIED);
			
			System.out.println(request.getMethod()+' '+request.getProtocol()+" IMS="+format(ifModifiedSince)+"  LM="+format(lastModified)+"  NOT modified");
			
			return notModified;
		}
		else
		{
			final long contentLength = getLength(item);
			//System.out.println("contentLength="+String.valueOf(contentLength));
			response.setContentLength((int)contentLength);
			//response.setHeader("Cache-Control", "public");

			System.out.println(request.getMethod()+' '+request.getProtocol()+" IMS="+format(ifModifiedSince)+"  LM="+format(lastModified)+"  modified: "+contentLength);

			ServletOutputStream out = null;
			InputStream in = null;
			try
			{
				out = response.getOutputStream();
				in = getData(item);

				final byte[] buffer = new byte[Math.max((int)contentLength, 50*1024)];
				for(int len = in.read(buffer); len != -1; len = in.read(buffer))
					out.write(buffer, 0, len);

				return delivered;
			}
			finally
			{
				if(in!=null)
					in.close();
				if(out!=null)
					out.close();
			}
		}
	}
	
	abstract class ContentType
	{
		abstract String getFixedMimeMajor();
		abstract String getFixedMimeMinor();
		abstract StringAttribute getMimeMajor();
		abstract StringAttribute getMimeMinor();
		abstract void initialize(String name);
		abstract String getContentType(Item item);
		abstract void map(ArrayList values, String mimeMajor, String mimeMinor);
	}
	
	final class FixedContentType extends ContentType
	{
		// TODO save one string
		final String mimeMajor;
		final String mimeMinor;
		
		FixedContentType(final String mimeMajor, final String mimeMinor)
		{
			this.mimeMajor = mimeMajor;
			this.mimeMinor = mimeMinor;
			
			if(mimeMajor==null)
				throw new NullPointerException("fixedMimeMajor must not be null");
			if(mimeMajor==null)
				throw new NullPointerException("fixedMimeMinor must not be null");
		}
		
		String getFixedMimeMajor()
		{
			return mimeMajor;
		}
		
		String getFixedMimeMinor()
		{
			return mimeMinor;
		}
		
		StringAttribute getMimeMajor()
		{
			return null;
		}
		
		StringAttribute getMimeMinor()
		{
			return null;
		}
		
		void initialize(final String name)
		{
		}
		
		String getContentType(final Item item)
		{
			return mimeMajor + '/' + mimeMinor;
		}
		
		void map(final ArrayList values, final String mimeMajor, final String mimeMinor)
		{
		}
	}

	final class HalfFixedContentType extends ContentType
	{
		// TODO save string with slash
		final String mimeMajor;
		final StringAttribute mimeMinor;
		
		HalfFixedContentType(final String mimeMajor, final StringAttribute mimeMinor)
		{
			this.mimeMajor = mimeMajor;
			this.mimeMinor = mimeMinor;
			
			if(mimeMajor==null)
				throw new NullPointerException("fixedMimeMajor must not be null");
			if(mimeMajor==null)
				throw new NullPointerException("mimeMinor must not be null");
			
			registerSource(this.mimeMinor);
		}
		
		String getFixedMimeMajor()
		{
			return mimeMajor;
		}
		
		String getFixedMimeMinor()
		{
			return null;
		}
		
		StringAttribute getMimeMajor()
		{
			return null;
		}
		
		StringAttribute getMimeMinor()
		{
			return mimeMinor;
		}
		
		void initialize(final String name)
		{
			if(!mimeMinor.isInitialized())
				Media.this.initialize(mimeMinor, name+"Minor");
		}
		
		String getContentType(final Item item)
		{
			return mimeMajor + '/' + mimeMinor.get(item);
		}
		
		void map(final ArrayList values, final String mimeMajor, final String mimeMinor)
		{
			values.add(this.mimeMinor.map(mimeMinor));
		}
	}

	final class StoredContentType extends ContentType
	{
		final StringAttribute mimeMajor;
		final StringAttribute mimeMinor;
		
		StoredContentType(final StringAttribute mimeMajor, final StringAttribute mimeMinor)
		{
			this.mimeMajor = mimeMajor;
			this.mimeMinor = mimeMinor;
			
			if(mimeMajor==null)
				throw new NullPointerException("mimeMajor must not be null");
			if(mimeMajor==null)
				throw new NullPointerException("mimeMinor must not be null");

			registerSource(this.mimeMajor);
			registerSource(this.mimeMinor);
		}
		
		String getFixedMimeMajor()
		{
			return null;
		}
		
		String getFixedMimeMinor()
		{
			return null;
		}
		
		StringAttribute getMimeMajor()
		{
			return mimeMajor;
		}
		
		StringAttribute getMimeMinor()
		{
			return mimeMinor;
		}
		
		void initialize(final String name)
		{
			if(!mimeMajor.isInitialized())
				Media.this.initialize(mimeMajor, name+"Major");
			if(!mimeMinor.isInitialized())
				Media.this.initialize(mimeMinor, name+"Minor");
		}
		
		String getContentType(final Item item)
		{
			return  mimeMajor.get(item) + '/' + mimeMinor.get(item);
		}
		
		void map(final ArrayList values, final String mimeMajor, final String mimeMinor)
		{
			values.add(this.mimeMajor.map(mimeMajor));
			values.add(this.mimeMinor.map(mimeMinor));
		}
	}
	

	private final static String format(final long date)
	{
		final SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.S");
		return df.format(new Date(date));
	}
	
}
