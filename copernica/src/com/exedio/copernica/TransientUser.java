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
package com.exedio.copernica;


public class TransientUser implements CopernicaUser
{
	final String id;
	private final String password;
	private final String name;
	
	public TransientUser(final String id, final String password, final String name)
	{
		this.id = id;
		this.password = password;
		this.name = name;
	}
	
	public String getCopernicaName()
	{
		return name;
	}
	
	public boolean checkCopernicaPassword(final String actualPassword)
	{
		return this.password.equals(actualPassword);
	}

}
