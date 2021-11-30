package com.orderscontrol.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.orderscontrol.demo.entity.Item;

@Repository
public interface ItemRepository extends BaseRepository<Item> {
	
	@Query(value = "select * from category where parent_category_id is null", nativeQuery = true)
	Optional<Item> findAllParents();
	
}
