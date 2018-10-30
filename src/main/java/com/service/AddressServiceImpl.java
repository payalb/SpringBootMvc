package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dto.Address;
import com.dto.User;
import com.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	AddressRepository addressRepository;
	
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public Address getAddressById(int addressId)  {
		return addressRepository.findById(addressId).get();
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public List<Address> getAddressByUser(User user) {
		return addressRepository.findByUsers_userId(user.getUserId());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int addAddress(Address address) {
		return addressRepository.save(address).getAddressId();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation=Isolation.READ_COMMITTED)
	public int updateAddress(Address address) {
		return addressRepository.save(address).getAddressId();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation=Isolation.READ_COMMITTED)
	public void deleteAddressById(int addressId) {
		 addressRepository.deleteById(addressId);
	}

}
