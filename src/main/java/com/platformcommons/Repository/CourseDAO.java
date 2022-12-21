package com.platformcommons.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platformcommons.Entities.Course;
@Repository
public interface CourseDAO extends JpaRepository<Course, Integer>{

	public Optional<Course> findByCourseName(String courseName);
}


