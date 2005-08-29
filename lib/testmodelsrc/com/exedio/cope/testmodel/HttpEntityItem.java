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

import com.exedio.cope.Item;
import com.exedio.cope.pattern.Media;
import com.exedio.cope.pattern.HttpRedirect;

/**
 * @cope.persistent
 * @author Ralf Wiebicke
 */
public class HttpEntityItem extends Item
{

	public static final Media file = new Media(OPTIONAL);

	public static final Media image = new Media(OPTIONAL, "image");
	
	public static final Media photo = new Media(OPTIONAL, "image", "jpeg");

	public static final HttpRedirect foto = new HttpRedirect(photo);
	
/**

	 **
	 * Creates a new HttpEntityItem with all the attributes initially needed.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public HttpEntityItem()
	{
		this(new com.exedio.cope.AttributeValue[]{
		});
	}/**

	 **
	 * Creates a new HttpEntityItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */private HttpEntityItem(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 *
 */private HttpEntityItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns whether this http entity {@link #file} has data available.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final boolean isFileNull()
	{
		return HttpEntityItem.file.isNull(this);
	}/**

	 **
	 * Returns a URL the data of the http entity {@link #file} is available under.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getFileURL()
	{
		return HttpEntityItem.file.getURL(this);
	}/**

	 **
	 * Returns the major mime type of the http entity {@link #file}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getFileMimeMajor()
	{
		return HttpEntityItem.file.getMimeMajor(this);
	}/**

	 **
	 * Returns the minor mime type of the http entity {@link #file}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getFileMimeMinor()
	{
		return HttpEntityItem.file.getMimeMinor(this);
	}/**

	 **
	 * Returns the content type of the http entity {@link #file}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getFileContentType()
	{
		return HttpEntityItem.file.getContentType(this);
	}/**

	 **
	 * Returns the data of the http entity {@link #file}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.io.InputStream getFileData()
	{
		return HttpEntityItem.file.getData(this);
	}/**

	 **
	 * Sets the new data for the http entity {@link #file}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 * @throws java.io.IOException if accessing <code>data</code> throws an IOException.
	 *
 */public final void setFile(final java.io.InputStream data,final java.lang.String mimeMajor,final java.lang.String mimeMinor)
			throws
				java.io.IOException
	{
		HttpEntityItem.file.set(this,data,mimeMajor,mimeMinor);
	}/**

	 **
	 * Returns whether this http entity {@link #image} has data available.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final boolean isImageNull()
	{
		return HttpEntityItem.image.isNull(this);
	}/**

	 **
	 * Returns a URL the data of the http entity {@link #image} is available under.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getImageURL()
	{
		return HttpEntityItem.image.getURL(this);
	}/**

	 **
	 * Returns the major mime type of the http entity {@link #image}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getImageMimeMajor()
	{
		return HttpEntityItem.image.getMimeMajor(this);
	}/**

	 **
	 * Returns the minor mime type of the http entity {@link #image}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getImageMimeMinor()
	{
		return HttpEntityItem.image.getMimeMinor(this);
	}/**

	 **
	 * Returns the content type of the http entity {@link #image}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getImageContentType()
	{
		return HttpEntityItem.image.getContentType(this);
	}/**

	 **
	 * Returns the data of the http entity {@link #image}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.io.InputStream getImageData()
	{
		return HttpEntityItem.image.getData(this);
	}/**

	 **
	 * Sets the new data for the http entity {@link #image}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 * @throws java.io.IOException if accessing <code>data</code> throws an IOException.
	 *
 */public final void setImage(final java.io.InputStream data,final java.lang.String mimeMinor)
			throws
				java.io.IOException
	{
		HttpEntityItem.image.set(this,data,null,mimeMinor);
	}/**

	 **
	 * Returns whether this http entity {@link #photo} has data available.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final boolean isPhotoNull()
	{
		return HttpEntityItem.photo.isNull(this);
	}/**

	 **
	 * Returns a URL the data of the http entity {@link #photo} is available under.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getPhotoURL()
	{
		return HttpEntityItem.photo.getURL(this);
	}/**

	 **
	 * Returns the major mime type of the http entity {@link #photo}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getPhotoMimeMajor()
	{
		return HttpEntityItem.photo.getMimeMajor(this);
	}/**

	 **
	 * Returns the minor mime type of the http entity {@link #photo}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getPhotoMimeMinor()
	{
		return HttpEntityItem.photo.getMimeMinor(this);
	}/**

	 **
	 * Returns the content type of the http entity {@link #photo}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getPhotoContentType()
	{
		return HttpEntityItem.photo.getContentType(this);
	}/**

	 **
	 * Returns the data of the http entity {@link #photo}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.io.InputStream getPhotoData()
	{
		return HttpEntityItem.photo.getData(this);
	}/**

	 **
	 * Sets the new data for the http entity {@link #photo}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 * @throws java.io.IOException if accessing <code>data</code> throws an IOException.
	 *
 */public final void setPhoto(final java.io.InputStream data)
			throws
				java.io.IOException
	{
		HttpEntityItem.photo.set(this,data,null,null);
	}/**

	 **
	 * The persistent type information for httpEntityItem.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(HttpEntityItem.class)
;}
