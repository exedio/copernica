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

package com.exedio.cope;


public final class ItemAttribute<E extends Item> extends FunctionAttribute<E>
{

	private final DeletePolicy policy;

	private ItemAttribute(final boolean isfinal, final boolean optional, final boolean unique, final DeletePolicy policy)
	{
		super(isfinal, optional, unique, null/* defaultValue makes no sense for ItemAttribute */);
		this.policy = policy;
		if(policy==null)
			throw new RuntimeException("delete policy for attribute "+this+" must not be null");
		if(policy==DeletePolicy.NULLIFY)
		{
			if(!optional)
				throw new RuntimeException("mandatory attribute "+this+" cannot have delete policy nullify");
			if(isfinal)
				throw new RuntimeException("final attribute "+this+" cannot have delete policy nullify");
		}
		
		checkDefaultValue();
	}
	
	public ItemAttribute(final Option option)
	{
		this(option, Item.FORBID);
	}
	
	public ItemAttribute(final Option option, final DeletePolicy policy)
	{
		this(option.isFinal, option.optional, option.unique, policy);
	}

	public FunctionAttribute<E> copyFunctionAttribute()
	{
		return new ItemAttribute<E>(isfinal, optional, implicitUniqueConstraint!=null, policy);
	}
	
	private Class<E> targetTypeClass = null;
	
	@Override
	Class initialize(final java.lang.reflect.Type genericType)
	{
		targetTypeClass = getClass(genericType, Item.class); // TODO get targetTypeClass in constructor
		return targetTypeClass;
	}
	
	private Type<? extends E> targetType = null;
	private Type<? extends E> onlyPossibleTargetType = null;
	private StringColumn typeColumn = null;
	
	/**
	 * Returns the type of items, this attribute accepts instances of.
	 */
	public Type<? extends E> getTargetType()
	{
		if(targetType==null)
			throw new RuntimeException();

		return targetType;
	}
	
	/**
	 * Returns the delete policy of this attribute.
	 */
	public DeletePolicy getDeletePolicy()
	{
		return policy;
	}
	
	Column createColumn(final Table table, final String name, final boolean optional)
	{
		if(targetType!=null)
			throw new RuntimeException();
		if(onlyPossibleTargetType!=null)
			throw new RuntimeException();
		if(typeColumn!=null)
			throw new RuntimeException();
		
		targetType = Type.findByJavaClass(targetTypeClass);
		targetType.registerReference(this);
		
		final ItemColumn result = new ItemColumn(table, name, optional, targetTypeClass, this);
		
		final String[] typeColumnValues = targetType.getTypesOfInstancesColumnValues();
		if(typeColumnValues==null)
			onlyPossibleTargetType = targetType.getOnlyPossibleTypeOfInstances();
		else
			typeColumn = new StringColumn(table, result.id + "Type"/* not equal to "name"! */, optional, typeColumnValues);

		return result;
	}
	
	StringColumn getTypeColumn()
	{
		if(targetType==null)
			throw new RuntimeException();

		return typeColumn;
	}
	
	E get(final Row row)
	{
		final Object cell = row.get(getColumn());

		if(cell==null)
		{
			if(typeColumn!=null && row.get(typeColumn)!=null)
				throw new RuntimeException("inconsistent type column: "+row.get(typeColumn));
			
			return null;
		}
		else
		{
			final Type<? extends E> cellType;
			if(typeColumn!=null)
			{
				final String cellTypeID = (String)row.get(typeColumn);
				
				if(cellTypeID==null)
					throw new RuntimeException("inconsistent type column");
				
				cellType = cast(getTargetType().getModel().findTypeByID(cellTypeID));
				
				if(cellType==null)
					throw new RuntimeException(cellTypeID);
			}
			else
			{
				cellType = onlyPossibleTargetType;
				
				if(cellType==null)
					throw new RuntimeException();
			}
			
			return cellType.getItemObject(((Integer)cell).intValue());
		}
	}
	
	@SuppressWarnings("unchecked") // OK: Model#findTypeByID cannot return checked types
	private Type<? extends E> cast(Type t)
	{
		return t;
	}
		
	void set(final Row row, final E surface)
	{
		if(surface==null)
		{
			row.put(getColumn(), null);
			if(typeColumn!=null)
				row.put(typeColumn, null);
		}
		else
		{
			final Item valueItem = surface;
			row.put(getColumn(), Integer.valueOf(valueItem.pk));
			if(typeColumn!=null)
				row.put(typeColumn, valueItem.type.id);
		}
	}
	
	public final EqualCondition equal(final E value, final Join join)
	{
		return new EqualCondition<E>(new JoinedFunction<E>(this, join), value);
	}
	
	public final EqualTargetCondition equalTarget()
	{
		return new EqualTargetCondition(this, null);
	}
	
	public final EqualTargetCondition equalTarget(final Join targetJoin)
	{
		return new EqualTargetCondition(this, targetJoin);
	}
	
	public static enum DeletePolicy
	{
		FORBID(),
		NULLIFY(),
		CASCADE();
	}
	
}
