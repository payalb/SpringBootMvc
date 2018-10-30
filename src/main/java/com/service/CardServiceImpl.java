package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dto.Card;
import com.dto.User;
import com.repository.CardRepository;

@Service
public class CardServiceImpl implements CardService{

	@Autowired
	private CardRepository cardRespository;
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public Card getCardById(int cardId) {
		return cardRespository.findById(cardId).get();
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public List<Card> getCardsByUser(User user) {
		return cardRespository.getCardByUser(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int addCard(Card card){
		return cardRespository.save(card).getCardId();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int updateCard(Card card){
		return cardRespository.save(card).getCardId();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteCardById(int cardId)  {
		 cardRespository.deleteById(cardId);
	}

}
