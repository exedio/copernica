package com.exedio.cope.lib;

import com.exedio.cope.lib.Database;

public abstract class Attribute
{
	public final AttributeMapping mapping;

	public Attribute()
	{
		this.mapping = null;
	}
	
	public Attribute(final AttributeMapping mapping)
	{
		this.mapping = mapping;
	}
	

	private boolean initialized = false;
	
	private String name;
	private boolean readOnly;
	private boolean notNull;
	private String persistentQualifier;
	
	public void initialize(final String name, final boolean readOnly, final boolean notNull)
	{
		if(initialized)
			throw new RuntimeException();

		this.name = name;
		this.readOnly = readOnly;
		this.notNull = notNull;

		initialized = true;
		this.persistentQualifier = Database.theInstance.makePersistentQualifier(this);
	}
	
	public final String getName()
	{
		return name;
	}
	
	public final boolean isReadOnly()
	{
		return readOnly;
	}
	
	public final boolean isNotNull()
	{
		return notNull;
	}
	
	public String getPersistentQualifier()
	{
		return persistentQualifier;
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
		if(mapping!=null)
		{
			buf.append('=');
			buf.append(mapping.toString());
		}
		return buf.toString();
	}
	
	abstract Object databaseToCache(Object cell);
	public abstract Object cacheToDatabase(Object cache);

}


