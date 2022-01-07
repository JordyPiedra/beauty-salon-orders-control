package com.orderscontrol.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.orderscontrol.demo.dto.BaseDto;
import com.orderscontrol.demo.entity.BaseEntity;
import com.orderscontrol.demo.exceptions.ItemNotFoundException;
import com.orderscontrol.demo.service.BaseServiceImp;
import com.orderscontrol.demo.utils.ObjectMapperUtils;

public class BaseController<T extends BaseServiceImp, U extends BaseDto> {

	private final T service;

	private final Class<U> dtoClass;

	public BaseController(T service) {
		this.service = service;
		this.dtoClass = getDtoClass();
	}

	@SuppressWarnings("unchecked")
	private Class<U> getDtoClass() {
		return (Class<U>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}

	@GetMapping()
	public List<U> findAll() throws Exception {
		return ObjectMapperUtils.mapAll(service.selectAll(),dtoClass);
	}

	@GetMapping("/{id}")
	public EntityModel<U> findOne(@PathVariable Long id) throws Exception {
		U entityDto = ObjectMapperUtils.map(service.selectOne(id), dtoClass);
		if (entityDto == null)
			throw new ItemNotFoundException("id-" + id);

		EntityModel<U> model = EntityModel.of(entityDto);
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).findAll());
		model.add(linkToUsers.withRel("all-items"));
		return model;
	}

	@PostMapping()
	public ResponseEntity<Object> create(@Valid @RequestBody U entityDto) {
		Object entitySaved = service.create((BaseEntity) ObjectMapperUtils.map(entityDto, service.entityClass));
		U dto = ObjectMapperUtils.map(entitySaved, dtoClass);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) throws ItemNotFoundException {
		Object entitySaved  =  service.deleteById(id);
		if (entitySaved == null )
			throw new ItemNotFoundException("id-"+id);
		
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable Long id,@Valid @RequestBody U entityDto) {
		Object entitySaved = service.update(id,(BaseEntity) ObjectMapperUtils.map(entityDto, service.entityClass));
		U dto = ObjectMapperUtils.map(entitySaved, dtoClass);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

}
