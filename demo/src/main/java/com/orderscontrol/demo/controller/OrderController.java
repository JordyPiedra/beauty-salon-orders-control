package com.orderscontrol.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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

import com.orderscontrol.demo.dto.DashboardOrderDto;
import com.orderscontrol.demo.dto.DashboardOrderDto.ListDescription;
import com.orderscontrol.demo.dto.OrderDetailDto;
import com.orderscontrol.demo.dto.OrderDto;
import com.orderscontrol.demo.entity.Order;
import com.orderscontrol.demo.entity.OrderDetail;
import com.orderscontrol.demo.exceptions.ItemNotFoundException;
import com.orderscontrol.demo.repository.OrderRepository;
import com.orderscontrol.demo.service.OrderService;
import com.orderscontrol.demo.utils.ObjectMapperUtils;
import com.sipios.springsearch.anotation.SearchSpec;

@RestController
@RequestMapping(path = "/orders")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController extends BaseController<OrderService, OrderDto> {

	@Autowired
	private OrderService service;

	public OrderController(OrderService orderService) {
		super(orderService);
	}

	@GetMapping("/search")
	public ResponseEntity<?> search(@SearchSpec Specification<Order> specs) {

		List<Order> orderList = ((OrderRepository) this.service.getBaseRepository())
				.findAll(Specification.where(specs));
		List<OrderDto> orderDtoList = ObjectMapperUtils.mapAll(orderList, OrderDto.class);
		return ResponseEntity.ok(orderDtoList);

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

			List<ListDescription> list = new ArrayList<ListDescription>();

			ListDescription serviceList = new ListDescription();
			serviceList.setDescription("Servicios contratados");
			serviceList.setIcon("content_cut");
			serviceList.setCounter(orderDto.getOrderDetails().size() + "");

			ListDescription priceList = new ListDescription();
			priceList.setDescription("Total");
			priceList.setIcon("credit_card");
			priceList.setCounter("$" + orderDto.getTotal());

			list.add(serviceList);
			list.add(priceList);
			dto.setList(list);

			dto.setDetail(orderDto.getOrderDetails());

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
		return ResponseEntity.ok(dto);
	}

	@PutMapping("/{id}")
	@Override
	public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody OrderDto orderDto) {
		Order entity = service.update(id, orderDto);
		OrderDto dto = ObjectMapperUtils.map(entity, OrderDto.class);
		return ResponseEntity.ok(dto);
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
