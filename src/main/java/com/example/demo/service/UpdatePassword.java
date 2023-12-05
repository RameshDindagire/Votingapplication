package com.example.demo.service;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.OtpEmailTemplate;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UpdatePassword {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	private final Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OtpEmailTemplate otpEmailTemplate;
	
	@Autowired
	private UserRepository repository;
	
	@Transactional
    public String resetPassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email);
        System.out.println("!@#$%^&*("+email+")");
        if (user != null) {
            // Update the password with the encoded value
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        	
        	
            // Optionally, you can clear the reset token or perform other actions
        	String findUsernameByEmail = this.repository.findUsernameByEmail(email);
        	System.out.println("@#$%^&*("+findUsernameByEmail+")@&#^%*");
        	try {
				this.otpEmailTemplate.updatePasswordConfimEmail(findUsernameByEmail, email);;
			} catch (UnsupportedEncodingException | MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return "Password reset successfully. You can now log in with your new password.";
        } else {
            return "Password reset failed. User with the provided email not found.";
        }
    }
}

