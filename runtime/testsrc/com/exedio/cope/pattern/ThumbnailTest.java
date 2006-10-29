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

import java.io.IOException;
import java.util.Arrays;

import com.exedio.cope.AbstractLibTest;
import com.exedio.cope.Feature;
import com.exedio.cope.Main;

public class ThumbnailTest extends AbstractLibTest
{
	public ThumbnailTest()
	{
		super(Main.thumbnailModel);
	}
	
	private ThumbnailItem item, jpeg, png, gif, text, empty;
	private final byte[] data  = {-86,122,-8,23};
	
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		deleteOnTearDown(jpeg  = new ThumbnailItem());
		deleteOnTearDown(png   = new ThumbnailItem());
		deleteOnTearDown(gif   = new ThumbnailItem());
		deleteOnTearDown(text  = new ThumbnailItem());
		deleteOnTearDown(empty = new ThumbnailItem());
		jpeg.setFile(data, "image/jpeg");
		png.setFile(data, "image/png");
		gif.setFile(data, "image/gif");
		text.setFile(data, "text/plain");
	}
	
	public void testThumbs() throws IOException
	{
		// test model
		assertEqualsUnmodifiable(Arrays.asList(new Feature[]{
				item.TYPE.getThis(),
				item.file,
				item.file.getBody(),
				item.file.getMimeMajor(),
				item.file.getMimeMinor(),
				item.file.getLastModified(),
				item.thumb,
			}), item.TYPE.getFeatures());
		assertEquals(item.TYPE, item.thumb.getType());
		assertEquals("thumb", item.thumb.getName());
		assertSame(item.file, item.thumb.getMedia());
		assertEquals(20, item.thumb.getBoundX());
		assertEquals(30, item.thumb.getBoundY());
		
		// test sizing algorithm
		assertBB(40, 60, 20, 30);
		assertBB(40, 50, 20, 25);
		assertBB(30, 60, 15, 30);
		assertBBNone(20, 30);
		assertBBNone(10, 10);
		
		// test urls
		assertEquals("media/ThumbnailItem/thumb/" + jpeg.getCopeID() + ".jpg", jpeg.getThumbURL());
		assertEquals("media/ThumbnailItem/thumb/" + png.getCopeID() + ".jpg", png.getThumbURL());
		assertEquals("media/ThumbnailItem/thumb/" + gif.getCopeID() + ".jpg", gif.getThumbURL());
		assertEquals(null, text.getThumbURL());
		assertEquals(null, empty.getThumbURL());
	}
	
	private void assertBB(final int srcX, final int srcY, final int tgtX, final int tgtY)
	{
		final int[] bb = item.thumb.boundingBox(srcX, srcY);
		assertEquals("width", tgtX, bb[0]);
		assertEquals("height", tgtY, bb[1]);
	}

	private void assertBBNone(final int srcX, final int srcY)
	{
		final int[] bb = item.thumb.boundingBox(srcX, srcY);
		assertNull(bb);
	}
}
