
package com.exedio.cope.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.exedio.cope.lib.util.ReactivationConstructorDummy;

public abstract class Item extends Search
{
	private final Type type;

	final int pk;
	
	private boolean deleted = false;

	/**
	 * The row containing the item cache for this item, if this item is active.
	 * If you want to be sure, that you get a row, use {@link #getRow()}.
	 */
	private Row rowWhenActive;

	/**
	 * Returns a string unique for this item in all other items of this application.
	 * For any item <code>a</code> the following holds true:
	 * <code>a.equals(findByID(a.getID()).</code>
	 * Does not activate this item, if it's not already active.
	 * Never returns null.
	 * @see #findByID(String)
	 */
	public final String getID()
	{
		return getClass().getName() + '.' + pk2id(pk);
	}
	
	/**
	 * Returns the type of this item.
	 * Never returns null.
	 */
	public final Type getType()
	{
		return type;
	}

	/**
	 * Returns true, if <code>o</code> represents the same item as this item.
	 * Is equivalent to
	 * <code>(o != null) && (o instanceof Item) && getID().equals(((Item)o).getID())</code>.
	 * Does not activate this item, if it's not already active.
	 */
	public final boolean equals(final Object o)
	{
		return (o!=null) && (getClass()==o.getClass()) && (pk==((Item)o).pk);
	}

	/**
	 * Returns a hash code, that is consistent with {@link #equals(Object)}.
	 * Note, that this is not neccessarily equivalent to <code>getID().hashCode()</code>.
	 * Does not activate this item, if it's not already active.
	 */
	public final int hashCode()
	{
		return getClass().hashCode() ^ pk;
	}

	/**
	 * Returns, whether this item is active.
	 */	
	public final boolean isActive()
	{
		return rowWhenActive!=null;
	}

	/**
	 * Returns the active item object representing the same item as this item object.
	 * For any two item objects <code>a</code>, <code>b</code> the following holds true:
	 * <p>
	 * If and only if <code>a.equals(b)</code> then <code>a.activeItem() == b.activeItem()</code>.
	 * <p>
	 * So it does for items, what {@link String#intern} does for strings.
	 * Does activate this item, if it's not already active.
	 * Is guaranteed to be very cheap, if this item object is already active, which means
	 * this method returns <code>this</code>.
	 * Never returns null.
	 */
	public final Item activeItem()
	{
		if(rowWhenActive!=null)
			return this;
		else
			return getRow().item;
	}

	/**
	 * Must never be public, since it does not throw exceptions for constraint violations.
	 * Subclasses (usually generated) must care about throwing these exception by calling
	 * {@link #throwInitialNotNullViolationException} and/or 
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
		this.type = Type.getType(getClass().getName());
		this.pk = type.nextPK();
		final Row row = new Row(this, false);
		//System.out.println("create item "+type+" "+pk);

		for(int i = 0; i<initialAttributeValues.length; i++)
		{
			final AttributeValue av = initialAttributeValues[i];
			if(av.attribute.isNotNull() && av.value == null)
			{
				initialNotNullViolationException = new NotNullViolationException(this, av.attribute);
				return;
			}
		}
		initialNotNullViolationException = null;

		row.put(initialAttributeValues);
		try
		{
			row.write();
		}
		catch(UniqueViolationException e)
		{
			initialUniqueViolationException = e;
			return;
		}

		this.rowWhenActive = row; // make active

		if(type==null)
			throw new NullPointerException(getClass().toString());
		if(pk==Type.NOT_A_PK)
			throw new RuntimeException();
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
		this.type = Type.getType(getClass().getName());
		this.pk = pk;
		rowWhenActive = null; // make passive
		initialNotNullViolationException = null;
		//System.out.println("reactivate item:"+type+" "+pk);

		if(type==null)
			throw new NullPointerException(getClass().toString());
		if(reactivationDummy!=Type.REACTIVATION_DUMMY)
			throw new RuntimeException("reactivation constructor is for internal purposes only, don't use it in your application!");
		if(pk==Type.NOT_A_PK)
			throw new RuntimeException();
	}
	
	private final NotNullViolationException initialNotNullViolationException;

	/**
	 * Throws a {@link NotNullViolationException}, if a not-null violation occured in the constructor.
	 * @throws NotNullViolationException
	 *         if one of the values in <code>initialAttributeValues</code>
	 *         is either null or not specified
	 *         and it's attribute is {@link Attribute#isNotNull() not-null}.
	 */
	protected final void throwInitialNotNullViolationException() throws NotNullViolationException
	{
		if(initialNotNullViolationException!=null)
			throw initialNotNullViolationException;
	}
	
