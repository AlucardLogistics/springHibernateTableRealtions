package com.alucard.springHibernate.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false";
		String user = "alucard";
		String pass = "alucard";
		
		try {
			System.out.println("Connecting to the database: " + jdbcUrl);
			
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			
			System.out.println("Connection successful!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
