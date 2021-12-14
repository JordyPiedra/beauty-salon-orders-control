package com.orderscontrol.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "customer_order")

public class Order extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2317622217895129513L;

	/** Client name */
	@Size(max=150)
	private String clientName;
	
	@Size(max=150)
	private String clientPhone;
	
	@Size(max=150)
	private String clientEmail;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderDetail> orderDetails;
	
	/** creation date time */
	@CreationTimestamp
	private LocalDateTime creationDate;
	
	/** last update date time */
	@UpdateTimestamp
	private LocalDateTime lastUpdateDate;
 
	
	public void addDetail(OrderDetail detail) {
		if (orderDetails == null) {
			orderDetails = new ArrayList<>();
		}
		orderDetails.add(detail);
		detail.setOrder(this);
	}
	
	
}
