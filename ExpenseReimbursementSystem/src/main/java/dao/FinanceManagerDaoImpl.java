package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Reimbursement;
import model.User;

public class FinanceManagerDaoImpl implements FinanceManagerDao {	

	@Override
	public List<Reimbursement> viewReimbursements(User myManager) {
		
		List<Reimbursement> employeesReimbursements = new ArrayList<>();

		try (Connection conn = OurCustomConnectionFactory.getConnection()) {
			
			int managerId = myManager.getUserId();
			int managerRole = myManager.getUserRole();
			int managerQueriedId = Integer.MIN_VALUE;
			int managerQueriedRole = Integer.MIN_VALUE;
			String manIdQuery = "SELECT * FROM users u WHERE u.user_id = ? AND u.user_role = 1;";
			PreparedStatement ps1 = conn.prepareStatement(manIdQuery);
			ps1.setInt(1,managerId);
			ResultSet rs1 = ps1.executeQuery();
			if(rs1.next()) {
				managerQueriedId = rs1.getInt("user_id");
				managerQueriedRole = rs1.getInt("user_role");
			}
			
			if (managerId == managerQueriedId & managerRole == managerQueriedRole) {
				
				
				String sql = "SELECT * FROM \r\n"
						+ "reimbursement r \r\n"
						+ "LEFT JOIN reimb_status rs2  ON r.reimb_status_id = rs2.reimb_status_id \r\n"
						+ "LEFT JOIN reimb_type rt ON r.reimb_type_id = rt.reimb_type_id \r\n"
						+ "LEFT JOIN users u ON r.reimb_author = u.user_id ORDER BY r.reimb_submitted";

				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();

				

				while (rs.next()) {
					employeesReimbursements.add(new Reimbursement(rs.getInt("reimb_id"), rs.getDouble("reimb_amount"),
							rs.getDate("reimb_submitted"), rs.getDate("reimb_resolved"), rs.getString("reimb_description"),
							rs.getInt("reimb_author"), rs.getInt("reimb_resolver"), rs.getInt("reimb_status_id"),
							rs.getInt("reimb_type_id"),
							rs.getString("reimb_status"),
							rs.getString("reimb_type"),
							rs.getString("first_name"),
							rs.getString("last_name")
							));
				}
				
				
				
			} else {
				System.out.println("You do not have manager credentials.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employeesReimbursements;
	}

	@Override
	public boolean approveReimbursement(Reimbursement myReimbursement, User myManager) {
		try (Connection conn = OurCustomConnectionFactory.getConnection()) {
			
			int managerId = myManager.getUserId();
			int managerRole = myManager.getUserRole();
			int managerQueriedId = Integer.MIN_VALUE;
			int managerQueriedRole = Integer.MIN_VALUE;
			String manIdQuery = "SELECT * FROM users u WHERE u.user_id = ? AND u.user_role = 1;";
			PreparedStatement ps1 = conn.prepareStatement(manIdQuery);
			ps1.setInt(1,managerId);
			ResultSet rs1 = ps1.executeQuery();
			if(rs1.next()) {
				managerQueriedId = rs1.getInt("user_id");
				managerQueriedRole = rs1.getInt("user_role");
			}
			
			if (managerId == managerQueriedId & managerRole == managerQueriedRole) {
				
				
				String sql = "UPDATE reimbursement SET reimb_status_id = 2, reimb_resolved = current_date, reimb_resolver = ? WHERE reimb_id = ?;";
				
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, myManager.getUserId());			
				ps.setInt(2, myReimbursement.getReiId());			
				
				int approved = ps.executeUpdate(); 
				if (approved == 1) {
					return true;
				}
				
			} else {
				System.out.println("You do not have manager credentials.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean denyReimbursement(Reimbursement myReimbursement, User myManager) {
		
		try (Connection conn = OurCustomConnectionFactory.getConnection()) {
			
			int managerId = myManager.getUserId();
			int managerRole = myManager.getUserRole();
			int managerQueriedId = Integer.MIN_VALUE;
			int managerQueriedRole = Integer.MIN_VALUE;
			String manIdQuery = "SELECT * FROM users u WHERE u.user_id = ? AND u.user_role = 1;";
			PreparedStatement ps1 = conn.prepareStatement(manIdQuery);
			ps1.setInt(1,managerId);
			ResultSet rs1 = ps1.executeQuery();
			if(rs1.next()) {
				managerQueriedId = rs1.getInt("user_id");
				managerQueriedRole = rs1.getInt("user_role");
			}
			
			if (managerId == managerQueriedId & managerRole == managerQueriedRole) {
				
				
				String sql = "UPDATE reimbursement SET reimb_status_id = 3, reimb_resolved = current_date, reimb_resolver = ? WHERE reimb_id = ?;";
				
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, myManager.getUserId());
				ps.setInt(2, myReimbursement.getReiId());			
				
				int approved = ps.executeUpdate(); 
				if (approved == 1) {
					return true;
				}
				
			} else {
				System.out.println("You do not have manager credentials.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Reimbursement> filterReimbursementsByStatus(Reimbursement myReimbursement, User myManager) {
		List<Reimbursement> employeesReimbursements = new ArrayList<>();

		try (Connection conn = OurCustomConnectionFactory.getConnection()) {
			
			int managerId = myManager.getUserId();
			int managerRole = myManager.getUserRole();
			int managerQueriedId = Integer.MIN_VALUE;
			int managerQueriedRole = Integer.MIN_VALUE;
			String manIdQuery = "SELECT * FROM users u WHERE u.user_id = ? AND u.user_role = 1;";
			PreparedStatement ps1 = conn.prepareStatement(manIdQuery);
			ps1.setInt(1,managerId);
			ResultSet rs1 = ps1.executeQuery();
			if(rs1.next()) {
				managerQueriedId = rs1.getInt("user_id");
				managerQueriedRole = rs1.getInt("user_role");
			}
			
			if (managerId == managerQueriedId & managerRole == managerQueriedRole) {
				
				
				String sql = "SELECT * FROM \r\n"
						+ "reimbursement r \r\n"
						+ "LEFT JOIN reimb_status rs2  ON r.reimb_status_id = rs2.reimb_status_id \r\n"
						+ "LEFT JOIN reimb_type rt ON r.reimb_type_id = rt.reimb_type_id \r\n"
						+ "LEFT JOIN users u ON r.reimb_author = u.user_id \r\n"
						+ "WHERE r.reimb_status_id = ? ORDER BY r.reimb_submitted";

				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, myReimbursement.getReiStatusId());
				ResultSet rs = ps.executeQuery();

				

				while (rs.next()) {
					employeesReimbursements.add(new Reimbursement(rs.getInt("reimb_id"), rs.getDouble("reimb_amount"),
							rs.getDate("reimb_submitted"), rs.getDate("reimb_resolved"), rs.getString("reimb_description"),
							rs.getInt("reimb_author"), rs.getInt("reimb_resolver"), rs.getInt("reimb_status_id"),
							rs.getInt("reimb_type_id"),
							rs.getString("reimb_status"),
							rs.getString("reimb_type"),
							rs.getString("first_name"),
							rs.getString("last_name")
							));
				}
				
				
				
			} else {
				System.out.println("You do not have manager credentials.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employeesReimbursements;
	}

	
	
	
	
	
	
	
//	public static void main(String[] args) {
//		FinanceManagerDao myDao = new FinanceManagerDaoImpl();
//		User myManager = new User();
//		myManager.setUserId(4);
//		myManager.setUserRole(1); //change to 1
//		System.out.println(myDao.viewReimbursements(myManager));
////		Reimbursement myReimbursement = new Reimbursement();
////		myReimbursement.setReiStatusId(1); //pending
////		myReimbursement.setReiStatusId(2); //approved
////		myReimbursement.setReiStatusId(3); //denied
////		myReimbursement.setReiStatusId(4); //none
////		System.out.println(myDao.filterReimbursementsByStatus(myReimbursement,myManager));
////		myReimbursement.setReiId(11);
////		System.out.println(myDao.approveReimbursement(myReimbursement,myManager));
////		System.out.println(myDao.denyReimbursement(myReimbursement,myManager));
//	}
	
	
	
	
}
