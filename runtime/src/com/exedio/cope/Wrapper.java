/*
 * Copyright (C) 2004-2007  exedio GmbH (www.exedio.com)
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
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class Wrapper
{
	private final java.lang.reflect.Type methodReturnType;
	private final String methodName;
	private ArrayList<Class> parameterTypes;
	private ArrayList<String> parameterNames;
	private final String comment;
	private final String modifier;
	private final String modifierComment;
	private final Class[] throwsClause;
	private final String methodWrapperPattern;
	private ArrayList<String> comments = null;
	
	public Wrapper(
			final java.lang.reflect.Type methodReturnType,
			final String methodName,
			final String comment,
			final String modifier,
			final String modifierComment)
	{
		this(methodReturnType, methodName, comment, modifier, modifierComment, null, null);
	}

	public Wrapper(
			final java.lang.reflect.Type methodReturnType,
			final String methodName,
			final String comment,
			final String modifier,
			final String modifierComment,
			final Class[] throwsClause)
	{
		this(methodReturnType, methodName, comment, modifier, modifierComment, throwsClause, null);
	}

	public Wrapper(
			final java.lang.reflect.Type methodReturnType,
			final String methodName,
			final String comment,
			final String modifier,
			final String modifierComment,
			final String methodWrapperPattern)
	{
		this(methodReturnType, methodName, comment, modifier, modifierComment, null, methodWrapperPattern);
	}

	public Wrapper(
			final java.lang.reflect.Type methodReturnType,
			final String methodName,
			final String comment,
			final String modifier,
			final String modifierComment,
			final Class[] throwsClause,
			final String methodWrapperPattern)
	{
		this.methodReturnType = methodReturnType;
		this.methodName = methodName;
		this.comment = comment;
		this.modifier = modifier;
		this.modifierComment = modifierComment;
		this.throwsClause = throwsClause;
		this.methodWrapperPattern = methodWrapperPattern;
		
		if(methodReturnType==null)
			throw new NullPointerException("methodReturnType must not be null");
		if(comment==null)
			throw new NullPointerException("comment must not be null");
	}

	public java.lang.reflect.Type getMethodReturnType()
	{
		return methodReturnType;
	}

	public String getMethodName()
	{
		return methodName;
	}

	public Wrapper addParameter(final Class type)
	{
		return addParameter(type, null);
	}
	
	public Wrapper addParameter(final Class type, final String name)
	{
		if(type==null)
			throw new NullPointerException("type must not be null");
		// name can be null, then the feature name is used
		
		if(parameterTypes==null)
		{
			parameterTypes = new ArrayList<Class>();
			parameterNames = new ArrayList<String>();
		}
		parameterTypes.add(type);
		parameterNames.add(name);

		return this;
	}

	public List<Class> getParameterTypes()
	{
		return
			parameterTypes!=null
			? Collections.unmodifiableList(parameterTypes)
			: Collections.<Class>emptyList();
	}

	public List<String> getParameterNames()
	{
		return
			parameterNames!=null
			? Collections.unmodifiableList(parameterNames)
			: Collections.<String>emptyList();
	}

	public String getComment()
	{
		return comment;
	}

	public String getModifier()
	{
		return modifier;
	}

	public String getModifierComment()
	{
		return modifierComment;
	}

	public Collection<Class> getThrowsClause()
	{
		return
			throwsClause!=null
			? Collections.unmodifiableCollection(Arrays.asList(throwsClause))
			: Collections.<Class>emptySet();
	}

	public String getMethodWrapperPattern()
	{
		return methodWrapperPattern;
	}
	
	public Wrapper addComment(final String comment)
	{
		if(comment==null)
			throw new NullPointerException("comment must not be null");
		
		if(comments==null)
			comments = new ArrayList<String>();
		comments.add(comment);

		return this;
	}
	
	public List<String> getComments()
	{
		return
			comments!=null
			? Collections.unmodifiableList(comments)
			: Collections.<String>emptyList();
	}
	
	public class TypeVariable0 { /* OK, just a placeholder */ };
}
