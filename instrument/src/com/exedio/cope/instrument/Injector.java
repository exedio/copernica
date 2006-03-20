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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

/**
 * Implements a modifying java parser.
 * This means, the input stream is continuesly written
 * into an output stream, and may be modified before writing.
 *
 * The parser recognizes java meta information only,
 * which is anything outside method bodies and attribute
 * inizializers.
 *
 * To use the parser, provide an implemention of the
 * InjectionConsumer interface to the constructor.
 * @see InjectionConsumer
 * 
 * @author Ralf Wiebicke
 */
final class Injector
{
	private final CRC32 inputCRC = new CRC32();
	private final Reader input;
	private final StringBuffer output;
	private final InjectionConsumer consumer;
	private final String fileName;

	private final StringBuffer buf = new StringBuffer();

	private boolean do_block = false;
	private boolean start_block = false;
	private boolean collect_when_blocking = false;
	private final StringBuffer collector = new StringBuffer();

	private String docComment = null;
	private boolean discardNextFeature = false;
	
	final JavaFile javaFile;

	/**
	 * Constructs a new java parser.
	 * @param input
	 * the input stream to be parsed.
	 * @param output
	 * the target, where the modified input stream is written to.
	 * May be null, if only reading is desired.
	 * @param consumer
	 * an implementation of InjectionConsumer,
	 * listening to parsed elements of the input stream.
	 * @see InjectionConsumer
	 */
	public Injector(final File inputFile,
								final InjectionConsumer consumer, final JavaRepository repository)
		throws FileNotFoundException
	{
		this.input = new InputStreamReader(new CheckedInputStream(new FileInputStream(inputFile), inputCRC));
		this.consumer = consumer;
		this.fileName = inputFile.getName();
		this.javaFile = new JavaFile(repository);
		this.output = javaFile.buffer;
	}
	
	void close() throws IOException
	{
		if(input!=null)
			input.close();
	}
	
	long getCRC()
	{
		return inputCRC.getValue();
	}

	private char outbuf;
	private boolean outbufvalid = false;

	/**
	 * The line number in the current file.
	 */
	private int positionLine = 1;

	/**
	 * The character in the current line.
	 */
	private int positionColumn = 0;

	private final char read() throws IOException, EndException
	{
		int c = input.read();

		if (output != null && !do_block && outbufvalid && !discardNextFeature)
			output.append(outbuf);

		if (c >= 0)
		{
			if (c == '\n')
			{
				positionLine++;
				positionColumn = -1;
			}
			else
			{
				positionColumn++;
			}

			if (do_block && collect_when_blocking)
				collector.append(outbuf);
			outbuf = (char)c;
			outbufvalid = true;
			//System.out.print((char)c);
			return (char)c;
		}
		else
			throw new EndException();
	}

	private void scheduleBlock(boolean collect_when_blocking)
	{
		if (do_block || collector.length() > 0)
			throw new IllegalArgumentException();
		start_block = true;
		this.collect_when_blocking = collect_when_blocking;
	}

	private String getCollector()
	{
		do_block = false;
		start_block = false;
		String s = collector.toString();
		collector.setLength(0);
		//System.out.println("  collector: >"+s+"<");
		return s;
	}

	private void flushOutbuf() throws IOException
	{
		if (outbufvalid)
		{
			if (do_block)
			{
				if (collect_when_blocking)
					collector.append(outbuf);
			}
			else
			{
				if (output != null)
					output.append(outbuf);
			}
			outbufvalid = false;
		}
	}

	private void write(String s) throws IOException
	{
		if (output != null)
			output.append(s);
	}

	/**
	 * Reads a comment.
	 * Is started after the initial '/' character.
	 * If the next character is either '/' or '*',
	 * the rest of the comment is read, and a value of -1 is returned.
	 * If not, there is no comment,
	 * and this next character is returned, casted to int.
	 */
	private int readComment() throws IOException, EndException
	{
		char x;
		switch (x = read())
		{
			case '*' :
				if (read() == '*')
				{
					// definitly a doc comment, see Java Lang. Spec. 3.7.
					//System.out.println("this is a '/** .. */' doc-comment");
				}
				//System.out.println("this is a '/* .. */' comment");
				while (true)
				{
					if (read() != '*')
						continue;
					char c;
					while ((c = read()) == '*');
					if (c == '/')
						break;
				}
				break;
			case '/' :
				//System.out.println("this is a '//' comment");
				do;
				while (read() != '\n');
				break;
			default :
				return (int)x;
		}
		return -1;
	}

