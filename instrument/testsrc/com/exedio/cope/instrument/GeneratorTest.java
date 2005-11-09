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

package com.exedio.cope.instrument;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Date;

import com.exedio.cope.AttributeValue;
import com.exedio.cope.MandatoryViolationException;
import com.exedio.cope.Type;
import com.exedio.cope.UniqueViolationException;
import com.exedio.cope.instrument.testmodel.Qualified;
import com.exedio.cope.instrument.testmodel.QualifiedName;
import com.exedio.cope.instrument.testmodel.Standard;
import com.exedio.cope.instrument.testmodel.TypeNone;
import com.exedio.cope.instrument.testmodel.TypePrivate;
import com.exedio.cope.util.ReactivationConstructorDummy;


public class GeneratorTest extends InstrumentorTest
{
	public static final int PUBLIC = Modifier.PUBLIC;
	public static final int PROTECTED = Modifier.PROTECTED;
	public static final int PRIVATE = Modifier.PRIVATE;
	public static final int STATIC = Modifier.STATIC;
	public static final int FINAL = Modifier.FINAL;
	
	public void testStandard() throws ClassNotFoundException
	{
		final Class standard = Standard.class;
		assertConstructor(standard, new Class[]{
				String.class, // notNullString
				String.class, // readOnlyString
				String.class, // initialString
				int.class, // nativeInteger
				long.class, // nativeLong
				double.class, // nativeDouble
				boolean.class, // nativeBoolean
				Date.class, // mandatoryDate
			}, PUBLIC);
		assertConstructor(standard, new Class[]{(new AttributeValue[0]).getClass()}, PRIVATE);
		assertConstructor(standard, new Class[]{ReactivationConstructorDummy.class, int.class}, PRIVATE);

		assertMethod(standard, "getDefaultString", String.class, PUBLIC|FINAL);
		assertMethod(standard, "setDefaultString", new Class[]{String.class}, PUBLIC|FINAL);
		assertMethod(standard, "getNotNullString", String.class, PUBLIC|FINAL);
		assertMethod(standard, "setNotNullString", new Class[]{String.class}, PUBLIC|FINAL, new Class[]{MandatoryViolationException.class});
		assertMethod(standard, "getReadOnlyString", String.class, PUBLIC|FINAL);
		assertNoMethod(standard, "setReadOnlyString", new Class[]{String.class});
		assertMethod(standard, "getUniqueString", String.class, PUBLIC|FINAL);
		assertMethod(standard, "setUniqueString", new Class[]{String.class}, PUBLIC|FINAL, new Class[]{UniqueViolationException.class});
		assertMethod(standard, "findByUniqueString", new Class[]{String.class}, Standard.class, PUBLIC|STATIC|FINAL);
		assertMethod(standard, "getInitialString", String.class, PUBLIC|FINAL);
		assertMethod(standard, "setInitialString", new Class[]{String.class}, PUBLIC|FINAL);

		assertMethod(standard, "getDefaultInteger", Integer.class, PUBLIC|FINAL);
		assertMethod(standard, "setDefaultInteger", new Class[]{Integer.class}, PUBLIC|FINAL);
		assertMethod(standard, "getNativeInteger", int.class, PUBLIC|FINAL);
		assertMethod(standard, "setNativeInteger", new Class[]{int.class}, PUBLIC|FINAL);

		assertMethod(standard, "getDefaultLong", Long.class, PUBLIC|FINAL);
		assertMethod(standard, "setDefaultLong", new Class[]{Long.class}, PUBLIC|FINAL);
		assertMethod(standard, "getNativeLong", long.class, PUBLIC|FINAL);
		assertMethod(standard, "setNativeLong", new Class[]{long.class}, PUBLIC|FINAL);

		assertMethod(standard, "getDefaultDouble", Double.class, PUBLIC|FINAL);
		assertMethod(standard, "setDefaultDouble", new Class[]{Double.class}, PUBLIC|FINAL);
		assertMethod(standard, "getNativeDouble", double.class, PUBLIC|FINAL);
		assertMethod(standard, "setNativeDouble", new Class[]{double.class}, PUBLIC|FINAL);

		assertMethod(standard, "getDefaultBoolean", Boolean.class, PUBLIC|FINAL);
		assertMethod(standard, "setDefaultBoolean", new Class[]{Boolean.class}, PUBLIC|FINAL);
		assertMethod(standard, "getNativeBoolean", boolean.class, PUBLIC|FINAL);
		assertMethod(standard, "setNativeBoolean", new Class[]{boolean.class}, PUBLIC|FINAL);

		assertMethod(standard, "getMandatoryDate", Date.class, PUBLIC|FINAL);
		assertMethod(standard, "setMandatoryDate", new Class[]{Date.class}, PUBLIC|FINAL, new Class[]{MandatoryViolationException.class});
		assertMethod(standard, "touchMandatoryDate", new Class[]{}, PUBLIC|FINAL);

		assertMethod(standard, "getPrivateDate", Date.class, PRIVATE|FINAL);
		assertMethod(standard, "setPrivateDate", new Class[]{Date.class}, PRIVATE|FINAL);
		assertMethod(standard, "touchPrivateDate", new Class[]{}, PRIVATE|FINAL);

		assertMethod(standard, "getPrivateString", String.class, PRIVATE|FINAL);
		assertMethod(standard, "setPrivateString", new Class[]{String.class}, PRIVATE|FINAL);

		assertNoMethod(standard, "getNoneGetterString");
		assertMethod(standard, "setNoneGetterString", new Class[]{String.class}, PUBLIC|FINAL);
		assertMethod(standard, "getPrivateGetterString", String.class, PRIVATE|FINAL);
		assertMethod(standard, "setPrivateGetterString", new Class[]{String.class}, PUBLIC|FINAL);

		assertMethod(standard, "getNoneSetterString", String.class, PUBLIC|FINAL);
		assertNoMethod(standard, "setNoneSetterString", new Class[]{String.class});
		assertMethod(standard, "getPrivateSetterString", String.class, PUBLIC|FINAL);
		assertMethod(standard, "setPrivateSetterString", new Class[]{String.class}, PRIVATE|FINAL);

		assertMethod(standard, "getNonfinalGetterString", String.class, PUBLIC);
		assertMethod(standard, "setNonfinalGetterString", new Class[]{String.class}, PROTECTED|FINAL);
		assertMethod(standard, "getNonfinalSetterString", String.class, PROTECTED|FINAL);
		assertMethod(standard, "setNonfinalSetterString", new Class[]{String.class}, PUBLIC);

		assertMethod(standard, "isAsIsBoolean", Boolean.class, PUBLIC|FINAL);
		assertNoMethod(standard, "getAsIsBoolean");
		assertMethod(standard, "setAsIsBoolean", new Class[]{Boolean.class}, PUBLIC|FINAL);

		assertMethod(standard, "isAnyMediaNull", boolean.class, PUBLIC|FINAL);
		assertMethod(standard, "getAnyMediaURL", String.class, PUBLIC|FINAL);
		assertMethod(standard, "getAnyMediaMimeMajor", String.class, PUBLIC|FINAL);
		assertMethod(standard, "getAnyMediaMimeMinor", String.class, PUBLIC|FINAL);
		assertMethod(standard, "getAnyMediaContentType", String.class, PUBLIC|FINAL);
		assertMethod(standard, "getAnyMediaLength", long.class, PUBLIC|FINAL);
		assertMethod(standard, "getAnyMediaLastModified", long.class, PUBLIC|FINAL);
		assertMethod(standard, "getAnyMediaData", InputStream.class, PUBLIC|FINAL);
		assertMethod(standard, "getAnyMediaData", new Class[]{File.class}, PUBLIC|FINAL, new Class[]{IOException.class});
		assertMethod(standard, "setAnyMedia", new Class[]{InputStream.class, String.class, String.class}, PUBLIC|FINAL, new Class[]{IOException.class});
		assertMethod(standard, "setAnyMedia", new Class[]{File.class, String.class, String.class}, PUBLIC|FINAL, new Class[]{IOException.class});

		assertMethod(standard, "isMajorMediaNull", boolean.class, FINAL);
		assertMethod(standard, "getMajorMediaURL", String.class, FINAL);
		assertMethod(standard, "getMajorMediaMimeMajor", String.class, FINAL);
		assertMethod(standard, "getMajorMediaMimeMinor", String.class, FINAL);
		assertMethod(standard, "getMajorMediaContentType", String.class, FINAL);
		assertMethod(standard, "getMajorMediaLength", long.class, FINAL);
		assertMethod(standard, "getMajorMediaLastModified", long.class, FINAL);
		assertMethod(standard, "getMajorMediaData", InputStream.class, FINAL);
		assertMethod(standard, "getMajorMediaData", new Class[]{File.class}, FINAL, new Class[]{IOException.class});
		assertMethod(standard, "setMajorMedia", new Class[]{InputStream.class, String.class}, FINAL, new Class[]{IOException.class});
		assertMethod(standard, "setMajorMedia", new Class[]{File.class, String.class}, FINAL, new Class[]{IOException.class});

		assertMethod(standard, "isMinorMediaNull", boolean.class, PROTECTED|FINAL);
		assertMethod(standard, "getMinorMediaURL", String.class, PROTECTED|FINAL);
		assertMethod(standard, "getMinorMediaMimeMajor", String.class, PROTECTED|FINAL);
		assertMethod(standard, "getMinorMediaMimeMinor", String.class, PROTECTED|FINAL);
		assertMethod(standard, "getMinorMediaContentType", String.class, PROTECTED|FINAL);
		assertMethod(standard, "getMinorMediaLength", long.class, PROTECTED|FINAL);
		assertMethod(standard, "getMinorMediaLastModified", long.class, PROTECTED|FINAL);
		assertMethod(standard, "getMinorMediaData", InputStream.class, PROTECTED|FINAL);
		assertMethod(standard, "getMinorMediaData", new Class[]{File.class}, PROTECTED|FINAL, new Class[]{IOException.class});
		assertMethod(standard, "setMinorMedia", new Class[]{InputStream.class}, PROTECTED|FINAL, new Class[]{IOException.class});
		assertMethod(standard, "setMinorMedia", new Class[]{File.class}, PROTECTED|FINAL, new Class[]{IOException.class});

		assertMethod(standard, "isNoSetterMediaNull", boolean.class, PUBLIC|FINAL);
		assertMethod(standard, "getNoSetterMediaURL", String.class, PUBLIC|FINAL);
		assertMethod(standard, "getNoSetterMediaMimeMajor", String.class, PUBLIC|FINAL);
		assertMethod(standard, "getNoSetterMediaMimeMinor", String.class, PUBLIC|FINAL);
		assertMethod(standard, "getNoSetterMediaContentType", String.class, PUBLIC|FINAL);
		assertMethod(standard, "getNoSetterMediaLength", long.class, PUBLIC|FINAL);
		assertMethod(standard, "getNoSetterMediaLastModified", long.class, PUBLIC|FINAL);
		assertMethod(standard, "getNoSetterMediaData", InputStream.class, PUBLIC|FINAL);
		assertMethod(standard, "getNoSetterMediaData", new Class[]{File.class}, PUBLIC|FINAL, new Class[]{IOException.class});
		assertNoMethod(standard, "setNoSetterMedia", new Class[]{InputStream.class,String.class,String.class});
		assertNoMethod(standard, "setNoSetterMedia", new Class[]{File.class,String.class,String.class});

		assertMethod(standard, "isPrivateSetterMediaNull", boolean.class, PUBLIC|FINAL);
		assertMethod(standard, "getPrivateSetterMediaURL", String.class, PUBLIC|FINAL);
		assertMethod(standard, "getPrivateSetterMediaMimeMajor", String.class, PUBLIC|FINAL);
		assertMethod(standard, "getPrivateSetterMediaMimeMinor", String.class, PUBLIC|FINAL);
		assertMethod(standard, "getPrivateSetterMediaContentType", String.class, PUBLIC|FINAL);
		assertMethod(standard, "getPrivateSetterMediaLength", long.class, PUBLIC|FINAL);
		assertMethod(standard, "getPrivateSetterMediaLastModified", long.class, PUBLIC|FINAL);
		assertMethod(standard, "getPrivateSetterMediaData", InputStream.class, PUBLIC|FINAL);
		assertMethod(standard, "getPrivateSetterMediaData", new Class[]{File.class}, PUBLIC|FINAL, new Class[]{IOException.class});
		assertMethod(standard, "setPrivateSetterMedia", new Class[]{InputStream.class,String.class,String.class}, PRIVATE|FINAL, new Class[]{IOException.class});
		assertMethod(standard, "setPrivateSetterMedia", new Class[]{File.class,String.class,String.class}, PRIVATE|FINAL, new Class[]{IOException.class});

		assertMethod(standard, "checkPublicHash", new Class[]{String.class}, Boolean.TYPE, PUBLIC|FINAL);
		assertMethod(standard, "checkPrivateHash", new Class[]{String.class}, Boolean.TYPE, PRIVATE|FINAL);
		assertMethod(standard, "checkMandatoryHash", new Class[]{String.class}, Boolean.TYPE, PUBLIC|FINAL);
		assertMethod(standard, "setPublicHash", new Class[]{String.class}, PUBLIC|FINAL);
		assertMethod(standard, "setPrivateHash", new Class[]{String.class}, PRIVATE|FINAL);
		assertMethod(standard, "setMandatoryHash", new Class[]{String.class}, PUBLIC|FINAL, new Class[]{MandatoryViolationException.class});
		assertNoMethod(standard, "getPublicHash");
		assertNoMethod(standard, "getPrivateHash");
		assertNoMethod(standard, "getMandatoryHash");

		assertField(standard, "TYPE", Type.class, PUBLIC|STATIC|FINAL);

		final Class typeNone = TypeNone.class;
		assertConstructor(typeNone, new Class[]{}, PRIVATE);
		assertConstructor(typeNone, new Class[]{(new AttributeValue[0]).getClass()}, PUBLIC); // @cope.generic.constructor public
		assertConstructor(typeNone, new Class[]{ReactivationConstructorDummy.class, int.class}, PRIVATE);
		assertMethod(typeNone, "getDefaultString", String.class, PUBLIC|FINAL);
		assertMethod(typeNone, "setDefaultString", new Class[]{String.class}, PUBLIC|FINAL);
		assertNoField(typeNone, "TYPE");

		final Class typePrivate = TypePrivate.class;
		assertConstructor(typePrivate, new Class[]{}, PUBLIC);
		assertConstructor(typePrivate, new Class[]{(new AttributeValue[0]).getClass()}, PRIVATE);
		assertConstructor(typePrivate, new Class[]{ReactivationConstructorDummy.class, int.class}, PRIVATE);
		assertMethod(typePrivate, "getDefaultString", String.class, PUBLIC|FINAL);
		assertMethod(typePrivate, "setDefaultString", new Class[]{String.class}, PUBLIC|FINAL);
		assertField(typePrivate, "TYPE", Type.class, PRIVATE|STATIC|FINAL);
	}

