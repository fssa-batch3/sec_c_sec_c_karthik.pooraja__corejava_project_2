package com.fssa.connection.exception;

public class ConnectionException extends Exception {
	private static final long serialVersionUID = -8105491977357554060L;

	public ConnectionException(String message) {
		super(message);
	}

	public ConnectionException(Throwable thrown) {
		super(thrown);
	}

	public ConnectionException(String message, Throwable thrown) {
		super(message, thrown);
	}
}