/*
 * Copyright (C) 2004-2005  exedio GmbH (www.exedio.com)
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

package com.exedio.cope.util;

import java.util.Arrays;

import com.exedio.cope.junit.CopeAssert;

public class PropertiesTest extends CopeAssert
{
	static class TestProperties extends Properties
	{
		final BooleanField boolFalse = new BooleanField("boolFalse", false);
		final BooleanField boolTrue = new BooleanField("boolTrue", true);
		final IntField int10 = new IntField("int10", 10, 5);
		final StringField stringMandatory = new StringField("stringMandatory");
		final StringField stringOptional = new StringField("stringOptional", "stringOptional.defaultValue");
		final StringField stringHidden = new StringField("stringHidden", true);
		
		public TestProperties(final java.util.Properties properties, final String source)
		{
			super(properties, source);
		}
		
		void assertIt()
		{
			assertEqualsUnmodifiable(Arrays.asList(new Properties.Field[]{
					boolFalse,
					boolTrue,
					int10,
					stringMandatory,
					stringOptional,
					stringHidden,
			}), getFields());
			
			assertEquals("boolFalse", boolFalse.key);
			assertEquals("boolTrue", boolTrue.key);
			assertEquals("int10", int10.key);
			assertEquals("stringMandatory", stringMandatory.key);
			assertEquals("stringOptional", stringOptional.key);
			assertEquals("stringHidden", stringHidden.key);
			
			assertEquals(false, boolFalse.defaultValue);
			assertEquals(true, boolTrue.defaultValue);
			assertEquals(10, int10.defaultValue);
			assertEquals(null, stringMandatory.defaultValue);
			assertEquals("stringOptional.defaultValue", stringOptional.defaultValue);
			assertEquals(null, stringHidden.defaultValue);

			assertEquals(false, boolFalse.hasHiddenValue());
			assertEquals(false, boolTrue.hasHiddenValue());
			assertEquals(false, int10.hasHiddenValue());
			assertEquals(false, stringMandatory.hasHiddenValue());
			assertEquals(false, stringOptional.hasHiddenValue());
			assertEquals(true, stringHidden.hasHiddenValue());
		}
	}
	
	public void testIt()
	{
		final java.util.Properties pminimal = new java.util.Properties();
		pminimal.setProperty("stringMandatory", "stringMandatory.minimalValue");
		pminimal.setProperty("stringHidden", "stringHidden.minimalValue");
		
		final TestProperties minimal = new TestProperties(pminimal, "minimal");
		minimal.assertIt();
		assertEquals("minimal", minimal.getSource());
		assertEquals(false, minimal.boolFalse.value);
		assertEquals(true, minimal.boolTrue.value);
		assertEquals(10, minimal.int10.value);
		assertEquals("stringMandatory.minimalValue", minimal.stringMandatory.value);
		assertEquals("stringOptional.defaultValue", minimal.stringOptional.value);
		minimal.ensureEquality(minimal);
		
		{
			final TestProperties minimal1 = new TestProperties(pminimal, "minimal2");
			assertEquals("minimal2", minimal1.getSource());
			minimal.ensureEquality(minimal1);
			minimal1.ensureEquality(minimal);
		}
		{
			final java.util.Properties p = copy(pminimal);
			p.setProperty("boolFalse", "true");
			p.setProperty("boolTrue", "false");
			p.setProperty("int10", "20");
			p.setProperty("stringMandatory", "stringMandatory.explicitValue");
			p.setProperty("stringOptional", "stringOptional.explicitValue");
			final TestProperties tp = new TestProperties(p, "maximal");
			assertEquals(true, tp.boolFalse.value);
			assertEquals(false, tp.boolTrue.value);
			assertEquals(20, tp.int10.value);
			assertEquals("stringMandatory.explicitValue", tp.stringMandatory.value);
			assertEquals("stringOptional.explicitValue", tp.stringOptional.value);
		}
		
		// boolean
		assertWrong(pminimal,
				"wrong.bool.true",
				"boolFalse", "True",
				"property boolFalse in wrong.bool.true has invalid value," +
					" expected >true< or >false<, but got >True<.");
		assertWrong(pminimal,
				"wrong.bool.false",
				"boolFalse", "falsE",
				"property boolFalse in wrong.bool.false has invalid value," +
					" expected >true< or >false<, but got >falsE<.");
		assertInconsistent(pminimal,
				"inconsistent.bool",
				"boolFalse", "true",
				"inconsistent initialization for boolFalse between minimal and inconsistent.bool," +
					" expected false but got true.",
				"inconsistent initialization for boolFalse between inconsistent.bool and minimal," +
					" expected true but got false.");
		
		// int
		{
			// test lowest value
			final java.util.Properties p = copy(pminimal);
			p.setProperty("int10", "5");
			final TestProperties tp = new TestProperties(p, "int.border");
			assertEquals(5, tp.int10.value);
		}
		assertWrong(pminimal,
				"wrong.int.tooSmall",
				"int10", "4",
				"property int10 in wrong.int.tooSmall has invalid value," +
				" expected an integer greater 5, but got 4.");
		assertWrong(pminimal,
				"wrong.int.noNumber",
				"int10", "10x",
				"property int10 in wrong.int.noNumber has invalid value," +
				" expected an integer greater 5, but got >10x<.");
		assertInconsistent(pminimal,
				"inconsistent.int",
				"int10", "88",
				"inconsistent initialization for int10 between minimal and inconsistent.int," +
					" expected 10 but got 88.",
				"inconsistent initialization for int10 between inconsistent.int and minimal," +
					" expected 88 but got 10."	);

		// String
		assertWrong(pminimal,
				"wrong.stringMandatory.missing",
				"stringMandatory", null,
				"property stringMandatory in wrong.stringMandatory.missing not set.");
		assertWrong(pminimal,
				"wrong.stringHidden.missing",
				"stringHidden", null,
				"property stringHidden in wrong.stringHidden.missing not set.");
		assertInconsistent(pminimal,
				"inconsistent.stringMandatory",
				"stringMandatory", "stringMandatory.inconsistentValue",
				"inconsistent initialization for stringMandatory between minimal and inconsistent.stringMandatory," +
					" expected stringMandatory.minimalValue but got stringMandatory.inconsistentValue.",
				"inconsistent initialization for stringMandatory between inconsistent.stringMandatory and minimal," +
					" expected stringMandatory.inconsistentValue but got stringMandatory.minimalValue."	);
		assertInconsistent(pminimal,
				"inconsistent.stringOptional",
				"stringOptional", "stringOptional.inconsistentValue",
				"inconsistent initialization for stringOptional between minimal and inconsistent.stringOptional," +
					" expected stringOptional.defaultValue but got stringOptional.inconsistentValue.",
				"inconsistent initialization for stringOptional between inconsistent.stringOptional and minimal," +
					" expected stringOptional.inconsistentValue but got stringOptional.defaultValue."	);
		assertInconsistent(pminimal,
				"inconsistent.stringHidden",
				"stringHidden", "stringHidden.inconsistentValue",
				"inconsistent initialization for stringHidden between minimal and inconsistent.stringHidden.",
				"inconsistent initialization for stringHidden between inconsistent.stringHidden and minimal."	);
	}
	
	private void assertWrong(
			final java.util.Properties template,
			final String source,
			final String key,
			final String value,
			final String message)
	{
		final java.util.Properties wrongProps = copy(template);
		if(value!=null)
			wrongProps.setProperty(key, value);
		else
			wrongProps.remove(key);
		
		try
		{
			new TestProperties(wrongProps, source);
			fail();
		}
		catch(RuntimeException e)
		{
			assertEquals(message, e.getMessage());
		}
	}
	
	private void assertInconsistent(
			final java.util.Properties template,
			final String source,
			final String key,
			final String value,
			final String message1, final String message2)
	{
		final TestProperties templateProps = new TestProperties(template, "minimal");
		templateProps.assertIt();
		
		final java.util.Properties p = copy(template);
		p.setProperty(key, value);
		final TestProperties inconsistentProps = new TestProperties(p, source);
		try
		{
			templateProps.ensureEquality(inconsistentProps);
			fail();
		}
		catch(RuntimeException e)
		{
			assertEquals(message1, 	e.getMessage());
		}
		try
		{
			inconsistentProps.ensureEquality(templateProps);
			fail();
		}
		catch(RuntimeException e)
		{
			assertEquals(message2, 	e.getMessage());
		}
	}
	
	private static final java.util.Properties copy(final java.util.Properties source)
	{
		return (java.util.Properties)source.clone();
	}
}
