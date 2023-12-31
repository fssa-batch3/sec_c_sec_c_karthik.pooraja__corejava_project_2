package com.fssa.books.validator;

import com.fssa.books.exception.DataException;
import com.fssa.books.exception.BookValidatorCustomExceptionMessage;
import com.fssa.books.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;


class TestBookValidator {
	Book input = new Book();

	@Test
	void testValidateTitle_ValidTitle() throws DataException {
		input.setTitle("Adventures of tom Sawyer");
		Assertions.assertTrue(BooksValidator.validateTitle(input.getTitle()));
	}

	@Test
	void testInvalidTitleNullInput() {
		input.setTitle(null);

		try {
			BooksValidator.validateTitle(input.getTitle());
			fail("Expected BookDataException was not thrown.");
		} catch (DataException e) {
			assertEquals(BookValidatorCustomExceptionMessage.NULL_OR_EMPTY_INVALID, e.getMessage());
		}
	}

	@Test
	void testValidateTitle_EmptyInput() {
		input.setTitle("");

		try {
			BooksValidator.validateTitle(input.getTitle());
			fail("Expected BookDataException was not thrown.");
		} catch (DataException e) {
			assertEquals(BookValidatorCustomExceptionMessage.NULL_OR_EMPTY_INVALID, e.getMessage());
		}
	}

	@Test
	void minimalTitleLength() {
		input.setTitle("Z");
		try {
			BooksValidator.validateTitle(input.getTitle());
			fail("Expected BookDataException was not thrown.");
		} catch (DataException e) {
			assertEquals(BookValidatorCustomExceptionMessage.MINIMAL_INPUT, e.getMessage());
		}
	}

	@Test
	void testValidateAuthor_ValidAuthor() {
		input.setAuthor("Tom Sawyer");
		try {
			Assertions.assertTrue(BooksValidator.validateAuthor(input.getAuthor()));
		} catch (DataException e) {

		}
	}

	@Test
	void testInvalidAuthorNullInput() {
		input.setAuthor(null);

		try {
			BooksValidator.validateAuthor(input.getAuthor());
			fail("Expected BookDataException was not thrown.");
		} catch (DataException e) {
			assertEquals(BookValidatorCustomExceptionMessage.NULL_OR_EMPTY_INVALID, e.getMessage());
		}
	}

	@Test
	void testValidateAuthorEmptyInput() throws DataException{
		input.setAuthor("");

		try {
			BooksValidator.validateAuthor(input.getAuthor());
			fail("Expected BookDataException was not thrown.");
		} catch (DataException e) {
			assertEquals(BookValidatorCustomExceptionMessage.NULL_OR_EMPTY_INVALID, e.getMessage());
		}
	}

	@Test
	void minimalAuthorLength() throws DataException{
		input.setAuthor("P");
		try {
			BooksValidator.validateAuthor(input.getAuthor());
			fail("Expected BookDataException was not thrown.");
		} catch (DataException e) {
			assertEquals(BookValidatorCustomExceptionMessage.MINIMAL_INPUT, e.getMessage());
		}
	}

	@Test
	void validUrl() throws DataException {
		input.setBookimageurl("https://iili.io/HWXep1e.png");
		try {
			Assertions.assertTrue(BooksValidator.isValidBookImageUrl(input.getBookimageurl()));
		} catch (DataException e) {

		}
	}

	@Test
	void invalidUrl() throws DataException {
		input.setBookimageurl("iili.io/HWXep1e.png");
		try {
			Assertions.assertTrue(BooksValidator.isValidBookImageUrl(input.getBookimageurl()));
		} catch (DataException e) {
			assertEquals(BookValidatorCustomExceptionMessage.INVALID_IMAGE_URL, e.getMessage());
		}
	}

	@Test
	void nullUrl() throws DataException{
		input.setBookimageurl(null);
		try {
			BooksValidator.isValidBookImageUrl(input.getBookimageurl());
			fail("Expected BookDataException was not thrown.");
		} catch (DataException e) {
			assertEquals(BookValidatorCustomExceptionMessage.NULL_OR_EMPTY_INVALID, e.getMessage());
		}
	}

	@Test
	void emptyUrl() throws DataException{
		input.setBookimageurl("");
		try {
			BooksValidator.isValidBookImageUrl(input.getBookimageurl());
			fail("Expected BookDataException was not thrown.");
		} catch (DataException e) {
			assertEquals(BookValidatorCustomExceptionMessage.NULL_OR_EMPTY_INVALID, e.getMessage());
		}
	}

