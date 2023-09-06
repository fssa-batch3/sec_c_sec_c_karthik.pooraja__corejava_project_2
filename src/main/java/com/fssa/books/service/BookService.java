package com.fssa.books.service;

import java.sql.SQLException;
import java.util.List;

import com.fssa.books.dao.BookDao;
import com.fssa.books.exception.BookDAOCRUDException;
import com.fssa.books.exception.BookDataException;
import com.fssa.books.model.Book;
import com.fssa.books.validator.BooksValidator;
import com.fssa.connection.exception.ConnectionException;

public class BookService { 

	public static boolean addBooks(Book book)
			throws BookDataException, BookDAOCRUDException, SQLException, ConnectionException {
		if (BooksValidator.validateBook(book)) {
			BookDao.addBooks(book); 
		}
		return true;
	}
	
	public static List<Book> readBooks() throws BookDataException, BookDAOCRUDException, SQLException, ConnectionException {
		return BookDao.readBooks(); 
	}

	public static boolean updateBooks(Book book)
			throws BookDataException, BookDAOCRUDException, SQLException, ConnectionException {
		if (BooksValidator.validateBook(book)) {
			BookDao.updateBooks(book);
			return true;
		}
		return false;

	} 

	public static boolean deleteBooks(int bookId)
			throws BookDataException, BookDAOCRUDException, SQLException, ConnectionException {
		if (BooksValidator.validateId(bookId)) {
			BookDao.deleteBooks(bookId);
		}
		return true;
	}

	
public static List<Book> getAllBookByCategory(String category) throws SQLException, ConnectionException, BookDataException {
		
		return BookDao.readBooksByCategory(category);
		
	}

}
