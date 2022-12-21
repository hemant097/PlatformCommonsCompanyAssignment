
package com.platformcommons.DTOs;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.platformcommons.Entities.StudentAddress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO {
	
	
	
	@NotNull
	private LocalDate dateOfBirth;
	
	@NotNull
	private String name;
	@NotNull
	private String parentName;
	
	@Email
	@NotNull
	private String email;
	
	@NotNull
	@Pattern(regexp = "[6789]{1}[0-9]{9}",message = " Please enter a"
			+ " valid 10 digit mobile number starting with 6/7/8/9 ")
	private String mobileNumber;
	
	@NotNull
	private StudentAddress address;

}
