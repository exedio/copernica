/*
 * Copyright (C) 2004-2008  exedio GmbH (www.exedio.com)
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

final class MediaServletItem extends Item
{
	/**
	 * @cope.initial
	 */
	static final StringField name = new StringField().optional();

	static final Media content = new Media().optional();

	static final MediaRedirect redirect = new MediaRedirect(content);

	static final MediaThumbnail thumbnail = new MediaThumbnail(content, 150, 150);

	static final MediaRedirect thumbnailRedirect = new MediaRedirect(thumbnail);
	
	static final MediaImageMagickThumbnail thumbnailMagick = new MediaImageMagickThumbnail(content, 150, 150);

	static final MediaImageMagickThumbnail thumbnailMagickPng = new MediaImageMagickThumbnail(content, 150, 150).outputContentType("image/png");

	static final MediaImageMagickThumbnail thumbnailMagickPngBlue = new MediaImageMagickThumbnail(content, 150, 150).outputContentType("image/png").flatten("blue");

	static final MediaNameServer nameServer = new MediaNameServer(name);
	
	
	MediaServletItem()
	{
		this((String)null);
	}
	
	/**

	 **
	 * Creates a new MediaServletItem with all the fields initially needed.
	 * @param name the initial value for field {@link #name}.
	 * @throws com.exedio.cope.StringLengthViolationException if name violates its length constraint.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	MediaServletItem(
				java.lang.String name)
			throws
				com.exedio.cope.StringLengthViolationException
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
	private MediaServletItem(com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@SuppressWarnings("unused") private MediaServletItem(com.exedio.cope.util.ReactivationConstructorDummy d,int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of {@link #name}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.get public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getName()
	{
		return MediaServletItem.name.get(this);
	}/**

	 **
	 * Sets a new value for {@link #name}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setName(java.lang.String name)
			throws
				com.exedio.cope.StringLengthViolationException
	{
		MediaServletItem.name.set(this,name);
	}/**

	 **
	 * Returns a URL the content of {@link #content} is available under.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getURL public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getContentURL()
	{
		return MediaServletItem.content.getURL(this);
	}/**

	 **
	 * Returns the content type of the media {@link #content}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getContentType public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getContentContentType()
	{
		return MediaServletItem.content.getContentType(this);
	}/**

	 **
	 * Returns whether media {@link #content} is null.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.isNull public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final boolean isContentNull()
	{
		return MediaServletItem.content.isNull(this);
	}/**

	 **
	 * Returns the last modification date of media {@link #content}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getLastModified public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final long getContentLastModified()
	{
		return MediaServletItem.content.getLastModified(this);
	}/**

	 **
	 * Returns the body length of the media {@link #content}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getLength public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final long getContentLength()
	{
		return MediaServletItem.content.getLength(this);
	}/**

	 **
	 * Returns the body of the media {@link #content}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getBody public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final byte[] getContentBody()
	{
		return MediaServletItem.content.getBody(this);
	}/**

	 **
	 * Writes the body of media {@link #content} into the given stream.
	 * Does nothing, if the media is null.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getBody public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void getContentBody(java.io.OutputStream body)
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
	 *       It can be customized with the tag <tt>@cope.getBody public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void getContentBody(java.io.File body)
			throws
				java.io.IOException
	{
		MediaServletItem.content.getBody(this,body);
	}/**

	 **
	 * Sets the content of media {@link #content}.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setContent(com.exedio.cope.pattern.Media.Value content)
			throws
				java.io.IOException
	{
		MediaServletItem.content.set(this,content);
	}/**

	 **
	 * Sets the content of media {@link #content}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setContent(byte[] body,java.lang.String contentType)
	{
		MediaServletItem.content.set(this,body,contentType);
	}/**

	 **
	 * Sets the content of media {@link #content}.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setContent(java.io.InputStream body,java.lang.String contentType)
			throws
				java.io.IOException
	{
		MediaServletItem.content.set(this,body,contentType);
	}/**

	 **
	 * Sets the content of media {@link #content}.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setContent(java.io.File body,java.lang.String contentType)
			throws
				java.io.IOException
	{
		MediaServletItem.content.set(this,body,contentType);
	}/**

	 **
	 * Returns a URL the content of {@link #redirect} is available under.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getURL public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getRedirectURL()
	{
		return MediaServletItem.redirect.getURL(this);
	}/**

	 **
	 * Returns the content type of the media {@link #redirect}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getContentType public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getRedirectContentType()
	{
		return MediaServletItem.redirect.getContentType(this);
	}/**

	 **
	 * Returns a URL the content of {@link #thumbnail} is available under.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getURL public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getThumbnailURL()
	{
		return MediaServletItem.thumbnail.getURL(this);
	}/**

	 **
	 * Returns the content type of the media {@link #thumbnail}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getContentType public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getThumbnailContentType()
	{
		return MediaServletItem.thumbnail.getContentType(this);
	}/**

	 **
	 * Returns a URL the content of {@link #thumbnail} is available under.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getURLWithFallbackToSource public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getThumbnailURLWithFallbackToSource()
	{
		return MediaServletItem.thumbnail.getURLWithFallbackToSource(this);
	}/**

	 **
	 * Returns a URL the content of {@link #thumbnailRedirect} is available under.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getURL public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getThumbnailRedirectURL()
	{
		return MediaServletItem.thumbnailRedirect.getURL(this);
	}/**

	 **
	 * Returns the content type of the media {@link #thumbnailRedirect}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getContentType public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getThumbnailRedirectContentType()
	{
		return MediaServletItem.thumbnailRedirect.getContentType(this);
	}/**

	 **
	 * Returns a URL the content of {@link #thumbnailMagick} is available under.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getURL public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getThumbnailMagickURL()
	{
		return MediaServletItem.thumbnailMagick.getURL(this);
	}/**

	 **
	 * Returns the content type of the media {@link #thumbnailMagick}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getContentType public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getThumbnailMagickContentType()
	{
		return MediaServletItem.thumbnailMagick.getContentType(this);
	}/**

	 **
	 * Returns a URL the content of {@link #thumbnailMagick} is available under.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getURLWithFallbackToSource public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getThumbnailMagickURLWithFallbackToSource()
	{
		return MediaServletItem.thumbnailMagick.getURLWithFallbackToSource(this);
	}/**

	 **
	 * Returns a URL the content of {@link #thumbnailMagickPng} is available under.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getURL public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getThumbnailMagickPngURL()
	{
		return MediaServletItem.thumbnailMagickPng.getURL(this);
	}/**

	 **
	 * Returns the content type of the media {@link #thumbnailMagickPng}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getContentType public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getThumbnailMagickPngContentType()
	{
		return MediaServletItem.thumbnailMagickPng.getContentType(this);
	}/**

	 **
	 * Returns a URL the content of {@link #thumbnailMagickPng} is available under.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getURLWithFallbackToSource public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getThumbnailMagickPngURLWithFallbackToSource()
	{
		return MediaServletItem.thumbnailMagickPng.getURLWithFallbackToSource(this);
	}/**

	 **
	 * Returns a URL the content of {@link #thumbnailMagickPngBlue} is available under.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getURL public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getThumbnailMagickPngBlueURL()
	{
		return MediaServletItem.thumbnailMagickPngBlue.getURL(this);
	}/**

	 **
	 * Returns the content type of the media {@link #thumbnailMagickPngBlue}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getContentType public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getThumbnailMagickPngBlueContentType()
	{
		return MediaServletItem.thumbnailMagickPngBlue.getContentType(this);
	}/**

	 **
	 * Returns a URL the content of {@link #thumbnailMagickPngBlue} is available under.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getURLWithFallbackToSource public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getThumbnailMagickPngBlueURLWithFallbackToSource()
	{
		return MediaServletItem.thumbnailMagickPngBlue.getURLWithFallbackToSource(this);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for mediaServletItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	static final com.exedio.cope.Type<MediaServletItem> TYPE = newType(MediaServletItem.class)
;}
