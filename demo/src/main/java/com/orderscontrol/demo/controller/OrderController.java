package com.orderscontrol.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderscontrol.demo.dto.OrderDto;
import com.orderscontrol.demo.service.OrderService;

@RestController
@RequestMapping(path = "/orders")
public class OrderController extends BaseController<OrderService, OrderDto> {


	public OrderController(OrderService orderService) {
		super(orderService);
	}
 
}
