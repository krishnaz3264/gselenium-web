package com.g.selenium.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class DBConnectionFactory {
	private static DBConnectionFactory dbConnectionFactory;
	private DataSource dataSource;

	public static DBConnectionFactory getInstance() {
		if (dbConnectionFactory == null) {
			dbConnectionFactory = new DBConnectionFactory();
		}
		return dbConnectionFactory;
	}

	private DBConnectionFactory() {
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
