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
import com.exedio.cope.LengthViolationException;
import com.exedio.cope.MandatoryViolationException;
import com.exedio.cope.Type;
import com.exedio.cope.UniqueViolationException;
import com.exedio.cope.instrument.testmodel.DoubleUnique;
import com.exedio.cope.instrument.testmodel.Qualified;
import com.exedio.cope.instrument.testmodel.QualifiedName;
import com.exedio.cope.instrument.testmodel.Standard;
import com.exedio.cope.instrument.testmodel.Sub;
import com.exedio.cope.instrument.testmodel.Super;
import com.exedio.cope.instrument.testmodel.TypeNone;
import com.exedio.cope.instrument.testmodel.TypePrivate;
import com.exedio.cope.instrument.testmodel.sub.SubTarget;
import com.exedio.cope.util.ReactivationConstructorDummy;


public class GeneratorTest extends InstrumentorTest
{
	public static final int PUBLIC = Modifier.PUBLIC;
	public static final int PROTECTED = Modifier.PROTECTED;
	public static final int PRIVATE = Modifier.PRIVATE;
	public static final int STATIC = Modifier.STATIC;
	public static final int FINAL = Modifier.FINAL;
	
	final static Class STRING = String.class;
	
	final static Class MANDATORY_VIOLATION = MandatoryViolationException.class;
	final static Class UNIQUE_VIOLATION = UniqueViolationException.class;
	final static Class REACTIVATION_DUMMY = ReactivationConstructorDummy.class;
	