	private UniqueViolationException initialUniqueViolationException;
	
	/**
	 * Throws a {@link UniqueViolationException}, if a unique violation occured in the constructor.
	 */
	protected final void throwInitialUniqueViolationException() throws UniqueViolationException
	{
		if(initialUniqueViolationException!=null)
			throw initialUniqueViolationException;
	}
	
	public final Object getAttribute(final Attribute attribute)
	{
		final AttributeMapping mapping = attribute.mapping;
		if(mapping!=null)
			return mapping.mapJava(getAttribute(mapping.sourceAttribute));

		return getRow().get(attribute);
	}
	
	public final Object getAttribute(final Attribute attribute, final Object[] qualifiers)
	{
		final AttributeMapping mapping = attribute.mapping;
		if(mapping!=null)
			return mapping.mapJava(getAttribute(mapping.sourceAttribute));

		return getRow().get(attribute);
	}

	/**
	 * @throws NotNullViolationException
	 *         if <code>value</code> is null and <code>attribute</code>
	 *         is {@link Attribute#isNotNull() not-null}.
	 * @throws ReadOnlyViolationException
	 *         if <code>attribute</code> is {@link Attribute#isReadOnly() read-only}
	 *         or a {@link AttributeMapping mapped attribute}.
	 * @throws ClassCastException
	 *         if <code>value</code> is not compatible to <code>attribute</code>.
	 */
	public final void setAttribute(final Attribute attribute, final Object value)
		throws
			UniqueViolationException,
			NotNullViolationException,
			ReadOnlyViolationException,
			ClassCastException
	{
		if(attribute.isReadOnly() || attribute.mapping!=null)
			throw new ReadOnlyViolationException(this, attribute);
		if(attribute.isNotNull() && value == null)
			throw new NotNullViolationException(this, attribute);

		final Row row = getRow();
		final Object previousValue = row.get(attribute);
		row.put(attribute, value);
		try
		{
			row.write();
		}
		catch(UniqueViolationException e)
		{
			row.put(attribute, previousValue);
			throw e;
		}
	}

	/**
	 * @throws ClassCastException
	 *         if <code>value</code> is not compatible to <code>attribute</code>.
	 */
	public final void setAttribute(final Attribute attribute, final Object[] qualifiers, final Object value)
		throws
			UniqueViolationException,
			ClassCastException
	{
		final Row row = getRow();
		final Object previousValue = row.get(attribute);
		row.put(attribute, value);
		try
		{
			row.write();
		}
		catch(UniqueViolationException e)
		{
			row.put(attribute, previousValue);
			throw e;
		}
	}
	
