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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import com.exedio.cope.search.ExtremumAggregate;

public abstract class FunctionAttribute<E extends Object>
	extends Attribute<E>
	implements Function<E>
{
	final UniqueConstraint implicitUniqueConstraint;
	final E defaultConstant;
	private ArrayList<UniqueConstraint> uniqueConstraints;
	
	FunctionAttribute(final boolean isfinal, final boolean optional, final boolean unique, final Class<E> valueClass, final E defaultConstant)
	{
		super(isfinal, optional, valueClass);
		this.defaultConstant = defaultConstant;
		this.implicitUniqueConstraint =
			unique ?
				new UniqueConstraint(this) :
				null;
	}
	
	final void checkDefaultValue()
	{
		if(defaultConstant!=null)
		{
			try
			{
				checkValue(defaultConstant, null);
			}
			catch(ConstraintViolationException e)
			{
				// BEWARE
				// Must not make exception e available to public,
				// since it contains a reference to this function attribute,
				// which has not been constructed successfully.
				throw new RuntimeException(
						"The default constant of the attribute " +
						"does not comply to one of it's own constraints, " +
						"caused a " + e.getClass().getSimpleName() +
						": " + e.getMessageWithoutFeature() +
						" Default constant was '" + defaultConstant + "'.");
			}
		}
	}
	
	public final E getDefaultConstant()
	{
		return defaultConstant;
	}
	
	/**
	 * Returns true, if a value for the attribute should be specified
	 * on the creation of an item.
	 * This implementation returns
	 * <tt>{@link #isFinal() isFinal()} || ({@link #isMandatory() isMandatory()} && {@link #getDefaultConstant()}==null)</tt>.
	 */
	@Override
	public boolean isInitial()
	{
		return isfinal || (!optional && defaultConstant==null);
	}
	
	@Override
	final void initialize(final Type<? extends Item> type, final String name)
	{
		super.initialize(type, name);
		
		if(implicitUniqueConstraint!=null)
			implicitUniqueConstraint.initialize(type, name + UniqueConstraint.IMPLICIT_UNIQUE_SUFFIX);
	}
	
	final void checkValueClass(final Class<? extends Object> superClass)
	{
		if(!superClass.isAssignableFrom(valueClass))
			throw new RuntimeException("is not a subclass of " + superClass.getName() + ": "+valueClass.getName());
	}
	
	public abstract FunctionAttribute<E> copyFunctionAttribute();

	abstract E get(final Row row);
	abstract void set(final Row row, final E surface);
	
	/**
	 * Checks attribute values set by
	 * {@link Item#set(FunctionAttribute,Object)} (for <tt>initial==false</tt>)
	 * and {@link Item#Item(SetValue[])} (for <tt>initial==true</tt>)
	 * and throws the exception specified there.
	 */
	@Override
	final void checkValue(final Object value, final Item exceptionItem)
		throws
			MandatoryViolationException,
			LengthViolationException
	{
		if(value == null)
		{
			if(!optional)
				throw new MandatoryViolationException(this, exceptionItem);
		}
		else
		{
			checkNotNullValue(cast(value), exceptionItem);
		}
	}

	/**
	 * Further checks non-null attribute values already checked by
	 * {@link #checkValue(Object, Item)}.
	 * To be overidden by subclasses,
	 * the default implementation does nothing.
	 */
	void checkNotNullValue(final E value, final Item exceptionItem)
		throws
			LengthViolationException
	{
		// empty default implementation
	}
	
	private static final Entity getEntity(final Item item)
	{
		return getEntity(item, true);
	}

	private static final Entity getEntity(final Item item, final boolean present)
	{
		return item.type.getModel().getCurrentTransaction().getEntity(item, present);
	}

	@Override
	public final E get(final Item item)
	{
		if(!getType().isAssignableFrom(item.type))
			throw new RuntimeException("attribute "+toString()+" does not belong to type "+item.type.toString());
		
		return cast(getEntity(item).get(this));
	}
	
	@Override
	public final void set(final Item item, final E value)
	{
		item.set(this, value);
	}

	public final void append(final Statement bf, final Join join)
	{
		bf.append(getColumn(), join);
	}
	
	public final int getTypeForDefiningColumn()
	{
		return getColumn().typeForDefiningColumn;
	}
	
	public final void appendParameter(final Statement bf, final E value)
	{
		final Row dummyRow = new Row();
		set(dummyRow, value);
		final Column column = getColumn();
		bf.appendParameter(column, dummyRow.get(column));
	}
	
	/**
	 * Returns the unique constraint of this attribute,
	 * that has been created implicitly when creating this attribute.
	 * Does return null, if there is no such unique constraint.
	 * @see #getUniqueConstraints()
	 */
	public UniqueConstraint getImplicitUniqueConstraint()
	{
		return implicitUniqueConstraint;
	}

	/**
	 * Returns a list of unique constraints this attribute is part of.
	 * This includes an
	 * {@link #getImplicitUniqueConstraint() implicit unique constraint},
	 * if there is one for this attribute.
	 */
	public List<UniqueConstraint> getUniqueConstraints()
	{
		return uniqueConstraints!=null ? Collections.unmodifiableList(uniqueConstraints) : Collections.<UniqueConstraint>emptyList();
	}
	
	@Override
	public SortedSet<Class> getSetterExceptions()
	{
		final SortedSet<Class> result = super.getSetterExceptions();
		if(uniqueConstraints!=null)
			result.add(UniqueViolationException.class);
		return result;
	}
	
	final void registerUniqueConstraint(final UniqueConstraint constraint)
	{
		if(constraint==null)
			throw new NullPointerException();
		
		if(uniqueConstraints==null)
		{
			uniqueConstraints = new ArrayList<UniqueConstraint>();
		}
		else
		{
			if(uniqueConstraints.contains(constraint))
				throw new RuntimeException(constraint.toString());
		}
		
		uniqueConstraints.add(constraint);
	}

	/**
	 * Finds an item by it's unique attributes.
	 * @return null if there is no matching item.
	 */
	public final Item searchUnique(final E value)
	{
		// TODO: search nativly for unique constraints
		return getType().searchSingleton(equal(value));
	}

	public final SetValue<E> map(final E value) // TODO SOON move up to Attribute
	{
		return new SetValue<E>(this, value);
	}
	
	public final Map<? extends Attribute, ? extends Object> execute(final E value, final Item exceptionItem) // TODO SOON move up to Attribute
	{
		return Collections.singletonMap(this, value);
	}
	
	// convenience methods for conditions and views ---------------------------------
	
	public final EqualCondition<E> isNull()
	{
		return equal((E)null);
	}
	
	public final NotEqualCondition<E> isNotNull()
	{
		return notEqual(null);
	}
	
	public final EqualCondition<E> equal(final E value)
	{
		return new EqualCondition<E>(this, value);
	}
	
	public final EqualCondition<E> equalAndCast(final Object value)
	{
		return equal(cast(value));
	}
	
	public final EqualCondition<E> equal(final Join join, final E value)
	{
		return this.bind(join).equal(value);
	}
	
	public final CompositeCondition in(final Collection<E> values)
	{
		return CompositeCondition.in(this, values);
	}
	
	public final NotEqualCondition<E> notEqual(final E value)
	{
		return new NotEqualCondition<E>(this, value);
	}
	
	public final NotEqualCondition<E> notEqualAndCast(final Object value)
	{
		return notEqual(cast(value));
	}
	
	public final EqualFunctionCondition<E> equal(final Function<E> right)
	{
		return new EqualFunctionCondition<E>(this, right);
	}
	
	public final CompareCondition<E> less(final E value)
	{
		return new CompareCondition<E>(CompareCondition.Operator.Less, this, value);
	}
	
	public final CompareCondition<E> lessAndCast(final Object value)
	{
		return less(cast(value));
	}
	
	public final CompareCondition<E> lessOrEqual(final E value)
	{
		return new CompareCondition<E>(CompareCondition.Operator.LessEqual, this, value);
	}
	
	public final CompareCondition<E> lessOrEqualAndCast(final Object value)
	{
		return lessOrEqual(cast(value));
	}
	
	public final CompareCondition<E> greater(final E value)
	{
		return new CompareCondition<E>(CompareCondition.Operator.Greater, this, value);
	}
	
	public final CompareCondition<E> greaterAndCast(final Object value)
	{
		return greater(cast(value));
	}
	
	public final CompareCondition<E> greaterOrEqual(final E value)
	{
		return new CompareCondition<E>(CompareCondition.Operator.GreaterEqual, this, value);
	}
	
	public final CompareCondition<E> greaterOrEqualAndCast(final Object value)
	{
		return greaterOrEqual(cast(value));
	}

	public final ExtremumAggregate<E> min()
	{
		return new ExtremumAggregate<E>(this, true);
	}
	
	public final ExtremumAggregate<E> max()
	{
		return new ExtremumAggregate<E>(this, false);
	}

	public final JoinedFunction<E> bind(final Join join)
	{
		return new JoinedFunction<E>(this, join);
	}
}
