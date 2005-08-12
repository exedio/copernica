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

package com.exedio.cope.pattern;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exedio.cope.Attribute;
import com.exedio.cope.DataAttribute;
import com.exedio.cope.Item;
import com.exedio.cope.Model;
import com.exedio.cope.NoSuchIDException;
import com.exedio.cope.Transaction;
import com.exedio.cope.Type;
import com.exedio.cope.util.ServletUtil;


/**
 * A servlet providing access to the data of cope data attributes.
 * 
 * In order to use it, you have to deploy the servlet in your <code>web.xml</code>,
 * providing the name of the cope model via an init-parameter.
 * Typically, your <code>web.xml</code> would contain a snippet like this:  
 *  
 * <pre>
 * &lt;servlet&gt;
 *    &lt;servlet-name&gt;data&lt;/servlet-name&gt;
 *    &lt;servlet-class&gt;com.exedio.cope.pattern.DataServlet&lt;/servlet-class&gt;
 *    &lt;init-param&gt;
 *       &lt;param-name&gt;model&lt;/param-name&gt;
 *       &lt;param-value&gt;{@link com.exedio.cope.Model com.bigbusiness.shop.Main#model}&lt;/param-value&gt;
 *    &lt;/init-param&gt;
 * &lt;/servlet&gt;
 * &lt;servlet-mapping&gt;
 *    &lt;servlet-name&gt;data&lt;/servlet-name&gt;
 *    &lt;url-pattern&gt;/data/*&lt;/url-pattern&gt;
 * &lt;/servlet-mapping&gt;
 * </pre>
 * 
 * @author Ralf Wiebicke
 */
public class DataServlet extends HttpServlet
{
	Model model = null;
	final HashMap dataAttributes = new HashMap();
	
	public final void init()
	{
		if(model!=null)
		{
			System.out.println("reinvokation of jspInit");
			return;
		}
		
		try
		{
			model = ServletUtil.getModel(getServletConfig());
			for(Iterator i = model.getTypes().iterator(); i.hasNext(); )
			{
				final Type type = (Type)i.next();
				for(Iterator j = type.getDeclaredAttributes().iterator(); j.hasNext(); )
				{
					final Attribute attribute = (Attribute)j.next();
					if(attribute instanceof DataAttribute)
					{
						dataAttributes.put('/'+type.getID()+'/'+attribute.getName(), attribute);
					}
				}
			}
		}
		catch(RuntimeException e)
		{
			e.printStackTrace();
			throw e;
		}
	}

	protected final void doGet(
			final HttpServletRequest request,
			final HttpServletResponse response)
		throws ServletException, IOException
	{
		if(request.getPathInfo()==null)
		{
			serveDirectory(request, response);
			return;
		}

		if(serveContent(request, response))
			return;
		
		serveError(request, response);
	}
		
	protected final void serveDirectory(
			final HttpServletRequest request,
			final HttpServletResponse response)
		throws ServletException, IOException
	{
		response.setContentType("text/html");
		
		final String prefix = request.getContextPath()+request.getServletPath();
		
		final OutputStream out = response.getOutputStream();
		
		final PrintStream p = new PrintStream(out);
		p.println("<html>");
		p.println("<head><title>cope data servlet</title><head>");
		p.println("<body>");
		p.println("<ol>");
		for(Iterator i = dataAttributes.entrySet().iterator(); i.hasNext(); )
		{
			final Map.Entry entry = (Map.Entry)i.next();
			final String key = (String)entry.getKey();
			final DataAttribute value = (DataAttribute)entry.getValue();
			p.println("<li><a href=\""+prefix+key+"/0\">"+value+"</a>");
		}
		p.println("</ol>");
		p.println("</body>");
		p.println("</html>");
		
		out.close();
	}
	
	protected final void serveError(
			final HttpServletRequest request,
			final HttpServletResponse response)
		throws ServletException, IOException
	{
		response.setStatus(response.SC_NOT_FOUND);
		response.setContentType("text/html");
		
		final PrintStream out = new PrintStream(response.getOutputStream());
		
		out.print("<html>\n" +
				"<head><title>Not Found</title><head>\n" +
				"<body>\n" +
				"<h1>Not Found</h1>\n" +
				"The requested URL was not found on this server.\n" +
				"</body>\n" +
				"</html>\n");
		
		out.close();
	}
	