	public void testStandard() throws ClassNotFoundException
	{
		final Class standard = Standard.class;
		assertConstructor(standard, new Class[]{
				STRING, // notNullString
				STRING, // readOnlyString
				STRING, // initialString
				int.class, // nativeInteger
				long.class, // nativeLong
				double.class, // nativeDouble
				boolean.class, // nativeBoolean
				Date.class, // mandatoryDate
			}, PUBLIC,
			new Class[]{
				MANDATORY_VIOLATION,
			});
		assertConstructor(standard, new Class[]{(new AttributeValue[0]).getClass()}, PRIVATE);
		assertConstructor(standard, new Class[]{REACTIVATION_DUMMY, int.class}, PRIVATE);

		assertMethod(standard, "getDefaultString", STRING, PUBLIC|FINAL);
		assertMethod(standard, "setDefaultString", new Class[]{STRING}, PUBLIC|FINAL);
		assertMethod(standard, "getNotNullString", STRING, PUBLIC|FINAL);
		assertMethod(standard, "setNotNullString", new Class[]{STRING}, PUBLIC|FINAL, new Class[]{MANDATORY_VIOLATION});
		assertMethod(standard, "getReadOnlyString", STRING, PUBLIC|FINAL);
		assertNoMethod(standard, "setReadOnlyString", new Class[]{STRING});
		assertMethod(standard, "getUniqueString", STRING, PUBLIC|FINAL);
		assertMethod(standard, "setUniqueString", new Class[]{STRING}, PUBLIC|FINAL, new Class[]{UNIQUE_VIOLATION});
		assertMethod(standard, "findByUniqueString", new Class[]{STRING}, standard, PUBLIC|STATIC|FINAL);
		assertMethod(standard, "getInitialString", STRING, PUBLIC|FINAL);
		assertMethod(standard, "setInitialString", new Class[]{STRING}, PUBLIC|FINAL);

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
		assertMethod(standard, "setMandatoryDate", new Class[]{Date.class}, PUBLIC|FINAL, new Class[]{MANDATORY_VIOLATION});
		assertMethod(standard, "touchMandatoryDate", new Class[]{}, PUBLIC|FINAL);

		assertMethod(standard, "getPrivateDate", Date.class, PRIVATE|FINAL);
		assertMethod(standard, "setPrivateDate", new Class[]{Date.class}, PRIVATE|FINAL);
		assertMethod(standard, "touchPrivateDate", new Class[]{}, PRIVATE|FINAL);

		assertMethod(standard, "getPrivateString", STRING, PRIVATE|FINAL);
		assertMethod(standard, "setPrivateString", new Class[]{STRING}, PRIVATE|FINAL);

		assertNoMethod(standard, "getNoneGetterString");
		assertMethod(standard, "setNoneGetterString", new Class[]{STRING}, PUBLIC|FINAL);
		assertMethod(standard, "getPrivateGetterString", STRING, PRIVATE|FINAL);
		assertMethod(standard, "setPrivateGetterString", new Class[]{STRING}, PUBLIC|FINAL);
		assertMethod(standard, "getInternalGetterStringInternal", STRING, PRIVATE|FINAL);
		assertMethod(standard, "setInternalGetterString", new Class[]{STRING}, PUBLIC|FINAL);
		assertNoMethod(standard, "getInternalGetterString");

		assertMethod(standard, "getNoneSetterString", STRING, PUBLIC|FINAL);
		assertNoMethod(standard, "setNoneSetterString", new Class[]{STRING});
		assertMethod(standard, "getPrivateSetterString", STRING, PUBLIC|FINAL);
		assertMethod(standard, "setPrivateSetterString", new Class[]{STRING}, PRIVATE|FINAL);
		assertMethod(standard, "getInternalSetterString", STRING, PUBLIC|FINAL);
		assertMethod(standard, "setInternalSetterStringInternal", new Class[]{STRING}, PRIVATE|FINAL);
		assertNoMethod(standard, "setInternalSetterString", new Class[]{STRING});

		assertMethod(standard, "getNonfinalGetterString", STRING, PUBLIC);
		assertMethod(standard, "setNonfinalGetterString", new Class[]{STRING}, PROTECTED|FINAL);
		assertMethod(standard, "getNonfinalSetterString", STRING, PROTECTED|FINAL);
		assertMethod(standard, "setNonfinalSetterString", new Class[]{STRING}, PUBLIC);

		assertMethod(standard, "isAsIsBoolean", Boolean.class, PUBLIC|FINAL);
		assertNoMethod(standard, "getAsIsBoolean");
		assertMethod(standard, "setAsIsBoolean", new Class[]{Boolean.class}, PUBLIC|FINAL);
		
		assertMethod(standard, "getDoubleUnique1", STRING, PUBLIC|FINAL);
		assertMethod(standard, "setDoubleUnique1", new Class[]{STRING}, PUBLIC|FINAL, new Class[]{UNIQUE_VIOLATION});
		assertMethod(standard, "getDoubleUnique2", Integer.class, PUBLIC|FINAL);
		assertMethod(standard, "setDoubleUnique2", new Class[]{Integer.class}, PUBLIC|FINAL, new Class[]{UNIQUE_VIOLATION});
		assertMethod(standard, "findByDoubleUnique", new Class[]{STRING, Integer.class}, standard, PUBLIC|STATIC|FINAL);

		assertMethod(standard, "isAnyMediaNull", boolean.class, PUBLIC|FINAL);
		assertMethod(standard, "getAnyMediaURL", STRING, PUBLIC|FINAL);
		assertMethod(standard, "getAnyMediaMimeMajor", STRING, PUBLIC|FINAL);
		assertMethod(standard, "getAnyMediaMimeMinor", STRING, PUBLIC|FINAL);
		assertMethod(standard, "getAnyMediaContentType", STRING, PUBLIC|FINAL);
		assertMethod(standard, "getAnyMediaLength", long.class, PUBLIC|FINAL);
		assertMethod(standard, "getAnyMediaLastModified", long.class, PUBLIC|FINAL);
		assertMethod(standard, "getAnyMediaData", InputStream.class, PUBLIC|FINAL);
		assertMethod(standard, "getAnyMediaData", new Class[]{File.class}, PUBLIC|FINAL, new Class[]{IOException.class});
		assertMethod(standard, "setAnyMedia", new Class[]{InputStream.class, STRING, STRING}, PUBLIC|FINAL, new Class[]{IOException.class});
		assertMethod(standard, "setAnyMedia", new Class[]{File.class, STRING, STRING}, PUBLIC|FINAL, new Class[]{IOException.class});

		assertMethod(standard, "isMajorMediaNull", boolean.class, FINAL);
		assertMethod(standard, "getMajorMediaURL", STRING, FINAL);
		assertMethod(standard, "getMajorMediaMimeMajor", STRING, FINAL);
		assertMethod(standard, "getMajorMediaMimeMinor", STRING, FINAL);
		assertMethod(standard, "getMajorMediaContentType", STRING, FINAL);
		assertMethod(standard, "getMajorMediaLength", long.class, FINAL);
		assertMethod(standard, "getMajorMediaLastModified", long.class, FINAL);
		assertMethod(standard, "getMajorMediaData", InputStream.class, FINAL);
		assertMethod(standard, "getMajorMediaData", new Class[]{File.class}, FINAL, new Class[]{IOException.class});
		assertMethod(standard, "setMajorMedia", new Class[]{InputStream.class, STRING}, FINAL, new Class[]{IOException.class});
		assertMethod(standard, "setMajorMedia", new Class[]{File.class, STRING}, FINAL, new Class[]{IOException.class});

		assertMethod(standard, "isMinorMediaNull", boolean.class, PROTECTED|FINAL);
		assertMethod(standard, "getMinorMediaURL", STRING, PROTECTED|FINAL);
		assertMethod(standard, "getMinorMediaMimeMajor", STRING, PROTECTED|FINAL);
		assertMethod(standard, "getMinorMediaMimeMinor", STRING, PROTECTED|FINAL);
		assertMethod(standard, "getMinorMediaContentType", STRING, PROTECTED|FINAL);
		assertMethod(standard, "getMinorMediaLength", long.class, PROTECTED|FINAL);
		assertMethod(standard, "getMinorMediaLastModified", long.class, PROTECTED|FINAL);
		assertMethod(standard, "getMinorMediaData", InputStream.class, PROTECTED|FINAL);
		assertMethod(standard, "getMinorMediaData", new Class[]{File.class}, PROTECTED|FINAL, new Class[]{IOException.class});
		assertMethod(standard, "setMinorMedia", new Class[]{InputStream.class}, PROTECTED|FINAL, new Class[]{IOException.class});
		assertMethod(standard, "setMinorMedia", new Class[]{File.class}, PROTECTED|FINAL, new Class[]{IOException.class});

		assertMethod(standard, "isNoSetterMediaNull", boolean.class, PUBLIC|FINAL);
		assertMethod(standard, "getNoSetterMediaURL", STRING, PUBLIC|FINAL);
		assertMethod(standard, "getNoSetterMediaMimeMajor", STRING, PUBLIC|FINAL);
		assertMethod(standard, "getNoSetterMediaMimeMinor", STRING, PUBLIC|FINAL);
		assertMethod(standard, "getNoSetterMediaContentType", STRING, PUBLIC|FINAL);
		assertMethod(standard, "getNoSetterMediaLength", long.class, PUBLIC|FINAL);
		assertMethod(standard, "getNoSetterMediaLastModified", long.class, PUBLIC|FINAL);
		assertMethod(standard, "getNoSetterMediaData", InputStream.class, PUBLIC|FINAL);
		assertMethod(standard, "getNoSetterMediaData", new Class[]{File.class}, PUBLIC|FINAL, new Class[]{IOException.class});
		assertNoMethod(standard, "setNoSetterMedia", new Class[]{InputStream.class,STRING,STRING});
		assertNoMethod(standard, "setNoSetterMedia", new Class[]{File.class,STRING,STRING});

		assertMethod(standard, "isPrivateSetterMediaNull", boolean.class, PUBLIC|FINAL);
		assertMethod(standard, "getPrivateSetterMediaURL", STRING, PUBLIC|FINAL);
		assertMethod(standard, "getPrivateSetterMediaMimeMajor", STRING, PUBLIC|FINAL);
		assertMethod(standard, "getPrivateSetterMediaMimeMinor", STRING, PUBLIC|FINAL);
		assertMethod(standard, "getPrivateSetterMediaContentType", STRING, PUBLIC|FINAL);
		assertMethod(standard, "getPrivateSetterMediaLength", long.class, PUBLIC|FINAL);
		assertMethod(standard, "getPrivateSetterMediaLastModified", long.class, PUBLIC|FINAL);
		assertMethod(standard, "getPrivateSetterMediaData", InputStream.class, PUBLIC|FINAL);
		assertMethod(standard, "getPrivateSetterMediaData", new Class[]{File.class}, PUBLIC|FINAL, new Class[]{IOException.class});
		assertMethod(standard, "setPrivateSetterMedia", new Class[]{InputStream.class,STRING,STRING}, PRIVATE|FINAL, new Class[]{IOException.class});
		assertMethod(standard, "setPrivateSetterMedia", new Class[]{File.class,STRING,STRING}, PRIVATE|FINAL, new Class[]{IOException.class});

		assertMethod(standard, "checkPublicHash", new Class[]{STRING}, Boolean.TYPE, PUBLIC|FINAL);
		assertMethod(standard, "checkPrivateHash", new Class[]{STRING}, Boolean.TYPE, PRIVATE|FINAL);
		assertMethod(standard, "checkMandatoryHash", new Class[]{STRING}, Boolean.TYPE, PUBLIC|FINAL);
		assertMethod(standard, "setPublicHash", new Class[]{STRING}, PUBLIC|FINAL);
		assertMethod(standard, "setPrivateHash", new Class[]{STRING}, PRIVATE|FINAL);
		assertMethod(standard, "setMandatoryHash", new Class[]{STRING}, PUBLIC|FINAL, new Class[]{MANDATORY_VIOLATION});
		assertNoMethod(standard, "getPublicHash");
		assertNoMethod(standard, "getPrivateHash");
		assertNoMethod(standard, "getMandatoryHash");

		assertField(standard, "TYPE", Type.class, PUBLIC|STATIC|FINAL);

		final Class typeNone = TypeNone.class;
		assertConstructor(typeNone, new Class[]{}, PRIVATE);
		assertConstructor(typeNone, new Class[]{(new AttributeValue[0]).getClass()}, PUBLIC); // @cope.generic.constructor public
		assertConstructor(typeNone, new Class[]{REACTIVATION_DUMMY, int.class}, PRIVATE);
		assertMethod(typeNone, "getDefaultString", STRING, PUBLIC|FINAL);
		assertMethod(typeNone, "setDefaultString", new Class[]{STRING}, PUBLIC|FINAL);
		assertNoField(typeNone, "TYPE");

		final Class typePrivate = TypePrivate.class;
		assertConstructor(typePrivate, new Class[]{}, PUBLIC);
		assertConstructor(typePrivate, new Class[]{(new AttributeValue[0]).getClass()}, PRIVATE);
		assertConstructor(typePrivate, new Class[]{REACTIVATION_DUMMY, int.class}, PRIVATE);
		assertMethod(typePrivate, "getDefaultString", STRING, PUBLIC|FINAL);
		assertMethod(typePrivate, "setDefaultString", new Class[]{STRING}, PUBLIC|FINAL);
		assertField(typePrivate, "TYPE", Type.class, PRIVATE|STATIC|FINAL);
	}

