
package com.exedio.cope.lib;

import com.exedio.cope.lib.pattern.Qualifier;

/**
 * @persistent
 */
public class QualifiedItem extends Item
{
	static final IntegerAttribute number = new IntegerAttribute(DEFAULT);
	
	static final Qualifier qualifier = new Qualifier(QualifierItem.parent, QualifierItem.key, QualifierItem.qualifyUnique);

/**

	 **
	 * Constructs a new QualifiedItem with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public QualifiedItem()
	{
		super(new com.exedio.cope.lib.AttributeValue[]{
		});
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.lib.util.ReactivationConstructorDummy,int)
	 *
 */private QualifiedItem(com.exedio.cope.lib.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #number}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final Integer getNumber()
	{
		return (Integer)getAttribute(this.number);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #number}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final void setNumber(final Integer number)
	{
		try
		{
			setAttribute(this.number,number);
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
	 * The persistent type information for qualifiedItem.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(QualifiedItem.class)
;}
