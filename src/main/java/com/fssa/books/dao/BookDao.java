package com.fssa.books.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import com.fssa.books.exception.BookDAOCRUDException;
import com.fssa.books.exception.DataException;
import com.fssa.books.model.Book;
import com.fssa.books.model.BookCategory;
import com.fssa.books.util.CustomLogger;
import com.fssa.connection.ConnectionUtil;
import com.fssa.connection.exception.ConnectionException;

public class BookDao {
	private BookDao() {
		super();

	}

	private static final String ADD_QUERY = "INSERT INTO books (title, author, publisheddate, publisher, bookimageurl, edition,category,stock) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
	private static final String READ_QUERY = "SELECT * FROM books";
	private static final String UPDATE_QUERY = "UPDATE books SET title=?, author=?, publisheddate=?, publisher=?, bookimageurl=?, edition=?,category=?,stock=? WHERE book_id=?";
	private static final String DELETE_QUERY = "DELETE FROM books WHERE book_id= ?";
	private static final String READ_BY_CATEGORY_QUERY = "SELECT * FROM books WHERE  category = ?";
	private static final String UPDATE_STOCK = "UPDATE books SET stock = ? WHERE book_id = ?";
	private static final String UPDATE_ON_ACTION = "UPDATE books b SET stock = CASE WHEN (SELECT status FROM transfer t WHERE t.request_id = ? AND t.book_id = b.book_id) = 'RETURNED' THEN b.stock + 1 WHEN (SELECT status FROM transfer t WHERE t.request_id = ? AND t.book_id = b.book_id) = 'ACCEPTED' THEN b.stock - 1 ELSE b.stock END WHERE b.book_id = ?";
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
				pstmt.setInt(8, book.getStock());
				pstmt.executeUpdate();

			}
		} catch (SQLException e) {
			CustomLogger.info(e.getMessage());
			return false;

		}
		return true;

	}

	public static List<Book> readBooks() throws SQLException, DataException, ConnectionException {
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
						book.setStock(rs.getInt("stock"));
						booklist.add(book);
					}
				}
			}
		}

		return booklist;
	}

	public static BookCategory fromString(String categoryStr) throws DataException {
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
				pstmt.setInt(9, book.getId()); // Use the provided ID for WHERE clause
				pstmt.setInt(8, book.getStock());
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
	public static boolean updateStockBasedOnAdminAction(int bookId,int requestId) throws SQLException, ConnectionException {
		
		try(Connection connection=ConnectionUtil.getConnection()){
			try(PreparedStatement pstmt = connection.prepareStatement(UPDATE_ON_ACTION)){ 
			    pstmt.setInt(1, requestId);
			    pstmt.setInt(2, requestId);
			    pstmt.setInt(3, bookId);
			    System.out.println(pstmt);
			    pstmt.executeUpdate();
			} catch (SQLException e) {
			    e.printStackTrace();
			    throw e; // Handle or log the exception appropriately
			}
		}
		return true;
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

	public static boolean updateBookStocks(int stocks,int bookId) throws SQLException, ConnectionException, BookDAOCRUDException {
		try (Connection conn = ConnectionUtil.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_STOCK)) {
				pstmt.setInt(1, stocks);
				pstmt.setInt(2,bookId);
				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected > 0) {
					CustomLogger.info("Updated Successfully");
		            
				} else {
					Book book=new Book();
					throw new BookDAOCRUDException("Failed to update book with ID: " + book.getId());
				}
			}
		}
		return true;
	}

	public static List<Book> readBooksByCategory(String category)
			throws SQLException, DataException, ConnectionException {
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
						book.setStock(rs.getInt("stock"));
						String categoryName = rs.getString("category"); // Assuming the category column is retrieved as
						// a string

// Convert the category string to enum (assuming you have a method to do that)
						BookCategory genre = fromString(categoryName);
						book.setCategoryname(genre);
						booklist.add(book);
					}
				}
			}
		} catch (SQLException | DataException e) {

		}

		return booklist;
	}
}
