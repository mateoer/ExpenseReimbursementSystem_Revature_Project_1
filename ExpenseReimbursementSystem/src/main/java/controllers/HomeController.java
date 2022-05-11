package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.User;


public class HomeController {
	
	public static void getCurrentUserFromTheirSession(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		User myUser = new User();
		String firstName = (String) req.getSession().getAttribute("firstName");
		String lastName = (String) req.getSession().getAttribute("lastName");
//		Integer userRole = (Integer) req.getSession().getAttribute("userRoleId");
				
		myUser.setFirstName(firstName);
		myUser.setLastName(lastName);
//		myUser.setUserRole(userRole);
		
		
		res.setContentType("application/json");
		PrintWriter printer = res.getWriter();
		printer.write(new ObjectMapper().writeValueAsString(myUser));
		
	}
	
}
