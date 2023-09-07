package com.fssa.books.dao;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.fssa.books.exception.BookDAOCRUDException;
import com.fssa.books.exception.BookDataException;
import com.fssa.books.model.Book;
import com.fssa.books.model.BookCategory;
import com.fssa.books.service.BookService;
import com.fssa.books.util.CustomLogger;
import com.fssa.connection.exception.ConnectionException;

/**
 * This method tests the addition of a book to the database using the BookService class.
 *
 * @throws SQLException            If a database access error occurs.
 * @throws BookDAOCRUDException     If an error related to CRUD operations on books occurs.
 * @throws ConnectionException     If there is an issue with the database connection.
 * @throws BookDataException       If there is an issue with book data.
 */
class TestBooksDAO {
@Test
void testAddBooks() throws SQLException, BookDAOCRUDException, ConnectionException, BookDataException {
    // Create a book object with the desired details
    Book book = new Book();
    book.setTitle("Journey to the End of the Night");
    book.setAuthor("Louis-Ferdinand");
    book.setPublisheddate(LocalDate.of(1932, 9, 19));
    book.setPublishername("Penguin Modern Classics");
    book.setBookimageurl("https://i.pinimg.com/736x/66/b2/68/66b2685be7b2bb138fb547ea5f690a3a--to-the-end-book-cover-design.jpg");
    book.setEdition(1);
    book.setCategoryname(BookCategory.FICTION);
    Assertions.assertTrue(BookService.addBooks(book));
}

/**
 * This method tests the reading of books from the database using the BookService class.
 *
 * @throws SQLException            If a database access error occurs.
 * @throws BookDAOCRUDException     If an error related to CRUD operations on books occurs.
 * @throws ConnectionException     If there is an issue with the database connection.
 * @throws BookDataException       If there is an issue with book data.
 */
@Test
void testReadBooks() throws SQLException, BookDAOCRUDException, ConnectionException, BookDataException {
    List<Book> bookList = BookService.readBooks();

    for (Book e : bookList) {
        CustomLogger.info(e);
    }

    Assertions.assertNotNull(bookList);
}

/**
 * This method tests the updating of a book in the database using the BookService class.
 *
 * @throws SQLException            If a database access error occurs.
 * @throws BookDAOCRUDException     If an error related to CRUD operations on books occurs.
 * @throws ConnectionException     If there is an issue with the database connection.
 * @throws BookDataException       If there is an issue with book data.
 */
@Test
void testUpdateBooks() throws SQLException, BookDAOCRUDException, ConnectionException, BookDataException {
    // Create an updated book object with new details
    Book book = new Book();
    book.setId(3);
    book.setTitle("Journey to the End of the Night");
    book.setAuthor("Louis-Ferdinand");
    book.setPublisheddate(LocalDate.of(1932, 9, 19));
    book.setPublishername("Penguin Modern Classics");
    book.setBookimageurl("https://i.pinimg.com/736x/66/b2/68/66b2685be7b2bb138fb547ea5f690a3a--to-the-end-book-cover-design.jpg");
    book.setEdition(1);
    book.setCategoryname(BookCategory.FICTION);

    Assertions.assertTrue(BookService.updateBooks(book));

    // Call the updateBooks method and assert the result
    boolean updateResult;
    try {
        updateResult = BookService.updateBooks(book);
        Assertions.assertTrue(updateResult);
    } catch (BookDataException | BookDAOCRUDException | SQLException | ConnectionException e) {
        e.printStackTrace();
    }
}

/**
 * This method tests the deletion of a book from the database using the BookDao class.
 *
 * @throws SQLException            If a database access error occurs.
 * @throws BookDAOCRUDException     If an error related to CRUD operations on books occurs.
 * @throws ConnectionException     If there is an issue with the database connection.
 */
@Test
void testDeleteBook() throws SQLException, BookDAOCRUDException, ConnectionException {
    // Call the deleteBooks method and assert the result
    boolean deleteResult = BookDao.deleteBooks(3);
    Assertions.assertTrue(deleteResult);
}

/**
 * This method tests the retrieval of all books in a specific category from the database using the BookService class.
 *
 * @throws SQLException            If a database access error occurs.
 * @throws ConnectionException     If there is an issue with the database connection.
 * @throws BookDataException       If there is an issue with book data.
 */
@Test
void testGetAllBooksByCategory() throws SQLException, ConnectionException, BookDataException {
    List<Book> bookList = BookService.getAllBookByCategory("FICTION");

    for (Book e : bookList) {
        CustomLogger.info(e);
    }
    Assertions.assertNotNull(bookList);
}
}
