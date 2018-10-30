package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dto.Card;
import com.dto.User;

public interface CardRepository extends CrudRepository<Card, Integer> {
	
	public List<Card> getCardByUser(User user);

}
