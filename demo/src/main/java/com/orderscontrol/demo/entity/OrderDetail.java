package com.orderscontrol.demo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class OrderDetail extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1010155486129391824L;

	private Item service;

	private double price;

	private String[] stylists;

	private String createdBy;
	
	/** Document */
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;

}
