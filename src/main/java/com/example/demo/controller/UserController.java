package com.example.demo.controller;

import java.security.Principal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/index") 
	public String dashBord(Model model,Principal principal ) {
		
		String userName = principal.getName();
		System.out.println("UserName "+userName);
		
		User user = this.userRepository.getUserByUserName(userName);
		System.out.println("USER>>>>>>>"+user.getUsername());
		
		System.out.println("USER>>>>>>>"+user.getRole());
		
		model.addAttribute("user",user);
			
		//get the user using username(Email)
		return "normal/user_dashbord";
		
	}
}
