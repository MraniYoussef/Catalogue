package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionS {
	private static Connection connection;
	

	static {
		try {
			 Class.forName("oracle.jdbc.OracleDriver");

	        	connection =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","aaaaaa");
						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error connection tothe database");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
}
