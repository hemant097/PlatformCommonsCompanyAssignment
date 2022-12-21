package com.platformcommons.DTOs;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.platformcommons.enums.CourseType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDTO {
	@NotBlank(message = "Name should not be empty")
	String courseName;
	@NotBlank(message = "Description should not be empty")
	String description;
	@NotNull(message = "Course type should only be elective or core")
	CourseType courseType;
	@NotBlank(message = "Duration should not be empty")
	String duration;
	@NotBlank(message = "Topics should not be empty")
	String topics;
}
