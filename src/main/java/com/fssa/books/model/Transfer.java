package com.fssa.books.model;

import java.time.LocalDate;

public class Transfer {
	private int requestId;
	private int userId;
	private int bookId;
	private TransferStatus status;
	private LocalDate startDate;
	private LocalDate endDate;
	private int daysExtended;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public TransferStatus isStatus() {
		return status;
	}
	public void setStatus(TransferStatus status) {
		this.status = status;
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
		this.endDate = endDate;
	}
	public int getDaysExtended() {
		return daysExtended;
	}
	public void setDaysExtended(int daysExtended) {
		this.daysExtended = daysExtended;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	@Override
	public String toString() {
		return "Transfer [userId=" + userId + ", bookId=" + bookId + ", status=" + status + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", daysExtended=" + daysExtended + "]";
	}
	
	

}
