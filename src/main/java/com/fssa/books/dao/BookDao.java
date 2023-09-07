<<<<<<< HEAD
package com.fssa.books.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fssa.books.exception.BookDAOCRUDException;
import com.fssa.books.exception.BookDataException;
import com.fssa.books.model.Book;
import com.fssa.books.model.BookCategory;
import com.fssa.books.util.CustomLogger;
import com.fssa.connection.ConnectionUtil;
import com.fssa.connection.exception.ConnectionException;

public class BookDao {
	private BookDao() {
		super();

	}

	private static final String ADD_QUERY = "INSERT INTO books (title, author, publisheddate, publisher, bookimageurl, edition,category) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String READ_QUERY = "SELECT * FROM books";
	private static final String UPDATE_QUERY = "UPDATE books SET title=?, author=?, publisheddate=?, publisher=?, bookimageurl=?, edition=?,category=? WHERE book_id=?";
	private static final String DELETE_QUERY = "DELETE FROM books WHERE book_id= ?";
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
		} catch (SQLException e) {
			CustomLogger.info(e.getMessage());
			e.printStackTrace();
		}
		return true;

	}

	public static List<Book> readBooks() throws SQLException, ConnectionException, BookDataException {
		List<Book> booklist = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			try (Statement st = conn.createStatement()) { // Change to the query to retrieve all books
				try (ResultSet rs = st.executeQuery(READ_QUERY)) {
					while (rs.next()) {
						Book book = new Book();
						book.setId(rs.getInt("book_id"));
						book.setTitle(rs.getString("title"));
						book.setAuthor(rs.getString("author"));
						book.setPublisheddate(rs.getDate("publisheddate").toLocalDate());
						book.setPublishername(rs.getString("publisher"));
						book.setBookimageurl(rs.getString("bookimageurl"));
						book.setEdition(rs.getInt("edition"));
						BookCategory category = fromString(rs.getString("category"));
						book.setCategoryname(category);
						booklist.add(book);
					}
				}
			}
		}

		return booklist;
	}

	public static BookCategory fromString(String categoryStr) throws BookDataException {
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

					return true;
				} else {
					CustomLogger.info("Failed to delete the book");
					return false;
				}
			}
		}
	}

	public static List<Book> readBooksByCategory(String category) throws SQLException, BookDataException {
		List<Book> booklist = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			try (PreparedStatement st = conn.prepareStatement(READ_BY_CATEGORY_QUERY)) {
				st.setString(1, category);
				try (ResultSet rs = st.executeQuery()) {
					while (rs.next()) {
						
						Book book = new Book();
						book.setId(rs.getInt("book_id"));
						book.setTitle(rs.getString("title"));
						book.setAuthor(rs.getString("author"));
						book.setPublisheddate(rs.getDate("publisheddate").toLocalDate());
						book.setPublishername(rs.getString("publisher"));
						book.setBookimageurl(rs.getString("bookimageurl"));
						book.setEdition(rs.getInt("edition"));
						String categoryName = rs.getString("category"); // Assuming the category column is retrieved as
						// a string

// Convert the category string to enum (assuming you have a method to do that)
                       BookCategory genre = fromString(categoryName);
                       book.setCategoryname(genre);
						booklist.add(book);
					}
				}
			}
		} catch (SQLException | BookDataException e) {
			e.printStackTrace();
		}

		return booklist;
	}
}
=======
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
import com.fssa.books.model.Book;
import com.fssa.books.model.BookCategory;
import com.fssa.books.util.*;

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
		catch(SQLException e) {
			CustomLogger.info(e.getMessage());
			e.printStackTrace();
		}
		return true;
		
	}



	public static List<Book> readBooks() throws SQLException, ConnectionException, BookDataException {
	    List<Book> booklist = new ArrayList<Book>();

	    try (Connection conn = ConnectionUtil.getConnection()) {
	        try (PreparedStatement st = conn.prepareStatement(READ_QUERY)) { // Change to the query to retrieve all books
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
	    
	    
	    public static List<Book> readBooksByCategory(String category) throws SQLException, BookDataException {
		    List<Book> booklist = new ArrayList<>();

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
		    catch(SQLException|BookDataException  e) {
		    	e.printStackTrace();
		    }

		    return booklist;
		}
}

>>>>>>> 92b7872f20fe8eb92f296630d6ec9bed3b1812a9
