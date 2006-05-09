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

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.exedio.cope.search.GreaterCondition;
import com.exedio.cope.search.GreaterEqualCondition;
import com.exedio.cope.search.LessCondition;
import com.exedio.cope.search.LessEqualCondition;
import com.exedio.cope.search.OrCondition;
import com.exedio.cope.util.ReactivationConstructorDummy;

public final class Type<C extends Item>
{
	private static final HashMap<Class<? extends Item>, Type<? extends Item>> typesByClass = new HashMap<Class<? extends Item>, Type<? extends Item>>();

	final Class<C> javaClass;
	final String id;
	final boolean isAbstract;
	final Type<? super C> supertype;
	
	final Function<C> thisFunction = new This<C>(this);
	private final List<Feature> declaredFeatures;
	private final List<Feature> features;
	private final HashMap<String, Feature> declaredFeaturesByName;
	private final HashMap<String, Feature> featuresByName;

	private final List<Attribute> declaredAttributes;
	private final List<Attribute> attributes;
	final List<UniqueConstraint> declaredUniqueConstraints;
	private final List<UniqueConstraint> uniqueConstraints;

	private ArrayList<Type<? extends C>> subTypes = null;

	private ArrayList<ItemAttribute<C>> referencesWhileInitialization = new ArrayList<ItemAttribute<C>>();
	private List<ItemAttribute<C>> declaredReferences = null;
	private List<ItemAttribute> references = null;
	
	private Model model;
	private ArrayList<Type<? extends C>> typesOfInstances;
	private HashMap<String, Type<? extends C>> typesOfInstancesMap;
	private Type<? extends C> onlyPossibleTypeOfInstances;
	private String[] typesOfInstancesColumnValues;
	
	private Table table;
	private PkSource pkSource;

	private final Constructor creationConstructor;
	private final Constructor reactivationConstructor;
	
	/**
	 * This number uniquely identifies a type within its model.
	 * However, this number is not stable across JVM restarts.
	 * So never put this number into any persistent storage,
	 * nor otherwise make this number accessible outside the library.
	 * <p>
	 * This number is negative for abstract types and positive
	 * (including zero) for non-abstract types.
	 */
	int transientNumber = -1;

	/**
	 * @throws RuntimeException if there is no type for the given java class.
	 */
	public static final <X extends Item> Type<X> findByJavaClass(final Class<X> javaClass)
	{
		final Type<X> result = castType(typesByClass.get(javaClass));
		if(result==null)
			throw new RuntimeException("there is no type for " + javaClass);
		return result;
	}
	
	@SuppressWarnings("unchecked") // OK: generic maps cannot ensure fit between key and value
	private static final <X extends Item> Type<X> castType(final Type t)
	{
		return (Type<X>)t;
	}
	
	private ArrayList<Feature> featuresWhileConstruction;
	
	private static final String classToId(final Class javaClass)
	{
		final String className = javaClass.getName();
		final int pos = className.lastIndexOf('.');
		return className.substring(pos+1).intern();
	}

	Type(final Class<C> javaClass)
	{
		this(javaClass, classToId(javaClass));
	}
	
