package com.coderscampus;

//import com.coderscampus.lesson3.User;

public class UserService {
	public User createUser(String username, String password, String name) {
		User loginUser = new User();
		loginUser.setUsername(username);
		loginUser.setPassword(password);
		loginUser.setName(name);
		
		return loginUser;
	}
}