	private char tokenBuf = '\0';
	private String commentBuf = null;
	private String comment = null;

	/**
	 * Splits the character stream into tokens.
	 * This tokenizer works only outside of method bodys.
	 * @return '\0' for multiple character token in buf,
	 * 'c' for comment token in comment,
	 * else for single character token.
	 */
	private char readToken() throws IOException, EndException
	{
		char c;

		if (tokenBuf != '\0')
		{
			c = tokenBuf;
			tokenBuf = '\0';
			//System.out.println("<<"+c+">>");
			return c;
		}

		if (commentBuf != null)
		{
			comment = commentBuf;
			commentBuf = null;
			//System.out.println("<<"+comment+">>");
			return 'c';
		}

		buf.setLength(0);

		while (true)
		{
			switch (c = read())
			{
				case '/' :
					boolean commentcollector = false;
					if (!do_block && start_block)
					{
						do_block = true;
						commentcollector = true;
					}
					readComment();
					if (commentcollector)
						flushOutbuf();
					if (buf.length() > 0)
					{
						if (commentcollector)
							commentBuf = getCollector();
						//System.out.println("<"+buf+">");
						return '\0';
					}
					if (commentcollector)
					{
						comment = getCollector();
						//System.out.println("<<"+comment+">>");
						return 'c';
					}
					break;
				case ' ' :
				case '\t' :
				case '\n' :
				case '\r' :
					if (buf.length() > 0)
					{
						//System.out.println("<"+buf+">");
						return '\0';
					}
					break;
				case '{' :
				case '}' :
				case '(' :
				case ')' :
				case ';' :
				case '=' :
				case ',' :
					if (buf.length() > 0)
					{
						tokenBuf = c;
						//System.out.println("<"+buf+">");
						return '\0';
					}
					//System.out.println("<<"+c+">>");
					return c;
				case '<' :
					buf.append(c);
					while(true)
					{
						c = read();
						buf.append(c);
						if(c=='>')
							break;
					}
					break;
				default :
					if (!do_block && start_block)
						do_block = true;
					buf.append(c);
					break;
			}
		}
	}

