package com.fssa.books.model;

import java.time.LocalDate;

public class ReturnHistory {
	private String bookName;
	private String userName;
	private String userEmail;
	private LocalDate startDate;
	private LocalDate endDate;
	private int requestId;
	private int bookId;

	public ReturnHistory(Book book, User user, Transfer transaction) {
		this.bookId = book.getId();
		this.bookName = book.getTitle();
		this.userName = user.getName();
		this.userEmail = user.getEmail();
		this.startDate = transaction.getStartDate();
		this.endDate=transaction.getEndDate();
		this.requestId = transaction.getRequestId();
	}
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate= endDate;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	@Override
	public String toString() {
		return "ReturnHistory [bookName=" + bookName + ", userName=" + userName + ", userEmail=" + userEmail
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", requestId=" + requestId + ", bookId="
				+ bookId + "]";
	}

	
	
}
