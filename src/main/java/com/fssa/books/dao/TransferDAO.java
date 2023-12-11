package com.fssa.books.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import com.fssa.books.model.BorrowHistory;
import com.fssa.books.model.ReturnHistory;
import com.fssa.books.model.Role;
import com.fssa.books.model.Status;
import com.fssa.books.model.Transfer;
import com.fssa.books.model.TransferStatus;
import com.fssa.books.model.User;
import com.fssa.books.model.Book;
import com.fssa.connection.ConnectionUtil;
import com.fssa.connection.exception.ConnectionException;

public class TransferDAO {

	private static final String insertQuery = "INSERT INTO transfer (user_id, book_id, request_date, status, start_date, end_date,extended_days) VALUES (?, ?, NOW(), 'PENDING', NULL, NULL,0)";
	private static final String showingReturns = "SELECT users.name AS user_name, users.email AS user_email, books.title AS book_title, books.book_id AS book_id, transfer.start_date AS start_date, transfer.end_date AS end_date, transfer.request_id AS request_Id FROM transfer INNER JOIN users ON transfer.user_id = users.id INNER JOIN books ON transfer.book_id = books.book_id WHERE transfer.start_date IS NOT NULL";
	private static final String showingBorrows = "SELECT transfer.request_id, users.name, users.email, books.title, books.book_id, transfer.status FROM transfer INNER JOIN users ON transfer.user_id = users.id INNER JOIN books ON transfer.book_id = books.book_id WHERE transfer.status <> 'RETURNED'";
    private static final String AcceptedQuery = "UPDATE transfer SET status = ?, start_date = CASE WHEN ? = 'ACCEPTED' THEN NOW() ELSE NULL END WHERE request_id = ?";
    private static final String ReturnQuery = "UPDATE transfer SET status = ?, end_date = CASE WHEN ? = 'RETURNED' THEN NOW() ELSE NULL END WHERE request_id = ?";
    private static final String UserRejectionQuery="UPDATE transfer SET status = 'REJECTEDBYUSER' WHERE book_id = ? AND status = 'PENDING'";
    private static final String STATUS = "SELECT book_id FROM transfer WHERE user_id = ? AND status='PENDING'";

    public static boolean insertTransferRequest(int bookid,int userid) throws SQLException, ConnectionException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
				preparedStatement.setInt(1, userid);
				preparedStatement.setInt(2, bookid);

				preparedStatement.executeUpdate();
			}
			catch(SQLException e) {
				e.printStackTrace();
				return false;
			}

		}
		return true;
	}
	 

	    public static boolean updateAcceptStatusAndDates(int requestId, String adminSelection) throws SQLException,ConnectionException {
	        try (Connection connection = ConnectionUtil.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(AcceptedQuery)) {

	            preparedStatement.setString(1,adminSelection);
	            preparedStatement.setString(2, adminSelection);
	            preparedStatement.setInt(3, requestId);

	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            // Handle any potential SQLException here
	            e.printStackTrace();
	        }
	        return true;
	    }
	    public static boolean updateReturnStatusAndDates(int requestId, String adminSelection) throws SQLException,ConnectionException {
	        try (Connection connection = ConnectionUtil.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(ReturnQuery)) {

	            preparedStatement.setString(1,adminSelection);
	            preparedStatement.setString(2, adminSelection);
	            preparedStatement.setInt(3, requestId);
	            System.out.println(preparedStatement);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            // Handle any potential SQLException here
	            e.printStackTrace();
	        }
	        return true;
	    }


	    public static List<BorrowHistory> showAllBorrowHistory() throws SQLException, ConnectionException {
	        List<BorrowHistory> transactions = new ArrayList<>();
	        try (Connection connection = ConnectionUtil.getConnection()) {
	            try (PreparedStatement preparedStatement = connection.prepareStatement(showingBorrows)) {
	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    while (resultSet.next()) {
	                    	
	                        String userName = resultSet.getString("users.name");
	                        String userEmail = resultSet.getString("users.email");
	                        String bookTitle = resultSet.getString("books.title");
	                        String transferStatus =resultSet.getString("transfer.status");
	                        int requestId =resultSet.getInt("transfer.request_id");
	                        int bookId=resultSet.getInt("books.book_id");
							

	                     // Create a new Book object and set its attributes
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
	                        BorrowHistory history = new BorrowHistory(book,user,transfer);

	                        // Add the BorrowHistory object to the list
	                        transactions.add(history);
	                    }
	                }
	            }
	        }
	        catch(SQLException| ConnectionException e) {
	        	e.printStackTrace();
	        }
	        return transactions;
	    }
	    
	    public static List<ReturnHistory> showAllReturnHistory() throws SQLException, ConnectionException {
	        List<ReturnHistory> transactions = new ArrayList<>();
	        try (Connection connection = ConnectionUtil.getConnection()) {
	            try (PreparedStatement preparedStatement = connection.prepareStatement(showingReturns)) {
	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    while (resultSet.next()) {
	                        String userName = resultSet.getString("user_name"); // Use the alias
	                        String userEmail = resultSet.getString("user_email"); // Use the alias
	                        String bookTitle = resultSet.getString("book_title"); // Use the alias
	                        LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
	                        LocalDate endDate = resultSet.getDate("end_date") != null ? resultSet.getDate("end_date").toLocalDate() : null;
	                        int bookId=resultSet.getInt("book_id");
	                        int RequestId=resultSet.getInt("request_id");
	              

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
	    public static boolean UserRejection(int bookid) throws SQLException, ConnectionException{
	    	try(Connection connection=ConnectionUtil.getConnection()){
	    		try(PreparedStatement pstmt=connection.prepareStatement(UserRejectionQuery)){
	    			pstmt.setInt(1,bookid);
	    			pstmt.executeUpdate();
	    		}
	    		
	    	}
	    	catch(SQLException|ConnectionException e){
	    		e.printStackTrace();
	    	}
	    	return true;
	    	
	    }
	    public static int getBookIdWithPendingStatus(int userId) throws ConnectionException, SQLException {
	        try (Connection connection = ConnectionUtil.getConnection()) {
	            try (PreparedStatement preparedStatement = connection.prepareStatement(STATUS)) {
	                preparedStatement.setInt(1, userId);

	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    if (resultSet.next()) {
	                        return resultSet.getInt("book_id");
	                    }
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	                // Handle exceptions as needed
	            }
	        }

	        // Return a default value or handle the case where no book ID was found
	        return -1; // You can use another default value if -1 is not suitable
	    }


	    

	    

}
