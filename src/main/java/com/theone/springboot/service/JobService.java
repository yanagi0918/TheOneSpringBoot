package com.theone.springboot.service;

import java.util.List;
import java.util.Optional;

import com.theone.springboot.entity.Company;
import com.theone.springboot.entity.Job;

public interface JobService {
	boolean isDup(Integer pk);

	Job saveOrUpdate(Job job);

	List<Job> getAllJobs();

	Optional<Job> getJob(Integer pk);

	void delete(Integer pk);

	List<Job> getBySalary(String salary);

	List<Job> getByJobdescriptionAndSalary(String jobdescription, String salary);
	
	List<Job> findByCompId(Integer compid);
	
	List<Job> findByCompany(Company company);

	Job saveOrUpdate2(Job job);

	List<Job> findByJobdescription(String jobdescription);
	
	List<Job> findByTitleContaining(String title);
	
	Job findByJobid(Integer jobid);
	
	List<Job> findByTitleContainingAndJobdescription(String title,String jobdescription);
}
