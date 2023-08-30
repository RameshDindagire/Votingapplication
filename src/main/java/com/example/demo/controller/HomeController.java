package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Helper.Message;
import com.example.demo.entity.User;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import com.example.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	Object attribute = null;

	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home-Smart maneger");
		return "base";
	}

	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "about-Smart maneger");
		return "about";
	}

	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title", "Register-Smart maneger");
		model.addAttribute("User", new User());
		return "signup";
	}

	@PostMapping("/do_register")
	public String addUser(@Valid @ModelAttribute("User") User user, BindingResult result1, Model model,
			HttpSession session)  {
		try {
			System.out.println("================>" + user);
			User addUser = this.userService.addUser(user);
			System.out.println("User" + user);

			model.addAttribute("User", new User());

			session.setAttribute("message", new Message("Successfully Register :)", "alert-success"));
		  } catch (UserAlreadyExistsException ex) {
	            // Handle UserAlreadyExistsException
	            model.addAttribute("User", user);
	            session.setAttribute("message", new Message("User with this email already exists.", "alert-danger"));
			} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("User", user);
			session.setAttribute("message", new Message("Smothing went Wrong :(" + e.getMessage(), "alert-denger"));
		} 
		return "signup";
	}
	
	@GetMapping("/signin")
	public String customeLogin(Model model) {
		model.addAttribute("title","Login Page");

		return"login";
	}
}