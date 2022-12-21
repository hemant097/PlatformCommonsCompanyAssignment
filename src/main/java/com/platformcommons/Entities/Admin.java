package com.platformcommons.Entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminid;
	
	@NotBlank(message = "Name should not be empty")
	private String name;
	
	@NotBlank(message = "Please Enter your Mobile Number")
	@Column(unique=true)
	@Pattern(regexp = "[6789]{1}[0-9]{9}",message = " Please enter a"
			+ " valid 10 digit mobile number starting with 6/7/8/9 ")
	private String mobile;
	
	@NotBlank(message = "Password is a mandatory field")
	private String password; 
	
	@NotBlank(message = "Please Enter your email")
	@Column(unique=true)
	@Email
	private String email;
	
}
