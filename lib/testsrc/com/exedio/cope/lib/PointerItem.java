
package com.exedio.cope.lib;

/**
 * @persistent
 */
public class PointerItem extends Item
{
	/**
	 * @persistent
	 * @not-null
	 */
	static final StringAttribute code = new StringAttribute();
	
	
	/**
	 * @persistent PointerItem2
	 * @not-null
	 */
	static final ItemAttribute pointer = new ItemAttribute();

	/**
	 * @persistent PointerItem
	 */
	static final ItemAttribute self = new ItemAttribute();

/**

	 **
	 * Constructs a new PointerItem with all the attributes initially needed.
	 * @param initialCode the initial value for attribute {@link #code}.
	 * @param initialPointer the initial value for attribute {@link #pointer}.
	 * @throws com.exedio.cope.lib.NotNullViolationException if initialCode, initialPointer is not null.
	 * @generated
	 *
 */PointerItem(
				final String initialCode,
				final PointerItem2 initialPointer)
			throws
				com.exedio.cope.lib.NotNullViolationException
	{
		super(TYPE, new com.exedio.cope.lib.AttributeValue[]{
			new com.exedio.cope.lib.AttributeValue(code,initialCode),
			new com.exedio.cope.lib.AttributeValue(pointer,initialPointer),
		});
		throwInitialNotNullViolationException();
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see Item#Item(com.exedio.cope.lib.util.ReactivationConstructorDummy,com.exedio.cope.lib.Type,int)
	 * @generated
	 *
 */private PointerItem(com.exedio.cope.lib.util.ReactivationConstructorDummy d, final int pk)
	{
		super(d,TYPE,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #code}.
	 * @generated
	 *
 */final String getCode()
	{
		return (String)getAttribute(this.code);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #code}.
	 * @generated
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
	 * Returns the value of the persistent attribute {@link #pointer}.
	 * @generated
	 *
 */final PointerItem2 getPointer()
	{
		return (PointerItem2)getAttribute(this.pointer);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #pointer}.
	 * @generated
	 *
 */final void setPointer(final PointerItem2 pointer)
			throws
				com.exedio.cope.lib.NotNullViolationException
	{
		try
		{
			setAttribute(this.pointer,pointer);
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
	 * Returns the value of the persistent attribute {@link #self}.
	 * @generated
	 *
 */final PointerItem getSelf()
	{
		return (PointerItem)getAttribute(this.self);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #self}.
	 * @generated
	 *
 */final void setSelf(final PointerItem self)
	{
		try
		{
			setAttribute(this.self,self);
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
	 * The persistent type information for pointerItem.
	 * @generated
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(
			PointerItem.class,
			new com.exedio.cope.lib.Attribute[]{
				code.initialize("code",false,true),
				pointer.initialize("pointer",false,true,PointerItem2.class),
				self.initialize("self",false,false,PointerItem.class),
			},
			null
		)
;}
