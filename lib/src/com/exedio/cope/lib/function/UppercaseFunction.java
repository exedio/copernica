
package com.exedio.cope.lib.function;

import com.exedio.cope.lib.ComputedStringFunction;
import com.exedio.cope.lib.ObjectAttribute;
import com.exedio.cope.lib.StringAttribute;
import com.exedio.cope.lib.StringFunction;

public final class UppercaseFunction
	extends ComputedStringFunction
	implements StringFunction
{
	private static final String[] sql = new String[]{"UPPER(", ")"};

	public UppercaseFunction(final StringAttribute sourceAttribute)
	{
		super(new ObjectAttribute[]{sourceAttribute}, sql, "upper");
	}

	public final Object mapJava(final Object[] sourceValues)
	{
		final Object sourceValue = sourceValues[0];
		return sourceValue==null ? null : ((String)sourceValue).toUpperCase();
	}
	
}
