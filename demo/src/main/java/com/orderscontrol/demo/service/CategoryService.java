package com.orderscontrol.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderscontrol.demo.entity.Category;
import com.orderscontrol.demo.repository.CategoryRepository;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jmpiedra
 */
@Slf4j
@Service
public class CategoryService extends BaseServiceImp<Category> {

	@Autowired
	private CategoryRepository repository;

	public CategoryService(CategoryRepository repository) {
		super(repository);
	}

	@Override
	public List<Category> selectAll() throws NotFoundException {
		Optional<List<Category>> optional = repository.findAllParents();
		return (List<Category>) optional.get();
	}
}
