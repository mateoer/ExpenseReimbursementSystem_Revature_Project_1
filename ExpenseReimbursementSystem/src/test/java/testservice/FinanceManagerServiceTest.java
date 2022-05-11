package testservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.FinanceManagerDaoImpl;
import model.Reimbursement;
import model.User;
import service.FinanceManagerServiceImpl;

class FinanceManagerServiceTest {

	FinanceManagerServiceImpl fmServImpl;
	FinanceManagerDaoImpl mockFinManDao = mock(FinanceManagerDaoImpl.class);

	@BeforeEach
	void setUpBeforeClass() throws Exception {
		fmServImpl = new FinanceManagerServiceImpl(mockFinManDao);
	}

	@Test
	void viewReimbursementsTest() {

		User myUser = new User();
		myUser.setUserRole(1);
		myUser.setUserId(4);

		List<Reimbursement> reiList = new ArrayList<Reimbursement>();
		reiList.add(new Reimbursement(20.00, "avocado sales", 2));
		reiList.add(new Reimbursement(25.00, "brunch", 4));

		List<Reimbursement> expectedReiList = new ArrayList<Reimbursement>();
		expectedReiList.addAll(reiList);

		when(mockFinManDao.viewReimbursements(myUser)).thenReturn(reiList);

		List<Reimbursement> actualReiList = fmServImpl.viewReimbursements(myUser);
		System.out.println("Expected list: " + expectedReiList);
		System.out.println("Actual list: " + actualReiList);

		assertEquals(expectedReiList, actualReiList);
		verify(mockFinManDao, times(1)).viewReimbursements(myUser);
	}

	@Test
	void filterReimbursementsByStatusTest() {

		User myUser = new User();
		myUser.setUserRole(1);
		myUser.setUserId(4);

		Reimbursement myReimbursement = new Reimbursement();

		List<Reimbursement> reiList = new ArrayList<Reimbursement>();
		reiList.add(new Reimbursement(20.00, "avocado sales", 2));
		reiList.add(new Reimbursement(25.00, "brunch", 4));

		List<Reimbursement> expectedReiList = new ArrayList<Reimbursement>();
		expectedReiList.addAll(reiList);
		
		/////FILTER PENDING
		myReimbursement.setReiStatusId(1);
		when(mockFinManDao.filterReimbursementsByStatus(myReimbursement, myUser)).thenReturn(reiList);

		List<Reimbursement> actualReiList = fmServImpl.filterReimbursementsByStatus(myReimbursement, myUser);
		System.out.println("Expected list: " + expectedReiList);
		System.out.println("Actual list: " + actualReiList);

		assertEquals(expectedReiList, actualReiList);
		verify(mockFinManDao, times(1)).filterReimbursementsByStatus(myReimbursement, myUser);
		
		
		
		//////FILTER APPROVED
		myReimbursement.setReiStatusId(2);
		when(mockFinManDao.filterReimbursementsByStatus(myReimbursement, myUser)).thenReturn(reiList);
		System.out.println("Expected list: " + expectedReiList);
		System.out.println("Actual list: " + actualReiList);

		assertEquals(expectedReiList, actualReiList);
		verify(mockFinManDao, times(1)).filterReimbursementsByStatus(myReimbursement, myUser);
		
		
		
		////////FILTER DENIED
		myReimbursement.setReiStatusId(3);
		when(mockFinManDao.filterReimbursementsByStatus(myReimbursement, myUser)).thenReturn(reiList);
		System.out.println("Expected list: " + expectedReiList);
		System.out.println("Actual list: " + actualReiList);

		assertEquals(expectedReiList, actualReiList);
		verify(mockFinManDao, times(1)).filterReimbursementsByStatus(myReimbursement, myUser);

	}
	
	@Test
	void approveDenyReimbursementTest() {
		
		User myUser = new User();
		myUser.setUserRole(1);
		myUser.setUserId(4);
		
		Reimbursement myReimbursement = new Reimbursement();
		myReimbursement.setReiId(12); //an existing reimbursement		
		
		
		
		///////APPROVE
		boolean testReiResp1 = true;
		myReimbursement.setReiStatusId(2);
		when(mockFinManDao.approveReimbursement(myReimbursement, myUser)).thenReturn(testReiResp1);
		boolean addReiResp1 = fmServImpl.approveReimbursement(myReimbursement, myUser);
		assertEquals(testReiResp1, addReiResp1);
		verify(mockFinManDao, times(1)).approveReimbursement(myReimbursement, myUser);
		
		//////DENY
		boolean testReiResp2 = true;
		myReimbursement.setReiStatusId(3);
		when(mockFinManDao.denyReimbursement(myReimbursement, myUser)).thenReturn(testReiResp2);
		boolean addReiResp2 = fmServImpl.denyReimbursement(myReimbursement, myUser);
		assertEquals(testReiResp2, addReiResp2);
		verify(mockFinManDao, times(1)).denyReimbursement(myReimbursement, myUser);
	}

}
