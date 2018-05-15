package com.g.selenium.web;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import com.g.selenium.dao.DBConnectionFactory;

@WebListener
public class GSeleniumWebStartup implements ServletContextListener {
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent event) {
		try {
			InitialContext context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("jdbc/GSeleniumDB");
			DBConnectionFactory.getInstance().setDataSource(dataSource);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
}