	Type(final Class<C> javaClass, final String id)
	{
		this.javaClass = javaClass;
		this.id = id;
		this.isAbstract = ( javaClass.getModifiers() & Modifier.ABSTRACT ) > 0;
		
		if(!Item.class.isAssignableFrom(javaClass))
			throw new IllegalArgumentException(javaClass + " is not a subclass of Item");
		if(javaClass.equals(Item.class))
			throw new IllegalArgumentException("Cannot make a type for " + javaClass + " itself, but only for subclasses.");
		
		typesByClass.put(javaClass, this);

		// supertype
		final Class superClass = javaClass.getSuperclass();
		
		if(superClass.equals(Item.class))
			supertype = null;
		else
		{
			supertype = findByJavaClass(castSuperType(superClass));
			supertype.registerSubType(this);
		}

		// declared features
		final Field[] fields = javaClass.getDeclaredFields();
		this.featuresWhileConstruction = new ArrayList<Feature>(fields.length);
		final int expectedModifier = Modifier.STATIC | Modifier.FINAL;
		try
		{
			for(final Field field : fields)
			{
				if((field.getModifiers()&expectedModifier)==expectedModifier)
				{
					final Class fieldType = field.getType();
					if(Feature.class.isAssignableFrom(fieldType))
					{
						field.setAccessible(true);
						final Feature feature = (Feature)field.get(null); 
						if(feature==null)
							throw new RuntimeException(field.getName());
						feature.initialize(this, field.getName());
					}
				}
			}
		}
		catch(IllegalAccessException e)
		{
			throw new RuntimeException(e);
		}
		featuresWhileConstruction.trimToSize();
		this.declaredFeatures = Collections.unmodifiableList(featuresWhileConstruction);
		// make sure, method registerInitialization fails from now on
		this.featuresWhileConstruction = null;

		// declared attributes / unique constraints
		{
			final ArrayList<Attribute> declaredAttributes = new ArrayList<Attribute>(declaredFeatures.size());
			final ArrayList<UniqueConstraint> declaredUniqueConstraints = new ArrayList<UniqueConstraint>(declaredFeatures.size());
			final HashMap<String, Feature> declaredFeaturesByName = new HashMap<String, Feature>();
			for(final Feature feature : declaredFeatures)
			{
				if(feature instanceof Attribute)
					declaredAttributes.add((Attribute)feature);
				if(feature instanceof UniqueConstraint)
					declaredUniqueConstraints.add((UniqueConstraint)feature);
				if(declaredFeaturesByName.put(feature.getName(), feature)!=null)
					throw new RuntimeException("duplicate feature "+feature.getName()+" for type "+javaClass.getName());
			}
			declaredAttributes.trimToSize();
			declaredUniqueConstraints.trimToSize();
			this.declaredAttributes = Collections.unmodifiableList(declaredAttributes);
			this.declaredUniqueConstraints = Collections.unmodifiableList(declaredUniqueConstraints);
			this.declaredFeaturesByName = declaredFeaturesByName;
		}

		// inherit features / attributes
		if(supertype==null)
		{
			this.features = this.declaredFeatures;
			this.featuresByName = this.declaredFeaturesByName;
			this.attributes = this.declaredAttributes;
			this.uniqueConstraints = this.declaredUniqueConstraints;
		}
		else
		{
			this.features = inherit(supertype.getFeatures(), this.declaredFeatures);
			{
				final HashMap<String, Feature> inherited = supertype.featuresByName;
				final HashMap<String, Feature> declared = this.declaredFeaturesByName;
				if(declared.isEmpty())
					this.featuresByName = inherited;
				else
				{
					final HashMap<String, Feature> result = new HashMap<String, Feature>(inherited);
					for(final Feature f : declared.values())
					{
						if(result.put(f.getName(), f)!=null)
							throw new RuntimeException("cannot override inherited feature "+f.getName()+" in type "+id);
					}
					this.featuresByName = result;
				}
			}
			this.attributes = inherit(supertype.getAttributes(), this.declaredAttributes);
			this.uniqueConstraints = inherit(supertype.getUniqueConstraints(), this.declaredUniqueConstraints);
		}

		// IMPLEMENTATION NOTE
		// Here we don't precompute the constructor parameters
		// because they are needed in the initialization phase
		// only.
		this.creationConstructor = getConstructor(new Class[]{(new SetValue[0]).getClass()}, "creation");
		this.reactivationConstructor = getConstructor(new Class[]{ReactivationConstructorDummy.class, int.class}, "reactivation");
	}
	
	@SuppressWarnings("unchecked") // OK: Class.getSuperclass() does not support generics
	private static final Class<Item> castSuperType(final Class o)
	{
		return (Class<Item>)o;
	}

	private static final <F extends Feature> List<F> inherit(final List<F> inherited, final List<F> declared)
	{
		assert inherited!=null;
		
		if(declared.isEmpty())
			return inherited;
		else if(inherited.isEmpty())
			return declared;
		else
		{
			final ArrayList<F> result = new ArrayList<F>(inherited);
			result.addAll(declared);
			result.trimToSize();
			return Collections.<F>unmodifiableList(result);
		}
	}
	
