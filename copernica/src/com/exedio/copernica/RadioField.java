/*
 * Copyright (C) 2004-2009  exedio GmbH (www.exedio.com)
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

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

class RadioField extends Field
{
	public final ArrayList<String> names = new ArrayList<String>();
	final HashMap<String, String> values = new HashMap<String, String>();

	/**
	 * Constructs a form field with an initial value.
	 * @throws NullPointerException if value is null.
	 */
	public RadioField(final Form form, final Object key, final String name, final String value)
	{
		super(form, key, name, value);

		if(value==null)
			throw new NullPointerException("value for " + name);
	}

	/**
	 * Constructs a form field with a value obtained from the submitted form.
	 * @throws NullPointerException if request does not contain a parameter for name.
	 */
	public RadioField(final Form form, final Object key, final String name)
	{
		super(form, key, name);

		if(value==null)
			throw new NullPointerException("value for " + name);
	}

	public String getValue(final String name)
	{
		return values.get(name);
	}

	public boolean isChecked(final String checkValue)
	{
		return value.equals(checkValue);
	}

	/**
	 * @throws NullPointerException if name or value is null
	 */
	public void addOption(final String name, final String value)
	{
		if(name==null)
			throw new NullPointerException("name");
		if(value==null)
			throw new NullPointerException("value");

		names.add(name);
		values.put(name, value);
	}

	@Override
	public void writeIt(final PrintStream out)
	{
		Main_Jspm.write(out, this);
	}
}
