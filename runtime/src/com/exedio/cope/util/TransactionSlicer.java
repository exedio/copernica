/*
 * Copyright (C) 2004-2007  exedio GmbH (www.exedio.com)
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

package com.exedio.cope.util;

import com.exedio.cope.Model;
import com.exedio.cope.Transaction;

public final class TransactionSlicer
{
	private final Model model;
	private final int bitesPerSlice;
	private Transaction transaction;
	private final String transactionName;

	private int bitsLeft;
	private int sliceCount = 0;
	
	public TransactionSlicer(final Model model, final int bitesPerSlice)
	{
		this.model = model;
		this.bitesPerSlice = bitesPerSlice;
		this.transaction = model.getCurrentTransaction();
		this.transactionName = transaction.getName();
		this.bitsLeft = bitesPerSlice;
		
		if(bitesPerSlice<=0)
			throw new IllegalArgumentException("bitesPerSlice must be positive, but was " + bitesPerSlice);
	}
	
	public boolean biteOff()
	{
		if((--bitsLeft)>0)
			return false;
		
		if(transaction!=model.getCurrentTransaction())
			throw new IllegalStateException("inconsistent transaction, expected " + transaction + ", but was " + model.getCurrentTransaction());

		model.commit();
		bitsLeft = bitesPerSlice;
		sliceCount++;
		transaction = model.startTransaction(
				transactionName!=null
				? (transactionName+" slice"+sliceCount)
				: ("slice"+sliceCount));
		return true;
	}
	
	public int getSliceCount()
	{
		return sliceCount;
	}
}