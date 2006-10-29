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

import com.exedio.cope.Item;
import com.exedio.cope.StringField;
import com.exedio.cope.pattern.Media;
import com.exedio.cope.pattern.MediaRedirect;

public class MediaItem extends Item
{
	
	public static final StringField name = new StringField(OPTIONAL);

	public static final Media file = new Media(OPTIONAL).lengthMax(20);

	public static final Media image = new Media(OPTIONAL, "image");
	
	public static final Media photo = new Media(OPTIONAL, "image", "jpeg").lengthMax(2000);

	public static final MediaRedirect foto = new MediaRedirect(photo);

	public static final MediaNameServer nameServer = new MediaNameServer(name);
	
	
	public MediaItem(final String name)
	{
		this(new com.exedio.cope.SetValue[]{
			MediaItem.name.map(name),
		});
	}
	
	/**

	 **
	 * Creates a new MediaItem with all the fields initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	public MediaItem()
	{
		this(new com.exedio.cope.SetValue[]{
		});
	}/**

	 **
	 * Creates a new MediaItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private MediaItem(final com.exedio.cope.SetValue[] setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private MediaItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent field {@link #name}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	public final java.lang.String getName()
	{
		return MediaItem.name.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #name}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setName(final java.lang.String name)
			throws
				com.exedio.cope.LengthViolationException
	{
		MediaItem.name.set(this,name);
	}/**

	 **
	 * Returns whether media {@link #file} is null.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final boolean isFileNull()
	{
		return MediaItem.file.isNull(this);
	}/**

	 **
	 * Returns a URL the content of the media {@link #file} is available under.
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
	 * Returns the last modification date of media {@link #file}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final long getFileLastModified()
	{
		return MediaItem.file.getLastModified(this);
	}/**

	 **
	 * Returns the body length of the media {@link #file}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final long getFileLength()
	{
		return MediaItem.file.getLength(this);
	}/**

	 **
	 * Returns the body of the media {@link #file}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final byte[] getFileBody()
	{
		return MediaItem.file.getBody(this);
	}/**

	 **
	 * Writes the body of media {@link #file} into the given stream.
	 * Does nothing, if the media is null.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void getFileBody(final java.io.OutputStream body)
			throws
				java.io.IOException
	{
		MediaItem.file.getBody(this,body);
	}/**

	 **
	 * Writes the body of media {@link #file} into the given file.
	 * Does nothing, if the media is null.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void getFileBody(final java.io.File body)
			throws
				java.io.IOException
	{
		MediaItem.file.getBody(this,body);
	}/**

	 **
	 * Sets the content of media {@link #file}.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setFile(final byte[] body,final java.lang.String contentType)
			throws
				java.io.IOException
	{
		MediaItem.file.set(this,body,contentType);
	}/**

	 **
	 * Sets the content of media {@link #file}.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setFile(final java.io.InputStream body,final java.lang.String contentType)
			throws
				java.io.IOException
	{
		MediaItem.file.set(this,body,contentType);
	}/**

	 **
	 * Sets the content of media {@link #file}.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setFile(final java.io.File body,final java.lang.String contentType)
			throws
				java.io.IOException
	{
		MediaItem.file.set(this,body,contentType);
	}/**

	 **
	 * Returns whether media {@link #image} is null.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final boolean isImageNull()
	{
		return MediaItem.image.isNull(this);
	}/**

	 **
	 * Returns a URL the content of the media {@link #image} is available under.
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
	 * Returns the last modification date of media {@link #image}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final long getImageLastModified()
	{
		return MediaItem.image.getLastModified(this);
	}/**

	 **
	 * Returns the body length of the media {@link #image}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final long getImageLength()
	{
		return MediaItem.image.getLength(this);
	}/**

	 **
	 * Returns the body of the media {@link #image}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final byte[] getImageBody()
	{
		return MediaItem.image.getBody(this);
	}/**

	 **
	 * Writes the body of media {@link #image} into the given stream.
	 * Does nothing, if the media is null.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void getImageBody(final java.io.OutputStream body)
			throws
				java.io.IOException
	{
		MediaItem.image.getBody(this,body);
	}/**

	 **
	 * Writes the body of media {@link #image} into the given file.
	 * Does nothing, if the media is null.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void getImageBody(final java.io.File body)
			throws
				java.io.IOException
	{
		MediaItem.image.getBody(this,body);
	}/**

	 **
	 * Sets the content of media {@link #image}.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setImage(final byte[] body,final java.lang.String contentType)
			throws
				java.io.IOException
	{
		MediaItem.image.set(this,body,contentType);
	}/**

	 **
	 * Sets the content of media {@link #image}.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setImage(final java.io.InputStream body,final java.lang.String contentType)
			throws
				java.io.IOException
	{
		MediaItem.image.set(this,body,contentType);
	}/**

	 **
	 * Sets the content of media {@link #image}.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setImage(final java.io.File body,final java.lang.String contentType)
			throws
				java.io.IOException
	{
		MediaItem.image.set(this,body,contentType);
	}/**

	 **
	 * Returns whether media {@link #photo} is null.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final boolean isPhotoNull()
	{
		return MediaItem.photo.isNull(this);
	}/**

	 **
	 * Returns a URL the content of the media {@link #photo} is available under.
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
	 * Returns the last modification date of media {@link #photo}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final long getPhotoLastModified()
	{
		return MediaItem.photo.getLastModified(this);
	}/**

	 **
	 * Returns the body length of the media {@link #photo}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final long getPhotoLength()
	{
		return MediaItem.photo.getLength(this);
	}/**

	 **
	 * Returns the body of the media {@link #photo}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final byte[] getPhotoBody()
	{
		return MediaItem.photo.getBody(this);
	}/**

	 **
	 * Writes the body of media {@link #photo} into the given stream.
	 * Does nothing, if the media is null.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void getPhotoBody(final java.io.OutputStream body)
			throws
				java.io.IOException
	{
		MediaItem.photo.getBody(this,body);
	}/**

	 **
	 * Writes the body of media {@link #photo} into the given file.
	 * Does nothing, if the media is null.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void getPhotoBody(final java.io.File body)
			throws
				java.io.IOException
	{
		MediaItem.photo.getBody(this,body);
	}/**

	 **
	 * Sets the content of media {@link #photo}.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setPhoto(final byte[] body,final java.lang.String contentType)
			throws
				java.io.IOException
	{
		MediaItem.photo.set(this,body,contentType);
	}/**

	 **
	 * Sets the content of media {@link #photo}.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setPhoto(final java.io.InputStream body,final java.lang.String contentType)
			throws
				java.io.IOException
	{
		MediaItem.photo.set(this,body,contentType);
	}/**

	 **
	 * Sets the content of media {@link #photo}.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setPhoto(final java.io.File body,final java.lang.String contentType)
			throws
				java.io.IOException
	{
		MediaItem.photo.set(this,body,contentType);
	}/**

	 **
	 * The persistent type information for mediaItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<MediaItem> TYPE = newType(MediaItem.class)
;}