	/**
	 * Sets the offset, the Expires http header is set into the future.
	 * Together with a http reverse proxy this ensures,
	 * that for that time no request for that data will reach the servlet.
	 * This may reduce the load on the server.
	 * 
	 * TODO: make this configurable, at best per DataAttribute.
	 */
	private static final long EXPIRES_OFFSET = 1000 * 5; // 5 seconds
	
	protected final boolean serveContent(
			final HttpServletRequest request,
			final HttpServletResponse response)
		throws ServletException, IOException
	{
		final String pathInfo = request.getPathInfo();
		//System.out.println("pathInfo="+pathInfo);
		if(pathInfo==null)
			return false;

		final int trailingSlash = pathInfo.lastIndexOf('/');
		if(trailingSlash<=0 && // null is leading slash, which is not allowed
			trailingSlash>=pathInfo.length()-1)
			return false;

		final String attributeString = pathInfo.substring(0, trailingSlash);
		//System.out.println("attributeString="+attributeString);

		final DataAttribute attribute = (DataAttribute)dataAttributes.get(attributeString);
		//System.out.println("attribute="+attribute);
		if(attribute==null)
			return false;
		
		final int trailingDot = pathInfo.lastIndexOf('.');
		//System.out.println("trailingDot="+trailingDot);

		final String pkString =
			(trailingDot>trailingSlash)
			? pathInfo.substring(trailingSlash+1, trailingDot)
			: pathInfo.substring(trailingSlash+1);
		//System.out.println("pkString="+pkString);

		final String id = attribute.getType().getID() + '.' + pkString;
		//System.out.println("ID="+id);
		try
		{
			model.startTransaction("DataServlet");
			final Item item = model.findByID(id);
			//System.out.println("item="+item);

			final String mimeMajor = item.getMimeMajor(attribute);
			//System.out.println("mimeMajor="+mimeMajor);
			if(mimeMajor!=null)
			{
				final String mimeMinor = item.getMimeMinor(attribute);
				//System.out.println("mimeMinor="+mimeMinor);
				response.setContentType(mimeMajor+'/'+mimeMinor);

				final long lastModified = item.getDataLastModified(attribute);
				//System.out.println("lastModified="+formatHttpDate(lastModified));
				response.setDateHeader("Last-Modified", lastModified);

				final long now = System.currentTimeMillis();
				response.setDateHeader("Expires", now+EXPIRES_OFFSET);
				
				final long ifModifiedSince = request.getDateHeader("If-Modified-Since");
				//System.out.println("ifModifiedSince="+ifModifiedSince);
				
				if(ifModifiedSince>=0 && ifModifiedSince>=lastModified)
				{
					//System.out.println("not modified");
					response.setStatus(response.SC_NOT_MODIFIED);
					
					System.out.println("ifModifiedSince="+format(ifModifiedSince)+"  lastModified="+format(lastModified)+"  NOT modified");
				}
				else
				{
					final long contentLength = item.getDataLength(attribute);
					//System.out.println("contentLength="+String.valueOf(contentLength));
					response.setHeader("Content-Length", String.valueOf(contentLength));
	
					System.out.println("ifModifiedSince="+format(ifModifiedSince)+"  lastModified="+format(lastModified)+"  modified: "+contentLength);

					ServletOutputStream out = null;
					InputStream in = null;
					try
					{
						out = response.getOutputStream();
						in = item.getData(attribute);
	
						final byte[] buffer = new byte[Math.max((int)contentLength, 50*1024)];
						for(int len = in.read(buffer); len != -1; len = in.read(buffer))
							out.write(buffer, 0, len);
					}
					finally
					{
						if(in!=null)
							in.close();
						if(out!=null)
							out.close();
					}
				}
				Transaction.commit();
				return true;
			}
			else
			{
				Transaction.commit();
				return false;
			}
		}
		catch(NoSuchIDException e)
		{
			return false;
		}
		finally
		{
			Transaction.rollbackIfNotCommitted();
		}
	}
	
	final String format(final long date)
	{
		final SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		return df.format(new Date(date));
	}
	
}
