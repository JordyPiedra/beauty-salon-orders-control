package com.jordypiedra.demo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jordypiedra.demo.exceptions.UserNotFoundException;
import com.jordypiedra.demo.repository.UserRepository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class UserJPAService {

	@Autowired
	private UserRepository repository;

	
	public List<User> findAll() {
		return repository.findAll();
	}

	public User save(User user) {
		repository.save(user);
		return user;
	}

	public User findOne(Integer id) throws UserNotFoundException {
		Optional<User> user = repository.findById(id);
		if (!user.isPresent())
			throw new UserNotFoundException("User :" + id + " not found");

		return user.get();
	}

	
}
