package com.repository;

import com.dto.User;
import com.exception.DatabaseException;

public interface UserRepository {
	
	public User getUserById(int id);

	public int addUser(User user) throws DatabaseException;

	public int updateUser(User user) throws DatabaseException;

}
