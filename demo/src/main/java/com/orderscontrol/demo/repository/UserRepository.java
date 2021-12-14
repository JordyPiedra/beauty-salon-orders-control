package com.orderscontrol.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.orderscontrol.demo.entity.User;

@Repository
public interface UserRepository extends BaseRepository<User> {

	@Query(value = "select * from user where username = :username and pwd = :password", nativeQuery = true)
	Optional<User> findLogin(@Param("username") String username,@Param("password") String password);
}
