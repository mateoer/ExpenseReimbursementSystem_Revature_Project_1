package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Reimbursement;
import model.User;

public class EmployeeDaoImpl implements EmployeeDao {


	@Override
	public List<Reimbursement> viewPastTickets(User myEmployee) {

		List<Reimbursement> pastReimbursements = new ArrayList<>();

		try (Connection conn = OurCustomConnectionFactory.getConnection()) {

			String sql = "SELECT * FROM \r\n" + "reimbursement r \r\n"
					+ "LEFT JOIN reimb_status rs2  ON r.reimb_status_id = rs2.reimb_status_id \r\n"
					+ "LEFT JOIN reimb_type rt ON r.reimb_type_id = rt.reimb_type_id \r\n"
					+ "LEFT JOIN users u ON r.reimb_resolver = u.user_id \r\n" + "WHERE r.reimb_author = ? ORDER BY r.reimb_submitted";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, myEmployee.getUserId());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				pastReimbursements.add(new Reimbursement(rs.getInt("reimb_id"), rs.getDouble("reimb_amount"),
						rs.getDate("reimb_submitted"), rs.getDate("reimb_resolved"), rs.getString("reimb_description"),
						rs.getInt("reimb_author"), rs.getInt("reimb_resolver"), rs.getInt("reimb_status_id"),
						rs.getInt("reimb_type_id"),
						rs.getString("reimb_status"),
						rs.getString("reimb_type"),
						rs.getString("first_name"),
						rs.getString("last_name")
						));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pastReimbursements;
	}

	@Override
	public boolean addReimbursementRequest(User myEmployee, Reimbursement newReimbursement) {

		try (Connection conn = OurCustomConnectionFactory.getConnection()) {			
			
			
			int employeeId = myEmployee.getUserId();
			int employeeRole = myEmployee.getUserRole();
			int employeeQueriedId = Integer.MIN_VALUE;
			int employeeQueriedRole = Integer.MIN_VALUE;
			String manIdQuery = "SELECT * FROM users u WHERE u.user_id = ? AND u.user_role = 2";
			PreparedStatement ps1 = conn.prepareStatement(manIdQuery);
			ps1.setInt(1,employeeId);
			ResultSet rs1 = ps1.executeQuery();
			if(rs1.next()) {
				employeeQueriedId = rs1.getInt("user_id");
				employeeQueriedRole = rs1.getInt("user_role");
			}
			
			if (employeeId == employeeQueriedId & employeeRole == employeeQueriedRole) {
				
				
				String sql = "INSERT INTO reimbursement (reimb_status_id, reimb_type_id, reimb_description, reimb_amount, reimb_author, reimb_submitted)\r\n"
						+ "VALUES (1,?,?,?,?,current_date)";

				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, newReimbursement.getReiTypeId());
				ps.setString(2, newReimbursement.getReiDescription());
				ps.setDouble(3,newReimbursement.getReiAmount());
				ps.setInt(4, myEmployee.getUserId());

				int newRequest = ps.executeUpdate(); 
				if (newRequest == 1) {
					return true;
				}				
				
			} else {
				System.out.println("You do not have employee credentials.");
				return false;
			}					
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public static void main(String[] args) {

		EmployeeDao myDao = new EmployeeDaoImpl();
		User myEmployee = new User();
		Reimbursement myReimbursement = new Reimbursement();
		myEmployee.setUserId(3);
		myEmployee.setUserRole(2);
//		System.out.println(myDao.viewPastTickets(myEmployee));
		myReimbursement.setReiTypeId(2);
		myReimbursement.setReiDescription("feria");
		
		myReimbursement.setReiAmount(13.00);
		
		System.out.println(myDao.addReimbursementRequest(myEmployee, myReimbursement));

	}
}
