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

import java.sql.ResultSet;
import java.sql.SQLException;

import com.exedio.cope.function.LengthView;
import com.exedio.cope.function.UppercaseView;

public abstract class StringView
	extends StaticView<String>
	implements StringFunction
{
	public StringView(
			final Function<?>[] sources,
			final String name,
			final String[] sqlFragments)
	{
		super(sources, name, String.class, StringColumn.JDBC_TYPE, sqlFragments);
	}

	final Object load(final ResultSet resultSet, final int columnIndex)
	throws SQLException
	{
		return resultSet.getString(columnIndex);
	}

	final String surface2Database(final Object value)
	{
		return StringColumn.cacheToDatabaseStatic(value);
	}
	
	final void surface2DatabasePrepared(final Statement bf, final Object value)
	{
		bf.appendParameter((String)value);
	}
	
	public final String get(final Item item)
	{
		return (String)getObject(item);
	}
	
	// convenience methods for conditions and views ---------------------------------

	public final LikeCondition like(final String value)
	{
		return new LikeCondition(this, value);
	}
	
	public final LikeCondition startsWith(final String value)
	{
		return LikeCondition.startsWith(this, value);
	}
	
	public final LikeCondition endsWith(final String value)
	{
		return LikeCondition.endsWith(this, value);
	}
	
	public final LikeCondition contains(final String value)
	{
		return LikeCondition.contains(this, value);
	}
	
	public final LengthView length()
	{
		return new LengthView(this);
	}
	
	public final UppercaseView toUpperCase()
	{
		return new UppercaseView(this);
	}
	
	public final EqualCondition equalIgnoreCase(final String value)
	{
		return toUpperCase().equal(value.toUpperCase());
	}
	
	public final LikeCondition likeIgnoreCase(final String value)
	{
		return toUpperCase().like(value.toUpperCase());
	}
}
