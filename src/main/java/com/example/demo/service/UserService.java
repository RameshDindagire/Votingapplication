package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.UserRequest;
import com.example.demo.entity.Candidates;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	private final Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;

	public User addUser(User userRequest) {
		
		if (userRepository.existsByUsername(userRequest.getUsername())) {
			log.info("user ["+userRequest.getUsername()+"] alredy exist");
			throw new UserAlreadyExistsException("alerday Exist");
            
        }
		
		User user = new User();
		if(userRequest.getUsername().contains("@mobitrail.com")) {
			 user = User.builder()
					.username(userRequest.getUsername())
					.password(passwordEncoder.encode(userRequest.getPassword()))
					.pannumber(userRequest.getPannumber())
					.mobilNumber(userRequest.getMobilNumber())
					.role("ROLE_ADMIN")
					.build();
			this.userRepository.save(user);
		}
		else {
			 user = User.builder()
					.username(userRequest.getUsername())
					.password(passwordEncoder.encode(userRequest.getPassword()))
					.pannumber(userRequest.getPannumber())
					.mobilNumber(userRequest.getMobilNumber())
					.role("ROLE_USER")
					.build();
			this.userRepository.save(user);
			log.info("User {} Saved "+user.getUsername());
		}
		return user;
	}

	public User getUserByUsernameAndPassword(String username, String password,HttpSession httpSession) {
		
		User findByUsernameAndPassword = userRepository.findByUsernameAndPassword(username, password);
		System.out.println("======>"+findByUsernameAndPassword.getUsername());
		if(findByUsernameAndPassword.getUsername()!=null) {
			httpSession.setAttribute("username", username);
			return findByUsernameAndPassword;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found");
		}
		
		
	}
//	public boolean isUserPresent(String email) {
//	       return userRepository.findByEmail(email) != null;
//	   }
//
//	public User findByEmail(String email) {
//	       return userRepository.findByEmail(email);
//	   }
	
	
}
