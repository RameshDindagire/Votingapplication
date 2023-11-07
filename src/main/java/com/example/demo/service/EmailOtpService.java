package com.example.demo.service;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailOtpService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	

	@Async
	public void sendMail(String Head,String email,String mailContentOtp) throws MessagingException, UnsupportedEncodingException {
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper Messagehelper = new MimeMessageHelper(mimeMessage,true);
		Messagehelper.setFrom("iamrameshdindagire@gmail.com",Head);
		Messagehelper.setTo(email);
		
		
//		System.out.println("******************"+otp);
		Messagehelper.setSubject(Head);
		Messagehelper.setText(mailContentOtp,true);
//		javaMailSender.send(mimeMessage);
	}

	@Async
	public void afterRegisterEmail(String Head, String email, String mailContentOtp)
			throws MessagingException, UnsupportedEncodingException {
		System.out.println(Head+"$$$$$$$$$$$$$$"+email+"$$$$$$$$$$$"+mailContentOtp);
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper Messagehelper = new MimeMessageHelper(mimeMessage, true);
		Messagehelper.setFrom("iamrameshdindagire@gmail.com", Head);
		Messagehelper.setTo(email);

//		System.out.println("******************"+otp);
		Messagehelper.setSubject(Head);
		Messagehelper.setText(mailContentOtp, true);
		javaMailSender.send(mimeMessage);
	}
}