package com.theone.springboot.service;

import java.util.List;
import java.util.Optional;

import com.theone.springboot.entity.Interview;

public interface InterviewService {

	
	boolean isDup(Integer pk);

	Interview saveOrUpdate(Interview interview);

	List<Interview> getAllInterviews();

	Optional<Interview> getInterview(Integer pk);


	void deleteInterview(Integer pk);

	List<Interview> findByUserId(String userid);

}
