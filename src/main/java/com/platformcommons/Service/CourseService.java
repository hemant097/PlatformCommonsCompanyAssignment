package com.platformcommons.Service;

import com.platformcommons.Entities.Course;
import com.platformcommons.Exceptions.AdminException;
import com.platformcommons.Exceptions.CourseException;
import com.platformcommons.Exceptions.StudentException;

public interface CourseService {

	public Course addCourse(Course course,String key) throws AdminException,CourseException;

	public String assignCourseToStudent(Integer studid,Integer courseid,String key)
			throws AdminException,CourseException,StudentException;
	
	public String leaveCourse(Integer studid,Integer courseid,String key,String dob)
			throws AdminException,CourseException,StudentException;
}
