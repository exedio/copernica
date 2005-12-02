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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public abstract class Attribute extends Feature
{
	private final boolean readOnly;
	final boolean mandatory;

	protected Attribute(final Option option)
	{
		this.readOnly = option.readOnly;
		this.mandatory = option.mandatory;
	}
	
	public final boolean isReadOnly()
	{
		return readOnly;
	}
	
	public final boolean isMandatory()
	{
		return mandatory;
	}
	
	// patterns ---------------------------------------------------------------------
	
	private ArrayList patternsWhileTypeInitialization = null;
	private List patterns = null;
	
	void registerPattern(final Pattern pattern)
	{
		if(isInitialized())
			throw new RuntimeException("registerPattern cannot be called after initialization of the attribute.");
		if(pattern==null)
			throw new NullPointerException();
		
		if(patternsWhileTypeInitialization==null)
			patternsWhileTypeInitialization = new ArrayList();
		
		patternsWhileTypeInitialization.add(pattern);
	}
	
	public List getPatterns()
	{
		if(!isInitialized())
			throw new RuntimeException("getPatterns cannot be called before initialization of the attribute.");
		if(patterns==null)
			throw new RuntimeException();

		return patterns;
	}
	
	// second initialization phase ---------------------------------------------------

	private Column column;
	
	void initialize(final Type type, final String name)
	{
		super.initialize(type, name);
		type.registerInitialization(this);
		
		final ArrayList patterns = patternsWhileTypeInitialization;
		patternsWhileTypeInitialization = null;
		this.patterns =
			patterns==null
			? Collections.EMPTY_LIST
			: Collections.unmodifiableList(Arrays.asList(patterns.toArray(new Pattern[patterns.size()])));
	}
	
	final void materialize(final Table table)
	{
		if(table==null)
			throw new NullPointerException();
		if(this.column!=null)
			throw new RuntimeException();

		this.column = createColumn(table, getName(), mandatory);
	}
	
	final Column getColumn()
	{
		if(this.column==null)
			throw new RuntimeException();

		return column;
	}
	
	/**
	 * Returns the name of database column for this attribute - use with care!
	 * <p>
	 * This information is needed only, if you want to access
	 * the database without cope.
	 * In this case you should really know, what you are doing.
	 * Please note, that this string may vary,
	 * if a cope model is configured for different databases.
	 * 
	 * @see Type#getTableName()
	 */
	public final String getColumnName()
	{
		return column.id;
	}
	
	abstract Column createColumn(Table table, String name, boolean notNull);
	
	public static class Option
	{
		public final boolean readOnly;
		public final boolean unique;
		public final boolean mandatory;

		Option(final boolean readOnly, final boolean unique, final boolean mandatory)
		{
			this.readOnly = readOnly;
			this.unique = unique;
			this.mandatory = mandatory;
		}
	}
	
}


