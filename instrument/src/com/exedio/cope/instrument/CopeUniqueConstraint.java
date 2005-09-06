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


final class CopeUniqueConstraint
{
	final String name;
	final int modifier;
	final CopeAttribute[] copeAttributes;
	
	/**
	 * For constraints covering more than one attribute.
	 */
	CopeUniqueConstraint(final JavaAttribute javaAttribute, final CopeAttribute[] copeAttributes)
	{
		this.name = javaAttribute.name;
		this.modifier = javaAttribute.modifier;
		this.copeAttributes = copeAttributes;

		final CopeClass copeClass = CopeClass.getCopeClass(copeAttributes[0].javaAttribute.parent);
		copeClass.add(this);

		/*
		final ArrayList xAttributeNames = new ArrayList();
		for(int i = 0; i<copeAttributes.length; i++)
			xAttributeNames.add(copeAttributes[i].javaAttribute.name);
		System.out.println("------uniqueconstraint:"+name+xAttributeNames);

		final UniqueConstraint rtvalue = (UniqueConstraint)javaAttribute.evaluate();
		final ArrayList rtAttributeNames = new ArrayList();
		for(Iterator i = rtvalue.getUniqueAttributes().iterator(); i.hasNext(); )
		{
			final ObjectAttribute attribute = (ObjectAttribute)i.next();
			final JavaAttribute ja = (JavaAttribute)javaAttribute.parent.getByRtValue(attribute);
			rtAttributeNames.add(ja.name);
		}
		System.out.println("------uniqueconstraint:"+name+rtAttributeNames);
		*/
	}
	
	/**
	 * For constraints covering exactly one attribute.
	 */
	CopeUniqueConstraint(final CopeAttribute copeAttribute)
	{
		this.name = copeAttribute.getName();
		this.modifier = copeAttribute.javaAttribute.modifier;
		this.copeAttributes = new CopeAttribute[]{copeAttribute};
	}
	
}
