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
import service.FinanceManagerService;
import service.FinanceManagerServiceImpl;

public class FinanceManagerController {

	public static void approveReimbursement(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		FinanceManagerService manService = new FinanceManagerServiceImpl();

		ObjectMapper myMapper = new ObjectMapper();
		Reimbursement rei = myMapper.readValue(req.getInputStream(), Reimbursement.class);
		User myManager = new User();
		myManager.setUserId((Integer) req.getSession().getAttribute("userId"));
		myManager.setUserRole((Integer) req.getSession().getAttribute("userRoleId"));

		boolean success = manService.approveReimbursement(rei, myManager);

		resp.setContentType("text/html");
		if (success) {
			resp.getWriter().println("Approved!");
		} else {
			resp.getWriter().println("Something went wrong.");
		}

	}

	public static void denyReimbursement(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		FinanceManagerService manService = new FinanceManagerServiceImpl();

		ObjectMapper myMapper = new ObjectMapper();
		Reimbursement rei = myMapper.readValue(req.getInputStream(), Reimbursement.class);
		User myManager = new User();
		myManager.setUserId((Integer) req.getSession().getAttribute("userId"));
		myManager.setUserRole((Integer) req.getSession().getAttribute("userRoleId"));

		boolean success = manService.denyReimbursement(rei, myManager);

		resp.setContentType("text/html");
		if (success) {
			resp.getWriter().println("Success!");
		} else {
			resp.getWriter().println("Something went wrong.");
		}

	}

	public static void getReimbursementList(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		List<Reimbursement> reiList = new ArrayList<>();
		FinanceManagerService myFM = new FinanceManagerServiceImpl();

		User myManager = new User();
		myManager.setUserId((Integer) req.getSession().getAttribute("userId"));
		myManager.setUserRole((Integer) req.getSession().getAttribute("userRoleId"));

		reiList = myFM.viewReimbursements(myManager);

		resp.setContentType("application/json");
		PrintWriter printer = resp.getWriter();
		printer.write(new ObjectMapper().writeValueAsString(reiList));

	}

	public static void filterByReimbursementList(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		List<Reimbursement> reiList = new ArrayList<>();
		FinanceManagerService myFM = new FinanceManagerServiceImpl();

		ObjectMapper myMapper = new ObjectMapper();
		Reimbursement rei = myMapper.readValue(req.getInputStream(), Reimbursement.class);
		User myManager = new User();
		myManager.setUserId((Integer) req.getSession().getAttribute("userId"));
		myManager.setUserRole((Integer) req.getSession().getAttribute("userRoleId"));
		
		reiList = myFM.filterReimbursementsByStatus(rei,myManager);
		
		resp.setContentType("text/html");
		if (!(reiList.isEmpty())) {
			resp.getWriter().println("Success!");
			resp.setContentType("application/json");
			PrintWriter printer = resp.getWriter();
			printer.write(new ObjectMapper().writeValueAsString(reiList));
		} else {
			resp.getWriter().println("Something went wrong.");
		}		
				

	}

}
