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

class DataItem extends Item
{
	static final DataField data = new DataField().optional();
	static final DataField data10 = new DataField().optional().lengthMax(10);
	static final DataField data10k = new DataField().optional().lengthMax(10*1000);
	static final DataField data100M = new DataField().optional().lengthMax(100*1000*1000);
	static final StringField name = new StringField().optional();
	
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
	 * Returns, whether there is no data for field {@link #data}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final boolean isDataNull()
	{
		return DataItem.data.isNull(this);
	}/**

	 **
	 * Returns the length of the data of the data field {@link #data}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final long getDataLength()
	{
		return DataItem.data.getLength(this);
	}/**

	 **
	 * Returns the value of the persistent field {@link #data}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final byte[] getDataArray()
	{
		return DataItem.data.getArray(this);
	}/**

	 **
	 * Writes the data of this persistent data field into the given stream.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void getData(final java.io.OutputStream data)
			throws
				java.io.IOException
	{
		DataItem.data.get(this,data);
	}/**

	 **
	 * Writes the data of this persistent data field into the given file.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void getData(final java.io.File data)
			throws
				java.io.IOException
	{
		DataItem.data.get(this,data);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #data}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setData(final com.exedio.cope.DataField.Value data)
	{
		DataItem.data.set(this,data);
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
	 * Sets a new value for the persistent field {@link #data}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setData(final java.io.InputStream data)
			throws
				java.io.IOException
	{
		DataItem.data.set(this,data);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #data}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setData(final java.io.File data)
			throws
				java.io.IOException
	{
		DataItem.data.set(this,data);
	}/**

	 **
	 * Returns, whether there is no data for field {@link #data10}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final boolean isData10Null()
	{
		return DataItem.data10.isNull(this);
	}/**

	 **
	 * Returns the length of the data of the data field {@link #data10}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final long getData10Length()
	{
		return DataItem.data10.getLength(this);
	}/**

	 **
	 * Returns the value of the persistent field {@link #data10}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final byte[] getData10Array()
	{
		return DataItem.data10.getArray(this);
	}/**

	 **
	 * Writes the data of this persistent data field into the given stream.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void getData10(final java.io.OutputStream data10)
			throws
				java.io.IOException
	{
		DataItem.data10.get(this,data10);
	}/**

	 **
	 * Writes the data of this persistent data field into the given file.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void getData10(final java.io.File data10)
			throws
				java.io.IOException
	{
		DataItem.data10.get(this,data10);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #data10}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setData10(final com.exedio.cope.DataField.Value data10)
	{
		DataItem.data10.set(this,data10);
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
	 * Sets a new value for the persistent field {@link #data10}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setData10(final java.io.InputStream data10)
			throws
				java.io.IOException
	{
		DataItem.data10.set(this,data10);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #data10}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setData10(final java.io.File data10)
			throws
				java.io.IOException
	{
		DataItem.data10.set(this,data10);
	}/**

	 **
	 * Returns, whether there is no data for field {@link #data10k}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final boolean isData10kNull()
	{
		return DataItem.data10k.isNull(this);
	}/**

	 **
	 * Returns the length of the data of the data field {@link #data10k}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final long getData10kLength()
	{
		return DataItem.data10k.getLength(this);
	}/**

	 **
	 * Returns the value of the persistent field {@link #data10k}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final byte[] getData10kArray()
	{
		return DataItem.data10k.getArray(this);
	}/**

	 **
	 * Writes the data of this persistent data field into the given stream.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void getData10k(final java.io.OutputStream data10k)
			throws
				java.io.IOException
	{
		DataItem.data10k.get(this,data10k);
	}/**

	 **
	 * Writes the data of this persistent data field into the given file.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void getData10k(final java.io.File data10k)
			throws
				java.io.IOException
	{
		DataItem.data10k.get(this,data10k);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #data10k}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setData10k(final com.exedio.cope.DataField.Value data10k)
	{
		DataItem.data10k.set(this,data10k);
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
	 * Sets a new value for the persistent field {@link #data10k}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setData10k(final java.io.InputStream data10k)
			throws
				java.io.IOException
	{
		DataItem.data10k.set(this,data10k);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #data10k}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setData10k(final java.io.File data10k)
			throws
				java.io.IOException
	{
		DataItem.data10k.set(this,data10k);
	}/**

	 **
	 * Returns, whether there is no data for field {@link #data100M}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final boolean isData100MNull()
	{
		return DataItem.data100M.isNull(this);
	}/**

	 **
	 * Returns the length of the data of the data field {@link #data100M}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final long getData100MLength()
	{
		return DataItem.data100M.getLength(this);
	}/**

	 **
	 * Returns the value of the persistent field {@link #data100M}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final|boolean-as-is</tt> in the comment of the field.
	 */
	final byte[] getData100MArray()
	{
		return DataItem.data100M.getArray(this);
	}/**

	 **
	 * Writes the data of this persistent data field into the given stream.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void getData100M(final java.io.OutputStream data100M)
			throws
				java.io.IOException
	{
		DataItem.data100M.get(this,data100M);
	}/**

	 **
	 * Writes the data of this persistent data field into the given file.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.getter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void getData100M(final java.io.File data100M)
			throws
				java.io.IOException
	{
		DataItem.data100M.get(this,data100M);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #data100M}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setData100M(final com.exedio.cope.DataField.Value data100M)
	{
		DataItem.data100M.set(this,data100M);
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
	 * Sets a new value for the persistent field {@link #data100M}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setData100M(final java.io.InputStream data100M)
			throws
				java.io.IOException
	{
		DataItem.data100M.set(this,data100M);
	}/**

	 **
	 * Sets a new value for the persistent field {@link #data100M}.
	 * @cope.generated This feature has been generated by the cope instrumentor and will be overwritten by the build process.
	 *       It can be customized with the tag <tt>@cope.setter public|package|protected|private|none|non-final</tt> in the comment of the field.
	 */
	final void setData100M(final java.io.File data100M)
			throws
				java.io.IOException
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
