package com.orderscontrol.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.orderscontrol.demo.entity.User;
import com.orderscontrol.demo.entity.UserJPAService;
import com.orderscontrol.demo.exceptions.ItemNotFoundException;

@RestController
public class RUserController {

	// RequestMapping(method = RequestMethod.GET, path = "/hello-world")

	@Autowired
	UserJPAService service;

	@GetMapping("/jpa/users")
	public List<User> findAll() {
		return service.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> findUser(@PathVariable Integer id) throws ItemNotFoundException {
		User user =  service.findOne(id);
		if (user == null )
			throw new ItemNotFoundException("id-"+id);
		
		EntityModel<User> model = EntityModel.of(user);
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).findAll());
		model.add(linkToUsers.withRel("all-users"));
		return model;
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<Object> findUser(@Valid @RequestBody User user) {
		User userSaved = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(userSaved.getId()).toUri();
	return 	ResponseEntity.created(location).build();
	}
 
}