	private final void appendMediaPath(
									final MediaAttribute attribute, final String variant,
									final StringBuffer bf,
									final String mimeMajor, final String mimeMinor)
	{
		if(mimeMajor==null)
			throw new NullPointerException();
		if(mimeMinor==null)
			throw new NullPointerException();
			
		bf.append(attribute.getType().trimmedName).
			append('/').
			append(attribute.getName());
		
		if(variant!=null)
		{
			bf.append('/').
				append(variant);
		}

		bf.append('/').
			append(pk2id(pk));

		final String compactExtension;
		if("image".equals(mimeMajor))
		{
			if("jpeg".equals(mimeMinor) || "pjpeg".equals(mimeMinor))
				compactExtension = ".jpg";
			else if("gif".equals(mimeMinor))
				compactExtension = ".gif";
			else if("png".equals(mimeMinor))
				compactExtension = ".png";
			else
				compactExtension = null;
		}
		else
			compactExtension = null;
		
		if(compactExtension==null)
		{
			bf.append('.').
				append(mimeMajor).
				append('.').
				append(mimeMinor);
		}
		else
			bf.append(compactExtension);
	}
	
	private final File getMediaFile(final MediaAttribute attribute,	final String mimeMajor, final String mimeMinor)
	{
		final File directory = Properties.getInstance().getMediaDirectory();
		final StringBuffer buf = new StringBuffer();
		appendMediaPath(attribute, null, buf, mimeMajor, mimeMinor);
		return new File(directory, buf.toString());
	}
	
	/**
	 * Returns a URL pointing to the data of this persistent media attribute.
	 * Returns null, if there is no data for this attribute.
	 */
	public final String getMediaURL(final MediaAttribute attribute, final String variant)
	{
		final Row row = getRow();

		final String mimeMajor = (String)row.get(attribute.mimeMajor);
		if(mimeMajor==null)
			return null;

		final String mimeMinor = (String)row.get(attribute.mimeMinor);
		final StringBuffer bf = new StringBuffer(Properties.getInstance().getMediaUrl());
		appendMediaPath(attribute, variant, bf, mimeMajor, mimeMinor);
		return bf.toString();
	}

	/**
	 * Returns the major mime type of this persistent media attribute.
	 * Returns null, if there is no data for this attribute.
	 */
	public final String getMediaMimeMajor(final MediaAttribute attribute)
	{
		return (String)getRow().get(attribute.mimeMajor);
	}

	/**
	 * Returns the minor mime type of this persistent media attribute.
	 * Returns null, if there is no data for this attribute.
	 */
	public final String getMediaMimeMinor(final MediaAttribute attribute)
	{
		return (String)getRow().get(attribute.mimeMinor);
	}

	/**
	 * Returns a stream for fetching the data of this persistent media attribute.
	 * <b>You are responsible for closing the stream, when you are finished!</b>
	 * Returns null, if there is no data for this attribute.
	 */
	public final InputStream getMediaData(final MediaAttribute attribute)
	{
		final Row row = getRow();
		final String mimeMajor = (String)row.get(attribute.mimeMajor);
		if(mimeMajor==null)
			return null;

		final String mimeMinor = (String)row.get(attribute.mimeMinor);
		final File file = getMediaFile(attribute, mimeMajor, mimeMinor);
		try
		{
			return new FileInputStream(file);
		}
		catch(FileNotFoundException e)
		{
			throw new SystemException(e);
		}
	}

