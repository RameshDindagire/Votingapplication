package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AdminUsers;
import com.example.demo.entity.User;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.repository.AdminUserRepository;
import com.example.demo.repository.UserRepository;

@Service
public class AdminUserService {

	@Autowired
	private AdminUserRepository adminUserRepositoryl;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public User createAdminUser(String adminUsername, String adminRole, String mobileNumber, String Password) {
		User adminUser = new User();
		if (userRepository.existsByUsername(adminUsername)) {
			throw new UserAlreadyExistsException("alerday Exist");

		}

		else {
			if (adminRole.contains("ROLE_ADMIN")) {
				System.out.println(adminUsername);
				System.out.println(adminRole);
				System.out.println(mobileNumber);
				System.out.println(Password);
				adminUser = User.builder().username(adminUsername).password(passwordEncoder.encode(Password))
						.pannumber("Null").mobilNumber(mobileNumber).role("ROLE_ADMIN").build();
//			this.adminUserRepositoryl.save(adminUser);
				this.userRepository.save(adminUser);

			} else if (adminRole.contains("ROLE_MANAGER")) {
				adminUser = User.builder().username(adminUsername).password(passwordEncoder.encode(Password))
						.pannumber("Null").mobilNumber(mobileNumber).role("ROLE_MANAGER").build();
//			this.adminUserRepositoryl.save(adminUser);
				this.userRepository.save(adminUser);
			}
		}
		return adminUser;
	}

}
