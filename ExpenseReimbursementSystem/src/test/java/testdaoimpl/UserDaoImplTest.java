package testdaoimpl;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.OurCustomConnectionFactory;
import dao.UserDao;
import dao.UserDaoImpl;

class UserDaoImplTest {
	
	
	UserDao myUser = new UserDaoImpl();

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
	void test() {
		
		//existing employee
		assertEquals("123", myUser.getCredentials("mateoer","123").getPassword());
		assertEquals(true, myUser.getCredentials("mateoer","123").getPassword().equals("123"));
		
		//existing manager
		assertEquals("abc", myUser.getCredentials("admin","abc").getPassword());
		assertEquals(true, myUser.getCredentials("admin","abc").getPassword().equals("abc"));
		
		//non exisiting person
		assertEquals(null, myUser.getCredentials("Raj","123").getFirstName());
		assertEquals("Sue", myUser.getCredentials("sue546","avocado").getFirstName());
		
		//existing user
		assertNotEquals("Sue", myUser.getCredentials("sue546","avocado").getPassword());
		
	}

}
