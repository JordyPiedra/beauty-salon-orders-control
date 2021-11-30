package com.orderscontrol.demo.entity;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@Data
public abstract class BaseEntity  implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1422759480216815929L;
	/**
	 * Unique identifier.
	 */
	@Id
	@TableGenerator(name = "baseIdGenerator", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "idGenerator")
	@Access(AccessType.PROPERTY)
	private Long id;
	
	/** The Status. */
	@Column(length = 50)
	private String status;
	

}
