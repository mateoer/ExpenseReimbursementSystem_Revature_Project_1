package service;

import java.util.List;

import model.Reimbursement;
import model.User;

public interface EmployeeService {
	public List<Reimbursement> viewPastTickets(User myEmployee);
	public boolean addReimbursementRequest (User myEmployee, Reimbursement newReimbursement);
}
