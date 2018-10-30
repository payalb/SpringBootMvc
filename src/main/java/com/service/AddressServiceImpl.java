package com.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dto.Address;
import com.dto.User;
import com.exception.DatabaseException;
import com.repository.AddressRepository;

@Service
@Transactional(rollbackFor = DatabaseException.class)
public class AddressServiceImpl implements AddressService{

	@Autowired
	AddressRepository addressRepository;
	
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public Address getAddressById(int addressId) throws DatabaseException {
		return addressRepository.getAddressById(addressId);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public List<Address> getAddressByUser(User user) {
		return addressRepository.getAddressByUser(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int addAddress(Address address) throws DatabaseException {
		return addressRepository.addAddress(address);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int updateAddress(Address address) throws DatabaseException {
		return addressRepository.updateAddress(address);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int deleteAddressById(int addressId) throws DatabaseException {
		return addressRepository.deleteAddressById(addressId);
	}

}
