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

		assertLinkPresentWithText("Attribute Item");
		clickLinkWithText("Attribute Item");
		assertTitleEquals("Attribute Item");
		assertTextPresent("Attribute Item");
	}
}
