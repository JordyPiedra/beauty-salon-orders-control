package com.orderscontrol.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderscontrol.demo.entity.User;
import com.orderscontrol.demo.exceptions.ItemNotFoundException;
import com.orderscontrol.demo.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jmpiedra
 */
@Slf4j
@Service
public class UserService extends BaseServiceImp<User> {

	@Autowired
	private UserRepository repository;

	public UserService(UserRepository repository) {
		super(repository);
	}

	public User login(String username, String pwd) {
		Optional<User> entity = repository.findLogin(username, pwd);

		if (!entity.isPresent()) {
			throw new ItemNotFoundException("Bad credentials");
		}

		return entity.get();
	}

}
