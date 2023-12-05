package com.example.demo.dto;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {

	 private String username;
	 
	 private String password;
	 
	 @Size(max = 10,message = "Plese Check your Pan Number !")
	 private String pannumber;
	 
	 @Size(min = 10,max = 12,message = "Check your Number !")
	 private String mobilNumber;
}
