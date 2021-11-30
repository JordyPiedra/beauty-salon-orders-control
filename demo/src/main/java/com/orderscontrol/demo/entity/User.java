package com.orderscontrol.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class User extends BaseEntity{

 
	/**
	 * 
	 */
	private static final long serialVersionUID = 7926444944978759406L;
	@Size(min=50)
	private String name;
	@Past
	private Date birthDate;
 
	
}
