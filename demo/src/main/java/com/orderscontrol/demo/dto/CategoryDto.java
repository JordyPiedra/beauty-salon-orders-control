package com.orderscontrol.demo.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orderscontrol.demo.entity.Category;
import com.orderscontrol.demo.entity.Item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor 
public class CategoryDto extends BaseDto {


	private String name;

	private String icon;

	private List<CategoryDto> childs;
	
	private List<ItemDto> items;
	
}
