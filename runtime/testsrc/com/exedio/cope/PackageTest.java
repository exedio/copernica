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

import java.util.Enumeration;
import java.util.HashMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.exedio.cope.badquery.BadQueryTest;

public class PackageTest extends TestCase
{

	public static TestSuite suite()
	{
		final TestSuite suite = new TestSuite();
		
		suite.addTestSuite( TrimTest.class );
		suite.addTestSuite( GetModelTest.class );
		suite.addTestSuite( TypeCollisionTest.class );
		suite.addTestSuite( QueryTest.class );
		suite.addTestSuite( QueryKeyTest.class );
		suite.addTestSuite( PkSourceSequentialTest.class );
		suite.addTestSuite( PkSourceButterflyTest.class );
		suite.addTestSuite( ModelTest.class );
		suite.addTestSuite( HiddenFeatureTest.class );
		suite.addTestSuite( ItemTest.class );
		suite.addTestSuite( ItemSerializationTest.class );
		
		suite.addTestSuite( FieldIntegerTest.class );
		suite.addTestSuite( FieldLongTest.class );
		suite.addTestSuite( FieldDoubleTest.class );
		suite.addTestSuite( FieldBooleanTest.class );
		suite.addTestSuite( FieldDateTest.class );
		suite.addTestSuite( DayFieldTest.class );
		suite.addTestSuite( FieldItemTest.class );
		suite.addTestSuite( FieldEnumTest.class );
		suite.addTestSuite( FieldMediaTest.class );
		suite.addTestSuite( FieldQualifierTest.class );
		
		suite.addTestSuite( DeleteTest.class );
		suite.addTestSuite( DeleteHierarchyTest.class );
		suite.addTestSuite( NameTest.class );
		suite.addTestSuite( StringTest.class );
		suite.addTestSuite( EnumTest.class );
		suite.addTestSuite( DefaultToTest.class );
		suite.addTestSuite( MatchTest.class );
		suite.addTestSuite( DataTest.class );
		suite.addTestSuite( UniqueItemTest.class );
		suite.addTestSuite( HierarchyTest.class );
		suite.addTestSuite( HierarchyEmptyTest.class );
		suite.addTestSuite( SearchTest.class );
		suite.addTestSuite( PlusTest.class );
		suite.addTestSuite( PlusOrderTest.class );
		suite.addTestSuite( OrderByTest.class );
		suite.addTestSuite( SelectTest.class );
		suite.addTestSuite( DistinctTest.class );
		suite.addTestSuite( FunctionTest.class );
		suite.addTestSuite( CompareConditionTest.class );
		suite.addTestSuite( CompareFunctionConditionTest.class );
		suite.addTestSuite( TypeInConditionTest.class );
		suite.addTestSuite( JoinTest.class );
		suite.addTestSuite( JoinOuterTest.class );
		suite.addTestSuite( JoinMultipleTest.class );
		suite.addTestSuite( JoinFunctionTest.class );
		suite.addTestSuite( HardJoinTest.class );
		suite.addTestSuite( SchemaTest.class );
		suite.addTestSuite( StatementInfoTest.class );
		suite.addTestSuite( TransactionTest.class );
		suite.addTestSuite( CacheIsolationTest.class );
		suite.addTestSuite( PolymorphQueryCacheInvalidationTest.class );
		suite.addTestSuite( TransactionOnlyTest.class );
		suite.addTestSuite( ModificationListenerTest.class );

		suite.addTestSuite( BadQueryTest.class );
		suite.addTest( com.exedio.cope.pattern.PackageTest.suite() );
		suite.addTest( com.exedio.cope.util.PackageTest.suite() );
		
		return suite;
	}

	private static final void collectModels(final TestSuite suite, final HashMap<Model, Properties> models)
	{
		for(Enumeration e = suite.tests(); e.hasMoreElements(); )
		{
			final Test test = (Test)e.nextElement();

			if(test instanceof com.exedio.cope.junit.CopeTest)
			{
				final com.exedio.cope.junit.CopeTest copeTest = (com.exedio.cope.junit.CopeTest)test;
				final Model model = copeTest.model;
				if(!models.containsKey(model))
					models.put(model, copeTest.getProperties());
			}
			else if(test instanceof TestSuite)
				collectModels((TestSuite)test, models);
		}
	}
	
	public static void main(String[] args)
	{
		final HashMap<Model, Properties> models = new HashMap<Model, Properties>();
		collectModels(PackageTest.suite(), models);
		for(final Model m : models.keySet())
		{
			//System.out.println("teardown " + m.getTypes());
			m.connect(models.get(m));
			m.tearDownDatabase();
		}
	}
}
