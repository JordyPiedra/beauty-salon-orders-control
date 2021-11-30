package com.orderscontrol.demo.service;

import java.util.List;

public interface BaseService<U> {

	/**
	 * Query all records
	 * 
	 * @return
	 * @throws Exception 
	 */
	List<U> selectAll() throws Exception;
	
	/**
	 * Query one record by Id
	 * 
	 * @return
	 * @throws Exception 
	 */
	U selectOne(Long id) throws Exception;

	/**
	 * Add all fields
	 * 
	 * @param record
	 * @return
	 */
	U create(U record);

	/**
	 * Update
	 * 
	 * @param record
	 * @return
	 */
	U update(Long id, U record);

	/**
	 * Delete according to id
	 * 
	 * @param id
	 * @return
	 */
	U deleteById(Long id);

	 
}
