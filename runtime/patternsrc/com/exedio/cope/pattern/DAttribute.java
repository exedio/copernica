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

import com.exedio.cope.EnumField;
import com.exedio.cope.IntegerField;
import com.exedio.cope.Item;
import com.exedio.cope.ItemField;
import com.exedio.cope.SetValue;
import com.exedio.cope.StringField;
import com.exedio.cope.Type;
import com.exedio.cope.UniqueConstraint;
import com.exedio.cope.util.ReactivationConstructorDummy;

public final class DAttribute extends Item
{
	public static final ItemField<DType> parent = newItemAttribute(FINAL, DType.class, CASCADE);
	public static final IntegerField position = new IntegerField(FINAL);
	public static final UniqueConstraint uniqueConstraint = new UniqueConstraint(parent, position);

	public static enum ValueType
	{
		STRING (String.class,     "String"),
		BOOLEAN(Boolean.class,    "Bool"),
		INTEGER(Integer.class,    "Int"),
		DOUBLE (Double.class,     "Double"),
		ENUM   (DEnumValue.class, "Enum");
		
		final Class valueClass;
		final String postfix;
		
		ValueType(final Class valueClass, final String postfix)
		{
			this.valueClass = valueClass;
			this.postfix = postfix;
		}
		
		public final Class getValueClass()
		{
			return valueClass;
		}
	}
	public static final EnumField<ValueType> valueType = newEnumAttribute(FINAL, ValueType.class);
	public static final IntegerField positionPerValueType = new IntegerField(FINAL);
	public static final UniqueConstraint uniqueConstraintPerValueType = new UniqueConstraint(parent, valueType, positionPerValueType);
	
	public static final StringField code = new StringField(FINAL);
	public static final UniqueConstraint uniqueConstraintCode = new UniqueConstraint(parent, code);

	public Object get(final Item item)
	{
		return getParent().getDtypeSystem().get(this, item);
	}
	
	public void set(final Item item, final Object value)
	{
		getParent().getDtypeSystem().set(this, item, value);
	}
	
	private void assertEnum()
	{
		final ValueType vt = getValueType();
		if(vt!=ValueType.ENUM)
			throw new RuntimeException("operation allowed for getValueType()==ENUM attributes only, but was " + vt);
	}
	
	public List<DEnumValue> getEnumValues()
	{
		assertEnum();
		return DEnumValue.TYPE.search(DEnumValue.parent.equal(this), DEnumValue.position, true);
	}
	
	public DEnumValue getEnumValue(final String code)
	{
		assertEnum();
		return DEnumValue.TYPE.searchSingleton(DEnumValue.parent.equal(this).and(DEnumValue.code.equal(code)));
	}
	
	public DEnumValue addEnumValue(final String code)
	{
		assertEnum();
		final List<DEnumValue> values = getEnumValues(); // TODO make more efficient
		final int position = values.isEmpty() ? 0 : (values.get(values.size()-1).getPosition()+1);
		return new DEnumValue(this, position, code);
	}
	
	

	
	DAttribute(final DType parent, final int position, final String code, final ValueType valueType, final int positionPerValueType)
	{
		super(new SetValue[]{
				DAttribute.parent.map(parent),
				DAttribute.position.map(position),
				DAttribute.code.map(code),
				DAttribute.valueType.map(valueType),
				DAttribute.positionPerValueType.map(positionPerValueType),
		});
	}
	
	private DAttribute(final SetValue[] initialAttributes)
	{
		super(initialAttributes);
	}
	
	private DAttribute(final ReactivationConstructorDummy d, final int pk)
	{
		super(d, pk);
	}
	
	public DType getParent()
	{
		return parent.get(this);
	}
	
	public int getPosition()
	{
		return position.getMandatory(this);
	}
	
	public ValueType getValueType()
	{
		return valueType.get(this);
	}
	
	int getPositionPerValueType()
	{
		return positionPerValueType.getMandatory(this);
	}
	
	public String getCode()
	{
		return code.get(this);
	}
	
	public static final Type<DAttribute> TYPE = newType(DAttribute.class);
}
