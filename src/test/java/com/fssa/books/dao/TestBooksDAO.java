package com.fssa.books.dao;



import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.fssa.books.*;
import com.fssa.books.exception.BookDAOCRUDException;
import com.fssa.books.model.BookCategory;
import com.fssa.books.model.Book;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.time.LocalDate;

public class TestBooksDAO {

    @Test
    public void testAddBooks() throws SQLException, BookDAOCRUDException {
        // Create a book object with the desired details
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setPublisheddate(LocalDate.of(2023, 7, 31));
        book.setPublishername("Test Publisher");
        book.setBookimageurl("http://example.com/test-book.jpg");
        book.setEdition(1);
        book.setCategoryname(BookCategory.FICTION);

        boolean result = BookDao.addBooks(book);
        Assertions.assertTrue(result); // Check if the book was added successfully
    }

    @Test
    public void testReadBooks() throws SQLException, BookDAOCRUDException {
        Assertions.assertTrue(BookDao.readBooks());
    }

    @Test
    public void testUpdateBooks() throws SQLException, BookDAOCRUDException {
        // Create an updated book object with new details
        Book updatedBook = new Book();
        updatedBook.setId(3); // Replace with the actual ID of the book to update
        updatedBook.setTitle("Updated Title");
        updatedBook.setAuthor("Updated Author");
        updatedBook.setPublisheddate(LocalDate.of(2023, 8, 1));
        updatedBook.setPublishername("Updated Publisher");
        updatedBook.setBookimageurl("http://example.com/updated-image.jpg");
        updatedBook.setEdition(2);
        updatedBook.setCategoryname(BookCategory.FANTASY);

        // Call the updateBooks method and assert the result
        boolean updateResult = BookDao.updateBooks(updatedBook); // Use the actual ID
        Assertions.assertTrue(updateResult);
    }

    @Test
    public void testDeleteBook() throws SQLException, BookDAOCRUDException {
        // Call the deleteBooks method and assert the result
        boolean deleteResult = BookDao.deleteBooks(4); // Use the actual ID
        Assertions.assertTrue(deleteResult); // Check if the book was deleted successfully
    }
}
