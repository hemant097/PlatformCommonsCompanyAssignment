package com.platformcommons.Service;

import com.platformcommons.DTOs.AdminDTO;
import com.platformcommons.DTOs.LoginDTO;
import com.platformcommons.Entities.Admin;
import com.platformcommons.Exceptions.AdminException;


public interface AdminLoginLogoutService {

	public Admin signUp(AdminDTO admin) throws AdminException;
	
	public String login(LoginDTO loginDto) throws AdminException ;
	
	public String logout(String key) throws AdminException ;
	
}
