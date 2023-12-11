package com.fssa.books.exception;

public class DataException extends Exception {
	private static final long serialVersionUID = -8105491977357554060L;

	public DataException(String message) {
		super(message);
	}

	public DataException(Throwable thrown) {
		super(thrown);
	}

	public DataException(String message, Throwable thrown) {
		super(message, thrown);
	}
}
