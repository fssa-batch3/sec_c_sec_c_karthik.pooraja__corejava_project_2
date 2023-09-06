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

class TestBooksDAO {

	@Test
	void testAddBooks() throws SQLException, BookDAOCRUDException, ConnectionException {
		// Create a book object with the desired details
		
		Book book = new Book();
		book.setTitle("The Adventures of Huckleberry Finn");
		book.setAuthor("Mark Twain");
		book.setPublisheddate(LocalDate.of(1884, 12, 20));
		book.setPublishername("Troll Illustrated Classics");
		book.setBookimageurl("https://static.wikia.nocookie.net/childrensbooks/images/a/a7/The_Adventures_of_Huckleberry_Finn.jpg");
		book.setEdition(1);
		book.setCategoryname(BookCategory.FANTASY);

		Assertions.assertTrue(BookDao.addBooks(book)); 
	}
 
    @Test
    void testReadBooks() throws SQLException, BookDAOCRUDException, ConnectionException, BookDataException {
        
        
       List<Book> bookList = BookService.readBooks();
		
		for(Book e : bookList) {
			CustomLogger.info(e);
		}
		
		Assertions.assertNotNull(bookList);
        
    }

    @Test
    void testUpdateBooks() throws SQLException, BookDAOCRUDException, ConnectionException {
        // Create an updated book object with new details
    	
    	Book book = new Book();
    	book.setId(1); 
		book.setTitle("Journey to the End of the Night");
		book.setAuthor("Louis-Ferdinand");
		book.setPublisheddate(LocalDate.of(1932, 9, 19));
		book.setPublishername("Penguin Modern Classics");
		book.setBookimageurl("https://i.pinimg.com/736x/66/b2/68/66b2685be7b2bb138fb547ea5f690a3a--to-the-end-book-cover-design.jpg");
		book.setEdition(1);
		book.setCategoryname(BookCategory.FICTION);

        // Call the updateBooks method and assert the result
        boolean updateResult;
		try { 
			updateResult = BookService.updateBooks(book);
			 Assertions.assertTrue(updateResult);
		} catch (BookDataException | BookDAOCRUDException | SQLException | ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Use the actual ID
       
    }

    @Test
    void testDeleteBook() throws SQLException, BookDAOCRUDException, ConnectionException {
        // Call the deleteBooks method and assert the result
        boolean deleteResult = BookDao.deleteBooks(3); 
        Assertions.assertTrue(deleteResult);
    }
	
	
	@Test
	void testGetAllBooksByCategory() throws SQLException, ConnectionException, BookDataException {
		
		List<Book> bookList = BookService.getAllBookByCategory("ROMANCE");
		
		for(Book e : bookList) {
			CustomLogger.info(e);
		}
		
	}
	
}


