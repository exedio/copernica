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

import java.util.Collection;
import java.util.List;

import com.exedio.cope.Attribute;
import com.exedio.cope.IntegerAttribute;
import com.exedio.cope.Item;
import com.exedio.cope.ItemAttribute;
import com.exedio.cope.Pattern;
import com.exedio.cope.Query;
import com.exedio.cope.SetValue;
import com.exedio.cope.Type;
import com.exedio.cope.UniqueConstraint;

/**
 * Makes a list of instances of type <tt>T</tt> available
 * on any instance of type <tt>S</tt>.
 * This list can contain duplicates,
 * and the user has full control of the order of it's elements.
 * 
 * @see Relation
 * @author Ralf Wiebicke
 */
public final class VectorRelation<S extends Item, T extends Item> extends Pattern
{
	final ItemAttribute<S> source;
	final IntegerAttribute order;
	final UniqueConstraint uniqueConstraint;
	final ItemAttribute<T> target;
	
	public VectorRelation(final ItemAttribute<S> source, final ItemAttribute<T> target)
	{
		this.source = source;
		this.order = new IntegerAttribute(Attribute.Option.FINAL);
		this.uniqueConstraint = new UniqueConstraint(source, order);
		this.target = target;
		
		registerSource(source);
		registerSource(order);
		registerSource(target);
	}
	
	public static final <S extends Item, T extends Item> VectorRelation<S,T> newRelation(final ItemAttribute<S> source, final ItemAttribute<T> target)
	{
		return new VectorRelation<S, T>(source, target);
	}
	
	public ItemAttribute<S> getSource()
	{
		return source;
	}
	
	public IntegerAttribute getOrder()
	{
		return order;
	}
	
	public UniqueConstraint getUniqueConstraint()
	{
		return uniqueConstraint;
	}
	
	public ItemAttribute<T> getTarget()
	{
		return target;
	}
	
	// second initialization phase ---------------------------------------------------

	public void initialize()
	{
		final String name = getName();
		
		if(!source.isInitialized())
			initialize(source, name + "Source");
		if(!target.isInitialized())
			initialize(target, name + "Target");
		
		initialize(order, name + "Order");
		initialize(uniqueConstraint, name + "UniqueConstraint");
	}
	
	public List<T> getTargets(final S source)
	{
		final Query<T> q = new Query<T>(target, this.source.equal(source));
		q.setOrderBy(order, true);
		return q.search();
	}

	public List<S> getSources(final T target)
	{
		final Query<S> q = new Query<S>(source, this.target.equal(target));
		q.setOrderBy(order, true);
		return q.search();
	}

	public void setTargets(final S source, final Collection<? extends T> targets)
	{
		final Type<? extends Item> type = getType();
		final Query<? extends Item> q = type.newQuery(this.source.equal(source));
		q.setOrderBy(order, true);
		final Collection<? extends Item> oldTupels = q.search();

		// TODO: this implementation wastes resources !!
		for(final Item tupel : oldTupels)
			tupel.deleteCopeItem();

		int order = 0;
		for(final T target : targets)
		{
			type.newItem(new SetValue[]{
					this.source.map(source),
					this.target.map(target),
					this.order.map(order++),
			});
		}
	}
	
}
