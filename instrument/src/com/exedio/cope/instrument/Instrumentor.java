
package com.exedio.cope.instrument;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.exedio.cope.lib.Attribute;
import com.exedio.cope.lib.BooleanAttribute;
import com.exedio.cope.lib.DateAttribute;
import com.exedio.cope.lib.DoubleAttribute;
import com.exedio.cope.lib.EnumerationAttribute;
import com.exedio.cope.lib.Function;
import com.exedio.cope.lib.IntegerFunction;
import com.exedio.cope.lib.ItemAttribute;
import com.exedio.cope.lib.LongAttribute;
import com.exedio.cope.lib.MediaAttribute;
import com.exedio.cope.lib.MediaAttributeVariant;
import com.exedio.cope.lib.StringFunction;
import com.exedio.cope.lib.UniqueConstraint;
import com.exedio.cope.lib.pattern.Qualifier;

public final class Instrumentor implements InjectionConsumer
{
	/**
	 * Holds several properties of the class currently
	 * worked on.
	 */
	private JavaClass class_state=null;
	
	/**
	 * Collects the class states of outer classes,
	 * when operating on a inner class.
	 * @see #class_state
	 * @element-type InstrumentorClass
	 */
	private ArrayList class_state_stack=new ArrayList();
	
	/**
	 * The last file level doccomment that was read.
	 */
	private String lastFileDocComment = null;
	
	public void onPackage(JavaFile javafile)
	throws InjectorParseException
	{
	}
	
	public void onImport(String importname)
	{
	}
	
	private boolean discardnextfeature=false;
	
	/**
	 * Tag name for persistent classes.
	 */
	private static final String PERSISTENT_CLASS = "persistent";

	/**
	 * Tag name for one qualifier of qualified attributes.
	 */
	private static final String ATTRIBUTE_QUALIFIER = "qualifier";
	
	/**
	 * Tag name for the generated setter option.
	 */
	private static final String ATTRIBUTE_SETTER = "cope-setter";

	/**
	 * Tag name for the generated constructor option.
	 */
	private static final String CLASS_CONSTRUCTOR = "cope-constructor";
	
	/**
	 * All generated class features get this doccomment tag.
	 */
	static final String GENERATED = "Generated by the cope instrumentor.";

	private void handleClassComment(final JavaClass jc, final String docComment)
			throws InjectorParseException
	{
		if(containsTag(docComment, PERSISTENT_CLASS))
		{
			final String constructorOptionString = Injector.findDocTag(docComment, CLASS_CONSTRUCTOR);
			new CopeClass(jc, constructorOptionString);
		}
	}
	
	public void onClass(final JavaClass jc)
			throws InjectorParseException
	{
		//System.out.println("onClass("+jc.getName()+")");

		discardnextfeature=false;
		
		class_state_stack.add(class_state);
		class_state=jc;
		
		if(lastFileDocComment != null)
		{
			handleClassComment(jc, lastFileDocComment);
			lastFileDocComment = null;
		}
	}

	public void onClassEnd(final JavaClass javaClass, final Writer output)
	throws IOException, InjectorParseException
	{
		//System.out.println("onClassEnd("+javaClass.getName()+")");
		final CopeClass persistentClass = CopeClass.getCopeClass(javaClass);

		if(persistentClass!=null)
			(new Generator(output)).writeClassFeatures(persistentClass);
		
		if(class_state!=javaClass)
			throw new RuntimeException();
		class_state=(JavaClass)(class_state_stack.remove(class_state_stack.size()-1));
	}

	public void onBehaviourHeader(JavaBehaviour jb)
	throws java.io.IOException
	{
	}
	
	public void onAttributeHeader(JavaAttribute ja)
	{
	}
	
	private final void handleAttribute(final JavaAttribute ja, final Class typeClass, final String docComment)
		throws InjectorParseException
	{
		final String type = ja.type;
		final List initializerArguments = ja.getInitializerArguments();
		//System.out.println(initializerArguments);
					
		final String qualifier = Injector.findDocTag(docComment, ATTRIBUTE_QUALIFIER);
		final List qualifiers;
		if(qualifier!=null)
			qualifiers = Collections.singletonList(qualifier);
		else
			qualifiers = null;
	
		final String setterOptionString = Injector.findDocTag(docComment, ATTRIBUTE_SETTER);

		if(
			IntegerFunction.class.isAssignableFrom(typeClass) ||
			LongAttribute.class.equals(typeClass) ||
			DoubleAttribute.class.equals(typeClass) ||
			BooleanAttribute.class.equals(typeClass) ||
			DateAttribute.class.equals(typeClass) ||
			StringFunction.class.isAssignableFrom(typeClass))
		{
			new CopeNativeAttribute(
				ja, typeClass,
				initializerArguments, setterOptionString, qualifiers);
		}
		else if(
			EnumerationAttribute.class.equals(typeClass)||
			ItemAttribute.class.equals(typeClass))
		{
			new CopeObjectAttribute(
				ja, typeClass,
				initializerArguments, setterOptionString, qualifiers);
		}
		else if(MediaAttribute.class.equals(typeClass))
		{
			new CopeMediaAttribute(
				ja, typeClass,
				initializerArguments, setterOptionString, qualifiers);
		}
		else
			throw new RuntimeException(typeClass.toString());
	}
	