	public void testDoubleUnique() throws ClassNotFoundException
	{
		final Class doubleUnique = DoubleUnique.class;
		final Class subTarget = SubTarget.class;
		assertConstructor(doubleUnique, new Class[]{STRING, subTarget}, PUBLIC, new Class[]{MANDATORY_VIOLATION, UNIQUE_VIOLATION});
		assertMethod(doubleUnique, "getString", STRING, PUBLIC|FINAL);
		assertMethod(doubleUnique, "getItem", subTarget, PUBLIC|FINAL);
		assertMethod(doubleUnique, "findByUnique", new Class[]{STRING, subTarget}, doubleUnique, PUBLIC|STATIC|FINAL);
	}
	
	public void testQualified() throws ClassNotFoundException
	{
		final Class qualified = Qualified.class;
		final Class qualifiedString = QualifiedName.class;
		assertMethod(qualified, "getNameQualifier", new Class[]{STRING}, qualifiedString, PUBLIC|FINAL);
		assertMethod(qualified, "getNumber", new Class[]{STRING}, Integer.class, PUBLIC|FINAL);
		assertMethod(qualified, "setNumber", new Class[]{STRING, int.class}, PUBLIC|FINAL);
		assertMethod(qualified, "getOptionalNumber", new Class[]{STRING}, Integer.class, PUBLIC|FINAL);
		assertMethod(qualified, "setOptionalNumber", new Class[]{STRING, Integer.class}, PUBLIC|FINAL);
		assertNoMethod(qualified, "getNoneGetterNumber", new Class[]{STRING});
		assertMethod(qualified, "setNoneGetterNumber", new Class[]{STRING, int.class}, PUBLIC|FINAL);
		assertMethod(qualified, "getPrivateGetterNumber", new Class[]{STRING}, Integer.class, PRIVATE|FINAL);
		assertMethod(qualified, "setPrivateGetterNumber", new Class[]{STRING, int.class}, PUBLIC|FINAL);
		assertMethod(qualified, "getInternalGetterNumberInternal", new Class[]{STRING}, Integer.class, PRIVATE|FINAL);
		assertMethod(qualified, "setInternalGetterNumber", new Class[]{STRING, int.class}, PUBLIC|FINAL);
		assertNoMethod(qualified, "getInternalGetterNumber", new Class[]{STRING});
		
		assertMethod(qualified, "getNoneSetterNumber", new Class[]{STRING}, Integer.class, PUBLIC|FINAL);
		assertNoMethod(qualified, "setNoneSetterNumber", new Class[]{STRING, int.class});
		assertNoMethod(qualified, "setNoneSetterNumber", new Class[]{STRING, Integer.class});
		assertMethod(qualified, "getPrivateSetterNumber", new Class[]{STRING}, Integer.class, PUBLIC|FINAL);
		assertMethod(qualified, "setPrivateSetterNumber", new Class[]{STRING, int.class}, PRIVATE|FINAL);
		assertMethod(qualified, "getInternalSetterNumber", new Class[]{STRING}, Integer.class, PUBLIC|FINAL);
		assertMethod(qualified, "setInternalSetterNumberInternal", new Class[]{STRING, int.class}, PRIVATE|FINAL);
		assertNoMethod(qualified, "setInternalSetterNumber", new Class[]{STRING, int.class});
		assertNoMethod(qualified, "setInternalSetterNumber", new Class[]{STRING, Integer.class});
	}
	
