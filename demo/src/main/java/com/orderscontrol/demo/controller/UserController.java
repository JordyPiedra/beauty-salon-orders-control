package com.orderscontrol.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orderscontrol.demo.dto.UserDto;
import com.orderscontrol.demo.dto.UserLoginDto;
import com.orderscontrol.demo.entity.User;
import com.orderscontrol.demo.service.UserService;
import com.orderscontrol.demo.utils.ObjectMapperUtils;
import com.orderscontrol.demo.utils.Security;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/users")
public class UserController extends BaseController<UserService, UserDto> {

	public UserController(UserService service) {
		super(service);
	}

	@Autowired
	private UserService service;

	@PostMapping("/login")
	public UserLoginDto login(@RequestParam("username") String username, @RequestParam("password") String pwd) {
		User user = service.login(username, pwd);
		String token = Security.getJWTToken(user);
		user.setToken(token);
		return ObjectMapperUtils.map(user, UserLoginDto.class);
	}

	@PostMapping("/register")
	public User register(@RequestParam("username") String username, @RequestParam("password") String pwd) {
		User user = new User();
		user.setUsername(username);
		user.setPwd(pwd);
		return service.create(user);
	}

}
