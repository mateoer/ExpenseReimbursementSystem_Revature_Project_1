package frontcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="ExpenseReimbursementSystem", urlPatterns= {"/EMHome/*", "/login/*", "/logout", "/json/*", "/FMhome/*"})
public class MasterServlet extends HttpServlet {

protected boolean isLoggedIn(HttpServletRequest req) {
		
		boolean unrestrictedURI = req.getRequestURI().equals("/ExpenseReimbursementSystem/login")
				|| req.getRequestURI().equals("/ExpenseReimbursementSystem/login/badlogin.html")
				|| req.getRequestURI().equals("/ExpenseReimbursementSystem/logout");

		if (!unrestrictedURI & req.getSession(false) == null)
			return false;
		else
			return true;
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("IN OUR MASTER SERVLET: doGet");
		if(isLoggedIn(req)) {
			
			Dispatcher.myVirtualRouter(req, resp); 
		}else {
			resp.getWriter().println("You're not logged in");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("IN OUR MASTER SERVLET: doPost");

		if(isLoggedIn(req)) {
			
			Dispatcher.myVirtualRouter(req, resp); 
		}else {
			resp.getWriter().println("You're not logged in");
		}
	}
	
	
}
