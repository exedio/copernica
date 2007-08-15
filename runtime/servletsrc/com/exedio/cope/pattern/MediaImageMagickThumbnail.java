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

public final class MediaImageMagickThumbnail extends MediaImageMagickFilter
{
	private final int boundX;
	private final int boundY;

	private static final int MIN_BOUND = 5;
	
	public MediaImageMagickThumbnail(final Media source, final int boundX, final int boundY)
	{
		this(source, boundX, boundY, "image/jpeg", ".jpg");
	}
	
	public MediaImageMagickThumbnail(
			final Media source,
			final int boundX, final int boundY,
			final String outputContentType, final String outputExtension)
	{
		super(
				source,
				outputContentType, outputExtension,
				new String[]{"-resize", String.valueOf(boundX) + 'x' + String.valueOf(boundY) + '>'});
		this.boundX = boundX;
		this.boundY = boundY;
		
		if(boundX<MIN_BOUND)
			throw new IllegalArgumentException("boundX must be " + MIN_BOUND + " or greater, but was " + boundX);
		if(boundY<MIN_BOUND)
			throw new IllegalArgumentException("boundY must be " + MIN_BOUND + " or greater, but was " + boundY);
	}
	
	public int getBoundX()
	{
		return boundX;
	}
	
	public int getBoundY()
	{
		return boundY;
	}
}
