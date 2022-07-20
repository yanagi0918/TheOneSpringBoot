package com.theone.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.theone.springboot.entity.InterviewMessage;
import com.theone.springboot.repository.InterviewMessageDao;
import com.theone.springboot.service.InterviewMessageService;

@Service
@Transactional
public class InterviewMessageServiceImpl implements InterviewMessageService {
	
	@Autowired
	InterviewMessageDao interviewMessageDao;
	@Override
	public List<InterviewMessage> findByInterviewCvNo(Integer cn_no) {
		return interviewMessageDao.findByInterviewCvNo(cn_no);
	}

	@Override
	public InterviewMessage save(InterviewMessage interviewMessage) {
		return interviewMessageDao.save(interviewMessage);
	}

	@Override
	public Optional<InterviewMessage> findById(Integer id) {
		return interviewMessageDao.findById(id);
	}




	
}
