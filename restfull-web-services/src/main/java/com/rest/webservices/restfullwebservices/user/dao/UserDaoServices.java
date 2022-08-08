package com.rest.webservices.restfullwebservices.user.dao;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.webservices.restfullwebservices.user.User;

@Component
public class UserDaoServices {
	private static List<User> users = new ArrayList<>();
	private static int userCount;

@SuppressWarnings("static-access")
//	static {
	UserDaoServices() throws ParseException, IOException {
		ExcelImport excelImport = new ExcelImport();

		users.addAll(excelImport.excelConvertor());
		this.userCount=users.size();
	}

//	}
	// find All
	public List<User> findAll() {
		return users;
	}

	// save
	public User save(User user) {
		if (user.getId() == 0)
			user.setId(++userCount);
		users.add(user);
		return user;
	}

	// find one by id
	public User findById(int id) {
		for (User u : users)
			if (u.getId() == id) {
				return u;
			}
		return null;
	}

	// delete a user by id
	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User u = iterator.next();
			if (u.getId() == id) {
				iterator.remove();
				return u;
			}
		}
		return null;
	}
}
