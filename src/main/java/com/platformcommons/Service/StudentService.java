package com.platformcommons.Service;

import java.util.List;

import com.platformcommons.DTOs.StudentDTO;
import com.platformcommons.Entities.Course;
import com.platformcommons.Entities.Student;
import com.platformcommons.Exceptions.AdminException;
import com.platformcommons.Exceptions.CourseException;
import com.platformcommons.Exceptions.StudentException;

public interface StudentService {

	public Student admitStudent(Student studDto,String key) throws StudentException,AdminException;
	
	
	public List<Student> getByName(String name,String key) throws StudentException,AdminException;
	
	public List<Student> getByCourse(String name,String key) throws StudentException,AdminException,CourseException;
	
	public List<Course> getCourseList(Integer studid,String dob)
			throws AdminException,CourseException,StudentException;

}
