package testdaoimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.FinanceManagerDao;
import dao.FinanceManagerDaoImpl;
import dao.OurCustomConnectionFactory;
import model.Reimbursement;
import model.User;

class FinanceManagerDaoImplTest {

	FinanceManagerDao myDao = new FinanceManagerDaoImpl();

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
	
	void viewReimbursementsTest() {

		User myManager = new User();

		// this is a manager
		myManager.setUserId(4);
		myManager.setUserRole(1);
		assertNotNull(myDao.viewReimbursements(myManager), "Finance Manager view all employee reimbursements");
		assertEquals(false, myDao.viewReimbursements(myManager).isEmpty(),
				"Finance Manager view all employee reimbursements");

		// this is an employee
		myManager.setUserId(3);
		myManager.setUserRole(2);
		// returns the empty reimbs list that creates, no items added bc employee can't
		// view other employees requests
		assertEquals(true, myDao.viewReimbursements(myManager).isEmpty(),
				"Employee view other employees' reimbursements");
	}

	@Test
	
	void approveDenyReimbursementsTest() {

		User myManager = new User();
		Reimbursement myReimbursement = new Reimbursement();

		//MANAGER
		myManager.setUserId(4);
		myManager.setUserRole(1);
		
		//an existing reimbursement
		myReimbursement.setReiId(12);
		
//		myReimbursement.setReiStatusId(1); // pending
		
		myReimbursement.setReiStatusId(2); // approved
		assertEquals(true, myDao.approveReimbursement(myReimbursement, myManager),"Approving reimbursement");
		myReimbursement.setReiStatusId(3); // denied
		assertEquals(true, myDao.denyReimbursement(myReimbursement, myManager),"Denying reimbursements");
		
		myReimbursement.setReiStatusId(4); // none
		assertEquals(true, myDao.approveReimbursement(myReimbursement, myManager), "Can update to another status if needed ?");
		assertEquals(true, myDao.denyReimbursement(myReimbursement, myManager), "Can update to another status if needed ?");
		
		
		//EMPLOYEE
		myManager.setUserId(3);
		myManager.setUserRole(2);
		
		//an existing reimbursement
		myReimbursement.setReiId(12);
		
//		myReimbursement.setReiStatusId(1); // pending
		
		myReimbursement.setReiStatusId(2); // approved
		assertEquals(false, myDao.approveReimbursement(myReimbursement, myManager),"Approving reimbursement");
		myReimbursement.setReiStatusId(3); // denied
		assertEquals(false, myDao.denyReimbursement(myReimbursement, myManager),"Denying reimbursements");
		
		myReimbursement.setReiStatusId(4); // none
		assertEquals(false, myDao.approveReimbursement(myReimbursement, myManager), "Can update to another status if needed ?");
		assertEquals(false, myDao.denyReimbursement(myReimbursement, myManager), "Can update to another status if needed ?");
	}

	@Test
	void filteringReimbursementsTest() {
		
		User myManager = new User();
		Reimbursement myReimbursement = new Reimbursement();
		
		

		//MANAGER
		myManager.setUserId(4);
		myManager.setUserRole(1);
		
		
		myReimbursement.setReiStatusId(1); //pending
		assertNotNull(myDao.viewReimbursements(myManager), "Finance Manager filter pending");
		assertEquals(false, myDao.viewReimbursements(myManager).isEmpty(),
				"Finance Manager filter pending");
		
		myReimbursement.setReiStatusId(2); // approved
		assertNotNull(myDao.viewReimbursements(myManager), "Finance Manager filter approved");
		assertEquals(false, myDao.viewReimbursements(myManager).isEmpty(),
				"Finance Manager filter approved");
		
		myReimbursement.setReiStatusId(3); // denied
		assertNotNull(myDao.viewReimbursements(myManager), "Finance Manager filter denied");
		assertEquals(false, myDao.viewReimbursements(myManager).isEmpty(),
				"Finance Manager filter denied");
		
		myReimbursement.setReiStatusId(4); //none
		assertNotNull(myDao.viewReimbursements(myManager), "Finance Manager filter other");
		assertEquals(false, myDao.viewReimbursements(myManager).isEmpty(),
				"Finance Manager filter other");
	}
}
