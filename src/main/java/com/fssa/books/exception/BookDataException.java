package com.fssa.books.exception;

public class BookDataException extends Exception {
	private static final long serialVersionUID = -8105491977357554060L;

	public BookDataException(String message) {
		super(message);
	}

	public BookDataException(Throwable thrown) {
		super(thrown);
	}

	public BookDataException(String message, Throwable thrown) {
		super(message, thrown);
	}
}
