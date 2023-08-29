package com.fssa.books.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;


import com.fssa.connection.*;
import com.fssa.connection.exception.ConnectionException;
import com.fssa.books.exception.BookDAOCRUDException;
import com.fssa.books.exception.BookDataException;
import com.fssa.util.*;

import com.fssa.books.model.Book;
import com.fssa.books.model.BookCategory;

public class BookDao {
	private BookDao() {
		super();

	}

	private static final String ADD_QUERY = "INSERT INTO books (title, author, publisheddate, publisher, bookimageurl, edition,category) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String READ_QUERY = "SELECT * FROM books";
	private static final String UPDATE_QUERY = "UPDATE books SET title=?, author=?, publisheddate=?, publisher=?, bookimageurl=?, edition=?,category=? WHERE book_id=?";
	private static final String DELETE_QUERY = "DELETE FROM books WHERE book_id=?";
	private static final String READ_BY_CATEGORY_QUERY = "SELECT * FROM books WHERE  category = ?";

	public static boolean addBooks(Book book) throws SQLException, ConnectionException {
		try (Connection conn = ConnectionUtil.getConnection()) {

			try (PreparedStatement pstmt = conn.prepareStatement(ADD_QUERY)) {

				pstmt.setString(1, book.getTitle());
				pstmt.setString(2, book.getAuthor());
				pstmt.setDate(3, java.sql.Date.valueOf(book.getPublisheddate()));
				pstmt.setString(4, book.getPublishername());
				pstmt.setString(5, book.getBookimageurl());
				pstmt.setInt(6, book.getEdition());
				pstmt.setString(7, book.getCategoryname().getBookCategory());
				pstmt.executeUpdate();
				
			}
		} 
		catch(SQLException| ConnectionException e) {
			CustomLogger.info(e.getMessage());
			e.printStackTrace();
		}
		return true;
		
	}



	public static List<Book> readBooks(String name) throws SQLException, ConnectionException, BookDataException {
	    List<Book> booklist = new ArrayList<Book>();

	    try (Connection conn = ConnectionUtil.getConnection()) {
	        try (PreparedStatement st = conn.prepareStatement(READ_QUERY)) {
	        	st.setString(1, name);
	            try (ResultSet rs = st.executeQuery()) {
	                while (rs.next()) {
	                    int id = rs.getInt("book_id");
	                    String title = rs.getString("title");
	                    String author = rs.getString("author");
	                    LocalDate publishedDate = rs.getDate("publisheddate").toLocalDate();
	                    String publisher = rs.getString("publisher");
	                    String bookImageUrl = rs.getString("bookimageurl");
	                    int edition = rs.getInt("edition");
	                    String categoryStr = rs.getString("category"); // Assuming the category column is retrieved as a string

	                    // Convert the category string to enum (assuming you have a method to do that)
	                    BookCategory category = fromString(categoryStr);

	                    // Create the Book object
	                    Book book = new Book(id, title, edition, author, publishedDate, publisher, category, bookImageUrl);
	                    booklist.add(book);
	                }
	            }
	        }
	    }

	    return booklist;
	}
		    
	    public static BookCategory fromString(String categoryStr)throws BookDataException{
	        return BookCategory.valueOf(categoryStr);  
	    }
	



	public static boolean updateBooks(Book book) throws BookDAOCRUDException, SQLException, ConnectionException {
		try (Connection conn = ConnectionUtil.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_QUERY)) {
				pstmt.setString(1, book.getTitle());
				pstmt.setString(2, book.getAuthor());
				pstmt.setDate(3, java.sql.Date.valueOf(book.getPublisheddate()));
				pstmt.setString(4, book.getPublishername());
				pstmt.setString(5, book.getBookimageurl());
				pstmt.setInt(6, book.getEdition());
				pstmt.setString(7, book.getCategoryname().getBookCategory()); // Use getBookingCategory()
				pstmt.setInt(8, book.getId()); // Use the provided ID for WHERE clause
				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected > 0) {
					CustomLogger.info("Updated Successfully");
					return true; // Book updated successfully
				} else {
					throw new BookDAOCRUDException("Failed to update book with ID: " + book.getId());
				}
			}
		}
	}

	public static boolean deleteBooks(int bookId) throws SQLException, ConnectionException {
		try (Connection conn = ConnectionUtil.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(DELETE_QUERY)) {
				pstmt.setInt(1, bookId);

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					CustomLogger.info("Deleted Successfully");

					return true; // Book deleted successfully
				} else {
					CustomLogger.info("Failed to delete the book");
					return false;
				}
			}
		}
	}
	    
	    
	    public static List<Book> readBooksByCategory(String category) throws SQLException, ConnectionException, BookDataException {
		    List<Book> booklist = new ArrayList<Book>();

		    try (Connection conn = ConnectionUtil.getConnection()) {
		        try (PreparedStatement st = conn.prepareStatement(READ_BY_CATEGORY_QUERY)) {
		        	st.setString(1, category);
		            try (ResultSet rs = st.executeQuery()) {
		                while (rs.next()) {
		                    int id = rs.getInt("book_id");
		                    String title = rs.getString("title");
		                    String author = rs.getString("author");
		                    LocalDate publishedDate = rs.getDate("publisheddate").toLocalDate();
		                    String publisher = rs.getString("publisher");
		                    String bookImageUrl = rs.getString("bookimageurl");
		                    int edition = rs.getInt("edition");
		                    String categoryName = rs.getString("category"); // Assuming the category column is retrieved as a string

		                    // Convert the category string to enum (assuming you have a method to do that)
		                    BookCategory genre = fromString(categoryName);

		                    // Create the Book object
		                    Book book = new Book(id, title, edition, author, publishedDate, publisher, genre, bookImageUrl);
		                    booklist.add(book);
		                }
		            }
		        }
		    }
		    catch(SQLException|ConnectionException|BookDataException  e) {
		    	e.printStackTrace();
		    }

		    return booklist;
		}


	        private static final String DB_URL = "jdbc:mysql://localhost:3306/core_java_project";
	        private static final String DB_USER = "root";
	        private static final String DB_PASSWORD = "123456";

	        public static boolean doesUserExist(String username) {
	            boolean userExists = false;

	            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
	                String query = "SELECT COUNT(*) FROM lms WHERE title = ?";
	                
	                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	                    preparedStatement.setString(1, username);

	                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                        if (resultSet.next()) {
	                            int count = resultSet.getInt(1);
	                            userExists = count > 0;
	                        }
	                    }
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }

	            return userExists;
	        }

	        public static void main(String[] args) {
	            String usernameToCheck = "user123"; // Replace with the username you want to check
	            boolean exists = doesUserExist(usernameToCheck);

	            if (exists) {
	                System.out.println("User exists in the database.");
	            } else {
	                System.out.println("User does not exist in the database.");
	            }
	        }
	    }

	    


