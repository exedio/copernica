
package com.exedio.cope.lib;

import java.util.Collections;
import java.util.List;

public final class IntegerAttribute extends Attribute
{
	protected List createColumns(final String name, final boolean notNull)
	{
		return Collections.singletonList(new IntegerColumn(getType(), name, notNull, 20));
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
