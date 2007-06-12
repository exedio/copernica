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

import javax.servlet.http.HttpServletRequest;

import com.exedio.cope.Model;
import com.exedio.cope.util.CacheQueryInfo;

final class QueryCacheCop extends ConsoleCop
{
	static final String HISTOGRAM_LIMIT = "hl";
	private static final int HISTOGRAM_LIMIT_DEFAULT = 100;
	
	final int histogramLimit;

	QueryCacheCop()
	{
		this(HISTOGRAM_LIMIT_DEFAULT);
	}
	
	private QueryCacheCop(final int histogramLimit)
	{
		super("query cache");
		addParameter(TAB, TAB_QUERY_CACHE);
		if(histogramLimit!=HISTOGRAM_LIMIT_DEFAULT)
			addParameter(HISTOGRAM_LIMIT, String.valueOf(histogramLimit));
		
		this.histogramLimit = histogramLimit;
	}

	static QueryCacheCop getQueryCacheCop(final HttpServletRequest request)
	{
		final String hl = request.getParameter(HISTOGRAM_LIMIT);
		return new QueryCacheCop(hl!=null ? Integer.valueOf(hl) : HISTOGRAM_LIMIT_DEFAULT);
	}
	
	static final class Content
	{
		final CacheQueryInfo[] histogram;
		
		final int avgKeyLength;
		final int maxKeyLength;
		final int minKeyLength;
		
		final int avgResultSize;
		final int maxResultSize;
		final int minResultSize;
		final int[] resultSizes;
		
		Content(final CacheQueryInfo[] histogram)
		{
			if(histogram.length>0)
			{
				int sumKeyLength = 0;
				int maxKeyLength = 0;
				int minKeyLength = Integer.MAX_VALUE;
				
				int sumResultSize = 0;
				int maxResultSize = 0;
				int minResultSize = Integer.MAX_VALUE;
				int[] resultSizes = new int[5];
				
				for(final CacheQueryInfo info : histogram)
				{
					final int keyLength = info.getQuery().length();
					sumKeyLength += keyLength;
					if(keyLength<minKeyLength)
						minKeyLength = keyLength;
					if(keyLength>maxKeyLength)
						maxKeyLength = keyLength;
		
					final int resultSize = info.getResultSize();
					sumResultSize += resultSize;
					if(resultSize<minResultSize)
						minResultSize = resultSize;
					if(resultSize>maxResultSize)
						maxResultSize = resultSize;
					if(resultSize<resultSizes.length)
						resultSizes[resultSize]++;
				}
				
				this.histogram = histogram;
				
				this.avgKeyLength = sumKeyLength / histogram.length;
				this.maxKeyLength = maxKeyLength;
				this.minKeyLength = minKeyLength;
				
				this.avgResultSize = sumResultSize / histogram.length;
				this.maxResultSize = maxResultSize;
				this.minResultSize = minResultSize;
				this.resultSizes = resultSizes;
			}
			else
			{
				this.histogram = histogram;
				
				this.avgKeyLength = -1;
				this.maxKeyLength = -1;
				this.minKeyLength = -1;
				
				this.avgResultSize = -1;
				this.maxResultSize = -1;
				this.minResultSize = -1;
				this.resultSizes = new int[0];
			}
		}
	}
		
	@Override
	final void writeBody(final PrintStream out, final Model model, final HttpServletRequest request)
	{
		final CacheQueryInfo[] histogram = model.getQueryCacheHistogram();
		QueryCache_Jspm.writeBody(this, out,
				model.getProperties().getQueryCacheLimit(),
				model.getQueryCacheInfo(),
				new Content(histogram),
				model.getProperties().getQueryCacheHistogram());
	}
}
