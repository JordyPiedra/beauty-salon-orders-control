package com.orderscontrol.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderscontrol.demo.dto.CategoryDto;
import com.orderscontrol.demo.dto.CategoryWithoutDetailDto;
import com.orderscontrol.demo.service.CategoryService;
import com.orderscontrol.demo.utils.ObjectMapperUtils;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/categories")
public class CategoryController extends BaseController<CategoryService, CategoryDto> {

	@Autowired
	private CategoryService serviceCategory;

	public CategoryController(CategoryService serviceCategory) {
		super(serviceCategory);
	}

	@Override
	public List<CategoryDto> findAll() throws Exception {
		return ObjectMapperUtils.mapAll(serviceCategory.selectAll(), CategoryDto.class);

	}
	
	@GetMapping("/without-detail") 
	public List<CategoryWithoutDetailDto> findAllWithoutDetail() throws Exception {
		return ObjectMapperUtils.mapAll(serviceCategory.selectAll(), CategoryWithoutDetailDto.class);

	}

}
