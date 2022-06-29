package com.theone.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.theone.springboot.entity.Order;
import com.theone.springboot.repository.OrderDao;
import com.theone.springboot.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl  implements OrderService{
	
	@Autowired
	OrderDao orderDao;
	
	@Override
	public boolean isDup(Integer pk) {
		return orderDao.existsById(pk);
	}
	
	@Override
	public Order saveOrUpdate(Order order) {
		return orderDao.save(order);
	}
	
	@Override
	public List<Order> getAllOrders() {
		return orderDao.findAll();
	}
	
	@Override
	public Optional<Order> getOrder(Integer pk) {
		return orderDao.findById(pk);
	}
	
	@Override
	public void deleteOrder(Integer pk) {
		orderDao.deleteById(pk);
	}
	
}
