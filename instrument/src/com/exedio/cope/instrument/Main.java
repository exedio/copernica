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

package com.exedio.cope.instrument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.exedio.cope.NestingRuntimeException;

public class Main
{
	
	static void inject(final File inputfile, final File outputfile, final JavaRepository repository)
	throws IOException, InjectorParseException
	{
		//System.out.println("injecting from "+inputfile+" to "+outputfile);
		
		if(!inputfile.exists())
			throw new RuntimeException("error: input file " + inputfile.getAbsolutePath() + " does not exist.");
		if(!inputfile.isFile())
			throw new RuntimeException("error: input file " + inputfile.getAbsolutePath() + " is not a regular file.");
			
		if(outputfile.exists())
		{
			if(inputfile.getCanonicalPath().equals(outputfile.getCanonicalPath()))
				throw new RuntimeException("error: input file and output file are the same.");
			if(!outputfile.isFile())
				throw new RuntimeException("error: output file is not a regular file.");
		}
		
		Reader input=null;
		Writer output=null;
		try
		{
			input =new InputStreamReader(new FileInputStream(inputfile));
			output=new OutputStreamWriter(new FileOutputStream(outputfile));
			(new Injector(input, output, new Instrumentor(), repository)).parseFile();
			input.close();
			output.close();
		}
		catch(InjectorParseException e)
		{
			e.printStackTrace();
			throw new InjectorParseException(inputfile.getAbsolutePath() + " does not exist");
		}
		finally
		{
			if(input!=null)  input.close();
			if(output!=null) output.close();
		}
	}
	
	private static final String TEMPFILE_SUFFIX=".temp_cope_injection";
	
	static void inject(final File tobemodifiedfile, final JavaRepository repository)
	throws IOException, InjectorParseException
	{
		System.out.println("Instrumenting "+tobemodifiedfile);
		final File outputfile=new File(tobemodifiedfile.getAbsolutePath()+TEMPFILE_SUFFIX);
		inject(tobemodifiedfile, outputfile, repository);
		if(!outputfile.exists())
			throw new RuntimeException("not exists "+outputfile+".");
		if(!tobemodifiedfile.delete())
			throw new RuntimeException("deleting "+tobemodifiedfile+" failed.");
		if(!outputfile.renameTo(tobemodifiedfile))
			throw new RuntimeException("renaming "+outputfile+" to "+tobemodifiedfile+" failed.");
	}
	
	static void expand(Collection files, String pattern)
	throws IOException
	{
		files.add(pattern);
	}
	
	public static void main(final String[] args)
	{
		try
		{
			(new Main()).run(new File("."), args);
		}
		catch(RuntimeException e)
		{
			e.printStackTrace();
			throw e;
		}
		catch(IllegalParameterException e)
		{
			e.printStackTrace();
			throw new NestingRuntimeException(e);
		}
		catch(InjectorParseException e)
		{
			e.printStackTrace();
			throw new NestingRuntimeException(e);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			throw new NestingRuntimeException(e);
		}
	}
	
	Main()
	{}
	
	private void printUsage(PrintStream o)
	{
		o.println("usage:");
		o.print("java ");
		o.print(getClass().getName());
		o.println(" tobemodified1.java ...");
	}
	
	private int i;
	private String[] args;
	private final ArrayList taskConfigs = new ArrayList();
	
	private ArrayList sourcefiles=new ArrayList();
	
	private void processParameter() throws IOException, IllegalParameterException
	{
		for(; i<args.length; i++)
			expand(sourcefiles, args[i]);
	}
	
	final void run(final File dir, final String[] args) throws IllegalParameterException, InjectorParseException, IOException
	{
		this.args = args;
		
		for(i=0; i<args.length; i++)
			processParameter();
		
		if(sourcefiles.isEmpty())
			throw new IllegalParameterException("nothing to do.");
		
		final JavaRepository repository = new JavaRepository();
		
		for(Iterator i=sourcefiles.iterator(); i.hasNext(); )
		{
			final String s=(String)i.next();
			inject(new File(dir, s), repository);
		}
	}
	
}
