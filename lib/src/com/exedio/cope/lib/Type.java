
package com.exedio.cope.lib;

import com.exedio.cope.lib.Database;
import com.exedio.cope.lib.util.ReactivationConstructorDummy;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public final class Type
{
	private static final ArrayList typesModifyable = new ArrayList();
	private static final List types = Collections.unmodifiableList(typesModifyable);
	private static final HashMap typesByName = new HashMap();
	
	private final Class javaClass;
	
	private final Attribute[] attributes;
	private final List attributeList;

	private final UniqueConstraint[] uniqueConstraints;
	private final List uniqueConstraintList;
	
	private final String persistentQualifier;
	private final Constructor reactivationConstructor;
	private static final Class[] reactivationConstructorParams =
		new Class[]{ReactivationConstructorDummy.class, int.class};

	public static final List getTypes()
	{
		return types;
	}
	
	public static final Type getType(String className)
	{
		return (Type)typesByName.get(className);
	}
	
	public Type(final Class javaClass, final Attribute[] attributes, final UniqueConstraint[] uniqueConstraints, final Runnable initializer)
	{
		this.javaClass = javaClass;
		this.attributes = attributes;
		this.attributeList = Collections.unmodifiableList(Arrays.asList(attributes));
		this.uniqueConstraints = uniqueConstraints;
		this.uniqueConstraintList = Collections.unmodifiableList(Arrays.asList(uniqueConstraints));
		initializer.run();
		typesModifyable.add(this);
		typesByName.put(javaClass.getName(), this);
		this.persistentQualifier = Database.theInstance.makePersistentQualifier(this);
		try
		{
			reactivationConstructor = javaClass.getDeclaredConstructor(reactivationConstructorParams);
		}
		catch(NoSuchMethodException e)
		{
			throw new SystemException(e);
		}
	}
	
	public final Class getJavaClass()
	{
		return javaClass;
	}
	
	public final List getAttributes()
	{
		return attributeList;
	}
	
	public final List getUniqueConstraints()
	{
		return uniqueConstraintList;
	}
	
	public String getPersistentQualifier()
	{
		return persistentQualifier;
	}


	private String toStringCache = null;
	
	public final String toString()
	{
		if(toStringCache!=null)
			return toStringCache;
		
		final StringBuffer buf = new StringBuffer();
		
		buf.append(javaClass.getName());
		for(int i = 0; i<uniqueConstraints.length; i++)
		{
			buf.append(' ');
			buf.append(uniqueConstraints[i].toString());
		}
		
		toStringCache = buf.toString();
		return toStringCache;
	}
	
	// active items of this type ---------------------------------------------
	
	/**
	 * TODO: use something more efficient for integer keys.
	 */
	private HashMap activeItems = new HashMap();
	
	/**
	 * Returns an item of this type and the given pk, if it's already active.
	 * Returns null, if either there is no such item with the given pk, or
	 * such an item is not active.
	 */
	Item getActiveItem(final int pk)
	{
		return (Item)activeItems.get(new Integer(pk));
	}
	
	void putActiveItem(final Item item)
	{
		activeItems.put(new Integer(item.pk), item);
	}
	
	void removeActiveItem(final Item item)
	{
		activeItems.remove(new Integer(item.pk));
	}
	
	private Item createItemObject(final int pk)
	{
		try
		{
			return (Item)reactivationConstructor.newInstance(new Object[]{null, new Integer(pk)});
		}
		catch(InstantiationException e)
		{
			throw new SystemException(e);
		}
		catch(IllegalAccessException e)
		{
			throw new SystemException(e);
		}
		catch(InvocationTargetException e)
		{
			throw new SystemException(e);
		}
	}

	Item getItem(final int pk)
	{
		final Item activeItem = getActiveItem(pk);
		if(activeItem!=null)
			return activeItem;
		else
			return createItemObject(pk);
	}

}
