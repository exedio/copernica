
package com.exedio.cope.lib;

/**
 * @persistent
 */
public class PointerItem2 extends Item
{
	/**
	 * @persistent
	 */
	static final StringAttribute code = new StringAttribute(NOT_NULL);

/**

	 **
	 * Constructs a new PointerItem2 with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param initialCode the initial value for attribute {@link #code}.
	 * @throws com.exedio.cope.lib.NotNullViolationException if initialCode is not null.
	 *
 */PointerItem2(
				final String initialCode)
			throws
				com.exedio.cope.lib.NotNullViolationException
	{
		super(new com.exedio.cope.lib.AttributeValue[]{
			new com.exedio.cope.lib.AttributeValue(code,initialCode),
		});
		throwInitialNotNullViolationException();
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
 */final String getCode()
	{
		return (String)getAttribute(this.code);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #code}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final void setCode(final String code)
			throws
				com.exedio.cope.lib.NotNullViolationException
	{
		try
		{
			setAttribute(this.code,code);
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
	 * The persistent type information for pointerItem2.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(
			PointerItem2.class,
			null
		)
;}
