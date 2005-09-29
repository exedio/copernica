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

package com.exedio.cope;

import java.sql.SQLException;

/**
 * Signals, that an attempt to write an attribute has been failed,
 * and the value to be set violated a unique constraint.
 *
 * This exception will be thrown by {@link Item#set(ObjectAttribute,Object) Item.set}
 * and item constructors
 * if that attribute is covered by a {@link UniqueConstraint unique constraint}
 * and the value to be set violated the uniqueness.
 * 
 * @author Ralf Wiebicke
 */
public final class UniqueViolationException extends ConstraintViolationException
{
	private final UniqueConstraint constraint;
	
	/**
	 * Creates a new UniqueViolationException with the neccessary information about the violation.
	 * @param item initializes, what is returned by {@link #getItem()}.
	 * @param constraint initializes, what is returned by {@link #getConstraint()}.
	 * @throws NullPointerException if <code>constraint</code> is null.
	 */
	UniqueViolationException(final SQLException cause, final Item item, final UniqueConstraint constraint)
	{
		super(cause);
		if(cause==null)
			throw new NullPointerException();
		if(constraint==null)
			throw new NullPointerException();
		this.constraint = constraint;
	}
	
	/**
	 * Returns the item that was attempted to be modified.
	 * Returns null, if the violation occured on the creation of an item.
	 */
	public final Item getItem()
	{
		return null;
	}

	/**
	 * Returns the violated constraint.
	 */
	public UniqueConstraint getConstraint()
	{
		return constraint;
	}
	
	public String getMessage()
	{
		return "unique violation for " + constraint;
	}
	
}
