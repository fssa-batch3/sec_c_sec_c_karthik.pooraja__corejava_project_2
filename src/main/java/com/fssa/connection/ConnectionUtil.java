package com.fssa.connection;

import java.sql.*;

public class ConnectionUtil {

	public static Connection getConnection() {
		Connection con = null;

		String url, userName, passWord;

//		Local Credentials
		url = System.getenv("DATABASE_HOST_LOCAL");
		userName = System.getenv("DATABASE_USERNAME_LOCAL");
		passWord = System.getenv("DATABASE_PASSWORD_LOCAL");
//		url = System.getenv("DATABASE_HOST");
//		userName = System.getenv("DATABASE_USERNAME");
//		passWord = System.getenv("DATABASE_PASSWORD");
		

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, passWord);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return con;
	}

	public static void main(String[] args) {
		try {
			ConnectionUtil.getConnection();
		} catch (RuntimeException e) {
			System.out.println(e);
		}
	}

}
