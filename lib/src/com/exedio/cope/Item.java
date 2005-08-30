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

package com.exedio.cope;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.exedio.cope.Attribute.Option;
import com.exedio.cope.ItemAttribute.DeletePolicy;
import com.exedio.cope.util.ReactivationConstructorDummy;

/**
 * This is the super class for all classes,
 * that want to store their data persistently with COPE.
 * 
 * @author Ralf Wiebicke
 */
public abstract class Item extends Cope
{
	final Type type;

	/**
	 * The primary key of the item,
	 * that is unique within the {@link #type} of this item.
	 */
	final int pk;
	
	/**
	 * Returns a string unique for this item in all other items of the model.
	 * For any item <code>a</code> in its model <code>m</code>
	 * the following holds true:
	 * <code>a.equals(m.findByID(a.getCopeID()).</code>
	 * Does not activate this item, if it's not already active.
	 * Never returns null.
	 * @see Model#findByID(String)
	 */
	public final String getCopeID()
	{
		return type.getID() + '.' + type.getPrimaryKeyIterator().pk2id(pk);
	}
	
	/**
	 * Returns the type of this item.
	 * Never returns null.
	 */
	public final Type getCopeType()
	{
		return type;
	}

	/**
	 * Returns true, if <code>o</code> represents the same item as this item.
	 * Is equivalent to
	 * <pre>(o != null) && (o instanceof Item) && getCopeID().equals(((Item)o).getCopeID())</pre>
	 * Does not activate this item, if it's not already active.
	 */
	public final boolean equals(final Object o)
	{
		return (o!=null) && (getClass()==o.getClass()) && (pk==((Item)o).pk);
	}

	/**
	 * Returns a hash code, that is consistent with {@link #equals(Object)}.
	 * Note, that this is not neccessarily equivalent to <code>getCopeID().hashCode()</code>.
	 * Does not activate this item, if it's not already active.
	 */
	public final int hashCode()
	{
		return getClass().hashCode() ^ pk;
	}
	
	public String toString()
	{
		return getCopeID();
	}

	/**
	 * Returns, whether this item is active.
	 */	
	public final boolean isActiveCopeItem()
	{
		final Entity entity = getEntityIfActive();
		return (entity!=null) && (entity.getItem() == this);
	}

	/**
	 * Returns the active item object representing the same item as this item object.
	 * For any two item objects <code>a</code>, <code>b</code> the following holds true:
	 * <p>
	 * If and only if <code>a.equals(b)</code> then <code>a.activeCopeItem() == b.activeCopeItem()</code>.
	 * <p>
	 * So it does for items, what {@link String#intern} does for strings.
	 * Does activate this item, if it's not already active.
	 * Is guaranteed to be very cheap, if this item object is already active, which means
	 * this method returns <code>this</code>.
	 * Never returns null.
	 */
	public final Item activeCopeItem()
	{
		return getEntity().getItem();
	}

	/**
	 * Must never be public, since it does not throw exceptions for constraint violations.
	 * Subclasses (usually generated) must care about throwing these exception by calling
	 * {@link #throwInitialMandatoryViolationException} and/or 
	 * {@link #throwInitialUniqueViolationException}.
	 * All this fiddling is needed, because one cannot wrap a <code>super()</code> call into a
	 * try-catch statement.
	 * @throws ClassCastException
	 *         if one of the values in <code>initialAttributeValues</code>
	 *         is not compatible to it's attribute.
	 */
	protected Item(final AttributeValue[] initialAttributeValues)
		throws ClassCastException
	{
		this.type = Type.findByJavaClass(getClass());
		this.pk = type.getPrimaryKeyIterator().nextPK();
		if(pk==Type.NOT_A_PK)
			throw new RuntimeException();
		//System.out.println("create item "+type+" "+pk);
		
		try
		{
			for(int i = 0; i<initialAttributeValues.length; i++)
			{
				final AttributeValue av = initialAttributeValues[i];
				av.attribute.checkValue(av.value, null);
			}
		}
		catch(MandatoryViolationException e)
		{
			initialNotNullViolationException = e;
			return;
		}
		catch(LengthViolationException e)
		{
			initialLengthViolationException = e;
			return;
		}

		final Entity entity = getEntity(false);
		entity.put( initialAttributeValues );
		try
		{
			entity.write();
		}
		catch(UniqueViolationException e)
		{
			initialUniqueViolationException = e;
			return;
		}
		catch(IntegrityViolationException e)
		{
			throw new NestingRuntimeException( e );
		}
	}
	
