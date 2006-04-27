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

import java.util.List;

import com.exedio.cope.Item;
import com.exedio.cope.ItemAttribute;
import com.exedio.cope.Pattern;
import com.exedio.cope.Query;
import com.exedio.cope.SetValue;
import com.exedio.cope.UniqueConstraint;
import com.exedio.cope.UniqueViolationException;

public final class Relation<S extends Item, T extends Item> extends Pattern
{
	final ItemAttribute<S> source;
	final ItemAttribute<T> target;
	final UniqueConstraint uniqueConstraint;
	
	public Relation(final ItemAttribute<S> source, final ItemAttribute<T> target)
	{
		this.source = source;
		this.target = target;
		this.uniqueConstraint = new UniqueConstraint(source, target);
		
		registerSource(source);
		registerSource(target);
	}
	
	public ItemAttribute<S> getSource()
	{
		return source;
	}
	
	public ItemAttribute<T> getTarget()
	{
		return target;
	}
	
	public UniqueConstraint getUniqueConstraint()
	{
		return uniqueConstraint;
	}
	
	// second initialization phase ---------------------------------------------------

	public void initialize()
	{
		final String name = getName();
		
		if(!source.isInitialized())
			initialize(source, name + "Source");
		if(!target.isInitialized())
			initialize(target, name + "Target");
		
		initialize(uniqueConstraint, name + "UniqueConstraint");
	}
	
	public List<? extends T> getTargets(final S source)
	{
		final Query<T> q = new Query<T>(target, this.source.equal(source));
		return q.search();
	}

	public List<? extends S> getSources(final T target)
	{
		final Query<S> q = new Query<S>(source, this.target.equal(target));
		return q.search();
	}

	/**
	 * @return <tt>true</tt> if the result of {@link #getTargets} changed as a result of the call.
	 */
	public boolean addToTargets(final S source, final T target)
	{
		try
		{
			getType().newItem(new SetValue[]{
					this.source.map(source),
					this.target.map(target),
			});
			return true;
		}
		catch(UniqueViolationException e)
		{
			assert uniqueConstraint==e.getConstraint();
			return false;
		}
	}

	/**
	 * @return <tt>true</tt> if the result of {@link #getSources} changed as a result of the call.
	 */
	public boolean addToSources(final T target, final S source)
	{
		return addToTargets(source, target);
	}

	/**
	 * @return <tt>true</tt> if the result of {@link #getTargets} changed as a result of the call.
	 */
	public boolean removeFromTargets(final S source, final T target)
	{
		final Item item = uniqueConstraint.searchUnique(new Object[]{source, target});
		if(item==null)
			return false;
		else
		{
			item.deleteCopeItem();
			return true;
		}
	}

	/**
	 * @return <tt>true</tt> if the result of {@link #getSources} changed as a result of the call.
	 */
	public boolean removeFromSources(final T target, final S source)
	{
		return removeFromTargets(source, target);
	}

}
