package com.theone.springboot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
@Table(name = "Order_Table")
@Component
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	private String userId;
	private String productId;
	private Integer totalPrice;
	@Column(columnDefinition = "Date")
	private Date orderDate;
	private String state;
	
	@OneToOne
    @JoinColumn(name="Order_Course_ID")
	private CourseBean courseBean;
	
	@ManyToOne
    @JoinColumn(name="Order_Member_ID")
    @JsonBackReference
    private Member member;
	
	public Order() {	}
	
	public Order(Integer orderId, String userId, String productId, Integer totalPrice, Date orderDate, String state) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.productId = productId;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
		this.state = state;
	}

	
	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public CourseBean getCourseBean() {
		return courseBean;
	}

	public void setCourseBean(CourseBean courseBean) {
		this.courseBean = courseBean;
	}

	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order [orderId=");
		builder.append(orderId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", productId=");
		builder.append(productId);
		builder.append(", totalPrice=");
		builder.append(totalPrice);
		builder.append(", orderDate=");
		builder.append(orderDate);
		builder.append(", state=");
		builder.append(state);
		builder.append("]");
		return builder.toString();
	}
	
}
