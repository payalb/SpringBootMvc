package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dto.CartItem;
import com.dto.User;
import com.exception.DatabaseException;

public interface CartItemRepository extends CrudRepository<CartItem, Integer>{
	
	
	public List<CartItem> getCartItemByUser(User user);

	public void deleteCartItemByUser(User user) throws DatabaseException;

}
