package com.orderscontrol.demo.controller;

import java.net.URI;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.orderscontrol.demo.dto.ItemDto;
import com.orderscontrol.demo.entity.BaseEntity;
import com.orderscontrol.demo.entity.Category;
import com.orderscontrol.demo.entity.Item;
import com.orderscontrol.demo.service.CategoryService;
import com.orderscontrol.demo.service.ItemService;
import com.orderscontrol.demo.utils.ObjectMapperUtils;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/items")
public class ItemController extends BaseController<ItemService, ItemDto> {

	@Autowired
	private ItemService service;

	@Autowired
	private CategoryService categoryService;

	public ItemController(ItemService service) {
		super(service);
	}

	@Override
	public List<ItemDto> findAll() throws Exception {

		List<Item> items = service.selectAll();

		List<ItemDto> itemsDto = ObjectMapperUtils.mapAll(items, ItemDto.class);
		for (int i = 0; i < itemsDto.size(); i++) {
			itemsDto.get(i).setCategoryId(items.get(i).getCategory().getId());
			itemsDto.get(i).setCategoryName(items.get(i).getCategory().getName());
		}
		return itemsDto;
	}
	@PostMapping()
	@Override
	public ResponseEntity<Object> create(@Valid @RequestBody ItemDto entityDto) {
		Item entitySaved = (Item) ObjectMapperUtils.map(entityDto, Item.class);
		Category category = categoryService.selectOne(entityDto.getCategoryId());
		entitySaved.setCategory(category);
		service.create(entitySaved);
		ItemDto dto = ObjectMapperUtils.map(entitySaved, ItemDto.class);
		dto.setCategoryId(entitySaved.getCategory().getId());
		dto.setCategoryName(entitySaved.getCategory().getName());
 		return ResponseEntity.ok(dto);

	}
	
	
	@Override
	public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid ItemDto entityDto) {
		// TODO Auto-generated method stub

		Item entitySaved = (Item) ObjectMapperUtils.map(entityDto, Item.class);
		Category category = categoryService.selectOne(entityDto.getCategoryId());
		entitySaved.setCategory(category);
		service.update(id,entitySaved);

		ItemDto dto = ObjectMapperUtils.map(entitySaved, ItemDto.class);
		dto.setCategoryId(entitySaved.getCategory().getId());
		dto.setCategoryName(entitySaved.getCategory().getName());
		return ResponseEntity.ok(dto);

	}

}
