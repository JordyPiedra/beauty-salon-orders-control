package com.orderscontrol.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.orderscontrol.demo.dto.OrderDto;
import com.orderscontrol.demo.entity.Order;
import com.orderscontrol.demo.service.OrderService;
import com.orderscontrol.demo.utils.ObjectMapperUtils;

@RestController
@RequestMapping(path = "/orders")
public class OrderController extends BaseController<OrderService, OrderDto> {

	@Autowired
	private OrderService service;
	
	public OrderController(OrderService orderService) {
		super(orderService);
	}
	
	
	@PostMapping()
	@Override
	public ResponseEntity<Object> create(@Valid @RequestBody OrderDto orderDto) {
		Order entity = service.create(orderDto);
		OrderDto dto = ObjectMapperUtils.map(entity, OrderDto.class);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	@Override
	public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody OrderDto orderDto) {
		Order entity = service.update(id, orderDto);
		OrderDto dto = ObjectMapperUtils.map(entity, OrderDto.class);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	@PatchMapping("/{id}/pay")
	public  EntityModel<OrderDto> sendToPay(@PathVariable Long id) throws Exception {
		Order entity = service.sendToPay(id);
		OrderDto dto = ObjectMapperUtils.map(entity, OrderDto.class);

		EntityModel<OrderDto> model = EntityModel.of(dto);
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).findAll());
		model.add(linkToUsers.withRel("all-items"));
		return model;
	}
	
	@PatchMapping("/{id}/complete")
	public  EntityModel<OrderDto> completeOrder(@PathVariable Long id) throws Exception {
		Order entity = service.completeOrder(id);
		OrderDto dto = ObjectMapperUtils.map(entity, OrderDto.class);

		EntityModel<OrderDto> model = EntityModel.of(dto);
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).findAll());
		model.add(linkToUsers.withRel("all-items"));
		return model;
	}

	@PatchMapping("/{id}/process")
	public  EntityModel<OrderDto> processOrder(@PathVariable Long id) throws Exception {
		Order entity = service.processOrder(id);
		OrderDto dto = ObjectMapperUtils.map(entity, OrderDto.class);

		EntityModel<OrderDto> model = EntityModel.of(dto);
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).findAll());
		model.add(linkToUsers.withRel("all-items"));
		return model;
	}
	
	@PatchMapping("/{id}/cancel")
	public  EntityModel<OrderDto> cancelOrder(@PathVariable Long id) throws Exception {
		Order entity = service.cancelOrder(id);
		OrderDto dto = ObjectMapperUtils.map(entity, OrderDto.class);

		EntityModel<OrderDto> model = EntityModel.of(dto);
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).findAll());
		model.add(linkToUsers.withRel("all-items"));
		return model;
	}
}