	@Test
	void minimalLengthUrl() throws DataException{
		input.setBookimageurl("A");
		try {
			BooksValidator.isValidBookImageUrl(input.getBookimageurl());
			fail("Expected BookDataException was not thrown.");
		} catch (DataException e) {
			assertEquals(BookValidatorCustomExceptionMessage.MINIMAL_INPUT, e.getMessage());
		}
	}

	@Test
	void validPublisher() throws DataException {
		input.setPublishername("Bible Society of India");
		Assertions.assertTrue(BooksValidator.validatePublisherName(input.getPublishername()));
	}

	@Test
	void nullPublisher() throws DataException {
		input.setPublishername(null);

		try {
			BooksValidator.validatePublisherName(input.getPublishername());
			fail("Expected BookDataException was not thrown.");
		} catch (DataException e) {
			assertEquals(BookValidatorCustomExceptionMessage.NULL_OR_EMPTY_INVALID, e.getMessage());
		}
	}

	@Test
	void emptyPublisher() throws DataException{
		input.setPublishername("");

		try {
			BooksValidator.validatePublisherName(input.getPublishername());
			fail("Expected BookDataException was not thrown.");
		} catch (DataException e) {
			assertEquals(BookValidatorCustomExceptionMessage.NULL_OR_EMPTY_INVALID, e.getMessage());
		}
	}

	@Test
	void minimalLengthPublisher() {
		input.setPublishername("A");
		try {
			BooksValidator.validatePublisherName(input.getPublishername());
			fail("Expected BookDataException was not thrown.");
		} catch (DataException e) {
			assertEquals(BookValidatorCustomExceptionMessage.MINIMAL_INPUT, e.getMessage());
		}
	}

	@Test
	void validId() throws DataException{
		input.setId(25);
		try {
			Assertions.assertTrue(BooksValidator.validateId(input.getId()));
		} catch (DataException e) {

		}
	}

	@Test
	void invalidUpperRangeId() throws DataException{
		try {
			input.setId(Integer.MAX_VALUE + 1);
			BooksValidator.validateId(input.getId());
			fail("Expected BookDataException was not thrown.");
		} catch (DataException e) {

			Assertions.assertEquals(BookValidatorCustomExceptionMessage.INVALID_INTEGER_RANGE, e.getMessage());
		}
	}

	@Test
	void invalidLowerRangeId() throws DataException{

		try {
			input.setId(-1);
			BooksValidator.validateId(input.getId());
			fail("Expected BookDataException was not thrown.");
		} catch (DataException e) {

			Assertions.assertEquals(BookValidatorCustomExceptionMessage.INVALID_INTEGER_RANGE, e.getMessage());
		}
	}

	@Test
	void invalidId() throws DataException{

		try {
			input.setId(012);
			BooksValidator.validateId(input.getId());
		} catch (DataException e) {

			Assertions.assertEquals(BookValidatorCustomExceptionMessage.INVALID_INTEGER, e.getMessage());
		}
	}

	@Test
	void validEdition() throws DataException{

		try {
			input.setEdition(25);
			Assertions.assertTrue(BooksValidator.validateEdition(input.getEdition()));
		} catch (DataException e) {

		}
	}

	@Test
	void invalidUpperRangeEdition() throws DataException{

		try {
			input.setEdition(Integer.MAX_VALUE + 1);
			BooksValidator.validateEdition(input.getEdition());
			fail("Expected BookDataException was not thrown.");
		} catch (DataException e) {

			Assertions.assertEquals(BookValidatorCustomExceptionMessage.INVALID_INTEGER_RANGE, e.getMessage());
		}
	}

	@Test
	void invalidLowerRangeEdition() throws DataException{
		try {
			input.setEdition(-10);
			BooksValidator.validateEdition(input.getEdition());
			fail("Expected BookDataException was not thrown.");
		} catch (DataException e) {

			Assertions.assertEquals(BookValidatorCustomExceptionMessage.INVALID_INTEGER_RANGE, e.getMessage());
		}
	}

	@Test
	void invalidEdition() throws DataException{
		input.setEdition(025);
		try {
			BooksValidator.validateEdition(input.getEdition());

		} catch (DataException e) {

			Assertions.assertEquals(BookValidatorCustomExceptionMessage.INVALID_INTEGER, e.getMessage());
		}
	}

	@Test
	void testValidBook() throws DataException {
		// Create a valid book object for testing
		Book book = new Book();
		book.setTitle("Book Title");
		book.setAuthor("Author Name");
		book.setEdition(1);
		book.setPublishername("Publisher Name");
		book.setPublisheddate(LocalDate.of(2023, 7, 31));
		book.setBookimageurl("http://example.com/book.jpg");

		boolean result = BooksValidator.validateBook(book);

		assertTrue(result); // Check if validation passes for a valid book
	}

}