	private Constructor getConstructor(final Class[] params, final String name)
	{
		try
		{
			final Constructor result = javaClass.getDeclaredConstructor(params);
			result.setAccessible(true);
			return result;
		}
		catch(NoSuchMethodException e)
		{
			throw new RuntimeException(javaClass.getName() + " does not have a " + name + " constructor", e);
		}
	}
	
	void registerInitialization(final Feature feature)
	{
		featuresWhileConstruction.add(feature);
	}

	void registerSubType(final Type subType)
	{
		assert subType!=null : id;
		if(this.model!=null)
			throw new RuntimeException(id+'-'+subType.id);

		if(subTypes==null)
			subTypes = new ArrayList<Type<? extends C>>();
		subTypes.add(castTypeInstance(subType));
	}
	
	@SuppressWarnings("unchecked")
	private Type<? extends C> castTypeInstance(final Type t)
	{
		return t;
	}
	
	void registerReference(final ItemAttribute<C> reference)
	{
		referencesWhileInitialization.add(reference);
	}
	
	void initialize(final Model model, final int transientNumber)
	{
		if(model==null)
			throw new RuntimeException();
		assert (transientNumber<0) == isAbstract;

		if(this.model!=null)
			throw new RuntimeException();
		if(this.typesOfInstances!=null)
			throw new RuntimeException();
		if(this.typesOfInstancesMap!=null)
			throw new RuntimeException();
		if(this.onlyPossibleTypeOfInstances!=null)
			throw new RuntimeException();
		if(this.typesOfInstancesColumnValues!=null)
			throw new RuntimeException();
		if(this.table!=null)
			throw new RuntimeException();
		if(this.pkSource!=null)
			throw new RuntimeException();
		if(this.transientNumber>=0)
			throw new RuntimeException();
		
		this.model = model;
		this.transientNumber = transientNumber;
		
		final ArrayList<Type> typesOfInstances = new ArrayList<Type>();
		collectTypesOfInstances(typesOfInstances, 15);
		switch(typesOfInstances.size())
		{
			case 0:
				throw new RuntimeException("type " + id + " is abstract and has no non-abstract (even indirect) subtypes");
			case 1:
				onlyPossibleTypeOfInstances = castTypeInstance(typesOfInstances.iterator().next());
				break;
			default:
				final HashMap<String, Type> typesOfInstancesMap = new HashMap<String, Type>();
				typesOfInstancesColumnValues = new String[typesOfInstances.size()];
				int i = 0;
				for(final Type t : typesOfInstances)
				{
					if(typesOfInstancesMap.put(t.id, t)!=null)
						throw new RuntimeException(t.id);
					typesOfInstancesColumnValues[i++] = t.id;
				}
				this.typesOfInstancesMap = castTypeInstanceHasMap(typesOfInstancesMap);
				break;
		}
		this.typesOfInstances = castTypeInstanceArrayList(typesOfInstances);

		for(final Attribute a : declaredAttributes)
			if(a instanceof ItemAttribute)
				((ItemAttribute)a).postInitialize();
	}
	
	@SuppressWarnings("unchecked")
	private ArrayList<Type<? extends C>> castTypeInstanceArrayList(final ArrayList l)
	{
		return l;
	}
	
	@SuppressWarnings("unchecked")
	private HashMap<String, Type<? extends C>> castTypeInstanceHasMap(final HashMap m)
	{
		return m;
	}
	
	private void collectTypesOfInstances(final ArrayList<Type> result, int levelLimit)
	{
		if(levelLimit<=0)
			throw new RuntimeException(result.toString());
		levelLimit--;
		
		if(!isAbstract)
			result.add(this);
		
		for(final Type<? extends C> t : getSubTypes())
			t.collectTypesOfInstances(result, levelLimit);
	}
	
	void postInitialize()
	{
		assert referencesWhileInitialization!=null;
		assert declaredReferences==null;
		assert references==null;
		
		referencesWhileInitialization.trimToSize();
		this.declaredReferences = Collections.unmodifiableList(referencesWhileInitialization);
		this.referencesWhileInitialization = null;
		if(supertype!=null)
		{
			final List<ItemAttribute> inherited = supertype.getReferences();
			final List<ItemAttribute<C>> declared = declaredReferences;
			if(declared.isEmpty())
				this.references = inherited;
			else if(inherited.isEmpty())
				this.references = castReferences(declared);
			else
			{
				final ArrayList<ItemAttribute> result = new ArrayList<ItemAttribute>(inherited);
				result.addAll(declared);
				result.trimToSize();
				this.references = Collections.unmodifiableList(result);
			}
		}
		else
		{
			this.references = castReferences(declaredReferences);
		}
	}
	
