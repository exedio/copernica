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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

final class CopeConnectionFactory implements ConnectionPool.Factory
{
	private final String url;
	private final java.util.Properties info;

	CopeConnectionFactory(final Properties properties, final Dialect dialect)
	{
		this.url = properties.getDatabaseUrl();

		info = new java.util.Properties();
		info.setProperty("user", properties.getDatabaseUser());
		info.setProperty("password", properties.getDatabasePassword());
		dialect.completeConnectionInfo(info);
	}

	public Connection createConnection() throws SQLException
	{
		return DriverManager.getConnection(url, info);
	}
}
