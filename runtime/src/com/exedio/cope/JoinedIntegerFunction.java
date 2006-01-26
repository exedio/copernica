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

package com.exedio.cope;

import com.exedio.cope.function.SumView;
import com.exedio.cope.search.GreaterCondition;
import com.exedio.cope.search.GreaterEqualCondition;
import com.exedio.cope.search.LessCondition;
import com.exedio.cope.search.LessEqualCondition;


public final class JoinedIntegerFunction extends JoinedFunction implements IntegerFunction
{
	public JoinedIntegerFunction(final IntegerFunction function, final Join join)
	{
		super(function, join);
	}
	
	public final Integer get(final Item item)
	{
		return (Integer)getObject(item);
	}
	
	public final EqualCondition equal(final Integer value)
	{
		return new EqualCondition(this, value);
	}
	
	public final EqualCondition equal(final int value)
	{
		return new EqualCondition(this, new Integer(value));
	}
	
	public final NotEqualCondition notEqual(final Integer value)
	{
		return new NotEqualCondition(this, value);
	}
	
	public final NotEqualCondition notEqual(final int value)
	{
		return new NotEqualCondition(this, new Integer(value));
	}
	
	public final LessCondition less(final int value)
	{
		return new LessCondition(this, new Integer(value));
	}
	
	public final LessEqualCondition lessOrEqual(final int value)
	{
		return new LessEqualCondition(this, new Integer(value));
	}
	
	public final GreaterCondition greater(final int value)
	{
		return new GreaterCondition(this, new Integer(value));
	}
	
	public final GreaterEqualCondition greaterOrEqual(final int value)
	{
		return new GreaterEqualCondition(this, new Integer(value));
	}
	
	public final SumView sum(final IntegerFunction other)
	{
		return new SumView(new IntegerFunction[]{this, other});
	}

}
