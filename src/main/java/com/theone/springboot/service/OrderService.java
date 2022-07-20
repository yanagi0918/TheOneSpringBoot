package com.theone.springboot.service;

import java.io.Writer;
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
	
	//會員查詢訂單
	List<Order> findByOrderIdAndMember (Integer orderId,Member member);
	
	//會員查詢課程
	List<Order> findByCourseBeanCourseNameAndMember(String courseName,Member member);
	
	//會員查詢課程分類
	List<Order> findByCourseBeanCourseCategoryAndMember(String courseCategory,Member member);
	
	//會員查詢訂單狀態
	List<Order> findByStateAndMember(String State,Member member);
	
	List<Order> findByMember(Member member);
	
	Optional<Order> findOrder(Integer pk);
	
	//價格加總
	Integer findTotalPrice();
	
	//CSV
	void csvExport(Writer writer);
	
	//寄信
	public void sendNotifyEmail(String recipient, String subject, String message);
}
