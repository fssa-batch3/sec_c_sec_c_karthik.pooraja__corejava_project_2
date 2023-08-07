package com.fssa.borrower.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.borrower.exception.BorrowerDataException;

public class TestBorrowerValidator {

    @Test
    public void testValidName() throws BorrowerDataException {
        Assertions.assertTrue(BorrowerValidator.isValidName("JohnDoe"));
    }

    @Test
    public void testNullName() {
        Assertions.assertThrows(BorrowerDataException.class, () -> BorrowerValidator.isValidName(null));
    }

    @Test
    public void testEmptyName() {
    	Assertions.assertThrows(BorrowerDataException.class, () -> BorrowerValidator.isValidName(""));
    }

    @Test
    public void testShortName() {
    	Assertions.assertThrows(BorrowerDataException.class, () -> BorrowerValidator.isValidName("A"));
    }

    @Test
    public void testNameWithNumber() {
    	Assertions.assertThrows(BorrowerDataException.class, () -> BorrowerValidator.isValidName("John123"));
    }

    @Test
    public void testNameWithSpecialCharacter() {
    	Assertions.assertThrows(BorrowerDataException.class, () -> BorrowerValidator.isValidName("John@Doe"));
    }
    @Test
    public void testNameWithSpaces() {
    	Assertions.assertThrows(BorrowerDataException.class, () -> BorrowerValidator.isValidName("John Doe"));
    }
    @Test
    public void testValidEmail() throws BorrowerDataException {
        Assertions.assertTrue(BorrowerValidator.isValidEmail("john.doe@example.com"));
    }

    @Test
    public void testNullEmail() {
    	Assertions.assertThrows(BorrowerDataException.class, () -> BorrowerValidator.isValidEmail(null));
    }

    @Test
    public void testEmptyEmail() {
    	Assertions.assertThrows(BorrowerDataException.class, () -> BorrowerValidator.isValidEmail(""));
    }

    @Test
    public void testEmailWithUppercaseLetters() {
    	Assertions.assertThrows(BorrowerDataException.class, () -> BorrowerValidator.isValidEmail("JohnDoe@example.com"));
    }

    @Test
    public void testEmailWithoutAtSymbol() {
    	Assertions.assertThrows(BorrowerDataException.class, () -> BorrowerValidator.isValidEmail("john.doeexample.com"));
    }
    @Test
    public void testEmailWithInvalidCharacters() {
        Assertions.assertThrows(BorrowerDataException.class, () -> BorrowerValidator.isValidEmail("john.doe$example.com"));
    }

    @Test
    public void testValidPassword() throws BorrowerDataException {
        Assertions.assertTrue(BorrowerValidator.isValidPassword("Pass@word123"));
    }

    @Test
    public void testNullPassword() {
        Assertions.assertThrows(BorrowerDataException.class, () -> BorrowerValidator.isValidPassword(null));
    }

    @Test
    public void testEmptyPassword() {
    	Assertions.assertThrows(BorrowerDataException.class, () -> BorrowerValidator.isValidPassword(""));
    }

    @Test
    public void testShortPassword() {
    	Assertions.assertThrows(BorrowerDataException.class, () -> BorrowerValidator.isValidPassword("Pass123"));
    }

    @Test
    public void testPasswordWithoutUppercase() {
    	Assertions.assertThrows(BorrowerDataException.class, () -> BorrowerValidator.isValidPassword("pass@word123"));
    }

    @Test
    public void testPasswordWithoutLowercase() {
    	Assertions.assertThrows(BorrowerDataException.class, () -> BorrowerValidator.isValidPassword("PASS@WORD123"));
    }

    @Test
    public void testPasswordWithoutSpecialCharacter() {
    	Assertions.assertThrows(BorrowerDataException.class, () -> BorrowerValidator.isValidPassword("Password123"));
    }

    @Test
    public void testPasswordWithoutNumber() {
    	Assertions.assertThrows(BorrowerDataException.class, () -> BorrowerValidator.isValidPassword("Pass@word"));
    }
    
    @Test
    public void testValidAge() throws BorrowerDataException {
        Assertions.assertTrue(BorrowerValidator.isValidAge((byte) 25));
    }

    @Test
    public void testNegativeAge() {
        Assertions.assertThrows(BorrowerDataException.class, () -> BorrowerValidator.isValidAge((byte) -5));
    }

    @Test
    public void testAgeGreaterThan120() {
    	Assertions.assertThrows(BorrowerDataException.class, () -> BorrowerValidator.isValidAge((byte) 150));
    }


}
