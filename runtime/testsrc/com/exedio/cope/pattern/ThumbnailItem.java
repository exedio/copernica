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

package com.exedio.cope.pattern;

import com.exedio.cope.Item;

public class ThumbnailItem extends Item
{
	
	static final Media file = new Media().optional().lengthMax(20);

	static final MediaThumbnail thumb = new MediaThumbnail(file, 20, 30);
	
	// TODO generate by instrumentor
	final String getThumbContentType()
	{
		return thumb.getContentType(this);
	}
	
	// TODO generate by instrumentor
	final String getThumbURL()
	{
		return thumb.getURL(this);
	}
	
	// TODO generate by instrumentor
	final String getThumbURLWithFallbackToSource()
	{
		return thumb.getURLWithFallbackToSource(this);
	}
	
	/**

	 **
	 * Creates a new ThumbnailItem with all the fields initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	public ThumbnailItem()
	{
		this(new com.exedio.cope.SetValue[]{
		});
	}/**

	 **
	 * Creates a new ThumbnailItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private ThumbnailItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private ThumbnailItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns whether media {@link #file} is null.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	final boolean isFileNull()
	{
		return ThumbnailItem.file.isNull(this);
	}/**

	 **
	 * Returns a URL the content of the media {@link #file} is available under.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	final java.lang.String getFileURL()
	{
		return ThumbnailItem.file.getURL(this);
	}/**

	 **
	 * Returns the content type of the media {@link #file}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	final java.lang.String getFileContentType()
	{
		return ThumbnailItem.file.getContentType(this);
	}/**

	 **
	 * Returns the last modification date of media {@link #file}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	final long getFileLastModified()
	{
		return ThumbnailItem.file.getLastModified(this);
	}/**

	 **
	 * Returns the body length of the media {@link #file}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	final long getFileLength()
	{
		return ThumbnailItem.file.getLength(this);
	}/**

	 **
	 * Returns the body of the media {@link #file}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	final byte[] getFileBody()
	{
		return ThumbnailItem.file.getBody(this);
	}/**

	 **
	 * Writes the body of media {@link #file} into the given stream.
	 * Does nothing, if the media is null.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	final void getFileBody(final java.io.OutputStream body)
			throws
				java.io.IOException
	{
		ThumbnailItem.file.getBody(this,body);
	}/**

	 **
	 * Writes the body of media {@link #file} into the given file.
	 * Does nothing, if the media is null.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	final void getFileBody(final java.io.File body)
			throws
				java.io.IOException
	{
		ThumbnailItem.file.getBody(this,body);
	}/**

	 **
	 * Sets the content of media {@link #file}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	final void setFile(final byte[] body,final java.lang.String contentType)
	{
		ThumbnailItem.file.set(this,body,contentType);
	}/**

	 **
	 * Sets the content of media {@link #file}.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	final void setFile(final java.io.InputStream body,final java.lang.String contentType)
			throws
				java.io.IOException
	{
		ThumbnailItem.file.set(this,body,contentType);
	}/**

	 **
	 * Sets the content of media {@link #file}.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	final void setFile(final java.io.File body,final java.lang.String contentType)
			throws
				java.io.IOException
	{
		ThumbnailItem.file.set(this,body,contentType);
	}/**

	 **
	 * The persistent type information for thumbnailItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<ThumbnailItem> TYPE = newType(ThumbnailItem.class)
;}
