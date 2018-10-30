package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.User;
import com.exception.DatabaseException;
import com.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional(readOnly = true)
	public User getUserById(int id) {
		return userRepository.getUserById(id);
	}

	@Override
	public int addUser(User user) throws DatabaseException {
		return userRepository.addUser(user);
	}

	@Override
	public int updateUser(User user) throws DatabaseException {
		return userRepository.updateUser(user);
	}

}
