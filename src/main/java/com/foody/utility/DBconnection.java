package com.foody.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
	private static final String url="jdbc:mysql://localhost:3306/food-resturant";
	private static final String username="root";
	private static final String password="root";
	static Connection con;

	public static Connection getConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			
			System.out.println("succesful");
		} catch (ClassNotFoundException e ) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

}
