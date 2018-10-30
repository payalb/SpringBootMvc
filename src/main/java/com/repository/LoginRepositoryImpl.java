package com.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.Login;
import com.exception.DatabaseException;

@Repository
public class LoginRepositoryImpl implements LoginRepository{

	@Autowired SessionFactory sessionFactory;
//	@Autowired
//	HibernateTemplate hibernateTemplate;
	
	@Override
	public Login getLogin(Login login) {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Login> cq = cb.createQuery(Login.class);
		Root<Login> root = cq.from(Login.class);
		cq.where(cb.equal(root.get("email"), login.getEmail())
				, cb.equal(root.get("password"), login.getPassword()));
		login = session.createQuery(cq).uniqueResult();
		session.close();
//		hibernateTemplate.get(Login.class, login.getLoginId());
		return login;
	}

	@Override
	public Login addLogin(Login login) throws DatabaseException {
		
		Session s = sessionFactory.openSession();
		try {
			s.beginTransaction();
			s.save(login.getUser());
			s.save(login);
			s.getTransaction().commit();	
		} finally {
			s.close();
		}
		if (login.getLoginId() == 0) {
			throw new DatabaseException("Unable to insert login data.");
		}
		return getLogin(login);
	}

	@Override
	public Login updateLogin(Login login) {
		Session s = sessionFactory.openSession();
		try {
			s.beginTransaction();
			s.update(login);
			s.getTransaction().commit();	
		} finally {
			s.close();
		}
		return getLogin(login);
	}

	@Override
	public boolean existEmail(String email) {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Login> cq = cb.createQuery(Login.class);
		Root<Login> root = cq.from(Login.class);
		cq.where(cb.equal(root.get("email"), email));
		Login login = session.createQuery(cq).uniqueResult();
		session.close();
		if (login != null) {
			return true;
		}
		return false;
	}

}
