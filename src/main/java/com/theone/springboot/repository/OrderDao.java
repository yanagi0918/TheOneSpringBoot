package com.theone.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theone.springboot.entity.Order;

public interface OrderDao extends JpaRepository<Order, Integer>{

}


