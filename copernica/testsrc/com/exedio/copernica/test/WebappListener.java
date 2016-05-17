/*
 * Copyright (C) 2004-2015  exedio GmbH (www.exedio.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package com.exedio.copernica.test;

import static com.exedio.cope.misc.ConnectToken.removeProperties;
import static com.exedio.cope.misc.ConnectToken.setProperties;

import com.exedio.cope.ConnectProperties;
import com.exedio.cope.servletutil.ServletSource;
import com.exedio.cope.testmodel.Main;
import com.exedio.cope.util.Properties;
import com.exedio.cope.util.Properties.Source;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * This listener should be the only listener with the whole web.xml.
 * If you need more, refactor / extend this class.
 */
public final class WebappListener implements ServletContextListener
{
	@Override
	public void contextInitialized(final ServletContextEvent sce)
	{
		final ServletContext ctx = sce.getServletContext();

		setProperties(Main.model, new ConnectProperties(without(ServletSource.create(ctx), "contextPath"), null));
	}

	/**
	 * Needed because
	 * {@link ConnectProperties#ConnectProperties(Source, Source)
	 * calls {@link Properties#ensureEquality(Properties)}.
	 */
	private static Source without(final Source s, final String key)
	{
		return new Source()
		{
			@Override
			public String get(String currentKey)
			{
				return key.equals(currentKey) ? null : s.get(currentKey);
			}

			@Override
			public Collection<String> keySet()
			{
				final ArrayList<String> result = new ArrayList<String>(s.keySet());
				result.remove(key);
				return result;
			}

			@Override
			public String getDescription()
			{
				return s.getDescription() + " without " + key;
			}
		};
	}

	@Override
	public void contextDestroyed(final ServletContextEvent sce)
	{
		removeProperties(Main.model);
	}
}
