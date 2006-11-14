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

import com.exedio.cope.AbstractLibTest;
import com.exedio.cope.Model;

public class DTypeTest extends AbstractLibTest
{
	public static final Model MODEL = new Model(DType.TYPE, DAttribute.TYPE, DEnumValue.TYPE, DTypeItem.TYPE);

	public DTypeTest()
	{
		super(MODEL);
	}
	
	DTypeItem item, item2;
	
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		deleteOnTearDown(item = new DTypeItem("item1"));
		deleteOnTearDown(item2 = new DTypeItem("item2"));
	}
	
	public void testIt()
	{
		// test model
		assertEquals(String.class,     DAttribute.ValueType.STRING.getValueClass());
		assertEquals(Boolean.class,    DAttribute.ValueType.BOOLEAN.getValueClass());
		assertEquals(Integer.class,    DAttribute.ValueType.INTEGER.getValueClass());
		assertEquals(Double.class,     DAttribute.ValueType.DOUBLE.getValueClass());
		assertEquals(DEnumValue.class, DAttribute.ValueType.ENUM.getValueClass());
		
		assertEquals(item.TYPE, item.features.getType());
		assertEquals("features", item.features.getName());
		
		assertSame(DType.TYPE, item.features.getTypeField().getValueType());
		assertEquals("featuresType", item.features.getTypeField().getName());
		
		// test persistence
		assertContains(item.features.getTypes());
		
		final DType cellPhone = item.features.createType("cellPhone");
		deleteOnTearDown(cellPhone);
		assertEquals(item.TYPE, cellPhone.getParentType());
		assertEquals(item.features, cellPhone.getDtypeSystem());
		assertEquals("cellPhone", cellPhone.getCode());
		assertContains(cellPhone, item.features.getTypes());
		assertEquals(cellPhone, item.features.getType("cellPhone"));
		assertEquals(null, item.features.getType("cellPhoneX"));
		assertContains(cellPhone.getAttributes());

		final DAttribute akkuTime = cellPhone.addIntegerAttribute("akkuTime");
		assertEquals(DAttribute.ValueType.INTEGER, akkuTime.getValueType());
		assertEquals(0, akkuTime.getPosition());
		assertEquals("akkuTime", akkuTime.getCode());
		assertSame(item.TYPE, akkuTime.getField().getType());
		assertEquals("featuresInt1", akkuTime.getField().getName());
		assertEquals(Integer.class, akkuTime.getField().getValueClass());
		assertEquals(list(akkuTime), cellPhone.getAttributes());
		assertEquals(akkuTime, cellPhone.getAttribute("akkuTime"));
		assertEquals(null, cellPhone.getAttribute("akkuTimeX"));

		final DAttribute memory = cellPhone.addStringAttribute("memory");
		assertEquals(DAttribute.ValueType.STRING, memory.getValueType());
		assertEquals(1, memory.getPosition());
		assertEquals("memory", memory.getCode());
		assertSame(item.TYPE, memory.getField().getType());
		assertEquals("featuresString1", memory.getField().getName());
		assertEquals(String.class, memory.getField().getValueClass());
		assertEquals(list(akkuTime, memory), cellPhone.getAttributes());
		
		assertEquals(null, item.getFeaturesType());
		
		item.setFeaturesType(cellPhone);
		assertEquals(cellPhone, item.getFeaturesType());
		assertEquals(null, item.getFeatures(akkuTime));
		assertEquals(null, item.getFeatures(memory));
		
		item.setFeatures(akkuTime, 5);
		assertEquals(5, item.getFeatures(akkuTime));
		assertEquals(5, akkuTime.get(item));
		assertEquals(null, item.getFeatures(memory));
		
		akkuTime.set(item, 10);
		assertEquals(10, item.getFeatures(akkuTime));
		assertEquals(10, akkuTime.get(item));
		assertEquals(null, item.getFeatures(memory));
		
		item.setFeatures(akkuTime, 80);
		assertEquals(80, item.getFeatures(akkuTime));
		assertEquals(null, item.getFeatures(memory));
		
		item.setFeatures(memory, "80TB");
		assertEquals(80, item.getFeatures(akkuTime));
		assertEquals(80, akkuTime.get(item));
		assertEquals("80TB", item.getFeatures(memory));
		assertEquals("80TB", memory.get(item));
		
		final DType organizer = item.features.createType("organizer");
		deleteOnTearDown(organizer);
		assertEquals(item.TYPE, organizer.getParentType());
		assertEquals(item.features, organizer.getDtypeSystem());
		assertEquals("organizer", organizer.getCode());
		assertContains(cellPhone, organizer, item.features.getTypes());

		final DAttribute weight = organizer.addIntegerAttribute("weight");
		assertEquals(DAttribute.ValueType.INTEGER, weight.getValueType());
		assertEquals(0, weight.getPosition());
		assertEquals("weight", weight.getCode());
		assertSame(akkuTime.getField(), weight.getField());
		assertEquals(list(weight), organizer.getAttributes());
		
		item2.setFeaturesType(organizer);
		assertEquals(organizer, item2.getFeaturesType());
		assertEquals(null, item2.getFeatures(weight));

		item2.setFeatures(weight, 500);
		assertEquals(500, item2.getFeatures(weight));
		
		final DAttribute bluetooth = organizer.addBooleanAttribute("bluetooth");
		assertEquals(DAttribute.ValueType.BOOLEAN, bluetooth.getValueType());
		assertEquals(1, bluetooth.getPosition());
		assertEquals("bluetooth", bluetooth.getCode());
		assertSame(item.TYPE, bluetooth.getField().getType());
		assertEquals("featuresBool1", bluetooth.getField().getName());
		assertEquals(Boolean.class, bluetooth.getField().getValueClass());
		assertEquals(list(weight, bluetooth), organizer.getAttributes());
		
		final DAttribute length = organizer.addDoubleAttribute("length");
		assertEquals(DAttribute.ValueType.DOUBLE, length.getValueType());
		assertEquals(2, length.getPosition());
		assertEquals("length", length.getCode());
		assertSame(item.TYPE, length.getField().getType());
		assertEquals("featuresDouble1", length.getField().getName());
		assertEquals(Double.class, length.getField().getValueClass());
		assertEquals(list(weight, bluetooth, length), organizer.getAttributes());
		
		assertEquals(null, item2.getFeatures(bluetooth));
		assertEquals(null, item2.getFeatures(length));
		item2.setFeatures(bluetooth, true);
		item2.setFeatures(length, 2.2);
		assertEquals(true, item2.getFeatures(bluetooth));
		assertEquals(2.2, item2.getFeatures(length));
		
		final DAttribute color = organizer.addEnumAttribute("color");
		assertEquals(DAttribute.ValueType.ENUM, color.getValueType());
		assertEquals(3, color.getPosition());
		assertEquals("color", color.getCode());
		assertSame(item.TYPE, color.getField().getType());
		assertEquals("featuresEnum1", color.getField().getName());
		assertEquals(DEnumValue.class, color.getField().getValueClass());
		assertEquals(list(weight, bluetooth, length, color), organizer.getAttributes());
		assertContains(color.getEnumValues());
		assertEquals(null, item2.getFeatures(color));
		
		final DEnumValue colorRed = color.addEnumValue("red");
		assertEquals(color, colorRed.getParent());
		assertEquals(0, colorRed.getPosition());
		assertEquals("red", colorRed.getCode());
		assertContains(colorRed, color.getEnumValues());
		assertEquals(colorRed, color.getEnumValue("red"));
		assertEquals(null, color.getEnumValue("redX"));
		
		final DEnumValue colorBlue = color.addEnumValue("blue");
		assertEquals(color, colorBlue.getParent());
		assertEquals(1, colorBlue.getPosition());
		assertEquals("blue", colorBlue.getCode());
		assertContains(colorRed, colorBlue, color.getEnumValues());

		item2.setFeatures(color, colorBlue);
		assertEquals(colorBlue, item2.getFeatures(color));
		
		final DAttribute manufacturer = organizer.addEnumAttribute("manufacturer");
		assertEquals(DAttribute.ValueType.ENUM, manufacturer.getValueType());
		assertEquals(4, manufacturer.getPosition());
		assertEquals("manufacturer", manufacturer.getCode());
		assertSame(item.TYPE, manufacturer.getField().getType());
		assertEquals("featuresEnum2", manufacturer.getField().getName());
		assertEquals(DEnumValue.class, manufacturer.getField().getValueClass());
		assertEquals(list(weight, bluetooth, length, color, manufacturer), organizer.getAttributes());
		assertContains(manufacturer.getEnumValues());
		assertEquals(null, item2.getFeatures(manufacturer));
		
		final DEnumValue manufacturer1 = manufacturer.addEnumValue("manufacturer1");
		assertEquals(manufacturer, manufacturer1.getParent());
		assertEquals(0, manufacturer1.getPosition());
		assertEquals("manufacturer1", manufacturer1.getCode());
		assertContains(manufacturer1, manufacturer.getEnumValues());

		item2.setFeatures(manufacturer, manufacturer1);
		assertEquals(manufacturer1, item2.getFeatures(manufacturer));
		try
		{
			item2.setFeatures(manufacturer, colorBlue);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals("dynamic type system mismatch: enum value " + colorBlue + " has type " + color + ", but must be " + manufacturer, e.getMessage());
		}
		assertEquals(manufacturer1, item2.getFeatures(manufacturer));

		
		// wrong value type
		try
		{
			item2.setFeatures(weight, "510");
			fail();
		}
		catch(ClassCastException e)
		{
			assertEquals("expected a " + Integer.class.getName() + ", but was a " + String.class.getName(), e.getMessage());
		}
		assertEquals(500, item2.getFeatures(weight));

		// wrong dtype
		try
		{
			item.getFeatures(weight);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals("dynamic type mismatch: attribute has type organizer, but item has cellPhone", e.getMessage());
		}
		try
		{
			item.setFeatures(weight, 510);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals("dynamic type mismatch: attribute has type organizer, but item has cellPhone", e.getMessage());
		}
		assertEquals(500, item2.getFeatures(weight));
		
		assertContains(akkuTime, memory, cellPhone.getAttributes());
		try
		{
			cellPhone.addStringAttribute("tooMuch");
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals("capacity for STRING exceeded, 1 available, but tried to allocate 2", e.getMessage());
		}
		assertContains(akkuTime, memory, cellPhone.getAttributes());
		
		/* TODO assertContains(akkuTime, memory, cellPhone.getAttributes());
		try
		{
			cellPhone.addDoubleAttribute("akkuTime");
			fail();
		}
		catch(UniqueViolationException e)
		{
			assertEquals("unique violation for DAttribute#uniqueConstraintName", e.getMessage());
		}
		assertContains(akkuTime, memory, cellPhone.getAttributes());*/
		
		try
		{
			akkuTime.getEnumValues();
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals("operation allowed for getValueType()==ENUM attributes only, but was " + DAttribute.ValueType.INTEGER, e.getMessage());
		}
		try
		{
			akkuTime.getEnumValue(null);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals("operation allowed for getValueType()==ENUM attributes only, but was " + DAttribute.ValueType.INTEGER, e.getMessage());
		}
		try
		{
			akkuTime.addEnumValue(null);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals("operation allowed for getValueType()==ENUM attributes only, but was " + DAttribute.ValueType.INTEGER, e.getMessage());
		}
			
		
		// test cleaning of attributes on setting type
		item2.setFeaturesType(cellPhone);
		assertEquals(null, item2.getFeatures(akkuTime)); // must not be 500 left from weight
		assertEquals(null, item2.getFeatures(memory));

		item.setFeaturesType(null);
		assertEquals(null, item.getFeaturesType());
		item2.setFeaturesType(null);
		assertEquals(null, item2.getFeaturesType());
	}
	
}
