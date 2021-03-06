/*
 * Copyright (C) 2004-2009  exedio GmbH (www.exedio.com)
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

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

import com.exedio.cope.Model;
import com.exedio.cope.misc.ConnectToken;
import com.exedio.cope.misc.ServletUtil;
import com.exedio.cops.BasicAuthorization;
import com.exedio.cops.CopsServlet;
import com.exedio.cops.Resource;

/**
 * The servlet providing Copernica, the Generic Backoffice for COPE.
 *
 * In order to use it, you have to deploy the servlet in your <tt>web.xml</tt>,
 * providing the name of the copernica provider via an init-parameter.
 * Typically, your <tt>web.xml</tt> would contain a snippet like this:
 *
 * <pre>
 * &lt;servlet&gt;
 *    &lt;servlet-name&gt;copernica&lt;/servlet-name&gt;
 *    &lt;servlet-class&gt;com.exedio.copernica.CopernicaServlet&lt;/servlet-class&gt;
 *    &lt;init-param&gt;
 *       &lt;param-name&gt;provider&lt;/param-name&gt;
 *       &lt;param-value&gt;{@link CopernicaProvider com.bigbusiness.shop.ShopProvider}&lt;/param-value&gt;
 *    &lt;/init-param&gt;
 * &lt;/servlet&gt;
 * &lt;servlet-mapping&gt;
 *    &lt;servlet-name&gt;copernica&lt;/servlet-name&gt;
 *    &lt;url-pattern&gt;/copernica.jsp/*&lt;/url-pattern&gt;
 * &lt;/servlet-mapping&gt;
 * </pre>
 *
 * If you want to use copernica &quot;purely&quot;,
 * without any customizations,
 * you may just configure the model to be used.
 * Then replace the init-param <tt>provider</tt> by
 * the following snippet.
 *
 * <pre>
 *    &lt;init-param&gt;
 *       &lt;param-name&gt;model&lt;/param-name&gt;
 *       &lt;param-value&gt;{@link com.exedio.cope.Model com.bigbusiness.shop.Main#model}&lt;/param-value&gt;
 *    &lt;/init-param&gt;
 * </pre>
 *
 * @author Ralf Wiebicke
 */
public final class CopernicaServlet extends CopsServlet
{
	private static final long serialVersionUID = 1l;

	private ConnectToken connectToken = null;
	private CopernicaProvider provider = null;

	static final Resource stylesheet = new Resource("copernica.css");
	static final Resource logo = new Resource("exedio.png");
	static final Resource shortcutIcon = new Resource("shortcutIcon.png");

	@Override
	public void init() throws ServletException
	{
		super.init();

		if(this.provider!=null)
		{
			System.out.println("reinvokation of jspInit");
			return;
		}

		this.provider = createProvider();
	}

	@Override
	public void destroy()
	{
		connectToken.returnItConditionally();
		connectToken = null;
		provider = null;
		super.destroy();
	}

	@Override
	protected void doRequest(
			final HttpServletRequest request,
			final HttpServletResponse response)
		throws IOException
	{
		PrintStream out = null;
		try
		{
			final Model model = provider.getModel();

			model.startTransaction("copernica");

			final CopernicaUser user = checkAccess(request);
			final CopernicaCop cop = CopernicaCop.getCop(provider, request);
			cop.init(request);

			out = new PrintStream(response.getOutputStream(), false, UTF_8.name());
			Copernica_Jspm.write(out, user, cop);
			out.close();

			model.commit();
		}
		catch(CopernicaAuthorizationFailedException e)
		{
			provider.getModel().rollback();
			if(out==null)
				out = new PrintStream(response.getOutputStream(), false, UTF_8.name());

			BasicAuthorization.reject(response, "Copernica");
			Copernica_Jspm.writeAuthenticationError(out, e);
		}
		catch(Exception e)
		{
			provider.getModel().rollback();
			response.setStatus(SC_INTERNAL_SERVER_ERROR);
			if(out==null)
				out = new PrintStream(response.getOutputStream(), false, UTF_8.name());

			provider.handleException(out, this, request, e);
		}
		finally
		{
			provider.getModel().rollbackIfNotCommitted();
		}
	}

	private final CopernicaProvider createProvider()
	{
		try
		{
			final ServletConfig config = getServletConfig();
			final String providerName = config.getInitParameter("provider");
			if(providerName==null)
			{
				connectToken = ServletUtil.getConnectedModel(this);
				final Model model = connectToken.getModel();
				model.reviseIfSupportedAndAutoEnabled();
				return new PureCopernicaProvider(model);
			}
			else
			{
				final Class<?> providerClass = Class.forName(providerName);
				final CopernicaProvider provider = (CopernicaProvider)providerClass.newInstance();
				connectToken = provider.connect(config, "servlet \"" + config.getServletName() +'"' + ' ' + '(' + providerClass.getName() + ')');
				return provider;
			}
		}
		catch(ClassNotFoundException e)
		{
			throw new RuntimeException(e);
		}
		catch(InstantiationException e)
		{
			throw new RuntimeException(e);
		}
		catch(IllegalAccessException e)
		{
			throw new RuntimeException(e);
		}
	}

	private final CopernicaUser checkAccess(final HttpServletRequest request)
		throws CopernicaAuthorizationFailedException
	{
		if(!provider.requiresAuthorization())
			return null;

		final String[] authorization = BasicAuthorization.getUserAndPassword(request);
		if(authorization==null)
			throw new CopernicaAuthorizationFailedException("noauth");

		final String userid = authorization[0];
		final String password = authorization[1];

		final CopernicaUser user = provider.findUserByID(userid);
		//System.out.println("user:"+user);
		if(user==null)
			throw new CopernicaAuthorizationFailedException("nouser", userid);

		if(!user.checkCopernicaPassword(password))
			throw new CopernicaAuthorizationFailedException("badpass", userid);

		return user;
	}
}
