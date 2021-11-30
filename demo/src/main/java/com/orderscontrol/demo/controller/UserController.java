package com.orderscontrol.demo.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	// RequestMapping(method = RequestMethod.GET, path = "/hello-world")
/*
	@Autowired
	UserDaoService service;

	@GetMapping("/users")
	public List<User> findAll() {
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public EntityModel<User> findUser(@PathVariable Integer id) throws UserNotFoundException {
		User user =  service.findOne(id);
		if (user == null )
			throw new UserNotFoundException("id-"+id);
		
		EntityModel<User> model = EntityModel.of(user);
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).findAll());
		model.add(linkToUsers.withRel("all-users"));
		return model;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> findUser(@Valid @RequestBody User user) {
		User userSaved = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(userSaved.getId()).toUri();
	return 	ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Integer id) throws UserNotFoundException {
		User user =  service.deleteById(id);
		if (user == null )
			throw new UserNotFoundException("id-"+id);
	}
	*/
}
