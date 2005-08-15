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

import com.exedio.cope.DataAttribute;
import com.exedio.cope.Item;

/**
 * @cope.persistent
 * @author Ralf Wiebicke
 */
public class DataItem extends Item
{
	public static final DataAttribute file = dataAttribute(DEFAULT);

	public static final DataAttribute image = dataAttribute(DEFAULT, "image");
	
	public static final DataAttribute photo = dataAttribute(DEFAULT, "image", "jpeg");
	
/**

	 **
	 * Creates a new DataItem with all the attributes initially needed.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public DataItem()
	{
		this(new com.exedio.cope.AttributeValue[]{
		});
	}/**

	 **
	 * Creates a new DataItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */private DataItem(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 *
 */private DataItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns a URL the data of the data attribute {@link #file} is available under.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getFileURL()
	{
		return getURL(DataItem.file);
	}/**

	 **
	 * Returns the major mime type of the data attribute {@link #file}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getFileMimeMajor()
	{
		return getMimeMajor(DataItem.file);
	}/**

	 **
	 * Returns the minor mime type of the data attribute {@link #file}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getFileMimeMinor()
	{
		return getMimeMinor(DataItem.file);
	}/**

	 **
	 * Returns the data of the data attribute {@link #file}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.io.InputStream getFileData()
	{
		return getData(DataItem.file);
	}/**

	 **
	 * Sets the new data for the data attribute {@link #file}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 * @throws java.io.IOException if accessing <code>data</code> throws an IOException.
	 *
 */public final void setFile(final java.io.InputStream data,final java.lang.String mimeMajor,final java.lang.String mimeMinor)throws java.io.IOException
	{
		try
		{
			set(DataItem.file,data,mimeMajor,mimeMinor);
		}
		catch(com.exedio.cope.NotNullViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns a URL the data of the data attribute {@link #image} is available under.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getImageURL()
	{
		return getURL(DataItem.image);
	}/**

	 **
	 * Returns the major mime type of the data attribute {@link #image}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getImageMimeMajor()
	{
		return getMimeMajor(DataItem.image);
	}/**

	 **
	 * Returns the minor mime type of the data attribute {@link #image}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getImageMimeMinor()
	{
		return getMimeMinor(DataItem.image);
	}/**

	 **
	 * Returns the data of the data attribute {@link #image}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.io.InputStream getImageData()
	{
		return getData(DataItem.image);
	}/**

	 **
	 * Sets the new data for the data attribute {@link #image}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 * @throws java.io.IOException if accessing <code>data</code> throws an IOException.
	 *
 */public final void setImage(final java.io.InputStream data,final java.lang.String mimeMinor)throws java.io.IOException
	{
		try
		{
			set(DataItem.image,data,null,mimeMinor);
		}
		catch(com.exedio.cope.NotNullViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns a URL the data of the data attribute {@link #photo} is available under.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getPhotoURL()
	{
		return getURL(DataItem.photo);
	}/**

	 **
	 * Returns the major mime type of the data attribute {@link #photo}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getPhotoMimeMajor()
	{
		return getMimeMajor(DataItem.photo);
	}/**

	 **
	 * Returns the minor mime type of the data attribute {@link #photo}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getPhotoMimeMinor()
	{
		return getMimeMinor(DataItem.photo);
	}/**

	 **
	 * Returns the data of the data attribute {@link #photo}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.io.InputStream getPhotoData()
	{
		return getData(DataItem.photo);
	}/**

	 **
	 * Sets the new data for the data attribute {@link #photo}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 * @throws java.io.IOException if accessing <code>data</code> throws an IOException.
	 *
 */public final void setPhoto(final java.io.InputStream data)throws java.io.IOException
	{
		try
		{
			set(DataItem.photo,data,null,null);
		}
		catch(com.exedio.cope.NotNullViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
	}/**

	 **
	 * The persistent type information for dataItem.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public static final com.exedio.cope.Type TYPE = 
		new com.exedio.cope.Type(DataItem.class)
;}
