package com.theone.springboot.service;

import java.util.List;
import java.util.Optional;

import com.theone.springboot.entity.Job;


public interface JobService {
	boolean isDup(Integer pk);
	
	Job saveOrUpdate(Job job);
	
	List<Job> getAllJobs();
	
	Optional<Job> getJob(Integer pk);

	void delete(Integer pk);
	
}
