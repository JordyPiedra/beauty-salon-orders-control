package com.orderscontrol.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDetailDto extends BaseDto {

	

	private Long itemId;
	
	private String description;
	
	private String key;
	
	private double price;

	private String[] participants;
	
	private boolean compound;

	
}
