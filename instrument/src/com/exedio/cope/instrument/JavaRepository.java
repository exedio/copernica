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

package com.exedio.cope.instrument;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bsh.UtilEvalError;

import com.exedio.cope.Attribute;
import com.exedio.cope.BooleanAttribute;
import com.exedio.cope.DataAttribute;
import com.exedio.cope.DateAttribute;
import com.exedio.cope.DayAttribute;
import com.exedio.cope.DoubleAttribute;
import com.exedio.cope.EnumAttribute;
import com.exedio.cope.Function;
import com.exedio.cope.IntegerFunction;
import com.exedio.cope.Item;
import com.exedio.cope.ItemAttribute;
import com.exedio.cope.LongAttribute;
import com.exedio.cope.StringAttribute;
import com.exedio.cope.StringFunction;
import com.exedio.cope.UniqueConstraint;
import com.exedio.cope.pattern.Hash;
import com.exedio.cope.pattern.Media;
import com.exedio.cope.pattern.Qualifier;
import com.exedio.cope.pattern.Relation;
import com.exedio.cope.pattern.Vector;
import com.exedio.cope.pattern.VectorRelation;

final class JavaRepository
{
	/**
	 * Defines a name space, that does not depend on
	 * information gathered by the instrumentor,
	 * thus can be used in build stage.
	 * Using this in JavaFile greatly reduces number of top name spaces,
	 * for which a new BshClassManager must be created.
	 */
	final CopeNameSpace externalNameSpace = new CopeNameSpace(null);
	
	// reusing externalNameSpace is more efficient than another root nameSpace
	final CopeNameSpace nameSpace = new NameSpace(externalNameSpace);
	
	/**
	 * Distiguishes two stages in life cycle of this repository,
	 * and its contents:
	 * building the repository and querying the repository.
	 */
	private boolean buildStage = true;
	
	private boolean generateStage = false;
	
	private final ArrayList<JavaFile> files = new ArrayList<JavaFile>();
	private final HashMap<String, JavaClass> javaClassByShortName = new HashMap<String, JavaClass>();
	private final HashMap<String, JavaClass> javaClassByFullName = new HashMap<String, JavaClass>();
	
	private final HashMap<JavaClass, CopeType> copeTypeByJavaClass = new HashMap<JavaClass, CopeType>();
	
	void endBuildStage()
	{
		assert buildStage;
		assert !generateStage;
		
		buildStage = false;
		
		// TODO put this into a new class CopeType
		for(final JavaClass javaClass : javaClassByFullName.values())
		{
			if(javaClass.isInterface())
				continue;
			
			if(isItem(javaClass))
			{
				final CopeType type = new CopeType(javaClass);

				for(final JavaAttribute javaAttribute : javaClass.getAttributes())
				{
					final int modifier = javaAttribute.modifier;

					if(Modifier.isFinal(modifier) && Modifier.isStatic(modifier))
					{
						final Class typeClass = javaAttribute.file.findTypeExternally(javaAttribute.type);

						if(typeClass!=null)
						{
							if(Function.class.isAssignableFrom(typeClass)||Attribute.class.isAssignableFrom(typeClass))
							{
								if(
									IntegerFunction.class.isAssignableFrom(typeClass) ||
									LongAttribute.class.equals(typeClass) ||
									DoubleAttribute.class.equals(typeClass) ||
									BooleanAttribute.class.equals(typeClass) ||
									DateAttribute.class.equals(typeClass) ||
									DayAttribute.class.equals(typeClass) ||
									StringFunction.class.isAssignableFrom(typeClass))
								{
									new CopeNativeAttribute(type, javaAttribute, typeClass);
								}
								else if(
									EnumAttribute.class.equals(typeClass)||
									ItemAttribute.class.equals(typeClass))
								{
									new CopeObjectAttribute(type, javaAttribute, typeClass);
								}
								else if(DataAttribute.class.equals(typeClass))
								{
									new CopeDataAttribute(type, javaAttribute, typeClass);
								}
								else
									throw new RuntimeException(typeClass.toString());
							}
							else if(UniqueConstraint.class.isAssignableFrom(typeClass))
								new CopeUniqueConstraint(type, javaAttribute);
							else if(Qualifier.class.isAssignableFrom(typeClass))
								new CopeQualifier(type, javaAttribute);
							else if(Hash.class.isAssignableFrom(typeClass))
								new CopeHash(type, javaAttribute);
							else if(Vector.class.isAssignableFrom(typeClass))
								new CopeVector(type, javaAttribute);
							else if(Media.class.isAssignableFrom(typeClass))
								new CopeMedia(type, javaAttribute);
							else if(Relation.class.isAssignableFrom(typeClass))
								new CopeRelation(type, javaAttribute, false);
							else if(VectorRelation.class.isAssignableFrom(typeClass))
								new CopeRelation(type, javaAttribute, true);
						}
					}
				}
			}
		}
		
		for(final CopeType ct : copeTypeByJavaClass.values())
			ct.endBuildStage();
		
		generateStage = true;
	}
	
