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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

final class JavaRepository
{
	/**
	 * Distiguishes two stages in life cycle of this repository,
	 * and its contents:
	 * building the repository and querying the repository.
	 */
	private boolean buildStage = true;
	
	private boolean generateStage = false;
	
	private final ArrayList files = new ArrayList();
	private final HashMap copeTypeByShortClassName = new HashMap();
	
	void endBuildStage()
	{
		assert buildStage;
		assert !generateStage;
		
		generateStage = true;
		
		for(Iterator i = copeTypeByShortClassName.values().iterator(); i.hasNext(); )
			((CopeType)i.next()).endBuildStage();
		
		buildStage = false;
	}
	
	boolean isBuildStage()
	{
		return buildStage;
	}

	void add(final JavaFile file)
	{
		assert buildStage;
		files.add(file);
	}
	
	final List getFiles()
	{
		assert !buildStage;
		return files;
	}
	
	void add(final CopeType copeType)
	{
		assert buildStage && !generateStage;
		final String name = JavaFile.extractClassName(copeType.javaClass.name);
		if(copeTypeByShortClassName.put(name, copeType)!=null)
			throw new RuntimeException(name);
		//System.out.println("--------- put cope type: "+name);
	}
	
	CopeType getCopeType(final String className)
	{
		assert generateStage;
		final CopeType result = (CopeType)copeTypeByShortClassName.get(className);
		if(result==null)
			throw new RuntimeException("no cope type for "+className);
		return result;
	}

}
