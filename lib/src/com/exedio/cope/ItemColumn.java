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

final class ItemColumn extends IntegerColumn
{
	static final int SYNTETIC_PRIMARY_KEY_PRECISION = 10;

	final Class targetTypeClass;
	final String integrityConstraintName;
	final ItemAttribute attribute;

	ItemColumn(final Table table, final String id,
					  final boolean notNull,
					  final Class targetTypeClass, final ItemAttribute attribute)
	{
		super(table, id, notNull, SYNTETIC_PRIMARY_KEY_PRECISION, false, null);
		if(targetTypeClass==null)
			throw new RuntimeException();
		this.targetTypeClass = targetTypeClass;
		this.integrityConstraintName = table.database.trimName(table.id+"_"+id+"_Fk").intern();
		this.attribute = attribute;
		table.database.addIntegrityConstraint(this);
	}

	/**
	 * Creates a primary key column with a foreign key contraint.
	 */	
	ItemColumn(final Table table, final Class targetTypeClass)
	{
		super(table);
		if(targetTypeClass==null)
			throw new RuntimeException();
		this.targetTypeClass = targetTypeClass;
		this.integrityConstraintName = table.id+"_Sup";
		this.attribute = null;
	}

	String getForeignTableNameProtected()
	{
		if(targetTypeClass!=null)
			return Type.findByJavaClass(targetTypeClass).getTable().protectedID;
		else
			return null; 
	}
	
	String getForeignTablePkNameProtected()
	{
		if(targetTypeClass!=null)
			return Type.findByJavaClass(targetTypeClass).getTable().getPrimaryKey().protectedID;
		else
			return null; 
	}
	
	void report(final ReportTable reportTable)
	{
		super.report(reportTable);
		new ReportConstraint(reportTable, integrityConstraintName, ReportConstraint.TYPE_FOREIGN_KEY, true);
	}
		
}
