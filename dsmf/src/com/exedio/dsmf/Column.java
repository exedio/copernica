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
package com.exedio.dsmf;


public final class Column extends Node
{
	final Table table;
	final String name;
	private final String requiredType;
	private String existingType;
		
	public Column(final Table table, final String name, final String type)
	{
		this(table, name, type, true);
	}

	Column(final Table table, final String name, final String type, final boolean required)
	{
		super(table.driver, table.connectionProvider);

		if(table==null)
			throw new RuntimeException(name);
		if(name==null)
			throw new RuntimeException(type);
		if(type==null)
			throw new RuntimeException(name);

		this.table = table;
		this.name = name;
		if(required)
		{
			this.requiredType = type;
			this.existingType = null;
		}
		else
		{
			this.requiredType = null;
			this.existingType = type;
		}
		table.register(this);
	}
	
	public final Table getTable()
	{
		return table;
	}

	public final String getName()
	{
		return name;
	}

	void notifyExists(final String existingType)
	{
		if(existingType==null)
			throw new RuntimeException(name);
		if(this.existingType!=null && !this.existingType.equals(existingType))
			throw new RuntimeException(name);

		this.existingType = existingType;
	}

	void finish()
	{
		if(cumulativeColor!=COLOR_NOT_YET_CALC || particularColor!=COLOR_NOT_YET_CALC)
			throw new RuntimeException();

		final String error;
		final int particularColor;
		if(existingType==null)
		{
			error = "missing";
			particularColor = COLOR_ERROR;
		}
		else if(requiredType==null)
		{
			error = "not used";
			particularColor = COLOR_WARNING;
		}
		else
		{
			if(requiredType!=null &&
				existingType!=null &&
				!requiredType.equals(existingType))
			{
				error = "different type in database: >"+existingType+"<";
				particularColor = COLOR_ERROR;
			}
			else
			{
				error = null;
				particularColor = COLOR_OK;
			}
		}
				
		this.error = error;
		this.particularColor = particularColor;
		cumulativeColor = particularColor;
	}
		
	public final boolean required()
	{
		return requiredType!=null;
	}
	
	public final boolean exists()
	{
		return existingType!=null;
	}
		
	public final String getType()
	{
		if(requiredType!=null)
			return requiredType;
		else
			return existingType;
	}
		
	public final void create()
	{
		//System.out.println("createColumn:"+bf);
		executeSQL(
			driver.getCreateColumnStatement(
				protectName(table.name),
				protectName(name),
				getType()));
	}

	public final void renameTo(final String newName)
	{
		//System.err.println("renameColumn:"+bf);
		executeSQL(
			driver.getRenameColumnStatement(
				protectName(table.name),
				protectName(name),
				protectName(newName),
				existingType));
	}
	
	public final void modify(final String newType)
	{
		executeSQL(
			driver.getModifyColumnStatement(
				protectName(table.name),
				protectName(name),
				newType));
	}

	public final void drop()
	{
		final StringBuffer bf = new StringBuffer();
		bf.append("alter table ").
			append(protectName(table.name)).
			append(" drop column ").
			append(protectName(name));

		//System.out.println("dropColumn:"+bf);
		executeSQL(bf.toString());
	}
	
	public final String toString()
	{
		return name;
	}
	
}
	
