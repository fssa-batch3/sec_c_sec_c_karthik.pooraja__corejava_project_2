package com.fssa.connection;

import java.sql.*;

import com.fssa.connection.exception.ConnectionException;



public class ConnectionUtil {

	public static Connection getConnection() throws ConnectionException{
		Connection con = null ;


//		Local Credentials
//		url = System.getenv("DATABASE_HOST_LOCAL");
//		userName = System.getenv("DATABASE_USERNAME_LOCAL");
//		passWord = System.getenv("DATABASE_PASSWORD_LOCAL");
		String url = System.getenv("DATABASE_HOST");
		String userName = System.getenv("DATABASE_USERNAME");
		String passWord = System.getenv("DATABASE_PASSWORD");
		

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, passWord);
		} catch (Exception e) {
			throw new ConnectionException(e);
		}
		return con;
	}

}
