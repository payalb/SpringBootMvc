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
		return cartItemRepository.findById(cartItemId).get();
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public List<CartItem> getCartItemByUser(User user) {
		return cartItemRepository.getCartItemByUser(user);
	}
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public int haveCartItem(CartItem cartItem) {
		return cartItemRepository.findById(cartItem.getCartItemId()).get().getCartItemId();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int addCartItem(CartItem cartItem) throws DatabaseException {
		return cartItemRepository.save(cartItem).getCartItemId();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int updateCartItem(CartItem cartItem) throws DatabaseException {
		return cartItemRepository.save(cartItem).getCartItemId();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteCartItemByUser(User user) throws DatabaseException {
		cartItemRepository.deleteCartItemByUser(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteCartItemById(int cartItemId) throws DatabaseException {
		 cartItemRepository.deleteById(cartItemId);
	}

}
