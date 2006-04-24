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

import java.util.SortedSet;

import com.exedio.cope.Attribute;
import com.exedio.cope.BooleanAttribute;
import com.exedio.cope.DateAttribute;
import com.exedio.cope.Feature;
import com.exedio.cope.FunctionAttribute;
import com.exedio.cope.MandatoryViolationException;
import com.exedio.cope.Settable;
import com.exedio.cope.View;

abstract class CopeAttribute extends CopeFeature
{
	/**
	 * The persistent type of this attribute.
	 */
	final String persistentType;

	final Option getterOption;
	final Option setterOption;
	final boolean initial;
	
	CopeAttribute(
			final CopeType parent,
			final JavaAttribute javaAttribute,
			final Class typeClass,
			final String persistentType)
		throws InjectorParseException
	{
		super(parent, javaAttribute);
		this.persistentType = persistentType;
		
		final String docComment = javaAttribute.getDocComment();
		this.getterOption = new Option(Injector.findDocTagLine(docComment, TAG_GETTER), true);
		this.setterOption = new Option(Injector.findDocTagLine(docComment, TAG_SETTER), true);
		this.initial = Injector.hasTag(docComment, TAG_INITIAL);
	}
	
	final int getGeneratedGetterModifier()
	{
		return getterOption.getModifier(modifier);
	}

	/**
	 * Returns the type of this attribute to be used in accessor (setter/getter) methods.
	 * Differs from {@link #getPersistentType() the persistent type},
	 * if and only if the attribute is {@link #isBoxed() boxed}.
	 */
	String getBoxedType()
	{
		return persistentType;
	}
	
	/**
	 * Returns, whether the persistent type is &quot;boxed&quot; into a native type.
	 * This happens if the attribute is mandatory
	 * and the persistent type is convertable to a native types (int, double, boolean).
	 * @see #getBoxedType()
	 */
	boolean isBoxed()
	{
		return false;
	}
	
	String getBoxingPrefix()
	{
		throw new RuntimeException();
	}
	
	String getBoxingPostfix()
	{
		throw new RuntimeException();
	}
	
	String getUnBoxingPrefix()
	{
		throw new RuntimeException();
	}
	
	String getUnBoxingPostfix()
	{
		throw new RuntimeException();
	}
	
	// TODO: put into rtlib
	final boolean isInitial()
	{
		if(initial)
			return true;
		
		final Feature instance = getInstance();
		final boolean isfinal = instance instanceof Attribute && ((Attribute)instance).isFinal();
		final boolean notNull = instance instanceof Attribute && ((Attribute)instance).isMandatory();
		final boolean isView = instance instanceof View;

		return (isfinal || notNull) && !isView;
	}

	// TODO: put into rtlib
	private final boolean isWriteable()
	{
		final Feature instance = getInstance();
		final boolean isfinal = instance instanceof Attribute && ((Attribute)instance).isFinal();
		final boolean isView = instance instanceof View;

		return !isfinal && !isView;
	}
	
	final boolean isImplicitlyUnique()
	{
		final Feature instance = getInstance();
		return instance instanceof FunctionAttribute && ((FunctionAttribute)instance).getImplicitUniqueConstraint()!=null;
	}

	final boolean isTouchable()
	{
		final Object instance = getInstance();

		return instance instanceof DateAttribute;
	}

	final boolean hasIsGetter()
	{
		final Feature instance = getInstance();
		final boolean isBoolean = instance instanceof BooleanAttribute;

		return isBoolean && getterOption.booleanAsIs;
	}

	final boolean hasGeneratedSetter()
	{
		return isWriteable() && setterOption.exists;
	}
	
	final int getGeneratedSetterModifier()
	{
		return setterOption.getModifier(modifier);
	}
	
	final SortedSet<Class> getSetterExceptions()
	{
		final Feature instance = getInstance();
		final SortedSet<Class> result = ((Settable<Object>)instance).getSetterExceptions();
		if(isBoxed())
			result.remove(MandatoryViolationException.class);
		return result;
	}
	
	final SortedSet<Class> getToucherExceptions()
	{
		final SortedSet<Class> result = getSetterExceptions();
		result.remove(MandatoryViolationException.class);
		return result;
	}

}
