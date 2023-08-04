package com.fssa.borrower.exception;

public interface BorrowerDataExceptionMessage {
	public static final String NULL_OR_EMPTY_STRING = "Your input can't be null or empty";
	public static final String INVALID_LENGTH = "Total character in your input is in invalid range";
	public static final String INVALID_NAME = "Your username can't contain spaces in between";
	public static final String NAME_PATTERN = "Your name input doesn't matches the expected pattern";
	public static final String EMAIL_PATTERN = "Your email input doesn't matches the expected pattern";
	public static final String PASSWORD_PATTERN = "Your password input doesn't matches the expected pattern";
	public static final String INVALID_INTEGER = "Your integer doesn't start with zero if you have more than one digit integer";
	public static final String INVALID_INTEGER_RANGE = "Your integer input is not in range";
    
}
