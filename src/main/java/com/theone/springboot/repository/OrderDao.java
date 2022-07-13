package com.theone.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theone.springboot.entity.Member;
import com.theone.springboot.entity.Order;

public interface OrderDao extends JpaRepository<Order, Integer>{
	
//	public String findByUserIdAndOrderId(String userId ,String orderId);
	
	List<Order> findByCourseBeanCourseName(String courseName);
	
	List<Order> findByCourseBeanCourseCategory(String courseCategory);
	
	List<Order> findByState(String State);
	
	List<Order> getByMember(Member Member);
	
}


