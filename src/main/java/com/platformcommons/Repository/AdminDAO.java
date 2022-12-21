package com.platformcommons.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platformcommons.Entities.Admin;

@Repository
public interface AdminDAO extends JpaRepository<Admin, Integer> {

	public Admin findByMobile(String mobileNum);
	
	public Admin findByEmail(String email);
}
