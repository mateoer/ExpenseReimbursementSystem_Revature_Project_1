package dao;

import model.User;

public interface UserDao {
	
//	//CREATE
//	create new user
//	
//	//RETRIEVE
//	retrieve user by username
//	retrieve user by id
//	retrieve all users	
//	retrieve user by username and password and return user id and user role
	
	public User getCredentials(String username, String password);
//	
//	//UPDATE
//	change username
//	change password
//	change user role
//	change email
//	change last name
//	change first name
//	
//	//DELETE
//	delete an user by id
	
	
	
	
}
