package com.exedio.copernica;

import net.sourceforge.jwebunit.WebTestCase;

public class WebTest extends WebTestCase
{

	public WebTest(String name)
	{
		super(name);
	}

	public void setUp() throws Exception
	{
		super.setUp();
		getTestContext().setBaseUrl("http://localhost:8080/copernicatest/");
	}

	public void testSearch()
	{
		beginAt("copernica.jsp");
		assertTitleEquals("Copernica");
		assertLinkPresentWithText("de");

		clickLinkWithText("Attribute Item");
		assertTitleEquals("Attribute Item");
		assertTextPresent("Attribute Item");
		
		assertLinkPresentWithText("50");
		clickLinkWithText("50");
		assertTitleEquals("Attribute Item");
		assertLinkNotPresentWithText("50");

		clickLinkWithText("[X]");
		assertTitleEquals("AttributeItem.103");
		assertFormElementEquals("someNotNullString", "running100");
		assertFormElementEquals("someNotNullInteger", "107");

		setFormElement("someNotNullString", "running100changed");
		submit("SAVE");
		assertTitleEquals("AttributeItem.103");
		assertFormElementEquals("someNotNullString", "running100changed");
		assertFormElementEquals("someNotNullInteger", "107");

		setFormElement("someNotNullInteger", "1077");
		submit("SAVE");
		assertTitleEquals("AttributeItem.103");
		assertFormElementEquals("someNotNullString", "running100changed");
		assertFormElementEquals("someNotNullInteger", "1077");

		setFormElement("someNotNullString", "running100");
		setFormElement("someNotNullInteger", "107");
		submit("SAVE");
		assertTitleEquals("AttributeItem.103");
		assertFormElementEquals("someNotNullString", "running100");
		assertFormElementEquals("someNotNullInteger", "107");
	}
}
