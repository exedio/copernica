
package com.exedio.cope.lib.function;

import com.exedio.cope.lib.ComputedIntegerFunction;
import com.exedio.cope.lib.IntegerFunction;
import com.exedio.cope.lib.StringFunction;

public class LengthFunction
	extends ComputedIntegerFunction
	implements IntegerFunction
{
	private static final String[] sql = new String[]{"LENGTH(", ")"};

	public LengthFunction(final StringFunction source)
	{
		super(new StringFunction[]{source}, sql, "length");
	}

	public final Object mapJava(final Object[] sourceValues)
	{
		final Object sourceValue = sourceValues[0];
		return sourceValue==null ? null : new Integer(((String)sourceValue).length());
	}

}
