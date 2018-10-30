package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dto.CartItem;
import com.dto.User;
import com.exception.DatabaseException;
import com.repository.CartItemRepository;

@Service
@Transactional(rollbackFor=DatabaseException.class)
public class CartItemServiceImpl implements CartItemService{

	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public CartItem getCartItemById(int cartItemId) {
		return cartItemRepository.getCartItemById(cartItemId);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public List<CartItem> getCartItemByUser(User user) {
		return cartItemRepository.getCartItemByUser(user);
	}
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public int haveCartItem(CartItem cartItem) {
		return cartItemRepository.haveCartItem(cartItem);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int addCartItem(CartItem cartItem) throws DatabaseException {
		return cartItemRepository.addCartItem(cartItem);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int updateCartItem(CartItem cartItem) throws DatabaseException {
		return cartItemRepository.updateCartItem(cartItem);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteCartItemByUser(User user) throws DatabaseException {
		cartItemRepository.deleteCartItemByUser(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int deleteCartItemById(int cartItemId) throws DatabaseException {
		return cartItemRepository.deleteCartItemById(cartItemId);
	}

}
