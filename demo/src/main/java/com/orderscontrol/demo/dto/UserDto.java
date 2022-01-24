package com.orderscontrol.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto extends BaseDto {

	/**
	 * 
	 */

	private String username;
 	private String role;
	private String pwd;

}
