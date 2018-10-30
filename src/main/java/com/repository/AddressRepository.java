package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dto.Address;

public interface AddressRepository extends CrudRepository<Address, Integer>{

	public List<Address> findByUsers_userId(int userId);
	
}
