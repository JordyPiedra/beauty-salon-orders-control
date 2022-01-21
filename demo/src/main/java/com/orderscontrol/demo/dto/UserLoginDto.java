package com.orderscontrol.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginDto extends BaseDto {

	/**
	 * 
	 */

	private String username;
	private String token;
	private String role;
 
}
