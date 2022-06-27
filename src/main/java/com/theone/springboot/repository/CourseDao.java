package com.theone.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theone.springboot.entity.CourseBean;

public interface CourseDao extends JpaRepository<CourseBean, Integer> {

	
}