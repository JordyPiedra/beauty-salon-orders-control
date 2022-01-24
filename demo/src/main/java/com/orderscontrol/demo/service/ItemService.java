package com.orderscontrol.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderscontrol.demo.entity.Item;
import com.orderscontrol.demo.repository.ItemRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ItemService extends BaseServiceImp<Item> {

	@Autowired
	private ItemRepository repository;

	public ItemService(ItemRepository repository) {
		super(repository);
	}
	
	@Override
	public Item create(Item entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

}