	/**
	 * Provides data for this persistent media attribute.
	 * <b>Closes the stream only, when finishing normally!</b>
	 * @param data give null to remove data.
	 * @throws NotNullViolationException
	 *         if data is null and attribute is {@link Attribute#isNotNull() not-null}.
	 * @throws IOException if reading data throws an IOException.
	 */
	public final void setMediaData(final MediaAttribute attribute, final InputStream data,
												 final String mimeMajor, final String mimeMinor)
	throws NotNullViolationException, IOException
	{
		if(data!=null)
		{
			if(mimeMajor==null||mimeMinor==null)
				throw new RuntimeException("if data is not null, mime types must also be not null");
		}
		else
		{
			if(mimeMajor!=null||mimeMinor!=null)
				throw new RuntimeException("if data is null, mime types must also be null");
		}

		final Row row = getRow();
		final String previousMimeMajor = (String)row.get(attribute.mimeMajor);
		final String previousMimeMinor = (String)row.get(attribute.mimeMinor);
		row.put(attribute.mimeMajor, mimeMajor);
		row.put(attribute.mimeMinor, mimeMinor);

		try
		{
			row.write();
		}
		catch(UniqueViolationException e)
		{
			new SystemException(e);
		}

		if(data!=null)
		{
			final File file = getMediaFile(attribute, mimeMajor, mimeMinor);
			final OutputStream out = new FileOutputStream(file);
			final byte[] b = new byte[20*1024];
			for(int len = data.read(b); len>=0; len = data.read(b))
				out.write(b, 0, len);
			out.close();
			data.close();

			// This is done after the new file is written,
			// to prevent loss of data, if writing the new file fails
			if(previousMimeMajor!=null)
			{
				final File previousFile = getMediaFile(attribute, previousMimeMajor, previousMimeMinor);
				if(!previousFile.equals(file))
				{
					if(!previousFile.delete())
						throw new RuntimeException("deleting "+previousFile+" failed.");
				}
			}
		}
		else
		{
			if(previousMimeMajor!=null)
			{
				final File file = getMediaFile(attribute, previousMimeMajor, previousMimeMinor);
				if(!file.delete())
					throw new RuntimeException("deleting "+file+" failed.");
			}
		}
	}
	
	public final void delete()
			throws IntegrityViolationException
	{
		// TODO: additionally we must ensure, that any passive item objects of this item
		// are marked deleted when they are tried to be loaded.
		if(rowWhenActive!=null)
		{
			if(type.getRow(pk)!=rowWhenActive)
				throw new RuntimeException();
			Database.theInstance.delete(type, pk);
			rowWhenActive.close();
			rowWhenActive = null;
		}
		else
		{
			final Row row = type.getRow(pk);
			if(row==null)
			{
				Database.theInstance.delete(type, pk);
			}
			else
			{
				if(row.item==this)
					throw new RuntimeException();
				if(row.item.rowWhenActive!=row)
					throw new RuntimeException();
				row.item.delete();
			}
		}
		deleted = true;
	}
	
	public final boolean isDeleted()
	{
		return deleted;
	}

	public static class Option
	{
		public final boolean readOnly;
		public final boolean notNull;
		public final boolean unique;

		private Option(final boolean readOnly, final boolean notNull, final boolean unique)
		{
			this.readOnly = readOnly;
			this.notNull = notNull;
			this.unique = unique;
		}
	}
	
	public static final Option DEFAULT = new Option(false, false, false);

	public static final Option READ_ONLY = new Option(true, false, false);
	public static final Option NOT_NULL = new Option(false, true, false);
	public static final Option UNIQUE = new Option(false, false, true);

	public static final Option READ_ONLY_NOT_NULL = new Option(true, true, false);
	public static final Option READ_ONLY_UNIQUE = new Option(true, false, true);
	public static final Option NOT_NULL_UNIQUE = new Option(false, true, true);
	 
	public static final Option READ_ONLY_NOT_NULL_UNIQUE = new Option(true, true, true);

	// activation/deactivation -----------------------------------------------------
	
	/**
	 * Activates this item.
	 * After this method, {link #row} is guaranteed to be not null.
	 */
	private final Row getRow()
	{
		if(rowWhenActive!=null)
		{
			if(type.getRow(pk)!=rowWhenActive)
				throw new RuntimeException();
			return rowWhenActive;
		}
		else
		{
			final Row row = type.getRow(pk);
			if(row==null)
			{
				rowWhenActive = new Row(this, true);
				Database.theInstance.load(rowWhenActive);
				return rowWhenActive;
			}
			else
			{
				if(row.item==this)
					throw new RuntimeException();
				if(row.item.rowWhenActive!=row)
					throw new RuntimeException();
				return row;
			}
		}
	}

	public final void passivate()
	{
		if(rowWhenActive!=null)
		{
			rowWhenActive.close();
			rowWhenActive = null;
		}
	}

}
