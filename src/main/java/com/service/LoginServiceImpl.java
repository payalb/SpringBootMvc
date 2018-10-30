package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.Login;
import com.exception.DatabaseException;
import com.repository.LoginRepository;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	@Transactional(readOnly = true)
	public Login getLogin(Login login) {
		return loginRepository.getLogin(login);
	}

	@Override
	public Login addLogin(Login login) throws DatabaseException {
		return loginRepository.addLogin(login);
	}

	@Override
	public Login updateLogin(Login login) {
		return loginRepository.updateLogin(login);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existEmail(String email) {
		return loginRepository.existEmail(email);
	}

}
