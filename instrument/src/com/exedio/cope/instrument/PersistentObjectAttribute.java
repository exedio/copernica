
package com.exedio.cope.instrument;

import java.util.List;

public final class PersistentObjectAttribute extends PersistentAttribute
{
	public PersistentObjectAttribute(
			final JavaAttribute javaAttribute,
			final Class typeClass,
			final List initializerArguments,
			final List qualifiers)
		throws InjectorParseException
	{
		super(javaAttribute, typeClass, getPersistentType(initializerArguments), initializerArguments, qualifiers);
	}
	
	private static final String getPersistentType(final List initializerArguments)
	{
		if(initializerArguments.size()<=1)
			throw new RuntimeException("second argument required");
		final String secondArgument =  (String)initializerArguments.get(1);
		if(!secondArgument.endsWith(".class"))
			throw new RuntimeException("second argument must end with .class: \'"+secondArgument+'\'');
		return secondArgument.substring(0, secondArgument.length()-".class".length());
	}

}
