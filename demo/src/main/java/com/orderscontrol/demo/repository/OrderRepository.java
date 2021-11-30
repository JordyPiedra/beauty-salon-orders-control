package com.orderscontrol.demo.repository;

import org.springframework.stereotype.Repository;

import com.orderscontrol.demo.entity.Order;

@Repository
public interface OrderRepository extends BaseRepository<Order> {
}
