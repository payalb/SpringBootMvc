package com.service;

import java.util.List;

import com.dto.Card;
import com.dto.User;
import com.exception.DatabaseException;

public interface CardService {
	public Card getCardById(int cardId);

	public List<Card> getCardsByUser(User user);

	public int addCard(Card card) throws DatabaseException;
	
	public int updateCard(Card card) throws DatabaseException;
	
	public int deleteCardById(int cardId) throws DatabaseException;
}
