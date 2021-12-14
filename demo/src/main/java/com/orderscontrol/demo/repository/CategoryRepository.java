package com.orderscontrol.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.orderscontrol.demo.entity.Category;

@Repository
public interface CategoryRepository extends BaseRepository<Category> {

	@Query(value = "select * from category where parent_category_id is null", nativeQuery = true)
	Optional<List<Category>> findAllParents();
}
