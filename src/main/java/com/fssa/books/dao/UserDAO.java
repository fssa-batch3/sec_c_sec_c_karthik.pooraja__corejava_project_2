package com.fssa.books.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import com.fssa.books.exception.DataException;
import com.fssa.books.model.Role;
import com.fssa.books.model.User;
import com.fssa.books.model.Status;
import com.fssa.books.model.Transfer;
import com.fssa.books.model.TransferStatus;
import com.fssa.books.util.CustomLogger;
import com.fssa.connection.ConnectionUtil;
import com.fssa.connection.exception.ConnectionException;
import com.fssa.books.model.Book;
import com.fssa.books.model.BorrowHistory;
import com.fssa.books.model.ReturnHistory;

public class UserDAO {

	private static final String ADD_USER_SQL = "INSERT INTO users (name, phoneNumber, email, password, role,status,transfer_status) VALUES (?, ?, ?, ?, ?,?,?)";
	private static final String READ_USER_SQL = "SELECT name,phoneNumber,email,password,role FROM users WHERE email = ? AND status=1 ";
	private static final String UPDATE_USER_SQL = "UPDATE users SET name = ?, phoneNumber = ?, password = ? WHERE id = ?";
	private static final String DEACTIVATE_USER_SQL = "UPDATE users SET status = false WHERE email = ?";
	private static final String IS_EXIST = "SELECT COUNT(*) FROM users WHERE email = ? AND password = ?";
	private static final String USER_DETAILS = "SELECT id,name,phoneNumber,email,password,role,transfer_status FROM users WHERE email = ?";
	private static final String SHOWING_USER_BORROW_DETAILS = "SELECT transfer.request_id, users.name, users.email, books.title,books.book_id,transfer.status FROM transfer  INNER JOIN users ON transfer.user_id = users.id INNER JOIN books ON transfer.book_id = books.book_id WHERE transfer.user_id=? AND transfer.status <> 'RETURNED'";
	private static final String SHOWING_USER_RETURN_DETAILS = "SELECT users.name AS user_name, users.email AS user_email, books.title AS book_title, books.book_id AS book_id, transfer.start_date AS start_date, transfer.end_date AS end_date, transfer.request_id AS request_Id FROM transfer INNER JOIN users ON transfer.user_id = users.id INNER JOIN books ON transfer.book_id = books.book_id WHERE transfer.start_date IS NOT NULL AND users.id = ?";
    private static final String  UPDATING_QUERY= "UPDATE users SET transfer_status = ? WHERE id = ?";

