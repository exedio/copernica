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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.exedio.cope.StringAttribute;

/**
 * Uses hashes from the java.security package.
 * 
 * @author Ralf Wiebicke
 */
public class JavaHash extends Hash
{
	private final String hash;
	private final String encoding;
	
	public JavaHash(final StringAttribute storage, final String hash, final String encoding)
	{
		super(storage);
		this.hash = hash;
		this.encoding = encoding;

		try
		{
			createDigest();
			encode("test");
		}
		catch(NoSuchAlgorithmException e)
		{
			throw new RuntimeException(e);
		}
		catch(UnsupportedEncodingException e)
		{
			throw new RuntimeException(e);
		}
	}

	public JavaHash(final StringAttribute storage, final String hash)
	{
		this(storage, hash, "utf8");
	}
	
	private final MessageDigest createDigest() throws NoSuchAlgorithmException
	{
		return MessageDigest.getInstance(hash);
	}
	
	private final byte[] encode(final String s) throws UnsupportedEncodingException
	{
		return s.getBytes(encoding);
	}
	
	public final String hash(final String plainText)
	{
		if(plainText == null)
			return null;
		if(plainText.length() == 0)
			return plainText;

		try
		{
			final MessageDigest messageDigest = createDigest();
			messageDigest.reset();
			messageDigest.update(encode(plainText));
			final byte[] resultBytes = messageDigest.digest();
			final String result = encodeBytes(resultBytes);
			//System.out.println("----------- encoded ("+hash+","+encoding+") >"+plainText+"< to >"+result+"< ("+resultBytes.length+").");
			return result;
		}
		catch(NoSuchAlgorithmException e)
		{
			throw new RuntimeException(e);
		}
		catch(UnsupportedEncodingException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	private static final char[] mapping = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	
	static final String encodeBytes(final byte[] buf)
	{
		final int length = buf.length;
		final char[] result = new char[length*2];

		int i2 = 0;
		for(int i = 0; i<length; i++,i2+=2)
		{
			final byte bufi = buf[i];
			final int bufilo = bufi & 0x0F;
			final int bufihi = (bufi & 0xF0)>>4;
			result[i2]   = mapping[bufihi];
			result[i2+1] = mapping[bufilo];
		}
		return new String(result);
	}

}
