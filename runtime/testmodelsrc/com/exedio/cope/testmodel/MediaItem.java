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
import com.exedio.cope.StringAttribute;
import com.exedio.cope.pattern.Media;
import com.exedio.cope.pattern.MediaRedirect;

/**
 * @cope.persistent
 * @author Ralf Wiebicke
 */
public class MediaItem extends Item
{
	
	public static final StringAttribute name = new StringAttribute(OPTIONAL);

	public static final Media file = new Media(OPTIONAL).lengthMax(20);

	public static final Media image = new Media(OPTIONAL, "image");
	
	public static final Media photo = new Media(OPTIONAL, "image", "jpeg").lengthMax(2000);

	public static final MediaRedirect foto = new MediaRedirect(photo);
	
	public static final MediaNameServer nameServer = new MediaNameServer(name);
	
	
	public MediaItem(final String name)
	{
		this(new com.exedio.cope.AttributeValue[]{
			new com.exedio.cope.AttributeValue(MediaItem.name,name),
		});
	}
	
/**

	 **
	 * Creates a new MediaItem with all the attributes initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <code>@cope.constructor public|package|protected|private|none</code> in the class comment and <code>@cope.initial</code> in the comment of attributes.
	 */
	public MediaItem()
	{
		this(new com.exedio.cope.AttributeValue[]{
		});
	}/**

	 **
	 * Creates a new MediaItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.generic.constructor public|package|protected|private|none</code> in the class comment.
	 */
	private MediaItem(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private MediaItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #name}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.String getName()
	{
		return MediaItem.name.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #name}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setName(final java.lang.String name)
	{
		try
		{
			MediaItem.name.set(this,name);
		}
		catch(com.exedio.cope.FinalViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.MandatoryViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
	}/**

	 **
	 * Returns whether this media {@link #file} has data available.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final boolean isFileNull()
	{
		return MediaItem.file.isNull(this);
	}/**

	 **
	 * Returns a URL the data of the media {@link #file} is available under.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.lang.String getFileURL()
	{
		return MediaItem.file.getURL(this);
	}/**

	 **
	 * Returns the content type of the media {@link #file}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.lang.String getFileContentType()
	{
		return MediaItem.file.getContentType(this);
	}/**

	 **
	 * Returns the last modification date of the media {@link #file}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final long getFileLastModified()
	{
		return MediaItem.file.getLastModified(this);
	}/**

	 **
	 * Returns the data length of the media {@link #file}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final long getFileLength()
	{
		return MediaItem.file.getLength(this);
	}/**

	 **
	 * Returns the data of the media {@link #file}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final byte[] getFileData()
	{
		return MediaItem.file.getData(this);
	}/**

	 **
	 * Reads data of media {@link #file}, and writes it into the given stream.
	 * Does nothing, if there is no data for the media.
	 * @throws java.io.IOException if accessing <code>data</code> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void getFileData(final java.io.OutputStream data)
			throws
				java.io.IOException
	{
		MediaItem.file.getData(this,data);
	}/**

	 **
	 * Reads data of media {@link #file}, and writes it into the given file.
	 * Does nothing, if there is no data for the media.
	 * @throws java.io.IOException if accessing <code>data</code> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void getFileData(final java.io.File data)
			throws
				java.io.IOException
	{
		MediaItem.file.getData(this,data);
	}/**

	 **
	 * Sets the new data for the media {@link #file}.
	 * @throws java.io.IOException if accessing <code>data</code> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setFile(final byte[] data,final java.lang.String contentType)
			throws
				java.io.IOException
	{
		MediaItem.file.set(this,data,contentType);
	}/**

	 **
	 * Sets the new data for the media {@link #file}.
	 * @throws java.io.IOException if accessing <code>data</code> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setFile(final java.io.InputStream data,final java.lang.String contentType)
			throws
				java.io.IOException
	{
		MediaItem.file.set(this,data,contentType);
	}/**

	 **
	 * Sets the new data for the media {@link #file}.
	 * @throws java.io.IOException if accessing <code>data</code> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setFile(final java.io.File data,final java.lang.String contentType)
			throws
				java.io.IOException
	{
		MediaItem.file.set(this,data,contentType);
	}/**

	 **
	 * Returns whether this media {@link #image} has data available.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final boolean isImageNull()
	{
		return MediaItem.image.isNull(this);
	}/**

	 **
	 * Returns a URL the data of the media {@link #image} is available under.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.lang.String getImageURL()
	{
		return MediaItem.image.getURL(this);
	}/**

	 **
	 * Returns the content type of the media {@link #image}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.lang.String getImageContentType()
	{
		return MediaItem.image.getContentType(this);
	}/**

	 **
	 * Returns the last modification date of the media {@link #image}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final long getImageLastModified()
	{
		return MediaItem.image.getLastModified(this);
	}/**

	 **
	 * Returns the data length of the media {@link #image}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final long getImageLength()
	{
		return MediaItem.image.getLength(this);
	}/**

	 **
	 * Returns the data of the media {@link #image}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final byte[] getImageData()
	{
		return MediaItem.image.getData(this);
	}/**

	 **
	 * Reads data of media {@link #image}, and writes it into the given stream.
	 * Does nothing, if there is no data for the media.
	 * @throws java.io.IOException if accessing <code>data</code> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void getImageData(final java.io.OutputStream data)
			throws
				java.io.IOException
	{
		MediaItem.image.getData(this,data);
	}/**

	 **
	 * Reads data of media {@link #image}, and writes it into the given file.
	 * Does nothing, if there is no data for the media.
	 * @throws java.io.IOException if accessing <code>data</code> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void getImageData(final java.io.File data)
			throws
				java.io.IOException
	{
		MediaItem.image.getData(this,data);
	}/**

	 **
	 * Sets the new data for the media {@link #image}.
	 * @throws java.io.IOException if accessing <code>data</code> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setImage(final byte[] data,final java.lang.String contentType)
			throws
				java.io.IOException
	{
		MediaItem.image.set(this,data,contentType);
	}/**

	 **
	 * Sets the new data for the media {@link #image}.
	 * @throws java.io.IOException if accessing <code>data</code> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setImage(final java.io.InputStream data,final java.lang.String contentType)
			throws
				java.io.IOException
	{
		MediaItem.image.set(this,data,contentType);
	}/**

	 **
	 * Sets the new data for the media {@link #image}.
	 * @throws java.io.IOException if accessing <code>data</code> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setImage(final java.io.File data,final java.lang.String contentType)
			throws
				java.io.IOException
	{
		MediaItem.image.set(this,data,contentType);
	}/**

	 **
	 * Returns whether this media {@link #photo} has data available.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final boolean isPhotoNull()
	{
		return MediaItem.photo.isNull(this);
	}/**

	 **
	 * Returns a URL the data of the media {@link #photo} is available under.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.lang.String getPhotoURL()
	{
		return MediaItem.photo.getURL(this);
	}/**

	 **
	 * Returns the content type of the media {@link #photo}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.lang.String getPhotoContentType()
	{
		return MediaItem.photo.getContentType(this);
	}/**

	 **
	 * Returns the last modification date of the media {@link #photo}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final long getPhotoLastModified()
	{
		return MediaItem.photo.getLastModified(this);
	}/**

	 **
	 * Returns the data length of the media {@link #photo}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final long getPhotoLength()
	{
		return MediaItem.photo.getLength(this);
	}/**

	 **
	 * Returns the data of the media {@link #photo}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final byte[] getPhotoData()
	{
		return MediaItem.photo.getData(this);
	}/**

	 **
	 * Reads data of media {@link #photo}, and writes it into the given stream.
	 * Does nothing, if there is no data for the media.
	 * @throws java.io.IOException if accessing <code>data</code> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void getPhotoData(final java.io.OutputStream data)
			throws
				java.io.IOException
	{
		MediaItem.photo.getData(this,data);
	}/**

	 **
	 * Reads data of media {@link #photo}, and writes it into the given file.
	 * Does nothing, if there is no data for the media.
	 * @throws java.io.IOException if accessing <code>data</code> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void getPhotoData(final java.io.File data)
			throws
				java.io.IOException
	{
		MediaItem.photo.getData(this,data);
	}/**

	 **
	 * Sets the new data for the media {@link #photo}.
	 * @throws java.io.IOException if accessing <code>data</code> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setPhoto(final byte[] data,final java.lang.String contentType)
			throws
				java.io.IOException
	{
		MediaItem.photo.set(this,data,contentType);
	}/**

	 **
	 * Sets the new data for the media {@link #photo}.
	 * @throws java.io.IOException if accessing <code>data</code> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setPhoto(final java.io.InputStream data,final java.lang.String contentType)
			throws
				java.io.IOException
	{
		MediaItem.photo.set(this,data,contentType);
	}/**

	 **
	 * Sets the new data for the media {@link #photo}.
	 * @throws java.io.IOException if accessing <code>data</code> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setPhoto(final java.io.File data,final java.lang.String contentType)
			throws
				java.io.IOException
	{
		MediaItem.photo.set(this,data,contentType);
	}/**

	 **
	 * The persistent type information for mediaItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.type public|package|protected|private|none</code> in the class comment.
	 */
	public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(MediaItem.class)
;}
