package com.orderscontrol.demo.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.orderscontrol.demo.dto.ItemDto;
import com.orderscontrol.demo.entity.Category;
import com.orderscontrol.demo.entity.Item;
import com.orderscontrol.demo.service.CategoryService;
import com.orderscontrol.demo.service.ItemService;
import com.orderscontrol.demo.utils.ObjectMapperUtils;

@RestController
@RequestMapping(path = "/items")
public class ItemController extends BaseController<ItemService, ItemDto> {

	@Autowired
	private ItemService service;

	@Autowired
	private CategoryService categoryService;

	public ItemController(ItemService service) {
		super(service);
	}

	
	@PostMapping()
	@Override
	public ResponseEntity<Object> create(@Valid @RequestBody ItemDto entityDto) {
		Item entitySaved = (Item) ObjectMapperUtils.map(entityDto, Item.class);
		Category category = categoryService.selectOne(entityDto.getCategoryId());
		entitySaved.setCategory(category);
		service.create(entitySaved);
		ItemDto dto = ObjectMapperUtils.map(entitySaved, ItemDto.class);
		dto.setCategoryId(entityDto.getCategoryId());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

}
