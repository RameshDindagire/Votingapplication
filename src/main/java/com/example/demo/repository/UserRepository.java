package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.User;


public interface UserRepository extends JpaRepository<User, Long>{

	boolean existsByUsername(String username);
	
	User findByUsernameAndPassword(String username, String password);

	@Query("select u from User u where u.username = :username")
	public User getUserByUserName(@Param("username")String username);
	
	User findByUsername(String username);
	

//	User findByEmail(String email);
}