	public void testQualified() throws ClassNotFoundException
	{
		final Class qualified = Qualified.class;
		final Class qualifiedString = QualifiedName.class;
		assertMethod(qualified, "getNameQualifier", new Class[]{String.class}, qualifiedString, PUBLIC|FINAL);
		assertMethod(qualified, "getNumber", new Class[]{String.class}, Integer.class, PUBLIC|FINAL);
		assertMethod(qualified, "setNumber", new Class[]{String.class, Integer.class}, PUBLIC|FINAL);
		assertMethod(qualified, "getOptionalNumber", new Class[]{String.class}, Integer.class, PUBLIC|FINAL);
		assertMethod(qualified, "setOptionalNumber", new Class[]{String.class, Integer.class}, PUBLIC|FINAL);
		assertNoMethod(qualified, "getNoneGetterNumber", new Class[]{String.class});
		assertMethod(qualified, "setNoneGetterNumber", new Class[]{String.class, Integer.class}, PUBLIC|FINAL);
		assertMethod(qualified, "getPrivateGetterNumber", new Class[]{String.class}, Integer.class, PUBLIC|FINAL); // TODO must be private
		assertMethod(qualified, "setPrivateGetterNumber", new Class[]{String.class, Integer.class}, PUBLIC|FINAL);
	}
	
