package com.theone.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.theone.springboot.entity.Company;
import com.theone.springboot.entity.Job;
import com.theone.springboot.repository.CompanyDao;
import com.theone.springboot.repository.JobDao;
import com.theone.springboot.service.JobService;

@Transactional
@Service
public class JobServiceImpl implements JobService{
	@Autowired
	private JobDao jobDao;
	
	@Autowired
	private CompanyDao companyDao;
	
	@Override
	public boolean isDup(Integer pk) {
		
		return jobDao.existsById(pk);
	}	
	@Override
	public Job saveOrUpdate(Job job) {
		Company company = companyDao.findById(Integer.valueOf(job.getCompId())).get();
		job.setCompany(company);
		return jobDao.save(job);
	}

	@Override
	public List<Job> getAllJobs() {
		
		return jobDao.findAll();
	}

	@Override
	public Optional<Job> getJob(Integer pk) {
		
		return jobDao.findById(pk);
	}

	@Override
	public void delete(Integer pk) {

		jobDao.deleteById(pk);
	}
	@Override
	public List<Job> getByJobdescription(String jobdescription) {
		return jobDao.getByJobdescription(jobdescription);
	}
	@Override
	public List<Job> getBySalary(String salary) {
		return jobDao.getBySalary(salary);
	}
	@Override
	public List<Job> getByJobdescriptionAndSalary(String jobdescription, String salary) {
		return getByJobdescriptionAndSalary(jobdescription, salary);
	}
	@Override
	public List<Job> findByCompId(Integer compId) {
		return jobDao.findByCompId(compId);
	}


}
