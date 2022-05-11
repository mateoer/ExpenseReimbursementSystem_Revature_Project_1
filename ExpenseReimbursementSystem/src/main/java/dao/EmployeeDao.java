package dao;

import java.util.List;

import model.Reimbursement;
import model.User;

public interface EmployeeDao {
	
	public List<Reimbursement> viewPastTickets(User myEmployee);
	public boolean addReimbursementRequest (User myEmployee, Reimbursement newReimbursement);
	
}
