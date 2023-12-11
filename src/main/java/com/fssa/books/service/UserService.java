package com.fssa.books.service;

import java.sql.SQLException;

import com.fssa.books.dao.UserDAO;
import com.fssa.books.exception.DataException;
import com.fssa.books.model.User;
import com.fssa.books.validator.UserValidator;
import com.fssa.connection.exception.ConnectionException;

public class UserService {
	UserDAO user = new UserDAO();

	public static boolean addUser(User user) throws SQLException, ConnectionException, DataException {
		if (UserValidator.ValidateUser(user)) {
			UserDAO.addUser(user);
		}

		return true;
	}

	public static boolean updateUser(User user) throws SQLException, ConnectionException, DataException {
		if (UserValidator.UpdatevalidateUser(user)) {
			UserDAO.updateUser(user);
		}

		return true;
	}

	public static boolean deleteUser(String email) throws SQLException, ConnectionException, DataException {
		if (UserValidator.validateEmail(email)) {
			UserDAO.deactivateUser(email);
		}
		return true;
	}

	public static boolean isExists(String email, String password)
			throws SQLException, ConnectionException, DataException {
		if (UserValidator.validateEmail(email) && UserValidator.validatePassword(password)) {
			UserDAO.isExists(email, password);
		}

		return true;
	}

	public static User details(String email) throws SQLException, ConnectionException, DataException {

		if (UserValidator.validateEmail(email)) {
			User ans=UserDAO.details(email);
			if(ans!=null) {
				return ans;
			}
		}

		return null;
	}

	public static boolean updateTransferStatus(String status, int userId) throws SQLException, ConnectionException {

	   boolean josh=UserDAO.updateTransferStatus(status, userId);
	   return josh;

	}
	

}
