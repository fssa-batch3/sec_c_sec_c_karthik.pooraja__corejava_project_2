package com.fssa.connection;

import java.sql.Connection;
import java.sql.DriverManager;

import com.fssa.connection.exception.ConnectionException;

public class ConnectionUtil {

	public static Connection getConnection() throws ConnectionException {
		Connection con = null;
		String url, userName, passWord;

		url = System.getenv("DATABASE_HOST");
		userName = System.getenv("DATABASE_USERNAME");
		passWord = System.getenv("DATABASE_PASSWORD");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, passWord);
			System.out.println("connection success");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ConnectionException("Unable to connect to the database");
		}
		return con;
	}

	public static void main(String[] args) {

		try {
			getConnection();
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}