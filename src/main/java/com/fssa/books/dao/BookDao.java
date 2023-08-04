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
import com.fssa.books.exception.BookValidatorDAOCustomExceptionMessage;
import com.fssa.books.model.Book;



public class BookDao {
	private static final String ADD_QUERY = "INSERT INTO Books (title, author, publisheddate, publisher, bookimageurl, edition,category) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String READ_QUERY = "SELECT * FROM Books";
	private static final String UPDATE_QUERY = "UPDATE Books SET title=?, author=?, publisheddate=?, publisher=?, bookimageurl=?, edition=?,category=? WHERE book_id=?";
	private static final String DELETE_QUERY = "DELETE FROM Books WHERE book_id=?";
	
	
    public static boolean addBooks(Book book) throws BookDAOCRUDException, SQLException{
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
             return true;              
            }
        }
    }
    
    
    public static boolean readBooks() throws BookDAOCRUDException,SQLException {

        try (Connection conn = ConnectionUtil.getConnection()) {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery(READ_QUERY)) {
                    while (rs.next()) {
                        int id = rs.getInt("book_id");
                        String title = rs.getString("title");
                        String author = rs.getString("author");
                        LocalDate publishedDate = rs.getDate("publisheddate").toLocalDate();
                        String publisher = rs.getString("publisher");
                        String bookImageUrl = rs.getString("bookimageurl");
                        int edition = rs.getInt("edition");
                        String category = rs.getString("category");           
                    }
                }
            }
        }

        return true;
    }
    
    
    public static boolean updateBooks(Book book) throws BookDAOCRUDException, SQLException {
        try (Connection conn = ConnectionUtil.getConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_QUERY)) {
                pstmt.setString(1, book.getTitle());
                pstmt.setString(2, book.getAuthor());
                pstmt.setDate(3, java.sql.Date.valueOf(book.getPublisheddate()));
                pstmt.setString(4, book.getPublishername());
                pstmt.setString(5, book.getBookimageurl());
                pstmt.setInt(6, book.getEdition());
                pstmt.setString(7, book.getCategoryname().getBookCategory()); // Use getBookingCategory()
                pstmt.setInt(8,book.getId()); // Use the provided ID for WHERE clause
                 System.out.println(pstmt);
                int rowsAffected = pstmt.executeUpdate();
                System.out.println(rowsAffected);
                if (rowsAffected > 0) {
                    System.out.println("Updated Successfully");
                    return true; // Book updated successfully
                } else {
                    throw new BookDAOCRUDException("Failed to update book with ID: " + book.getId());
                }
            }
        }
    }

    
    
    public static boolean deleteBooks(int bookId) throws BookDAOCRUDException,SQLException {
        try (Connection conn = ConnectionUtil.getConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement(DELETE_QUERY)) {
                pstmt.setInt(1, bookId);

                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Deleted Successfully");
                    return true; // Book deleted successfully
                } else {
                    System.out.println("Failed to delete the book");
                    return false; // Failed to delete the book
                }
            }
        }
    }
    	    
    

}

    


