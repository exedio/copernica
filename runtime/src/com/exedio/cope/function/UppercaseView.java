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

package com.exedio.cope.function;

import com.exedio.cope.Join;
import com.exedio.cope.Statement;
import com.exedio.cope.StringField;
import com.exedio.cope.StringFunction;
import com.exedio.cope.StringView;

public final class UppercaseView extends StringView implements StringFunction
{
	private final StringFunction source;

	/**
	 * Creates a new UppercaseView.
	 * Instead of using this constructor directly,
	 * you may want to use the more convenient wrapper method
	 * {@link StringField#toUpperCase()}.
	 */
	public UppercaseView(final StringFunction source)
	{
		super(new StringFunction[]{source}, "upper");
		this.source = source;
	}

	@Override
	public final String mapJava(final Object[] sourceValues)
	{
		assert sourceValues.length==1;
		final Object sourceValue = sourceValues[0];
		return sourceValue==null ? null : ((String)sourceValue).toUpperCase();
	}

	@Deprecated // OK: for internal use within COPE only
	public final void append(final Statement bf, final Join join)
	{
		bf.append("UPPER(").
			append(source, join).
			append(')');
	}
}
