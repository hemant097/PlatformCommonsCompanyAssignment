package com.platformcommons.Service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platformcommons.Entities.Course;
import com.platformcommons.Entities.Student;
import com.platformcommons.Exceptions.AdminException;
import com.platformcommons.Exceptions.CourseException;
import com.platformcommons.Exceptions.StudentException;
import com.platformcommons.Repository.CourseDAO;
import com.platformcommons.Repository.CurrentSessionDAO;
import com.platformcommons.Repository.StudentDAO;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	StudentDAO sDAO;
	@Autowired
	CourseDAO cDAO;
	@Autowired
	CurrentSessionDAO currSessDAO;
	
	
	public boolean adminLoggedIn(String key) {
		
		if(currSessDAO.findByUniqueid(key)!=null)
			return true;
		else
			return false;
	}
	
	public boolean studentExists(Integer id,String dob) {
		
		LocalDate ld = LocalDate.parse(dob);
		Optional<Student> optStudent = sDAO.findById(id);
		if(optStudent.isPresent()) {
			Student s = optStudent.get();
			if(s.getDateOfBirth().compareTo(ld)==0)
				return true;
		}
		
		return false;
		
	}
	
	public Course addCourse(Course course,String key) throws AdminException, CourseException {
		if(!adminLoggedIn(key))
			throw new AdminException("Admin not logged in");
		
		if(cDAO.findByCourseName(course.getCourseName()).isPresent() )
			throw new CourseException("Course already present");
		
		
		return cDAO.save(course);
	}

	@Override
	public String assignCourseToStudent(Integer studid, Integer courseid, String key)
			throws AdminException, CourseException, StudentException {
		
		if(!adminLoggedIn(key))
			throw new AdminException("Admin not logged in");
		
		Optional<Course> optCourse = cDAO.findById(courseid);
		
		if(optCourse.isEmpty() )
			throw new CourseException("Course does not exist");
		
		Optional<Student> optStudent = sDAO.findById(studid);
		
		
		if(optStudent.isEmpty())
			throw new StudentException("Student does not exist");
		
		Student student = optStudent.get();
		Course course = optCourse.get();
		
		course.getStudentList().add(student);
		student.getCourses().add(course);
		
		cDAO.save(course);
		
		return "successfully added";
	}

	@Override
	public String leaveCourse(Integer studid, Integer courseid, String key,String dob)
			throws AdminException, CourseException, StudentException {
		if(!adminLoggedIn(key))
			throw new AdminException("Admin not logged in");
		
		Optional<Course> optCourse = cDAO.findById(courseid);
		
		if(optCourse.isEmpty() )
			throw new CourseException("Course does not exist");
		
		Optional<Student> optStudent = sDAO.findById(studid);
		
		
		if(optStudent.isEmpty())
			throw new StudentException("Student does not exist");
		
		if(!studentExists(studid, dob))
			throw new StudentException("Student date of birth wrong");
		
		Student student = optStudent.get();
		Course course = optCourse.get();
		
		course.getStudentList().remove(student);
		student.getCourses().remove(course);
		
		cDAO.save(course);
		
		return "successfully left this course";
	}

}
