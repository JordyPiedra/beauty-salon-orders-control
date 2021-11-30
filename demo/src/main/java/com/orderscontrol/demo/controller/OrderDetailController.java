package com.orderscontrol.demo.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.orderscontrol.demo.dto.OrderDto;
import com.orderscontrol.demo.entity.BaseEntity;
import com.orderscontrol.demo.entity.OrderDetail;
import com.orderscontrol.demo.service.OrderService;
import com.orderscontrol.demo.utils.ObjectMapperUtils;

@RestController
@RequestMapping(path = "/orders/{id}/details")
public class OrderDetailController extends BaseController<OrderService, OrderDto> {

	private OrderService orderService;
	
	public OrderDetailController(OrderService orderService) {
		super(orderService);
	}
	
	//public ResponseEntity<Object> create(@PathVariable Long id, @Valid OrderDetail entityDto) {
//		Object entitySaved = orderService.create(id,(BaseEntity) ObjectMapperUtils.map(entityDto, orderService.entityClass));
//		U dto = ObjectMapperUtils.map(entitySaved, dtoClass);
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId())
//				.toUri();
//		return ResponseEntity.created(location).build();
//	}

}
