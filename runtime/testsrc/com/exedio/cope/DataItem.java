/*
 * Copyright (C) 2004-2006  exedio GmbH (www.exedio.com)
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

package com.exedio.cope;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @cope.persistent
 * @author Ralf Wiebicke
 */
public class DataItem extends Item
{

	public static final DataAttribute data = new DataAttribute(OPTIONAL);
	public static final DataAttribute data10 = new DataAttribute(OPTIONAL).lengthMax(10);
	public static final DataAttribute data10k = new DataAttribute(OPTIONAL).lengthMax(10*1000);
	public static final DataAttribute data100M = new DataAttribute(OPTIONAL).lengthMax(100*1000*1000);
	public static final StringAttribute name = new StringAttribute(OPTIONAL);
	
	public boolean isDataNull() // TODO generate this
	{
		return data.isNull(this);
	}
	
	public long getDataLength() // TODO generate this
	{
		return data.getLength(this);
	}
	
	public byte[] getData() // TODO generate this
	throws IOException
	{
		return DataItem.data.get(this);
	}
	
	public byte[] getData10() // TODO generate this
	throws IOException
	{
		return DataItem.data10.get(this);
	}
	
	public void getData(final OutputStream data) // TODO generate this
	throws IOException
	{
		DataItem.data.get(this, data);
	}
	
	public void getData(final File data) // TODO generate this
	throws IOException
	{
		DataItem.data.get(this, data);
	}
	
	public void setData(final byte[] data) // TODO generate this
	throws IOException
	{
		try
		{
			DataItem.data.set(this, data);
		}
		catch(MandatoryViolationException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public void setData10(final byte[] data10) // TODO generate this
	throws IOException
	{
		try
		{
			DataItem.data10.set(this, data10);
		}
		catch(MandatoryViolationException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public void setData(final File data) // TODO generate this
	throws IOException
	{
		try
		{
			DataItem.data.set(this, data);
		}
		catch(MandatoryViolationException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public void setData10(final File data10) // TODO generate this
	throws IOException
	{
		try
		{
			DataItem.data10.set(this, data10);
		}
		catch(MandatoryViolationException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public DataItem(final byte[] data)
	{
		this(new com.exedio.cope.SetValue[]{
				DataItem.data.map(data),
		});
	}
	
	public DataItem(final byte[] data, final byte[] data10)
	{
		this(new com.exedio.cope.SetValue[]{
				DataItem.data.map(data),
				DataItem.data10.map(data10),
		});
	}
	
	
/**

	 **
	 * Creates a new DataItem with all the attributes initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of attributes.
	 */
	public DataItem()
	{
		this(new com.exedio.cope.SetValue[]{
		});
	}/**

	 **
	 * Creates a new DataItem and sets the given attributes initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	protected DataItem(final com.exedio.cope.SetValue[] initialAttributes)
	{
		super(initialAttributes);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	protected DataItem(com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #data}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setData(final java.io.InputStream data)
			throws
				java.io.IOException
	{
		DataItem.data.set(this,data);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #data10}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setData10(final java.io.InputStream data10)
			throws
				java.io.IOException
	{
		DataItem.data10.set(this,data10);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #data10k}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setData10k(final java.io.InputStream data10k)
			throws
				java.io.IOException
	{
		DataItem.data10k.set(this,data10k);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #data100M}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setData100M(final java.io.InputStream data100M)
			throws
				java.io.IOException
	{
		DataItem.data100M.set(this,data100M);
	}/**

	 **
	 * Returns the value of the persistent attribute {@link #name}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the attribute.
	 */
	public final java.lang.String getName()
	{
		return DataItem.name.get(this);
	}/**

	 **
	 * Sets a new value for the persistent attribute {@link #name}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the attribute.
	 */
	public final void setName(final java.lang.String name)
			throws
				com.exedio.cope.LengthViolationException
	{
		DataItem.name.set(this,name);
	}/**

	 **
	 * The persistent type information for dataItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type TYPE =
		new com.exedio.cope.Type(DataItem.class)
;}
