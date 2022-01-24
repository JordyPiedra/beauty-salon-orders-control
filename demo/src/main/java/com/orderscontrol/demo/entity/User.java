package com.orderscontrol.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7926444944978759406L;

	private String username;
	
	private String role;
	
	private String pwd;
	@Transient
	private String token;

}
