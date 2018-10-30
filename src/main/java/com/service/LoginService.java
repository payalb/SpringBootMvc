package com.service;

import com.dto.Login;
import com.exception.DatabaseException;


public interface LoginService {
	
	public boolean existEmail(String email);
	
	public Login getLogin(Login login);
	
	public Login addLogin(Login login) throws DatabaseException;
	
	public Login updateLogin(Login login);
	
}
