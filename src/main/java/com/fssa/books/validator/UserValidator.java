package com.fssa.books.validator;

import com.fssa.books.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.books.exception.BookValidatorCustomExceptionMessage;
import com.fssa.books.exception.DataException;


public class UserValidator {
	private static final String emailregex ="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	private static final String nameregex = "^[a-zA-Z ]{2,46}$";
	private static final String numberregex ="(0/91)?[7-9][0-9]{9}";
	private static final String passwordregex = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";
	
	
	public static boolean validateName(String name) throws DataException {
		if (name == null || name.trim().equals("")) {
			throw new DataException(BookValidatorCustomExceptionMessage.NULL_OR_EMPTY_INVALID);
		} 
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(name);
		Boolean isMatch = matcher.matches();

		if (Boolean.FALSE.equals(isMatch)) {
			throw new DataException("Name is not in required length");

		}
		return true;
	}
	
	public static boolean validateEmail(String email) throws DataException {
		if (email == null || email.trim().equals("")) {
			throw new DataException(BookValidatorCustomExceptionMessage.NULL_OR_EMPTY_INVALID);
		} 
		
		Pattern pattern = Pattern.compile(emailregex);
		Matcher matcher = pattern.matcher(email);
		Boolean isMatch = matcher.matches();

		if (Boolean.FALSE.equals(isMatch)) {
			throw new DataException("Email is not in required pattern");

		}
		return true;
	}
	
	public static boolean validatePhoneNumber(String number) throws DataException {
		if (number == null || number.trim().equals("")) {
			throw new DataException(BookValidatorCustomExceptionMessage.NULL_OR_EMPTY_INVALID);
		} 
		
		Pattern pattern = Pattern.compile(numberregex);
		Matcher matcher = pattern.matcher(number);
		Boolean isMatch = matcher.matches();

		if (Boolean.FALSE.equals(isMatch)) {
			throw new DataException("Number is not in valid format");

		}
		return true;
	}
	
	public static boolean validatePassword(String password) throws DataException {
		if (password == null || password.trim().equals("")) {
			throw new DataException(BookValidatorCustomExceptionMessage.NULL_OR_EMPTY_INVALID);
		} 
		
		Pattern pattern = Pattern.compile(passwordregex);
		Matcher matcher = pattern.matcher(password);
		Boolean isMatch = matcher.matches();

		if (Boolean.FALSE.equals(isMatch)) {
			throw new DataException("Password is not in valid format");

		}
		return true;
	}
	public static boolean ValidateUser(User user) throws DataException{
		validateName(user.getName());
		validateEmail(user.getEmail());
		validatePassword(user.getPassword());
		validatePhoneNumber(user.getPhoneNumber());	
		return true;	
	}
	
	public static boolean UpdatevalidateUser(User user) throws DataException{
		validateName(user.getName());
		validatePassword(user.getPassword());
		validatePhoneNumber(user.getPhoneNumber());	
		return true;	
	}
	

}
