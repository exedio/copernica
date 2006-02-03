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

package com.exedio.cope;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;

/**
 * An input stream suitable for testing stream related code.
 * Makes the <code>ByteArrayInputStream</code> close-sensitive,
 * i.e. all methods except <code>close</code> throw an <code>IOException</code>,
 * if the stream has already been closed.
 * 
 * @author Ralf Wiebicke
 */
class TestByteArrayInputStream extends InputStream
{
	private final ByteArrayInputStream in;
	boolean closed = false;
	
	TestByteArrayInputStream(final byte[] buf)
	{
		this.in = new ByteArrayInputStream(buf);
	}
	
	public int read() throws IOException
	{
		assertOpen();
		return in.read();
	}

	public int read(final byte b[], final int off, final int len) throws IOException
	{
		assertOpen();
		return in.read(b, off, len);
	}

	public long skip(final long n) throws IOException
	{
		assertOpen();
		return in.skip(n);
	}

	public int available() throws IOException
	{
		assertOpen();
		return in.available();
	}

	public boolean markSupported()
	{
		return false;
	}

	public void mark(int readAheadLimit)
	{
	}

	public void reset() throws IOException
	{
		throw new IOException("mark/reset not supported");
	}

	public void close() throws IOException
	{
		super.close();
		closed = true;
	}
	
	private void assertOpen() throws IOException
	{
		if(closed)
			throw new IOException("stream already closed");
	}
	
	public void assertClosed()
	{
		Assert.assertEquals("stream still open", true, closed);
	}
}

