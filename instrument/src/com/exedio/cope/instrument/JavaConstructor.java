/*
Copyright (C) 2000  Ralf Wiebicke
 
This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.
 
This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.
 
You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package injection;

import java.io.PrintStream;

/**
 * Represents a constructor of a class parsed by the java parser.
 * @see Injector
 */
public final class JavaConstructor extends JavaBehaviour
{
	/**
	 * The index of the start of the last parameter of the
	 * parameter list in {@link #literal}.
	 * Is the index of the last comma, if there is more than one
	 * parameter or otherwise the index after the opening parent.
	 *
	 * Needed for {@link #getWrappedLiteral()},
	 * if this IS a constructor.
	 *
	 * Is initialized to -1.
	 */
	private int last_param_start=-1;
	
	/**
	 * The index of the end of the last parameter of the
	 * parameter list in {@link #literal}.
	 * Is the index of the closing bracket of the parameter list.
	 *
	 * Needed for {@link #getWrappedLiteral()},
	 * if this IS a constructor.
	 *
	 * Is initialized to -1.
	 */
	private int last_param_end=-1;
	
	public JavaConstructor(JavaClass parent,
	int modifiers,
	String name)
	throws InjectorParseException
	{
		super(parent, modifiers, null, name);
	}
	
	/**
	 * Sets {@link #last_param_start} to the given value.
	 * @throws RuntimeException if pos is negative.
	 */
	public final void setLastParameterStart(int pos)
	{
		if(pos<0)
			throw new RuntimeException();
		last_param_start=pos;
	}
	
	/**
	 * Sets {@link #last_param_end} to the given value.
	 * @throws RuntimeException if pos is negative.
	 * @throws RuntimeException if called more than once.
	 */
	public final void setLastParameterEnd(int pos)
	{
		if(pos<0)
			throw new RuntimeException();
		if(last_param_end>=0)
			throw new RuntimeException();
		last_param_end=pos;
	}
	
	/**
	 * See Java Language Specification 8.6.3
	 * &quot;Constructor Modifiers&quot;
	 */
	public final int getAllowedModifiers()
	{
		return
		java.lang.reflect.Modifier.PUBLIC |
		java.lang.reflect.Modifier.PROTECTED |
		java.lang.reflect.Modifier.PRIVATE;
	}
	
	public final void printMore(PrintStream o)
	{
		super.printMore(o);
		System.out.println("    ("+last_param_start+'|'+last_param_end+')');
	}
	
}
