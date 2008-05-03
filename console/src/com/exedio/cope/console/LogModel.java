/*
 * Copyright (C) 2004-2008  exedio GmbH (www.exedio.com)
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

package com.exedio.cope.console;

import com.exedio.cope.DateField;
import com.exedio.cope.IntegerField;
import com.exedio.cope.Item;
import com.exedio.cope.LongField;
import com.exedio.cope.SetValue;
import com.exedio.cope.Type;
import com.exedio.cope.util.ReactivationConstructorDummy;

final class LogModel extends Item
{
	static final DateField date = new DateField().toFinal().unique();
	static final IntegerField running = new IntegerField().toFinal().min(0);
	static final LongField nextTransactionId = new LongField().toFinal();
	
	LogModel(final SetValue... setValues)
	{
		super(setValues);
	}
	
	@SuppressWarnings("unused")
	private LogModel(final ReactivationConstructorDummy d, final int pk)
	{
		super(d,pk);
	}
	
	private static final long serialVersionUID = 1l;
	
	static final Type TYPE = newType(LogModel.class);
}