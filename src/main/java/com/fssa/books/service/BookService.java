package com.fssa.books.service;

import java.sql.SQLException;
import java.util.List;

import com.fssa.books.dao.BookDao;
import com.fssa.books.exception.BookDAOCRUDException;
import com.fssa.books.exception.DataException;
import com.fssa.books.model.Book;
import com.fssa.books.validator.BooksValidator;
import com.fssa.connection.exception.ConnectionException;

public class BookService { 

	public static boolean addBooks(Book book)
			throws DataException, BookDAOCRUDException, SQLException, ConnectionException {
		if (BooksValidator.validateBook(book)) {
			BookDao.addBooks(book); 
		}
		return true;
	}
	
	public static List<Book> readBooks() throws DataException, BookDAOCRUDException, SQLException, ConnectionException {
		return BookDao.readBooks(); 
	}

	public static boolean updateBooks(Book book)
			throws DataException, BookDAOCRUDException, SQLException, ConnectionException {
		if (BooksValidator.validateBook(book)) {
			BookDao.updateBooks(book);
			return true;
		}
		return false;

	} 

	public static boolean deleteBooks(int bookId)
			throws DataException, BookDAOCRUDException, SQLException, ConnectionException {
		if (BooksValidator.validateId(bookId)) {
			BookDao.deleteBooks(bookId);
		}
		return true;
	}
	public static boolean updateBookStock(int stock,int bookId)
			throws DataException, BookDAOCRUDException, SQLException, ConnectionException {
		if (BooksValidator.validateId(bookId)&&BooksValidator.validateStock(stock)) {
			BookDao.updateBookStocks(stock, bookId);
		}
		return true;
	}

	
public static List<Book> getAllBookByCategory(String category) throws SQLException, ConnectionException, DataException {
		
		return BookDao.readBooksByCategory(category);
		
	}

}
