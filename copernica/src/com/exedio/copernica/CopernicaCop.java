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
package com.exedio.copernica;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.exedio.cope.EnumValue;
import com.exedio.cope.Item;
import com.exedio.cope.Model;
import com.exedio.cope.Type;
import com.exedio.cops.Cop;


abstract class CopernicaCop extends Cop implements RequestCache
{
	static final String LANGUAGE = "l";

	static final String TYPE = "t";
	static final String ORDER_ASCENDING = "oa";
	static final String ORDER_DESCENDING = "od";
	static final String START = "st";
	static final String COUNT = "ct";
	
	final static String ITEM = "i";


	final CopernicaProvider provider;
	final CopernicaLanguage language;

	CopernicaCop(final CopernicaProvider provider, final CopernicaLanguage language)
	{
		super("copernica.jsp"); // TODO: make the jsp name flexible or retrieve it automatically
		this.language = language;
		this.provider = provider;
		if(language!=null)
			addParameter(LANGUAGE, language.getCopernicaID());
	}
	
	void init(final HttpServletRequest request)
	{
	}
	
	abstract CopernicaCop switchLanguage(CopernicaLanguage newLanguage);
	abstract boolean isType(final Type type);
	abstract String getTitle();

	final EmptyCop toHome()
	{
		return new EmptyCop(provider, language);
	}
	
	CopernicaCop toPrev()
	{
		return null;
	}
	
	CopernicaCop toNext()
	{
		return null;
	}
	
	final TypeCop toType(final Type newType)
	{
		return new TypeCop(provider, language, newType);
	}
	
	final ItemCop toItem(final Item newItem)
	{
		return new ItemCop(provider, language, newItem);
	}
	
	static final CopernicaCop getCop(final CopernicaProvider provider, final HttpServletRequest request)
	{	
		final Model model = provider.getModel();
		final String typeID = request.getParameter(TYPE);
		final String itemID = request.getParameter(ITEM);
		final String langID = request.getParameter(LANGUAGE);
		
		final CopernicaLanguage language = (langID!=null) ? provider.findLanguageByID(langID) : null;
		if(typeID!=null)
		{
			return TypeCop.getCop(provider, language, typeID, request);
		}
		else if(itemID!=null)
		{
			return ItemCop.getCop(provider, language, itemID);
		}
		else
			return new EmptyCop(provider, language);
	}
	
	private String nullName = null;
	//private int nullNameMisses = 0;
	public String getDisplayNameNull()
	{
		if(nullName==null)
		{
			//nullNameMisses++;
			nullName = provider.getDisplayNameNull(language);
		}
		
		return nullName;
	}

	private String onName = null;
	//private int onNameMisses = 0;
	public String getDisplayNameOn()
	{
		if(onName==null)
		{
			//onNameMisses++;
			onName = provider.getDisplayNameOn(language);
		}
		
		return onName;
	}
	
	private String offName = null;
	//private int offNameMisses = 0;
	public String getDisplayNameOff()
	{
		if(offName==null)
		{
			//offNameMisses++;
			offName = provider.getDisplayNameOff(language);
		}
		
		return offName;
	}

	private HashMap itemDisplayNames = null;
	//private int itemDisplayNamesHits = 0;
	//private int itemDisplayNamesMisses = 0;
	
	public String getDisplayName(final CopernicaLanguage displayLanguage, final Item item)
	{
		if((language==null&&displayLanguage==null) || (language!=null&&language.equals(displayLanguage)))
		{
			if(itemDisplayNames==null)
			{
				itemDisplayNames = new HashMap();
			}
			else
			{
				final String cachedResult = (String)itemDisplayNames.get(item);
				if(cachedResult!=null)
				{
					//itemDisplayNamesHits++;
					return cachedResult;
				}
			}

			//itemDisplayNamesMisses++;
			final String result = provider.getDisplayName(this, displayLanguage, item);
			itemDisplayNames.put(item, result);
			return result;
		}
		else
		{
			//itemDisplayNamesMisses++;
			return provider.getDisplayName(this, displayLanguage, item);
		}
	}
	
	private HashMap enumDisplayNames = null;
	//private int enumDisplayNamesHits = 0;
	//private int enumDisplayNamesMisses = 0;
	
	public String getDisplayName(final EnumValue enumerationValue)
	{
		if(enumDisplayNames==null)
		{
			enumDisplayNames = new HashMap();
		}
		else
		{
			final String cachedResult = (String)enumDisplayNames.get(enumerationValue);
			if(cachedResult!=null)
			{
				//enumDisplayNamesHits++;
				return cachedResult;
			}
		}

		//enumDisplayNamesMisses++;
		final String result = provider.getDisplayName(language, enumerationValue);
		enumDisplayNames.put(enumerationValue, result);
		return result;
	}
	
	void log()
	{
		//System.out.println("itemDisplayNames: ("+itemDisplayNamesMisses+"/"+itemDisplayNamesHits+")");
		//System.out.println("enumDisplayNames: ("+enumDisplayNamesMisses+"/"+enumDisplayNamesHits+")");
		//System.out.println("nullNameMisses: "+nullNameMisses+", onNameMisses: "+onNameMisses+", offNameMisses: "+offNameMisses);
	}
	
	abstract void writeBody(PrintStream out) throws IOException;
	
}