	void assertField(
			final Class javaClass, final String name,
			final Class returnType, final int modifiers)
	{
		final Field field;
		try
		{
			field = javaClass.getDeclaredField(name);
		}
		catch(NoSuchFieldException e)
		{
			throw new AssertionError(e);
		}
		assertEquals(returnType, field.getType());
		assertEquals(modifiers, field.getModifiers());
	}
	
	void assertNoField(final Class javaClass, final String name)
	{
		try
		{
			javaClass.getDeclaredField(name);
			fail("field " + name + " exists.");
		}
		catch(NoSuchFieldException e)
		{
			// success
		}
	}

	void assertMethod(final Class javaClass, final String name, final Class returnType, final int modifiers)
	{
		assertMethod(javaClass, name, null, returnType, modifiers, new Class[]{});
	}
	
	void assertMethod(final Class javaClass, final String name, final Class[] parameterTypes, final int modifiers)
	{
		assertMethod(javaClass, name, parameterTypes, Void.TYPE, modifiers, new Class[]{});
	}

	void assertMethod(final Class javaClass, final String name, final Class[] parameterTypes, final int modifiers, final Class[] exceptionTypes)
	{
		assertMethod(javaClass, name, parameterTypes, Void.TYPE, modifiers, exceptionTypes);
	}

