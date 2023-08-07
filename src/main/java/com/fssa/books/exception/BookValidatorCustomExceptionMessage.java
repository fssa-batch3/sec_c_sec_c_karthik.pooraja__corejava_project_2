package com.fssa.books.exception;

public interface BookValidatorCustomExceptionMessage {
    public static final String INVALID_INTEGER_RANGE="Your input is not in required range";
    public static final String INVALID_INTEGER="the number shouldn't start with zero";
    public static final String NULL_OR_EMPTY_INVALID="Your Title input can't be null or empty";
    public static final String MINIMAL_INPUT="Your input length is not enough";
    public static final String ID_INVALID="Your input can't be zero";
    public static final String INVALID_DATE="Published date will not be in Future";
    public static final String INVALID_CATEGORY="Category input is not valid";
    public static final String INVALID_IMAGE_URL="Image URL input is not valid";
    
    
}
