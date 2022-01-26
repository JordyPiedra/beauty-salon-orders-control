package com.orderscontrol.demo.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DashboardOrderDto extends BaseDto {

	private String title;

	private String subtitle;

	private String clientName;

	private String clientPhone;

	private String clientEmail;

	private List<ListDescription> list;

	private int participants;

	private double price;

	private List<OrderDetailDto> detail;

	private int services;

	@Getter
	@Setter
	@NoArgsConstructor
	public static class ListDescription {
		private String description;
		private String counter;
		private String icon;
	}
}
