package com.service;

import java.util.List;

import com.dto.CartItem;
import com.dto.User;
import com.exception.DatabaseException;

public interface CartItemService {
	
	public int haveCartItem(CartItem cartItem);

	public CartItem getCartItemById(int cartItemId);

	public List<CartItem> getCartItemByUser(User user);

	public int addCartItem(CartItem cartItem) throws DatabaseException;

	public int updateCartItem(CartItem cartItem) throws DatabaseException;

	public void deleteCartItemByUser(User user) throws DatabaseException;
	
	public int deleteCartItemById(int cartItemId) throws DatabaseException;
}
