package com.example.demo.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Candidates;

public interface CandidatesRepository extends JpaRepository<Candidates, Integer>{

	@Query("SELECT c FROM Candidates c WHERE c.candidate_id = :candidateId")
    Candidates findByCandidateId(Integer candidateId);
	
//	 @Query("SELECT DISTINCT c.name FROM Candidates c JOIN c.VoteUsers v WHERE v.candidates = :candidateId")
//	 List<String> findDistinctCandidateNamesByCandidateId(@Param("candidateId") Integer candidateId);
	
}
