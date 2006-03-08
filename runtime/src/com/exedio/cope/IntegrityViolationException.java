/*
 * Copyright (C) 2004-2006  exedio GmbH (www.exedio.com)
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

package com.exedio.cope;


/**
 * Signals, that an attempt to delete an item has been failed,
 * because some other item point to that item with some
 * {@link ItemAttribute item attribute}.
 * <p>
 * Also knows as foreign key constraint violation.
 * <p>
 * This exception is thrown by {@link Item#deleteCopeItem}.
 * 
 * @author Ralf Wiebicke
 */
public final class IntegrityViolationException extends ConstraintViolationException
{
	private static final long serialVersionUID = 217658164836512l;
	
	private final ItemAttribute attribute;

	/**
	 * Creates a new UniqueViolationException with the neccessary information about the violation.
	 * @param item initializes, what is returned by {@link #getItem()}.
	 * @param attribute initializes, what is returned by {@link #getAttribute()}.
	 * @throws NullPointerException if <code>item</code> or <code>attribute</code> is null.
	 */
	IntegrityViolationException(final ItemAttribute attribute, final Item item)
	{
		super(attribute, item, null);
		
		if(item==null)
			throw new NullPointerException();
		
		this.attribute = attribute;
	}

	/**
	 * Returns the item attribute, for which the integrity (foreign key) constraint has been violated.
	 * Returns null, if the violated constraint is unknown.
	 */
	public ItemAttribute getAttribute()
	{
		return attribute;
	}
	
	public String getMessage()
	{
		return "integrity violation on deletion of " + getItem().getCopeID() + " because of " + attribute;
	}
	
}
