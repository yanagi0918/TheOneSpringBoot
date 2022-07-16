package com.theone.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.theone.springboot.entity.Member;
import com.theone.springboot.entity.Order;

public interface OrderDao extends JpaRepository<Order, Integer>{
	
//	public String findByUserIdAndOrderId(String userId ,String orderId);
	
	List<Order> findByCourseBeanCourseNameAndMember(String courseName,Member member);
	
	List<Order> findByCourseBeanCourseCategoryAndMember(String courseCategory,Member member);
	
	List<Order> findByStateAndMember(String State,Member member);
	
	List<Order> getByMember(Member Member);
	
	List<Order> findByOrderIdAndMember(Integer orderId,Member member);
	
	@Query(nativeQuery = true, value = "select sum(total_price)\r\n"+ "from order_table")
    Integer findTotalPrice();
	
}


