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

package com.exedio.cope.instrument;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

import com.exedio.cope.SetValue;
import com.exedio.cope.LengthViolationException;
import com.exedio.cope.MandatoryViolationException;
import com.exedio.cope.FinalViolationException;
import com.exedio.cope.Type;
import com.exedio.cope.UniqueViolationException;
import com.exedio.cope.util.ReactivationConstructorDummy;

final class Generator
{
	private static final String THROWS_NULL   = "if {0} is null.";
	private static final String THROWS_UNIQUE = "if {0} is not unique.";
	private static final String THROWS_LENGTH = "if {0} violates its length constraint.";
	private static final String CONSTRUCTOR_INITIAL = "Creates a new {0} with all the attributes initially needed.";
	private static final String CONSTRUCTOR_INITIAL_PARAMETER = "the initial value for attribute {0}.";
	private static final String CONSTRUCTOR_INITIAL_CUSTOMIZE = "It can be customized with the tags " +
																					"<tt>@"+Instrumentor.CLASS_INITIAL_CONSTRUCTOR+" public|package|protected|private|none</tt> " +
																					"in the class comment and " +
																					"<tt>@"+Instrumentor.ATTRIBUTE_INITIAL+"</tt> in the comment of attributes.";
	private static final String CONSTRUCTOR_GENERIC = "Creates a new {0} and sets the given attributes initially.";
	private static final String CONSTRUCTOR_GENERIC_CALLED = "This constructor is called by {0}.";
	private static final String CONSTRUCTOR_GENERIC_CUSTOMIZE = "It can be customized with the tag " +
																					"<tt>@"+Instrumentor.CLASS_GENERIC_CONSTRUCTOR+" public|package|protected|private|none</tt> " +
																					"in the class comment.";
	private static final String CONSTRUCTOR_REACTIVATION = "Reactivation constructor. Used for internal purposes only.";
	private static final String GETTER = "Returns the value of the persistent attribute {0}.";
	private static final String GETTER_CUSTOMIZE = "It can be customized with the tag " +
																  "<tt>@"+Instrumentor.ATTRIBUTE_GETTER+" public|package|protected|private|none|non-final|boolean-as-is</tt> " +
																  "in the comment of the attribute.";
	private static final String CHECKER = "Returns whether the given value corresponds to the hash in {0}.";
	private static final String SETTER = "Sets a new value for the persistent attribute {0}.";
	private static final String SETTER_CUSTOMIZE = "It can be customized with the tag " +
																  "<tt>@"+Instrumentor.ATTRIBUTE_SETTER+" public|package|protected|private|none|non-final</tt> " +
																  "in the comment of the attribute.";
	private static final String SETTER_MEDIA = "Sets the new data for the media {0}.";
	private static final String SETTER_MEDIA_IOEXCEPTION = "if accessing {0} throws an IOException.";
	private static final String GETTER_MEDIA_IS_NULL = "Returns whether this media {0} has data available.";
	private static final String GETTER_MEDIA_URL   = "Returns a URL the data of the media {0} is available under.";
	private static final String GETTER_MEDIA_CONTENT_TYPE = "Returns the content type of the media {0}.";
	private static final String GETTER_MEDIA_LENGTH = "Returns the data length of the media {0}.";
	private static final String GETTER_MEDIA_LASTMODIFIED = "Returns the last modification date of the media {0}.";
	private static final String GETTER_MEDIA_DATA_BYTE  = "Returns the data of the media {0}.";
	private static final String GETTER_MEDIA_DATA_STREAM  = "Reads data of media {0}, and writes it into the given stream.";
	private static final String GETTER_MEDIA_DATA_FILE = "Reads data of media {0}, and writes it into the given file.";
	private static final String GETTER_MEDIA_DATA_EXTRA = "Does nothing, if there is no data for the media.";
	private static final String GETTER_STREAM_WARNING  = "<b>You are responsible for closing the stream, when you are finished!</b>";
	private static final String TOUCHER = "Sets the current date for the date attribute {0}.";
	private static final String FINDER_UNIQUE = "Finds a {0} by it''s unique attributes.";
	private static final String FINDER_UNIQUE_PARAMETER = "shall be equal to attribute {0}.";
	private static final String FINDER_UNIQUE_RETURN = "null if there is no matching item.";
	private static final String QUALIFIER = "Returns the qualifier.";
	private static final String QUALIFIER_GETTER = "Returns the qualifier.";
	private static final String QUALIFIER_SETTER = "Sets the qualifier.";
	private static final String VECTOR_GETTER = "Returns the value of the vector.";
	private static final String VECTOR_SETTER = "Sets the vector.";
	private static final String TYPE = "The persistent type information for {0}.";
	private static final String TYPE_CUSTOMIZE = "It can be customized with the tag " +
																"<tt>@"+Instrumentor.CLASS_TYPE+" public|package|protected|private|none</tt> " +
																"in the class comment.";
	private static final String GENERATED = "This feature has been generated by the cope instrumentor and will be overwritten by the build process.";

