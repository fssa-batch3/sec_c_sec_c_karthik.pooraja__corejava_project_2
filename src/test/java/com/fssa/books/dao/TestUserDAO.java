package com.fssa.books.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.fssa.books.exception.BookDAOCRUDException;
import com.fssa.books.exception.DataException;
import com.fssa.books.model.User;
import com.fssa.books.model.Role;
import com.fssa.books.service.UserService;
import com.fssa.books.util.CustomLogger;
import com.fssa.connection.ConnectionUtil;
import com.fssa.connection.exception.ConnectionException;

public class TestUserDAO {
	@Test
	void TestAddUser() throws SQLException, BookDAOCRUDException, ConnectionException, DataException {
		User user = new User();
		user.setName("Madhan Kumar");
		user.setEmail("madhaney@gmail.com");
		user.setPassword("Hi1234@da");
		user.setPhoneNumber("9443322110");
		Assertions.assertTrue(UserService.addUser(user));
	}

	@Test
	void TestUpdateUser() throws SQLException, BookDAOCRUDException, ConnectionException, DataException {
		User user = new User();
		user.setName("Karthik kumar");
		user.setPhoneNumber("9443322110");
		user.setEmail("madhan@gmail.com");
		user.setPassword("Hi1234@da");
		user.setId(1);
		Assertions.assertTrue(UserService.updateUser(user));
	}

	@Test
	void TestDeleteUser() throws SQLException, BookDAOCRUDException, ConnectionException, DataException {
		String email = "madhane@gmail.com";
		Assertions.assertTrue(UserService.deleteUser(email));
	}

	@Test
	public void testDetails() throws ConnectionException, DataException, SQLException {
		// Initialize test data in the in-memory database (if needed)
		// For example, you can insert a test user into the "users" table

		// Test the UserService.details(email) method
		String testEmail = "karthik@gmail.com";
		User user = UserService.details(testEmail);

		Assertions.assertNotNull(user);
	}

	@Test
	public void testIsExist() throws ConnectionException, DataException, SQLException {
		String email = "madhan@gmail.com";
		String password = "Hi1234@da";
		Assertions.assertTrue(UserService.isExists(email, password));
	}
    @Test
	public void testUpdateTransferStatus() throws SQLException, ConnectionException {
		String status="BORROWED";
		int userId=3; 
		Assertions.assertTrue(UserService.updateTransferStatus(status, userId));
	}
    
}
