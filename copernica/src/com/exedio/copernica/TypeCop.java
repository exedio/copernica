package com.exedio.copernica;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import com.exedio.cope.lib.Function;
import com.exedio.cope.lib.Query;
import com.exedio.cope.lib.StatementInfo;
import com.exedio.cope.lib.Type;

final class TypeCop extends CopernicaCop
{
	private static final int COUNT_CEILING = 500;
	
	final Type type;
	final Function orderBy; 
	final boolean orderAscending;
	final int start;
	final int count;

	private boolean lastPage;
	private Collection items = null;
	private StatementInfo statementInfo;

	TypeCop(final CopernicaProvider provider, final CopernicaLanguage language, final Type type)
	{
		this(provider, language, type, null, true, 0, 10);
	}
	
	TypeCop(final CopernicaProvider provider, final CopernicaLanguage language, final Type type,
					final Function orderBy, final boolean orderAscending,
					final int start, int count)
	{
		super(provider, language);
		
		if(count>COUNT_CEILING)
			count = COUNT_CEILING;

		this.type = type;
		this.orderBy = orderBy;
		this.orderAscending = orderAscending;
		this.start = start;
		this.count = count;

		addParameter(TYPE, type.getID());
		if(orderBy!=null)
			addParameter(orderAscending ? ORDER_ASCENDING : ORDER_DESCENDING, orderBy.getName());
		if(start!=0)
			addParameter(START, String.valueOf(start));
		if(count!=10)
			addParameter(COUNT, String.valueOf(count));
	}
	
	final CopernicaCop switchLanguage(final CopernicaLanguage newLanguage)
	{
		return new TypeCop(provider, newLanguage, type, orderBy, orderAscending, start, count);
	}
	
	final boolean isType(final Type type)
	{
		return this.type == type;
	}

	final String getTitle()
	{
		return provider.getDisplayName(language, type);
	}

	final CopernicaCop toPrev()
	{
		return start==0 ? null : previousPage();
	}
	
	final CopernicaCop toNext()
	{
		computeItems();
		return lastPage ? null : nextPage();
	}
	
	final boolean isFirstPage()
	{
		return start == 0;
	}
	
	final boolean isLastPage()
	{
		computeItems();
		return lastPage;
	}
	
	final TypeCop firstPage()
	{
		return new TypeCop(provider, language, type, orderBy, orderAscending, 0, count);
	}
	
	final TypeCop previousPage()
	{
		int newStart = start - count;
		if(newStart<0)
			newStart = 0;
		return new TypeCop(provider, language, type, orderBy, orderAscending, newStart, count);
	}
	
	final TypeCop nextPage()
	{
		int newStart = start + count;
		return new TypeCop(provider, language, type, orderBy, orderAscending, newStart, count);
	}
	
	final TypeCop switchCount(final int newCount)
	{
		return new TypeCop(provider, language, type, orderBy, orderAscending, start, newCount);
	}
	
	final TypeCop orderBy(final Function newOrderBy, final boolean ascending) 
	{
		return new TypeCop(provider, language, type, newOrderBy, ascending, start, count);
	}
	
	final Collection getItems()
	{
		computeItems();
		return items;
	}

	final StatementInfo getStatementInfo()
	{
		computeItems();
		return statementInfo;
	}

	private final void computeItems()
	{
		if(items!=null)
			return;
		
		final Query query = new Query(type, null);
		if(orderBy!=null)
			query.setOrderBy(orderBy, orderAscending);
		query.setDeterministicOrder(true);
		query.setRange(start, count);
		query.enableMakeStatementInfo();
		
		items = query.search();
		lastPage = count>items.size();
		statementInfo = query.getStatementInfo();
	}
	
	static final TypeCop getCop(
			final CopernicaProvider provider,
			final CopernicaLanguage language,
			final String typeID,
			final HttpServletRequest request)
	{
		final Type type = provider.getModel().findTypeByID(typeID);
		if(type==null)
			throw new RuntimeException("type "+typeID+" not available");

		final String orderAscendingID = request.getParameter(ORDER_ASCENDING);
		final String orderDescendingID = request.getParameter(ORDER_DESCENDING);
		final boolean orderAscending = orderAscendingID!=null;
		final String orderID = orderAscending ? orderAscendingID : orderDescendingID;
		final Function orderBy = (orderID==null) ? null : (Function)type.getFeature(orderID);

		final String startString = request.getParameter(START);
		final String countString = request.getParameter(COUNT);
		final int start = (startString==null) ?  0 : Integer.parseInt(startString);
		final int count = (countString==null) ? 10 : Integer.parseInt(countString);

		return new TypeCop(provider, language, type, orderBy, orderAscending, start, count);
	}
}
