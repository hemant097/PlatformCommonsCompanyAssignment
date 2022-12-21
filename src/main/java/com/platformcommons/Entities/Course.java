package com.platformcommons.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.platformcommons.enums.CourseType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer courseid;
	
	@Column(unique = true)
	@NotBlank(message = "Name should not be empty")
	String courseName;
	
	@NotBlank(message = "Description should not be empty")
	String description;
	
	@NotNull(message = "Course type should not be elective or core")
	CourseType courseType;
	
	@NotBlank(message = "Duration should not be empty")
	String duration;
	
	@NotBlank(message = "Topics should not be empty")
	String topics;
	
	@JsonIgnore
	@ManyToMany(mappedBy="courses")
	List<Student> studentList = new ArrayList<>();
}
