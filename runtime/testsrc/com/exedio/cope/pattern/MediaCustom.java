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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exedio.cope.Condition;
import com.exedio.cope.Item;
import com.exedio.cope.StringField;

/**
 * A test subclass of MediaPath for unit-testing custom extensions of MediaPath.
 * @author Ralf Wiebicke
 */
final class MediaCustom extends MediaPath
{
	final StringField source;

	MediaCustom(final StringField source)
	{
		this.source = source;
	}
	
	StringField getSource()
	{
		return source;
	}
	
	@Override
	public void initialize()
	{
		super.initialize();
		
		final String name = getName();
		if(source!=null && !source.isInitialized())
			initialize(source, name+"Source");
	}
	
	@Override
	public String getContentType(final Item item)
	{
		return source.get(item)!=null ? "text/plain" : null;
	}

	@Override
	public Media.Log doGet(
			final HttpServletRequest request, final HttpServletResponse response,
			final Item item, final String extension)
	{
		throw new RuntimeException();
	}
	
	@Override
	public Condition isNull()
	{
		throw new RuntimeException();
	}

	@Override
	public Condition isNotNull()
	{
		throw new RuntimeException();
	}
}