	private final JavaFile javaFile;
	private final Writer o;
	private final CRC32 outputCRC = new CRC32();
	private final String lineSeparator;
	
	Generator(final JavaFile javaFile, final File outputFile) throws FileNotFoundException
	{
		this.javaFile = javaFile;
		this.o = new OutputStreamWriter(new CheckedOutputStream(new FileOutputStream(outputFile), outputCRC));
		
		final String systemLineSeparator = System.getProperty("line.separator");
		if(systemLineSeparator==null)
		{
			System.out.println("warning: property \"line.separator\" is null, using LF (unix style).");
			lineSeparator = "\n";
		}
		else
			lineSeparator = systemLineSeparator;
	}
	
	void close() throws IOException
	{
		if(o!=null)
			o.close();
	}
	
	long getCRC()
	{
		return outputCRC.getValue();
	}

	private static final String toCamelCase(final String name)
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

	private void writeThrowsClause(final Collection<Class> exceptions)
	throws IOException
	{
		if(!exceptions.isEmpty())
		{
			o.write("\t\t\tthrows");
			boolean first = true;
			for(final Class e : exceptions)
			{
				if(first)
					first = false;
				else
					o.write(',');
				o.write(lineSeparator);
				o.write("\t\t\t\t");
				o.write(e.getName());
			}
			o.write(lineSeparator);
		}
	}

	private void writeCommentHeader()
	throws IOException
	{
		o.write("/**");
		o.write(lineSeparator);
		o.write(lineSeparator);
		o.write("\t **");
		o.write(lineSeparator);
	}

	private void writeCommentFooter()
	throws IOException
	{
		writeCommentFooter(null);
	}
	
	private void writeCommentFooter(final String extraComment)
	throws IOException
	{
		o.write("\t * @"+Instrumentor.GENERATED+' ');
		o.write(GENERATED);
		o.write(lineSeparator);
		if(extraComment!=null)
		{
			o.write("\t *       ");
			o.write(extraComment);
			o.write(lineSeparator);
		}
		o.write("\t */");
		o.write(lineSeparator);
		o.write('\t'); // TODO put this into calling methods
	}
	
	private static final String link(final String target)
	{
		return "{@link #" + target + '}';
	}
	
	private static final String link(final String target, final String name)
	{
		return "{@link #" + target + ' ' + name + '}';
	}
	
	private static final String format(final String pattern, final String parameter1)
	{
		return MessageFormat.format(pattern, new Object[]{ parameter1 });
	}

	private static final String format(final String pattern, final String parameter1, final String parameter2)
	{
		return MessageFormat.format(pattern, new Object[]{ parameter1, parameter2 });
	}

	private void writeInitialConstructor(final CopeType type) 
	throws IOException
	{
		if(!type.hasInitialConstructor())
			return;

		final List<CopeAttribute> initialAttributes = type.getInitialAttributes();
		final SortedSet<Class> constructorExceptions = type.getConstructorExceptions();
		
		writeCommentHeader();
		o.write("\t * ");
		o.write(format(CONSTRUCTOR_INITIAL, type.name));
		o.write(lineSeparator);
		for(final CopeAttribute initialAttribute : initialAttributes)
		{
			o.write("\t * @param ");
			o.write(initialAttribute.name);
			o.write(' ');
			o.write(format(CONSTRUCTOR_INITIAL_PARAMETER, link(initialAttribute.name)));
			o.write(lineSeparator);
		}
		for(Iterator i = constructorExceptions.iterator(); i.hasNext(); )
		{
			final Class constructorException = (Class)i.next();
			o.write("\t * @throws ");
			o.write(constructorException.getName());
			o.write(' ');

			boolean first = true;
			final StringBuffer initialAttributesBuf = new StringBuffer();
			for(final CopeAttribute initialAttribute : initialAttributes)
			{
				if(!initialAttribute.getSetterExceptions().contains(constructorException))
					continue;

				if(first)
					first = false;
				else
					initialAttributesBuf.append(", ");
				initialAttributesBuf.append(initialAttribute.name);
			}

			final String pattern;
			if(MandatoryViolationException.class.equals(constructorException))
				pattern = THROWS_NULL;
			else if(UniqueViolationException.class.equals(constructorException))
				pattern = THROWS_UNIQUE;
			else if(LengthViolationException.class.equals(constructorException))
				pattern = THROWS_LENGTH;
			else
				throw new RuntimeException(constructorException.getName());

			o.write(format(pattern, initialAttributesBuf.toString()));
			o.write(lineSeparator);
		}
		writeCommentFooter(CONSTRUCTOR_INITIAL_CUSTOMIZE);
		writeModifier(type.getInitialConstructorModifier());
		o.write(type.name);
		o.write('(');
		
		boolean first = true;
		for(final CopeAttribute initialAttribute : initialAttributes)
		{
			if(first)
				first = false;
			else
				o.write(',');
			
			o.write(lineSeparator);
			o.write("\t\t\t\tfinal ");
			o.write(initialAttribute.getBoxedType());
			o.write(' ');
			o.write(initialAttribute.name);
		}
		
		o.write(')');
		o.write(lineSeparator);
		writeThrowsClause(constructorExceptions);
		o.write("\t{");
		o.write(lineSeparator);
		o.write("\t\tthis(new "+SetValue.class.getName()+"[]{");
		o.write(lineSeparator);
		for(final CopeAttribute initialAttribute : initialAttributes)
		{
			o.write("\t\t\t");
			o.write(type.name);
			o.write('.');
			o.write(initialAttribute.name);
			o.write(".map(");
			o.write(initialAttribute.name);
			o.write("),");
			o.write(lineSeparator);
		}
		o.write("\t\t});");
		o.write(lineSeparator);
		o.write("\t}");
	}
	
