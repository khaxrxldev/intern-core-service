package com.intern.core.service.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	private static Connection connection = null;
	
	public static Connection getDbConnection(String driverName, String dbUsername, String dbPassword, String dbUrl) {
		
		try {
		    Class.forName(driverName);
		    connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		
		return connection;
	}
}
