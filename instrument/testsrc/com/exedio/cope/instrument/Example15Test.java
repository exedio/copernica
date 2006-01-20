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

import java.lang.reflect.Modifier;

public class Example15Test extends InjectorTest
{

	// TODO spaces in generics 
	// TODO constructor with generics 
	// TODO enums
	// TODO annotations

	public Example15Test()
	{
		super("Example15.java", false);
	}

	public void assertInjection()
	{
		assertPackage("com.exedio.cope.instrument");

		assertImport("java.util.Date");
		assertImport("java.util.HashMap");
		assertImport("java.util.HashSet");

		final JavaClass exampleClass = assertClass("Example15", null, null);

		final JavaAttribute name =
			assertAttributeHeader("name", "String", Modifier.PRIVATE);
		assertAttribute("name", null, name);

		final JavaAttribute dates =
			assertAttributeHeader("dates", "HashSet<Date>", Modifier.PUBLIC);
		assertAttribute("dates", null, dates);

		final JavaAttribute primes =
			assertAttributeHeader("primes", "HashMap<Integer, Boolean>", 0);
		assertAttribute("primes", null, primes);

		final JavaBehaviour setter =
			assertBehaviourHeader("set", "void", Modifier.PUBLIC);
		assertMethod("set", null, setter);

		final JavaBehaviour getDates =
			assertBehaviourHeader("getDates", "HashSet<Date>", Modifier.PRIVATE);
		assertMethod("getDates", null, getDates);

		final JavaBehaviour getPrimes =
			assertBehaviourHeader("getPrimes", "HashMap<Integer, Boolean>", 0);
		assertMethod("getPrimes", null, getPrimes);

		assertClassEnd("Example15", exampleClass);
	}

}
