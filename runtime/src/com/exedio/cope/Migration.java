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

final class Migration // TODO make public when migration has matured
{
	final int version;
	final String comment;
	final String[] body;
	
	Migration(final int version, final String comment, final String... body) // TODO make public when migration has matured
	{
		this.version = version;
		this.comment = comment;
		this.body = body;
		
		if(version<0)
			throw new IllegalArgumentException("version must not be negative");
		if(comment==null)
			throw new NullPointerException("comment must not be null");
		if(body==null)
			throw new NullPointerException("body must not be null");
		if(body.length==0)
			throw new IllegalArgumentException("body must not be empty");
	}
	
	@Override
	public String toString()
	{
		return "MS" + version + ':' + comment;
	}
}