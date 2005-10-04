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

import com.exedio.cope.Item;
import com.exedio.cope.ItemAttribute;
import com.exedio.cope.StringAttribute;
import com.exedio.cope.UniqueConstraint;


/**
 * @cope.persistent
 * @author Ralf Wiebicke
 */
public class AttributeEmptyItem extends Item
{
	public static final ItemAttribute parent = new ItemAttribute(OPTIONAL, AttributeItem.class, CASCADE);

	public static final ItemAttribute key = new ItemAttribute(OPTIONAL, EmptyItem.class);
	
	public static final UniqueConstraint parentKey = new UniqueConstraint(parent, key);

	public static final StringAttribute someQualifiedString = new StringAttribute(OPTIONAL);

/**

	 **
	 * Creates a new AttributeEmptyItem with all the attributes initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public AttributeEmptyItem()
	{
		this(new com.exedio.cope.AttributeValue[]{
		});
	}/**

	 **
	 * Creates a new AttributeEmptyItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */private AttributeEmptyItem(final com.exedio.cope.AttributeValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */private AttributeEmptyItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #parent}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final AttributeItem getParent()
	{
		return (AttributeItem)get(AttributeEmptyItem.parent);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #parent}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setParent(final AttributeItem parent)
			throws
				com.exedio.cope.UniqueViolationException
	{
		try
		{
			set(AttributeEmptyItem.parent,parent);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.MandatoryViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #key}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final EmptyItem getKey()
	{
		return (EmptyItem)get(AttributeEmptyItem.key);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #key}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setKey(final EmptyItem key)
			throws
				com.exedio.cope.UniqueViolationException
	{
		try
		{
			set(AttributeEmptyItem.key,key);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.MandatoryViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.ReadOnlyViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #someQualifiedString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final java.lang.String getSomeQualifiedString()
	{
		return (java.lang.String)get(AttributeEmptyItem.someQualifiedString);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #someQualifiedString}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public final void setSomeQualifiedString(final java.lang.String someQualifiedString)
	{
		try
		{
			set(AttributeEmptyItem.someQualifiedString,someQualifiedString);
		}
		catch(com.exedio.cope.LengthViolationException e)
		{
			throw new com.exedio.cope.NestingRuntimeException(e);
		}
		catch(com.exedio.cope.MandatoryViolationException e)
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
	 * Finds a attributeEmptyItem by it's unique attributes.
	 * @param parent shall be equal to attribute {@link #parent}.
	 * @param key shall be equal to attribute {@link #key}.
	 * @return null if there is no matching item.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *
 */public static final AttributeEmptyItem findByParentKey(final AttributeItem parent,final EmptyItem key)
	{
		return (AttributeEmptyItem)AttributeEmptyItem.parentKey.searchUnique(new Object[]{parent,key});
	}/**

	 **
	 * The persistent type information for attributeEmptyItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <code>@cope.type public|package|protected|private|none</code> in the class comment.
	 *
 */public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(AttributeEmptyItem.class)
;}
