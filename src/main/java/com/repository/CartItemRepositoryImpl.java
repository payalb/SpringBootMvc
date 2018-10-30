package com.repository;

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

import com.dto.CartItem;
import com.dto.User;
import com.exception.DatabaseException;

@Repository
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DatabaseException.class)
public class CartItemRepositoryImpl implements CartItemRepository {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public int haveCartItem(CartItem cartItem) {
		int itemNum = 0;
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<CartItem> cq = cb.createQuery(CartItem.class);
		Root<CartItem> root = cq.from(CartItem.class);
		cq.where(cb.equal(root.get("product"), cartItem.getProduct())
				, cb.equal(root.get("user"), cartItem.getUser()));
		cartItem = session.createQuery(cq).uniqueResult();
		if (cartItem != null && cartItem.getAmount() > 0)
			itemNum = cartItem.getAmount();
		session.close();
		return itemNum;
	}
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public CartItem getCartItemById(int cartItemId) {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<CartItem> cq = cb.createQuery(CartItem.class);
		Root<CartItem> root = cq.from(CartItem.class);
		cq.where(cb.equal(root.get("cartItemId"), cartItemId));
		CartItem cartItem = session.createQuery(cq).uniqueResult();
		session.close();
		return cartItem;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public List<CartItem> getCartItemByUser(User user) {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<CartItem> cq = cb.createQuery(CartItem.class);
		Root<CartItem> root = cq.from(CartItem.class);
		cq.where(cb.equal(root.get("user"), user));
		List<CartItem> cartItems = session.createQuery(cq).list();
		session.close();
		return cartItems;
	}

	@Override
	public int addCartItem(CartItem cartItem) throws DatabaseException {
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		s.save(cartItem);
		s.getTransaction().commit();
		s.close();
		if (cartItem.getCartItemId() == 0) {
			throw new DatabaseException("Unable to insert cart item data.");
		}
		return cartItem.getCartItemId();
	}

	@Override
	public int updateCartItem(CartItem cartItem) throws DatabaseException {
		Session session = sessionFactory.openSession();
		String hql = "update cartItem set amount = :amount where cartItemId = :cartItemId";
		Transaction transaction = session.beginTransaction();
		int row = session.createQuery(hql)
				.setParameter("amount", cartItem.getAmount())
				.setParameter("cartItemId", cartItem.getCartItemId())
				.executeUpdate();
		transaction.commit();
		session.close();
		if (row == 0) {
			throw new DatabaseException("Unable to update cart item data.");
		}
		return row;
	}

	@Override
	public int deleteCartItemById(int cartItemId) throws DatabaseException {
		Session session = sessionFactory.openSession();
		String hql = "delete from cartItem where cartItemId = :cartItemId";
		int row = 0;
		try {
			Transaction transaction = session.beginTransaction();
			row = session.createQuery(hql)
					.setParameter("cartItemId", cartItemId)
			        .executeUpdate();
			if (row <= 0)
				throw new DatabaseException("Unable to delete cart item data.");
			transaction.commit();
		} finally {
			session.close();
		}
		
		return row;
	}
	
	public void deleteCartItemByUser(User user) throws DatabaseException {
		Session session = sessionFactory.openSession();
		String hql = "delete from cartItem where cartItemId = :cartItemId";
		List<CartItem> list = getCartItemByUser(user);
		Transaction transaction = session.beginTransaction();
		for (CartItem item : list) {
			int row = session.createQuery(hql)
					.setParameter("cartItemId", item.getCartItemId())
			        .executeUpdate();
			if (row <= 0)
				throw new DatabaseException("Unable to delete cart item data.");
		}
		transaction.commit();
		session.close();
	}

}
