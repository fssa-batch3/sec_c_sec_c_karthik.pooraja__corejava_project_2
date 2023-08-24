package com.fssa.books.exception;

public class BookValidatorDAOCustomExceptionMessage {
	private BookValidatorDAOCustomExceptionMessage() {
		super();
	}

	public static final String ADD_BOOKS = "Your book is not added due to some invaild inputs";
	public static final String UPDATE_BOOKS = "Your book is not updated due to some invaild inputs";
	public static final String READ_BOOKS = "Your booklist is not obtained";
	public static final String DELETE_BOOKS = "Your book is not deleted due to invaild id";
}
