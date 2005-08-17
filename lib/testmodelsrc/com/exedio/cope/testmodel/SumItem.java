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

package com.exedio.cope.testmodel;

import com.exedio.cope.AttributeValue;
import com.exedio.cope.IntegerAttribute;
import com.exedio.cope.Item;
import com.exedio.cope.function.SumFunction;

/**
 * @cope.persistent
 * @author Ralf Wiebicke
 */
public class SumItem extends Item
{
	public static final IntegerAttribute num1 = integerAttribute(OPTIONAL);

	public static final IntegerAttribute num2 = integerAttribute(OPTIONAL);

	public static final IntegerAttribute num3 = integerAttribute(OPTIONAL);
	
	public static final SumFunction sum12 = num1.sum(num2);

	public static final SumFunction sum13 = num1.sum(num3);

	public static final SumFunction sum23 = num2.sum(num3);

	public static final SumFunction sum123 = sum(num1, num2, num3);

	public static final SumFunction sum12a3 = sum12.sum(num3);
	
	public SumItem(final int initialNum1, final int initialNum2, final int initialNum3)
	{
		super(new AttributeValue[]{
			new AttributeValue(num1,new Integer(initialNum1)),
			new AttributeValue(num2,new Integer(initialNum2)),
			new AttributeValue(num3,new Integer(initialNum3)),
		});
	}

/**

	 **
	 * Creates a new SumItem with all the attributes initially needed.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public SumItem()
	{
		this(new com.exedio.cope.AttributeValue[]{
		});
	}/**

	 **
	 * Creates a new SumItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */private SumItem(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 *
 */private SumItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #num1}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.Integer getNum1()
	{
		return (java.lang.Integer)get(SumItem.num1);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #num1}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setNum1(final java.lang.Integer num1)
	{
		try
		{
			set(SumItem.num1,num1);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.MandatoryViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #num2}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.Integer getNum2()
	{
		return (java.lang.Integer)get(SumItem.num2);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #num2}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setNum2(final java.lang.Integer num2)
	{
		try
		{
			set(SumItem.num2,num2);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.MandatoryViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #num3}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.Integer getNum3()
	{
		return (java.lang.Integer)get(SumItem.num3);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #num3}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setNum3(final java.lang.Integer num3)
	{
		try
		{
			set(SumItem.num3,num3);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.MandatoryViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.UniqueViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #sum12}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.Integer getSum12()
	{
		return (java.lang.Integer)get(SumItem.sum12);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #sum13}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.Integer getSum13()
	{
		return (java.lang.Integer)get(SumItem.sum13);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #sum23}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.Integer getSum23()
	{
		return (java.lang.Integer)get(SumItem.sum23);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #sum123}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.Integer getSum123()
	{
		return (java.lang.Integer)get(SumItem.sum123);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #sum12a3}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.Integer getSum12a3()
	{
		return (java.lang.Integer)get(SumItem.sum12a3);
	}/**

	 **
	 * The persistent type information for sumItem.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(SumItem.class)
;}
