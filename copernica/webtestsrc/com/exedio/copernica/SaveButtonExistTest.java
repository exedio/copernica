package com.exedio.copernica;


public class SaveButtonExistTest extends AbstractWebTest
{

	public SaveButtonExistTest(String name)
	{
		super(name);
	}

	public void testSaveButtonExists()
	{
		beginAt("copernica.jsp");
		assertTitleEquals("Copernica");

		clickLinkWithText("String Item");
		assertTitleEquals("String Item");
		
		final String SAVE = "SAVE";
		clickLinkWithText("StringItem.1");
		assertTitleEquals("StringItem.1");
		assertSubmitButtonPresent(SAVE);

		clickLinkWithText("Collision Item1");
		assertTitleEquals("Collision Item1");
		
		clickLinkWithText("EmptyItem.1");
		assertTitleEquals("EmptyItem.1");
		assertSubmitButtonNotPresent(SAVE);
	}
}
