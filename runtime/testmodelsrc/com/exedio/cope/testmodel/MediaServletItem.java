/*
 * Copyright (C) 2004-2007  exedio GmbH (www.exedio.com)
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
import com.exedio.cope.StringField;
import com.exedio.cope.pattern.Media;
import com.exedio.cope.pattern.MediaImageMagickThumbnail;
import com.exedio.cope.pattern.MediaPnmThumbnail;
import com.exedio.cope.pattern.MediaRedirect;
import com.exedio.cope.pattern.MediaThumbnail;

public class MediaServletItem extends Item
{
	/**
	 * @cope.initial
	 */
	public static final StringField name = new StringField(OPTIONAL);

	public static final Media content = new Media(OPTIONAL);

	public static final MediaRedirect redirect = new MediaRedirect(content);

	public static final MediaThumbnail thumbnail = new MediaThumbnail(content, 150, 150);

	public static final MediaImageMagickThumbnail thumbnailMagick = new MediaImageMagickThumbnail(content, 150, 150);

	public static final MediaPnmThumbnail thumbnailPnm = new MediaPnmThumbnail(content, 150, 150);
	
	public static final MediaNameServer nameServer = new MediaNameServer(name);
	
	
	public MediaServletItem()
	{
		this((String)null);
	}
	
	/**

	 **
	 * Creates a new MediaServletItem with all the fields initially needed.
	 * @param name the initial value for field {@link #name}.
	 * @throws com.exedio.cope.LengthViolationException if name violates its length constraint.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	public MediaServletItem(
				final java.lang.String name)
			throws
				com.exedio.cope.LengthViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			MediaServletItem.name.map(name),
		});
	}/**

	 **
	 * Creates a new MediaServletItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private MediaServletItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private MediaServletItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
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
		return MediaServletItem.name.get(this);
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
		MediaServletItem.name.set(this,name);
	}/**

	 **
	 * Returns whether media {@link #content} is null.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final boolean isContentNull()
	{
		return MediaServletItem.content.isNull(this);
	}/**

	 **
	 * Returns a URL the content of the media {@link #content} is available under.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.lang.String getContentURL()
	{
		return MediaServletItem.content.getURL(this);
	}/**

	 **
	 * Returns the content type of the media {@link #content}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final java.lang.String getContentContentType()
	{
		return MediaServletItem.content.getContentType(this);
	}/**

	 **
	 * Returns the last modification date of media {@link #content}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final long getContentLastModified()
	{
		return MediaServletItem.content.getLastModified(this);
	}/**

	 **
	 * Returns the body length of the media {@link #content}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final long getContentLength()
	{
		return MediaServletItem.content.getLength(this);
	}/**

	 **
	 * Returns the body of the media {@link #content}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final byte[] getContentBody()
	{
		return MediaServletItem.content.getBody(this);
	}/**

	 **
	 * Writes the body of media {@link #content} into the given stream.
	 * Does nothing, if the media is null.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void getContentBody(final java.io.OutputStream body)
			throws
				java.io.IOException
	{
		MediaServletItem.content.getBody(this,body);
	}/**

	 **
	 * Writes the body of media {@link #content} into the given file.
	 * Does nothing, if the media is null.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void getContentBody(final java.io.File body)
			throws
				java.io.IOException
	{
		MediaServletItem.content.getBody(this,body);
	}/**

	 **
	 * Sets the content of media {@link #content}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setContent(final byte[] body,final java.lang.String contentType)
	{
		MediaServletItem.content.set(this,body,contentType);
	}/**

	 **
	 * Sets the content of media {@link #content}.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setContent(final java.io.InputStream body,final java.lang.String contentType)
			throws
				java.io.IOException
	{
		MediaServletItem.content.set(this,body,contentType);
	}/**

	 **
	 * Sets the content of media {@link #content}.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	public final void setContent(final java.io.File body,final java.lang.String contentType)
			throws
				java.io.IOException
	{
		MediaServletItem.content.set(this,body,contentType);
	}/**

	 **
	 * The persistent type information for mediaServletItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<MediaServletItem> TYPE = newType(MediaServletItem.class)
;}
