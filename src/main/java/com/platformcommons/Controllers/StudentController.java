package com.platformcommons.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platformcommons.Entities.Course;
import com.platformcommons.Exceptions.AdminException;
import com.platformcommons.Exceptions.CourseException;
import com.platformcommons.Exceptions.StudentException;
import com.platformcommons.Service.AdminLoginLogoutService;
import com.platformcommons.Service.CourseService;
import com.platformcommons.Service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private AdminLoginLogoutService adminService;
	@Autowired
	private StudentService studService;
	
	@Autowired 
	private CourseService cService;
	
	@PostMapping("/leaveCourse/{courseid}/{studentid}/{dateOfBirth}")
	public ResponseEntity<String> leaveCourse(
			@PathVariable("courseid") Integer cId,
			@PathVariable("studentid") Integer sId, 
			@PathVariable("dateOfBirth") String dob,
			@RequestHeader("key") String key) 
			throws StudentException,AdminException, CourseException{
		
		String response= cService.leaveCourse( sId,cId, key,dob);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
			
	}
	
	@PostMapping("/getCourses/{studentid}/{dateOfBirth}")
	public ResponseEntity<List<Course>> getCoursesOfStudent(
			@PathVariable("studentid") Integer sId, 
			@PathVariable("dateOfBirth") String dob)
			throws StudentException,AdminException, CourseException{
		
		List<Course> courseList= studService.getCourseList(sId, dob);
		
		return new ResponseEntity<>(courseList,HttpStatus.OK);
			
	}
	
}
