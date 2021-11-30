package com.orderscontrol.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderscontrol.demo.entity.Order;
import com.orderscontrol.demo.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jmpiedra
 */
@Slf4j
@Service
public class OrderService extends BaseServiceImp<Order> {

	@Autowired
	private OrderRepository repository;

	public OrderService(OrderRepository repository) {
		super(repository);
	}

}