	/**
	 * Reactivation constructor.
	 * Is used for internal purposes only.
	 * Does not actually create a new item, but a passive item object for
	 * an already existing item.
	 */
	protected Item(
		final ReactivationConstructorDummy reactivationDummy,
		final int pk)
	{
		this.type = Type.findByJavaClass(getClass());
		this.pk = pk;
		//System.out.println("reactivate item:"+type+" "+pk);

		if(reactivationDummy!=Type.REACTIVATION_DUMMY)
			throw new RuntimeException("reactivation constructor is for internal purposes only, don't use it in your application!");
		if(pk==Type.NOT_A_PK)
			throw new RuntimeException();
	}
	
	private MandatoryViolationException initialNotNullViolationException = null;

	/**
	 * Throws a {@link MandatoryViolationException}, if a mandatory violation occured in the constructor.
	 * @throws MandatoryViolationException
	 *         if one of the values in <code>initialAttributeValues</code>
	 *         is either null or not specified
	 *         and it's attribute is {@link Attribute#isMandatory() mandatory}.
	 */
	protected final void throwInitialMandatoryViolationException() throws MandatoryViolationException
	{
		if(initialNotNullViolationException!=null)
			throw initialNotNullViolationException;
	}
	
	private LengthViolationException initialLengthViolationException = null;

	/**
	 * Throws a {@link LengthViolationException}, if a length violation occured in the constructor.
	 * @throws LengthViolationException
	 *         if one of the values in <code>initialAttributeValues</code>
	 *         violated the length constraint of it's attribute.
	 */
	protected final void throwInitialLengthViolationException() throws LengthViolationException
	{
		if(initialLengthViolationException!=null)
			throw initialLengthViolationException;
	}
	
	private UniqueViolationException initialUniqueViolationException = null;
	
	/**
	 * Throws a {@link UniqueViolationException}, if a unique violation occured in the constructor.
	 */
	protected final void throwInitialUniqueViolationException() throws UniqueViolationException
	{
		if(initialUniqueViolationException!=null)
			throw initialUniqueViolationException;
	}
	
	public final Object get(final Function function)
	{
		if(function instanceof ObjectAttribute)
			return get((ObjectAttribute)function);
		else
			return get((ComputedFunction)function);
	}

	public final Object get(final ObjectAttribute attribute)
	{
		return getEntity().get(attribute);
	}
	
	public final Object get(final ComputedFunction function)
	{
		final List sources = function.getSources();
		final Object[] values = new Object[sources.size()];
		int pos = 0;
		for(Iterator i = sources.iterator(); i.hasNext(); )
			values[pos++] = get((Function)i.next());
	
		return function.mapJava(values);
	}
	
	/**
	 * @throws MandatoryViolationException
	 *         if <code>value</code> is null and <code>attribute</code>
	 *         is {@link Attribute#isMandatory() mandatory}.
	 * @throws ReadOnlyViolationException
	 *         if <code>attribute</code> is {@link Attribute#isReadOnly() read-only}.
	 * @throws ClassCastException
	 *         if <code>value</code> is not compatible to <code>attribute</code>.
	 */
	public final void set(final ObjectAttribute attribute, final Object value)
		throws
			UniqueViolationException,
			MandatoryViolationException,
			LengthViolationException,
			ReadOnlyViolationException,
			ClassCastException
	{
		if(attribute.isReadOnly())
			throw new ReadOnlyViolationException(this, attribute);

		attribute.checkValue(value, this);

		final Entity entity = getEntity();
		entity.put(attribute, value);
		try
		{
			entity.write();
		}
		catch (IntegrityViolationException e)
		{
			throw new NestingRuntimeException( e );
		}
	}

