package testdaoimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import dao.OurCustomConnectionFactory;
import model.Reimbursement;
import model.User;

class EmployeeDaoImplTest {
	
	EmployeeDao myDao = new EmployeeDaoImpl();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		try (Connection conn = OurCustomConnectionFactory.getConnection()) {

			conn.setAutoCommit(false);
			
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	@AfterEach
	void tearDown() throws Exception {
		try (Connection conn = OurCustomConnectionFactory.getConnection()) {

			String sql = "ROLLBACK;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate(); 

			
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	@Test
	
	void addReimbursementReqTest() {
		User myEmployee = new User();
		Reimbursement myReimbursement = new Reimbursement();
		
		//adding a dummy reimbursement
		myReimbursement.setReiTypeId(2);
		myReimbursement.setReiDescription("feria");		
		myReimbursement.setReiAmount(13.00);
		
		
		//testing with an existing employee user id
		myEmployee.setUserId(3); 		
		
		myEmployee.setUserRole(2); //employee role		
		assertEquals(true, myDao.addReimbursementRequest(myEmployee, myReimbursement));
		
		myEmployee.setUserRole(1); //finance manager role
		assertEquals(false, myDao.addReimbursementRequest(myEmployee, myReimbursement));		
		
		myEmployee.setUserRole(45); //non existing role
		assertEquals(false, myDao.addReimbursementRequest(myEmployee, myReimbursement));
		
		
		
		//testing with an existing finance manager user id
		myEmployee.setUserId(4); 		
		
						//this case fails bc user has finance manager id on DB table
		myEmployee.setUserRole(2); //employee role		
		assertEquals(false, myDao.addReimbursementRequest(myEmployee, myReimbursement),"employee role");
		
		myEmployee.setUserRole(1); //finance manager role
		assertEquals(false, myDao.addReimbursementRequest(myEmployee, myReimbursement),"finance manager role");		
		
		myEmployee.setUserRole(45); //non existing role
		assertEquals(false, myDao.addReimbursementRequest(myEmployee, myReimbursement),"non existing role");		
		
	}
	
	@Test
	void viewPastTicketsTest() {
		
		User myEmployee = new User();
		
		myEmployee.setUserId(3);
		myEmployee.setUserRole(2);		
		assertNotNull(myDao.viewPastTickets(myEmployee));
		
		
		myEmployee.setUserId(4);
		myEmployee.setUserRole(1);
		assertEquals(true, myDao.viewPastTickets(myEmployee).isEmpty(), "Finance Manager view tickets");
		
	}

}
