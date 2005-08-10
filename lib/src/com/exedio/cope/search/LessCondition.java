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

package com.exedio.cope.search;

import java.util.Date;

import com.exedio.cope.DateAttribute;
import com.exedio.cope.DoubleAttribute;
import com.exedio.cope.EnumAttribute;
import com.exedio.cope.EnumValue;
import com.exedio.cope.IntegerAttribute;
import com.exedio.cope.LongAttribute;
import com.exedio.cope.ObjectAttribute;
import com.exedio.cope.StringAttribute;

public class LessCondition extends LiteralCondition
{
	/**
	 * Creates a new LessCondition.
	 * Instead of using this constructor directly,
	 * you may want to use the more type-safe wrapper methods.
	 * @see StringAttribute#less(String)
	 * @see IntegerAttribute#less(int)
	 * @see LongAttribute#less(long)
	 * @see DoubleAttribute#less(double)
	 * @see DateAttribute#less(Date)
	 * @see EnumAttribute#less(EnumValue)
	 */
	public LessCondition(final ObjectAttribute attribute, final Object value)
	{
		super("<", attribute, value);
	}
	
}
