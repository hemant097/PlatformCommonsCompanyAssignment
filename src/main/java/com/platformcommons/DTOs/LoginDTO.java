package com.platformcommons.DTOs;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTO {

	@NotBlank(message = "Password is a mandatory field")
	private String password; 
	
	@NotBlank(message = "Please Enter your email")
	@Email
	private String email;
}
