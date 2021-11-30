package com.orderscontrol.demo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8134571290710452885L;

	@Column(length = 150)
	private String name;

	@Column(length = 50)
	private String icon;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentCategoryId")
	private Category parent;

	@OneToMany(mappedBy = "parent")
	private List<Category> childs;
	
	@OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
	private List<Item> items;
	
}
