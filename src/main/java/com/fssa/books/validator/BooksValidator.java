package com.fssa.books.validator;

import java.time.LocalDate;
import java.util.regex.Pattern;

import com.fssa.books.exception.BookDataException;
import com.fssa.books.exception.BookValidatorCustomExceptionMessage;
import com.fssa.books.model.Book;

/**
 * 
 * @author KarthikRajaPooraja BooksValidator class to validate the attributes
 *         which are collected by the model class Books
 *
 */
public class BooksValidator {
	private BooksValidator() {
		super();
	}

	static boolean validateTitle(String title) throws BookDataException {
		if (title == null || title.trim().equals("")) {
			throw new BookDataException(BookValidatorCustomExceptionMessage.NULL_OR_EMPTY_INVALID);
		} else if (title.length() < 2) {
			throw new BookDataException(BookValidatorCustomExceptionMessage.MINIMAL_INPUT);
		}
		return true;
	}

	public static boolean validateAuthor(String author) throws BookDataException {
		if (author == null || author.trim().equals("")) {
			throw new BookDataException(BookValidatorCustomExceptionMessage.NULL_OR_EMPTY_INVALID);
		} else if (author.length() < 2) {
			throw new BookDataException(BookValidatorCustomExceptionMessage.MINIMAL_INPUT);
		}
		return true;
	}

	public static boolean validateEdition(int edition) throws BookDataException {
		if (edition <= 0 || edition > Integer.MAX_VALUE) {
			throw new BookDataException(BookValidatorCustomExceptionMessage.INVALID_INTEGER_RANGE);
		}
		String numStr = Integer.toString(edition);
		if (numStr.length() > 1 && numStr.charAt(0) == '0') {
			throw new BookDataException(BookValidatorCustomExceptionMessage.INVALID_INTEGER);
		}
		return true;
	}

	public static boolean validateId(int id) throws BookDataException {
		if (id <= 0 || id >= Integer.MAX_VALUE) {
			throw new BookDataException(BookValidatorCustomExceptionMessage.INVALID_INTEGER_RANGE);
		}
		String numStr = Integer.toString(id);
		if (numStr.length() > 1 && numStr.charAt(0) == '0') {
			throw new BookDataException(BookValidatorCustomExceptionMessage.INVALID_INTEGER);
		}
		return true;
	}

	public static boolean validatePublishedDate(LocalDate publisheddate) throws BookDataException {
		if (!publisheddate.isBefore(LocalDate.now())) {
			throw new BookDataException(BookValidatorCustomExceptionMessage.INVALID_DATE);
		}
		return true;
	}

	public static boolean isValidBookImageUrl(String bookImageUrl) throws BookDataException {
		String urlPattern = "^(https?://)?[^\\s/$.?#].[^\\s]*\\.(?:png|jpg|jpeg|gif|svg)$";
		if (bookImageUrl == null || bookImageUrl.trim().equals("")) {
			throw new BookDataException(BookValidatorCustomExceptionMessage.NULL_OR_EMPTY_INVALID);
		} else if (bookImageUrl.length() < 2) {
			throw new BookDataException(BookValidatorCustomExceptionMessage.MINIMAL_INPUT);
		}

		boolean isMatch = Pattern.matches(urlPattern, bookImageUrl);

		if (!isMatch) {
			throw new BookDataException(BookValidatorCustomExceptionMessage.INVALID_IMAGE_URL);
		}
		return true;
	} 

	public static boolean validatePublisherName(String publisher) throws BookDataException {
		if (publisher == null || publisher.trim().equals("")) {
			throw new BookDataException(BookValidatorCustomExceptionMessage.NULL_OR_EMPTY_INVALID);
		} else if (publisher.length() < 2) {
			throw new BookDataException(BookValidatorCustomExceptionMessage.MINIMAL_INPUT);
		}
		return true;
	}

	public static boolean validateBook(Book book) throws BookDataException {

		validateAuthor(book.getAuthor());
		validateTitle(book.getTitle());
		validateEdition(book.getEdition());
		validatePublisherName(book.getPublishername());
		validatePublishedDate(book.getPublisheddate());
		isValidBookImageUrl(book.getBookimageurl());
   
		return true;

	}

}
