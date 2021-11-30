package com.orderscontrol.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.orderscontrol.demo.entity.Category;
import com.orderscontrol.demo.entity.Item;

@Repository
public interface CategoryRepository extends BaseRepository<Category> {
	
	@Query(value = "select * from category where parent_category_id is null", nativeQuery = true)
	Optional<Category> findAllParents();
}