	/**
	 * @throws MandatoryViolationException
	 *         if <code>value</code> is null and <code>attribute</code>
	 *         is {@link Attribute#isMandatory() mandatory}.
	 * @throws ReadOnlyViolationException
	 *         if <code>attribute</code> is {@link Attribute#isReadOnly() read-only}.
	 * @throws ClassCastException
	 *         if <code>value</code> is not compatible to <code>attribute</code>.
	 */
	public final void set(final AttributeValue[] attributeValues)
		throws
			UniqueViolationException,
			MandatoryViolationException,
			LengthViolationException,
			ReadOnlyViolationException,
			ClassCastException
	{
		for(int i = 0; i<attributeValues.length; i++)
		{
			final AttributeValue attributeValue = attributeValues[i];
			final ObjectAttribute attribute = attributeValue.attribute;

			if(attribute.isReadOnly())
				throw new ReadOnlyViolationException(this, attribute);
	
			attribute.checkValue(attributeValue.value, this);
		}

		final Entity entity = getEntity();		
		entity.put(attributeValues);
		try
		{
			entity.write();
		}
		catch (IntegrityViolationException e)
		{
			throw new NestingRuntimeException( e );
		}
	}

	/**
	 * @throws ReadOnlyViolationException
	 *         if <code>attribute</code> is {@link Attribute#isReadOnly() read-only}.
	 */
	public final void touch(final DateAttribute attribute)
		throws
			UniqueViolationException,
			ReadOnlyViolationException
	{
		try
		{
			set(attribute, new Date()); // TODO: make a more efficient implementation
		}
		catch(MandatoryViolationException e)
		{
			throw new NestingRuntimeException(e);
		}
		catch(LengthViolationException e)
		{
			throw new NestingRuntimeException(e);
		}
	}

	private final void appendDataPath(
									final DataAttribute attribute,
									final StringBuffer bf)
	{
		bf.append(attribute.filePath).
			append(type.getPrimaryKeyIterator().pk2id(pk));
	}
	
	private final File getDataFile(final DataAttribute attribute)
	{
		final File directory = type.getModel().getProperties().getDatadirPath();
		final StringBuffer buf = new StringBuffer();
		appendDataPath(attribute, buf);
		return new File(directory, buf.toString());
	}
	
	/**
	 * Returns, whether there is no data for this attribute.
	 */
	public final boolean isNull(final DataAttribute attribute)
	{
		final File file = getDataFile(attribute);
		return !file.exists();
	}

	/**
	 * Returns a stream for fetching the data of this persistent data attribute.
	 * <b>You are responsible for closing the stream, when you are finished!</b>
	 * Returns null, if there is no data for this attribute.
	 */
	public final InputStream get(final DataAttribute attribute)
	{
		final File file = getDataFile(attribute);
		try
		{
			return new FileInputStream(file);
		}
		catch(FileNotFoundException e)
		{
			return null;
		}
	}

	/**
	 * Returns the length of the data of this persistent data attribute.
	 * Returns -1, if there is no data for this attribute.
	 */
	public final long getDataLength(final DataAttribute attribute)
	{
		final File file = getDataFile(attribute);

		return file.exists() ? file.length() : -1l;
	}

	/**
	 * Returns the date of the last modification
	 * of the data of this persistent data attribute.
	 * Returns -1, if there is no data for this attribute.
	 */
	public final long getDataLastModified(final DataAttribute attribute)
	{
		final File file = getDataFile(attribute);

		return file.exists() ? file.lastModified() : -1l;
	}

