/*
 * Created on 22.04.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.exedio.cope.instrument;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.exedio.cope.lib.AttributeValue;
import com.exedio.cope.lib.LengthViolationException;
import com.exedio.cope.lib.NestingRuntimeException;
import com.exedio.cope.lib.NotNullViolationException;
import com.exedio.cope.lib.ReadOnlyViolationException;
import com.exedio.cope.lib.Type;
import com.exedio.cope.lib.UniqueViolationException;
import com.exedio.cope.lib.util.ReactivationConstructorDummy;

final class Generator
{
	private static final String CONSTRUCTOR_NEW = "Constructs a new {0} with all the attributes initially needed.";
	private static final String CONSTRUCTOR_NEW_PARAMETER = "the initial value for attribute {0}.";
	private static final String CONSTRUCTOR_GENERIC = "Creates an item and sets the given attributes initially.";
	private static final String CONSTRUCTOR_REACTIVATION = "Reactivation constructor. Used for internal purposes only.";
	private static final String GETTER = "Returns the value of the persistent attribute {0}.";
	private static final String SETTER = "Sets a new value for the persistent attribute {0}.";
	private static final String SETTER_MEDIA = "Provides data for the persistent media attribute {0}.";
	private static final String GETTER_MEDIA_URL = "Returns a URL pointing to the data of the persistent attribute {0}.";
	private static final String GETTER_MEDIA_VARIANT = "Returns a URL pointing to the varied data of the persistent attribute {0}.";
	private static final String GETTER_MEDIA_MAJOR = "Returns the major mime type of the persistent media attribute {0}.";
	private static final String GETTER_MEDIA_MINOR = "Returns the minor mime type of the persistent media attribute {0}.";
	private static final String GETTER_MEDIA_DATA = "Returns a stream for fetching the data of the persistent media attribute {0}.";

	private final Writer o;
	private final String lineSeparator;
	
	Generator(final Writer output)
	{
		this.o=output;
		
		final String systemLineSeparator = System.getProperty("line.separator");
		if(systemLineSeparator==null)
		{
			System.out.println("warning: property \"line.separator\" is null, using LF (unix style).");
			lineSeparator = "\n";
		}
		else
			lineSeparator = systemLineSeparator;
	}

	public static final String toCamelCase(final String name)
	{
		final char first = name.charAt(0);
		if (Character.isUpperCase(first))
			return name;
		else
			return Character.toUpperCase(first) + name.substring(1);
	}

	private static final String lowerCamelCase(final String s)
	{
		final char first = s.charAt(0);
		if(Character.isLowerCase(first))
			return s;
		else
			return Character.toLowerCase(first) + s.substring(1);
	}
	
	private static final String getShortName(final Class aClass)
	{
		final String name = aClass.getName();
		final int pos = name.lastIndexOf('.');
		return name.substring(pos+1);
	}

	private void writeThrowsClause(final Collection exceptions)
	throws IOException
	{
		if(!exceptions.isEmpty())
		{
			o.write("\t\t\tthrows");
			boolean first = true;
			for(final Iterator i = exceptions.iterator(); i.hasNext(); )
			{
				if(first)
					first = false;
				else
					o.write(',');
				o.write(lineSeparator);
				o.write("\t\t\t\t");
				o.write(((Class)i.next()).getName());
			}
			o.write(lineSeparator);
		}
	}

	private final void writeCommentHeader()
	throws IOException
	{
		o.write("/**");
		o.write(lineSeparator);
		o.write(lineSeparator);
		o.write("\t **");
		o.write(lineSeparator);
	}

	private final void writeCommentGenerated()
	throws IOException
	{
		o.write("\t * <p><small>"+Instrumentor.GENERATED+"</small>");
		o.write(lineSeparator);
	}
	
	private final void writeCommentFooter()
	throws IOException
	{
		o.write("\t *");
		o.write(lineSeparator);
		o.write(" */");
	}
	
	private static final HashMap constraintViolationText = new HashMap(5);
	
	static
	{
		constraintViolationText.put(NotNullViolationException.class, "null");
		constraintViolationText.put(UniqueViolationException.class, "not unique");
	}
	
	private static final String link(final String target)
	{
		return "{@link #" + target + "}";
	}
	
	private static final String format(final String pattern, final String parameter1)
	{
		return MessageFormat.format(pattern, new Object[]{ parameter1 });
	}

	private void writeConstructor(final CopeClass javaClass)
	throws IOException
	{
		if(!javaClass.hasGeneratedConstructor())
			return;

		final List initialAttributes = javaClass.getInitialAttributes();
		final SortedSet constructorExceptions = javaClass.getConstructorExceptions();
		
		writeCommentHeader();
		o.write("\t * ");
		o.write(format(CONSTRUCTOR_NEW, javaClass.getName()));
		o.write(lineSeparator);
		writeCommentGenerated();
		for(Iterator i = initialAttributes.iterator(); i.hasNext(); )
		{
			final CopeAttribute initialAttribute = (CopeAttribute)i.next();
			o.write("\t * @param initial");
			o.write(toCamelCase(initialAttribute.getName()));
			o.write(' ');
			o.write(format(CONSTRUCTOR_NEW_PARAMETER, link(initialAttribute.getName())));
			o.write(lineSeparator);
		}
		for(Iterator i = constructorExceptions.iterator(); i.hasNext(); )
		{
			final Class constructorException = (Class)i.next();
			o.write("\t * @throws ");
			o.write(constructorException.getName());
			o.write(" if");
			boolean first = true;
			for(Iterator j = initialAttributes.iterator(); j.hasNext(); )
			{
				final CopeAttribute initialAttribute = (CopeAttribute)j.next();
				if(!initialAttribute.getSetterExceptions().contains(constructorException))
					continue;

				if(first)
					first = false;
				else
					o.write(',');
				o.write(" initial");
				o.write(toCamelCase(initialAttribute.getName()));
			}
			o.write(" is ");
			final String violationText = (String)constraintViolationText.get(constructorException);
			if(violationText==null)
				throw new RuntimeException(constructorException.getName());
			o.write(violationText);
			o.write('.');
			o.write(lineSeparator);
		}
		writeCommentFooter();
		final String modifier = Modifier.toString(javaClass.getGeneratedConstructorModifier());
		if(modifier.length()>0)
		{
			o.write(modifier);
			o.write(' ');
		}
		o.write(javaClass.getName());
		o.write('(');
		
		boolean first = true;
		for(Iterator i = initialAttributes.iterator(); i.hasNext(); )
		{
			if(first)
				first = false;
			else
				o.write(',');
			final CopeAttribute initialAttribute = (CopeAttribute)i.next();
			o.write(lineSeparator);
			o.write("\t\t\t\tfinal ");
			o.write(initialAttribute.getBoxedType());
			o.write(" initial");
			o.write(toCamelCase(initialAttribute.getName()));
		}
		
		o.write(')');
		o.write(lineSeparator);
		writeThrowsClause(constructorExceptions);
		o.write("\t{");
		o.write(lineSeparator);
		o.write("\t\tsuper(new "+AttributeValue.class.getName()+"[]{");
		o.write(lineSeparator);
		for(Iterator i = initialAttributes.iterator(); i.hasNext(); )
		{
			final CopeAttribute initialAttribute = (CopeAttribute)i.next();
			o.write("\t\t\tnew "+AttributeValue.class.getName()+"(");
			o.write(initialAttribute.getName());
			o.write(',');
			writePrefixedAttribute("initial", initialAttribute);
			o.write("),");
			o.write(lineSeparator);
		}
		o.write("\t\t});");
		o.write(lineSeparator);
		for(Iterator i = javaClass.getConstructorExceptions().iterator(); i.hasNext(); )
		{
			final Class exception = (Class)i.next();
			o.write("\t\tthrowInitial");
			o.write(getShortName(exception));
			o.write("();");
			o.write(lineSeparator);
		}
		o.write("\t}");
	}
	
	private void writeGenericConstructor(final CopeClass copeClass)
	throws IOException
	{
		writeCommentHeader();
		o.write("\t * ");
		o.write(CONSTRUCTOR_GENERIC);
		o.write(lineSeparator);
		writeCommentGenerated();
		writeCommentFooter();
		o.write("protected ");
		o.write(copeClass.getName());
		o.write("(final "+AttributeValue.class.getName()+"[] initialAttributes)");
		o.write(lineSeparator);
		o.write("\t{");
		o.write(lineSeparator);
		o.write("\t\tsuper(initialAttributes);");
		o.write(lineSeparator);
		o.write("\t}");
	}
	
	private void writeReactivationConstructor(final CopeClass copeClass)
	throws IOException
	{
		final boolean abstractClass = copeClass.isAbstract();
		writeCommentHeader();
		o.write("\t * ");
		o.write(CONSTRUCTOR_REACTIVATION);
		o.write(lineSeparator);
		writeCommentGenerated();
		o.write("\t * @see Item#Item("
			+ ReactivationConstructorDummy.class.getName() + ",int)");
		o.write(lineSeparator);
		writeCommentFooter();
		o.write( abstractClass ? "protected " : "private " );
		o.write(copeClass.getName());
		o.write("("+ReactivationConstructorDummy.class.getName()+" d,final int pk)");
		o.write(lineSeparator);
		o.write("\t{");
		o.write(lineSeparator);
		o.write("\t\tsuper(d,pk);");
		o.write(lineSeparator);
		o.write("\t}");
	}
	
	private void writeAccessMethods(final CopeAttribute copeAttribute)
	throws IOException
	{
		final String type = copeAttribute.getBoxedType();

		// getter
		writeCommentHeader();
		o.write("\t * ");
		o.write(format(GETTER, link(copeAttribute.getName())));
		o.write(lineSeparator);
		writeCommentGenerated();
		writeCommentFooter();
		o.write(Modifier.toString(copeAttribute.getGeneratedGetterModifier()));
		o.write(' ');
		o.write(type);
		o.write(" get");
		o.write(toCamelCase(copeAttribute.getName()));
		o.write("()");
		o.write(lineSeparator);
		o.write("\t{");
		o.write(lineSeparator);
		writeGetterBody(copeAttribute);
		o.write("\t}");
		
		// setter
		if(copeAttribute.hasGeneratedSetter())
		{
			writeCommentHeader();
			o.write("\t * ");
			o.write(format(SETTER, link(copeAttribute.getName())));
			o.write(lineSeparator);
			writeCommentGenerated();
			writeCommentFooter();
			o.write(Modifier.toString(copeAttribute.getGeneratedSetterModifier()));
			o.write(" void set");
			o.write(toCamelCase(copeAttribute.getName()));
			o.write("(final ");
			o.write(type);
			o.write(' ');
			o.write(copeAttribute.getName());
			o.write(')');
			o.write(lineSeparator);
			writeThrowsClause(copeAttribute.getSetterExceptions());
			o.write("\t{");
			o.write(lineSeparator);
			writeSetterBody(copeAttribute);
			o.write("\t}");
		}
	}

	private void writeMediaGetterMethod(final CopeAttribute mediaAttribute,
													final Class returnType,
													final String part,
													final CopeMediaVariant variant,
													final String commentPattern)
	throws IOException
	{
		writeCommentHeader();
		o.write("\t * ");
		o.write(format(commentPattern, link(mediaAttribute.getName())));
		o.write(lineSeparator);
		writeCommentGenerated();
		writeCommentFooter();
		o.write(Modifier.toString(mediaAttribute.getGeneratedGetterModifier()));
		o.write(' ');
		o.write(returnType.getName());
		o.write(" get");
		o.write(toCamelCase(mediaAttribute.getName()));
		o.write(part);
		if(variant!=null)
		{
			final String prefix = mediaAttribute.getName();
			if(variant.name.startsWith(prefix))
				o.write(variant.name.substring(prefix.length()));
			else
				o.write(variant.name);
		}
		o.write("()");
		o.write(lineSeparator);
		o.write("\t{");
		o.write(lineSeparator);
		o.write("\t\treturn getMedia");
		o.write(part);
		o.write('(');
		o.write(mediaAttribute.copeClass.getName());
		o.write('.');
		if(variant!=null)
			o.write(variant.name);
		else
			o.write(mediaAttribute.getName());
		o.write(");");
		o.write(lineSeparator);
		o.write("\t}");
	}
	
	private void writeMediaAccessMethods(final CopeMediaAttribute mediaAttribute)
	throws IOException
	{
		final String mimeMajor = mediaAttribute.mimeMajor;
		final String mimeMinor = mediaAttribute.mimeMinor;

		// getters
		writeMediaGetterMethod(mediaAttribute, String.class, "URL", null, GETTER_MEDIA_URL);
		final List mediaVariants = mediaAttribute.getVariants();
		if(mediaVariants!=null)
		{
			for(Iterator i = mediaVariants.iterator(); i.hasNext(); )
				writeMediaGetterMethod(mediaAttribute, String.class, "URL", (CopeMediaVariant)i.next(), GETTER_MEDIA_VARIANT);
		}
		writeMediaGetterMethod(mediaAttribute, String.class, "MimeMajor", null, GETTER_MEDIA_MAJOR);
		writeMediaGetterMethod(mediaAttribute, String.class, "MimeMinor", null, GETTER_MEDIA_MINOR);
		writeMediaGetterMethod(mediaAttribute, InputStream.class, "Data", null, GETTER_MEDIA_DATA);
		
		// setters
		if(mediaAttribute.hasGeneratedSetter())
		{
			writeCommentHeader();
			o.write("\t * ");
			o.write(format(SETTER_MEDIA, link(mediaAttribute.getName())));
			o.write(lineSeparator);
			writeCommentGenerated();
			writeCommentFooter();
			o.write(Modifier.toString(mediaAttribute.getGeneratedSetterModifier()));
			o.write(" void set");
			o.write(toCamelCase(mediaAttribute.getName()));
			o.write("Data(final " + InputStream.class.getName() + " data");
			if(mimeMajor==null)
				o.write(",final "+String.class.getName()+" mimeMajor");
			if(mimeMinor==null)
				o.write(",final "+String.class.getName()+" mimeMinor");
			o.write(')');
			final SortedSet setterExceptions = mediaAttribute.getSetterExceptions();
			writeThrowsClause(setterExceptions);
			if(setterExceptions.isEmpty())
				o.write("throws ");
			o.write(IOException.class.getName());
			o.write(lineSeparator);
			o.write("\t{");
			o.write(lineSeparator);
			
			final SortedSet exceptionsToCatch = new TreeSet(mediaAttribute.getExceptionsToCatchInSetter());
			exceptionsToCatch.remove(ReadOnlyViolationException.class);
			exceptionsToCatch.remove(LengthViolationException.class);
			exceptionsToCatch.remove(UniqueViolationException.class);
			if(!exceptionsToCatch.isEmpty())
			{
				o.write("\t\ttry");
				o.write(lineSeparator);
				o.write("\t\t{");
				o.write(lineSeparator);
				o.write('\t');
			}
			o.write("\t\tsetMediaData(");
			o.write(mediaAttribute.copeClass.getName());
			o.write('.');
			o.write(mediaAttribute.getName());
			o.write(",data");
			o.write(mimeMajor==null ? ",mimeMajor" : ",null");
			o.write(mimeMinor==null ? ",mimeMinor" : ",null");
			o.write(");");
			o.write(lineSeparator);
			if(!exceptionsToCatch.isEmpty())
			{
				o.write("\t\t}");
				o.write(lineSeparator);

				for(Iterator i = exceptionsToCatch.iterator(); i.hasNext(); )
					writeViolationExceptionCatchClause((Class)i.next());
			}
			o.write("\t}");
		}
	}
	
	private void writeUniqueFinder(final CopeUniqueConstraint constraint)
	throws IOException
	{
		final CopeAttribute[] copeAttributes = constraint.copeAttributes;
		final String className = copeAttributes[0].getParent().name;
		
		writeCommentHeader();
		o.write("\t * Finds a ");
		o.write(lowerCamelCase(className));
		o.write(" by it's unique attributes.");
		o.write(lineSeparator);
		writeCommentGenerated();
		for(int i=0; i<copeAttributes.length; i++)
		{
			o.write("\t * @param searched");
			o.write(toCamelCase(copeAttributes[i].getName()));
			o.write(" shall be equal to attribute {@link #");
			o.write(copeAttributes[i].getName());
			o.write("}.");
			o.write(lineSeparator);
		}
		writeCommentFooter();
		o.write(Modifier.toString((constraint.modifier & (Modifier.PRIVATE|Modifier.PROTECTED|Modifier.PUBLIC)) | (Modifier.STATIC|Modifier.FINAL) ));
		o.write(' ');
		o.write(className);
		o.write(" findBy");
		o.write(toCamelCase(constraint.name));
		
		o.write('(');
		final Set qualifiers = new HashSet();
		for(int i=0; i<copeAttributes.length; i++)
		{
			if(i>0)
				o.write(',');
			final CopeAttribute copeAttribute = copeAttributes[i];
			o.write("final ");
			o.write(copeAttribute.getBoxedType());
			o.write(" searched");
			o.write(toCamelCase(copeAttribute.getName()));
		}
		o.write(')');
		o.write(lineSeparator);
		o.write("\t{");
		o.write(lineSeparator);
		o.write("\t\treturn (");
		o.write(className);
		o.write(')');

		if(copeAttributes.length==1)
		{
			o.write(copeAttributes[0].getName());
			o.write(".searchUnique(");
			writePrefixedAttribute("searched", copeAttributes[0]);
		}
		else
		{
			o.write(constraint.name);
			o.write(".searchUnique(new Object[]{");
			writePrefixedAttribute("searched", copeAttributes[0]);
			for(int i = 1; i<copeAttributes.length; i++)
			{
				o.write(',');
				writePrefixedAttribute("searched", copeAttributes[i]);
			}
			o.write('}');
		}
		
		o.write(");");
		o.write(lineSeparator);
		o.write("\t}");
	}
	
	private void writePrefixedAttribute(final String prefix, final CopeAttribute attribute)
			throws IOException
	{
		if(attribute.isBoxed())
			o.write(attribute.getBoxingPrefix());
		o.write(prefix);
		o.write(toCamelCase(attribute.getName()));
		if(attribute.isBoxed())
			o.write(attribute.getBoxingPostfix());
	}
	
	private void writeQualifier(final CopeQualifier qualifier)
	throws IOException
	{
		writeCommentHeader();
		o.write("\t * Returns the qualifier.");
		o.write(lineSeparator);
		writeCommentGenerated();
		writeCommentFooter();

		o.write("final ");
		o.write(qualifier.qualifierClassString);
		o.write(" get");
		o.write(toCamelCase(qualifier.name));
		o.write("(final ");
		o.write(qualifier.keyAttribute.persistentType);
		o.write(' ');
		o.write(qualifier.keyAttribute.javaAttribute.name);
		o.write(')');
		o.write(lineSeparator);

		o.write("\t{");
		o.write(lineSeparator);

		o.write("\t\treturn (");
		o.write(qualifier.qualifierClassString);
		o.write(")");
		o.write(qualifier.uniqueConstraintString);
		o.write(".searchUnique(new Object[]{this,");
		o.write(qualifier.keyAttribute.javaAttribute.name);
		o.write("});");
		o.write(lineSeparator);

		o.write("\t}");
		
		final List qualifierAttributes = Arrays.asList(qualifier.uniqueConstraint.copeAttributes);
		for(Iterator i = qualifier.qualifierClass.getCopeAttributes().iterator(); i.hasNext(); )
		{
			final CopeAttribute attribute = (CopeAttribute)i.next();
			if(qualifierAttributes.contains(attribute))
				continue;
			writeQualifierGetter(qualifier, attribute);
			writeQualifierSetter(qualifier, attribute);
		}
	}

	private void writeQualifierGetter(final CopeQualifier qualifier, final CopeAttribute attribute)
	throws IOException
	{
		writeCommentHeader();
		o.write("\t * Returns the qualifier.");
		o.write(lineSeparator);
		writeCommentGenerated();
		writeCommentFooter();

		final String resultType = attribute.persistentType;
		o.write("final ");
		o.write(resultType);
		o.write(" get");
		o.write(toCamelCase(attribute.getName()));
		o.write("(final ");
		o.write(qualifier.keyAttribute.persistentType);
		o.write(' ');
		o.write(qualifier.keyAttribute.javaAttribute.name);
		o.write(')');
		o.write(lineSeparator);

		o.write("\t{");
		o.write(lineSeparator);

		o.write("\t\treturn (");
		o.write(resultType);
		o.write(')');
		o.write(qualifier.uniqueConstraintString);
		o.write(".getQualified(new Object[]{this,");
		o.write(qualifier.keyAttribute.javaAttribute.name);
		o.write("},");
		o.write(qualifier.qualifierClassString);
		o.write('.');
		o.write(attribute.getName());
		o.write(");");
		o.write(lineSeparator);

		o.write("\t}");
	}

	private void writeQualifierSetter(final CopeQualifier qualifier, final CopeAttribute attribute)
	throws IOException
	{
		writeCommentHeader();
		o.write("\t * Sets the qualifier.");
		o.write(lineSeparator);
		writeCommentGenerated();
		writeCommentFooter();

		final String resultType = attribute.persistentType;
		o.write("final void set");
		o.write(toCamelCase(attribute.getName()));
		o.write("(final ");
		o.write(qualifier.keyAttribute.persistentType);
		o.write(' ');
		o.write(qualifier.keyAttribute.javaAttribute.name);
		o.write(",final ");
		o.write(resultType);
		o.write(' ');
		o.write(attribute.getName());
		o.write(')');
		o.write(lineSeparator);
		
		writeThrowsClause(Arrays.asList(new Class[]{
			NotNullViolationException.class,
			LengthViolationException.class,
			ReadOnlyViolationException.class,
			ClassCastException.class}));

		o.write("\t{");
		o.write(lineSeparator);

		o.write("\t\t");
		o.write(qualifier.uniqueConstraintString);
		o.write(".setQualified(new Object[]{this,");
		o.write(qualifier.keyAttribute.javaAttribute.name);
		o.write("},");
		o.write(qualifier.qualifierClassString);
		o.write('.');
		o.write(attribute.getName());
		o.write(',');
		o.write(attribute.getName());
		o.write(");");
		o.write(lineSeparator);

		o.write("\t}");
	}

	private final void writeType(final CopeClass copeClass)
	throws IOException
	{
		writeCommentHeader();
		o.write("\t * The persistent type information for ");
		o.write(lowerCamelCase(copeClass.getName()));
		o.write(".");
		o.write(lineSeparator);
		writeCommentGenerated();
		writeCommentFooter();
		
		o.write("public static final "+Type.class.getName()+" TYPE = ");
		o.write(lineSeparator);

		o.write("\t\tnew "+Type.class.getName()+"(");
		o.write(copeClass.getName());
		o.write(".class)");
		o.write(lineSeparator);

		o.write(";");
	}

	void writeClassFeatures(final CopeClass copeClass)
			throws IOException, InjectorParseException
	{
		if(!copeClass.isInterface())
		{
			//System.out.println("onClassEnd("+jc.getName()+") writing");
			writeConstructor(copeClass);
			writeGenericConstructor(copeClass);
			writeReactivationConstructor(copeClass);
			for(final Iterator i = copeClass.getCopeAttributes().iterator(); i.hasNext(); )
			{
				// write setter/getter methods
				final CopeAttribute copeAttribute = (CopeAttribute)i.next();
				//System.out.println("onClassEnd("+jc.getName()+") writing attribute "+copeAttribute.getName());
				if(copeAttribute instanceof CopeMediaAttribute)
					writeMediaAccessMethods((CopeMediaAttribute)copeAttribute);
				else
					writeAccessMethods(copeAttribute);
			}
			for(final Iterator i = copeClass.getUniqueConstraints().iterator(); i.hasNext(); )
			{
				// write unique finder methods
				final CopeUniqueConstraint constraint = (CopeUniqueConstraint)i.next();
				writeUniqueFinder(constraint);
			}
			for(final Iterator i = copeClass.getQualifiers().iterator(); i.hasNext(); )
			{
				final CopeQualifier qualifier = (CopeQualifier)i.next();
				writeQualifier(qualifier);
			}
			writeType(copeClass);
		}
	}

	// ----------------- methods for a new interface abstracting the persistence
	// ----------------- implementation used, e.g. EJB.

	/**
	 * Identation contract:
	 * This methods is called, when o stream is immediatly after a line break,
	 * and it should return the o stream after immediatly after a line break.
	 * This means, doing nothing fullfils the contract.
	 */
	private void writeGetterBody(final CopeAttribute attribute)
	throws IOException
	{
		o.write("\t\treturn ");
		if(attribute.isBoxed())
			o.write(attribute.getUnBoxingPrefix());
		o.write('(');
		o.write(attribute.persistentType);
		o.write(")getAttribute(");
		o.write(attribute.copeClass.getName());
		o.write('.');
		o.write(attribute.getName());
		o.write(')');
		if(attribute.isBoxed())
			o.write(attribute.getUnBoxingPostfix());
		o.write(';');
		o.write(lineSeparator);
	}

	/**
	 * Identation contract:
	 * This methods is called, when o stream is immediatly after a line break,
	 * and it should return the o stream after immediatly after a line break.
	 * This means, doing nothing fullfils the contract.
	 */
	private void writeSetterBody(final CopeAttribute attribute)
	throws IOException
	{
		final SortedSet exceptionsToCatch = attribute.getExceptionsToCatchInSetter();

		if(!exceptionsToCatch.isEmpty())
		{
			o.write("\t\ttry");
			o.write(lineSeparator);
			o.write("\t\t{");
			o.write(lineSeparator);
			o.write('\t');
		}
		o.write("\t\tsetAttribute(");
		o.write(attribute.copeClass.getName());
		o.write('.');
		o.write(attribute.getName());
		o.write(',');
		if(attribute.isBoxed())
			o.write(attribute.getBoxingPrefix());
		o.write(attribute.getName());
		if(attribute.isBoxed())
			o.write(attribute.getBoxingPostfix());
		o.write(");");
		o.write(lineSeparator);
		if(!exceptionsToCatch.isEmpty())
		{
			o.write("\t\t}");
			o.write(lineSeparator);
			
			for(Iterator i = exceptionsToCatch.iterator(); i.hasNext(); )
				writeViolationExceptionCatchClause((Class)i.next());
		}
	}
	
	private void writeViolationExceptionCatchClause(final Class exceptionClass)
	throws IOException
	{
		o.write("\t\tcatch("+exceptionClass.getName()+" e)");
		o.write(lineSeparator);
		o.write("\t\t{");
		o.write(lineSeparator);
		o.write("\t\t\tthrow new "+NestingRuntimeException.class.getName()+"(e);");
		o.write(lineSeparator);
		o.write("\t\t}");
		o.write(lineSeparator);
	}

}
