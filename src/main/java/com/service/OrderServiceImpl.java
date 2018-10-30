package com.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dto.Address;
import com.dto.Card;
import com.dto.CartItem;
import com.dto.Order;
import com.dto.OrderStatus;
import com.dto.Product;
import com.dto.User;
import com.exception.DatabaseException;
import com.repository.AddressRepository;
import com.repository.CardRepository;
import com.repository.CartItemRepository;
import com.repository.OrderRepository;
import com.repository.ProductRepository;

@Service
@Transactional(rollbackFor=DatabaseException.class)
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CartItemRepository cartRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private CardRepository cardRepository;
	
	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public Order getOrderById(int orderId) {
		return orderRepository.getOrderById(orderId);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public List<Order> getOrdersByUser(User user) {
		return orderRepository.getOrdersByUser(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void addOrder(Order order) throws DatabaseException {
		orderRepository.addOrder(order);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int updateOrder(Order order) throws DatabaseException {
		return orderRepository.updateOrder(order);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void checkOut(User user, Integer addressId, Integer cardId) throws DatabaseException {
		List<CartItem> cartList = cartRepository.getCartItemByUser(user);
		Map<Product, Integer> products = new HashMap<>();
		int orderAmount = 0;
		for (int i = 0; i < cartList.size(); i++) {
			CartItem item = cartList.get(i);
			Product product = item.getProduct();
			if (product.getStock() < item.getAmount()) {
				throw new DatabaseException("Don't have enough stock.");
			}
			orderAmount += item.getAmount();
			product.setStock(product.getStock() - item.getAmount());
			productRepository.updateProduct(product);
			products.put(product, item.getAmount());
		}
		Address address = addressRepository.getAddressById(addressId);
		Card card = cardRepository.getCardById(cardId);
		Order order = new Order(user, address, card, products, new Date(new java.util.Date().getTime())
				, orderAmount, OrderStatus.ACTIVE);
		orderRepository.addOrder(order);
		cartRepository.deleteCartItemByUser(user);
	}
	
}
