
package com.exedio.cope.lib;

import java.util.Collections;
import java.util.List;

public final class IntegerAttribute extends ObjectAttribute
{
	public IntegerAttribute(final Option option)
	{
		super(option);
	}
	
	protected List createColumns(final String name, final boolean notNull)
	{
		return Collections.singletonList(new IntegerColumn(getType(), name, notNull, 10, false));
	}
	
	Object cacheToSurface(final Object cache)
	{
		return (Integer)cache;
	}
		
	Object surfaceToCache(final Object surface)
	{
		return (Integer)surface;
	}
	
}
