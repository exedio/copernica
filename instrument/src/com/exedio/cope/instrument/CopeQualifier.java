package com.exedio.cope.instrument;

import java.util.List;

final class CopeQualifier
{
	final String name;
	final String qualifierClassString;

	final CopeClass qualifierClass;
	final CopeUniqueConstraint uniqueConstraint;

	final CopeAttribute[] keyAttributes;

	public CopeQualifier(final String name, final CopeClass copeClass, final List initializerArguments)
		throws InjectorParseException
	{
		this.name = name;
		if(initializerArguments.size()!=1)
			throw new InjectorParseException("Qualifier must have 1 argument, but has "+initializerArguments);
		final String uniqueConstraintString = (String)initializerArguments.get(0);

		final int dot = uniqueConstraintString.lastIndexOf('.');
		if(dot<0)
			throw new InjectorParseException("Qualifier argument must have dot, but is "+uniqueConstraintString);
		this.qualifierClassString = uniqueConstraintString.substring(0, dot);

		//System.out.println("--------- qualifierClassString: "+qualifierClassString);
		//Sstem.out.println("--------- key: "+key);
		//System.out.println("--------- qualifyUnique: "+qualifyUnique);
		this.qualifierClass = copeClass.javaClass.file.repository.getCopeClass(qualifierClassString);
		//System.out.println("--------- qualifierClass: "+qualifierClass.javaClass.name);
		
		final String constraintName = uniqueConstraintString.substring(dot+1);
		//System.out.println("--------- keyString: "+keyString);
		
		this.uniqueConstraint = qualifierClass.getCopeUniqueConstraint(constraintName);
		if(uniqueConstraint==null)
			throw new InjectorParseException("unique constraint not found "+uniqueConstraintString);
		
		final CopeAttribute[] uniqueAttributes = uniqueConstraint.copeAttributes;
		if(uniqueAttributes.length<2)
			throw new RuntimeException(uniqueAttributes.toString());
		
		this.keyAttributes = new CopeAttribute[uniqueAttributes.length-1];
		for(int i = 0; i<this.keyAttributes.length; i++)
			this.keyAttributes[i] = uniqueAttributes[i+1];

		copeClass.addQualifier(this);
	}

}
