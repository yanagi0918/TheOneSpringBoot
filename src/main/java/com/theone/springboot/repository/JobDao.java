package com.theone.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theone.springboot.entity.Company;
import com.theone.springboot.entity.Job;

public interface JobDao extends JpaRepository<Job, Integer>{

	List<Job> getByJobdescription(String jobdescription);
	
	List<Job> getBySalary(String salary);
	
	List<Job> getByJobdescriptionAndSalary(String jobdescription,String salary);
	
	List<Job> findByCompId(Integer compId);
	
	List<Job> findByCompany(Company company);
}
