package com.orderscontrol.demo.dto;

import java.util.List;

import com.orderscontrol.demo.entity.OrderDetail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto extends BaseDto {

	

	private String clientName;
	
	private String clientPhone;
	
	private String clientEmail;
	
	private List<OrderDetail> orderDetails;
	
}
