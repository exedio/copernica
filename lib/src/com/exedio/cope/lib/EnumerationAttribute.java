
package com.exedio.cope.lib;

public final class EnumerationAttribute extends Attribute
{
	Object databaseToCache(final Object cell)
	{
		if(cell==null)
			return null;
		else if(cell instanceof Integer)
		{
			// TODO: This is nonsense, must retrieve the correct EnumerationValue
			return cell;
		}
		else
			throw new RuntimeException("cellToCache:"+cell);
	}

	public Object cache2Database(final Object cache)
	{
		if(cache==null)
			return "NULL";
		else
			return Integer.toString(((EnumerationValue)cache).number);
	}
}
