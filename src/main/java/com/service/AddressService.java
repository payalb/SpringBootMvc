package com.service;

import java.util.List;

import com.dto.Address;
import com.dto.User;
import com.exception.DatabaseException;

public interface AddressService {

	public Address getAddressById(int addressId) throws DatabaseException;

	public List<Address> getAddressByUser(User user);
	
	public int addAddress(Address address) throws DatabaseException;
	
	public int updateAddress(Address address) throws DatabaseException;
	
	public int deleteAddressById(int addressId) throws DatabaseException;
}
