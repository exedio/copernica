package com.exedio.cope.lib;

import java.util.Collection;

import com.exedio.cope.lib.search.Condition;
import com.exedio.cope.testmodel.AttributeItem;
import com.exedio.cope.testmodel.EmptyItem;


public abstract class AttributeTest extends DatabaseLibTest
{

	protected EmptyItem someItem, someItem2;
	protected AttributeItem item;
	protected AttributeItem item2;

	public void setUp() throws Exception
	{
		super.setUp();
		deleteOnTearDown(someItem = new EmptyItem());
		deleteOnTearDown(someItem2 = new EmptyItem());
		deleteOnTearDown(item = new AttributeItem("someString", 5, 6l, 2.2, true, someItem, AttributeItem.SomeEnumeration.enumValue1));
		deleteOnTearDown(item2 = new AttributeItem("someString2", 6, 7l, 2.3, false, someItem2, AttributeItem.SomeEnumeration.enumValue2));
	}
	
	protected static Collection search(final ObjectAttribute selectAttribute)
	{
		return search(selectAttribute, null);
	}
	
	protected static Collection search(final ObjectAttribute selectAttribute, final Condition condition)
	{
		return Cope.search(new Query(selectAttribute, new Type[]{selectAttribute.getType()}, condition));
	}
	
}