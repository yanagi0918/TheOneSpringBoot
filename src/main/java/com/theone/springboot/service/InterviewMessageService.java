package com.theone.springboot.service;

import java.util.List;
import java.util.Map;

import com.theone.springboot.entity.InterviewMessage;


public interface InterviewMessageService {

	List<InterviewMessage> findByInterviewCvNo(Integer cv_no);
	
//	Map<String,Object>addMessage(InterviewMessage interviewMessage);
	
	InterviewMessage save( InterviewMessage interviewMessage);
}
