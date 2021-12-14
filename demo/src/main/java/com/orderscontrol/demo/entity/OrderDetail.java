package com.orderscontrol.demo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderDetail extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1010155486129391824L;

	@OneToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;

	private double price;

	private String[] participants;

	private String createdBy;

	/** Document */
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;

}
