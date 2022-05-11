package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.UserService;
import service.UserServiceImpl;

public class LoginController {

	public static void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		req.getRequestDispatcher("/index.html").forward(req, resp);
	}

	public static void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String myPath = null;

		if (!req.getMethod().equals("POST")) {
			myPath = "/index.html";
			req.getRequestDispatcher(myPath).forward(req, resp);
		}
		
		//extract the form data
		String username = req.getParameter("theirUsername");
		String password = req.getParameter("theirPassword");
		
		UserService myUserService = new UserServiceImpl();
		User currentUser = new User();
		currentUser = myUserService.getCredentials(username, password);
		
		String currentUsername = currentUser.getUsername();
		String currentPassword = currentUser.getPassword();
		
		if(currentUsername != null & currentPassword != null) {
			
			
			if(currentUser.getUsername().equals(username) && currentUser.getPassword().equals(password)) {
				
				req.getSession().setAttribute("currentUser", username );
				req.getSession().setAttribute("loggedInPassword", password);
				req.getSession().setAttribute("firstName", currentUser.getFirstName());
				req.getSession().setAttribute("lastName", currentUser.getLastName());
				req.getSession().setAttribute("userRoleId", currentUser.getUserRole());
				req.getSession().setAttribute("userId", currentUser.getUserId());
				req.getSession().setAttribute("email", currentUser.getEmail());
				
				switch (currentUser.getUserRole()) {
				
				case 1:
					myPath = "/resources/html/FMHome.html";
					req.getRequestDispatcher(myPath).forward(req,resp);
					break;
					
				case 2:
					myPath = "/resources/html/EMHome.html";
					req.getRequestDispatcher(myPath).forward(req,resp);
					break;

				default:
					myPath = "/resources/html/badlogin.html";
					req.getRequestDispatcher(myPath).forward(req, resp);
					break;
				}			
				
				
			}else {
				System.out.println("Wrong username or password");
			}
			
		}else{
			myPath = "/resources/html/badlogin.html";
			req.getRequestDispatcher(myPath).forward(req, resp);
		}
		
		
		
	}
}