	/**
	 * Parses a method body or an attribute initializer,
	 * depending on the parameter.
	 * For method bodys, the input stream must be directly behind
	 * the first opening curly bracket of the body.
	 * For attribute initializers, the input stream must be directly
	 * behind the '='.
	 * @return
	 * the delimiter, which terminated the attribute initializer
	 * (';' or ',') or '}' for methods.
	 */
	@SuppressWarnings("fallthrough")
	private char parseBody(final boolean attribute, final TokenConsumer tokenConsumer)
		throws IOException, EndException, ParseException
	{
		//System.out.println("    body("+(attribute?"attribute":"method")+")");

		int bracketdepth = (attribute ? 0 : 1);
		int curlyBracketDepth = bracketdepth;
		char c = read();
		while (true)
		{
			switch (c)
			{
				case '/' :
					int i = readComment();
					if (i >= 0)
						c = (char)i;
					else
						c = read();
					break;
				case '{' :
					curlyBracketDepth++;
					// FALL THROUGH
				case '(' :
					bracketdepth++;
					//System.out.print("<("+bracketdepth+")>");
					if(tokenConsumer!=null && curlyBracketDepth==0)
						tokenConsumer.addToken(c);
					if(tokenConsumer!=null)
						tokenConsumer.addChar(c);
					c = read();
					break;
				case '}' :
					curlyBracketDepth--;
					// FALL THROUGH
				case ')' :
					bracketdepth--;
					//System.out.print("<("+bracketdepth+")>");
					if (bracketdepth == 0 && !attribute)
						return '}';
					if (bracketdepth < 0)
						throw new ParseException("';' expected.");
					if (tokenConsumer!=null && curlyBracketDepth==0)
						tokenConsumer.addToken(c);
					if(tokenConsumer!=null)
						tokenConsumer.addChar(c);
					c = read();
					break;
				case ';' :
					// dont have to test for "attribute" here
					// since then the test in the '}' branch would have
					// already terminated the loop
					if (bracketdepth == 0)
						return ';';
					c = read();
					break;
				case ',' :
					if (bracketdepth == 0)
						return ',';
					if (tokenConsumer!=null && curlyBracketDepth==0)
						tokenConsumer.addToken(c);
					if(tokenConsumer!=null)
						tokenConsumer.addChar(c);
					c = read();
					break;
					// ignore brackets inside of literal String's
				case '"' :
					if (tokenConsumer!=null && curlyBracketDepth==0)
						tokenConsumer.addToken(c);
					if(tokenConsumer!=null)
						tokenConsumer.addChar(c);
					il : while (true)
					{
						switch (c=read())
						{
							case '"' :
								if (tokenConsumer!=null && curlyBracketDepth==0)
									tokenConsumer.addToken(c);
								if(tokenConsumer!=null)
									tokenConsumer.addChar(c);
								break il;
							case '\\' :
								read();
								break; // ignore escaped characters
						}
						if (tokenConsumer!=null && curlyBracketDepth==0)
							tokenConsumer.addToken(c);
						if(tokenConsumer!=null)
							tokenConsumer.addChar(c);
					}
					c = read();
					break;
					// ignore brackets inside of literal characters
				case '\'' :
					il : while (true)
					{
						switch (read())
						{
							case '\'' :
								break il;
							case '\\' :
								read();
								break; // ignore escaped characters
						}
					}
					c = read();
					break;
				default :
					if (tokenConsumer!=null && curlyBracketDepth==0)
						tokenConsumer.addToken(c);
					if(tokenConsumer!=null)
						tokenConsumer.addChar(c);
					c = read();
					break;
			}
		}
	}

	/**
	 * Parses a class feature. May be an attribute, a method or a inner
	 * class. May even be a normal class, in this case parent==null.
	 * @param parent the class that contains the class feature
	 * if null, there is no containing class, and
	 * the feature must be a class itself.
	 */
	private JavaFeature[] parseFeature(JavaClass parent)
		throws IOException, EndException, InjectorParseException
	{
		return parseFeature(parent, buf.toString());
	}

