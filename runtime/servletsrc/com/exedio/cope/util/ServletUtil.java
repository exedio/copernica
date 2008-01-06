/*
 * Copyright (C) 2004-2007  exedio GmbH (www.exedio.com)
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

package com.exedio.cope.util;

import java.io.File;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import com.exedio.cope.Cope;
import com.exedio.cope.Model;

public class ServletUtil
{
	private interface Config
	{
		String getInitParameter(String name);
		String getName();
		ServletContext getServletContext();
	}

	private static final String PARAMETER_MODEL = "model";

	private static final Config wrap(final Servlet servlet)
	{
		final ServletConfig config = servlet.getServletConfig();
		return new Config()
		{
			public String getInitParameter(final String name)
			{
				return config.getInitParameter(name);
			}
			public String getName()
			{
				return config.getServletName();
			}
			public ServletContext getServletContext()
			{
				return config.getServletContext();
			}
		};
	}
	
	public static final ConnectToken getConnectedModel(final Servlet servlet)
	throws ServletException
	{
		return getConnectedModel(
				wrap(servlet),
				"servlet",
				servlet);
	}
	
	private static final Config wrap(final FilterConfig config)
	{
		return new Config()
		{
			public String getInitParameter(final String name)
			{
				return config.getInitParameter(name);
			}
			public String getName()
			{
				return config.getFilterName();
			}
			public ServletContext getServletContext()
			{
				return config.getServletContext();
			}
		};
	}
	
	public static final ConnectToken getConnectedModel(final Filter filter, final FilterConfig config)
	throws ServletException
	{
		return getConnectedModel(
				wrap(config),
				"filter",
				filter);
	}
	
	private static final ConnectToken getConnectedModel(
					final Config config,
					final String kind,
					final Object nameObject)
	throws ServletException
	{
		final String initParam = config.getInitParameter(PARAMETER_MODEL);
		final String name = config.getName();
		final ServletContext context = config.getServletContext();
		
		final String description =
					kind + ' ' +
					'"' + name + '"' + ' ' +
					'(' + nameObject.getClass().getName() + '@' + System.identityHashCode(nameObject) + ')';
		//System.out.println("----------" + name + "---init-param---"+initParam+"---context-param---"+context.getInitParameter(PARAMETER_MODEL)+"---");
		final String modelName;
		final String modelNameSource;
		if(initParam==null)
		{
			final String contextParam = context.getInitParameter(PARAMETER_MODEL);
			if(contextParam==null)
				throw new ServletException(description + ": neither init-param nor context-param '"+PARAMETER_MODEL+"' set");
			modelName = contextParam;
			modelNameSource = "context-param";
		}
		else
		{
			modelName = initParam;
			modelNameSource = "init-param";
		}
		
		final Model result;
		try
		{
			result = Cope.getModel(modelName);
		}
		catch(IllegalArgumentException e)
		{
			throw new ServletException(description + ", " + modelNameSource + ' ' + PARAMETER_MODEL + ':' + ' ' + e.getMessage(), e);
		}
		return connect(result, context, description);
	}
	
	/**
	 * Connects the model using the properties from
	 * the file <tt>cope.properties</tt>
	 * in the directory <tt>WEB-INF</tt>
	 * of the web application.
	 * @see Model#connect(com.exedio.cope.ConnectProperties)
	 * @see ConnectToken#issue(Model,com.exedio.cope.ConnectProperties,String)
	 */
	public static final ConnectToken connect(final Model model, final ServletContext context, final String name)
	{
		return ConnectToken.issue(model,
			new com.exedio.cope.ConnectProperties(
				new File(context.getRealPath("WEB-INF/cope.properties")), getPropertyContext(context)), name);
	}
	
	public static final Properties.Context getPropertyContext(final ServletContext context)
	{
		return new Properties.Context(){
					public String get(final String key)
					{
						return context.getInitParameter(key);
					}

					@Override
					public String toString()
					{
						return "javax.servlet.ServletContext.getInitParameter of '" + context.getServletContextName() + '\'';
					}
				};
	}

	/**
	 * @deprecated Use {@link #getConnectedModel(Servlet)} instead
	 */
	@Deprecated
	public static final ConnectToken getModel(final Servlet servlet)
	throws ServletException
	{
		return getConnectedModel(servlet);
	}

	/**
	 * @deprecated Use {@link #getConnectedModel(Filter,FilterConfig)} instead
	 */
	@Deprecated
	public static final ConnectToken getModel(final Filter filter, final FilterConfig config)
	throws ServletException
	{
		return getConnectedModel(filter, config);
	}

	/**
	 * @deprecated Renamed to {@link #connect(Model, ServletContext, String)}.
	 */
	@Deprecated
	public static final ConnectToken initialize(final Model model, final ServletContext context, final String name)
	{
		return connect(model, context, name);
	}
}
