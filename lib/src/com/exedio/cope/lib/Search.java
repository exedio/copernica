
package com.exedio.cope.lib;

import com.exedio.cope.lib.Database;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import com.exedio.cope.lib.search.AndCondition;
import com.exedio.cope.lib.search.Condition;
import com.exedio.cope.lib.search.EqualCondition;
import java.util.ArrayList;

/**
 * Utility class for searching persistent data.
 * May be subclassed to access methods without class qualifier.
 */
public class Search
{
	/**
	 * Search shall never be instantiated.
	 */
	protected Search()
	{}
	
	/**
	 * Returns the item with the given ID.
	 * Returns null, if no such item exists.
	 * Always returns {@link Item#primaryItem() primary} objects.
	 * @see Item#getID()
	 */
	public static final Item findByID(final String id)
	{
		return null;
	}
	
	public static final EqualCondition equal(final StringAttribute attribute, final String value)
	{
		return new EqualCondition(attribute, value);
	}
	
	public static final EqualCondition equal(final ItemAttribute attribute, final Item value)
	{
		return new EqualCondition(attribute, value);
	}
	
	public static final AndCondition and(final Condition condition1, final Condition condition2)
	{
		return new AndCondition(new Condition[]{condition1, condition2});
	}
	
	/**
	 * Converts a collection of primary keys to a collection of items of the given type.
	 * @param pks the collection of primary keys, is expected not to be modified
	 * @return an unmodifiable collection.
	 */
	private static final Collection wrapPrimaryKeys(final Type type, final Collection pks)
	{
		// TODO: dont convert all items at once, but use some kind of wrapper collection
		final ArrayList result = new ArrayList(pks.size());
		for(Iterator i = pks.iterator(); i.hasNext(); )
		{
			final Integer pk = (Integer)i.next();
			System.out.println("pk:"+pk);
			final Item item = type.getActiveItem(pk.intValue());
			if(item!=null)
				result.add(item);
			else
			{
				// TODO create inactive item with the given pk, if there is no active item
				System.out.println("OOOOPS:"+type+" "+pk);
			}
		}
		return Collections.unmodifiableList(result);
	}
	
	/**
	 * Always returns unmodifiable collections.
	 */
	public static final Collection search(final Type type, final Condition condition)
	{
		//System.out.println("select " + type.getJavaClass().getName() + " where " + condition);
		return wrapPrimaryKeys(type, Database.theInstance.search(type, condition));
	}
	
	public static final Item searchUnique(final Type type, final Condition condition)
	{
		final Iterator searchResult = search(type, condition).iterator();
		if(searchResult.hasNext())
		{
			final Item result = (Item)searchResult.next();
			if(searchResult.hasNext())
				throw new SystemException(null);
			else
				return result;
		}
		else
			return null;
	}
}
