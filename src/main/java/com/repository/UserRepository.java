package com.repository;

import org.springframework.data.repository.CrudRepository;

import com.dto.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	

}
