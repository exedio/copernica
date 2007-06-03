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

package com.exedio.cope.console;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;

import javax.servlet.http.HttpServletRequest;

import com.exedio.cope.Model;
import com.exedio.cope.Transaction;

final class TransactionCop extends ConsoleCop
{
	TransactionCop()
	{
		super("transactions");
		addParameter(TAB, TAB_TRANSACTION);
	}
	
	@Override
	void writeHead(final HttpServletRequest request, final PrintStream out)
	{
		Transaction_Jspm.writeHead(out);
	}
	
	@Override
	void writeBody(final PrintStream out, final Model model, final HttpServletRequest request)
	{
		final Transaction[] openTransactions = model.getOpenTransactions().toArray(new Transaction[]{});
		Arrays.sort(openTransactions, new Comparator<Transaction>(){

			public int compare(final Transaction tx1, final Transaction tx2)
			{
				final long id1 = tx1.getID();
				final long id2 = tx2.getID();
				return id1<id2 ? -1 : id1>id2 ? 1 : 0;
			}
			
		});

		final Thread[] threads = new Thread[openTransactions.length];
		final StackTraceElement[][] stacktraces = new StackTraceElement[openTransactions.length][];
		for(int i = 0; i<openTransactions.length; i++)
		{
			final Thread thread = openTransactions[i].getBoundThread();
			if(thread!=null)
			{
				threads[i] = thread;
				stacktraces[i] = thread.getStackTrace();
			}
		}

		Transaction_Jspm.writeBody(
				out, request, this,
				model.getNextTransactionId(),
				model.getLastTransactionStartDate(),
				openTransactions, threads, stacktraces);
	}
}
