
package com.exedio.cope.lib;

/**
 * Signals, that an attempt to find an item by it's ID has been failed,
 * because there is no item with such an ID.
 *
 * This exception will be thrown by {@link Model#findByID(String) Model.findByID},
 * if there is no item with the given ID.
 */
public class NoSuchIDException extends Exception
{
	NoSuchIDException(final String id, final String detail)
	{
		super("no such id <"+id+">, "+detail);
	}

	NoSuchIDException(final String id, final NumberFormatException cause, final String numberString)
	{
		super("no such id <"+id+">, wrong number format <"+numberString+">");
	}

	NoSuchIDException(final long id, final String detail)
	{
		super("no such id number <"+id+">, "+detail);
	}
}