	private final void handleUniqueConstraint(final JavaAttribute ja, final Class typeClass)
		throws InjectorParseException
	{
		final String type = ja.type;
		final JavaClass jc = ja.parent;
		final List initializerArguments = ja.getInitializerArguments();
		//System.out.println(initializerArguments);
		final CopeClass persistentClass = CopeClass.getCopeClass(jc);
		final ArrayList persistentAttributes = new ArrayList(initializerArguments.size());
		for(Iterator i = initializerArguments.iterator(); i.hasNext(); )
		{
			final String initializerArgument = (String)i.next();
			final CopeAttribute persistentAttribute = persistentClass.getCopeAttribute(initializerArgument);
			if(persistentAttribute==null)
				throw new InjectorParseException("attribute >"+initializerArgument+"< in unique constraint "+ja.name+" not found.");
			persistentAttributes.add(persistentAttribute);
		}
		persistentClass.makeUnique(
			new PersistentUniqueConstraint(ja,
				(CopeAttribute[])persistentAttributes.toArray(new CopeAttribute[persistentAttributes.size()])));
	}
	
	private final void handleQualifier(final JavaAttribute ja, final Class typeClass)
		throws InjectorParseException
	{
		final JavaClass jc = ja.parent;
		final CopeClass persistentClass = CopeClass.getCopeClass(jc);
		final List initializerArguments = ja.getInitializerArguments();
		//System.out.println("---------"+initializerArguments);
		new PersistentQualifier(persistentClass, initializerArguments);
	}

	private final void handleMediaVariant(final JavaAttribute ja, final Class typeClass)
		throws InjectorParseException
	{
		final JavaClass jc = ja.parent;
		final CopeClass persistentClass = CopeClass.getCopeClass(jc);
		final List initializerArguments = ja.getInitializerArguments();
		if(initializerArguments.size()!=1)
			throw new InjectorParseException("attribute >"+ja.name+"< has invalid initializer arguments: "+initializerArguments);
		//System.out.println("---------"+initializerArguments);
		final String initializerArgument = (String)initializerArguments.get(0);
		final CopeMediaAttribute mediaAttribute = (CopeMediaAttribute)persistentClass.getCopeAttribute(initializerArgument);
		if(mediaAttribute==null)
			throw new InjectorParseException("attribute >"+initializerArgument+"< in media attribute variant "+ja.name+" not found.");
		new PersistentMediaVariant(ja, mediaAttribute);
	}

	public void onClassFeature(final JavaFeature jf, final String docComment)
	throws IOException, InjectorParseException
	{
		//System.out.println("onClassFeature("+jf.getName()+" "+docComment+")");
		if(!class_state.isInterface() &&
			jf instanceof JavaAttribute &&
			!discardnextfeature)
		{
			final JavaAttribute ja = (JavaAttribute)jf;
			final int modifier = ja.modifier;

			if(Modifier.isFinal(modifier) && Modifier.isStatic(modifier))
			{
				Class typeClass = null;
				try
				{
					typeClass = ja.file.findType(ja.type);
				}
				catch(InjectorParseException e)
				{
				}

				if(typeClass!=null)
				{
					if(Function.class.isAssignableFrom(typeClass)||Attribute.class.isAssignableFrom(typeClass))
						handleAttribute(ja, typeClass, docComment);
					else if(UniqueConstraint.class.isAssignableFrom(typeClass))
						handleUniqueConstraint(ja, typeClass);
					else if(Qualifier.class.isAssignableFrom(typeClass))
						handleQualifier(ja, typeClass);
					else if(MediaAttributeVariant.class.isAssignableFrom(typeClass))
						handleMediaVariant(ja, typeClass);
				}
			}
		}
		discardnextfeature=false;
	}
	
	public boolean onDocComment(String docComment, final Writer output)
	throws IOException
	{
		//System.out.println("onDocComment("+docComment+")");

		if(docComment.indexOf(GENERATED)>=0 || docComment.indexOf("@author cope instrumentor")>=0)
		{
			discardnextfeature=true;
			return false;
		}
		else
		{
			output.write(docComment);
			return true;
		}
	}
	
	public void onFileDocComment(String docComment, final Writer output)
	throws IOException, InjectorParseException
	{
		//System.out.println("onFileDocComment("+docComment+")");
		
		output.write(docComment);
		
		if (class_state != null)
		{
			// handle doccomment immediately
			handleClassComment(class_state, docComment);
		}
		else
		{
			// remember to be handled as soon as we know what class we're talking about
			lastFileDocComment = docComment;
		}
	}
	
	private static final boolean containsTag(final String docComment, final String tagName)
	{
		return docComment!=null && docComment.indexOf('@'+tagName)>=0 ;
	}

}


