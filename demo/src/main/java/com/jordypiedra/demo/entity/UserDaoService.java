package com.jordypiedra.demo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jordypiedra.demo.exceptions.UserNotFoundException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<User>();

	private static int usersCounts = 3;
	static {
		users.add(new User(1, "User1", new Date()));
		users.add(new User(2, "User2", new Date()));
		users.add(new User(3, "User3", new Date()));

	}

	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++usersCounts);
		}
		users.add(user);
		return user;
	}

	public User findOne(Integer id) throws UserNotFoundException {
		for (User user : users) {
			if (id == user.getId())
				return user;
		}
		throw new UserNotFoundException("User :" + id + " not found");
	}

	public List<User> findAll() {
		return users;
	}

	public User deleteById(Integer id) {
		for (User user : users) {
			if (id == user.getId()) {
				users.remove(user);
				return user;
			}

		}
		return null;
	}
}
