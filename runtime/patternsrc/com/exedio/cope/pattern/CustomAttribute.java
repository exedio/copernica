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

package com.exedio.cope.pattern;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import com.exedio.cope.Attribute;
import com.exedio.cope.FunctionAttribute;
import com.exedio.cope.Item;
import com.exedio.cope.Pattern;
import com.exedio.cope.SetValue;
import com.exedio.cope.Settable;

public abstract class CustomAttribute<E>
	extends Pattern
	implements Settable<E>
{
	private final FunctionAttribute<? extends Object>[] storages;
	private final List<FunctionAttribute<? extends Object>> storageList;
	private final boolean initial;
	private final boolean isFinal;
	private final Method getter;
	private final Method setter;
	private final Class valueType;
	
	@SuppressWarnings("unchecked") // OK: no generic array creation
	public CustomAttribute(final FunctionAttribute storage)
	{
		this(new FunctionAttribute[]{storage});
	}
	
	@SuppressWarnings("unchecked") // OK: no generic array creation
	public CustomAttribute(final FunctionAttribute storage1, final FunctionAttribute storage2)
	{
		this(new FunctionAttribute[]{storage1, storage2});
	}
	
	public CustomAttribute(final FunctionAttribute<? extends Object>[] storages)
	{
		this.storages = storages;
		this.storageList = Collections.unmodifiableList(Arrays.asList(storages));

		{
			boolean initial = false;
			boolean isFinal = false;
			for(FunctionAttribute<? extends Object> storage : storages)
			{
				registerSource(storage);
				initial = initial || storage.isInitial();
				isFinal = isFinal || storage.isFinal();
			}
			this.initial = initial;
			this.isFinal = isFinal;
		}
		
		final Method[] methods = getClass().getDeclaredMethods();
		Method getter = null; 
		Method setter = null;
		for(int i = 0; i<methods.length; i++)
		{
			final Method m = methods[i];
			final String methodName = m.getName();
			if("get".equals(methodName))
			{
				// TODO test all the exceptions
				if(getter!=null)
					throw new RuntimeException("more than one getter method:"+getter+" and "+m);
				if(m.getParameterTypes().length!=storages.length)
					throw new RuntimeException("number of parameters of getter method must be equal to number of storages");
				getter = m;
			}
			else if("set".equals(methodName))
			{
				if(setter!=null)
					throw new RuntimeException("more than one setter method:"+setter+" and "+m);
				if(m.getParameterTypes().length!=1)
					throw new RuntimeException("number of parameters of setter method must be 1");
				if(m.getReturnType()!=(new SetValue[0]).getClass() && storages.length!=1)
					throw new RuntimeException("oops");
				setter = m;
			}
		}
		if(getter==null)
			throw new RuntimeException("no suitable getter method found for custom attribute");
		if(setter==null)
			throw new RuntimeException("no suitable setter method found for custom attribute");
		
		getter.setAccessible(true);
		setter.setAccessible(true);
		
		this.getter = getter;
		this.setter = setter;
		this.valueType = getter.getReturnType();
	}
	
	public final List<FunctionAttribute<? extends Object>> getStorages()
	{
		return storageList;
	}
	
	public final boolean isInitial()
	{
		return initial;
	}
	
	public final boolean isFinal()
	{
		return isFinal;
	}
	
	public final SortedSet<Class> getSetterExceptions()
	{
		final SortedSet<Class> result = storages[0].getSetterExceptions();
		for(int i = 1; i<storages.length; i++)
			result.addAll(storages[i].getSetterExceptions());
		return result;
	}
	
	public final void initialize()
	{
		final String name = getName();

		for(int i = 0; i<storages.length; i++)
			if(!storages[i].isInitialized())
				initialize(storages[i], name+"Storage"+i);
	}
	
	public final Class getValueType()
	{
		assert valueType!=null;
		return valueType;
	}

	@SuppressWarnings("unchecked") // OK: reflection does not support generics
	private E cast(final Object o)
	{
		return (E)o;
	}
	
	public final E get(final Item item)
	{
		final Object[] params = new Object[storages.length];
		for(int i = 0; i<params.length; i++)
			params[i] = storages[i].get(item);
		
		try
		{
			return cast(getter.invoke(this, params));
		}
		catch(IllegalArgumentException e)
		{
			throw new RuntimeException(e);
		}
		catch(IllegalAccessException e)
		{
			throw new RuntimeException(e);
		}
		catch(InvocationTargetException e)
		{
			throw new RuntimeException(e);
		}
	}

	public final void set(final Item item, final E value) throws CustomAttributeException
	{
		item.set(convert(execute(value, item)));
	}
	
	private static final SetValue[] convert(Map<? extends Attribute, ? extends Object> map)
	{
		final SetValue[] result = new SetValue[map.size()];
		int n = 0;
		for(Attribute attribute : map.keySet())
			result[n++] = new SetValue(attribute, map.get(attribute));
		
		return result;
	}
	
	public final Map<? extends FunctionAttribute, ? extends Object> execute(final E value, final Item exceptionItem) throws CustomAttributeException
	{
		final Object result;
		try
		{
			result = setter.invoke(this, new Object[]{value});
		}
		catch(IllegalArgumentException e)
		{
			throw new RuntimeException(e);
		}
		catch(IllegalAccessException e)
		{
			throw new RuntimeException(e);
		}
		catch(InvocationTargetException e)
		{
			final Throwable t = e.getCause();
			if(t instanceof RuntimeException)
				throw (RuntimeException)t;
			else
				throw new CustomAttributeException(this, exceptionItem, e.getCause());
		}
		
		if(storages.length==1)
			return Collections.singletonMap(storages[0], result);
		else
		{
			final SetValue[] resultArray = (SetValue[])result;
			final HashMap<FunctionAttribute, Object> resultMap = new HashMap<FunctionAttribute, Object>();
			for(int i = 0; i<resultArray.length; i++)
				resultMap.put((FunctionAttribute)resultArray[i].settable, resultArray[i].value);
			return resultMap;
		}
	}
	
	public final SetValue map(final E value)
	{
		return new SetValue(this, value);
	}

}
