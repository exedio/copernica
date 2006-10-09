/*
 * Copyright (C) 2004-2006  exedio GmbH (www.exedio.com)
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

package com.exedio.cope.pattern;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.exedio.cope.SetValue;
import com.exedio.cope.IntegerField;
import com.exedio.cope.Item;
import com.exedio.cope.StringField;

/**
 */
public final class CustomItem extends Item
{
	public static final StringField numberString = new StringField(OPTIONAL);
	
	public static final CustomAttribute<Integer> number = new CustomAttribute<Integer>(numberString)
	{
		Integer get(final String numberString)
		{
			return numberString!=null ? Integer.valueOf(Integer.parseInt(numberString)) : null;
		}
		
		String set(final Integer value) throws IOException
		{
			if(value!=null && value.intValue()<0)
				throw new IOException("test exception:"+value);
			
			return value!=null ? String.valueOf(value) : null;
		}
	};
	
	// TODO should be generated by instrumentor
	public Integer getNumber()
	{
		return number.get(this);
	}
	
	// TODO should be generated by instrumentor
	public void setNumber(final Integer value) throws IOException
	{
		try
		{
			number.set(this, value);
		}
		catch(CustomAttributeException e)
		{
			final Throwable cause = e.getCause();
			if(cause instanceof IOException)
			{
				throw (IOException)cause;
			}
			else
			{
				throw new RuntimeException(e);
			}
		}
	}
	
	// TODO should be generated by instrumentor
	public CustomItem(final Integer number) throws IOException
	{
		this(new com.exedio.cope.SetValue[]{
			CustomItem.number.map(number),
		});
	}

	public static final IntegerField element1 = new IntegerField(OPTIONAL);
	public static final IntegerField element2 = new IntegerField(OPTIONAL);
	public static final IntegerField element3 = new IntegerField(OPTIONAL);
	
	public static final CustomAttribute<List<Integer>> elements = new CustomAttribute<List<Integer>>(new IntegerField[]{element1, element2, element3})
	{
		List get(final Integer element1, final Integer element2, final Integer element3)
		{
			final ArrayList<Integer> result = new ArrayList<Integer>(3);
			result.add(element1);
			result.add(element2);
			result.add(element3);
			return result;
		}
		
		SetValue[] set(final List value)
		{
			final Iterator i = value.iterator();
			
			return new SetValue[]{
					element1.map((Integer)i.next()),
					element2.map((Integer)i.next()),
					element3.map((Integer)i.next()),
			};
		}
	};
	
	// TODO should be generated by instrumentor
	public List<Integer> getElements()
	{
		return elements.get(this);
	}
	
	// TODO should be generated by instrumentor
	public void setElements(final List<Integer> value)
	{
		try
		{
			elements.set(this, value);
		}
		catch(CustomAttributeException e)
		{
			throw new RuntimeException(e);
		}
	}

	public CustomItem(final List<Integer> elements)
	{
		this(new com.exedio.cope.SetValue[]{
			CustomItem.elements.map(elements),
		});
	}

/**

	 **
	 * Creates a new CustomItem with all the fields initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	public CustomItem()
	{
		this(new com.exedio.cope.SetValue[]{
		});
	}/**

	 **
	 * Creates a new CustomItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	private CustomItem(final com.exedio.cope.SetValue[] setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private CustomItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent field {@link #numberString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	public final java.lang.String getNumberString()
	{
		return CustomItem.numberString.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #numberString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setNumberString(final java.lang.String numberString)
			throws
				com.exedio.cope.LengthViolationException
	{
		CustomItem.numberString.set(this,numberString);
	}/**

	 **
	 * Returns the value of the persistent field {@link #element1}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	public final java.lang.Integer getElement1()
	{
		return CustomItem.element1.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #element1}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setElement1(final java.lang.Integer element1)
	{
		CustomItem.element1.set(this,element1);
	}/**

	 **
	 * Returns the value of the persistent field {@link #element2}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	public final java.lang.Integer getElement2()
	{
		return CustomItem.element2.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #element2}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setElement2(final java.lang.Integer element2)
	{
		CustomItem.element2.set(this,element2);
	}/**

	 **
	 * Returns the value of the persistent field {@link #element3}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	public final java.lang.Integer getElement3()
	{
		return CustomItem.element3.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #element3}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	public final void setElement3(final java.lang.Integer element3)
	{
		CustomItem.element3.set(this,element3);
	}/**

	 **
	 * The persistent type information for customItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<CustomItem> TYPE = newType(CustomItem.class)
;}
