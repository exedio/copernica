
package com.exedio.cope.lib;

public final class ItemAttribute extends Attribute
{
	Object databaseToCache(final Object cell)
	{
		if(cell==null)
			return null;
		else if(cell instanceof Integer)
		{
			// TODO: This is nonsense, must retrieve the correct Item, but first I need the Type here.
			// TODO: Item objects should not go into item cache, use thier pks instead.
			return cell;
		}
		else
			throw new RuntimeException("cellToCache:"+cell);
	}

	public Object cacheToDatabase(final Object cache)
	{
		if(cache==null)
			return "NULL";
		else
			return Integer.toString(((Item)cache).pk);
	}

	Object cacheToSurface(final Object cache)
	{
		return (Item)cache;
	}
		
	Object surfaceToCache(final Object surface)
	{
		return (Item)surface;
	}
	
}
