package com.theone.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.theone.springboot.entity.CourseBean;
import com.theone.springboot.entity.Member;
import com.theone.springboot.entity.Order;
import com.theone.springboot.repository.CourseDao;
import com.theone.springboot.repository.OrderDao;
import com.theone.springboot.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl  implements OrderService{
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	CourseDao courseDao;
	
	@Override
	public boolean isDup(Integer pk) {
		return orderDao.existsById(pk);
	}
	
	@Override
	public Order saveOrUpdate(Order order) {
		CourseBean course = courseDao.findById(Integer.valueOf(order.getProductId())).get();
		order.setCourseBean(course);
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
	
	@Override
	public void saveOrder(String courseid,Member member) {
		CourseBean course = courseDao.findById(Integer.parseInt(courseid)).get();
		Order order = new Order();
		order.setCourseBean(course);
		order.setUserId(member.getUserid());
		order.setTotalPrice(course.getPrice());		
		orderDao.save(order);
//		Order order = orderDao.findById(6).get();
		System.err.println(order.getCourseBean().getCoursePicUrl());
		
	}
	
}
