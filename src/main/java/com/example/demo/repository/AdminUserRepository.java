package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.AdminUsers;
import com.example.demo.entity.User;

public interface AdminUserRepository extends JpaRepository<AdminUsers, Integer>{

	boolean existsByUsername(String username);
	
	@Query("SELECT au FROM AdminUsers au WHERE au.username = :username")
    AdminUsers findByUsername(String username);
}
