package com.repository;

import java.util.List;

import com.dto.Order;
import com.dto.User;
import com.exception.DatabaseException;

public interface OrderRepository {
	
	public Order getOrderById(int orderId);

	public List<Order> getOrdersByUser(User user);

	public void addOrder(Order order) throws DatabaseException;
	
	public int updateOrder(Order order) throws DatabaseException;
}