	/**
	 * The same as parseFeature(JavaClass) but the first token has
	 * already been fetched from the input stream.
	 * @param bufs the first token of the class feature.
	 * @see #parseFeature(JavaClass)
	 */
	private JavaFeature[] parseFeature(JavaClass parent, String bufs)
		throws IOException, EndException, InjectorParseException
	{
		int modifiers = 0;

		while (true)
		{
			//System.out.println("bufs >"+bufs+"<");
			if ("public".equals(bufs))
				modifiers |= Modifier.PUBLIC;
			else if ("protected".equals(bufs))
				modifiers |= Modifier.PROTECTED;
			else if ("private".equals(bufs))
				modifiers |= Modifier.PRIVATE;
			else if ("static".equals(bufs))
				modifiers |= Modifier.STATIC;
			else if ("final".equals(bufs))
				modifiers |= Modifier.FINAL;
			else if ("synchronized".equals(bufs))
				modifiers |= Modifier.SYNCHRONIZED;
			else if ("volatile".equals(bufs))
				modifiers |= Modifier.VOLATILE;
			else if ("transient".equals(bufs))
				modifiers |= Modifier.TRANSIENT;
			else if ("native".equals(bufs))
				modifiers |= Modifier.NATIVE;
			else if ("abstract".equals(bufs))
				modifiers |= Modifier.ABSTRACT;
			else if ("interface".equals(bufs))
			{
				modifiers |= Modifier.INTERFACE;
				JavaClass[] jcarray = { parseClass(parent, modifiers)};
				return jcarray;
			}
			else if ("class".equals(bufs))
			{
				JavaClass[] jcarray = { parseClass(parent, modifiers)};
				return jcarray;
			}
			else if ("enum".equals(bufs))
			{
				if(readToken()!='\0')
					throw new ParseException("enum name expected");
				final String enumName = buf.toString();
				if(readToken()!='{')
					throw new ParseException("'{' expected");
				parseBody(false, null);
				final JavaClass result = new JavaClass(javaFile, parent, modifiers, true, enumName, Collections.EMPTY_LIST, Collections.EMPTY_LIST);
				
				consumer.onClass(result);
				consumer.onClassEnd(result);

				discardNextFeature=false;
				
				if (collect_when_blocking)
					write(getCollector());
				if (do_block)
					getCollector();
				
				return new JavaFeature[]{result};
			}
			else
			{
				if (parent == null)
					throw new ParseException("'class' or 'interface' expected.");
				break;
			}

			char c = readToken();
			if (c != '\0')
			{
				if (parent == null)
					throw new ParseException("'class' or 'interface' expected.");
				else
				{
					if (c == '{' && modifiers == Modifier.STATIC)
					{
						// this is a static initializer
						if (collect_when_blocking)
							write(getCollector());
						flushOutbuf();
						parseBody(false, null);
						scheduleBlock(true);
						docComment = null;
						return new JavaClass[0];
					}
					else
					{
						throw new ParseException("modifier expected.");
					}
				}
			}
			bufs = buf.toString();
		}
		String featuretype = buf.toString();
		String featurename;

		char c = readToken();

		if (c != '\0')
		{
			if (c == '(') // it's a constructor !
			{
				featurename = featuretype;
				featuretype = null;
				if (!parent.name.equals(featurename))
					throw new ParseException(
						"constructor '"
							+ featurename
							+ " must have the classes name '"
							+ parent.name
							+ '\'');
			}
			else
				throw new ParseException("'(' expected.");
		}
		else
		{
			featurename = buf.toString();
			c = readToken();
		}

		if (c == '(') // it's a method/constructor
		{
			final JavaBehaviour jb =
				(featuretype == null)
					? (JavaBehaviour)new JavaConstructor(parent,
						modifiers,
						featurename)
					: new JavaMethod(parent, modifiers, featuretype, featurename);
			parseBehaviour(jb);
			JavaFeature[] jbarray = { jb };
			return jbarray;
		}
		else // it's an attribute
		{
			final JavaAttribute ja =
				new JavaAttribute(parent, modifiers, featuretype, featurename);
			return parseAttribute(ja, c);
		}
	}

	private void parseBehaviour(JavaBehaviour jb)
		throws IOException, EndException, ParseException
	{
		char c = readToken();
		// parsing parameter list
		while (true)
		{
			String parametertype;
			if (c == ')')
			{
				break;
			}
			else if (c == '\0')
			{
				parametertype = buf.toString();
				if ("final".equals(parametertype))
				{
					c = readToken();
					if (c == '\0')
						parametertype = buf.toString();
					else
						throw new ParseException("parameter type expected.");
				}
			}
			else
				throw new ParseException("')' expected.");
			c = readToken();
			if (c != '\0')
				throw new ParseException("parameter name expected.");
			//System.out.println("addParameter("+parametertype+", "+buf.toString()+")");
			jb.addParameter(parametertype, buf.toString());
			c = readToken();
			if (c == ',')
			{
				c = readToken();
				continue;
			}
			else if (c == ')')
			{
				break;
			}
			else
				throw new ParseException("')' expected.");
		}
		// parsing throws clauses
		c = readToken();
		ti : while (true)
		{
			switch (c)
			{
				case '{' :
					if (collect_when_blocking)
					{
						output.append(getCollector());
						consumer.onBehaviourHeader(jb);
					}
					parseBody(false, null);
					flushOutbuf();
					break ti;
				case ';' :
					if (collect_when_blocking)
					{
						output.append(getCollector());
						consumer.onBehaviourHeader(jb);
					}
					flushOutbuf();
					break ti;
				case '\0' :
					if (buf.toString().equals("throws"))
					{
						do
						{
							c = readToken();
							if (c == '\0')
								jb.addThrowable(buf.toString());
							else
								throw new ParseException("class name expected.");
							c = readToken();
						}
						while (c == ',');
					}
					else
						throw new ParseException("'throws' expected.");
					break;
				default :
					throw new ParseException("'{' expected.");
			}
		}
		if (do_block)
			getCollector();
		else
		{
			//jb.print(System.out);
		}
	}

