package com.exedio.cope.instrument;

import com.exedio.cope.instrument.findtype.FindType;
import com.exedio.cope.instrument.findtype.subfindtype.SubFindType;
import com.exedio.cope.instrument.findtype.subfindtype2.SubFindType2;
import com.exedio.cope.instrument.findtype.subfindtype2.SubFindType3Non;

public class ResolveImportTest extends InstrumentorTest
{
	public ResolveImportTest(final String name)
	{
		super(name);
	}
	
	public void testImports() throws InjectorParseException
	{
		final JavaFile file = new JavaFile();
		file.setPackage("com.exedio.cope.instrument.findtype");
		
		file.addImport("com.exedio.cope.instrument.findtype.subfindtype.*");
		file.addImport("com.exedio.cope.instrument.findtype.subfindtype2.SubFindType2");
		file.addImport(com.exedio.cope.instrument.findtype.subfindtype.BothFindType.class.getName());
		
		//assertEquals(FindType.class, file.findType("FindType"));
		assertEquals(FindType.class, file.findType(FindType.class.getName()));

		assertEquals(SubFindType.class, file.findType("SubFindType"));
		assertEquals(SubFindType.class, file.findType(SubFindType.class.getName()));

		assertEquals(SubFindType2.class, file.findType("SubFindType2"));
		assertEquals(SubFindType2.class, file.findType(SubFindType2.class.getName()));

		assertEquals(com.exedio.cope.instrument.findtype.BothFindType.class, file.findType("BothFindType"));
		assertEquals(com.exedio.cope.instrument.findtype.BothFindType.class, file.findType(com.exedio.cope.instrument.findtype.BothFindType.class.getName()));

		assertEquals(com.exedio.cope.instrument.findtype.subfindtype.BothFindType2.class, file.findType("BothFindType2"));
		assertEquals(com.exedio.cope.instrument.findtype.subfindtype.BothFindType2.class, file.findType(com.exedio.cope.instrument.findtype.subfindtype.BothFindType2.class.getName()));

		try
		{
			file.findType("SubFindType3Non");
		}
		catch(InjectorParseException e)
		{
			assertEquals("type SubFindType3Non not found.", e.getMessage());
		}
		assertEquals(SubFindType3Non.class, file.findType(SubFindType3Non.class.getName()));
	}

}
