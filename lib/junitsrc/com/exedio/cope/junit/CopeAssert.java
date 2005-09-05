/*
 * Copyright (C) 2004-2005  exedio GmbH (www.exedio.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.exedio.cope.junit;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import com.exedio.cope.NestingRuntimeException;
import com.exedio.cope.Query;

public abstract class CopeAssert extends TestCase
{
	protected final static void assertContainsList(final List expected, final Collection actual)
	{
		if(expected.size()!=actual.size() ||
				!expected.containsAll(actual) ||
				!actual.containsAll(expected))
			fail("expected "+expected+", but was "+actual);
	}

	protected final static void assertContains(final Collection actual)
	{
		assertContainsList(Collections.EMPTY_LIST, actual);
	}

	protected final static void assertContains(final Object o, final Collection actual)
	{
		assertContainsList(Collections.singletonList(o), actual);
	}

	protected final static void assertContains(final Object o1, final Object o2, final Collection actual)
	{
		assertContainsList(Arrays.asList(new Object[]{o1, o2}), actual);
	}

	protected final static void assertContains(final Object o1, final Object o2, final Object o3, final Collection actual)
	{
		assertContainsList(Arrays.asList(new Object[]{o1, o2, o3}), actual);
	}

	protected final static void assertContains(final Object o1, final Object o2, final Object o3, final Object o4, final Collection actual)
	{
		assertContainsList(Arrays.asList(new Object[]{o1, o2, o3, o4}), actual);
	}

	protected final static void assertContains(final Object o1, final Object o2, final Object o3, final Object o4, final Object o5, final Collection actual)
	{
		assertContainsList(Arrays.asList(new Object[]{o1, o2, o3, o4, o5}), actual);
	}

	protected final static void assertContainsUnmodifiable(final Collection actual)
	{
		assertUnmodifiable(actual);
		assertContains(actual);
	}

	protected final static void assertContainsUnmodifiable(final Object o, final Collection actual)
	{
		assertUnmodifiable(actual);
		assertContains(o, actual);
	}

	protected final static void assertContainsUnmodifiable(final Object o1, final Object o2, final Collection actual)
	{
		assertUnmodifiable(actual);
		assertContains(o1, o2, actual);
	}

	protected final static List list()
	{
		return Collections.EMPTY_LIST;
	}

	protected final static List list(final Object o)
	{
		return Collections.singletonList(o);
	}
	
	protected final static List list(final Object o1, final Object o2)
	{
		return Arrays.asList(new Object[]{o1, o2});
	}
	
	protected final static List list(final Object o1, final Object o2, final Object o3)
	{
		return Arrays.asList(new Object[]{o1, o2, o3});
	}
	
	protected final static List list(final Object o1, final Object o2, final Object o3, final Object o4)
	{
		return Arrays.asList(new Object[]{o1, o2, o3, o4});
	}
	
	protected final static List list(final Object o1, final Object o2, final Object o3, final Object o4, final Object o5)
	{
		return Arrays.asList(new Object[]{o1, o2, o3, o4, o5});
	}
	
	protected final static List list(final Object o1, final Object o2, final Object o3, final Object o4, final Object o5, final Object o6)
	{
		return Arrays.asList(new Object[]{o1, o2, o3, o4, o5, o6});
	}
	
	protected final static List list(final Object o1, final Object o2, final Object o3, final Object o4, final Object o5, final Object o6, final Object o7)
	{
		return Arrays.asList(new Object[]{o1, o2, o3, o4, o5, o6, o7});
	}
	
	protected final static List list(final Object o1, final Object o2, final Object o3, final Object o4, final Object o5, final Object o6, final Object o7, final Object o8)
	{
		return Arrays.asList(new Object[]{o1, o2, o3, o4, o5, o6, o7, o8});
	}
	
	protected final static List list(final Object o1, final Object o2, final Object o3, final Object o4, final Object o5, final Object o6, final Object o7, final Object o8, final Object o9)
	{
		return Arrays.asList(new Object[]{o1, o2, o3, o4, o5, o6, o7, o8, o9});
	}
	
	protected final static List list(final Object o1, final Object o2, final Object o3, final Object o4, final Object o5, final Object o6, final Object o7, final Object o8, final Object o9, final Object o10)
	{
		return Arrays.asList(new Object[]{o1, o2, o3, o4, o5, o6, o7, o8, o9, o10});
	}
	
	protected final static void assertUnmodifiable(final Collection c)
	{
		try
		{
			c.add(new Object());
			fail("should have thrown UnsupportedOperationException");
		}
		catch(UnsupportedOperationException e) {}
		try
		{
			c.addAll(Collections.singleton(new Object()));
			fail("should have thrown UnsupportedOperationException");
		}
		catch(UnsupportedOperationException e) {}
		
		if(!c.isEmpty())
		{
			final Object o = c.iterator().next();
			try
			{
				c.clear();
				fail("should have thrown UnsupportedOperationException");
			}
			catch(UnsupportedOperationException e) {}
			try
			{
				c.remove(o);
				fail("should have thrown UnsupportedOperationException");
			}
			catch(UnsupportedOperationException e) {}
			try
			{
				c.removeAll(Collections.singleton(o));
				fail("should have thrown UnsupportedOperationException");
			}
			catch(UnsupportedOperationException e) {}
			try
			{
				c.retainAll(Collections.EMPTY_LIST);
				fail("should have thrown UnsupportedOperationException");
			}
			catch(UnsupportedOperationException e) {}

			final Iterator iterator = c.iterator();
			try
			{
				iterator.remove();
				fail("should have thrown UnsupportedOperationException");
			}
			catch(UnsupportedOperationException e) {}
		}
	}
	
	protected final static void assertEqualsUnmodifiable(final List expected, final Collection actual)
	{
		assertUnmodifiable(actual);
		assertEquals(expected, actual);
	}
	
	private static final String DATE_FORMAT_FULL = "dd.MM.yyyy HH:mm:ss.SSS";
	
	/**
	 * @param resolution the expected resolution of the timestamp saved in param <code>actual</code>, in milliseonds
	 */
	protected final static void assertWithin(final long resolution, final Date expectedBefore, final Date expectedAfter, final Date actual)
	{
		final Date expectedBeforeFloor = new Date((expectedBefore.getTime() / resolution) * resolution);
		final Date expectedAfterCeil   = new Date(((expectedAfter.getTime() / resolution) * resolution) + resolution);

		//final SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_FULL);
		//System.out.println();
		//System.out.println("FLOOR: " + df.format(expectedBefore) + " to " + df.format(expectedBeforeFloor));
		//System.out.println("CEIL:  " + df.format(expectedAfter)  + " to " + df.format(expectedAfterCeil));
		
		final SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_FULL);
		final String message =
			"expected date within " + df.format(expectedBeforeFloor) + " (" + df.format(expectedBefore) + ")" +
			" and " + df.format(expectedAfterCeil) + " (" + df.format(expectedAfter) + ")" +
			", but was " + df.format(actual);

		assertTrue(message, !expectedBeforeFloor.after(actual));
		assertTrue(message, !expectedAfterCeil.before(actual));
	}

	protected final static void assertWithin(final Date expectedBefore, final Date expectedAfter, final Date actual)
	{
		final SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_FULL);
		final String message =
			"expected date within " + df.format(expectedBefore) +
			" and " + df.format(expectedAfter) +
			", but was " + df.format(actual);

		assertTrue(message, !expectedBefore.after(actual));
		assertTrue(message, !expectedAfter.before(actual));
	}
	
	protected final static Object waitForKey(final Object o)
	{
		System.out.println("WAITING FOR KEY");
		try
		{
			System.in.read();
		}
		catch(IOException e)
		{
			throw new NestingRuntimeException(e);
		}
		return o;
	}

	protected final static void waitForKey()
	{
		waitForKey(null);
	}

	/**
	 * Calls {@link Query#search()} on the given query and returns the result.
	 * Prints the statement info to standard out.
	 */
	protected static final Collection infoSearch(final Query query)
	{
		query.enableMakeStatementInfo();
		final Collection result = query.search();
		query.getStatementInfo().print(System.out);
		return result;
	}

}
