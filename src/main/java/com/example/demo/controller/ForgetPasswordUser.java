package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.Helper.Message;
import com.example.demo.config.OtpEmailTemplate;
import com.example.demo.exception.ApiException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EmailOtpService;
import com.example.demo.service.EmailOtpVerfiy;
import com.example.demo.service.TokenService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;

@Controller
public class ForgetPasswordUser {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailOtpService emailOtpService;
	
	@Autowired
	private EmailOtpVerfiy emailOtpVerfiy;
	
	@Autowired
	private OtpEmailTemplate emailTemplate;
	
	@Autowired
	private TokenService tokenService;

	@GetMapping("/forget-password")
	public String forgetpassword() {
		return "forget-password";
	}

	@PostMapping("/forget-password")
	public String forgetpassword1(@RequestParam("email") String email,HttpSession session) {
		
		if(userRepository.existsByEmail(email)==true) {
			System.out.println("forgetPassword++++++++++++++++++++++++"+email);
			// Generate OTP
	        String otp = emailOtpVerfiy.generateOtp(email);

			emailTemplate.sendOTP(email, otp);
			session.setAttribute("message", new Message("Email Send Successfully :)", "alert-success"));
			
			// Store the email in the session for verification
	        session.setAttribute("email", email);

	        // Set a flag to indicate that OTP has been sent
	        session.setAttribute("otpSent", true);

	        return "otp-verification"; // Redirect to OTP verification form
		}
		else {
			session.setAttribute("message", new Message("Email Not Found :(", "alert-danger"));
		}
		return "forget-password";
	}
	
	 
	
	@PostMapping("/otp-verification")
	public String otpVerification(@RequestParam("digit1") String digit1, 
            @RequestParam("digit2") String digit2,
            @RequestParam("digit3") String digit3, 
            @RequestParam("digit4") String digit4,
            @RequestParam("digit5") String digit5, 
            @RequestParam("digit6") String digit6, HttpSession session) {
		
		 String email = (String) session.getAttribute("email");
		    String otp = digit1 + digit2 + digit3 + digit4 + digit5 + digit6;
		System.out.println("**********************"+email);
		System.out.println("**********************"+otp);
		
	    // Check if the OTP matches  
	    if (email != null && emailOtpVerfiy.verifyOtp(email, otp)) {
	        // OTP is valid
	    	System.out.println("****done********");
	    	String token = tokenService.generateAndStoreToken(email);
	    	session.setAttribute("message", new Message("OTP is valid. You can reset your password.", "alert-success"));
	        // Redirect to the "forgot-password" page with the token
	    	return "redirect:/forgot-password-confirm?token=" + token;
	    } else {
	        // Invalid OTP
	    	session.setAttribute("errorMessage", "Invalid OTP. Please try again.");
	        return "redirect:/otp-verification";
	    }
	}
	
	@GetMapping("forgot-password-confirm")
    public String showForgotPasswordPage(@RequestParam("token") String token, Model model,HttpSession session) {
        String email = tokenService.getEmailForToken(token);
        if (email != null) {
            // Token is valid, allow the user to reset their password
        	System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+email);
//            model.addAttribute("email", email);
        	session.setAttribute("email", email); // Set the email in the session
            return "forgot-password-confirm"; // Return the HTML page you created
        } else {
            // Token is not valid, handle appropriately (e.g., show an error page)
            return "invalid-token";
        }
    }
	@PostMapping("/reset-password")
	public String resetPassword(@RequestBody Map<String, String> requestBody,HttpSession session) {
		String email = (String) session.getAttribute("email");
//		 String email = requestBody.get("email");
		 String newPassword = requestBody.get("newPassword");
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+email);
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+newPassword);
	    return "login"; // A confirmation page
	}
}
