package com.theone.springboot.service;

import java.util.List;
import java.util.Optional;

import com.theone.springboot.entity.Member;
import com.theone.springboot.entity.Order;

public interface OrderService {
	
	boolean isDup(Integer pk);

	Order saveOrUpdate(Order order);

	List<Order> getAllOrders();

	Optional<Order> getOrder(Integer pk);

	void deleteOrder(Integer pk);
	
	void saveOrder(String id,Member member);

	Order findByOrderId (Integer orderId);
	
	List<Order> findByCourseBeanCourseName(String courseName);
	
	List<Order> findByCourseBeanCourseCategory(String courseCategory);
	
	List<Order> findByState(String State);
	
	List<Order> findByMember(Member member);
}
