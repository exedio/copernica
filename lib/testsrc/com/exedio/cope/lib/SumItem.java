package com.exedio.cope.lib;

import com.exedio.cope.lib.function.SumFunction;

/**
 * @persistent
 */
public class SumItem extends Item
{
	static final IntegerAttribute num1 = new IntegerAttribute(DEFAULT);

	static final IntegerAttribute num2 = new IntegerAttribute(DEFAULT);

	static final IntegerAttribute num3 = new IntegerAttribute(DEFAULT);
	
	static final SumFunction sum12 = new SumFunction(num1, num2);

	static final SumFunction sum13 = new SumFunction(num1, num3);

	static final SumFunction sum23 = new SumFunction(num2, num3);

	SumItem(final int initialNum1, final int initialNum2, final int initialNum3)
	{
		super(new AttributeValue[]{
			new AttributeValue(num1,new Integer(initialNum1)),
			new AttributeValue(num2,new Integer(initialNum2)),
			new AttributeValue(num3,new Integer(initialNum3)),
		});
	}

/**

	 **
	 * Constructs a new SumItem with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public SumItem()
	{
		super(new com.exedio.cope.lib.AttributeValue[]{
		});
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.lib.util.ReactivationConstructorDummy,int)
	 *
 */private SumItem(com.exedio.cope.lib.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #num1}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final Integer getNum1()
	{
		return (Integer)getAttribute(this.num1);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #num1}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final void setNum1(final Integer num1)
	{
		try
		{
			setAttribute(this.num1,num1);
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.UniqueViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #num2}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final Integer getNum2()
	{
		return (Integer)getAttribute(this.num2);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #num2}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final void setNum2(final Integer num2)
	{
		try
		{
			setAttribute(this.num2,num2);
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.UniqueViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #num3}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final Integer getNum3()
	{
		return (Integer)getAttribute(this.num3);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #num3}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final void setNum3(final Integer num3)
	{
		try
		{
			setAttribute(this.num3,num3);
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
		catch(com.exedio.cope.lib.UniqueViolationException e)
		{
			throw new com.exedio.cope.lib.SystemException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #sum12}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final Integer getSum12()
	{
		return (Integer)getAttribute(this.sum12);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #sum13}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final Integer getSum13()
	{
		return (Integer)getAttribute(this.sum13);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #sum23}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final Integer getSum23()
	{
		return (Integer)getAttribute(this.sum23);
	}/**

	 **
	 * The persistent type information for sumItem.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(SumItem.class)
;}
