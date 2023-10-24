package com.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Connection {
	  protected static Connection getConnection() {
	        Connection connection = null;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "1326");
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	            // Handle or log the exception appropriately
	        }
	        return connection;
	    }
}