	public void testHierarchy() throws ClassNotFoundException
	{
		final Class superc = Super.class;
		final Class sub = Sub.class;

		assertConstructor(superc, new Class[]{
				STRING, // superMandatory
				Integer.class, // superInitial
			}, PUBLIC,
			new Class[]{
				LengthViolationException.class,
				MANDATORY_VIOLATION,
			});
		assertConstructor(superc, new Class[]{(new AttributeValue[0]).getClass()}, PROTECTED);
		assertConstructor(superc, new Class[]{REACTIVATION_DUMMY, int.class}, PROTECTED);

		assertConstructor(sub, new Class[]{
				STRING, // superMandatory
				Integer.class, // superInitial
				boolean.class, // subMandatory
				Long.class, // subInitial
			}, PUBLIC,
			new Class[]{
				LengthViolationException.class,
				MANDATORY_VIOLATION,
			});
		assertConstructor(sub, new Class[]{(new AttributeValue[0]).getClass()}, PRIVATE);
		assertConstructor(sub, new Class[]{REACTIVATION_DUMMY, int.class}, PRIVATE);
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
		assertConstructor(javaClass, parameterTypes, modifiers, new Class[]{});
	}
	
	void assertConstructor(
			final Class javaClass, final Class[] parameterTypes, final int modifiers, final Class[] exceptionTypes)
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
		assertEquals(Arrays.asList(exceptionTypes), Arrays.asList(constructor.getExceptionTypes()));
	}

}
