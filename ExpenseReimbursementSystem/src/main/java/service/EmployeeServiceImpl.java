package service;

import java.util.List;

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import model.Reimbursement;
import model.User;

public class EmployeeServiceImpl implements EmployeeService {
	
//	EmployeeDao myEmployeeDao = new EmployeeDaoImpl();
	
	EmployeeDao myEmployeeDao = null;
	
	public EmployeeServiceImpl() {
		super();
		this.myEmployeeDao = new EmployeeDaoImpl();
	}
	
	public EmployeeServiceImpl(EmployeeDaoImpl myEmployeeDao) {
		super();
		this.myEmployeeDao = myEmployeeDao;
	}
	
	////////////////////////////////////////////////////////

	@Override
	public List<Reimbursement> viewPastTickets(User myEmployee) {
		return myEmployeeDao.viewPastTickets(myEmployee);
	}

	@Override
	public boolean addReimbursementRequest(User myEmployee, Reimbursement newReimbursement) {
		return myEmployeeDao.addReimbursementRequest(myEmployee, newReimbursement);
	}

}
