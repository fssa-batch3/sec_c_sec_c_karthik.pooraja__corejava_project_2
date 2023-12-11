package com.fssa.connection;

import java.sql.*;

import com.fssa.connection.exception.ConnectionException;



public class ConnectionUtil {

	public static Connection getConnection() throws ConnectionException{
		Connection con = null ;

		String url = System.getenv("DATABASE_HOST_LOCAL");
		String userName = System.getenv("DATABASE_USERNAME_LOCAL");
		String passWord = System.getenv("DATABASE_PASSWORD_LOCAL");
		

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, passWord);
		} catch (Exception e) {
			throw new ConnectionException(e);
		}
		return con;
	}

}
