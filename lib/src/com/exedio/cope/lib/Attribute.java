package com.exedio.cope.lib;

import java.util.Collections;
import java.util.List;

public abstract class Attribute implements Feature
{
	private final boolean readOnly;
	private final boolean notNull;
	private final UniqueConstraint singleUniqueConstraint;

	protected Attribute(final Option option)
	{
		this.readOnly = option.readOnly;
		this.notNull = option.notNull;
		this.singleUniqueConstraint =
			option.unique ?
				new UniqueConstraint((ObjectAttribute)this) :
				null;
	}
	
	public final boolean isReadOnly()
	{
		return readOnly;
	}
	
	public final boolean isNotNull()
	{
		return notNull;
	}
	
	/**
	 * Returns the unique constraint of this attribute,
	 * if there is a unique constraint covering this attribute and this attribute only.
	 * Does return null, if there is no such unique constraint,
	 * i.e. this attribute is not covered by any unique constraint,
	 * or this attribute is covered by a unique constraint covering more
	 * attributes than this attribute.
	 */
	public UniqueConstraint getSingleUniqueConstaint()
	{
		return singleUniqueConstraint;
	}
	
	// second initialization phase ---------------------------------------------------

	private Type type;
	private String name;
	private List columns;
	private Column mainColumn;
	
	final void initialize(final Type type, final String name)
	{
		if(type==null)
			throw new RuntimeException();
		if(name==null)
			throw new RuntimeException();

		if(this.type!=null)
			throw new RuntimeException();
		if(this.name!=null)
			throw new RuntimeException();

		this.type = type;
		this.name = name.intern();
		postInitialize();
	}
	
	protected void postInitialize()
	{
	}
	
	final void materialize(final Table table)
	{
		if(table==null)
			throw new NullPointerException();

		if(this.name==null)
			throw new RuntimeException();
		if(this.columns!=null)
			throw new RuntimeException();
		if(this.mainColumn!=null)
			throw new RuntimeException();

		this.columns =
				Collections.unmodifiableList(createColumns(table, name, notNull));
		this.mainColumn = this.columns.isEmpty() ? null : (Column)columns.iterator().next();
	}
	
	public final Type getType()
	{
		if(this.type==null)
			throw new RuntimeException();

		return type;
	}
	
	public final String getName()
	{
		if(this.type==null)
			throw new RuntimeException();

		return name;
	}
	
	final List getColumns()
	{
		if(this.type==null)
			throw new RuntimeException();

		return columns;
	}
	
	final Column getMainColumn()
	{
		if(this.type==null)
			throw new RuntimeException();

		return mainColumn;
	}
	
	public final String toString()
	{
		// should be precomputed
		final StringBuffer buf = new StringBuffer();
		buf.append(name);
		buf.append('{');
		boolean first = true;
		if(readOnly)
		{
			if(first)
				first = false;
			else
				buf.append(',');
			buf.append("read-only");
		}
		if(notNull)
		{
			if(first)
				first = false;
			else
				buf.append(',');
			buf.append("not-null");
		}
		buf.append('}');

		return buf.toString();
	}
	
	protected abstract List createColumns(Table table, String name, boolean notNull);
	
	public static class Option
	{
		public final boolean readOnly;
		public final boolean notNull;
		public final boolean unique;

		Option(final boolean readOnly, final boolean notNull, final boolean unique)
		{
			this.readOnly = readOnly;
			this.notNull = notNull;
			this.unique = unique;
		}
	}
	
}


