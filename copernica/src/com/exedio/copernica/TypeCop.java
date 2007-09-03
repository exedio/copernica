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

package com.exedio.copernica;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.exedio.cope.Feature;
import com.exedio.cope.Function;
import com.exedio.cope.Query;
import com.exedio.cope.QueryInfo;
import com.exedio.cope.Transaction;
import com.exedio.cope.Type;

final class TypeCop extends CopernicaCop
{
	private static final String ORDER_ASCENDING = "oa";
	private static final String ORDER_DESCENDING = "od";
	private static final String OFFSET = "st"; // TODO change value to "off"
	private static final String LIMIT  = "ct"; // TODO change value to "lim"

	private static final int OFFSET_DEFAULT = 0;
	private static final int LIMIT_DEFAULT  = 10;

	final Type type;
	final Function orderBy;
	final boolean orderAscending;
	final int offset;
	final int limit;

	private Query.Result queryResult = null;
	private List<QueryInfo> queryInfos;

	TypeCop(final CopernicaProvider provider, final CopernicaLanguage language, final Type type)
	{
		this(provider, language, type, null, true, OFFSET_DEFAULT, LIMIT_DEFAULT);
	}
	
	TypeCop(final CopernicaProvider provider, final CopernicaLanguage language, final Type type,
					final Function orderBy, final boolean orderAscending,
					final int offset, int limit)
	{
		super("type", provider, language);
		
		final int limitCeiling = provider.getLimitCeiling(type);
		if(limit>limitCeiling)
			limit = limitCeiling;

		this.type = type;
		this.orderBy = orderBy;
		this.orderAscending = orderAscending;
		this.offset = offset;
		this.limit = limit;

		addParameter(TYPE, type.getID());
		// orderBy must be a feature
		if(orderBy!=null)
			addParameter(orderAscending ? ORDER_ASCENDING : ORDER_DESCENDING, ((Feature)orderBy).getName());
		if(offset!=OFFSET_DEFAULT)
			addParameter(OFFSET, String.valueOf(offset));
		if(limit!=LIMIT_DEFAULT)
			addParameter(LIMIT, String.valueOf(limit));
	}
	
	@Override
	final CopernicaCop switchLanguage(final CopernicaLanguage newLanguage)
	{
		return new TypeCop(provider, newLanguage, type, orderBy, orderAscending, offset, limit);
	}
	
	@Override
	final boolean isType(final Type type)
	{
		return this.type == type;
	}

	@Override
	final String getTitle()
	{
		return provider.getDisplayName(language, type);
	}

	@Override
	final CopernicaCop toPrev()
	{
		return offset==0 ? null : previousPage();
	}
	
	@Override
	final CopernicaCop toNext()
	{
		return isLastPage() ? null : nextPage();
	}
	
	final boolean isFirstPage()
	{
		return offset == 0;
	}
	
	final boolean isLastPage()
	{
		return (offset+limit)>=queryResult.getCountWithoutLimit();
	}
	
	final TypeCop firstPage()
	{
		return new TypeCop(provider, language, type, orderBy, orderAscending, 0, limit);
	}
	
	final TypeCop lastPage()
	{
		return new TypeCop(provider, language, type, orderBy, orderAscending, ((queryResult.getCountWithoutLimit()-1)/limit)*limit, limit);
	}
	
	final TypeCop previousPage()
	{
		int newOffset = offset - limit;
		if(newOffset<0)
			newOffset = 0;
		return new TypeCop(provider, language, type, orderBy, orderAscending, newOffset, limit);
	}
	
	final TypeCop nextPage()
	{
		int newOffset = offset + limit;
		return new TypeCop(provider, language, type, orderBy, orderAscending, newOffset, limit);
	}
	
	final TypeCop switchLimit(final int newLimit)
	{
		return new TypeCop(provider, language, type, orderBy, orderAscending, offset, newLimit);
	}
	
	final TypeCop orderBy(final Function newOrderBy, final boolean ascending)
	{
		return new TypeCop(provider, language, type, newOrderBy, ascending, offset, limit);
	}
	
	final List getItems()
	{
		return queryResult.getData();
	}

	final int getTotal()
	{
		return queryResult.getCountWithoutLimit();
	}

	final List<QueryInfo> getQueryInfos()
	{
		return queryInfos;
	}

	@Override
	void init(final HttpServletRequest request)
	{
		super.init(request);
		assert queryResult==null;
		
		final Query query = type.newQuery(null);
		if(orderBy!=null)
			query.setOrderByAndThis(orderBy, orderAscending);
		else
			query.setOrderByThis(true);
		query.setLimit(offset, limit);
		
		final Transaction transaction = type.getModel().getCurrentTransaction();
		transaction.setQueryInfoEnabled(true);

		queryResult = query.searchAndCountWithoutLimit();
		
		queryInfos = transaction.getQueryInfos();
		transaction.setQueryInfoEnabled(false);
	}
	
	@Override
	void writeBody(final PrintStream out)
		throws IOException
	{
		TypeCop_Jspm.writeBody(out, this);
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

		return new TypeCop(
				provider, language, type,
				orderBy, orderAscending,
				getIntParameter(request, OFFSET, OFFSET_DEFAULT),
				getIntParameter(request, LIMIT,  LIMIT_DEFAULT));
	}
}
