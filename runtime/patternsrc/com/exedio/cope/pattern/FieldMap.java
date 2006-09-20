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

import java.util.LinkedHashMap;

import com.exedio.cope.Field;
import com.exedio.cope.Cope;
import com.exedio.cope.Feature;
import com.exedio.cope.FunctionField;
import com.exedio.cope.Item;
import com.exedio.cope.ItemField;
import com.exedio.cope.Pattern;
import com.exedio.cope.SetValue;
import com.exedio.cope.Type;
import com.exedio.cope.UniqueConstraint;

public final class FieldMap<K,V> extends Pattern
{
	private ItemField<? extends Item> parent = null;
	private final FunctionField<K> key;
	private UniqueConstraint uniqueConstraint = null;
	private final FunctionField<V> value;
	private Type<?> relationType = null;

	private FieldMap(final FunctionField<K> key, final FunctionField<V> value)
	{
		this.key = key;
		this.value = value;
		if(key==null)
			throw new NullPointerException("key must not be null");
		if(key.getImplicitUniqueConstraint()!=null)
			throw new NullPointerException("key must not be unique");
		if(value==null)
			throw new NullPointerException("value must not be null");
		if(value.getImplicitUniqueConstraint()!=null)
			throw new NullPointerException("value must not be unique");
	}
	
	public static final <K,V> FieldMap<K,V> newMap(final FunctionField<K> key, final FunctionField<V> value)
	{
		return new FieldMap<K,V>(key, value);
	}
	
	@Override
	public void initialize()
	{
		final Type<?> type = getType();
		
		parent = Item.newItemField(Field.Option.FINAL, type.getJavaClass(), ItemField.DeletePolicy.CASCADE);
		uniqueConstraint = new UniqueConstraint(parent, key);
		final LinkedHashMap<String, Feature> relationTypeFeatures = new LinkedHashMap<String, Feature>();
		relationTypeFeatures.put("parent", parent);
		relationTypeFeatures.put("key", key);
		relationTypeFeatures.put("uniqueConstraint", uniqueConstraint);
		relationTypeFeatures.put("value", value);
		this.relationType = newType(relationTypeFeatures);
	}
	
	public ItemField<?> getParent()
	{
		assert parent!=null;
		return parent;
	}
	
	public FunctionField<K> getKey()
	{
		return key;
	}

	public UniqueConstraint getUniqueConstraint()
	{
		assert uniqueConstraint!=null;
		return uniqueConstraint;
	}
	
	public FunctionField<V> getValue()
	{
		return value;
	}

	public Type<?> getRelationType()
	{
		assert relationType!=null;
		return relationType;
	}
	
	public V get(final Item item, final K key)
	{
		final Item relationItem = uniqueConstraint.searchUnique(new Object[]{item, key});
		if(relationItem!=null)
			return value.get(relationItem);
		else
			return null;
	}
	
	public void set(final Item item, final K key, final V value)
	{
		final Item relationItem = uniqueConstraint.searchUnique(new Object[]{item, key});
		if(relationItem==null)
		{
			if(value!=null)
				uniqueConstraint.getType().newItem(new SetValue[]{
						Cope.mapAndCast(this.parent, item),
						this.key.map(key),
						this.value.map(value),
				});
		}
		else
		{
			if(value!=null)
				this.value.set(relationItem, value);
			else
				relationItem.deleteCopeItem();
		}
	}

	public V getAndCast(final Item item, final Object key)
	{
		return get(item, Cope.verboseCast(this.key.getValueClass(), key));
	}

	public void setAndCast(final Item item, final Object key, final Object value)
	{
		set(item, Cope.verboseCast(this.key.getValueClass(), key), Cope.verboseCast(this.value.getValueClass(), value));
	}
}