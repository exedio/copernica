
package com.exedio.cope.lib;

/**
 * @persistent
 */
public class PointerItem2 extends Item
{
	/**
	 * @persistent
	 * @not-null
	 */
	static final StringAttribute code = new StringAttribute();

/**

	 **
	 * Constructs a new PointerItem2 with all the attributes initially needed.
	 * @param initialCode the initial value for attribute {@link #code}.
	 * @throws com.exedio.cope.lib.NotNullViolationException if initialCode is not null.
	 * @author cope instrumentor
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
	 * @see Item#Item(com.exedio.cope.lib.util.ReactivationConstructorDummy,int)
	 * @author cope instrumentor
	 *
 */private PointerItem2(com.exedio.cope.lib.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #code}.
	 * @author cope instrumentor
	 *
 */final String getCode()
	{
		return (String)getAttribute(this.code);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #code}.
	 * @author cope instrumentor
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
	 * @author cope instrumentor
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(
			PointerItem2.class,
			new com.exedio.cope.lib.Attribute[]{
				code.initialize(false,true),
			},
			null
		)
;}
