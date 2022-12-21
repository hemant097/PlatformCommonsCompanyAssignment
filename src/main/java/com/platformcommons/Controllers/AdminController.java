package com.platformcommons.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platformcommons.DTOs.AdminDTO;
import com.platformcommons.DTOs.CourseDTO;
import com.platformcommons.DTOs.LoginDTO;
import com.platformcommons.DTOs.StudentDTO;
import com.platformcommons.Entities.Admin;
import com.platformcommons.Entities.Course;
import com.platformcommons.Entities.Student;
import com.platformcommons.Exceptions.AdminException;
import com.platformcommons.Exceptions.CourseException;
import com.platformcommons.Exceptions.StudentException;
import com.platformcommons.Service.AdminLoginLogoutService;
import com.platformcommons.Service.CourseService;
import com.platformcommons.Service.StudentService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminLoginLogoutService adminService;
	@Autowired
	private StudentService studService;
	
	@Autowired 
	private CourseService cService;
	
	@PostMapping("/signUp")
	public ResponseEntity<Admin> saveUser(@Valid @RequestBody AdminDTO admin) throws AdminException{
		
		Admin savedAdmin = adminService.signUp(admin);
		
		return new ResponseEntity<Admin>(savedAdmin,HttpStatus.OK);
	}
	
	@PostMapping("/logIn")
	public ResponseEntity<String> logIn(@Valid @RequestBody LoginDTO loginDto) throws AdminException{
		
		String key = adminService.login(loginDto);
		
		return new ResponseEntity<String>(key,HttpStatus.OK);
	}
	
	@DeleteMapping("/logOut")
	public ResponseEntity<String> logOut(@Valid @RequestHeader("key") String key) throws AdminException{
		
		String res = adminService.logout(key);
		
		return new ResponseEntity<String>(res,HttpStatus.OK);
	}
	
	@PostMapping("/addStudent")
	
	public ResponseEntity<Student> addStudent(
			@Valid @RequestBody Student student,
			@RequestHeader("key") String key) throws StudentException, AdminException{
		
		Student savedStudent = studService.admitStudent(student, key);
		
		return new ResponseEntity<>(savedStudent,HttpStatus.OK);
	}
	
	@GetMapping("/studentsByName/{name}")
	public ResponseEntity<List<Student>> getStudentsByName(
			@Valid @PathVariable("name") String name,
			@RequestHeader("key") String key) throws StudentException, AdminException{
		
		List<Student> list = studService.getByName(name, key);
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping("/studentsByCourse/{courseName}")
	public ResponseEntity<List<Student>> getStudentsByCourseName(
			@Valid @PathVariable("courseName") String name,
			@RequestHeader("key") String key) throws StudentException, AdminException, CourseException{
		
		List<Student> list = studService.getByCourse(name, key);
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
@PostMapping("/addCourse")
	
	public ResponseEntity<Course> addCourse(
			@Valid @RequestBody Course c,
			@RequestHeader("key") String key) throws AdminException, CourseException{
		
		Course course = cService.addCourse(c, key);
		
		return new ResponseEntity<>(course,HttpStatus.OK);
	}

	@PostMapping("/assigncoursetostudent/{courseid}/{studentid}")
	public ResponseEntity<String> assignCourseToStudent(
			@PathVariable("courseid") Integer cId,
			@PathVariable("studentid") Integer sId, 
			@RequestHeader("key") String key) 
			throws StudentException,AdminException, CourseException{
		
		String response= cService.assignCourseToStudent( sId,cId, key);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
			
	}
	
}
