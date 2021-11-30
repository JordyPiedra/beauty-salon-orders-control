package com.orderscontrol.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDetailDto extends BaseDto {

	

	private Long itemId;

	private double price;

	private Long[] participants;

	
}
