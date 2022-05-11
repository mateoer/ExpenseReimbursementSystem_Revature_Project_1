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

import dao.EmployeeDaoImpl;
import model.Reimbursement;
import model.User;
import service.EmployeeServiceImpl;

class EmployeeServiceTest {
	
	EmployeeServiceImpl empServImpl;
	EmployeeDaoImpl mockEmpDao = mock(EmployeeDaoImpl.class);

	@BeforeEach
	void setUpBeforeClass() throws Exception {
		empServImpl = new EmployeeServiceImpl(mockEmpDao);
	}


	@Test
	void viewPastTicketsTest() {
		
		List<Reimbursement> reiList = new ArrayList<Reimbursement>();
		reiList.add(new Reimbursement(20.00, "avocado sales", 2));
		reiList.add(new Reimbursement(25.00, "brunch", 4));
		
		List<Reimbursement> expectedReiList = new ArrayList<Reimbursement>();
		expectedReiList.addAll(reiList);
		
		User myUser = new User();
		myUser.setUserRole(2);
		myUser.setUserId(3);
		when(mockEmpDao.viewPastTickets(myUser)).thenReturn(reiList);
		
		
		List<Reimbursement> actualReiList = empServImpl.viewPastTickets(myUser);
		System.out.println("Expected list: "+ expectedReiList);
		System.out.println("Actual list: "+ actualReiList);
		
		assertEquals(expectedReiList, actualReiList);
		verify(mockEmpDao, times(1)).viewPastTickets(myUser);
		
	}
	
	@Test
	void addReimbursementRequestTest() {
		
		User myUser = new User();
		myUser.setUserRole(2);
		myUser.setUserId(3);
		
		Reimbursement rei1 = new Reimbursement(20.00,"avocado sales", 2);
		Reimbursement rei2 = new Reimbursement(25.00,"brunch", 4);
		
		boolean testReiResp1 = true;
		boolean testReiResp2 = true;
		when(mockEmpDao.addReimbursementRequest(myUser, rei1)).thenReturn(testReiResp1);
		when(mockEmpDao.addReimbursementRequest(myUser, rei2)).thenReturn(testReiResp2);
		
		boolean addReiResp1 = empServImpl.addReimbursementRequest(myUser, rei1);
		boolean addReiResp2 = empServImpl.addReimbursementRequest(myUser, rei2);
		
		assertEquals(testReiResp1, addReiResp1);
		assertEquals(testReiResp2, addReiResp2);
		
		verify(mockEmpDao, times(1)).addReimbursementRequest(myUser, rei1);
		verify(mockEmpDao, times(1)).addReimbursementRequest(myUser, rei2);
		
		
		
	}

}