	/**
	 * Provides data for this persistent data attribute.
	 * Closes <data>data</data> after reading the contents of the stream.
	 * @param data give null to remove data.
	 * @throws MandatoryViolationException
	 *         if data is null and attribute is {@link Attribute#isMandatory() mandatory}.
	 * @throws IOException if reading data throws an IOException.
	 */
	public final void set(final DataAttribute attribute, final InputStream data)
	throws MandatoryViolationException, IOException
	{
		try
		{
			final File file = getDataFile(attribute);

			if(data!=null)
			{
				final OutputStream out = new FileOutputStream(file);
				final byte[] b = new byte[20*1024];
				for(int len = data.read(b); len>=0; len = data.read(b))
					out.write(b, 0, len);
				out.close();
				data.close();
			}
			else
			{
				if(file.exists())
				{
					if(!file.delete())
						throw new RuntimeException("deleting "+file+" failed.");
				}
			}
		}
		finally
		{
			if(data!=null)
				data.close();
		}
	}
	
	public final void deleteCopeItem()
			throws IntegrityViolationException
	{
		deleteCopeItem(new HashSet());
	}

	private final void deleteCopeItem(final HashSet toDelete)
			throws IntegrityViolationException
	{
		toDelete.add(this);
		
		//final String tostring = toString();
		//System.out.println("------------delete:"+tostring);
		try
		{
			// TODO make sure, no item is deleted twice
			for(Iterator i = type.getReferences().iterator(); i.hasNext(); )
			{
				final ItemAttribute attribute = (ItemAttribute)i.next();
				if(attribute.getDeletePolicy().nullify)
				{
					for(Iterator j = attribute.getType().search(attribute.equal(this)).iterator(); j.hasNext(); )
					{
						final Item item = (Item)j.next();
						//System.out.println("------------nullify:"+item.toString());
						item.set(attribute, null);
					}
				}
				if(attribute.getDeletePolicy().cascade)
				{
					for(Iterator j = attribute.getType().search(attribute.equal(this)).iterator(); j.hasNext(); )
					{
						final Item item = (Item)j.next();
						////System.out.println("------------check:"+item.toString());
						if(!toDelete.contains(item))
							item.deleteCopeItem(toDelete);
					}
				}
			}
			Entity entity = getEntity();
			entity.delete();
			entity.write();
		}
		catch(UniqueViolationException e)
		{
			// cannot happen, since null does not violate uniqueness
			throw new NestingRuntimeException(e);
		}
		catch(MandatoryViolationException e)
		{
			// cannot happen, since nullify ItemAttributes cannot be mandatory
			throw new NestingRuntimeException(e);
		}
		catch(LengthViolationException e)
		{
			// cannot happen, since there are no StringAttributes written
			throw new NestingRuntimeException(e);
		}
		catch(ReadOnlyViolationException e)
		{
			// cannot happen, since nullify ItemAttributes cannot be readonly
			throw new NestingRuntimeException(e);
		}
	}
	
	/**
	 * Returns, whether the item does exist.
	 * There are two possibilities, why an item could not exist:
	 * <ol>
	 * <li>the item has been deleted by {@link #deleteCopeItem()}.
	 * <li>the item has been created in a transaction,
	 *     that was subsequently rolled back by {@link Transaction#rollback()}.
	 * </ol>
	 */
	public final boolean existsCopeItem()
	{
		try
		{
			return getEntity().exists();
		}
		catch ( NoSuchItemException e )
		{
			return false;
		}
	}

	public static final Attribute.Option MANDATORY = new Attribute.Option(false, false, true);
	public static final Attribute.Option OPTIONAL = new Attribute.Option(false, false, false);

	public static final Attribute.Option UNIQUE = new Attribute.Option(false, true, true);
	public static final Attribute.Option UNIQUE_OPTIONAL = new Attribute.Option(false, true, false);

