package com.orderscontrol.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.orderscontrol.demo.dto.OrderDetailDto;
import com.orderscontrol.demo.dto.OrderDto;
import com.orderscontrol.demo.entity.Item;
import com.orderscontrol.demo.entity.Order;
import com.orderscontrol.demo.entity.OrderDetail;
import com.orderscontrol.demo.repository.ItemRepository;
import com.orderscontrol.demo.repository.OrderRepository;
import com.orderscontrol.demo.utils.ObjectMapperUtils;
import com.orderscontrol.demo.utils.Security;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jmpiedra
 */
@Slf4j
@Service
public class OrderService extends BaseServiceImp<Order> {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private ItemRepository itemRepository;

	public OrderService(OrderRepository repository) {
		super(repository);
	}

	public Order create(OrderDto orderDto) {
		Order entitySaved = ObjectMapperUtils.map(orderDto, Order.class);
		entitySaved.setOrderDetails(null);
		List<OrderDetailDto> detailDto = orderDto.getOrderDetails();
		for (OrderDetailDto orderDetailDto : detailDto) {
			Optional<Item> item = itemRepository.findById(orderDetailDto.getItemId());
			if (item.isPresent()) {
				OrderDetail detail = new OrderDetail();
				detail.setItem(item.get());
				detail.setPrice(orderDetailDto.getPrice());
				detail.setParticipants(orderDetailDto.getParticipants());
				detail.setCreatedBy(Security.getCurrentUser().getUsername());
				detail.setKeyData(orderDetailDto.getKey());	
				detail.setStatus("ACTIVE");
				entitySaved.addDetail(detail);
			}
		}
		entitySaved.setStatus("EN PROCESO");
		return repository.save(entitySaved);
	}

	public Order update(Long id, @Valid OrderDto orderDto) {
		Order existentEntity = repository.findById(id).get();

		Order entitySaved = ObjectMapperUtils.map(orderDto, Order.class);
		entitySaved.setOrderDetails(null);
		List<OrderDetailDto> detailDto = orderDto.getOrderDetails();
		for (OrderDetailDto orderDetailDto : detailDto) {
			Optional<Item> item = itemRepository.findById(orderDetailDto.getItemId());
			if (item.isPresent()) {
				OrderDetail detail = new OrderDetail();
				detail.setItem(item.get());
				detail.setPrice(orderDetailDto.getPrice());
				detail.setParticipants(orderDetailDto.getParticipants());
				detail.setCreatedBy(Security.getUsernameFromToken());
				entitySaved.addDetail(detail);
			}
		}
		entitySaved.setId(existentEntity.getId());
		entitySaved.setCreationDate(existentEntity.getCreationDate());
		entitySaved.setStatus(existentEntity.getStatus());
		return repository.save(entitySaved);
	}

	public Order sendToPay(Long id) {
		return changeOrderStatus(id , "EN CAJA");

	}

	public Order completeOrder(Long id) {
		return changeOrderStatus(id , "COMPLETADO");
	}
	
	public Order processOrder(Long id) {
		return changeOrderStatus(id , "EN PROCESO");
	}
	
	public Order cancelOrder(Long id) {
		return changeOrderStatus(id , "ANULADO");
	}
	
	
	
	public Order changeOrderStatus(Long id , String status) {
		Order existentEntity = repository.findById(id).get();
		existentEntity.setStatus(status);
		return repository.save(existentEntity);
	}

}
