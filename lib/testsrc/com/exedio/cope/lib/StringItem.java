
package com.exedio.cope.lib;

/**
 * @persistent
 * @cope-constructor package
 */
public class StringItem extends Item
{
	static final StringAttribute any = new StringAttribute(DEFAULT);

	static final StringAttribute min4 = new StringAttribute(DEFAULT, 4);

	static final StringAttribute max4 = new StringAttribute(DEFAULT, 0, 4);

	static final StringAttribute min4Max8 = new StringAttribute(DEFAULT, 4, 8);

/**

	 **
	 * Constructs a new StringItem with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */StringItem()
	{
		super(new com.exedio.cope.lib.AttributeValue[]{
		});
	}/**

	 **
	 * Creates an item and sets the given attributes initially.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */protected StringItem(final com.exedio.cope.lib.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.lib.util.ReactivationConstructorDummy,int)
	 *
 */private StringItem(com.exedio.cope.lib.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #any}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final java.lang.String getAny()
	{
		return (java.lang.String)getAttribute(StringItem.any);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #any}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final void setAny(final java.lang.String any)
	{
		try
		{
			setAttribute(StringItem.any,any);
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.lib.UniqueViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #min4}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final java.lang.String getMin4()
	{
		return (java.lang.String)getAttribute(StringItem.min4);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #min4}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final void setMin4(final java.lang.String min4)
			throws
				com.exedio.cope.lib.LengthViolationException
	{
		try
		{
			setAttribute(StringItem.min4,min4);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.lib.UniqueViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #max4}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final java.lang.String getMax4()
	{
		return (java.lang.String)getAttribute(StringItem.max4);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #max4}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final void setMax4(final java.lang.String max4)
			throws
				com.exedio.cope.lib.LengthViolationException
	{
		try
		{
			setAttribute(StringItem.max4,max4);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.lib.UniqueViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #min4Max8}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final java.lang.String getMin4Max8()
	{
		return (java.lang.String)getAttribute(StringItem.min4Max8);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #min4Max8}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final void setMin4Max8(final java.lang.String min4Max8)
			throws
				com.exedio.cope.lib.LengthViolationException
	{
		try
		{
			setAttribute(StringItem.min4Max8,min4Max8);
		}
		catch(com.exedio.cope.lib.NotNullViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.lib.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.lib.UniqueViolationException e)
		{
			throw new com.exedio.cope.lib.NestingRuntimeException(e);
		}
	}/**

	 **
	 * The persistent type information for stringItem.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(StringItem.class)
;}
