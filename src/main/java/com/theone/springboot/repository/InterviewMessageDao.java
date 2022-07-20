package com.theone.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theone.springboot.entity.InterviewMessage;



public interface InterviewMessageDao extends JpaRepository<InterviewMessage, Integer> {

	List<InterviewMessage> findByInterviewCvNo(Integer cv_no);
	
	
//	@SQLInsert(sql = "INSERT INTO Interview_Message(name,words,time)"+
//							"value(#{name},#{words},#{time})")
//	int insertMessage(InterviewMessage interviewMessage);
}
