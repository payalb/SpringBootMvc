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

import com.dto.Card;
import com.dto.User;
import com.exception.DatabaseException;

@Repository
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DatabaseException.class)
public class CardRepositoryImpl implements CardRepository{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public Card getCardById(int cardId) {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Card> cq = cb.createQuery(Card.class);
		Root<Card> root = cq.from(Card.class);
		cq.where(cb.equal(root.get("cardId"), cardId));
		Card card = session.createQuery(cq).uniqueResult();
		session.close();
		return card;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public List<Card> getCardsByUser(User user) {
		Session session = sessionFactory.openSession();
		List<Card> cards = new ArrayList<>();
		try {
			user = session.load(User.class, user.getUserId());
			cards = user.getCards();
		} finally {
			session.close();
		}
		return cards;
	}

	@Override
	public int addCard(Card card) throws DatabaseException {
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		s.save(card);
		s.getTransaction().commit();
		s.close();
		if (card.getCardId() == 0) {
			throw new DatabaseException("Unable to insert card data.");
		}
		return card.getCardId();
	}

	@Override
	public int updateCard(Card card) throws DatabaseException {
		Session session = sessionFactory.openSession();
		String hql = "update Card set cardName = :cardName, cardNum = :cardNum, expiration = :expiration" 
				+ ", cvv = :cvv where cardId = :cardId";
		Transaction transaction = session.beginTransaction();
		int row = session.createQuery(hql)
				.setParameter("cardName", card.getCardName())
				.setParameter("cardNum", card.getCardNum())
				.setParameter("expiration", card.getExpiration())
				.setParameter("cvv", card.getCvv())
				.setParameter("cardId", card.getCardId())
				.executeUpdate();
		transaction.commit();
		session.close();
		if (row == 0) {
			throw new DatabaseException("Unable to update card data.");
		}
		return row;
	}

	@Override
	public int deleteCardById(int cardId) throws DatabaseException {
		Session session = sessionFactory.openSession();
		String hql = "delete from Card where cardId = :cardId";
		Transaction transaction = session.beginTransaction();
		int row = session.createQuery(hql)
				.setParameter("cardId", cardId)
		        .executeUpdate();
		transaction.commit();
		session.close();
		if (row == 0) {
			throw new DatabaseException("Unable to delete card data.");
		}
		return row;
	}
}
