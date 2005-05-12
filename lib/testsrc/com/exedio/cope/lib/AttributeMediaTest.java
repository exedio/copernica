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
package com.exedio.cope.lib;

import java.io.IOException;

public class AttributeMediaTest extends AttributeTest
{
	public void testSomeMedia() throws IOException
	{
		// TODO: test with not null media
		assertEquals(item.TYPE, item.someData.getType());
		assertEquals(null, item.getSomeDataURL());
		assertEquals(null, item.getSomeDataURLSomeVariant());
		assertEquals(null, item.getSomeDataData());
		assertEquals(null, item.getSomeDataMimeMajor());
		assertEquals(null, item.getSomeDataMimeMinor());

		final byte[] bytes = new byte[]{3,7,1,4};
		item.setSomeData(stream(bytes),"someMimeMajor", "someMimeMinor");

		final String prefix =
			"data/AttributeItem/someData/";
		final String pkString = pkString(item);
		final String expectedURL =
			prefix + pkString + ".someMimeMajor.someMimeMinor";
		final String expectedURLSomeVariant =
			prefix + "SomeVariant/" + pkString + ".someMimeMajor.someMimeMinor";
		//System.out.println(expectedURL);
		//System.out.println(item.getSomeMediaURL());
		assertEquals(expectedURL, item.getSomeDataURL());
		assertEquals(expectedURLSomeVariant, item.getSomeDataURLSomeVariant());
		assertData(bytes, item.getSomeDataData());
		assertEquals("someMimeMajor", item.getSomeDataMimeMajor());
		assertEquals("someMimeMinor", item.getSomeDataMimeMinor());

		item.passivateCopeItem();
		assertEquals(expectedURL, item.getSomeDataURL());
		assertEquals(expectedURLSomeVariant, item.getSomeDataURLSomeVariant());
		assertData(bytes, item.getSomeDataData());
		assertEquals("someMimeMajor", item.getSomeDataMimeMajor());
		assertEquals("someMimeMinor", item.getSomeDataMimeMinor());

		assertMediaMime(item, "image", "jpeg", bytes, "jpg");
		assertMediaMime(item, "image", "pjpeg", bytes, "jpg");
		assertMediaMime(item, "image", "gif", bytes, "gif");
		assertMediaMime(item, "image", "png", bytes, "png");
		assertMediaMime(item, "image", "someMinor", bytes, "image.someMinor");

		final byte[] manyBytes = new byte[49467];
		for(int i = 0; i<manyBytes.length; i++)
		{
			manyBytes[i] = (byte)((121*i)%253);
			//System.out.print(manyBytes[i]+", ");
		}
		item.setSomeData(stream(manyBytes),"someMimeMajor", "someMimeMinor");
		assertData(manyBytes, item.getSomeDataData());

		item.setSomeData(null, null, null);
		assertEquals(null, item.getSomeDataURL());
		assertEquals(null, item.getSomeDataURLSomeVariant());
		assertEquals(null, item.getSomeDataData());
		assertEquals(null, item.getSomeDataMimeMajor());
		assertEquals(null, item.getSomeDataMimeMinor());
	}


}
