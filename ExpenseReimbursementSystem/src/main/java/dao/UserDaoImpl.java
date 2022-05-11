package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.UserDaoImpl;
import model.User;

public class UserDaoImpl implements UserDao {
	
//	public static void main(String[] args) {
//		UserDao myUser = new UserDaoImpl();
////		System.out.println(myUser.getCredentials("mateoer","123"));
////		System.out.println(myUser.getCredentials("Raj","123"));
//		System.out.println(myUser.getCredentials("sue546","avocado"));
//		
//		
//	}

	@Override
	public User getCredentials(String username, String password) {
		
		User myUser = new User();
		
		try (Connection conn = OurCustomConnectionFactory.getConnection()) {
			
			
			
			String sql = "SELECT * FROM users u WHERE u.username = ? AND u.user_password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				myUser.setUserId(rs.getInt("user_id"));
				myUser.setUsername(rs.getString("username"));
				myUser.setPassword(rs.getString("user_password"));
				myUser.setFirstName(rs.getString("first_name"));
				myUser.setLastName(rs.getString("last_name"));
				myUser.setEmail(rs.getString("email"));
				myUser.setUserRole(rs.getInt("user_role"));
			}else {
				System.out.println("no user found");
				
			}		
			

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return myUser;
		
		
		
	}

}
