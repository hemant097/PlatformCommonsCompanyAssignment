package com.platformcommons.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platformcommons.DTOs.AdminDTO;
import com.platformcommons.DTOs.LoginDTO;
import com.platformcommons.Entities.Admin;
import com.platformcommons.Entities.CurrentSession;
import com.platformcommons.Exceptions.AdminException;
import com.platformcommons.Repository.AdminDAO;
import com.platformcommons.Repository.CurrentSessionDAO;

import net.bytebuddy.utility.RandomString;

@Service
public class AdminLoginLogoutServiceImpl implements AdminLoginLogoutService {

	@Autowired
	private AdminDAO aDAO;
	@Autowired
	private CurrentSessionDAO csDAO;
	
	@Override
	public Admin signUp(AdminDTO adminDto) throws AdminException {
		
		
		Admin admin = aDAO.findByMobile(adminDto.getMobile());
		
		if(admin==null) {
			admin = new Admin();
			
			admin.setEmail(adminDto.getEmail());
			admin.setName(adminDto.getName());
			admin.setMobile(adminDto.getMobile());
			admin.setPassword(adminDto.getPassword());
			
			return aDAO.save(admin);
			
		}
		else
			throw new AdminException("Admin already exists");
	}

	@Override
	public String login(LoginDTO loginDto) throws AdminException {
		Admin admin = aDAO.findByEmail(loginDto.getEmail());

		if(admin==null) {
			return "no admin is registered with this email";
		}
		
		Optional<CurrentSession> isAdmin = csDAO.findById(admin.getAdminid());
		
		if(isAdmin.isPresent()) {
			throw new AdminException("Admin already logged in");
		}
		
		if(admin.getPassword().equals(loginDto.getPassword())) {
			String key = RandomString.make(6);
			CurrentSession currSession = new CurrentSession(admin.getAdminid(), key,LocalDateTime.now());
			csDAO.save(currSession);
			return currSession.toString();
			
		}
		else return "Password does not match";
	}

	@Override
	public String logout(String key) throws AdminException {
		CurrentSession currSession = csDAO.findByUniqueid(key);
		
		if(currSession==null)
			throw new AdminException("admin not logged in");
		
		csDAO.delete(currSession);
		
		return "Logged Out Successfully";
	}

}
