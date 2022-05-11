package dao;

import java.util.List;

import model.Reimbursement;
import model.User;

public interface FinanceManagerDao {

	public List<Reimbursement> viewReimbursements (User myManager);
	public boolean approveReimbursement (Reimbursement myReimbursement, User myManager);
	public boolean denyReimbursement (Reimbursement myReimbursement, User myManager);
	public List<Reimbursement> filterReimbursementsByStatus (Reimbursement myReimbursement, User myManager);
	
	
}
