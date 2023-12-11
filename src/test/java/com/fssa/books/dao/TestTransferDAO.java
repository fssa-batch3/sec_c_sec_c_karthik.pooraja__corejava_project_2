package com.fssa.books.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.books.exception.BookDAOCRUDException;
import com.fssa.books.exception.DataException;
import com.fssa.books.model.Book;
import com.fssa.books.model.BorrowHistory;
import com.fssa.books.model.ReturnHistory;
import com.fssa.books.model.Transfer;
import com.fssa.books.model.TransferStatus;
import com.fssa.books.service.BookService;
import com.fssa.books.util.CustomLogger;
import com.fssa.books.dao.TransferDAO;
import com.fssa.connection.exception.ConnectionException;

public class TestTransferDAO {
	@Test 
	void testAddTransfer() throws SQLException, ConnectionException {
	      Assertions.assertTrue(TransferDAO.insertTransferRequest(1, 3));
		}
	@Test
	void testRejectedUpdateTransfer() throws SQLException, BookDAOCRUDException, ConnectionException, DataException {
      Assertions.assertTrue(TransferDAO.updateAcceptStatusAndDates(1, "REJECTED"));
	}
	@Test
	void testAcceptedUpdateTransfer() throws SQLException, BookDAOCRUDException, ConnectionException, DataException {
      Assertions.assertTrue(TransferDAO.updateAcceptStatusAndDates(2, "ACCEPTED"));
	}
	@Test
	void testRejectedUpdatetwoTransfer() throws SQLException, BookDAOCRUDException, ConnectionException, DataException {
      Assertions.assertTrue(TransferDAO.updateAcceptStatusAndDates(1, "REJECTEDBYUSER"));
	}
	@Test
	void showBorrowHistory() throws SQLException, ConnectionException {
		List<BorrowHistory> borrowHistory=null;
		try {
			borrowHistory = TransferDAO.showAllBorrowHistory();
			for (BorrowHistory k : borrowHistory) {
		        CustomLogger.info(k);
		    }
		} 
		catch (SQLException | ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    Assertions.assertNotNull(borrowHistory);
	}
	
	@Test
	void showReturnHistory() throws SQLException, ConnectionException {
		List<ReturnHistory> returnHistory=null;
		try {
			returnHistory = TransferDAO.showAllReturnHistory();
			for (ReturnHistory k :returnHistory) {
		        CustomLogger.info(k);
		    }
		} 
		catch (SQLException | ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    Assertions.assertNotNull(returnHistory);
	}
	@Test
	void updateAcceptedStatus() throws SQLException, ConnectionException {
	    try {
	        boolean updated = BookDao.updateStockBasedOnAdminAction(1, 7);
	        Assertions.assertTrue(updated);

	    } catch (SQLException | ConnectionException e) {
	        e.printStackTrace();
	    }
	}
	@Test
	void checkStatus() throws SQLException,ConnectionException{
		try {
			int answer=TransferDAO.getBookIdWithPendingStatus(1);
			Assertions.assertEquals(answer,1);
		}
		catch(SQLException | ConnectionException e){
			e.printStackTrace();
		}
	}

	
}
