
package com.exedio.cope.lib;

/**
 * @persistent
 */
public class PointerItem2 extends Item
{

	static final StringAttribute code = new StringAttribute(NOT_NULL);

/**

	 **
	 * Constructs a new PointerItem2 with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param initialCode the initial value for attribute {@link #code}.
	 * @throws com.exedio.cope.lib.NotNullViolationException if initialCode is null.
	 *
 */PointerItem2(
				final java.lang.String initialCode)
			throws
				com.exedio.cope.lib.NotNullViolationException
	{
		super(new com.exedio.cope.lib.AttributeValue[]{
			new com.exedio.cope.lib.AttributeValue(code,initialCode),
		});
		throwInitialNotNullViolationException();
	}/**

	 **
	 * Creates an item and sets the given attributes initially.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */protected PointerItem2(final com.exedio.cope.lib.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.lib.util.ReactivationConstructorDummy,int)
	 *
 */private PointerItem2(com.exedio.cope.lib.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #code}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final java.lang.String getCode()
	{
		return (java.lang.String)getAttribute(PointerItem2.code);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #code}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final void setCode(final java.lang.String code)
			throws
				com.exedio.cope.lib.NotNullViolationException
	{
		try
		{
			setAttribute(PointerItem2.code,code);
		}
		catch(com.exedio.cope.lib.LengthViolationException e)
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
	 * The persistent type information for pointerItem2.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(PointerItem2.class)
;}
