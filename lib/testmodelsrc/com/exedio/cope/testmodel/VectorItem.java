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
import com.exedio.cope.DateAttribute;
import com.exedio.cope.IntegerAttribute;
import com.exedio.cope.Item;
import com.exedio.cope.ObjectAttribute;
import com.exedio.cope.StringAttribute;
import com.exedio.cope.pattern.Vector;

/**
 * @cope.persistent
 * @author Ralf Wiebicke
 */
public class VectorItem extends Item
{
	// explicit external source

	public static final IntegerAttribute num1 = new IntegerAttribute(OPTIONAL);

	public static final IntegerAttribute num2 = new IntegerAttribute(OPTIONAL);

	public static final IntegerAttribute num3 = new IntegerAttribute(OPTIONAL);
	
	public static final Vector nums = new Vector(new ObjectAttribute[]{num1, num2, num3});

	// implicit external source

	public static final Vector dates = new Vector(new ObjectAttribute[]{new DateAttribute(OPTIONAL), new DateAttribute(OPTIONAL)});

	// internal source

	public static final Vector strings = new Vector(new StringAttribute(OPTIONAL, 1, 11), 4);

	public VectorItem(final int initialNum1, final int initialNum2, final int initialNum3)
	{
		super(new AttributeValue[]{
			new AttributeValue(num1,new Integer(initialNum1)),
			new AttributeValue(num2,new Integer(initialNum2)),
			new AttributeValue(num3,new Integer(initialNum3)),
		});
	}

/**

	 **
	 * Creates a new VectorItem with all the attributes initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <code>@cope.constructor public|package|protected|private|none</code> in the class comment and <code>@cope.initial</code> in the comment of attributes.
	 *
 */public VectorItem()
	{
		this(new com.exedio.cope.AttributeValue[]{
		});
	}/**

	 **
	 * Creates a new VectorItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */private VectorItem(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */private VectorItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #num1}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 *
 */public final java.lang.Integer getNum1()
	{
		return (java.lang.Integer)get(VectorItem.num1);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #num1}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 *
 */public final void setNum1(final java.lang.Integer num1)
	{
		try
		{
			set(VectorItem.num1,num1);
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
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 *
 */public final java.lang.Integer getNum2()
	{
		return (java.lang.Integer)get(VectorItem.num2);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #num2}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 *
 */public final void setNum2(final java.lang.Integer num2)
	{
		try
		{
			set(VectorItem.num2,num2);
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
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</code> in the comment of the attribute.
	 *
 */public final java.lang.Integer getNum3()
	{
		return (java.lang.Integer)get(VectorItem.num3);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #num3}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.setter public|package|protected|private|none|non-final</code> in the comment of the attribute.
	 *
 */public final void setNum3(final java.lang.Integer num3)
	{
		try
		{
			set(VectorItem.num3,num3);
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
	 * Returns the value of the vector.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.util.List getNums()
	{
		return VectorItem.nums.get(this);
	}/**

	 **
	 * Sets the vector.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setNums(final java.util.Collection nums)
			throws
				com.exedio.cope.UniqueViolationException,
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.ReadOnlyViolationException,
				java.lang.ClassCastException
	{
		VectorItem.nums.set(this,nums);
	}/**

	 **
	 * Returns the value of the vector.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.util.List getDates()
	{
		return VectorItem.dates.get(this);
	}/**

	 **
	 * Sets the vector.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setDates(final java.util.Collection dates)
			throws
				com.exedio.cope.UniqueViolationException,
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.ReadOnlyViolationException,
				java.lang.ClassCastException
	{
		VectorItem.dates.set(this,dates);
	}/**

	 **
	 * Returns the value of the vector.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.util.List getStrings()
	{
		return VectorItem.strings.get(this);
	}/**

	 **
	 * Sets the vector.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setStrings(final java.util.Collection strings)
			throws
				com.exedio.cope.UniqueViolationException,
				com.exedio.cope.MandatoryViolationException,
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.ReadOnlyViolationException,
				java.lang.ClassCastException
	{
		VectorItem.strings.set(this,strings);
	}/**

	 **
	 * The persistent type information for vectorItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.type public|package|protected|private|none</code> in the class comment.
	 *
 */public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(VectorItem.class)
;}