	private JavaAttribute[] parseAttribute(JavaAttribute ja, char c)
		throws IOException, EndException, InjectorParseException
	{
		consumer.onAttributeHeader(ja);

		final ArrayList commaSeparatedAttributes = new ArrayList();
		commaSeparatedAttributes.add(ja);
		//if(!do_block) ja.print(System.out);

		while (true)
		{

			switch (c)
			{
				case ';' :
					if (collect_when_blocking)
						write(getCollector());
					flushOutbuf();
					if (do_block)
						getCollector();
					final JavaAttribute[] jaarray =
						new JavaAttribute[commaSeparatedAttributes.size()];
					commaSeparatedAttributes.toArray(jaarray);
					return jaarray;
				case ',' :
					c = readToken();
					if (c != '\0')
						throw new ParseException("attribute name expected.");
					ja = new JavaAttribute(ja, buf.toString());
					commaSeparatedAttributes.add(ja);
					//if(!do_block) ja.print(System.out);
					c = readToken();
					break;
				case '=' :
					if (collect_when_blocking)
						write(getCollector());
					c = parseBody(true, ja);
					flushOutbuf();
					break;
				default :
					throw new ParseException("';', '=' or ',' expected.");
			}
		}
	}

	private JavaClass parseClass(JavaClass parent, int modifiers)
		throws IOException, EndException, InjectorParseException
	{
		if (readToken() != '\0')
			throw new ParseException("class name expected.");
		String classname = buf.toString();
		//System.out.println("class ("+Modifier.toString(modifiers)+") >"+classname+"<");
		
		char imc;
		char extendsOrImplements = '-';
		final ArrayList<String> classExtends = new ArrayList<String>();
		final ArrayList<String> classImplements = new ArrayList<String>();
		while((imc=readToken()) != '{')
		{
			if(imc=='\0')
			{
				final String s = buf.toString();
				
				if("extends".equals(s))
					extendsOrImplements = 'e';
				else if("implements".equals(s))
					extendsOrImplements = 'i';
				else
				{
					switch(extendsOrImplements)
					{
						case '-':
							throw new ParseException("expected extends or implements");
						case 'e':
							classExtends.add(s);
							break;
						case 'i':
							classImplements.add(s);
							break;
						default:
							throw new RuntimeException(String.valueOf(extendsOrImplements));
					}
				}
				
				//System.out.println("---------------"+s+"---"+extendsOrImplements+"---------------"+classExtends+"--------"+classImplements);
			}
		}

		JavaClass jc = new JavaClass(javaFile, parent, modifiers, false, classname, classExtends, classImplements);
		//cc.print(System.out);

		consumer.onClass(jc);
		discardNextFeature=false;
		
		if (collect_when_blocking)
			write(getCollector());
		if (do_block)
			getCollector();

		scheduleBlock(true);
		ml : while (true)
		{
			switch (readToken())
			{
				case '}' :
					getCollector();
					break ml;
				case 'c' :
					if (comment.startsWith("/**"))
					{
						docComment = comment;
						//System.out.println("docComment: "+docComment);
						final boolean onDocCommentResult = consumer.onDocComment(docComment);
						discardNextFeature = !onDocCommentResult;
						if(onDocCommentResult)
							output.append(docComment);
						scheduleBlock(onDocCommentResult);
					}
					else
					{
						//System.out.println("comment: "+comment);
						write(comment);
						scheduleBlock(true);
					}
					break;
				case '\0' :
					JavaFeature[] jfarray = parseFeature(jc);
					for (int i = 0; i < jfarray.length; i++)
						consumer.onClassFeature(jfarray[i], docComment);
					discardNextFeature=false;
					docComment = null;
					scheduleBlock(true);
					break;
				case ';' :
					// javac (but not jikes) accepts semicolons on class level,
					// so do we.
					getCollector();
					break;
				case '{' :
					// this is an object initializer as defined
					// in Java Language Specification D.1.3
					if (collect_when_blocking)
						write(getCollector());
					flushOutbuf();
					parseBody(false, null);
					scheduleBlock(true);
					break;
				default :
					throw new ParseException("class member expected.");
			}
		}

		consumer.onClassEnd(jc);
		return jc;
	}

