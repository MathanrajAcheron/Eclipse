package com.csv.service;

import java.sql.*;

public class ModelDAO {
	static Connection connection;

	public static Connection openConnection() {
		String drivername = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/acherontraining";
		String username = "root";
		String password = "root";
		connection = null;
		try {
			Class.forName(drivername);
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return connection;
	}

	public static void closeConnection() {
		try {
			if(connection!=null)
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
