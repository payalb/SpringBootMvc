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
		return userRepository.findById(id).get();
	}

	@Override
	public int addUser(User user) throws DatabaseException {
		return userRepository.save(user).getUserId();
	}

	@Override
	public int updateUser(User user) throws DatabaseException {
		return userRepository.save(user).getUserId();
	}

}