	void assertMethod(final Class javaClass, final String name, final Class[] parameterTypes, final Class returnType, final int modifiers)
	{
		assertMethod(javaClass, name, parameterTypes, returnType, modifiers, new Class[]{});
	}

	void assertMethod(
			final Class javaClass, final String name, final Class[] parameterTypes,
			final Class returnType, final int modifiers, final Class[] exceptionTypes)
	{
		final Method method;
		try
		{
			method = javaClass.getDeclaredMethod(name, parameterTypes);
		}
		catch(NoSuchMethodException e)
		{
			throw new AssertionError(e);
		}
		assertEquals(returnType, method.getReturnType());
		assertEquals(modifiers, method.getModifiers());
		assertEquals(Arrays.asList(exceptionTypes), Arrays.asList(method.getExceptionTypes()));
	}

	void assertNoMethod(final Class javaClass, final String name)
	{
		assertNoMethod(javaClass, name, null);
	}

	void assertNoMethod(final Class javaClass, final String name, final Class[] parameterTypes)
	{
		try
		{
			javaClass.getDeclaredMethod(name, parameterTypes);
			fail("method " + name + " exists.");
		}
		catch(NoSuchMethodException e)
		{
			// success
		}
	}

	void assertConstructor(
			final Class javaClass, final Class[] parameterTypes, final int modifiers)
	{
		final Constructor constructor;
		try
		{
			constructor = javaClass.getDeclaredConstructor(parameterTypes);
		}
		catch(NoSuchMethodException e)
		{
			throw new AssertionError(e);
		}
		assertEquals(modifiers, constructor.getModifiers());
	}

}
