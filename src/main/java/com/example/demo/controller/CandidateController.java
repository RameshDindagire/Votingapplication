package com.example.demo.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Helper.Message;
import com.example.demo.entity.Candidates;
import com.example.demo.entity.User;
import com.example.demo.entity.VoteUsers;
import com.example.demo.repository.CandidatesRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VoteUsersRepository;
import com.example.demo.service.CandidatesService;
import com.example.demo.service.VoteUserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CandidateController {

	@Autowired
	private CandidatesService candidatesService;

	@Autowired
	private VoteUserService voteUserService;

	@Autowired
	private VoteUsersRepository voteUsersRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CandidatesRepository candidatesRepository;

	@GetMapping("/user/select")
	public String getCandidate(Model model) {
		List<Candidates> allCandidates = this.candidatesService.getAllCandidates();
		System.out.println("==================>" + allCandidates);
		
//		String uniqueToken = UUID.randomUUID().toString();
//	    
//	    // Store the token in the session
//	    session.setAttribute("voteToken", uniqueToken);
	    
		model.addAttribute("candidateUsers", allCandidates);

		return "normal/candidates";
	}

	@PostMapping("/user/select")
	public String addCandidate(@RequestParam("selectedCandidateId") Integer selectedCandidateId, Model model, Principal principal, HttpSession session) {

	    String currentUser = principal.getName();
	    User user = userRepository.findByUsername(currentUser);

	    System.out.println(user.getId());

	    List<VoteUsers> voteUsersVal = this.voteUserService.getVoteUsersByUser(user.getId().toString());

	    if (voteUsersVal.isEmpty()) {
	        // Retrieve the stored token from the session
//	        String storedToken = (String) session.getAttribute("voteToken");
//
//	        if (storedToken == null || !storedToken.equals(submittedToken)) {
//	            // Tokens do not match, display an error message
//	            session.setAttribute("message", new Message("Token validation failed. Please try again.", "alert-danger"));
//	            return "normal/candidates";
//	        }

	        System.out.println("----------------->" + selectedCandidateId);
	        Candidates selectedCandidate = candidatesRepository.findByCandidateId(selectedCandidateId);

	        System.out.println("------------------->found Id" + selectedCandidate);
	        if (selectedCandidateId != null) {
	            VoteUsers voteUsers = new VoteUsers();
	            voteUsers.setUser(user.getId().toString());
	            voteUsers.setCandidates(selectedCandidate.getCandidate_id().toString());
	            this.voteUsersRepository.save(voteUsers);
	            session.setAttribute("message", new Message("You Have Voted Successfully", "alert-success"));
	        } else {
	            System.out.println("Selected candidate not found");
	        }
	    } else {
	        System.out.println("User Already Voted>>>>>>>>>" + voteUsersVal.get(0).getUser());
	        session.setAttribute("message", new Message("You Have Already Voted.", "alert-danger"));
	        return "normal/candidates";
	    }

	    // Remove the token from the session after processing
//	    session.removeAttribute("voteToken");

	    return "normal/candidates";
	}
	

}
