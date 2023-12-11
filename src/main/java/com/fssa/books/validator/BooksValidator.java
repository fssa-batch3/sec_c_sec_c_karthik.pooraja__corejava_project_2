package com.fssa.books.validator;

import java.time.LocalDate;
import java.util.regex.Pattern;

import com.fssa.books.exception.DataException;
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

	static boolean validateTitle(String title) throws DataException {
		if (title == null || title.trim().equals("")) {
			throw new DataException(BookValidatorCustomExceptionMessage.NULL_OR_EMPTY_INVALID);
		} else if (title.length() < 2) {
			throw new DataException(BookValidatorCustomExceptionMessage.MINIMAL_INPUT);
		}
		return true;
	}

	public static boolean validateAuthor(String author) throws DataException {
		if (author == null || author.trim().equals("")) {
			throw new DataException(BookValidatorCustomExceptionMessage.NULL_OR_EMPTY_INVALID);
		} else if (author.length() < 2) {
			throw new DataException(BookValidatorCustomExceptionMessage.MINIMAL_INPUT);
		}
		return true;
	}

	public static boolean validateEdition(int edition) throws DataException {
		if (edition <= 0 || edition > Integer.MAX_VALUE) {
			throw new DataException(BookValidatorCustomExceptionMessage.INVALID_INTEGER_RANGE);
		}
		String numStr = Integer.toString(edition);
		if (numStr.length() > 1 && numStr.charAt(0) == '0') {
			throw new DataException(BookValidatorCustomExceptionMessage.INVALID_INTEGER);
		}
		return true;
	}

	public static boolean validateId(int id) throws DataException {
		if (id <= 0 || id >= Integer.MAX_VALUE) {
			throw new DataException(BookValidatorCustomExceptionMessage.INVALID_INTEGER_RANGE);
		}
		String numStr = Integer.toString(id);
		if (numStr.length() > 1 && numStr.charAt(0) == '0') {
			throw new DataException(BookValidatorCustomExceptionMessage.INVALID_INTEGER);
		}
		return true;
	}

	public static boolean validatePublishedDate(LocalDate publisheddate) throws DataException {
		if (!publisheddate.isBefore(LocalDate.now())) {
			throw new DataException(BookValidatorCustomExceptionMessage.INVALID_DATE);
		}
		return true;
	}

	public static boolean isValidBookImageUrl(String bookImageUrl) throws DataException {
		String urlPattern = "^(https?://)?[^\\s/$.?#].[^\\s]*\\.(?:png|jpg|jpeg|gif|svg)$";
		if (bookImageUrl == null || bookImageUrl.trim().equals("")) {
			throw new DataException(BookValidatorCustomExceptionMessage.NULL_OR_EMPTY_INVALID);
		} else if (bookImageUrl.length() < 2) {
			throw new DataException(BookValidatorCustomExceptionMessage.MINIMAL_INPUT);
		}

		boolean isMatch = Pattern.matches(urlPattern, bookImageUrl);

		if (!isMatch) {
			throw new DataException(BookValidatorCustomExceptionMessage.INVALID_IMAGE_URL);
		}
		return true;
	} 

	public static boolean validatePublisherName(String publisher) throws DataException {
		if (publisher == null || publisher.trim().equals("")) {
			throw new DataException(BookValidatorCustomExceptionMessage.NULL_OR_EMPTY_INVALID);
		} else if (publisher.length() < 2) {
			throw new DataException(BookValidatorCustomExceptionMessage.MINIMAL_INPUT);
		}
		return true;
	}
	public static boolean validateStock(int stock) throws DataException {
		if (stock<=-1) {
			throw new DataException(BookValidatorCustomExceptionMessage.INVALID_INTEGER_RANGE);
		} 
		return true;
	}

	public static boolean validateBook(Book book) throws DataException {

		validateAuthor(book.getAuthor());
		validateTitle(book.getTitle());
		validateEdition(book.getEdition());
		validatePublisherName(book.getPublishername());
		validatePublishedDate(book.getPublisheddate());
		isValidBookImageUrl(book.getBookimageurl());
		validateStock(book.getStock());
		return true;
	}

}
