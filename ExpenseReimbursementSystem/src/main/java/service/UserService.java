package service;

import model.User;

public interface UserService {
	
	public User getCredentials(String username, String password);
}
