/*
 * Copyright (C) 2004-2007  exedio GmbH (www.exedio.com)
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
import java.io.InputStream;
import java.io.OutputStream;

class DataItem extends Item
{

	static final DataField data = new DataField().optional();
	static final DataField data10 = new DataField().optional().lengthMax(10);
	static final DataField data10k = new DataField().optional().lengthMax(10*1000);
	static final DataField data100M = new DataField().optional().lengthMax(100*1000*1000);
	static final StringField name = new StringField().optional();
	
	boolean isDataNull() // TODO generate this
	{
		return data.isNull(this);
	}
	
	long getDataLength() // TODO generate this
	{
		return data.getLength(this);
	}
	
	void getData(final OutputStream data) // TODO generate this
	throws IOException
	{
		DataItem.data.get(this, data);
	}
	
	void getData(final File data) // TODO generate this
	throws IOException
	{
		DataItem.data.get(this, data);
	}
	
	void setData(final DataField.Value data) // TODO generate this
	throws IOException
	{
		DataItem.data.set(this, data);
	}
	
	void setData(final InputStream data) // TODO generate this
	throws IOException
	{
		DataItem.data.set(this, data);
	}
	
	void setData10(final InputStream data10) // TODO generate this
	throws IOException
	{
		DataItem.data10.set(this, data10);
	}
	
	void setData(final File data) // TODO generate this
	throws IOException
	{
		DataItem.data.set(this, data);
	}
	
	void setData10(final File data10) // TODO generate this
	throws IOException
	{
		DataItem.data10.set(this, data10);
	}
	
	DataItem(final byte[] data)
	{
		this(new com.exedio.cope.SetValue[]{
				DataItem.data.map(data),
		});
	}
	
	DataItem(final byte[] data, final byte[] data10)
	{
		this(new com.exedio.cope.SetValue[]{
				DataItem.data.map(data),
				DataItem.data10.map(data10),
		});
	}
	
	
/**

	 **
	 * Creates a new DataItem with all the fields initially needed.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tags <tt>@cope.constructor public|package|protected|private|none</tt> in the class comment and <tt>@cope.initial</tt> in the comment of fields.
	 */
	DataItem()
	{
		this(new com.exedio.cope.SetValue[]{
		});
	}/**

	 **
	 * Creates a new DataItem and sets the given fields initially.
	 * This constructor is called by {@link com.exedio.cope.Type#newItem Type.newItem}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.generic.constructor public|package|protected|private|none</tt> in the class comment.
	 */
	protected DataItem(final com.exedio.cope.SetValue... setValues)
	{
		super(setValues);
	}/**

	 **
	 * Reactivation constructor. Used for internal purposes only.
	 * @see com.exedio.cope.Item#Item(com.exedio.cope.util.ReactivationConstructorDummy,int)
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	protected DataItem(final com.exedio.cope.util.ReactivationConstructorDummy d,final int pk)
	{
		super(d,pk);
	}/**

	 **
	 * Returns the value of the persistent field {@link #data}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final byte[] getArrayData()
	{
		return DataItem.data.getArray(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #data}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setData(final byte[] data)
	{
		DataItem.data.set(this,data);
	}/**

	 **
	 * Returns the value of the persistent field {@link #data10}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final byte[] getArrayData10()
	{
		return DataItem.data10.getArray(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #data10}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setData10(final byte[] data10)
	{
		DataItem.data10.set(this,data10);
	}/**

	 **
	 * Returns the value of the persistent field {@link #data10k}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final byte[] getArrayData10k()
	{
		return DataItem.data10k.getArray(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #data10k}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setData10k(final byte[] data10k)
	{
		DataItem.data10k.set(this,data10k);
	}/**

	 **
	 * Returns the value of the persistent field {@link #data100M}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final byte[] getArrayData100M()
	{
		return DataItem.data100M.getArray(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #data100M}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setData100M(final byte[] data100M)
	{
		DataItem.data100M.set(this,data100M);
	}/**

	 **
	 * Returns the value of the persistent field {@link #name}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final java.lang.String getName()
	{
		return DataItem.name.get(this);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #name}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setName(final java.lang.String name)
			throws
				com.exedio.cope.LengthViolationException
	{
		DataItem.name.set(this,name);
	}/**

	 **
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 */
	private static final long serialVersionUID = 1l;/**

	 **
	 * The persistent type information for dataItem.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.type public|package|protected|private|none</tt> in the class comment.
	 */
	public static final com.exedio.cope.Type<DataItem> TYPE = newType(DataItem.class)
;}
