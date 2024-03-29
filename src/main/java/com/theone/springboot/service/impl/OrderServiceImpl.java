package com.theone.springboot.service.impl;

import java.io.Writer;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.theone.springboot.entity.CourseBean;
import com.theone.springboot.entity.Member;
import com.theone.springboot.entity.Order;
import com.theone.springboot.repository.CourseDao;
import com.theone.springboot.repository.MemberDao;
import com.theone.springboot.repository.OrderDao;
import com.theone.springboot.service.OrderService;
import com.theone.springboot.utils.OrderCsvExporter;

@Service
@Transactional
public class OrderServiceImpl  implements OrderService{
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	CourseDao courseDao;
	
	@Autowired
	MemberDao memberDao;
	
	@Autowired
	OrderCsvExporter csvExporter;
	
	@Autowired
	JavaMailSender mailSender;
	
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
		order.setMember(member);
		order.setUserId(member.getUserid());
		order.setTotalPrice(course.getPrice());
		order.setProductId(courseid);
		order.setOrderDate(new Date());
		order.setState("已付款");
		orderDao.save(order);
//		Order order = orderDao.findById(6).get();
		System.err.println(order.getCourseBean().getCoursePicUrl());
		
	}

	@Override
	public List<Order> findByOrderIdAndMember (Integer orderId,Member member) {
		return orderDao.findByOrderIdAndMember(orderId,member);
	}

	@Override
	public List<Order> findByCourseBeanCourseNameAndMember(String courseName,Member member){
		return orderDao.findByCourseBeanCourseNameAndMember(courseName, member);
	}

	@Override
	public List<Order> findByCourseBeanCourseCategoryAndMember(String courseCategory,Member member) {
		return orderDao.findByCourseBeanCourseCategoryAndMember(courseCategory,member);
	}

	@Override
	public List<Order> findByStateAndMember(String State,Member member){
		return orderDao.findByStateAndMember(State, member);
	}

	@Override
	public List<Order> findByMember(Member member) {
		return orderDao.getByMember(member);
	}

	@Override
	public Optional<Order> findOrder(Integer pk) {
		 return orderDao.findById(pk);
	}

	@Override
	public Integer findTotalPrice() {
		return orderDao.findTotalPrice();
	}

	@Override
	public void csvExport(Writer writer) {
		List<Order> orders = orderDao.findAll();
		csvExporter.csvExport(writer, orders);
	}

	@Override
	public void sendNotifyEmail(String recipient, String subject, String message) {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom("eeit45theone@gmail.com");
			messageHelper.setTo(recipient);
			messageHelper.setSubject(subject);
			messageHelper.setText(message, true);
		};
		try {
			mailSender.send(messagePreparator);
			 System.out.println("sent");
		} catch (MailException e) {
			 System.out.println(e);
		}
	}

	
	
}
