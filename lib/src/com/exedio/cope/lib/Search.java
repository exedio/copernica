
package persistence;

import java.util.Collection;
import java.util.Iterator;
import persistence.search.AndCondition;
import persistence.search.Condition;
import persistence.search.EqualCondition;

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
	
	public static final EqualCondition equal(final StringAttribute attribute, final String value)
	{
		return new EqualCondition(attribute, value);
	}
	
	public static final AndCondition and(final Condition condition1, final Condition condition2)
	{
		return new AndCondition(new Condition[]{condition1, condition2});
	}
	
	public static final Collection search(final Type type, final Condition condition)
	{
		return null;
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
