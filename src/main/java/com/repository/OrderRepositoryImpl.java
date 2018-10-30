package com.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dto.Order;
import com.dto.User;
import com.exception.DatabaseException;

@Repository
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DatabaseException.class)
public class OrderRepositoryImpl implements OrderRepository {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	HibernateTemplate template;

	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public Order getOrderById(int orderId) {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);
		Root<Order> root = cq.from(Order.class);
		cq.where(cb.equal(root.get("orderId"), orderId));
		Order order = session.createQuery(cq).uniqueResult();
		session.close();
		return order;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public List<Order> getOrdersByUser(User user) {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);
		Root<Order> root = cq.from(Order.class);
		cq.where(cb.equal(root.get("user"), user));
		List<Order> orders = session.createQuery(cq).list();
		session.close();
		return orders;
	}

	@Override
	public void addOrder(Order order) throws DatabaseException {
//		Session s = sessionFactory.openSession();
//		try {
//			s.beginTransaction();
//			s.save(order);
//			s.getTransaction().commit();
//			if (order.getOrderId() == 0) {
//				throw new DatabaseException("Unable to insert order data.");
//			}
//			throw new DatabaseException("Interrupt");
//		} finally {
//			s.close();
//		}
//		
//		return order.getOrderId();
		template.save(order);
//		throw new DatabaseException("Interrupt");
	}

	@Override
	public int updateOrder(Order order) throws DatabaseException {
		Session session = sessionFactory.openSession();
		String hql = "update order set status = :status where orderId = :orderId";
		Transaction transaction = session.beginTransaction();
		int row = session.createQuery(hql)
				.setParameter("fullName", order.getStatus())
				.setParameter("orderId", order.getOrderId())
				.executeUpdate();
		transaction.commit();
		session.close();
		if (row == 0) {
			throw new DatabaseException("Unable to update card data.");
		}
		return row;
	}

}