	private void writeGenericConstructor(final CopeType type)
	throws IOException
	{
		final Option option = type.genericConstructorOption;
		if(!option.exists)
			return;

		writeCommentHeader();
		o.write("\t * ");
		o.write(format(CONSTRUCTOR_GENERIC, type.name));
		o.write(lineSeparator);
		o.write("\t * ");
		o.write(format(CONSTRUCTOR_GENERIC_CALLED, "{@link " + Type.class.getName() + "#newItem Type.newItem}"));
		o.write(lineSeparator);
		writeCommentFooter(CONSTRUCTOR_GENERIC_CUSTOMIZE);
		writeModifier(option.getModifier(type.allowSubTypes() ? Modifier.PROTECTED : Modifier.PRIVATE));
		o.write(type.name);
		o.write("(final "+SetValue.class.getName()+"[] initialAttributes)");
		o.write(lineSeparator);
		o.write("\t{");
		o.write(lineSeparator);
		o.write("\t\tsuper(initialAttributes);");
		o.write(lineSeparator);
		o.write("\t}");
	}
	
	private void writeReactivationConstructor(final CopeType type)
	throws IOException
	{
		final Option option = type.reactivationConstructorOption;
		if(!option.exists)
			return;

		writeCommentHeader();
		o.write("\t * ");
		o.write(CONSTRUCTOR_REACTIVATION);
		o.write(lineSeparator);
		o.write("\t * @see Item#Item("
			+ ReactivationConstructorDummy.class.getName() + ",int)");
		o.write(lineSeparator);
		writeCommentFooter();
		writeModifier(option.getModifier(type.allowSubTypes() ? Modifier.PROTECTED : Modifier.PRIVATE));
		o.write(type.name);
		o.write("("+ReactivationConstructorDummy.class.getName()+" d,final int pk)");
		o.write(lineSeparator);
		o.write("\t{");
		o.write(lineSeparator);
		o.write("\t\tsuper(d,pk);");
		o.write(lineSeparator);
		o.write("\t}");
	}
	
	private void writeAccessMethods(final CopeAttribute attribute)
	throws InjectorParseException, IOException
	{
		final String type = attribute.getBoxedType();

		// getter
		if(attribute.getterOption.exists)
		{
			writeCommentHeader();
			o.write("\t * ");
			o.write(format(GETTER, link(attribute.name)));
			o.write(lineSeparator);
			writeStreamWarning(type);
			writeCommentFooter(GETTER_CUSTOMIZE);
			writeModifier(attribute.getGeneratedGetterModifier());
			o.write(type);
			if(attribute.hasIsGetter())
				o.write(" is");
			else
				o.write(" get");
			o.write(toCamelCase(attribute.name));
			o.write(attribute.getterOption.suffix);
			o.write("()");
			o.write(lineSeparator);
			o.write("\t{");
			o.write(lineSeparator);
			writeGetterBody(attribute);
			o.write("\t}");
		}
		
		// setter
		if(attribute.hasGeneratedSetter())
		{
			writeCommentHeader();
			o.write("\t * ");
			o.write(format(SETTER, link(attribute.name)));
			o.write(lineSeparator);
			writeCommentFooter(SETTER_CUSTOMIZE);
			writeModifier(attribute.getGeneratedSetterModifier());
			o.write("void set");
			o.write(toCamelCase(attribute.name));
			o.write(attribute.setterOption.suffix);
			o.write("(final ");
			o.write(type);
			o.write(' ');
			o.write(attribute.name);
			o.write(')');
			o.write(lineSeparator);
			writeThrowsClause(attribute.getSetterExceptions());
			o.write("\t{");
			o.write(lineSeparator);
			writeSetterBody(attribute);
			o.write("\t}");
			
			// touch for date attributes
			if(attribute.isTouchable())
			{
				writeCommentHeader();
				o.write("\t * ");
				o.write(format(TOUCHER, link(attribute.name)));
				o.write(lineSeparator);
				writeCommentFooter();
				writeModifier(attribute.getGeneratedSetterModifier());
				o.write("void touch");
				o.write(toCamelCase(attribute.name));
				o.write("()");
				o.write(lineSeparator);
				writeThrowsClause(attribute.getToucherExceptions());
				o.write("\t{");
				o.write(lineSeparator);
				writeToucherBody(attribute);
				o.write("\t}");
			}
		}
		
		writeUniqueFinder(attribute);
	}
	
