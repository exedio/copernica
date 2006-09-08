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

package com.exedio.cope.instrument;


final class CopeAttributeMap extends CopeFeature
{
	public CopeAttributeMap(final CopeType parent, final JavaAttribute javaAttribute)
	{
		super(parent, javaAttribute);
	}

	@Override
	boolean isBoxed()
	{
		return false;
	}
	
	@Override
	String getBoxedType()
	{
		throw new RuntimeException();
	}
	
	String getKeyType()
	{
		return Injector.getGenerics(javaAttribute.type).get(0);
	}
	
	String getValueType()
	{
		return Injector.getGenerics(javaAttribute.type).get(1);
	}
}