	@SuppressWarnings("unchecked")
	private final List<ItemAttribute> castReferences(final List l)
	{
		return (List<ItemAttribute>)l;
	}
	
	void materialize(final Database database)
	{
		if(database==null)
			throw new RuntimeException();

		if(this.model==null)
			throw new RuntimeException();
		if(this.table!=null)
			throw new RuntimeException();
		if(this.pkSource!=null)
			throw new RuntimeException();

		this.table = new Table(database, id, supertype, typesOfInstancesColumnValues);

		if(supertype!=null)
			pkSource = supertype.getPkSource();
		else
			pkSource = database.makePkSource(table);
		
		for(final Attribute a : declaredAttributes)
			a.materialize(table);
		for(final UniqueConstraint uc : declaredUniqueConstraints)
			uc.materialize(database);
		this.table.setUniqueConstraints(this.declaredUniqueConstraints);
		this.table.finish();
	}
	
	public Class<C> getJavaClass()
	{
		return javaClass;
	}
	
	public String getID()
	{
		return id;
	}
	
	public Model getModel()
	{
		if(model==null)
			throw new RuntimeException("model not set for type " + id + ", probably you forgot to put this type into the model.");

		return model;
	}
	
	/**
	 * Returns a list of types,
	 * that instances (items) of this type can have.
	 * These are all subtypes of this type,
	 * including indirect subtypes,
	 * and including this type itself,
	 * which are not abstract.
	 */
	List<Type<? extends C>> getTypesOfInstances()
	{
		if(typesOfInstances==null)
			throw new RuntimeException();

		return Collections.unmodifiableList(typesOfInstances);
	}
	
	Type<? extends C> getTypeOfInstance(final String id)
	{
		return typesOfInstancesMap.get(id);
	}
	
	Type<? extends C> getOnlyPossibleTypeOfInstances()
	{
		if(typesOfInstances==null)
			throw new RuntimeException();

		return onlyPossibleTypeOfInstances;
	}
	
	String[] getTypesOfInstancesColumnValues()
	{
		if(typesOfInstances==null)
			throw new RuntimeException();
		
		if(typesOfInstancesColumnValues==null)
			return null;
		else
		{
			final String[] result = new String[typesOfInstancesColumnValues.length];
			System.arraycopy(typesOfInstancesColumnValues, 0, result, 0, result.length);
			return result;
		}
	}
	
	Table getTable()
	{
		if(model==null)
			throw new RuntimeException();

		return table;
	}
	
	/**
	 * Returns the name of database table for this type - use with care!
	 * <p>
	 * This information is needed only, if you want to access
	 * the database without cope.
	 * In this case you should really know, what you are doing.
	 * Please note, that this string may vary,
	 * if a cope model is configured for different databases.
	 * 
	 * @see Attribute#getColumnName()
	 */
	public String getTableName()
	{
		return table.id;
	}
	
	/**
	 * Returns the type representing the {@link Class#getSuperclass() superclass}
	 * of this type's {@link #getJavaClass() java class}.
	 * If this type has no super type
	 * (i.e. the superclass of this type's java class is {@link Item}),
	 * then null is returned.
	 */
	public Type<? super C> getSupertype()
	{
		return supertype;
	}
	
	public List<Type<? extends C>> getSubTypes()
	{
		return subTypes==null ? Collections.<Type<? extends C>>emptyList() : Collections.unmodifiableList(subTypes);
	}
	
	public boolean isAssignableFrom(final Type type)
	{
		return javaClass.isAssignableFrom(type.javaClass);
	}
	
	public boolean isAbstract()
	{
		return isAbstract;
	}
	
	public Function<C> getThis()
	{
		return thisFunction;
	}