	private void writeHash(final CopeHash hash)
	throws IOException, InjectorParseException
	{
		// checker
		writeCommentHeader();
		o.write("\t * ");
		o.write(format(CHECKER, link(hash.name)));
		o.write(lineSeparator);
		writeCommentFooter();
		writeModifier(hash.getGeneratedCheckerModifier());
		o.write("boolean check");
		o.write(toCamelCase(hash.name));
		o.write("(final ");
		o.write(String.class.getName());
		o.write(' ');
		o.write(hash.name);
		o.write(')');
		o.write(lineSeparator);
		o.write("\t{");
		o.write(lineSeparator);
		writeCheckerBody(hash);
		o.write("\t}");

		// setter
		writeCommentHeader();
		o.write("\t * ");
		o.write(format(SETTER, link(hash.name)));
		o.write(lineSeparator);
		writeCommentFooter();
		writeModifier(hash.getGeneratedSetterModifier());
		o.write("void set");
		o.write(toCamelCase(hash.name));
		o.write("(final ");
		o.write(String.class.getName());
		o.write(' ');
		o.write(hash.name);
		o.write(')');
		o.write(lineSeparator);
		writeThrowsClause(hash.getSetterExceptions());
		o.write("\t{");
		o.write(lineSeparator);
		writeSetterBody(hash);
		o.write("\t}");
	}
	
	private void writeMediaGetter(final CopeMedia media,
											final Class returnType,
											final String part,
											final String commentPattern)
	throws IOException
	{
		final String prefix = (boolean.class==returnType) ? "is" : "get";
		writeCommentHeader();
		o.write("\t * ");
		o.write(format(commentPattern, link(media.name)));
		o.write(lineSeparator);
		writeStreamWarning(returnType.getName());
		writeCommentFooter();
		writeModifier(media.getGeneratedGetterModifier());
		o.write(returnType.getName());
		if(returnType==byte.class)
			o.write("[]");
		o.write(' ');
		o.write(prefix);
		o.write(toCamelCase(media.name));
		o.write(part);
		o.write("()");
		o.write(lineSeparator);
		o.write("\t{");
		o.write(lineSeparator);
		o.write("\t\treturn ");
		o.write(media.type.name);
		o.write('.');
		o.write(media.name);
		o.write('.');
		o.write(prefix);
		o.write(part);
		o.write("(this);");
		o.write(lineSeparator);
		o.write("\t}");
	}
	
	private void writeMediaGetter(
			final CopeMedia media,
			final Class dataType,
			final String commentPattern)
	throws IOException
	{
		writeCommentHeader();
		o.write("\t * ");
		o.write(format(commentPattern, link(media.name)));
		o.write(lineSeparator);
		o.write("\t * ");
		o.write(GETTER_MEDIA_DATA_EXTRA);
		o.write(lineSeparator);
		o.write("\t * @throws ");
		o.write(IOException.class.getName());
		o.write(' ');
		o.write(format(SETTER_MEDIA_IOEXCEPTION, "<tt>data</tt>"));
		o.write(lineSeparator);
		writeCommentFooter();
		writeModifier(media.getGeneratedGetterModifier());
		o.write("void get");
		o.write(toCamelCase(media.name));
		o.write("Data(final " + dataType.getName() + " data)");
		o.write(lineSeparator);
		final TreeSet<Class> setterExceptions = new TreeSet<Class>();
		setterExceptions.addAll(Arrays.asList(new Class[]{IOException.class})); // TODO
		writeThrowsClause(setterExceptions);
		o.write("\t{");
		o.write(lineSeparator);
		o.write("\t\t");
		o.write(media.type.name);
		o.write('.');
		o.write(media.name);
		o.write(".getData(this,data);");
		o.write(lineSeparator);
		o.write("\t}");
	}
	
