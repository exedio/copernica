
package com.exedio.cope.lib;

/**
 * @persistent
 */
public class PointerItem extends Item
{

	static final StringAttribute code = new StringAttribute(NOT_NULL);
	
	static final ItemAttribute pointer = new ItemAttribute(NOT_NULL, PointerItem2.class);

	static final ItemAttribute self = new ItemAttribute(DEFAULT, PointerItem.class);

/**

	 **
	 * Constructs a new PointerItem with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param initialCode the initial value for attribute {@link #code}.
	 * @param initialPointer the initial value for attribute {@link #pointer}.
	 * @throws com.exedio.cope.lib.NotNullViolationException if initialCode, initialPointer is not null.
	 *
 */PointerItem(
				final String initialCode,
				final PointerItem2 initialPointer)
			throws
				com.exedio.cope.lib.NotNullViolationException
	{
		super(new com.exedio.cope.lib.AttributeValue[]{
			new com.exedio.cope.lib.AttributeValue(code,initialCode),
			new com.exedio.cope.lib.AttributeValue(pointer,initialPointer),
		});
		throwInitialNotNullViolationException();
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.lib.util.ReactivationConstructorDummy,int)
	 *
 */private PointerItem(com.exedio.cope.lib.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #code}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final String getCode()
	{
		return (String)getAttribute(PointerItem.code);
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
			setAttribute(PointerItem.code,code);
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
	 * Returns the value of the persistent attribute {@link #pointer}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final PointerItem2 getPointer()
	{
		return (PointerItem2)getAttribute(PointerItem.pointer);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #pointer}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final void setPointer(final PointerItem2 pointer)
			throws
				com.exedio.cope.lib.NotNullViolationException
	{
		try
		{
			setAttribute(PointerItem.pointer,pointer);
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
	 * Returns the value of the persistent attribute {@link #self}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final PointerItem getSelf()
	{
		return (PointerItem)getAttribute(PointerItem.self);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #self}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final void setSelf(final PointerItem self)
	{
		try
		{
			setAttribute(PointerItem.self,self);
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
	 * The persistent type information for pointerItem.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(PointerItem.class)
;}
