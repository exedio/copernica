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


public class ReportForeignKeyConstraint extends ReportConstraint
{
	final String foreignKeyColumn;
	final String targetTable;
	final String targetColumn;
	
	ReportForeignKeyConstraint(final ReportTable table, final String name, final boolean required, final String foreignKeyColumn, final String targetTable, final String targetColumn)
	{
		super(table, name, TYPE_FOREIGN_KEY, required, null);
		
		if(required && foreignKeyColumn==null)
			throw new RuntimeException(name);
		if(required && targetTable==null)
			throw new RuntimeException(name);
		if(required && targetColumn==null)
			throw new RuntimeException(name);

		this.foreignKeyColumn = foreignKeyColumn;
		this.targetTable = targetTable;
		this.targetColumn = targetColumn;
		//System.out.println("-------------"+name+"-"+foreignKeyColumn+"-"+targetTable+"-"+targetColumn);
	}
	
	final void create()
	{
		final StringBuffer bf = new StringBuffer();
		bf.append("alter table ").
			append(protectName(table.name)).
			append(" add constraint ").
			append(protectName(name)).
			append(" foreign key (").
			append(protectName(foreignKeyColumn)).
			append(") references ").
			append(protectName(targetTable));

		if(database.mysql)
		{
			bf.append('(').
				append(protectName(targetColumn)).
				append(')');
		}

		//System.out.println("createForeignKeyConstraints:"+bf);
		executeSQL(bf.toString());
	}

}