	private void writeMediaSetter(final CopeMedia media, final Class dataType)
	throws IOException
	{
		writeCommentHeader();
		o.write("\t * ");
		o.write(format(SETTER_MEDIA, link(media.name)));
		o.write(lineSeparator);
		o.write("\t * @throws ");
		o.write(IOException.class.getName());
		o.write(' ');
		o.write(format(SETTER_MEDIA_IOEXCEPTION, "<tt>data</tt>"));
		o.write(lineSeparator);
		writeCommentFooter();
		writeModifier(media.getGeneratedSetterModifier());
		o.write("void set");
		o.write(toCamelCase(media.name));
		o.write("(final ");
		o.write(dataType.getName());
		if(dataType==byte.class)
			o.write("[]");
		o.write(" data,final "+String.class.getName()+" contentType");
		o.write(')');
		final SortedSet<Class> setterExceptions = new TreeSet<Class>();
		setterExceptions.addAll(Arrays.asList(new Class[]{IOException.class})); // TODO
		o.write(lineSeparator);
		writeThrowsClause(setterExceptions);
		o.write("\t{");
		o.write(lineSeparator);
		
		o.write("\t\t");
		o.write(media.type.name);
		o.write('.');
		o.write(media.name);
		o.write(".set(this,data,contentType);");
		o.write(lineSeparator);
		o.write("\t}");
	}
	
	private void writeMedia(final CopeMedia media)
	throws IOException
	{
		writeMediaGetter(media, boolean.class,     "Null",         GETTER_MEDIA_IS_NULL);
		writeMediaGetter(media, String.class,      "URL",          GETTER_MEDIA_URL);
		writeMediaGetter(media, String.class,      "ContentType",  GETTER_MEDIA_CONTENT_TYPE);
		writeMediaGetter(media, long.class,        "LastModified", GETTER_MEDIA_LASTMODIFIED);
		writeMediaGetter(media, long.class,        "Length",       GETTER_MEDIA_LENGTH);
		writeMediaGetter(media, byte.class,        "Data",         GETTER_MEDIA_DATA_BYTE);
		writeMediaGetter(media, OutputStream.class,                GETTER_MEDIA_DATA_STREAM);
		writeMediaGetter(media, File.class,                        GETTER_MEDIA_DATA_FILE);

		if(media.setterOption.exists)
		{
			writeMediaSetter(media, byte.class);
			writeMediaSetter(media, InputStream.class);
			writeMediaSetter(media, File.class);
		}
	}
	
	private void writeUniqueFinder(final CopeAttribute attribute)
	throws IOException, InjectorParseException
	{
		if(!attribute.isImplicitlyUnique())
			return;
		
		final String className = attribute.getParent().name;
		
		writeCommentHeader();
		o.write("\t * ");
		o.write(format(FINDER_UNIQUE, lowerCamelCase(className)));
		o.write(lineSeparator);
		o.write("\t * @param ");
		o.write(attribute.name);
		o.write(' ');
		o.write(format(FINDER_UNIQUE_PARAMETER, link(attribute.name)));
		o.write(lineSeparator);
		o.write("\t * @return ");
		o.write(FINDER_UNIQUE_RETURN);
		o.write(lineSeparator);

		writeCommentFooter();
		writeModifier((attribute.modifier & (Modifier.PRIVATE|Modifier.PROTECTED|Modifier.PUBLIC)) | (Modifier.STATIC|Modifier.FINAL) );
		o.write(className);
		o.write(" findBy");
		o.write(toCamelCase(attribute.name));
		
		o.write("(final ");
		o.write(attribute.getBoxedType());
		o.write(' ');
		o.write(attribute.name);
		o.write(')');
		o.write(lineSeparator);
		o.write("\t{");
		o.write(lineSeparator);
		o.write("\t\treturn (");
		o.write(className);
		o.write(')');

		o.write(attribute.type.name);
		o.write('.');
		o.write(attribute.name);
		o.write(".searchUnique(");
		writeAttribute(attribute);
		
		o.write(");");
		o.write(lineSeparator);
		o.write("\t}");
	}
	
