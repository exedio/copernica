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

import java.lang.reflect.Modifier;

/**
 * Represents a class parsed by the java parser.
 * Is an inner class, if parent is not null.
 * @see Injector
 */
class JavaClass extends JavaFeature
{

	/**
	 * @param parent may be null for non-inner classes
	 */
	public JavaClass(JavaFile file, JavaClass parent, int modifiers, String name)
	throws InjectorParseException
	{
		super(file, parent, modifiers, null, name);
	}
	
	/**
	 * Constructs the fully qualified name of this class,
	 * including package path.
	 */
	public String getFullName()
	{
		StringBuffer buf=new StringBuffer();
		String packagename=getPackageName();
		if(packagename!=null)
		{
			buf.append(packagename);
			buf.append('.');
		}
		int pos=buf.length();
		for(JavaClass i=this; i!=null; i=i.parent)
		{
			if(i!=this)
				buf.insert(pos, '$');
			buf.insert(pos, i.name);
		}
		return buf.toString();
	}
	
	/**
	 * Constructs the fully qualified name of this class,
	 * including package path.
	 * The same as {@link #getFullName()}, but without
	 * dots and dollars, so that this string can be used
	 * as part of a java identifier.
	 */
	public String getFullNameEscaped()
	{
		StringBuffer buf=new StringBuffer();
		String packagename=getPackageName();
		if(packagename!=null)
		{
			buf.append('_');
			for(int i=0; i<packagename.length(); i++)
			{
				char c=packagename.charAt(i);
				if(c=='.')
					buf.append('_');
				else
					buf.append(c);
			}
		}
		int pos=buf.length();
		for(JavaClass i=this; i!=null; i=i.parent)
		{
			buf.insert(pos, i.name);
			buf.insert(pos, '_');
		}
		return buf.toString();
	}
	
	public final boolean isInterface()
	{
		return (modifier & Modifier.INTERFACE) > 0;
	}
	
	public final int getAllowedModifiers()
	{
		return
		Modifier.INTERFACE |
		Modifier.PUBLIC |
		Modifier.PROTECTED |
		Modifier.PRIVATE |
		Modifier.FINAL |
		Modifier.STATIC |
		Modifier.ABSTRACT;
	}
	
}