	/**
	 * Returns all {@link ItemAttribute}s of the model this type belongs to,
	 * which {@link ItemAttribute#getValueType value type} equals this type.
	 * @see #getReferences()
	 */
	public List<ItemAttribute<C>> getDeclaredReferences()
	{
		assert declaredReferences!=null;
		return declaredReferences;
	}

	/**
	 * Returns all {@link ItemAttribute}s of the model this type belongs to,
	 * which {@link ItemAttribute#getValueType value type} equals this type
	 * or any of it's super types.
	 * @see #getDeclaredReferences()
	 */
	public List<ItemAttribute> getReferences()
	{
		assert references!=null;
		return references;
	}

	/**
	 * Returns the list of persistent attributes declared by the this type.
	 * This excludes inherited attributes.
	 * The elements in the list returned are ordered by their occurance in the source code.
	 * This method returns an empty list if the type declares no attributes.
	 * <p>
	 * If you want to get all persistent attributes of this type,
	 * including attributes inherited from super types,
	 * use {@link #getAttributes}.
	 * <p> 
	 * Naming of this method is inspired by Java Reflection API
	 * method {@link Class#getDeclaredFields() getDeclaredFields}.
	 */
	public List<Attribute> getDeclaredAttributes()
	{
		return declaredAttributes;
	}
	
	/**
	 * Returns the list of accessible persistent attributes of this type.
	 * This includes inherited attributes.
	 * The elements in the list returned are ordered by their type,
	 * with types higher in type hierarchy coming first,
	 * and within each type by their occurance in the source code.
	 * This method returns an empty list if the type has no accessible attributes.
	 * <p>
	 * If you want to get persistent attributes declared by this type only,
	 * excluding attributes inherited from super types,
	 * use {@link #getDeclaredAttributes}.
	 */
	public List<Attribute> getAttributes()
	{
		return attributes;
	}
	
	public List<Feature> getDeclaredFeatures()
	{
		return declaredFeatures;
	}

	public List<Feature> getFeatures()
	{
		return features;
	}
	
	public Feature getDeclaredFeature(final String name)
	{
		return declaredFeaturesByName.get(name);
	}

	public Feature getFeature(final String name)
	{
		return featuresByName.get(name);
	}

	public List<UniqueConstraint> getDeclaredUniqueConstraints()
	{
		return declaredUniqueConstraints;
	}
	
	public List<UniqueConstraint> getUniqueConstraints()
	{
		return uniqueConstraints;
	}
	
	private static final SetValue[] EMPTY_SET_VALUES = {};
	
	public C newItem(final SetValue[] setValues)
		throws ConstraintViolationException
	{
		final C result;
		try
		{
			result =
				cast((Item)creationConstructor.newInstance(
					new Object[]{
						setValues!=null
						? setValues
						: EMPTY_SET_VALUES
					}
				));
		}
		catch(InstantiationException e)
		{
			throw new RuntimeException(e);
		}
		catch(IllegalAccessException e)
		{
			throw new RuntimeException(e);
		}
		catch(InvocationTargetException e)
		{
			final Throwable t = e.getCause();
			if(t instanceof RuntimeException)
				throw (RuntimeException)t;
			else
				throw new RuntimeException(e);
		}
		
		return result;
	}

	static <X> X verboseCast(final Class<X> clazz, final Object o)
	{
		// NOTE:
		// This code is redundant to the following call to Class#cast(Object),
		// but creates an exception with a much more verbose message.
		if(o!= null && !clazz.isInstance(o))
			throw new ClassCastException("expected a " + clazz.getName() + ", but was a " + o.getClass().getName());
		
		return clazz.cast(o);
	}
	
	public C cast(final Item item)
	{
		return verboseCast(javaClass, item);
	}

	/**
	 * Searches for items of this type, that match the given condition.
	 * <p>
	 * Returns an unmodifiable collection.
	 * Any attempts to modify the returned collection, whether direct or via its iterator,
	 * result in an <tt>UnsupportedOperationException</tt>.
	 * @param condition the condition the searched items must match.
	 */
	public List<C> search(final Condition condition)
	{
		return newQuery(condition).search();
	}
	