	private void writeUniqueFinder(final CopeUniqueConstraint constraint)
	throws IOException, InjectorParseException
	{
		final CopeAttribute[] attributes = constraint.getAttributes();
		final String className = attributes[0].getParent().name;
		
		writeCommentHeader();
		o.write("\t * ");
		o.write(format(FINDER_UNIQUE, lowerCamelCase(className)));
		o.write(lineSeparator);
		for(int i=0; i<attributes.length; i++)
		{
			o.write("\t * @param ");
			o.write(attributes[i].name);
			o.write(' ');
			o.write(format(FINDER_UNIQUE_PARAMETER, link(attributes[i].name)));
			o.write(lineSeparator);
		}
		o.write("\t * @return ");
		o.write(FINDER_UNIQUE_RETURN);
		o.write(lineSeparator);

		writeCommentFooter();
		writeModifier((constraint.modifier & (Modifier.PRIVATE|Modifier.PROTECTED|Modifier.PUBLIC)) | (Modifier.STATIC|Modifier.FINAL) );
		o.write(className);
		o.write(" findBy");
		o.write(toCamelCase(constraint.name));
		
		o.write('(');
		for(int i=0; i<attributes.length; i++)
		{
			if(i>0)
				o.write(',');
			final CopeAttribute attribute = attributes[i];
			o.write("final ");
			o.write(attribute.getBoxedType());
			o.write(' ');
			o.write(attribute.name);
		}
		o.write(')');
		o.write(lineSeparator);
		o.write("\t{");
		o.write(lineSeparator);
		o.write("\t\treturn (");
		o.write(className);
		o.write(')');

		o.write(attributes[0].type.name);
		o.write('.');
		o.write(constraint.name);
		o.write(".searchUnique(new Object[]{");
		writeAttribute(attributes[0]);
		for(int i = 1; i<attributes.length; i++)
		{
			o.write(',');
			writeAttribute(attributes[i]);
		}
		o.write("});");
		o.write(lineSeparator);
		o.write("\t}");
	}
	
	private void writeAttribute(final CopeAttribute attribute)
			throws IOException
	{
		if(attribute.isBoxed())
			o.write(attribute.getBoxingPrefix());
		o.write(attribute.name);
		if(attribute.isBoxed())
			o.write(attribute.getBoxingPostfix());
	}
	
	private void writeQualifierParameters(final CopeQualifier qualifier)
	throws IOException, InjectorParseException
	{
		final CopeAttribute[] keys = qualifier.getKeyAttributes();
		for(int i = 0; i<keys.length; i++)
		{
			if(i>0)
				o.write(',');
			o.write("final ");
			o.write(keys[i].persistentType);
			o.write(' ');
			o.write(keys[i].name);
		}
	}
	
	private void writeQualifierCall(final CopeQualifier qualifier)
	throws IOException, InjectorParseException
	{
		final CopeAttribute[] keys = qualifier.getKeyAttributes();
		for(int i = 0; i<keys.length; i++)
		{
			o.write(',');
			o.write(keys[i].name);
		}
	}
	
	private void writeQualifier(final CopeQualifier qualifier)
	throws IOException, InjectorParseException
	{
		writeCommentHeader();
		o.write("\t * ");
		o.write(QUALIFIER);
		o.write(lineSeparator);
		writeCommentFooter();

		o.write("public final "); // TODO: obey attribute visibility
		o.write(qualifier.qualifierClassString);
		o.write(" get");
		o.write(toCamelCase(qualifier.name));
		o.write('(');
		writeQualifierParameters(qualifier);
		o.write(')');
		o.write(lineSeparator);

		o.write("\t{");
		o.write(lineSeparator);

		o.write("\t\treturn (");
		o.write(qualifier.qualifierClassString);
		o.write(")");
		o.write(qualifier.name);
		o.write(".getQualifier(new Object[]{this");
		writeQualifierCall(qualifier);
		o.write("});");
		o.write(lineSeparator);

		o.write("\t}");
		
		final List qualifierAttributes = Arrays.asList(qualifier.getUniqueConstraint().getAttributes());
		for(final CopeFeature feature : qualifier.getQualifierClass().getFeatures())
		{
			if(feature instanceof CopeAttribute)
			{
				final CopeAttribute attribute = (CopeAttribute)feature;
				if(qualifierAttributes.contains(attribute))
					continue;
				writeQualifierGetter(qualifier, attribute);
				writeQualifierSetter(qualifier, attribute);
			}
		}
	}

