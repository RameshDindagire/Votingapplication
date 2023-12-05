package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class VotingApplication {

	
//	@SpringBootApplication
//	public class VotingApplication extends SpringBootServletInitializer{
//
//		protected SpringApplicationBuilder
//	    configure(SpringApplicationBuilder application)
//	    {
//	        return application.sources(
//	        		VotingApplication.class);
//	    } 
	
	public static void main(String[] args) {
		SpringApplication.run(VotingApplication.class, args);
	}

}