	public static boolean addUser(User user) throws ConnectionException, SQLException {
		int rowsUpdated = 0;
		try (Connection connection = ConnectionUtil.getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(ADD_USER_SQL)) {

				statement.setString(1, user.getName());
				statement.setString(2, user.getPhoneNumber());
				statement.setString(3, user.getEmail());
				statement.setString(4, user.getPassword());
				statement.setString(5, user.getRole().BORROWER.toString());
				statement.setBoolean(6, true);
				statement.setString(7, user.isTransfer_status().INLIMIT.toString());
				rowsUpdated = statement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return rowsUpdated > 0;
	}

	public static User readUser(String email) throws ConnectionException, SQLException, DataException {
		User user = null;

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(READ_USER_SQL)) {

			statement.setString(1, email);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String name = resultSet.getString("name");
				String phoneNumber = resultSet.getString("phoneNumber");
				String userEmail = resultSet.getString("email");
				String password = resultSet.getString("password");
				// Assuming User class has a Role property
				Role role = fromString(resultSet.getString("role"));

				// Create a User object
				user = new User(name, phoneNumber, userEmail, password);

				// Set the role property on the user object
				user.setRole(role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	public static Role fromString(String categoryStr) throws DataException {
		return Role.valueOf(categoryStr);
	}

	public static boolean updateUser(User user) throws ConnectionException, SQLException {
		int rowsUpdated = 0;
		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL)) {
				statement.setString(1, user.getName());
				statement.setString(2, user.getPhoneNumber());
				statement.setString(3, user.getPassword());
				statement.setInt(4, user.getId());
				rowsUpdated = statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsUpdated > 0;
	}

	public static boolean deactivateUser(String email) throws ConnectionException, SQLException {
		int rowsUpdated = 0;
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(DEACTIVATE_USER_SQL)) {

			statement.setString(1, email);
			System.out.println(statement);
			rowsUpdated = statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsUpdated > 0;

	}

	public static boolean isExists(String email, String password) throws ConnectionException, SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(IS_EXIST)) {
				statement.setString(1, email);
				statement.setString(2, password);
				try (ResultSet rs = statement.executeQuery()) {
					if (rs.next()) {
						int count = rs.getInt(1);
						return count > 0;
					}
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	public static User details(String email) throws ConnectionException, DataException {
		// User user = null; // Initialize the User object to null

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement statement = connection.prepareStatement(USER_DETAILS)) {

				statement.setString(1, email);

				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) {
						User user = new User();
						user.setId(resultSet.getInt("id"));
						user.setName(resultSet.getString("name"));
						user.setPhoneNumber(resultSet.getString("phoneNumber"));
						user.setEmail(resultSet.getString("email"));
						user.setPassword(resultSet.getString("password"));
						String roleValue = resultSet.getString("role");
						String transfer = resultSet.getString("transfer_status");
						if (roleValue != null) {
							user.setRole(Role.valueOf(roleValue));
						}
						if (transfer != null) {
							user.setTransfer_status(Status.valueOf(transfer));
						}

						CustomLogger.info(user);
						return user;
						
					}
				}
			}
		} catch (SQLException | ConnectionException e) {
			CustomLogger.info(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static boolean updateTransferStatus(String status,int userId) throws SQLException, ConnectionException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATING_QUERY)) {
			    if (status.equals("INLIMIT")) {
			    	
			        preparedStatement.setString(1, Status.ABOVELIMIT.toString());
			    } else if (status.equals("ACCEPTED")) {
			    	
			        preparedStatement.setString(1, Status.BORROWED.toString());
			    } else if(status.equals("RETURNED")||status.equals("REJECTED")){
			        preparedStatement.setString(1, Status.INLIMIT.toString());
			    }
			    preparedStatement.setInt(2, userId);
			    System.out.println(preparedStatement);
			    preparedStatement.executeUpdate();
			}

		}
		return true;

	}

	public static List<BorrowHistory> getUserBorrowDetails(int userId) throws SQLException, ConnectionException {
		List<BorrowHistory> borrowHistory = new ArrayList<>();
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SHOWING_USER_BORROW_DETAILS)) {
			preparedStatement.setInt(1, userId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					String userName = resultSet.getString("users.name");
					String userEmail = resultSet.getString("users.email");
					String bookTitle = resultSet.getString("books.title");
					String transferStatus = resultSet.getString("transfer.status");
					int requestId = resultSet.getInt("transfer.request_id");
					int bookId = resultSet.getInt("books.book_id");
					Book book = new Book();
					book.setTitle(bookTitle);
					book.setId(bookId);

					// Create a new User object and set its attributes
					User user = new User();
					user.setName(userName);
					user.setEmail(userEmail);

					// Create a new Transfer object and set its attributes
					Transfer transfer = new Transfer();
					if (transferStatus != null) {
						transfer.setStatus(TransferStatus.valueOf(transferStatus));
						transfer.setRequestId(requestId);
					}

					// Create a new BorrowHistory object using the CombinedData
					BorrowHistory history = new BorrowHistory(book, user, transfer);

					// Add the BorrowHistory object to the list
					borrowHistory.add(history);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e; // Handle or log the exception appropriately
		}
		return borrowHistory;
	}

	public static List<ReturnHistory> getUserReturnDetails(int userId) throws SQLException, ConnectionException {
		List<ReturnHistory> transactions = new ArrayList<>();
		try (Connection connection = ConnectionUtil.getConnection()) {
			try (PreparedStatement preparedStatement = connection.prepareStatement(SHOWING_USER_RETURN_DETAILS)) {
				preparedStatement.setInt(1, userId); // Set the user ID parameter
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						String userName = resultSet.getString("user_name"); // Use the alias
						String userEmail = resultSet.getString("user_email"); // Use the alias
						String bookTitle = resultSet.getString("book_title"); // Use the alias
						LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
						LocalDate endDate = resultSet.getDate("end_date") != null
								? resultSet.getDate("end_date").toLocalDate()
								: null;
						int bookId = resultSet.getInt("book_id");
						int RequestId = resultSet.getInt("request_id");

						// Create a new Book object and set its attributes
						Book book = new Book();
						book.setTitle(bookTitle);
						book.setId(bookId);
						// Create a new User object and set its attributes
						User user = new User();
						user.setName(userName);
						user.setEmail(userEmail);

						// Create a new Transfer object
						Transfer transfer = new Transfer();
						transfer.setStartDate(startDate);
						transfer.setEndDate(endDate);
						transfer.setRequestId(RequestId);

						// Create a new ReturnHistory object using the updated constructor
						ReturnHistory history = new ReturnHistory(book, user, transfer);

						// Add the ReturnHistory object to the list
						transactions.add(history);
					}
				}
			}
		} catch (SQLException | ConnectionException e) {
			e.printStackTrace();
		}
		return transactions;
	}
}
