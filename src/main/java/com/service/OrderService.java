package com.service;

import java.util.List;

import com.dto.Order;
import com.dto.User;
import com.exception.DatabaseException;

public interface OrderService {

	public Order getOrderById(int orderId);

	public List<Order> getOrdersByUser(User user);

	public void addOrder(Order order) throws DatabaseException;

	public int updateOrder(Order order) throws DatabaseException;
	
	public void checkOut(User user, Integer addressId, Integer cardId) throws DatabaseException; 
}