	private void writeQualifierGetter(final CopeQualifier qualifier, final CopeAttribute attribute)
	throws IOException, InjectorParseException
	{
		if(attribute.getterOption.exists)
		{
			writeCommentHeader();
			o.write("\t * ");
			o.write(QUALIFIER_GETTER);
			o.write(lineSeparator);
			writeCommentFooter();
	
			final String resultType = attribute.persistentType;
			writeModifier(attribute.getGeneratedGetterModifier());
			o.write(resultType);
			o.write(" get");
			o.write(toCamelCase(attribute.name));
			o.write(attribute.getterOption.suffix);
			o.write('(');
			writeQualifierParameters(qualifier);
			o.write(')');
			o.write(lineSeparator);
	
			o.write("\t{");
			o.write(lineSeparator);
	
			o.write("\t\treturn (");
			o.write(resultType);
			o.write(')');
			o.write(qualifier.name);
			o.write(".get(new Object[]{this");
			writeQualifierCall(qualifier);
			o.write("},");
			o.write(qualifier.qualifierClassString);
			o.write('.');
			o.write(attribute.name);
			o.write(");");
			o.write(lineSeparator);
	
			o.write("\t}");
		}
	}

	private void writeQualifierSetter(final CopeQualifier qualifier, final CopeAttribute attribute)
	throws IOException, InjectorParseException
	{
		if(attribute.setterOption.exists)
		{
			writeCommentHeader();
			o.write("\t * ");
			o.write(QUALIFIER_SETTER);
			o.write(lineSeparator);
			writeCommentFooter();
	
			writeModifier(attribute.getGeneratedSetterModifier());
			o.write("void set");
			o.write(toCamelCase(attribute.name));
			o.write(attribute.setterOption.suffix);
			o.write('(');
			writeQualifierParameters(qualifier);
			o.write(",final ");
			o.write(attribute.getBoxedType());
			o.write(' ');
			o.write(attribute.name);
			o.write(')');
			o.write(lineSeparator);
			
			writeThrowsClause(attribute.getSetterExceptions());
	
			o.write("\t{");
			o.write(lineSeparator);
	
			o.write("\t\t");
			o.write(qualifier.qualifierClassString);
			o.write('.');
			o.write(attribute.name);
			o.write(".set(");
			o.write(qualifier.name);
			o.write(".getForSet(new Object[]{this");
			writeQualifierCall(qualifier);
			o.write("}),");
			o.write(attribute.name);
			o.write(");");
			o.write(lineSeparator);
			o.write("\t}");
		}
	}
	
	private void writeVector(final CopeVector vector)
		throws IOException
	{
		writeCommentHeader();
		o.write("\t * ");
		o.write(VECTOR_GETTER);
		o.write(lineSeparator);
		writeCommentFooter();

		o.write("public final "); // TODO: obey attribute visibility
		o.write(List.class.getName());
		o.write(" get");
		o.write(toCamelCase(vector.name));
		o.write("()");
		o.write(lineSeparator);

		o.write("\t{");
		o.write(lineSeparator);

		o.write("\t\treturn ");
		o.write(vector.type.name);
		o.write('.');
		o.write(vector.name);
		o.write(".get(this);");
		o.write(lineSeparator);

		o.write("\t}");

		writeCommentHeader();
		o.write("\t * ");
		o.write(VECTOR_SETTER);
		o.write(lineSeparator);
		writeCommentFooter();

		o.write("public final void set"); // TODO: obey attribute visibility
		o.write(toCamelCase(vector.name));
		o.write("(final ");
		o.write(Collection.class.getName());
		o.write(' ');
		o.write(vector.name);
		o.write(')');
		o.write(lineSeparator);

		writeThrowsClause(Arrays.asList(new Class[]{
				UniqueViolationException.class,
				MandatoryViolationException.class,
				LengthViolationException.class,
				FinalViolationException.class,
				ClassCastException.class}));

		o.write("\t{");
		o.write(lineSeparator);

		o.write("\t\t");
		o.write(vector.type.name);
		o.write('.');
		o.write(vector.name);
		o.write(".set(this,");
		o.write(vector.name);
		o.write(");");
		o.write(lineSeparator);

		o.write("\t}");
	}

	private void writeType(final CopeType type)
	throws IOException
	{
		final Option option = type.typeOption;
		if(option.exists)
		{
			writeCommentHeader();
			o.write("\t * ");
			o.write(format(TYPE, lowerCamelCase(type.name)));
			o.write(lineSeparator);
			writeCommentFooter(TYPE_CUSTOMIZE);
			
			writeModifier(option.getModifier(Modifier.PUBLIC) | Modifier.STATIC | Modifier.FINAL); // TODO obey class visibility
			o.write(Type.class.getName()+" TYPE =");
			o.write(lineSeparator);
	
			o.write("\t\tnew "+Type.class.getName()+"(");
			o.write(type.name);
			o.write(".class)");
			o.write(lineSeparator);
	
			o.write(";");
		}
	}
	
