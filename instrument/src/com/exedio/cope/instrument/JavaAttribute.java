
package injection;

import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import persistence.NotNullViolationException;
import persistence.ReadOnlyViolationException;
import persistence.UniqueViolationException;
import tools.ClassComparator;

/**
 * Represents an attribute of a class parsed by the
 * java parser.
 * Contains additional information about this attribute
 * described in the doccomment of this attribute.
 * @see Injector
 */
public final class JavaAttribute extends JavaFeature
{
	private String persistentType = null;
	private boolean readOnly = false;
	private boolean notNull = false;
	private boolean mapped = false;
	private List qualifiers = null;

	public JavaAttribute(JavaClass parent, int modifiers, String type, String name)
	throws InjectorParseException
	{
		// parent must not be null
		super(parent.getFile(), parent, modifiers, type, name);
		if(type==null)
			throw new RuntimeException();
	}
	
	/**
	 * Constructs a java attribute with the same
	 * {@link #parent}, {@link #modifiers} and {@link #type}
	 * but the given name.
	 * Needed for comma separated attributes.
	 */
	public JavaAttribute(JavaAttribute ja, String name)
	throws InjectorParseException
	{
		this(ja.getParent(), ja.getModifiers(), ja.type, name);
	}
	
	/**
	 * Returns the persistent type of this attribute.
	 */
	public String getPersistentType()
	{
		return this.persistentType;
	}

	private static final HashMap toNativeTypeMapping = new HashMap(3);
	private static final HashMap toBoxingCodeMapping = new HashMap(3);
	private static final HashMap toUnboxingCodeMapping = new HashMap(3);
	
	private static final void fillNativeTypeMap(final String persistentType, final String nativeType,
															  final String boxingCode, final String unboxingCode)
	{
		toNativeTypeMapping.put(persistentType, nativeType);
		toBoxingCodeMapping.put(persistentType, boxingCode);
		toUnboxingCodeMapping.put(persistentType, unboxingCode);
	}
	
	static
	{
		fillNativeTypeMap("Integer", "int", "new Integer(", ").intValue()");
	}


	private String boxedType = null;
	private boolean boxed;
	private String boxingCode;
	private String unboxingCode;

	private final String makeBoxedTypeAndFlag()
	{
		if(boxedType!=null)
			return boxedType;

		final String accessorType;
		if(notNull)
		{
			final String nativeType = (String)toNativeTypeMapping.get(persistentType);
			if(nativeType!=null)
			{
				boxedType = nativeType;
				boxed = true;
				boxingCode = (String)toBoxingCodeMapping.get(persistentType);
				unboxingCode = (String)toUnboxingCodeMapping.get(persistentType);
			}
			else
			{
				boxedType = persistentType;
				boxed = false;
			}
		}
		else
			return boxedType = persistentType;
		
		this.boxedType = boxedType;
		return boxedType;
	}

	/**
	 * Returns the type of this attribute to be used in accessor (setter/getter) methods.
	 * Differs from {@link #getPersistentType() the persistent type},
	 * if and only if the attribute is {@link #isBoxed() boxed}.
	 */
	public final String getBoxedType()
	{
		if(boxedType==null)
			makeBoxedTypeAndFlag();
		
		return boxedType;
	}
	
	/**
	 * Returns, whether the persistent type is &quot;boxed&quot; into a native type.
	 * This happens if the attribute has a not-null constraint 
	 * and the persistent type is convertable to a native types (int, double, boolean).
	 * @see #getBoxedType()
	 */
	public final boolean isBoxed()
	{
		if(boxedType==null)
			makeBoxedTypeAndFlag();
		
		return boxed;
	}
	
	public final String getBoxingCode()
	{
		if(boxedType==null)
			makeBoxedTypeAndFlag();
		
		return boxingCode;
	}
	
	public final String getUnBoxingCode()
	{
		if(boxedType==null)
			makeBoxedTypeAndFlag();
		
		return unboxingCode;
	}
	
	public boolean isPartOfUniqueConstraint()
	{
		for( final Iterator i=getParent().getUniqueConstraints().iterator(); i.hasNext(); )
		{
			final JavaAttribute[] uniqueConstraint = (JavaAttribute[])i.next();
			for(int j=0; j<uniqueConstraint.length; j++)
			{
				if(this == uniqueConstraint[j])
					return true;
			}
		}
		return false;
	}
	
	public boolean isReadOnly()
	{
		return readOnly;
	}
	
	public boolean isNotNull()
	{
		return notNull;
	}
	
	public boolean isMapped()
	{
		return mapped;
	}
	
