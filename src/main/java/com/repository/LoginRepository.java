package com.repository;

import com.dto.Login;
import com.exception.DatabaseException;

public interface LoginRepository {
	
	public Login getLogin(Login login);

	public Login addLogin(Login login) throws DatabaseException;
	
	public Login updateLogin(Login login);
	
	public boolean existEmail(String email);
}
