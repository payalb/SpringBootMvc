package com.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dto.Address;
import com.dto.User;
import com.exception.DatabaseException;

@Repository
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DatabaseException.class)
public class AddressRepositoryImpl implements AddressRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public Address getAddressById(int addressId) throws DatabaseException {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		Address address = null;
		try {
			CriteriaQuery<Address> cq = cb.createQuery(Address.class);
			Root<Address> root = cq.from(Address.class);
			cq.where(cb.equal(root.get("addressId"), addressId));
			address = session.createQuery(cq).uniqueResult();
			if (address == null)
				throw new DatabaseException("Unable to get the address information.");
		} finally {
			session.close();
		}
		return address;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public List<Address> getAddressByUser(User user) {
		Session session = sessionFactory.openSession();
		List<Address> addresses = new ArrayList<>();
		try {
			user = session.load(User.class, user.getUserId());
			addresses = user.getAddresses();
		} finally {
			session.close();
		}
		return addresses;
	}
	
	@Override
	public int addAddress(Address address) throws DatabaseException {
		Session s = sessionFactory.openSession();
		System.out.println(address);
		try {
			s.beginTransaction();
			s.save(address);
			s.getTransaction().commit();
			if (address.getAddressId() == 0) {
				throw new DatabaseException("Unable to insert address data.");
			}
		} finally {
			s.close();
		}
		return address.getAddressId();
	}

	@Override
	public int updateAddress(Address address) throws DatabaseException {
		Session session = sessionFactory.openSession();
		String hql = "update Address set fullName = :fullName, street = :street, streetTwo = :streetTwo, " 
				+ "city = :city, state = :state, zip = :zip, country = :country where addressId = :addressId";
		int row = 0;
		try {
			Transaction transaction = session.beginTransaction();
			row = session.createQuery(hql)
					.setParameter("fullName", address.getFullName())
					.setParameter("street", address.getStreet())
					.setParameter("streetTwo", address.getStreetTwo())
					.setParameter("city", address.getCity())
					.setParameter("state", address.getState())
					.setParameter("zip", address.getZip())
					.setParameter("country", address.getCountry())
					.setParameter("addressId", address.getAddressId())
					.executeUpdate();
			transaction.commit();
			if (row == 0) {
				throw new DatabaseException("Unable to update address data.");
			}
		} finally {
			session.close();
		}
		return row;
	}

	@Override
	public int deleteAddressById(int addressId) throws DatabaseException {
		Session session = sessionFactory.openSession();
		String hql = "delete from Address where addressId = :addressId";
		int row = 0;
		try {
			Transaction transaction = session.beginTransaction();
			row = session.createQuery(hql)
					.setParameter("addressId", addressId)
			        .executeUpdate();
			transaction.commit();
			if (row == 0) {
				throw new DatabaseException("Unable to delete address data.");
			}
		} finally {
			session.close();
		}
		return row;
	}

}
