package com.theone.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.theone.springboot.entity.Interview;
import com.theone.springboot.repository.InterviewDao;
import com.theone.springboot.service.InterviewService;

@Service
@Transactional
public class InterviewServiceImpl implements InterviewService {

	@Autowired
	InterviewDao interviewDao;

	@Override
	public boolean isDup(Integer pk) {
		return interviewDao.existsById(pk);
	}

	@Override
	public Interview saveOrUpdate(Interview interview) {
		return interviewDao.save(interview);
	}

	@Override
	public List<Interview> getAllInterviews() {
		return interviewDao.findAll();
	}

	@Override
	public Optional<Interview> getInterview(Integer pk) {
		return interviewDao.findById(pk);
	}

	@Override
	public void deleteInterview(Integer pk) {
		interviewDao.deleteById(pk);
	}

}
