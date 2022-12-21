package com.platformcommons.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platformcommons.DTOs.StudentDTO;
import com.platformcommons.Entities.Course;
import com.platformcommons.Entities.Student;
import com.platformcommons.Entities.StudentAddress;
import com.platformcommons.Exceptions.AdminException;
import com.platformcommons.Exceptions.CourseException;
import com.platformcommons.Exceptions.StudentException;
import com.platformcommons.Repository.CourseDAO;
import com.platformcommons.Repository.CurrentSessionDAO;
import com.platformcommons.Repository.StudentDAO;

@Service
public class StudentServiceImpl implements StudentService {
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
	
	@Override
	public Student admitStudent(Student stud,String key) throws StudentException,AdminException {
	
		if(!adminLoggedIn(key))
			throw new AdminException("Admin not logged in");
		
		if(sDAO.findByEmail(stud.getEmail()).isPresent() )
			throw new StudentException("Student already present");
	
		List<StudentAddress> sAddrList = stud.getAddresses();
		
		for(StudentAddress s: sAddrList)
			s.setStud(stud);
		
		return sDAO.save(stud);
		
	}

	@Override
	public List<Student> getByName(String name,String key) throws StudentException, AdminException {
		if(!adminLoggedIn(key))
			throw new AdminException("Admin not logged in");
		
		
		List<Student> studentList = null;
		
		studentList = sDAO.findByName(name);
		
		if(studentList.size()==0)
			throw new StudentException("no students with this name");
		
		return studentList;
	}

	@Override
	public List<Student> getByCourse(String courseName, String key) throws StudentException, AdminException, CourseException {
		
		if(!adminLoggedIn(key))
			throw new AdminException("Admin not logged in");
		
		List<Student> studentList = null;
		
		Optional<Course> optCourse = cDAO.findByCourseName(courseName);
		
		if(optCourse.isEmpty())
			throw new CourseException("No course ");
		
		studentList = optCourse.get().getStudentList();
		
		if(studentList.size()==0 || studentList==null)
			throw new StudentException("No students in this course");
		
		return studentList;
	}

	@Override
	public List<Course> getCourseList(Integer studid, String dob)
			throws AdminException, CourseException, StudentException {
		Optional<Student> optStudent = sDAO.findById(studid);
		
		
		if(optStudent.isEmpty())
			throw new StudentException("Student does not exist");
		
		if(!studentExists(studid, dob))
			throw new StudentException("Student date of birth wrong");
		
		Student student = optStudent.get();
		
		return student.getCourses();
	}
	
	

	

}
