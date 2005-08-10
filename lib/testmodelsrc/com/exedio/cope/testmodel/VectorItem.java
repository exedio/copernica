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
import com.exedio.cope.ObjectAttribute;
import com.exedio.cope.pattern.Vector;

/**
 * @cope.persistent
 * @author Ralf Wiebicke
 */
public class VectorItem extends Item
{
	// explicit external source

	public static final IntegerAttribute num1 = integerAttribute(DEFAULT);

	public static final IntegerAttribute num2 = integerAttribute(DEFAULT);

	public static final IntegerAttribute num3 = integerAttribute(DEFAULT);
	
	public static final Vector nums = new Vector(new ObjectAttribute[]{num1, num2, num3});

	// implicit external source TODO

	// internal source

	public static final Vector strings = new Vector(stringAttribute(DEFAULT), 4);

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
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public VectorItem()
	{
		this(new com.exedio.cope.AttributeValue[]{
		});
	}/**

	 **
	 * Creates a new VectorItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */private VectorItem(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 *
 */private VectorItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #num1}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.Integer getNum1()
	{
		return (java.lang.Integer)get(VectorItem.num1);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #num1}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
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
		catch(com.exedio.cope.NotNullViolationException e)
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
		return (java.lang.Integer)get(VectorItem.num2);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #num2}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
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
		catch(com.exedio.cope.NotNullViolationException e)
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
		return (java.lang.Integer)get(VectorItem.num3);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #num3}.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
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
		catch(com.exedio.cope.NotNullViolationException e)
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
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.util.List getNums()
	{
		return VectorItem.nums.get(this);
	}/**

	 **
	 * Sets the vector.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setNums(final java.util.Collection nums)
			throws
				com.exedio.cope.UniqueViolationException,
				com.exedio.cope.NotNullViolationException,
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.ReadOnlyViolationException,
				java.lang.ClassCastException
	{
		VectorItem.nums.set(this,nums);
	}/**

	 **
	 * Returns the value of the vector.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.util.List getStrings()
	{
		return VectorItem.strings.get(this);
	}/**

	 **
	 * Sets the vector.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setStrings(final java.util.Collection strings)
			throws
				com.exedio.cope.UniqueViolationException,
				com.exedio.cope.NotNullViolationException,
				com.exedio.cope.LengthViolationException,
				com.exedio.cope.ReadOnlyViolationException,
				java.lang.ClassCastException
	{
		VectorItem.strings.set(this,strings);
	}/**

	 **
	 * The persistent type information for vectorItem.
	 * @cope.generated This method has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public static final com.exedio.cope.Type TYPE = 
		new com.exedio.cope.Type(VectorItem.class)
;}
