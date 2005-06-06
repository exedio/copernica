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
package com.exedio.cope;

public final class ReportColumn extends ReportNode
{
	public final String name;
	public final ReportTable table;
	private final String requiredType;
	private String existingType;
		
	ReportColumn(final String name, final String type, final boolean required, final ReportTable table)
	{
		if(name==null)
			throw new RuntimeException(type);
		if(type==null)
			throw new RuntimeException(name);
		if(table==null)
			throw new RuntimeException(name);

		this.name = name;
		this.table = table;
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
	}

	void notifyExists(final String existingType)
	{
		if(existingType==null)
			throw new RuntimeException(name);
		if(this.existingType!=null && !this.existingType.equals(existingType))
			throw new RuntimeException(name);

		this.existingType = existingType;
	}

	protected void finish()
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
		table.report.database.createColumn(this);
	}

	public final void renameTo(final String newName)
	{
		table.report.database.renameColumn(table.name, name, newName, existingType);
	}

	public final void modify(final String newType)
	{
		table.report.database.modifyColumn(table.name, name, newType);
	}

	public final void drop()
	{
		table.report.database.dropColumn(table.name, name);
	}

}
	
