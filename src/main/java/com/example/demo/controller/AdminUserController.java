package com.example.demo.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Helper.Message;
import com.example.demo.entity.AdminUsers;
import com.example.demo.entity.User;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.repository.AdminUserRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VoteUsersRepository;
import com.example.demo.service.AdminUserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/v1")
public class AdminUserController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AdminUserService adminUserService;
	
	@Autowired
	private AdminUserRepository adminUserRepositoryl;
	
	@Autowired
	private VoteUsersRepository voteUsersRepository; 
	
	@Autowired
	private UserRepository userRepository;
	
	

//	@GetMapping("/admin")
//	@PreAuthorize("hasRole('ADMIN')")
//	public String getAllCandidatesVotes(Model model) {
//		List<Object[]> candidateVoteCounts = this.voteUsersRepository.getCandidateVoteCounts();
//		
//		
//		System.out.println(candidateVoteCounts.toString());
//		StringBuilder voteCountString = new StringBuilder();
//	    for (Object[] voteCount : candidateVoteCounts) {
//	        String candidates = voteCount[0].toString();
//	        
//	        Long totalVotes = (Long) voteCount[1];
//
//	        voteCountString.append("Candidate ID: ").append(candidates)
//	                        .append(", Total Votes: ").append(totalVotes)
//	                        .append("\n");
//	    }
//
//	    System.out.println("Candidate Vote Counts:" + voteCountString.toString());
//
//		model.addAttribute("canidatevotes", candidateVoteCounts);
//		return "normal/admin_dashbord";
//	}
	
//	@GetMapping("/login")
//	public String showLoginPage() {
//		return "normal/adminLoginPage";
//	}
//	
//	@PostMapping("/login")
//	public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
//		
//		if (!adminUserRepositoryl.existsByUsername(username)) {
//			model.addAttribute("notExist", true);
//			return "normal/adminLoginPage";
//		}
//		
//		AdminUsers adminUser = this.adminUserRepositoryl.findByUsername(username);
//		System.out.println(adminUser.getUsername() + ">>>>>>>>" + adminUser.getPassword()+">>>>>>"+adminUser.getRole());
//		
//		if (!passwordEncoder.matches(password, adminUser.getPassword())) {
//			model.addAttribute("incorrect", true);
//			return "normal/adminPage";
//		}
//		
//		if (adminUser.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
//			List<Object[]> candidateVoteCounts = this.voteUsersRepository.getCandidateVoteCounts();
//	    	
//			StringBuilder voteCountString = new StringBuilder();
//		    for (Object[] voteCount : candidateVoteCounts) {
//		        String candidates = voteCount[0].toString();
//		        Long totalVotes = (Long) voteCount[1];
//		        voteCountString.append("Candidate ID: ").append(candidates)
//		                        .append(", Total Votes: ").append(totalVotes)
//		                        .append("\n");
//		    }
//		    System.out.println("Candidate Vote Counts:" + voteCountString.toString());
//
//			model.addAttribute("canidatevotes", candidateVoteCounts);
//	    	return "normal/admin_dashbord";  // Redirect to admin dashboard
//			
//		} else {
//			// For regular users, you would have a different dashboard view
//			return "normal/adminLoginPage";   // Redirect to user dashboard
//		}
//	}
//	
	
	@PostMapping("/create-admin")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createAdmin(@RequestParam("adminUsername") String adminUsername,@RequestParam("adminRole") String adminRole,  @RequestParam("mobileNumber") String mobileNumber, @RequestParam("Password") String Password, Model model, HttpSession session) {

//		User adminUser = this.adminUserService.createAdminUser(adminUsername, adminRole, mobileNumber, Password);
		try {
			if (adminUsername.contains("@mobitrail.com")) {
				
//				session.setAttribute("message", new Message("Successfully Created Admin :)", "alert-success"));
				User adminUser = this.adminUserService.createAdminUser(adminUsername, adminRole, mobileNumber, Password);
//				this.userRepository.save(adminUser);
				return ResponseEntity.ok("Successfully Created Admin :)");
			} else {
//				session.setAttribute("message", new Message("Add Proper UserName :)", "alert-denger"));
				 return ResponseEntity.badRequest().body("Add Proper UserName :)");
			}
		} catch (UserAlreadyExistsException ex) {
			session.setAttribute("message", new Message("Admin Alredy Register :)", "alert-denger"));
			return ResponseEntity.badRequest().body("Admin Already Registered :(");
			
		}

	}
	
	
	@GetMapping("/login")
	public String loginUser(Model model,Principal principal ) {
		String userName = principal.getName();
		System.out.println("UserName:::::::::::::::::: "+userName);
		
		User user = this.userRepository.getUserByUserName(userName);
		System.out.println("USER>>>>>>>"+user.getUsername());
		
		System.out.println("USER>>>>>>>"+user.getRole());
		
		model.addAttribute("user",user);
	    return "normal/admin_dashbord";  // Redirect to admin dashboard
		
	}
	
	@GetMapping("/getAllCandidates")
	@PreAuthorize("hasRole('ADMIN')")
	public String getAllCandidates(Model model) {
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
    	return "normal/candidatesList";
	}
	
	
	@GetMapping("/db-create")
	@PreAuthorize("hasRole('ADMIN')")
	public String createDb(Model model) {
		
		return "normal/db_create_form";
	}
	

	
	 /* for testing  */
//	@PostMapping("/create-admin")
//	@PreAuthorize("hasRole('ADMIN')")
//	public ResponseEntity<?> createAdmin(@RequestParam("adminUsername") String adminUsername,@RequestParam("adminRole") String adminRole,  @RequestParam("mobileNumber") String mobileNumber, @RequestParam("Password") String Password, Model model, HttpSession session) {
//		
//		System.out.println(adminUsername);
//		System.out.println(adminRole);
//		System.out.println(Password);
//		System.out.println(mobileNumber);
//		
//		try {
////			if(adminUsername.contains("@mobitrail.com")) 
////			{
////				session.setAttribute("message", new Message("Successfully Created Admin :)", "alert-success"));
////				AdminUsers createAdminUser = adminUserService.createAdminUser(adminUsername, AdminRole, mobileNumber,Password);
////			}
////			else {
////				session.setAttribute("message", new Message("Add Proper UserName :)", "alert-denger"));
////			}
//		} catch (UserAlreadyExistsException ex) {
//			session.setAttribute("message", new Message("Admin Alredy Register :)", "alert-denger"));
//		}
//		
//		return new ResponseEntity<>(HttpStatus.OK);
//		
//	}
//	 
	
}
