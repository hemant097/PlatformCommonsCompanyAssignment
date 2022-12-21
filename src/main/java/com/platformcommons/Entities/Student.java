package com.platformcommons.Entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer studentid;
	String name;
	LocalDate dateOfBirth;
	String gender;
	
	@Email
	String email;
	
	@NotNull
	private String parentName;
	
	@NotNull
	@Pattern(regexp = "[6789]{1}[0-9]{9}",message = " Please enter a"
			+ " valid 10 digit mobile number starting with 6/7/8/9 ")
	private String mobileNumber;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="studentcourse",
    joinColumns= @JoinColumn(name="studentid", referencedColumnName="studentid"),
    inverseJoinColumns=@JoinColumn(name="courseid", referencedColumnName="courseid")
    )
	List<Course> courses = new ArrayList<>();
	
	@OneToMany(mappedBy="stud",fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	List<StudentAddress> addresses= new ArrayList<>();
}
