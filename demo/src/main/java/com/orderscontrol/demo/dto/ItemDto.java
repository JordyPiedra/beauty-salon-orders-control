package com.orderscontrol.demo.dto;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.orderscontrol.demo.entity.Category;
import com.orderscontrol.demo.entity.OrderDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemDto extends BaseDto{

	
	private String detail;
	
	private boolean compound;
	
	private double[] price;
	
	private Long categoryId;
	
 
	
}
