package service;

import dao.UserDao;
import dao.UserDaoImpl;
import model.User;

public class UserServiceImpl implements UserService {

	UserDao myUserDao = new UserDaoImpl();
	
	@Override
	public User getCredentials(String username, String password) {
		return myUserDao.getCredentials(username, password);	
	}

}