	public List<C> search(final Condition condition, final Function orderBy, final boolean ascending)
	{
		final Query<C> query = newQuery(condition);
		query.setOrderBy(orderBy, ascending);
		return query.search();
	}
	
	/**
	 * Searches equivalently to {@link #search(Condition)},
	 * but assumes that the condition forces the search result to have at most one element.
	 * <p>
	 * Returns null, if the search result is {@link Collection#isEmpty() empty},
	 * returns the only element of the search result, if the result {@link Collection#size() size} is exactly one.
	 * @throws RuntimeException if the search result size is greater than one.
	 * @see Query#searchSingleton()
	 */
	public C searchSingleton(final Condition condition)
	{
		return newQuery(condition).searchSingleton();
	}
	
	/**
	 * @deprecated renamed to {@link #searchSingleton(Condition)}.
	 */
	@Deprecated
	public C searchUnique(final Condition condition)
	{
		return searchSingleton(condition);
	}
	
	public Query<C> newQuery(final Condition condition)
	{
		return new Query<C>(thisFunction, this, condition);
	}
	
	public String toString()
	{
		return id;
	}
	
	PkSource getPkSource()
	{
		if(pkSource==null)
			throw new RuntimeException("no primary key source in " + id + "; maybe you have to initialize the model first");
		
		return pkSource;
	}
	
	void onDropTable()
	{
		getPkSource().flushPK();
	}

	
	static final ReactivationConstructorDummy REACTIVATION_DUMMY = new ReactivationConstructorDummy();

	C getItemObject(final int pk)
	{
		final Entity entity = getModel().getCurrentTransaction().getEntityIfActive(this, pk);
		if(entity!=null)
			return cast(entity.getItem());
		else
			return createItemObject(pk);
	}
	
	C createItemObject(final int pk)
	{
		try
		{
			return
				cast((Item)reactivationConstructor.newInstance(
					new Object[]{
						REACTIVATION_DUMMY,
						Integer.valueOf(pk)
					}
				));
		}
		catch(InstantiationException e)
		{
			throw new RuntimeException(id, e);
		}
		catch(IllegalAccessException e)
		{
			throw new RuntimeException(id, e);
		}
		catch(InvocationTargetException e)
		{
			throw new RuntimeException(id, e);
		}
	}

	static final int NOT_A_PK = Integer.MIN_VALUE;	

	static final class This<E extends Item> implements Function<E>
	{
		final Type<E> type;
		
		private This(final Type<E> type)
		{
			assert type!=null;
			this.type = type;
		}
		
		public Type<E> getType()
		{
			return type;
		}
		
		public E get(final Item item)
		{
			return cast(item);
		}
		
		@SuppressWarnings("unchecked") // items not covered by generics
		private E cast(final Object o)
		{
			return (E)o;
		}
		
		public void append(Statement bf, Join join)
		{
			bf.appendPK(type, join);
		}
		
		public void appendParameter(Statement bf, E value)
		{
			throw new RuntimeException(); // TODO
		}

		public String toString()
		{
			return type.id + "#this";
		}
		
		// convenience methods for conditions and views ---------------------------------

		public final EqualCondition<E> equal(final E value)
		{
			return new EqualCondition<E>(this, value);
		}
		
		public final EqualCondition<E> equal(final Join join, final E value)
		{
			return new EqualCondition<E>(new JoinedFunction<E>(this, join), value);
		}
		
		public final OrCondition in(final Collection<E> values)
		{
			return OrCondition.in(this, values);
		}
		
		public final NotEqualCondition notEqual(final E value)
		{
			return new NotEqualCondition<E>(this, value);
		}
		
		public final EqualFunctionCondition equal(final Function<E> right)
		{
			return new EqualFunctionCondition(this, right);
		}

		public final LessCondition less(final E value)
		{
			return new LessCondition<E>(this, value);
		}
		
		public final LessEqualCondition lessOrEqual(final E value)
		{
			return new LessEqualCondition<E>(this, value);
		}
		
		public final GreaterCondition greater(final E value)
		{
			return new GreaterCondition<E>(this, value);
		}
		
		public final GreaterEqualCondition greaterOrEqual(final E value)
		{
			return new GreaterEqualCondition<E>(this, value);
		}
	}
}
