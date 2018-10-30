package com.service;

import com.dto.User;
import com.exception.DatabaseException;

public interface UserService {

	public User getUserById(int id);

	public int addUser(User user) throws DatabaseException;

	public int updateUser(User user) throws DatabaseException;
}
