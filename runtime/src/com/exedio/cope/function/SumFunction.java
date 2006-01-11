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

package com.exedio.cope.function;

import com.exedio.cope.IntegerView;
import com.exedio.cope.Cope;
import com.exedio.cope.IntegerFunction;

public final class SumFunction
	extends IntegerView
	implements IntegerFunction
{

	/**
	 * Creates a new SumFunction.
	 * Instead of using this constructor directly,
	 * you may want to use the more convenient wrapper methods.
	 * @see IntegerFunction#sum(IntegerFunction)
	 * @see Cope#sum(IntegerFunction,IntegerFunction)
	 * @see Cope#sum(IntegerFunction,IntegerFunction,IntegerFunction)
	 */
	public SumFunction(final IntegerFunction[] addends)
	{
		super(addends, makePlusses(addends.length), "sum");
	}
	
	private static final String[] makePlusses(final int length)
	{
		final String[] result = new String[length+1];

		result[0] = "(";
		for(int i=length-1; i>0; i--)
			result[i] = "+";
		result[length] = ")";

		return result;
	}

	public final Object mapJava(final Object[] sourceValues)
	{
		int result = 0;
		for(int i=0; i<sourceValues.length; i++)
		{
			if(sourceValues[i]==null)
				return null;
			result += ((Integer)sourceValues[i]).intValue();
		}
		return new Integer(result);
	}

}
