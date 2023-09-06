package com.fssa.books.exception;

public class BookDAOCRUDException extends Exception {
	private static final long serialVersionUID = -8105491977357554060L;

	public BookDAOCRUDException(String message) {
		super(message);
	}

	public BookDAOCRUDException(Throwable thrown) {
		super(thrown);
	}

	public BookDAOCRUDException(String message, Throwable thrown) {
		super(message, thrown);
	}
}
