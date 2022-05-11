package frontcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.EmployeeController;
import controllers.FinanceManagerController;
import controllers.HomeController;
import controllers.LoginController;

public class Dispatcher {
	
	public static void myVirtualRouter(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		System.out.println("\n\n\tIN OUR DISPATCHER (myVirtualRouter()) ");

		System.out.println("Current URL: " + req.getRequestURL());
		System.out.println("Current URI: " + req.getRequestURI());
		
		
		EmployeeController employeeController = new EmployeeController();
		
		switch (req.getRequestURI()) {
		
		case "/ExpenseReimbursementSystem/json/newreimbursement":
			System.out.println("case: employee new reimbursement request");
			employeeController.newEmployeeReimbursementRequest(req,resp);
			break;
			
		case "/ExpenseReimbursementSystem/json/approvereimbursement":
			System.out.println("case: manager approve reimbursement");
			FinanceManagerController.approveReimbursement(req, resp);
			break;
			
		case "/ExpenseReimbursementSystem/json/denyreimbursement":
			System.out.println("case: manager deny reimbursement");
			FinanceManagerController.denyReimbursement(req, resp);
			break;			
		
		case "/ExpenseReimbursementSystem/json/emreimbursementlist":
			System.out.println("case: employee environment reimbursement list");
			employeeController.getEmployeeReimbursementList(req,resp);
			break;
		
		case "/ExpenseReimbursementSystem/json/reimbursementlist":
			System.out.println("case: manager environment reimbursement list");
			FinanceManagerController.getReimbursementList(req,resp);
			break;
			
		case "/ExpenseReimbursementSystem/json/filterbyreimbursementlist":
			System.out.println("case: manager filter by reimbursement list");
			FinanceManagerController.filterByReimbursementList(req,resp);
			break;
		
		case "/ExpenseReimbursementSystem/login":
			System.out.println("case: successful login");
			LoginController.login(req, resp);
			break;
			
		case "/ExpenseReimbursementSystem/json/getCurrentUserObject":
			System.out.println("case: welcome credentials");
			HomeController.getCurrentUserFromTheirSession(req, resp);
			break;
			
		default:
			System.out.println("You gave me a bad URL");
			req.getRequestDispatcher("/resources/html/badlogin.html").forward(req, resp);
			break;
		}
		
		
	}

}
