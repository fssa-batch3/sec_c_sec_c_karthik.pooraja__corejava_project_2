package com.fssa.books.service;

import java.sql.SQLException;

import com.fssa.books.dao.BookDao;
import com.fssa.books.exception.BookDAOCRUDException;
import com.fssa.books.exception.BookDataException;
import com.fssa.books.model.Book;
import com.fssa.books.validator.BooksValidator;
import com.fssa.connection.exception.ConnectionException;


public class BookService  {
	private BookService() {
		super();	
	}
    public static void addBooks(Book book) throws BookDataException, BookDAOCRUDException, SQLException,ConnectionException {
    	if(BooksValidator.validateBook(book)) {
    		BookDao.addBooks(book);
    	}
    }
    public static void updateBooks(Book book) throws BookDataException, BookDAOCRUDException, SQLException,ConnectionException{
    	if(BooksValidator.validateBook(book)) {
    		BookDao.updateBooks(book);
    	}
    	
    }
    
	public static void deleteBooks(int bookId) throws BookDataException, BookDAOCRUDException, SQLException,ConnectionException{
    	if(BooksValidator.validateId(bookId)) {
    		BookDao.deleteBooks(bookId);
    	}
    }
    
    public static void getAllBooks() throws BookDAOCRUDException, SQLException,ConnectionException{
    	BookDao.readBooks();
    }
    
    
}
