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

/**
 * A common super class for all patterns.
 * <p>
 * Patterns should be constructable in three different ways:
 * <dl>
 * <dt>1) by an explicit external source</dt>
 * <dd>
 * This is the most verbose kind of defining a pattern.
 * First the source for the pattern is created, such as:
 * <pre>static final StringAttribute source = new StringAttribute(OPTIONAL)</pre>
 * Then the pattern ist created using the previously defined source:
 * <pre>static final Hash hash = new MD5Hash(source)</pre>
 * </dd>
 * <dt>2) by an implicit external source</dt>
 * <dd>
 * More concisely the pattern can be constructed by defining the source
 * implicitely when the defining the pattern itself:
 * <pre>static final Hash hash = new MD5Hash(new StringAttribute(OPTIONAL))</pre>
 * </dd>
 * <dt>3) by an internal source</dt>
 * <dd>
 * Finally, the construction of the source can be done the the pattern itself:
 * <pre>static final Hash hash = new MD5Hash(OPTIONAL)</pre>
 * </dd>
 * </dl>
 * 
 * @author Ralf Wiebicke
 */
public abstract class Pattern extends Feature
{
	@Override
	final void initialize(final Type<? extends Item> type, final String name, final java.lang.reflect.Type genericType)
	{
		super.initialize(type, name, genericType);
		initialize();
	}
	
	protected final void registerSource(final Attribute attribute)
	{
		attribute.registerPattern(this);
	}

	/**
	 * Here you can do additional initialization not yet done in the constructor.
	 * In this method you can call methods {@link #getType()} and {@link #getName()}
	 * for the first time.
	 */
	public void initialize()
	{
		// empty default implementation
	}
	
	protected final void initialize(final Attribute<Object> attribute, final String name)
	{
		attribute.initialize(getType(), name, (java.lang.reflect.Type)null);
	}
	
	protected final void initialize(final UniqueConstraint uniqueConstraint, final String name)
	{
		uniqueConstraint.initialize(getType(), name, (java.lang.reflect.Type)null);
	}
	
	// Make non-final method from super class final
	public final Type<? extends Item> getType()
	{
		return super.getType();
	}
	
}
