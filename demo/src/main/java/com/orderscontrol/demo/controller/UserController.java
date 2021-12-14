package com.orderscontrol.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orderscontrol.demo.entity.User;
import com.orderscontrol.demo.service.UserService;
import com.orderscontrol.demo.utils.Security;

@RestController
public class UserController {

	@Autowired
	UserService service;

	@PostMapping("/login")
	public User login(@RequestParam("username") String username, @RequestParam("password") String pwd) {

		User user = service.login(username, pwd);
		String token = Security.getJWTToken(username);
		user.setToken(token);
		return user;

	}

	@PostMapping("/register")
	public User register(@RequestParam("username") String username, @RequestParam("password") String pwd) {
		User user = new User();
		user.setUsername(username);
		user.setPwd(pwd);
		return service.create(user);
	}


}
