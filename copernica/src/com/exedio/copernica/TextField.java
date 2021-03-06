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

abstract class TextField extends Field
{

	/**
	 * Constructs a form field with an initial value.
	 * @throws NullPointerException if value is null.
	 */
	public TextField(final Form form, final Object key, final String name, final String value)
	{
		super(form, key, name, value);

		if(value==null)
			throw new NullPointerException("value for " + name);
	}

	/**
	 * Constructs a form field with a value obtained from the submitted form.
	 * @throws NullPointerException if request does not contain a parameter for name.
	 */
	public TextField(final Form form, final Object key, final String name)
	{
		super(form, key, name);

		if(value==null)
			throw new NullPointerException("value for " + name);
	}

	/**
	 * Let the content of the <tt>type</tt> attribute of the <tt>input</tt> tag
	 * contain <tt>password</tt> instead of <tt>text</tt>,
	 * if you use {@link #write(PrintStream)}.
	 */
	public boolean password = false;

	/**
	 * The content of the <tt>size</tt> attribute of the <tt>input</tt> tag,
	 * if you use {@link #write(PrintStream)}.
	 */
	public int size = 30;

	@Override
	public void writeIt(final PrintStream out)
	{
		Main_Jspm.write(out, this);
	}
}
