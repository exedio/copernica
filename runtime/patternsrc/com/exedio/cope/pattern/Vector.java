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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.exedio.cope.SetValue;
import com.exedio.cope.EqualCondition;
import com.exedio.cope.FinalViolationException;
import com.exedio.cope.FunctionAttribute;
import com.exedio.cope.Item;
import com.exedio.cope.LengthViolationException;
import com.exedio.cope.MandatoryViolationException;
import com.exedio.cope.NotEqualCondition;
import com.exedio.cope.Pattern;
import com.exedio.cope.Settable;
import com.exedio.cope.UniqueViolationException;
import com.exedio.cope.search.AndCondition;
import com.exedio.cope.search.OrCondition;

public final class Vector extends Pattern implements Settable<Collection>
{
	private final FunctionAttribute[] sources;

	public Vector(final FunctionAttribute[] sources)
	{
		this.sources = sources;

		for(int i = 0; i<sources.length; i++)
			registerSource(sources[i]);
	}
	
	public Vector(final FunctionAttribute template, final int length)
	{
		this(template2Sources(template, length));
	}
	
	private static final FunctionAttribute[] template2Sources(final FunctionAttribute template, final int length)
	{
		final FunctionAttribute[] result = new FunctionAttribute[length];
		
		for(int i = 0; i<length; i++)
			result[i] = template.copyFunctionAttribute();

		return result;
	}
	
	public void initialize()
	{
		final String name = getName();
		
		for(int i = 0; i<sources.length; i++)
		{
			final FunctionAttribute source = sources[i];
			if(!source.isInitialized())
				initialize(source, name+(i+1/*TODO: make this '1' customizable*/));
		}
	}
	
	public List<FunctionAttribute> getSources()
	{
		return Collections.unmodifiableList(Arrays.asList(sources));
	}
	
	public List<Object> get(final Item item)
	{
		final ArrayList<Object> result = new ArrayList<Object>(sources.length);

		for(int i = 0; i<sources.length; i++)
		{
			final Object value = sources[i].get(item);
			if(value!=null)
				result.add(value);
		}
		return result;
	}
	
	public void set(final Item item, final Collection value)
		throws
			UniqueViolationException,
			MandatoryViolationException,
			LengthViolationException,
			FinalViolationException,
			ClassCastException
	{
		int i = 0;
		final SetValue[] setValues = new SetValue[sources.length];

		for(Iterator it = value.iterator(); it.hasNext(); i++)
			setValues[i] = new SetValue(sources[i], it.next());

		for(; i<sources.length; i++)
			setValues[i] = new SetValue(sources[i], null);
		
		try
		{
			item.set(setValues);
		}
		catch(CustomAttributeException e)
		{
			// cannot happen, since FunctionAttribute only are allowed for source
			throw new RuntimeException(e);
		}
	}
	
	public SetValue map(final Collection value)
	{
		return new SetValue(this, value);
	}
	
	public Map<? extends FunctionAttribute, ? extends Object> execute(final Collection value, final Item exceptionItem)
	{
		int i = 0;
		final HashMap<FunctionAttribute, Object> result = new HashMap<FunctionAttribute, Object>();

		for(final Object v : value)
			result.put(sources[i++], v);

		for(; i<sources.length; i++)
			result.put(sources[i], null);
		
		return result;
	}
	
	public AndCondition equal(final Collection value)
	{
		int i = 0;
		final EqualCondition[] conditions = new EqualCondition[sources.length];
		
		for(Iterator it = value.iterator(); it.hasNext(); i++)
			conditions[i] = new EqualCondition(sources[i], it.next());

		for(; i<sources.length; i++)
			conditions[i] = new EqualCondition(sources[i], null);

		return new AndCondition(conditions);
	}
	
	public OrCondition notEqual(final Collection value)
	{
		int i = 0;
		final NotEqualCondition[] conditions = new NotEqualCondition[sources.length];
		
		for(Iterator it = value.iterator(); it.hasNext(); i++)
			conditions[i] = new NotEqualCondition(sources[i], it.next());

		for(; i<sources.length; i++)
			conditions[i] = new NotEqualCondition(sources[i], null);

		return new OrCondition(conditions);
	}

	public OrCondition contains(final Object value)
	{
		final EqualCondition[] conditions = new EqualCondition[sources.length];
		
		for(int i = 0; i<sources.length; i++)
			conditions[i] = new EqualCondition(sources[i], value);

		return new OrCondition(conditions);
	}
	
}
