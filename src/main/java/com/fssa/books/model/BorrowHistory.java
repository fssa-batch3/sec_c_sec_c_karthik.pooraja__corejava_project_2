package com.fssa.books.model;

public class BorrowHistory {
	    private String bookName;
	    private String userName;
	    private String userEmail;
	    private TransferStatus transferStatus;
	    private int transactionId;
        private int bookId;

		public BorrowHistory(Book book, User user, Transfer transaction) {
	    	this.bookId=book.getId();
	        this.bookName = book.getTitle();
	        this.userName = user.getName();
	        this.userEmail = user.getEmail();
	        this.transferStatus = transaction.isStatus();
	        this.transactionId=transaction.getRequestId();
	    }
        
	    // Getters and setters for the attributes

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

	    public TransferStatus getTransferStatus() {
	        return transferStatus;
	    }

	    public void setTransferStatus(TransferStatus transferStatus) {
	        this.transferStatus = transferStatus;
	    }

		public int getRequestId() {
			return transactionId;
		}

		public void setRequestId(int requestId) {
			this.transactionId = requestId;
		}
		
		public int getBookId() {
			return bookId;
		}

		public void setBookId(int bookId) {
			this.bookId = bookId;
		}

	    @Override
		public String toString() {
			return "BorrowHistory [bookName=" + bookName + ", userName=" + userName + ", userEmail=" + userEmail
					+ ", transferStatus=" + transferStatus + ", transactionId=" + transactionId + ", bookId=" + bookId
					+ "]";
		}

}
