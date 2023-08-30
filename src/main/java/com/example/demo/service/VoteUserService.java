package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Candidates;
import com.example.demo.entity.User;
import com.example.demo.entity.VoteUsers;
import com.example.demo.repository.CandidatesRepository;
import com.example.demo.repository.VoteUsersRepository;

@Service
public class VoteUserService {

	
	@Autowired
	private VoteUsersRepository voteUsersRepository;
	
	@Autowired
	private CandidatesRepository candidatesRepository;

	public List<VoteUsers> getVoteUsersByUser(String string) {
        return voteUsersRepository.findByUser(string);
    }

}
