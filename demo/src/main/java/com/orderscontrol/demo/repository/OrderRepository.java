package com.orderscontrol.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.orderscontrol.demo.entity.Order;

@Repository
public interface OrderRepository extends BaseRepository<Order>, JpaSpecificationExecutor<Order> {
	
    public List<Order> findAllByOrderByIdDesc();

}
