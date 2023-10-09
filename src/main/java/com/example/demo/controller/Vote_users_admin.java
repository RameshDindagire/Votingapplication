package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Candidates;
import com.example.demo.entity.User;
import com.example.demo.repository.CandidatesRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VoteUsersRepository;
import com.example.demo.service.UserService;

@Controller
public class Vote_users_admin {
	
	@Autowired
	private VoteUsersRepository voteUsersRepository; 
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CandidatesRepository candidatesRepository;
	
	@GetMapping("/admin")
	public String getAllCandidatesVotes(Model model) {
		List<Object[]> candidateVoteCounts = this.voteUsersRepository.getCandidateVoteCounts();
		
		
		System.out.println(candidateVoteCounts.toString());
		StringBuilder voteCountString = new StringBuilder();
	    for (Object[] voteCount : candidateVoteCounts) {
	        String candidates = voteCount[0].toString();
	        
	        Long totalVotes = (Long) voteCount[1];

	        voteCountString.append("Candidate ID: ").append(candidates)
	                        .append(", Total Votes: ").append(totalVotes)
	                        .append("\n");
	    }

	    System.out.println("Candidate Vote Counts:" + voteCountString.toString());

		model.addAttribute("canidatevotes", candidateVoteCounts);
		return "normal/admin_dashbord";
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "normal/adminPage";
	}

	@PostMapping("/login")
	public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
		
		if (!userRepository.existsByUsername(username)) {
	        model.addAttribute("notExist", true);
	        return "normal/adminPage";
	    }

	    User user = this.userRepository.getUserByUserName(username);
	    System.out.println(user.getUsername() + ">>>>>>>>" + user.getPassword()+">>>>>>"+user.getRole());

	    if (!passwordEncoder.matches(password, user.getPassword())) {
	        model.addAttribute("incorrect", true);
	        return "normal/adminPage";
	    }
	    
	    if (user.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
	    	List<Object[]> candidateVoteCounts = this.voteUsersRepository.getCandidateVoteCounts();
	    	
			StringBuilder voteCountString = new StringBuilder();
		    for (Object[] voteCount : candidateVoteCounts) {
		        String candidates = voteCount[0].toString();
		        Long totalVotes = (Long) voteCount[1];
		        voteCountString.append("Candidate ID: ").append(candidates)
		                        .append(", Total Votes: ").append(totalVotes)
		                        .append("\n");
		    }
		    System.out.println("Candidate Vote Counts:" + voteCountString.toString());

			model.addAttribute("canidatevotes", candidateVoteCounts);
	    	return "normal/admin_dashbord";  // Redirect to admin dashboard
	        
	    } else {
	        // For regular users, you would have a different dashboard view
	        return "normal/adminPage";   // Redirect to user dashboard
	    }
	}
}
