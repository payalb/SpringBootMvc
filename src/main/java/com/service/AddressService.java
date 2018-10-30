package com.service;

import java.util.List;

import com.dto.Address;
import com.dto.User;

public interface AddressService {

	public Address getAddressById(int addressId);

	public List<Address> getAddressByUser(User user);
	
	public int addAddress(Address address);
	
	public int updateAddress(Address address);
	
	public void deleteAddressById(int addressId);
}
