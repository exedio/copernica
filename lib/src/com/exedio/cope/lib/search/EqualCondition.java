
package com.exedio.cope.lib.search;

import com.exedio.cope.lib.Function;
import com.exedio.cope.lib.IntegerFunction;
import com.exedio.cope.lib.ObjectAttribute;
import com.exedio.cope.lib.Query;
import com.exedio.cope.lib.Statement;
import com.exedio.cope.lib.StringFunction;

public final class EqualCondition extends Condition
{
	public final Function function;
	public final Object value;

	public EqualCondition(final ObjectAttribute function)
	{
		this.function = function;
		this.value = null;
	}
	
	public EqualCondition(final ObjectAttribute function, final Object value)
	{
		this.function = function;
		this.value = value;
	}
	
	public EqualCondition(final StringFunction function, final String value)
	{
		this.function = function;
		this.value = value;
	}
	
	public EqualCondition(final IntegerFunction function, final Integer value)
	{
		this.function = function;
		this.value = value;
	}
	
	public final void appendStatement(final Statement bf)
	{
		bf.append(function);
		if(value!=null)
			bf.append('=').
				appendValue(function, value);
		else
			bf.append(" is null");
	}

	public final void check(final Query query)
	{
		check(function, query);
	}

	public final String toString()
	{
		return function.getName() + "='" + value + '\'';
	}
	
}
