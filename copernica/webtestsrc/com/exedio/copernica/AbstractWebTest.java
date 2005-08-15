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
package com.exedio.copernica;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sourceforge.jwebunit.TestContext;
import net.sourceforge.jwebunit.WebTestCase;

import org.xml.sax.SAXException;

import com.meterware.httpunit.Button;
import com.meterware.httpunit.WebForm;

public class AbstractWebTest extends WebTestCase
{

	protected static final String SAVE_BUTTON = "Save";

	public void setUp() throws Exception
	{
		super.setUp();
		final TestContext ctx = getTestContext();
		ctx.setBaseUrl("http://127.0.0.1:8080/copetest-hsqldb/");
		ctx.setAuthorization("admin", "nimda");
		beginAt("admin.jsp");
		submit("CREATE");
		beginAt("init.jsp");
		submit("INIT");
	}
	
	public void tearDown() throws Exception
	{
		beginAt("admin.jsp");
		submit("DROP");
		super.tearDown();
	}
	
	protected final void assertFormElementEqualsWithLabel(final String formElementLabel, final String expectedValue)
	{
		final String formElementName = getDialog().getFormElementNameForLabel(formElementLabel);
		assertNotNull("no form element with label "+formElementLabel, formElementName);
		assertFormElementEquals(formElementName, expectedValue);
	}
	
	protected final void submitWithValue(final String buttonValue) throws SAXException, IOException
	{
		final Button buttonWithValue = null;
		final WebForm[] forms = getDialog().getResponse().getForms();
		for(int i = 0; i<forms.length; i++)
		{
			final Button[] buttons = forms[i].getButtons();
			for(int j = 0; j<buttons.length; j++)
			{
				final Button button = buttons[j];
				if(button.getValue().equals(buttonValue))
				{
					if(buttonWithValue!=null)
						fail("there is more than one button with value "+buttonValue);
					else
					{
						submit(button.getName());
						return;
					}
				}
			}
		}
		fail("there is no button with value "+buttonValue);
	}
	
	private static final String DATE_FORMAT_FULL = "dd.MM.yyyy HH:mm:ss.SSS";

	/**
	 * @param resolution the expected resolution of the timestamp saved in param <code>actual</code>, in milliseonds
	 */
	protected final static void assertWithin(final long resolution, final Date expectedBefore, final Date expectedAfter, final Date actual)
	{
		final Date expectedBeforeFloor = new Date(expectedBefore.getTime() - resolution);
		final Date expectedAfterCeil   = new Date(expectedAfter.getTime()  + resolution);

		//final SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_FULL);
		//System.out.println();
		//System.out.println("FLOOR: " + df.format(expectedBefore) + " to " + df.format(expectedBeforeFloor));
		//System.out.println("CEIL:  " + df.format(expectedAfter)  + " to " + df.format(expectedAfterCeil));
		
		assertWithin(expectedBeforeFloor, expectedAfterCeil, actual);
	}

	protected final static void assertWithin(final Date expectedBefore, final Date expectedAfter, final Date actual)
	{
		final SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_FULL);
		final String message =
			"expected date within " + df.format(expectedBefore) +
			" and " + df.format(expectedAfter) +
			", but was " + df.format(actual);

		assertTrue(message, !expectedBefore.after(actual));
		assertTrue(message, !expectedAfter.before(actual));
	}
	
}
