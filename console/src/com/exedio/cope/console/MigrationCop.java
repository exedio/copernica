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

package com.exedio.cope.console;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import com.exedio.cope.Migration;
import com.exedio.cope.Model;


final class MigrationCop extends ConsoleCop
{
	MigrationCop()
	{
		super("migration");
		addParameter(TAB, TAB_MIGRATION);
	}
	
	@Override
	void writeHead(final HttpServletRequest request, final PrintStream out)
	{
		Migration_Jspm.writeHead(out);
	}
	
	int oldest = Integer.MAX_VALUE;
	int latest = Integer.MIN_VALUE;
	
	private void register(final int revision)
	{
		if(oldest>revision)
			oldest = revision;
		if(latest<revision)
			latest = revision;
	}

	@Override
	final void writeBody(final PrintStream out, final Model model, final HttpServletRequest request)
	{
		if(model.isMigrationSupported())
		{
			final List<Migration> migrations = model.getMigrations();
			final HashMap<Integer, Migration> migrationMap = new HashMap<Integer, Migration>();
			for(final Migration m : migrations)
			{
				register(m.getRevision());
				migrationMap.put(m.getRevision(), m);
			}
			
			final Map<Integer, byte[]> logsRaw = model.getMigrationLogs();
			final HashMap<Integer, String> logStrings = new HashMap<Integer, String>();
			final HashMap<Integer, TreeMap<String, String>> logProperties = new HashMap<Integer, TreeMap<String, String>>();
			try
			{
				for(final Integer v : logsRaw.keySet())
				{
					register(v);
					final byte[] infoBytes = logsRaw.get(v);
					final Properties infoProperties = Migration.parse(infoBytes);
					if(infoProperties!=null)
					{
						final TreeMap<String, String> map = new TreeMap<String, String>();
						for(final Map.Entry<Object, Object> entry : infoProperties.entrySet())
							map.put((String)entry.getKey(), (String)entry.getValue());
						logProperties.put(v, map);
						continue;
					}
					logStrings.put(v, new String(infoBytes, "latin1"));
				}
			}
			catch(UnsupportedEncodingException e)
			{
				throw new RuntimeException(e);
			}
			
			final int current = model.getMigrationRevision();
			register(current);
			
			Migration_Jspm.writeBody(this, request, out, oldest, latest, current, migrationMap, logStrings, logProperties);
		}
		else
			Migration_Jspm.writeBody(this, request, out, 0, 0, 0, null, null, null);
	}
}