	public static final Attribute.Option READ_ONLY = new Attribute.Option(true, false, true);
	public static final Attribute.Option READ_ONLY_OPTIONAL = new Attribute.Option(true, false, false);

	public static final Attribute.Option READ_ONLY_UNIQUE = new Attribute.Option(true, true, true);
	public static final Attribute.Option READ_ONLY_UNIQUE_OPTIONAL = new Attribute.Option(true, true, false);
	 
	public static final ItemAttribute.DeletePolicy FORBID = new ItemAttribute.DeletePolicy(0);
	public static final ItemAttribute.DeletePolicy NULLIFY = new ItemAttribute.DeletePolicy(1);
	public static final ItemAttribute.DeletePolicy CASCADE = new ItemAttribute.DeletePolicy(2);
	
	// activation/deactivation -----------------------------------------------------
	
	private final Entity getEntity()
	{
		return getEntity(true);
	}

	private final Entity getEntity(final boolean present)
	{
		return Transaction.get().getEntity(this, present);
	}

	private final Entity getEntityIfActive()
	{
		return Transaction.get().getEntityIfActive(type, pk);
	}

	//-----------------------------------------
	
	protected static final ItemAttribute itemAttribute(final Option option, final Class targetTypeClass)
	{
		return new ItemAttribute(option, targetTypeClass, FORBID);
	}
	
	protected static final ItemAttribute itemAttribute(final Option option, final Class targetTypeClass, final DeletePolicy policy)
	{
		return new ItemAttribute(option, targetTypeClass, policy);
	}
	
	public static final StringAttribute stringAttribute(final Option option)
	{
		return new StringAttribute(option);
	}

	public static final StringAttribute stringAttribute(final Option option, final int minimumLength)
	{
		return new StringAttribute(option, minimumLength);
	}

	public static final StringAttribute stringAttribute(final Option option, final int minimumLength, final int maximumLength)
	{
		return new StringAttribute(option, minimumLength, maximumLength);
	}

	protected static final IntegerAttribute integerAttribute(final Option option)
	{
		return new IntegerAttribute(option);
	}
	
	protected static final LongAttribute longAttribute(final Option option)
	{
		return new LongAttribute(option);
	}
	
	protected static final DoubleAttribute doubleAttribute(final Option option)
	{
		return new DoubleAttribute(option);
	}
	
	public static final DataAttribute dataAttribute(final Option option)
	{
		return new DataAttribute(option);
	}

	protected static final UniqueConstraint uniqueConstraint(final ObjectAttribute uniqueAttribute)
	{
		return new UniqueConstraint(uniqueAttribute);
	}

	protected static final UniqueConstraint uniqueConstraint(final ObjectAttribute uniqueAttribute1, final ObjectAttribute uniqueAttribute2)
	{
		return new UniqueConstraint(uniqueAttribute1, uniqueAttribute2);
	}
	
	protected static final UniqueConstraint uniqueConstraint(final ObjectAttribute uniqueAttribute1, final ObjectAttribute uniqueAttribute2, final ObjectAttribute uniqueAttribute3)
	{
		return new UniqueConstraint(uniqueAttribute1, uniqueAttribute2, uniqueAttribute3);
	}
	
	protected static final DateAttribute dateAttribute(final Option option)
	{
		return new DateAttribute(option);
	}

	/**
	 * @param forbidTimestampColumn
	 * 		forces the new date attribute to be implemented with an integer column
	 * 		holding the time value of the dates,
	 * 		even if the database supports timestamp columns.
	 */
	protected static final DateAttribute dateAttribute(final Option option, final boolean forbidTimestampColumn)
	{
		return new DateAttribute(option, forbidTimestampColumn);
	}
	
	public static final BooleanAttribute booleanAttribute(final Option option)
	{
		return new BooleanAttribute(option);
	}

	protected static final EnumAttribute enumAttribute(final Option option, final Class enumClass)
	{
		return new EnumAttribute(option, enumClass);
	}

}
