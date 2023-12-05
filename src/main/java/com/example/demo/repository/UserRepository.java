package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.User;


public interface UserRepository extends JpaRepository<User, Integer>{

	boolean existsByUsername(String username);
	
	User findByUsernameAndPassword(String username, String password);

	@Query("select u from User u where u.username = :username")
	public User getUserByUserName(@Param("username")String username);
	
	User findByUsername(String username);
	
	 boolean existsByEmail(String email);
	 
	
	 @Modifying
	 @Query("UPDATE User u SET u.password = :newPassword WHERE u.email = :email")
	 void updatePasswordByEmail(@Param("email") String email, @Param("newPassword") String newPassword);

	User findByEmail(String email);
	
	@Query("SELECT u.username FROM User u WHERE u.email = :email")
    String findUsernameByEmail(String email);
}
