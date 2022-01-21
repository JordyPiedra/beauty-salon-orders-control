package com.orderscontrol.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.orderscontrol.demo.dto.DashboardOrderDto;
import com.orderscontrol.demo.dto.OrderDetailDto;
import com.orderscontrol.demo.dto.OrderDto;
import com.orderscontrol.demo.entity.Order;
import com.orderscontrol.demo.entity.OrderDetail;
import com.orderscontrol.demo.exceptions.ItemNotFoundException;
import com.orderscontrol.demo.service.OrderService;
import com.orderscontrol.demo.utils.ObjectMapperUtils;

@RestController
@RequestMapping(path = "/orders")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController extends BaseController<OrderService, OrderDto> {

	@Autowired
	private OrderService service;

	public OrderController(OrderService orderService) {
		super(orderService);
	}

	@GetMapping("/dashboard")
	public List<DashboardOrderDto> findAllDashboard() throws Exception {
		List<OrderDto> orderDtoList = ObjectMapperUtils.mapAll(service.selectAll(), OrderDto.class);
		List<DashboardOrderDto> dashboardOrderList = new ArrayList<DashboardOrderDto>();
		for (OrderDto orderDto : orderDtoList) {
			DashboardOrderDto dto = new DashboardOrderDto();
			dto.setId(orderDto.getId());
			dto.setTitle(orderDto.getClientName());
			dto.setStatus(orderDto.getStatus());
			dto.setSubtitle(orderDto.getCreationDate().toString());
			dto.setPrice(orderDto.getTotal());
			dto.setServices(orderDto.getOrderDetails().size());
			dashboardOrderList.add(dto);
		}
		return dashboardOrderList;
	}

	@Override
	public EntityModel<OrderDto> findOne(@PathVariable Long id) throws Exception {
		Order order = service.selectOne(id);

		OrderDto entityDto = ObjectMapperUtils.map(service.selectOne(id), OrderDto.class);
		if (entityDto == null)
			throw new ItemNotFoundException("id-" + id);

		for (OrderDetailDto detail : entityDto.getOrderDetails()) {
			OrderDetail detailValue = order.getDetailById(detail.getId());
			if (detailValue != null) {
				detail.setKey(detailValue.getKeyData());
				detail.setItemId(detailValue.getItem().getId());
			}

		}
		EntityModel<OrderDto> model = EntityModel.of(entityDto);
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).findAll());
		model.add(linkToUsers.withRel("all-items"));
		return model;
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
	public EntityModel<OrderDto> sendToPay(@PathVariable Long id) throws Exception {
		Order entity = service.sendToPay(id);
		OrderDto dto = ObjectMapperUtils.map(entity, OrderDto.class);

		EntityModel<OrderDto> model = EntityModel.of(dto);
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).findAll());
		model.add(linkToUsers.withRel("all-items"));
		return model;
	}

	@PatchMapping("/{id}/complete")
	public EntityModel<OrderDto> completeOrder(@PathVariable Long id) throws Exception {
		Order entity = service.completeOrder(id);
		OrderDto dto = ObjectMapperUtils.map(entity, OrderDto.class);

		EntityModel<OrderDto> model = EntityModel.of(dto);
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).findAll());
		model.add(linkToUsers.withRel("all-items"));
		return model;
	}

	@PatchMapping("/{id}/process")
	public EntityModel<OrderDto> processOrder(@PathVariable Long id) throws Exception {
		Order entity = service.processOrder(id);
		OrderDto dto = ObjectMapperUtils.map(entity, OrderDto.class);

		EntityModel<OrderDto> model = EntityModel.of(dto);
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).findAll());
		model.add(linkToUsers.withRel("all-items"));
		return model;
	}

	@PatchMapping("/{id}/cancel")
	public EntityModel<OrderDto> cancelOrder(@PathVariable Long id) throws Exception {
		Order entity = service.cancelOrder(id);
		OrderDto dto = ObjectMapperUtils.map(entity, OrderDto.class);

		EntityModel<OrderDto> model = EntityModel.of(dto);
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).findAll());
		model.add(linkToUsers.withRel("all-items"));
		return model;
	}
}
