package com.exedio.cope.lib;

import java.util.TreeSet;

import com.exedio.cope.lib.search.Condition;

public class Query
{
	final Type selectType;
	final TreeSet fromTypes = new TreeSet(Type.COMPARATOR);
	final Condition condition;

	Function orderBy = null;
	boolean orderAscending;
	boolean deterministicOrder = false;

	int start = 0;
	int count = -1;
	
	public Query(final Type type, final Condition condition)
	{
		this.selectType = type;
		this.fromTypes.add(type);
		this.condition = condition;
	}
	
	public Query(final Type selectType, final Type fromType2, final Condition condition)
	{
		this.selectType = selectType;
		this.fromTypes.add(selectType);
		this.fromTypes.add(fromType2);
		this.condition = condition;
	}
	
	public Query(final Type selectType, final Type[] fromTypes, final Condition condition)
	{
		this.selectType = selectType;
		for(int i = 0; i<fromTypes.length; i++)
			this.fromTypes.add(fromTypes[i]);
		this.condition = condition;
	}
	
	public void setOrderBy(final Function orderBy, final boolean ascending)
	{
		this.orderBy = orderBy;
		this.orderAscending = ascending;
	}

	public void setDeterministicOrder(final boolean deterministicOrder)
	{
		this.deterministicOrder = deterministicOrder;
	}

	/**
	 * @throws RuntimeException if start < 0
	 */	
	public void setRange(final int start, final int count)
	{
		this.start = start;
		this.count = count;
		if(start<0)
			throw new RuntimeException();
	}
	
	void check()
	{
		if(condition!=null)
			condition.check(fromTypes);
	}

}
