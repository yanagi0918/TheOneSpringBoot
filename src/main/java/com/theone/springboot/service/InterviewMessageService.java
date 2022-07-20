package com.theone.springboot.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.theone.springboot.entity.CommentMessage;
import com.theone.springboot.entity.InterviewMessage;


public interface InterviewMessageService {

	List<InterviewMessage> findByInterviewCvNo(Integer cv_no);
	
	
	InterviewMessage save( InterviewMessage interviewMessage);
	
	Optional<InterviewMessage> findById(Integer id);
}
