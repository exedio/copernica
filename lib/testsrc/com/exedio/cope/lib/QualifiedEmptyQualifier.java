
package com.exedio.cope.lib;

/**
 * @persistent
 */
public class QualifiedEmptyQualifier extends Item
{
	static final ItemAttribute parent = new ItemAttribute(READ_ONLY_NOT_NULL, QualifiedItem.class);
	
	static final ItemAttribute key = new ItemAttribute(READ_ONLY_NOT_NULL, EmptyItem.class);
	
	static final UniqueConstraint qualifyUnique = new UniqueConstraint(parent, key);
	
	static final StringAttribute qualifiedA = new StringAttribute(DEFAULT);
	static final StringAttribute qualifiedB = new StringAttribute(DEFAULT);
	

/**

	 **
	 * Constructs a new QualifiedEmptyQualifier with all the attributes initially needed.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param initialParent the initial value for attribute {@link #parent}.
	 * @param initialKey the initial value for attribute {@link #key}.
	 * @throws com.exedio.cope.lib.NotNullViolationException if initialParent, initialKey is not null.
	 * @throws com.exedio.cope.lib.UniqueViolationException if initialParent, initialKey is not unique.
	 *
 */QualifiedEmptyQualifier(
				final QualifiedItem initialParent,
				final EmptyItem initialKey)
			throws
				com.exedio.cope.lib.NotNullViolationException,
				com.exedio.cope.lib.UniqueViolationException
	{
		super(new com.exedio.cope.lib.AttributeValue[]{
			new com.exedio.cope.lib.AttributeValue(parent,initialParent),
			new com.exedio.cope.lib.AttributeValue(key,initialKey),
		});
		throwInitialNotNullViolationException();
		throwInitialUniqueViolationException();
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @see Item#Item(com.exedio.cope.lib.util.ReactivationConstructorDummy,int)
	 *
 */private QualifiedEmptyQualifier(com.exedio.cope.lib.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #parent}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final QualifiedItem getParent()
	{
		return (QualifiedItem)getAttribute(QualifiedEmptyQualifier.parent);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #key}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final EmptyItem getKey()
	{
		return (EmptyItem)getAttribute(QualifiedEmptyQualifier.key);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #qualifiedA}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final String getQualifiedA()
	{
		return (String)getAttribute(QualifiedEmptyQualifier.qualifiedA);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #qualifiedA}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final void setQualifiedA(final String qualifiedA)
	{
		try
		{
			setAttribute(QualifiedEmptyQualifier.qualifiedA,qualifiedA);
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
	 * Returns the value of the persistent attribute {@link #qualifiedB}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final String getQualifiedB()
	{
		return (String)getAttribute(QualifiedEmptyQualifier.qualifiedB);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #qualifiedB}.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */final void setQualifiedB(final String qualifiedB)
	{
		try
		{
			setAttribute(QualifiedEmptyQualifier.qualifiedB,qualifiedB);
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
	 * Finds a qualifiedEmptyQualifier by it's unique attributes.
	 * <p><small>Generated by the cope instrumentor.</small>
	 * @param searchedParent shall be equal to attribute {@link #parent}.
	 * @param searchedKey shall be equal to attribute {@link #key}.
	 *
 */static final QualifiedEmptyQualifier findByQualifyUnique(final QualifiedItem searchedParent,final EmptyItem searchedKey)
	{
		return (QualifiedEmptyQualifier)qualifyUnique.searchUnique(new Object[]{searchedParent,searchedKey});
	}/**

	 **
	 * The persistent type information for qualifiedEmptyQualifier.
	 * <p><small>Generated by the cope instrumentor.</small>
	 *
 */public static final com.exedio.cope.lib.Type TYPE = 
		new com.exedio.cope.lib.Type(QualifiedEmptyQualifier.class)
;}
