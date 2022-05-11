package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import javax.servlet.http.HttpServlet;



public class OurCustomConnectionFactory /*extends HttpServlet*/ {	

	public static String url = "jdbc:postgresql://"+System.getenv("TRAINING_DB_ENDPOINT")+"/ERS_DB";
	public static String username = System.getenv("TRAINING_DB_USERNAME");
	public static String password = System.getenv("TRAINING_DB_PASSWORD");	
	
	
	static { 
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Static block has failed me");
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username,password);
	}
	
	
	
}
