package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.VoteUsers;

@EnableJpaRepositories
public interface VoteUsersRepository extends JpaRepository<VoteUsers, Integer>{

	@Query("SELECT vu FROM VoteUsers vu WHERE vu.user = :user")
    List<VoteUsers> findByUser(@Param("user") String string);
	
	@Query("SELECT v.candidates, COUNT(v.user) AS totalVotes FROM VoteUsers v GROUP BY v.candidates")
    List<Object[]> getCandidateVoteCounts();
}
