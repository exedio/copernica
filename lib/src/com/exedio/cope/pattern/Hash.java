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

import com.exedio.cope.Item;
import com.exedio.cope.LengthViolationException;
import com.exedio.cope.NotNullViolationException;
import com.exedio.cope.ReadOnlyViolationException;
import com.exedio.cope.StringAttribute;
import com.exedio.cope.Type;
import com.exedio.cope.TypeComponent;
import com.exedio.cope.UniqueViolationException;

public abstract class Hash extends TypeComponent
{
	final StringAttribute storage;

	public Hash(final StringAttribute storage)
	{
		this.storage = storage;
	}
	
	public abstract String hash(String plainText);
	
	public void setHash(final Item item, final String plainText)
		throws
			UniqueViolationException,
			NotNullViolationException,
			LengthViolationException,
			ReadOnlyViolationException
	{
		item.setAttribute(storage, hash(plainText));
	}
	
	public boolean checkHash(final Item item, final String actualPlainText)
	{
		final String expectedHash = (String)item.getAttribute(storage);
		final String actualHash = hash(actualPlainText);
		if(expectedHash==null)
			return actualHash==null;
		else
			return expectedHash.equals(actualHash);
	}

	// second initialization phase ---------------------------------------------------

	private Type type;
	private String name = null;

	public final void initialize(final Type type, final String name)
	{
		if(type==null)
			throw new RuntimeException();
		if(name==null)
			throw new RuntimeException();

		if(this.type!=null)
			throw new RuntimeException();
		if(this.name!=null)
			throw new RuntimeException();

		this.type = type;
		this.name = name.intern();
	}
	
	public final String getName()
	{
		if(name==null)
			throw new RuntimeException();
		
		return name;
	}

	public final Type getType()
	{
		if(this.type==null)
			throw new RuntimeException();

		return type;
	}
	
}
