package service;

import java.util.List;

import dao.FinanceManagerDao;
import dao.FinanceManagerDaoImpl;
import model.Reimbursement;
import model.User;

public class FinanceManagerServiceImpl implements FinanceManagerService {
	
//	FinanceManagerDao myManagerDao = new FinanceManagerDaoImpl();
	
	FinanceManagerDao myManagerDao = null;
	
	public FinanceManagerServiceImpl() {
		super();
		this.myManagerDao = new FinanceManagerDaoImpl();
	}
	
	public FinanceManagerServiceImpl(FinanceManagerDaoImpl myManagerDao) {
		super();
		this.myManagerDao = myManagerDao;
	}
	
	///////////////////////////////////////////////////////////////

	@Override
	public List<Reimbursement> viewReimbursements(User myManager) {
		return myManagerDao.viewReimbursements(myManager);
	}

	@Override
	public boolean approveReimbursement(Reimbursement myReimbursement, User myManager) {
		return myManagerDao.approveReimbursement(myReimbursement, myManager);
	}

	@Override
	public boolean denyReimbursement(Reimbursement myReimbursement, User myManager) {
		return myManagerDao.denyReimbursement(myReimbursement, myManager);
	}

	@Override
	public List<Reimbursement> filterReimbursementsByStatus(Reimbursement myReimbursement, User myManager) {
		return myManagerDao.filterReimbursementsByStatus(myReimbursement, myManager);
	}

}
