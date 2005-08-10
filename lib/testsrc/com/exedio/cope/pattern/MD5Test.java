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

package com.exedio.cope.pattern;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.exedio.cope.AbstractLibTest;
import com.exedio.cope.NestingRuntimeException;
import com.exedio.cope.testmodel.MD5Item;
import com.exedio.cope.testmodel.Main;

public class MD5Test extends AbstractLibTest
{
	public MD5Test()
	{
		super(Main.md5Model);
	}
	
	MD5Item item;
	
	public void setUp() throws Exception
	{
		super.setUp();
		deleteOnTearDown(item = new MD5Item());
	}
	
	public void testMD5()
	{
		assertEquals(list(item.password, item.hashed1, item.hashed1Latin), item.TYPE.getPatterns());

		assertEquals(item.TYPE, item.password.getType());
		assertEquals("password", item.password.getName());
		assertEquals(item.TYPE, item.password.getStorage().getType());
		assertEquals("passwordHash", item.password.getStorage().getName());
		assertEquals(32, item.password.getStorage().getMinimumLength());
		assertEquals(32, item.password.getStorage().getMaximumLength());
		
		assertEquals(item.TYPE, item.hashed1.getType());
		assertEquals(item.TYPE, item.hashed1Latin.getType());
		assertEquals("hashed1", item.hashed1.getName());
		assertEquals("hashed1Latin", item.hashed1Latin.getName());
		assertEquals(item.hashed1MD5, item.hashed1.getStorage());
		assertEquals(item.hashed1MD5, item.hashed1Latin.getStorage());

		assertEquals("000ff0aa", JavaHash.encodeBytes(new byte[]{0x00, 0x0F, (byte)0xF0, (byte)0xAA}));
		assertEquals("0123456789abcdef", JavaHash.encodeBytes(new byte[]{0x01, 0x23, 0x45, 0x67, (byte)0x89, (byte)0xab, (byte)0xcd, (byte)0xef}));

		assertNull(item.getHashed1MD5());
		assertTrue(item.checkHashed1(null));
		assertTrue(!item.checkHashed1("bing"));
		
		// reference example from http://de.wikipedia.org/wiki/MD5
		item.setPassword("Franz jagt im komplett verwahrlosten Taxi quer durch Bayern");
		assertEquals("a3cca2b2aa1e3b5b3b5aad99a8529074", item.getPassword());
		assertTrue(item.checkPassword("Franz jagt im komplett verwahrlosten Taxi quer durch Bayern"));
		assertTrue(!item.checkPassword("franz jagt im komplett verwahrlosten Taxi quer durch Bayern"));
		item.setPassword("franz jagt im komplett verwahrlosten Taxi quer durch Bayern");
		assertEquals("4679e94e07f9a61f42b3d7f50cae0aef", item.getPassword());
		assertTrue(!item.checkPassword("Franz jagt im komplett verwahrlosten Taxi quer durch Bayern"));
		assertTrue(item.checkPassword("franz jagt im komplett verwahrlosten Taxi quer durch Bayern"));

		item.setHashed1MD5("bello");
		assertEquals("bello", item.getHashed1MD5());
		assertTrue(!item.checkHashed1(null));
		assertTrue(!item.checkHashed1("bello"));

		item.setHashed1("knollo");
		assertEquals("ad373a47d81949f466552edf29499b32", item.getHashed1MD5());
		assertTrue(!item.checkHashed1(null));
		assertTrue(!item.checkHashed1("bello"));
		assertTrue(item.checkHashed1("knollo"));
		
		final String longPlainText =
			"knolloknolloknolloknolloknolloknolloknolloknolloknolloknolloknollo" +
			"knolloknolloknolloknolloknolloknolloknolloknolloknolloknolloknollo" +
			"knolloknolloknolloknolloknollo";
		item.setHashed1(longPlainText);
		assertEquals("6ce62d0dbd8e8b3f453ba742c102cd0b", item.getHashed1MD5());
		assertTrue(!item.checkHashed1(null));
		assertTrue(!item.checkHashed1("bello"));
		assertTrue(item.checkHashed1(longPlainText));

		// Test, that special characters produce different hashes
		// with different pre-MD5 encodings.
		final String specialPlainText = "Viele Gr\u00fc\u00dfe";
		
		item.setHashed1(specialPlainText);
		assertEquals("b6f7c12664a57ad17298068b62c9053c", item.getHashed1MD5());
		assertTrue(!item.checkHashed1(null));
		assertTrue(!item.checkHashed1("bello"));
		assertTrue(item.checkHashed1(specialPlainText));
		assertTrue(!item.checkHashed1Latin(specialPlainText));

		item.setHashed1Latin(specialPlainText);
		assertEquals("f80281c9b755508af7c42f585ed76e23", item.getHashed1MD5());
		assertTrue(!item.checkHashed1Latin(null));
		assertTrue(!item.checkHashed1Latin("bello"));
		assertTrue(item.checkHashed1Latin(specialPlainText));
		assertTrue(!item.checkHashed1(specialPlainText));

		try
		{
			new MD5Hash(item.hashed1MD5, "nixus");
			fail("should have thrown UnsupportedEncodingException");
		}
		catch(NestingRuntimeException e)
		{
			assertEquals("nixus", e.getMessage());
			assertEquals(UnsupportedEncodingException.class, e.getNestedCause().getClass());
		}
		try
		{
			new JavaHash(item.hashed1MD5, "nixus");
			fail("should have thrown NoSuchAlgorithmException");
		}
		catch(NestingRuntimeException e)
		{
			assertEquals("NIXUS MessageDigest not available", e.getMessage());
			assertEquals(NoSuchAlgorithmException.class, e.getNestedCause().getClass());
		}
	}

}
