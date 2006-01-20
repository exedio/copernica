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

package com.exedio.cope.instrument;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class Example15
{
	private String name;
	public HashSet<Date> dates;
	HashMap<Integer, Boolean> primes = new HashMap();

	public Example15(final HashSet<Date> dates, HashMap<Integer, Boolean> primes)
	{
		this.dates = dates;
		this.primes = primes;
	}
	
	public void set(HashSet<Date> dates, final HashMap<Integer, Boolean> primes)
	{
		this.dates = dates;
		this.primes = primes;
	}

	private HashSet<Date> getDates()
	{
		return dates;
	}

	HashMap<Integer, Boolean> getPrimes()
	{
		return primes;
	}

}
