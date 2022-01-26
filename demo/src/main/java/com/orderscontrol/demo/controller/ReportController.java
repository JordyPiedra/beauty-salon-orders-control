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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orderscontrol.demo.dto.DashboardOrderDto;
import com.orderscontrol.demo.dto.DashboardOrderDto.ListDescription;
import com.orderscontrol.demo.dto.OrderDetailDto;
import com.orderscontrol.demo.dto.OrderDto;
import com.orderscontrol.demo.entity.Order;
import com.orderscontrol.demo.entity.OrderDetail;
import com.orderscontrol.demo.exceptions.ItemNotFoundException;
import com.orderscontrol.demo.service.ReportService;
import com.orderscontrol.demo.utils.ObjectMapperUtils;
import com.sipios.springsearch.anotation.SearchSpec;

@RestController
@RequestMapping(path = "/reports")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReportController  {

	@Autowired
	private ReportService service;

 
	@GetMapping("/commission-report")
	public ResponseEntity<?> generateCommissionReport(@SearchSpec Specification<Order> specs) {

		return ResponseEntity.ok(service.commissionList(specs));

	}

 
}
