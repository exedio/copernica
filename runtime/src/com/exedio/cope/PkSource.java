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

package com.exedio.cope;

import java.sql.Connection;

final class PkSource
{
	static final int MIN_VALUE = 0;
	static final int MAX_VALUE = Integer.MAX_VALUE;
	static final int NaPK = Integer.MIN_VALUE;

	private final Type type;
	private PkSourceImpl impl;
	
	PkSource(final Type type)
	{
		this.type = type;
		this.impl = new PkSourceMaxImpl(type);
	}

	void flush()
	{
		impl.flush();
	}

	int next(final Connection connection)
	{
		final int result = impl.next(connection);
		
		if(!isValid(result))
			throw new RuntimeException("primary key overflow to " + result + " in type " + type.id);
		
		return result;
	}

	static boolean isValid(final int pk)
	{
		return pk>=MIN_VALUE && pk<=MAX_VALUE;
	}

	Integer getInfo()
	{
		return impl.getInfo();
	}
}
