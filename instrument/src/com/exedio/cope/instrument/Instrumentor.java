
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
import com.exedio.cope.lib.DoubleAttribute;
import com.exedio.cope.lib.EnumerationAttribute;
import com.exedio.cope.lib.IntegerAttribute;
import com.exedio.cope.lib.ItemAttribute;
import com.exedio.cope.lib.MediaAttribute;
import com.exedio.cope.lib.StringAttribute;
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
	 * Tag name for mapped attributes.
	 */
	private static final String MAPPED_ATTRIBUTE = "mapped";
	
	/**
	 * Tag name for one qualifier of qualified attributes.
	 */
	private static final String ATTRIBUTE_QUALIFIER = "qualifier";
	
	/**
	 * Tag name for one variant of media attributes.
	 */
	private static final String VARIANT_MEDIA_ATTRIBUTE = "variant";
	
	/**
	 * All generated class features get this doccomment tag.
	 */
	static final String GENERATED = "Generated by the cope instrumentor.";

	private void handleClassComment(final JavaClass jc, final String docComment)
	{
		if(containsTag(docComment, PERSISTENT_CLASS))
			new PersistentClass(jc);
	}
	
	public void onClass(final JavaClass jc)
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
		final PersistentClass persistentClass = PersistentClass.getPersistentClass(javaClass);

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
		final String type = ja.getType();
		final List initializerArguments = ja.getInitializerArguments();
		//System.out.println(initializerArguments);
					
		final boolean mapped = containsTag(docComment, MAPPED_ATTRIBUTE);
					
		final String qualifier = Injector.findDocTag(docComment, ATTRIBUTE_QUALIFIER);
		final List qualifiers;
		if(qualifier!=null)
			qualifiers = Collections.singletonList(qualifier);
		else
			qualifiers = null;
	
		if(
			IntegerAttribute.class.equals(typeClass) ||
			DoubleAttribute.class.equals(typeClass) ||
			BooleanAttribute.class.equals(typeClass) ||
			StringAttribute.class.equals(typeClass))
		{
			new PersistentNativeAttribute(
				ja, typeClass,
				initializerArguments, mapped, qualifiers);
		}
		else if(
			EnumerationAttribute.class.equals(typeClass)||
			ItemAttribute.class.equals(typeClass))
		{
			new PersistentObjectAttribute(
				ja,
				initializerArguments, mapped, qualifiers);
		}
		else if(MediaAttribute.class.equals(typeClass))
		{
			final String variant = Injector.findDocTag(docComment, VARIANT_MEDIA_ATTRIBUTE);
			final List variants;
			if(variant!=null)
				variants = Collections.singletonList(variant);
			else
				variants = null;
		
			new PersistentMediaAttribute(
				ja,
				initializerArguments, mapped, qualifiers,
				variants);
		}
		else
			throw new RuntimeException(typeClass.toString());
	}
	
	private final void handleUniqueConstraint(final JavaAttribute ja, final Class typeClass)
		throws InjectorParseException
	{
		final String type = ja.getType();
		final JavaClass jc = ja.getParent();
		final List initializerArguments = ja.getInitializerArguments();
		//System.out.println(initializerArguments);
		final PersistentClass persistentClass = PersistentClass.getPersistentClass(jc);
		final ArrayList persistentAttributes = new ArrayList(initializerArguments.size());
		for(Iterator i = initializerArguments.iterator(); i.hasNext(); )
		{
			final String initializerArgument = (String)i.next();
			final PersistentAttribute persistentAttribute = persistentClass.getPersistentAttribute(initializerArgument);
			if(persistentAttribute==null)
				throw new InjectorParseException("attribute >"+initializerArgument+"< in unique constraint "+ja.getName()+" not found.");
			persistentAttributes.add(persistentAttribute);
		}
		persistentClass.makeUnique(
			new PersistentUniqueConstraint(ja,
				(PersistentAttribute[])persistentAttributes.toArray(new PersistentAttribute[persistentAttributes.size()])));
	}
	
	private final void handleQualifier(final JavaAttribute ja, final Class typeClass)
		throws InjectorParseException
	{
		final JavaClass jc = ja.getParent();
		final PersistentClass persistentClass = PersistentClass.getPersistentClass(jc);
		final List initializerArguments = ja.getInitializerArguments();
		//System.out.println("---------"+initializerArguments);
		new PersistentQualifier(persistentClass, initializerArguments);
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
			final int modifier = ja.getModifiers();

			if(Modifier.isFinal(modifier) && Modifier.isStatic(modifier))
			{
				Class typeClass = null;
				try
				{
					typeClass = ja.getFile().findType(ja.getType());
				}
				catch(InjectorParseException e)
				{
				}

				if(typeClass!=null)
				{
					if(Attribute.class.isAssignableFrom(typeClass))
						handleAttribute(ja, typeClass, docComment);
					else if(UniqueConstraint.class.isAssignableFrom(typeClass))
						handleUniqueConstraint(ja, typeClass);
					else if(Qualifier.class.isAssignableFrom(typeClass))
						handleQualifier(ja, typeClass);
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
	throws IOException
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


