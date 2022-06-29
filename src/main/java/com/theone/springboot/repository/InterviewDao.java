package com.theone.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theone.springboot.entity.Interview;


public interface InterviewDao extends JpaRepository<Interview, Integer> {
	
	

}
