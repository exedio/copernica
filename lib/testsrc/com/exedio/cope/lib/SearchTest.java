/*
 * Created on 02.04.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.exedio.cope.lib;

import java.util.Collection;

/**
 * @author rw7
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SearchTest extends DatabaseLibTest
{
	public void testUnmodifiableSearchResult()
			throws IntegrityViolationException
	{
		final ItemWithoutAttributes someItem = new ItemWithoutAttributes();
		final ItemWithManyAttributes item;
		try
		{
			item = new ItemWithManyAttributes("someString", 5, true, someItem, ItemWithManyAttributes.SomeEnumeration.enumValue1);
		}
		catch(NotNullViolationException e)
		{
			throw new SystemException(e);
		}
		item.setSomeNotNullInteger(0);
		final Collection searchResult = Search.search(item.TYPE, Search.equal(item.someNotNullInteger, 0));
		assertEquals(set(item), toSet(searchResult));
		assertUnmodifiable(searchResult);
		
		assertDelete(item);
		assertDelete(someItem);
	}

	public void testIllegalSearch()
	{
		try
		{
			Search.search(ItemWithoutAttributes.TYPE, Search.equal(ItemWithManyAttributes.someInteger, 0));
			fail("should have thrown RuntimeException");
		}
		catch(RuntimeException e)
		{
			assertEquals(
				"attribute someInteger{} belongs to type com.exedio.cope.lib.ItemWithManyAttributes, which is not a from-type of the query: [com.exedio.cope.lib.ItemWithoutAttributes]",
				e.getMessage());
		}
	}
	
	public void testJoin()
	{
	}
	
}
