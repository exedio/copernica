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

public class MediaMandatoryItem extends Item
{
	static final StringField name = new StringField().optional();

	static final Media file = new Media().lengthMax(20);

	/**
	 * Creates a new MediaMandatoryItem with all the fields initially needed.
	 */
	public MediaMandatoryItem(final byte[] fileBody, final String fileContentType)
	{
		this(Media.toValue(fileBody, fileContentType));
	}
	
	// must not be generated by the instrumentor
	// because file is mandatory
	final boolean isFileNull()
	{
		return MediaMandatoryItem.file.isNull(this);
	}
	
	
	/**

	 **
	 * Creates a new MediaMandatoryItem with all the fields initially needed.
	 * @param file the initial value for field {@link #file}.
	 * @throws com.exedio.cope.MandatoryViolationException if file is null.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	MediaMandatoryItem(
				final com.exedio.cope.pattern.Media.Value file)
			throws
				com.exedio.cope.MandatoryViolationException
	{
		this(new com.exedio.cope.SetValue[]{
			MediaMandatoryItem.file.map(file),
		});
	}/**

	 **
	 * Creates a new MediaMandatoryItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private MediaMandatoryItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	@SuppressWarnings("unused") private MediaMandatoryItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
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
		return MediaMandatoryItem.name.get(this);
	}/**

	 **
	 * Sets a new value for {@link #name}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setName(final java.lang.String name)
			throws
				com.exedio.cope.LengthViolationException
	{
		MediaMandatoryItem.name.set(this,name);
	}/**

	 **
	 * Returns a URL the content of {@link #file} is available under.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getURL public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getFileURL()
	{
		return MediaMandatoryItem.file.getURL(this);
	}/**

	 **
	 * Returns the content type of the media {@link #file}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getContentType public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final java.lang.String getFileContentType()
	{
		return MediaMandatoryItem.file.getContentType(this);
	}/**

	 **
	 * Returns the last modification date of media {@link #file}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getLastModified public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final long getFileLastModified()
	{
		return MediaMandatoryItem.file.getLastModified(this);
	}/**

	 **
	 * Returns the body length of the media {@link #file}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getLength public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final long getFileLength()
	{
		return MediaMandatoryItem.file.getLength(this);
	}/**

	 **
	 * Returns the body of the media {@link #file}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getBody public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final byte[] getFileBody()
	{
		return MediaMandatoryItem.file.getBody(this);
	}/**

	 **
	 * Writes the body of media {@link #file} into the given stream.
	 * Does nothing, if the media is null.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getBody public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void getFileBody(final java.io.OutputStream body)
			throws
				java.io.IOException
	{
		MediaMandatoryItem.file.getBody(this,body);
	}/**

	 **
	 * Writes the body of media {@link #file} into the given file.
	 * Does nothing, if the media is null.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getBody public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void getFileBody(final java.io.File body)
			throws
				java.io.IOException
	{
		MediaMandatoryItem.file.getBody(this,body);
	}/**

	 **
	 * Sets the content of media {@link #file}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setFile(final byte[] body,final java.lang.String contentType)
	{
		MediaMandatoryItem.file.set(this,body,contentType);
	}/**

	 **
	 * Sets the content of media {@link #file}.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setFile(final java.io.InputStream body,final java.lang.String contentType)
			throws
				java.io.IOException
	{
		MediaMandatoryItem.file.set(this,body,contentType);
	}/**

	 **
	 * Sets the content of media {@link #file}.
	 * @throws java.io.IOException if accessing <tt>body</tt> throws an IOException.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.set public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setFile(final java.io.File body,final java.lang.String contentType)
			throws
				java.io.IOException
	{
		MediaMandatoryItem.file.set(this,body,contentType);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for mediaMandatoryItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<MediaMandatoryItem> TYPE = newType(MediaMandatoryItem.class)
;}
