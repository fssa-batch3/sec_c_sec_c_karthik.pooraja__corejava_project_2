package com.fssa.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.fssa.connection.exception.ConnectionException;


public class ConnectionUtil {

	    public static Connection getConnection() throws ConnectionException {
	        Connection con = null;
	        String url, userName, passWord;

	        url = System.getenv("DATABASE_HOST");
	        userName = System.getenv("DATABASE_USERNAME");
	        passWord = System.getenv("DATABASE_PASSWORD");

	        try {
	            setupDriver(); // Extracted the driver setup
	            con = DriverManager.getConnection(url, userName, passWord);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new ConnectionException("Unable to connect to the database");
	        }
	        return con;
	    }

	    private static void setupDriver() throws ClassNotFoundException {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    }



	public static void main(String[] args) throws ConnectionException ,SQLException{
		ConnectionUtil.getConnection();
	}
}