package com.orderscontrol.demo.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommissionReportDto {

	private String username;

	private List<ListDescription> items;
	
	private double total;

	@Getter
	@Setter
	@NoArgsConstructor
	public static class ListDescription {

		private Long orderId;

		private String orderDate;
		
		private String orderClient;

		private String orderStatus;

		private double orderTotal;

		private Long itemId;

		private String description;
		
		private double itemsTotal;
		
		private double commission;
	}
}
