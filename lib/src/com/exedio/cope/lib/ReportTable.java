package com.exedio.cope.lib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;


public final class ReportTable extends ReportNode
{
	public final String name;
	private final Table table;
	private boolean exists = false;
	private ReportLastAnalyzed lastAnalyzed = null;

	private final HashMap columnMap = new HashMap();
	private final ArrayList columnList = new ArrayList();

	private final HashMap constraints = new HashMap();

	ReportTable(final com.exedio.cope.lib.Table table)
	{
		this.name = table.id;
		this.table = table;
		this.exists = false;
	}

	ReportTable(final String name)
	{
		this.name = name;
		this.table = null;
		this.exists = true;
	}
		
	final void setLastAnalyzed(final Date lastAnalyzed)
	{
		if(this.lastAnalyzed!=null)
			throw new RuntimeException();

		this.lastAnalyzed = new ReportLastAnalyzed(lastAnalyzed, this);
	}
	
	final void notifyExists()
	{
		exists = true;
	}
		
	final ReportColumn notifyRequiredColumn(final Column column)
	{
		final ReportColumn result = new ReportColumn(column, this);
		if(columnMap.put(result.name, result)!=null)
			throw new RuntimeException(column.toString());
		columnList.add(result);
		return result;
	}
		
	final ReportColumn notifyExistentColumn(final String columnName, final String existingType)
	{
		ReportColumn result = (ReportColumn)columnMap.get(columnName);
		if(result==null)
		{
			result = new ReportColumn(columnName, existingType, this);
			columnMap.put(columnName, result);
			columnList.add(result);
		}
		else
		{
			result.notifyExists(existingType);
		}

		return result;
	}
	
	final ReportConstraint notifyRequiredConstraint(final String constraintName)
	{
		final ReportConstraint result = new ReportConstraint(constraintName, this);
		if(constraints.put(result.name, result)!=null)
			throw new RuntimeException(constraintName);
		result.notifyRequired();
		return result;
	}
		
	final ReportConstraint notifyExistentConstraint(final String constraintName)
	{
		ReportConstraint result = (ReportConstraint)constraints.get(constraintName);
		if(result==null)
		{
			result = new ReportConstraint(constraintName, this);
			constraints.put(constraintName, result);
		}
		result.notifyExists();
		return result;
	}
	
	public final boolean required()
	{
		return table!=null;
	}
	
	public final boolean exists()
	{
		return exists;
	}
		
	public final ReportLastAnalyzed getLastAnalyzed()
	{
		return lastAnalyzed;
	}
		
	public final Collection getColumns()
	{
		return columnList;
	}
		
	public final ReportColumn getColumn(final String columnName)
	{
		return (ReportColumn)columnMap.get(columnName);
	}
		
	public final Collection getConstraints()
	{
		return constraints.values();
	}
		
	protected void finish()
	{
		if(cumulativeColor!=COLOR_NOT_YET_CALC || particularColor!=COLOR_NOT_YET_CALC)
			throw new RuntimeException();

		if(!exists)
		{
			error = "MISSING !!!";
			particularColor = COLOR_RED;
		}
		else if(table==null)
		{
			error = "not used";
			particularColor = COLOR_YELLOW;
		}
		else
			particularColor = COLOR_OK;
				
		cumulativeColor = particularColor;
			
		if(lastAnalyzed!=null)
		{
			lastAnalyzed.finish();
			cumulativeColor = Math.max(cumulativeColor, lastAnalyzed.cumulativeColor);
		}
			
		for(Iterator i = columnList.iterator(); i.hasNext(); )
		{
			final ReportColumn column = (ReportColumn)i.next();
			column.finish();
			cumulativeColor = Math.max(cumulativeColor, column.cumulativeColor);
		}

		for(Iterator i = constraints.values().iterator(); i.hasNext(); )
		{
			final ReportConstraint constraint = (ReportConstraint)i.next();
			constraint.finish();
			cumulativeColor = Math.max(cumulativeColor, constraint.cumulativeColor);
		}
	}
	
	public final void create()
	{
		Database.theInstance.createTable(table);
	}
	
	public final void renameTo(final String newName)
	{
		Database.theInstance.renameTable(name, newName);
	}

	public final void drop()
	{
		Database.theInstance.dropTable(name);
	}

	public final void analyze()
	{
		Database.theInstance.analyzeTable(name);
	}
	
}