	void write() throws IOException, InjectorParseException
	{
		final String buffer = javaFile.buffer.toString();
		int previousClassEndPosition = 0;
		for(final JavaClass javaClass : javaFile.getClasses())
		{
			final CopeType type = CopeType.getCopeType(javaClass);
			final int classEndPosition = javaClass.getClassEndPosition();
			if(type!=null)
			{
				assert previousClassEndPosition<=classEndPosition;
				if(previousClassEndPosition<classEndPosition)
					o.write(buffer, previousClassEndPosition, classEndPosition-previousClassEndPosition);

				writeClassFeatures(type);
				previousClassEndPosition = classEndPosition;
			}
		}
		o.write(buffer, previousClassEndPosition, buffer.length()-previousClassEndPosition);
	}

	private void writeClassFeatures(final CopeType type)
			throws IOException, InjectorParseException
	{
		if(!type.isInterface())
		{
			writeInitialConstructor(type);
			writeGenericConstructor(type);
			writeReactivationConstructor(type);
			
			for(final CopeFeature feature : type.getFeatures())
			{
				if(feature instanceof CopeAttribute)
					writeAccessMethods((CopeAttribute)feature);
				else if(feature instanceof CopeUniqueConstraint)
					writeUniqueFinder((CopeUniqueConstraint)feature);
				else if(feature instanceof CopeQualifier)
					writeQualifier((CopeQualifier)feature);
				else if(feature instanceof CopeVector)
					writeVector((CopeVector)feature);
				else if(feature instanceof CopeMedia)
					writeMedia((CopeMedia)feature);
				else if(feature instanceof CopeHash)
					writeHash((CopeHash)feature);
				else
					throw new RuntimeException(feature.getClass().getName());
			}
			
			writeType(type);
		}
	}

	/**
	 * Identation contract:
	 * This methods is called, when o stream is immediately after a line break,
	 * and it should return the o stream after immediately after a line break.
	 * This means, doing nothing fullfils the contract.
	 */
	private void writeGetterBody(final CopeAttribute attribute)
	throws IOException
	{
		o.write("\t\treturn ");
		o.write(attribute.type.name);
		o.write('.');
		o.write(attribute.name);
		o.write(".get");
		if(attribute.isBoxed())
			o.write("Mandatory");
		o.write("(this)");
		o.write(';');
		o.write(lineSeparator);
	}

	/**
	 * Identation contract:
	 * This methods is called, when o stream is immediately after a line break,
	 * and it should return the o stream after immediately after a line break.
	 * This means, doing nothing fullfils the contract.
	 */
	private void writeSetterBody(final CopeAttribute attribute)
	throws IOException
	{
		o.write("\t\t");
		o.write(attribute.type.name);
		o.write('.');
		o.write(attribute.name);
		o.write(".set(this,");
		o.write(attribute.name);
		o.write(");");
		o.write(lineSeparator);
	}
	
	/**
	 * Identation contract:
	 * This methods is called, when o stream is immediately after a line break,
	 * and it should return the o stream after immediately after a line break.
	 * This means, doing nothing fullfils the contract.
	 */
	private void writeToucherBody(final CopeAttribute attribute)
	throws IOException
	{
		o.write("\t\t");
		o.write(attribute.type.name);
		o.write('.');
		o.write(attribute.name);
		o.write(".touch(this);");
		o.write(lineSeparator);
	}
	
	/**
	 * Identation contract:
	 * This methods is called, when o stream is immediately after a line break,
	 * and it should return the o stream after immediately after a line break.
	 * This means, doing nothing fullfils the contract.
	 */
	private void writeCheckerBody(final CopeHash hash)
	throws IOException
	{
		o.write("\t\treturn ");
		o.write(hash.type.name);
		o.write('.');
		o.write(hash.name);
		o.write(".check(this,");
		o.write(hash.name);
		o.write(");");
		o.write(lineSeparator);
	}

	/**
	 * Identation contract:
	 * This methods is called, when o stream is immediately after a line break,
	 * and it should return the o stream after immediately after a line break.
	 * This means, doing nothing fullfils the contract.
	 */
	private void writeSetterBody(final CopeHash hash)
	throws IOException, InjectorParseException
	{
		o.write("\t\t");
		o.write(hash.type.name);
		o.write('.');
		o.write(hash.name);
		o.write(".set(this,");
		o.write(hash.name);
		o.write(");");
		o.write(lineSeparator);
	}
	
	private void writeStreamWarning(final String type) throws IOException
	{
		if(InputStream.class.getName().equals(type))
		{
			o.write("\t * ");
			o.write(GETTER_STREAM_WARNING);
			o.write(lineSeparator);
		}
	}

	private void writeModifier(final int modifier) throws IOException
	{
		final String modifierString = Modifier.toString(modifier);
		if(modifierString.length()>0)
		{
			o.write(modifierString);
			o.write(' ');
		}
	}

}
