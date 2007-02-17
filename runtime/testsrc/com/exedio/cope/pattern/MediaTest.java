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

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import com.exedio.cope.AbstractLibTest;
import com.exedio.cope.Feature;
import com.exedio.cope.Model;

public final class MediaTest extends AbstractLibTest
{
	static final Model MODEL = new Model(MediaItem.TYPE);

	public MediaTest()
	{
		super(MODEL);
	}
	
	// TODO test various combinations of internal, external implicit, and external explicit source

	protected MediaItem item;
	
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		deleteOnTearDown(item = new MediaItem("test media item"));
	}
	
	public void testData() throws IOException
	{
		assertEquals(0, data0.length);
		assertEquals(4, data4.length);
		assertEquals(6, data6.length);
		assertEquals(8, data8.length);
		assertEquals(20, data20.length);
		assertEquals(21, data21.length);
		
		assertEqualsUnmodifiable(Arrays.asList(new Feature[]{
				item.TYPE.getThis(),
				item.name,
				item.file,
				item.file.getBody(),
				item.file.getContentType(),
				item.file.getLastModified(),
				item.image,
				item.image.getBody(),
				item.image.getContentType(),
				item.image.getLastModified(),
				item.photo,
				item.photo.getBody(),
				item.photo.getLastModified(),
				item.foto,
				item.nameServer,
			}), item.TYPE.getFeatures());

		// foto
		assertEquals(item.TYPE, item.foto.getType());
		assertEquals("foto", item.foto.getName());
		assertSame(item.photo, item.foto.getTarget());
		
		try
		{
			new MediaRedirect(null);
			fail();
		}
		catch(NullPointerException e)
		{
			assertEquals("target must not be null", e.getMessage());
		}
		
		assertEquals(null, item.getFotoContentType());
		assertEquals(null, item.getFotoURL());

		item.setPhoto(data4, "image/jpeg");
		assertEquals("image/jpeg", item.getFotoContentType());
		assertEquals("media/MediaItem/foto/" + item.getCopeID() + ".jpg", item.getFotoURL());
		
		item.setPhoto((InputStream)null, null);
		assertEquals(null, item.getFotoContentType());
		assertEquals(null, item.getFotoURL());
		
		// nameServer
		assertEquals(item.TYPE, item.nameServer.getType());
		assertEquals("nameServer", item.nameServer.getName());
		assertSame(item.name, item.nameServer.getSource());
		assertEquals("text/plain", item.getNameServerContentType());
		assertEquals("media/MediaItem/nameServer/" + item.getCopeID() + ".txt", item.getNameServerURL());
		

		// logs -----------------------------------------------
		
		assertEquals(0, item.photo.noSuchItem.get());
		assertEquals(0, item.photo.isNull.get());
		assertEquals(0, item.photo.notModified.get());
		assertEquals(0, item.photo.delivered.get());
		
		item.photo.noSuchItem.increment();
		assertEquals(1, item.photo.noSuchItem.get());
		assertEquals(0, item.photo.isNull.get());
		assertEquals(0, item.photo.notModified.get());
		assertEquals(0, item.photo.delivered.get());

		item.photo.noSuchItem.increment();
		item.photo.isNull.increment();
		item.photo.notModified.increment();
		item.photo.delivered.increment();
		assertEquals(2, item.photo.noSuchItem.get());
		assertEquals(1, item.photo.isNull.get());
		assertEquals(1, item.photo.notModified.get());
		assertEquals(1, item.photo.delivered.get());
	}
}
