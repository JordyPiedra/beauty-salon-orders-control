package com.orderscontrol.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderscontrol.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
