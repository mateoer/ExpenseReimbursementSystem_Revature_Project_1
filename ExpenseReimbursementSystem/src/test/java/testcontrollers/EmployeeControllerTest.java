package testcontrollers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import controllers.EmployeeController;
import model.Reimbursement;
import model.User;
import service.EmployeeServiceImpl;

class EmployeeControllerTest {
	
	EmployeeController empController;	
	EmployeeServiceImpl mockService = mock(EmployeeServiceImpl.class);
	
	PrintWriter mockPrintWriter = mock(PrintWriter.class);
	ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
	ServletInputStream mockServletInputStream = mock(ServletInputStream.class);
	
	HttpSession mockHttpSession = mock(HttpSession.class);
	HttpServletResponse mockHttpServletResponse = mock(HttpServletResponse.class);
	HttpServletRequest mockHttpServletRequest = mock(HttpServletRequest.class);

	@BeforeEach
	void setUpBeforeClass() throws Exception {
		empController = new EmployeeController();
	}

	

	@Test
	void newReimbursementRequestTest() throws StreamReadException, DatabindException, IOException, ServletException  {		
		
		//////ARRANGE
		User myUser = new User();
//		Reimbursement myReimbursement = new Reimbursement(20.00, "avocado sales", 2);
		
		myUser.setUserId(3); //an existing employee user
		myUser.setUserRole(2);
		
		Reimbursement myReimbursement = new Reimbursement();
		myReimbursement.setReiAmount(20.00);
		myReimbursement.setReiDescription("avocado sale");
		myReimbursement.setReiTypeId(2);
		
//		myReimbursement = mockObjectMapper.readValue(mockHttpServletRequest.getInputStream(), 
//																   Reimbursement.class);
//		mockObjectMapper.readValue(mockHttpServletRequest.getInputStream(), Reimbursement.class);
		
		
		boolean testReiResp = mockService.addReimbursementRequest(myUser, myReimbursement);		
		String testReiReq = new ObjectMapper().writeValueAsString(myReimbursement);
		mockObjectMapper.readValue(testReiReq, Reimbursement.class);
		
		
		
		///whens over here
		when(mockHttpServletRequest.getSession(true)).thenReturn(mockHttpSession);
		when(mockHttpSession.getAttribute("userId")).thenReturn(myUser.getUserId());
		when(mockHttpSession.getAttribute("userRoleId")).thenReturn(myUser.getUserRole());
		
		when(mockService.addReimbursementRequest(myUser, myReimbursement)).thenReturn(testReiResp);
		when(mockObjectMapper.readValue(mockServletInputStream, Reimbursement.class)).thenReturn(myReimbursement);	
		when(mockHttpServletResponse.getWriter()).thenReturn(mockPrintWriter);
		
		
		
		//////ACT		
		empController.newEmployeeReimbursementRequest(mockHttpServletRequest, mockHttpServletResponse);
		
		////////ASSERT
		
		///verify over here
		verify(mockHttpServletRequest, times(1)).getSession(true).getAttribute("userId");
		verify(mockHttpServletRequest, times(1)).getSession(true).getAttribute("userRoleId");
		verify(mockService, times(1)).addReimbursementRequest(myUser, myReimbursement);
		verify(mockObjectMapper, times(1)).readValue(mockHttpServletRequest.getInputStream(), Reimbursement.class);		
		verify(mockHttpServletResponse, times(1)).getWriter();
	}

}
