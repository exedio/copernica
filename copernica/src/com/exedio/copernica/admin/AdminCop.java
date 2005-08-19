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

package com.exedio.copernica.admin;

import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.http.HttpServletRequest;

import com.exedio.cope.Model;
import com.exedio.cops.Cop;

abstract class AdminCop extends Cop
{
	final String name;

	protected AdminCop(final String name)
	{
		super("admin.jsp");
		this.name = name;
	}
	
	final PropertiesCop toProperties()
	{
		return new PropertiesCop();
	}
	
	final SchemaCop toSchema()
	{
		return new SchemaCop(null, false, false);
	}
	
	final StatisticsCop toStatistics() // TODO rename toConnectionPoolStats
	{
		return new StatisticsCop();
	}
	
	final HttpEntityStatsCop toHttpEntityStats()
	{
		return new HttpEntityStatsCop();
	}
	
	void writeHead(PrintStream out) throws IOException
	{
		// default implementation does nothing
	}
	
	abstract void writeBody(PrintStream out, Model model) throws IOException;
	
	static final AdminCop getCop(final HttpServletRequest request)
	{
		if(request.getParameter(StatisticsCop.STATISTICS)!=null)
			return new StatisticsCop();
		if(request.getParameter(HttpEntityStatsCop.STATISTICS)!=null)
			return new HttpEntityStatsCop();
		
		final String schemaID = request.getParameter(SchemaCop.SCHEMA);
		if(schemaID==null)
		{
			return new PropertiesCop();
		}
		else
		{
			boolean showDropBoxes = false;
			boolean showRenameFields = false;

			final String[] showIDs = request.getParameterValues(SchemaCop.SHOW);
			if(showIDs!=null)
			{
				for(int i = 0; i<showIDs.length; i++)
				{
					final String showID = showIDs[i];
					if(SchemaCop.SHOW_DROP_BOXES.equals(showID))
						showDropBoxes = true;
					else if(SchemaCop.SHOW_RENAME_FIELDS.equals(showID))
						showRenameFields = true;
					else
						throw new RuntimeException(showID);
				}
			}
			
			if(schemaID.length()==0)
				return new SchemaCop(null, showDropBoxes, showRenameFields);
			else
				return new SchemaCop(schemaID, showDropBoxes, showRenameFields);
		}
	}

}
