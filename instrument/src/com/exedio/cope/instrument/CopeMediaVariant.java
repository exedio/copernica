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
package com.exedio.cope.instrument;


final class CopeMediaVariant
{
	final String name;
	final String shortName;
	final CopeMediaAttribute mediaAttribute;

	public CopeMediaVariant(final JavaAttribute javaAttribute, final CopeMediaAttribute mediaAttribute)
	{
		this.name = javaAttribute.name;
		this.mediaAttribute = mediaAttribute;
		final String prefix = mediaAttribute.getName();
		this.shortName = this.name.startsWith(prefix)
				? this.name.substring(prefix.length())
				: this.name;
		mediaAttribute.addVariant(this);
	}

}
