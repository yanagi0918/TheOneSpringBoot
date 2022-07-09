package com.theone.springboot.entity;
import java.io.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@SuppressWarnings("serial")
@Entity
@Table(name = "Job_Table")
public class Job implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer job_id;
	private String title;
	private String jobdescription;
	private String qualification;
	private Integer required_number;
	private String salary;
	private String description;
	private String compId;
	public Job() {}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getJob_id() {
		return job_id;
	}

	public void setJob_id(Integer job_id) {
		this.job_id = job_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJobdescription() {
		return jobdescription;
	}

	public void setJobdescription(String jobdescription) {
		this.jobdescription = jobdescription;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public Integer getRequired_number() {
		return required_number;
	}

	public void setRequired_number(Integer required_number) {
		this.required_number = required_number;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public Job(Integer job_id, String title, String jobdescription, String qualification, Integer required_number,
			String salary, String description, String compId) {
		super();
		this.job_id = job_id;
		this.title = title;
		this.jobdescription = jobdescription;
		this.qualification = qualification;
		this.required_number = required_number;
		this.salary = salary;
		this.description = description;
		this.compId = compId;
	}
	public Job(String title, String jobdescription, String qualification, Integer required_number, String salary, String description,
			String compId) {
		super();
		this.title = title;
		this.jobdescription = jobdescription;
		this.qualification = qualification;
		this.required_number = required_number;
		this.salary = salary;
		this.description = description;
		this.compId = compId;
	}
	public Job(Integer job_id) {
		super();
		this.job_id = job_id;
	}
	
	
}