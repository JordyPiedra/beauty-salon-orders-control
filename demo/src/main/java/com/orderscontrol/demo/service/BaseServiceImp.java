package com.orderscontrol.demo.service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.orderscontrol.demo.entity.BaseEntity;
import com.orderscontrol.demo.repository.BaseRepository;

import javassist.NotFoundException;

public class BaseServiceImp<T extends BaseEntity> implements BaseService<T> {

	private final BaseRepository<T> repository;

	public final Class<T> entityClass;

	public BaseServiceImp(BaseRepository<T> repository) {
		this.repository = repository;
		entityClass = getEntityClass();

	}

	@SuppressWarnings("unchecked")
	private Class<T> getEntityClass() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * Returns all instances of the entity.
	 *
	 * @return all entities mapped in its DTO
	 * @throws NotFoundException
	 */
	@Override
	public List<T> selectAll() throws NotFoundException {
		List<T> items = repository.findAll();
		return items;
	}

	/**
	 * Retrieves an entity by its id.
	 *
	 * @return an entity mapped in its DTO
	 */
	@Override
	public T selectOne(Long id) {
		T entity = repository.findById(id).get();
		return entity;
	}

	/**
	 * Saves the entity obtained from its DTO class and returns the object mapped to
	 * DTO
	 *
	 * @param The entity's DTO
	 * @return The saved entity mapped in its DTO
	 */
	@Override
	public T create(T entity) {
		return repository.save(entity);
	}

	/**
	 * Find the entity by Id and save it with the deleted state
	 *
	 * @param Entity ID
	 * @return The saved entity mapped in its DTO
	 */
	@Override
	public T deleteById(Long id) {
		T entity = repository.findById(id).get();
		entity.setStatus("REMOVED");
		return repository.save(entity);
	}

	/**
	 * Find the entity and compare the changes with the incoming DTO to proceed to
	 * save it.
	 *
	 * @param Entity ID an the entity's DTO
	 * @return The saved entity mapped in its DTO
	 */
	@Override
	public T update(Long id, T entityUpdated) {
		T entity = repository.findById(id).get();
		if (entity == null)
			return null;
		entityUpdated.setId(entity.getId());
		return repository.save(entityUpdated);
	}

	
}
