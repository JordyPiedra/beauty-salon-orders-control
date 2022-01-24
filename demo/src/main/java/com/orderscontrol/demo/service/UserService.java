package com.orderscontrol.demo.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository repository;

	public UserService(UserRepository repository) {
		super(repository);
	}

	@Override
	public User create(User entity) {
		entity.setPwd(passwordEncoder.encode(entity.getPwd()));
		return super.create(entity);
	}

	public User login(String username, String pwd) {
		Optional<User> entity = repository.findByUsername(username);

		if (!entity.isPresent()) {
			throw new ItemNotFoundException("User Not Found");
		}
		if (passwordEncoder.matches(pwd, entity.get().getPwd()))
			return entity.get();

		throw new ItemNotFoundException("Bad credentials");
	}

	@Override
	public User update(Long id, User entityUpdated) {
		User entity = repository.findById(id).get();
		if (entity == null)
			return null;
		entityUpdated.setId(entity.getId());
		if (StringUtils.isAllEmpty(entityUpdated.getPwd())) {
			entityUpdated.setPwd(entity.getPwd());
		} else {
			entityUpdated.setPwd(passwordEncoder.encode(entityUpdated.getPwd()));
		}
		return repository.save(entityUpdated);
	}

}
