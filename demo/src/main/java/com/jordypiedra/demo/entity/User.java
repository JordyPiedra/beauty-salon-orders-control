package com.jordypiedra.demo.entity;

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
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=2)
	private String name;
	@Past
	private Date birthDate;
 
	
}
