package com.orderscontrol.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.orderscontrol.demo.entity.OrderDetail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto extends BaseDto {

	
	private String username;
	
	private String clientName;
	
	private String clientPhone;
	
	private String clientEmail;
	
	private double total;
	
	private List<OrderDetailDto> orderDetails;
	
	/** creation date time */
	private LocalDateTime creationDate;
	
	/** last update date time */
	private LocalDateTime lastUpdateDate;
}
