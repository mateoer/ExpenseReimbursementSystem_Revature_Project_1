package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Reimbursement;
import model.User;
import service.EmployeeService;
import service.EmployeeServiceImpl;

public class EmployeeController {

	EmployeeServiceImpl empService;

	

	public EmployeeController() {
		super();
		empService = new EmployeeServiceImpl();
	}
	
	public EmployeeController(EmployeeServiceImpl eService) {
		super();
		empService = eService;
	}

	public EmployeeServiceImpl getrSer() {
		return empService;
	}

	public void setrSer(EmployeeServiceImpl eService) {
		empService = eService;
	}

	public void newEmployeeReimbursementRequest(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

//		EmployeeService empService = new EmployeeServiceImpl();

		ObjectMapper myMapper = new ObjectMapper();
		Reimbursement rei = myMapper.readValue(req.getInputStream(), Reimbursement.class);
		User myEmployee = new User();
		myEmployee.setUserId((Integer) req.getSession().getAttribute("userId"));
		myEmployee.setUserRole((Integer) req.getSession().getAttribute("userRoleId"));
		boolean success = empService.addReimbursementRequest(myEmployee, rei);

		resp.setContentType("text/html");
		if (success) {
			resp.getWriter().println("New reimbursement request created!");
		} else {
			resp.getWriter().println("Reimbursement request could not be created.");
			System.out.println(rei);
		}

	}

	public void getEmployeeReimbursementList(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		List<Reimbursement> reiList = new ArrayList<>();
		EmployeeService myEm = new EmployeeServiceImpl();

		User myEmployee = new User();
		myEmployee.setUserId((Integer) req.getSession().getAttribute("userId"));
		myEmployee.setUserRole((Integer) req.getSession().getAttribute("userRoleId"));

		reiList = myEm.viewPastTickets(myEmployee);

		res.setContentType("application/json");
		PrintWriter printer = res.getWriter();
		printer.write(new ObjectMapper().writeValueAsString(reiList));

	}

}
