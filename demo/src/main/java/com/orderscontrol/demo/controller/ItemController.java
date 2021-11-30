package com.orderscontrol.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderscontrol.demo.dto.ItemDto;
import com.orderscontrol.demo.service.ItemService;

@RestController
@RequestMapping(path = "/items")
public class ItemController extends BaseController<ItemService, ItemDto> {


	public ItemController(ItemService service) {
		super(service);
	}
 
}