	public List getQualifiers()
	{
		return qualifiers;
	}
	
	/**
	 * Return a fully qualified name of the attribute,
	 * including class and package path.
	 * Syntax follows the javadoc tags,
	 * with a '#' between class and attribute name.
	 * Is used for type tracing log files.
	 */
	public final String getFullDocName()
	{
		return getFile().getPackageName()+'.'+getParent().getName()+'#'+getName();
	}
	
	private String camelCaseName = null;
	
	public final String getCamelCaseName()
	{
		if(camelCaseName!=null)
			return camelCaseName;

		final String name = getName();
		final char first = name.charAt(0);
		if(Character.isUpperCase(first))
			camelCaseName = name;
		else
			camelCaseName = Character.toUpperCase(first) + name.substring(1);

		return camelCaseName;
	}
	
	/**
	 * See Java Specification 8.3.1 &quot;Field Modifiers&quot;
	 */
	public final int getAllowedModifiers()
	{
		return
		Modifier.PUBLIC |
		Modifier.PROTECTED |
		Modifier.PRIVATE |
		Modifier.FINAL |
		Modifier.STATIC |
		Modifier.TRANSIENT |
		Modifier.VOLATILE;
	}
	
	public final int getMethodModifiers()
	{
		return
		getModifiers() &
		(
		Modifier.PUBLIC |
		Modifier.PROTECTED |
		Modifier.PRIVATE
		) |
		Modifier.FINAL;
	}
	
	public final void makePersistent(final String persistentType)
	{
		if(persistentType==null)
			throw new NullPointerException();
		if(this.persistentType!=null)
			throw new RuntimeException("Du Schwein!");
		getParent().addPersistentAttribute(this);
		this.persistentType = persistentType;
	}

	public final void makeReadOnly()
	{
		if(this.qualifiers!=null)
			throw new RuntimeException();
		readOnly = true;
	}
	
	public final void makeNotNull()
	{
		if(this.qualifiers!=null)
			throw new RuntimeException();
		notNull = true;
	}
	
	public final void makeMapped()
	{
		mapped = true;
	}
	
	public final boolean isInitial()
	{
		return (readOnly || notNull) && !mapped;
	}
	
	public final boolean hasSetter()
	{
		return !readOnly && !mapped;
	}
	
	public final void makeQualified(final List qualifiers)
	{
		if(qualifiers==null)
			throw new NullPointerException();
		if(this.qualifiers!=null)
			throw new RuntimeException();
		if(this.readOnly)
			throw new RuntimeException();
		if(this.notNull)
			throw new RuntimeException();
		this.qualifiers = Collections.unmodifiableList(qualifiers);
	}

	private SortedSet setterExceptions = null;

	public final SortedSet getSetterExceptions()
	{
		if(setterExceptions!=null)
			return setterExceptions;
		
		final TreeSet modifyableSetterExceptions = new TreeSet(ClassComparator.newInstance());
		
		if(isPartOfUniqueConstraint())
			modifyableSetterExceptions.add(UniqueViolationException.class);
		if(readOnly)
			modifyableSetterExceptions.add(ReadOnlyViolationException.class);
		if(notNull && !toNativeTypeMapping.containsKey(persistentType))
			modifyableSetterExceptions.add(NotNullViolationException.class);

		this.setterExceptions = Collections.unmodifiableSortedSet(modifyableSetterExceptions);
		return this.setterExceptions;
	}


	private TreeSet exceptionsToCatchInSetter = null;

	/**
	 * Compute exceptions to be caught in the setter.
	 * These are just those thrown by {@link Item#setAttribute(Attribute,Object)}
	 * (or {@link Item#setAttribute(Attribute,Object[],Object)} for qualified attributes)
	 * which are not in the setters throws clause.
	 * (see {@link #getSetterExceptions())
	 */
	public final SortedSet getExceptionsToCatchInSetter()
	{
		if(exceptionsToCatchInSetter!=null)
			return exceptionsToCatchInSetter;

		exceptionsToCatchInSetter = new TreeSet(ClassComparator.newInstance());
		exceptionsToCatchInSetter.add(UniqueViolationException.class);
		if(qualifiers==null)
		{
			// qualified setAttribute does not throw not-null/read-only
			exceptionsToCatchInSetter.add(NotNullViolationException.class);
			exceptionsToCatchInSetter.add(ReadOnlyViolationException.class);
		}
		exceptionsToCatchInSetter.removeAll(getSetterExceptions());
		
		return exceptionsToCatchInSetter;
	}

}
