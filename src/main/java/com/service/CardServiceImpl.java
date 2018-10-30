package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dto.Card;
import com.dto.User;
import com.exception.DatabaseException;
import com.repository.CardRepository;

@Service
@Transactional(rollbackFor=DatabaseException.class)
public class CardServiceImpl implements CardService{

	@Autowired
	private CardRepository cardRespository;
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public Card getCardById(int cardId) {
		return cardRespository.getCardById(cardId);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public List<Card> getCardsByUser(User user) {
		return cardRespository.getCardsByUser(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int addCard(Card card) throws DatabaseException {
		return cardRespository.addCard(card);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int updateCard(Card card) throws DatabaseException {
		return cardRespository.updateCard(card);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int deleteCardById(int cardId) throws DatabaseException {
		return cardRespository.deleteCardById(cardId);
	}

}
