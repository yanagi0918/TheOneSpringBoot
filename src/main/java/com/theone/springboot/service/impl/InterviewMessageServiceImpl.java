package com.theone.springboot.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<InterviewMessage> findByInterviewCvNo(Integer cv_no) {
		return interviewMessageDao.findByInterviewCvNo(cv_no);
	}


//	@Override
//	public Map<String,Object>addMessage(InterviewMessage interviewMessage) {
//		Map<String,Object> resultMap=new HashMap<>();
//		interviewMessage.setTime(LocalDateTime.now());
//		Integer result= interviewMessageDao.insertMessage(interviewMessage);
//		if(result>0) {
//			resultMap.put("code", 200);
//			resultMap.put("message", "發送訊息成功");
//		}else {
//			resultMap.put("code", 400);
//			resultMap.put("message", "!!發送訊息失敗");
//			
//		}
//		
//		return resultMap;
//	}


	@Override
	public InterviewMessage save(InterviewMessage interviewMessage) {
		return interviewMessageDao.save(interviewMessage);
	}

	
}
