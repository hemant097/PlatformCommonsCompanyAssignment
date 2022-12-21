package com.platformcommons.DTOs;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminDTO {

	@NotBlank(message = "Name should not be empty")
	private String name;
	
	@NotBlank(message = "Please Enter your Mobile Number")
	@Pattern(regexp = "[6789]{1}[0-9]{9}",message = " Please enter a"
			+ " valid 10 digit mobile number starting with 6/7/8/9 ")
	private String mobile;
	
	@NotBlank(message = "Password is a mandatory field")
	private String password; 
	
	@NotBlank(message = "Please Enter your email")
	@Email
	private String email;
}
