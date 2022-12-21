package com.platformcommons.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platformcommons.Entities.Student;
@Repository
public interface StudentDAO extends JpaRepository<Student, Integer>{ 

	public Optional<Student> findByEmail(String email);
	
	public List<Student> findByName(String name);
}
