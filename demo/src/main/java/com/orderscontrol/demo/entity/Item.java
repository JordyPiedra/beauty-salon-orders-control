package com.orderscontrol.demo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Item extends BaseEntity{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5184298195195701711L;

	@Size(max=100)
	private String code;
	
	@Size(max=100)
	private String detail;
	
	private boolean compound;
	
	private double[] price;
	
	private double commissionPrice1;
	
	private double commissionPrice2;


	
	/** Document */
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;
	
 
	
}
