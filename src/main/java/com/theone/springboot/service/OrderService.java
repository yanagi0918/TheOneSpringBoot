package com.theone.springboot.service;

import java.util.List;
import java.util.Optional;

import com.theone.springboot.entity.Order;

public interface OrderService {
	
	boolean isDup(Integer pk);

	Order saveOrUpdate(Order order);

	List<Order> getAllOrders();

	Optional<Order> getOrder(Integer pk);

	void deleteOrder(Integer pk);
	
	public void test();

}
