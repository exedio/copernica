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

package com.exedio.cope.pattern;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.exedio.cope.AttributeValue;
import com.exedio.cope.IntegerAttribute;
import com.exedio.cope.Item;
import com.exedio.cope.StringAttribute;

/**
 * @cope.persistent
 */
public final class CustomItem extends Item
{
	public static final StringAttribute numberString = new StringAttribute(OPTIONAL);
	
	public static final CustomAttribute number = new CustomAttribute(numberString)
	{
		Integer get(final String numberString)
		{
			return numberString!=null ? new Integer(Integer.parseInt(numberString)) : null;
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
		return (Integer)number.get(this);
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
	
	public CustomItem(final Integer number)
	{
		this(new com.exedio.cope.AttributeValue[]{
			CustomItem.number.map(number),
		});
	}

	public static final IntegerAttribute element1 = new IntegerAttribute(OPTIONAL);
	public static final IntegerAttribute element2 = new IntegerAttribute(OPTIONAL);
	public static final IntegerAttribute element3 = new IntegerAttribute(OPTIONAL);
	
	public static final CustomAttribute elements = new CustomAttribute(new IntegerAttribute[]{element1, element2, element3})
	{
		List get(final Integer element1, final Integer element2, final Integer element3)
		{
			final ArrayList result = new ArrayList(3);
			result.add(element1);
			result.add(element2);
			result.add(element3);
			return result;
		}
		
		AttributeValue[] set(final List value)
		{
			final Iterator i = ((List)value).iterator();
			
			return new AttributeValue[]{
					element1.map((Integer)i.next()),
					element2.map((Integer)i.next()),
					element3.map((Integer)i.next()),
			};
		}
	};
	
	// TODO should be generated by instrumentor
	public List getElements()
	{
		return (List)elements.get(this);
	}
	
	// TODO should be generated by instrumentor
	public void setElements(final List value)
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

	public CustomItem(final List elements)
	{
		this(new com.exedio.cope.AttributeValue[]{
			CustomItem.elements.map(elements),
		});
	}

/**

	 **
	 * Creates a new CustomItem with all the attributes initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <code>@cope.constructor public|package|protected|private|none</code> in the class comment and <code>@cope.initial</code> in the comment of attributes.
	 */
	public CustomItem()
	{
		this(new com.exedio.cope.AttributeValue[]{
		});
	}/**

	 **
	 * Creates a new CustomItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.generic.constructor public|package|protected|private|none</code> in the class comment.
	 */
	private CustomItem(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private CustomItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #numberString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.String getNumberString()
	{
		return CustomItem.numberString.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #numberString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setNumberString(final java.lang.String numberString)
	{
		try
		{
			CustomItem.numberString.set(this,numberString);
		}
		catch(com.exedio.cope.FinalViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.MandatoryViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #element1}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.Integer getElement1()
	{
		return CustomItem.element1.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #element1}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setElement1(final java.lang.Integer element1)
	{
		try
		{
			CustomItem.element1.set(this,element1);
		}
		catch(com.exedio.cope.FinalViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.MandatoryViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #element2}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.Integer getElement2()
	{
		return CustomItem.element2.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #element2}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setElement2(final java.lang.Integer element2)
	{
		try
		{
			CustomItem.element2.set(this,element2);
		}
		catch(com.exedio.cope.FinalViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.MandatoryViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #element3}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 */
	public final java.lang.Integer getElement3()
	{
		return CustomItem.element3.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #element3}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 */
	public final void setElement3(final java.lang.Integer element3)
	{
		try
		{
			CustomItem.element3.set(this,element3);
		}
		catch(com.exedio.cope.FinalViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.MandatoryViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new java.lang.RuntimeException(e);
		}
	}/**

	 **
	 * The persistent type information for customItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.type public|package|protected|private|none</code> in the class comment.
	 */
	public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(CustomItem.class)
;}