	public void parseFile() throws IOException, InjectorParseException
	{
		try
		{
			char c;
			while (true)
			{
				scheduleBlock(true);
				try
				{
					c = readToken();
				}
				catch (EndException e)
				{
					return;
				}

				if (collect_when_blocking)
					write(getCollector());
				if (do_block)
					getCollector();

				switch (c)
				{
					case '\0' :
						String bufs = buf.toString();
						if ("package".equals(bufs))
						{
							c = readToken();
							if (c != '\0')
								throw new ParseException("package name expected.");
							javaFile.setPackage(buf.toString());
							consumer.onPackage(javaFile);
							//System.out.println("package >"+buf.toString()+"<");
							c = readToken();
							if (c != ';')
								throw new ParseException("';' expected.");
						}
						else if ("import".equals(bufs))
						{
							c = readToken();
							if (c != '\0')
								throw new ParseException("class name expected.");
							String importstring = buf.toString();
							//System.out.println("import >"+importstring+"<");
							javaFile.addImport(importstring);
							consumer.onImport(importstring);
							c = readToken();
							if (c != ';')
								throw new ParseException("';' expected.");
						}
						else
							parseFeature(null, bufs);
						// null says, its a top-level class
						break;

					case 'c' :
						if (comment.startsWith("/**"))
						{
							docComment = comment;
							//System.out.println ("file level docComment: "+docComment);
							consumer.onFileDocComment(docComment);
							output.append(docComment);
							docComment = null; // Mark docComment as handled...
						}
						else
						{
							//System.out.println("comment: "+comment);
							write(comment);
						}
						break;

					default :
						System.out.println("bufc >" + c + "<");
						break;
				}
			}
		}
		catch (EndException e)
		{
			throw new ParseException("Unexpected End-of-File.");
		}
		catch (RuntimeException e)
		{
			throw new ParseException(e);
		}
	}

	private class EndException extends Exception
	{
		private static final long serialVersionUID = 298765358236485l;
		
		public EndException()
		{
		}
	}

	private class ParseException extends InjectorParseException
	{
		private static final long serialVersionUID = 2938658236582l;
		
		final int line;
		final int column;

		private ParseException(String message)
		{
			//super("["+positionLine+':'+positionColumn+']'+' '+message);
			super(message);
			line = positionLine;
			column = positionColumn;
		}

		private ParseException(final RuntimeException cause)
		{
			//super("["+positionLine+':'+positionColumn+']'+' '+message);
			super(cause);
			line = positionLine;
			column = positionColumn;
		}

		public String getMessage()
		{
			return
				"("
				+ fileName
				+ ':'
				+ line
				+ ':'
				+ column
				+ ')'
				+ ' '
				+ super.getMessage();
		}

	}

	public final static boolean hasTag(String doccomment, String tagname)
	{
		if(doccomment==null)
			return false;

		final String s = '@' + tagname;
		final int pos = doccomment.indexOf(s);
		if(pos<0)
			return false;
		if(pos+s.length()==doccomment.length())
			return true;
		return Character.isWhitespace(doccomment.charAt(pos+s.length()));
	}

	/**
	 * @param tagname the tag name without the '@' prefix
	 * @return the first line following the tag
	 */
	public final static String findDocTagLine(String doccomment, String tagname)
	{
		if(doccomment==null)
			return null;

		String s = '@' + tagname + ' ';
		int start = doccomment.indexOf(s);
		if (start < 0)
			return null;
		start += s.length();

		int end;
		li : for (end = start; end < doccomment.length(); end++)
		{
			switch (doccomment.charAt(end))
			{
				case '\n' :
				case '\r' :
				case '*' :
					break li;
			}
		}
		String result = doccomment.substring(start, end).trim();
		//System.out.println("doctag:>"+tagname+"< >"+docComment.substring(start, end)+"<");
		return result;
	}

}