	boolean isBuildStage()
	{
		return buildStage;
	}

	boolean isGenerateStage()
	{
		return generateStage;
	}
	
	boolean isItem(JavaClass javaClass)
	{
		//System.out.println("--------------"+javaClass.getFullName());
		while(true)
		{
			final String classExtends = javaClass.classExtends;
			if(classExtends==null)
				return false;
			
			//System.out.println("--------------**"+javaClass.getFullName());
			{
				final Class extendsClass = javaClass.file.findTypeExternally(classExtends);
				//System.out.println("--------------*1"+extendsClass);
				if(extendsClass!=null)
					return Item.class.isAssignableFrom(extendsClass);
			}
			{
				final JavaClass byName = getJavaClass(classExtends);
				//System.out.println("--------------*2"+byName);
				if(byName!=null)
				{
					javaClass = byName;
					continue;
				}
			}
			System.out.println("unknown type " + classExtends + " in " + javaClass);
			return false;
		}
	}

	void add(final JavaFile file)
	{
		assert buildStage;
		files.add(file);
	}
	
	final List<JavaFile> getFiles()
	{
		assert !buildStage;
		return files;
	}
	
	void add(final JavaClass javaClass)
	{
		assert buildStage && !generateStage;
		
		//final JavaClass previous =
		javaClassByShortName.put(javaClass.name, javaClass);
		
		//if(previous!=null) System.out.println("collision:"+previous.getFullName()+','+javaClass.getFullName());
		
		if(javaClassByFullName.put(javaClass.getFullName(), javaClass)!=null)
			throw new RuntimeException(javaClass.getFullName());
	}
	
	final JavaClass getJavaClass(final String name)
	{
		return (name.indexOf('.')<0) ? javaClassByShortName.get(name) : javaClassByFullName.get(name);
	}
	
	void add(final CopeType copeType)
	{
		assert !buildStage;
		assert !generateStage;
		
		if(copeTypeByJavaClass.put(copeType.javaClass, copeType)!=null)
			throw new RuntimeException(copeType.javaClass.getFullName());
		//System.out.println("--------- put cope type: "+name);
	}
	
	CopeType getCopeType(final String className)
	{
		assert !buildStage;
		
		final JavaClass javaClass = getJavaClass(className);
		if(javaClass==null)
			throw new RuntimeException("no java class for "+className);
		
		final CopeType result = copeTypeByJavaClass.get(javaClass);
		if(result==null)
			throw new RuntimeException("no cope type for "+className);
		
		return result;
	}

	final class NameSpace extends CopeNameSpace
	{
		private static final long serialVersionUID = 8362587526354862l;
		
		NameSpace(final CopeNameSpace parent)
		{
			super(parent);
		}
		
		public Class getClass(final String name) throws UtilEvalError
		{
			assert generateStage;
			
			final Class superResult = super.getClass(name);
			if(superResult!=null)
				return superResult;
			
			if(name.endsWith("Hash")) // TODO this is a hack
				return DummyHash.class;
			
			return null;
		}
	}
	
	public static final class DummyHash extends Hash
	{
		public DummyHash(final StringAttribute storage)
		{
			super(storage);
		}
		
		public DummyHash(final com.exedio.cope.Attribute.Option storageOption)
		{
			super(storageOption);
		}
		
		public String hash(final String plainText)
		{
			throw new RuntimeException(); // should not happen
		}
	}
}
