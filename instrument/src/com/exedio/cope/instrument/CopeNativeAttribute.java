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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;

import com.exedio.cope.Attribute;
import com.exedio.cope.BooleanAttribute;
import com.exedio.cope.DateAttribute;
import com.exedio.cope.DayAttribute;
import com.exedio.cope.DoubleAttribute;
import com.exedio.cope.Feature;
import com.exedio.cope.IntegerFunction;
import com.exedio.cope.LengthViolationException;
import com.exedio.cope.LongAttribute;
import com.exedio.cope.StringFunction;
import com.exedio.cope.util.Day;

final class CopeNativeAttribute extends CopeAttribute
{
	final Class typeClass;
	final String nativeType;

	public CopeNativeAttribute(
			final JavaAttribute javaAttribute,
			final String name,
			Class typeClass,
			final List initializerArguments,
			final String docComment)
		throws InjectorParseException
	{
		super(javaAttribute, name, typeClass, getPersistentType(typeClass), initializerArguments, docComment);
		
		this.typeClass = normalizeTypeClass(typeClass);
		this.nativeType = (String)toNativeTypeMapping.get(this.typeClass);
	}
	
	public CopeNativeAttribute(
			final JavaAttribute javaAttribute,
			Class typeClass,
			final List initializerArguments,
			final String docComment)
		throws InjectorParseException
	{
		this(javaAttribute, javaAttribute.name, typeClass, initializerArguments, docComment);
	}
	
	private static final Class normalizeTypeClass(final Class typeClass)
	{
		if(StringFunction.class.isAssignableFrom(typeClass))
			return StringFunction.class;
		else if(IntegerFunction.class.isAssignableFrom(typeClass))
			return IntegerFunction.class;
		else
			return typeClass;
	}

	private static final String getPersistentType(final Class typeClass)
	{
		final String result = (String)toPersistentTypeMapping.get(normalizeTypeClass(typeClass));

		if(result==null)
			throw new RuntimeException(typeClass.toString());

		return result;
	}

	private static final HashMap toPersistentTypeMapping = new HashMap(3);
	private static final HashMap toNativeTypeMapping = new HashMap(3);
	private static final HashMap toBoxingPrefixMapping = new HashMap(3);
	private static final HashMap toBoxingPostfixMapping = new HashMap(3);
	private static final HashMap toUnboxingPrefixMapping = new HashMap(3);
	private static final HashMap toUnboxingPostfixMapping = new HashMap(3);
	
	private static final void fillNativeTypeMap(final Class typeClass, final Class persistentType, final Class nativeType,
															  final String boxingPrefix, final String boxingPostfix,
															  final String unboxingPrefix, final String unboxingPostfix)
	{
		if(persistentType.isPrimitive())
			throw new RuntimeException(nativeType.toString());

		toPersistentTypeMapping.put(typeClass, persistentType.getName());

		if(nativeType!=null)
		{
			if(!nativeType.isPrimitive())
				throw new RuntimeException(nativeType.toString());

			toNativeTypeMapping.put(typeClass, nativeType.getName());
		}
		
		toBoxingPrefixMapping.put(typeClass, boxingPrefix);
		toBoxingPostfixMapping.put(typeClass, boxingPostfix);
		toUnboxingPrefixMapping.put(typeClass, unboxingPrefix);
		toUnboxingPostfixMapping.put(typeClass, unboxingPostfix);
	}
	
	private static final void fillNativeTypeMap(final Class typeClass, final Class persistentType, final Class nativeType)
	{
		fillNativeTypeMap(typeClass, persistentType, nativeType,
				"new "+persistentType.getName()+"(", ")", "(", ")."+nativeType.getName()+"Value()");
	}

	private static final void fillNativeTypeMap(final Class typeClass, final Class persistentType)
	{
		fillNativeTypeMap(typeClass, persistentType, null, null, null, null, null);
	}

	static
	{
		fillNativeTypeMap(BooleanAttribute.class, Boolean.class, boolean.class, "(", "?"+Boolean.class.getName()+".TRUE:"+Boolean.class.getName()+".FALSE)", "(", ").booleanValue()");
		fillNativeTypeMap(LongAttribute.class,    Long.class,    long.class);
		fillNativeTypeMap(IntegerFunction.class,  Integer.class, int.class);
		fillNativeTypeMap(DoubleAttribute.class,  Double.class,  double.class);
		fillNativeTypeMap(StringFunction.class,   String.class);
		fillNativeTypeMap(DateAttribute.class,    Date.class);
		fillNativeTypeMap(DayAttribute.class,     Day.class);
	}
	
	public final String getBoxedType()
	{
		return isBoxed() ? nativeType : persistentType;
	}
	
	public final boolean isBoxed()
	{
		final Feature instance = getInstance();
		final boolean notNull = instance instanceof Attribute && ((Attribute)instance).isMandatory();

		return (notNull && nativeType!=null);
	}
	
	public final String getBoxingPrefix()
	{
		if(!isBoxed())
			throw new RuntimeException();

		return (String)toBoxingPrefixMapping.get(typeClass);
	}
	
	public final String getBoxingPostfix()
	{
		if(!isBoxed())
			throw new RuntimeException();

		return (String)toBoxingPostfixMapping.get(typeClass);
	}
	
	public final String getUnBoxingPrefix()
	{
		if(!isBoxed())
			throw new RuntimeException();

		return (String)toUnboxingPrefixMapping.get(typeClass);
	}
	
	public final String getUnBoxingPostfix()
	{
		if(!isBoxed())
			throw new RuntimeException();

		return (String)toUnboxingPostfixMapping.get(typeClass);
	}
	
	protected void fillExceptionsThrownByGenericSetter(final SortedSet result)
	{
		super.fillExceptionsThrownByGenericSetter(result);
		if(typeClass==StringFunction.class)
			result.add(LengthViolationException.class);
	}
}
