/*
 * Copyright (C) 2004-2008  exedio GmbH (www.exedio.com)
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

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.List;

import com.exedio.cope.instrument.Wrapper;
import com.exedio.cope.util.CharacterSet;

public abstract class Feature
{
	private Type<? extends Item> type;
	private static final CharacterSet NAME_CHARACTER_SET = new CharacterSet('0', '9', 'A', 'Z', 'a', 'z');
	private String name;
	private String id;
	private java.lang.reflect.Field annotationField = null;
	
	/**
	 * Is called in the constructor of the containing type.
	 */
	void initialize(final Type<? extends Item> type, final String name)
	{
		{
			final int l = name.length();
			for(int i = 0; i<l; i++)
				if(!NAME_CHARACTER_SET.contains(name.charAt(i)))
					throw new IllegalArgumentException("name >" + name + "< of feature in type " + type + " contains illegal character >"+ name.charAt(i) + "< at position " + i);
		}
		
		if(this.type!=null)
			throw new IllegalStateException("feature already initialized: " + id);
		
		assert type!=null;
		assert name!=null;
		assert this.type==null;
		assert this.name==null;
		assert this.id==null;

		this.type = type;
		this.name = Model.intern(name);
		this.id =   Model.intern(type.id + '.' + name);
		
		type.registerInitialization(this);
	}
	
	final boolean isInitialized()
	{
		return type!=null;
	}
	
	public Type<? extends Item> getType()
	{
		if(this.type==null)
			throw new FeatureNotInitializedException();

		return type;
	}
	
	public final String getName()
	{
		if(this.type==null)
			throw new FeatureNotInitializedException();
		assert name!=null;

		return name;
	}
	
	/**
	 * @see Model#getFeature(String)
	 */
	public final String getID()
	{
		if(this.type==null)
			throw new FeatureNotInitializedException();
		assert id!=null;

		return id;
	}
	
	final void setAnnotationField(final java.lang.reflect.Field annotationField)
	{
		assert this.annotationField==null;
		this.annotationField = annotationField;
	}
	
	/**
	 * @see Class#getAnnotation(Class)
	 */
	public final <T extends Annotation> T getAnnotation(final Class<T> annotationClass)
	{
		return
			annotationField!=null
			? annotationField.getAnnotation(annotationClass)
			: null;
	}
	
	public List<Wrapper> getWrappers()
	{
		return Collections.<Wrapper>emptyList();
	}
	
	void toStringNonInitialized(final StringBuilder bf)
	{
		bf.append(super.toString());
	}
	
	@Override
	public final String toString()
	{
		if(type!=null)
		{
			assert id!=null;
			return id;
		}
		else
		{
			final StringBuilder bf = new StringBuilder();
			toStringNonInitialized(bf);
			return bf.toString();
		}
	}
	
	public final void toString(final StringBuilder bf, final Type defaultType)
	{
		if(type!=null)
			bf.append((defaultType==type) ? name : id);
		else
			toStringNonInitialized(bf);
	}
}
