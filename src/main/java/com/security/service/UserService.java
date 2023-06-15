package com.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.security.model.User;

@Service
public class UserService {

	List<User> list = new ArrayList<>();

//Create Fake Api;
	public UserService() {
		list.add(new User("mhaku", "Shravan123", "mhaku15@gmail.com"));
		list.add(new User("onkar", "onkar123", "onkar15@gmail.com"));
		list.add(new User("pradip", "pradip123", "15@gmail.com"));
	}

	// get All user
	public List<User> getAllUser() {
		return this.list;
	}
	// get Single User

	public User getUser(String username) {
		return this.list.stream().filter((user) -> user.getUsername().equals(username)).findAny().orElse(null);
	}

// Add user
	public User addUser(User user) {
		list.add(user);
		return user;
	}
}
