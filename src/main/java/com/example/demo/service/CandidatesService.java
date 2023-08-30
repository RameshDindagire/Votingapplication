package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Candidates;
import com.example.demo.repository.CandidatesRepository;

@Service
public class CandidatesService {

	@Autowired
	private CandidatesRepository candidatesRepository;
	
	
	public List<Candidates> getAllCandidates() {
		List<Candidates> findAll = this.candidatesRepository.findAll();
		return findAll;
	}

//	public String getCandidateById(Integer selectedCandidateId) {
//		Long referenceById = this.candidatesRepository.findByIdCandidates(selectedCandidateId);
//		return referenceById.toString();
//	}
}
